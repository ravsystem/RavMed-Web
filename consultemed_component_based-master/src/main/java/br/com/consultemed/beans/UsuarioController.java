/**
 * 
 */
package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Usuario;
import br.com.consultemed.services.UsuarioService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@ManagedBean
@Named
@RequestScoped
public class UsuarioController{

	
	@Getter
	@Setter
	private List<Usuario> usuarios;

	@Inject
	@Getter
	@Setter
	private Usuario usuario;
	
	@Getter
	@Setter
	private Usuario usuarioEditar;
	
	@Inject
	private UsuarioService service;
	
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioEditar() {
		return usuarioEditar;
	}

	public void setUsuarioEditar(Usuario usuarioEditar) {
		this.usuarioEditar = usuarioEditar;
	}

	public String editar() {
		this.usuario = this.usuarioEditar;
		return "/pages/usuarios/addUsuarios.xhtml";
	}
	
	public String excluir() throws Exception {
		this.usuario = this.usuarioEditar;
		this.service.deletarUsuario(this.usuario.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/usuarios/usuarios.xhtml?faces-redirect=true";
	}
	
	public String novoUsuario() {
		this.usuario = new Usuario();
		return "/pages/usuarios/addUsuarios.xhtml?faces-redirect=true";
	}
	
	public String addUsuario() {
		
		if(this.service.validaLogin(this.usuario.getLogin()) == true) {
			warnLogin();
			return null;
		}else {
			
			this.service.salvarUsuario(this.usuario);
			return "/pages/usuarios/usuarios.xhtml?faces-redirect=true";
		}
	}
	
	public List<Usuario> listaUsuario(){
		this.usuarios = this.service.listaUsuarios();
		return usuarios;
	}
	
	public void warnLogin() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Usuário cadastrado com esse Login."));
    }
	
}
