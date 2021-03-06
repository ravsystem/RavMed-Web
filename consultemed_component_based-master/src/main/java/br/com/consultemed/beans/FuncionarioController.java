package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.services.FuncionarioService;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class FuncionarioController {

	@Getter
	@Setter
	private List<Funcionario> funcionarios;

	@Inject
	@Getter
	@Setter
	private Funcionario funcionario;
	
	@Getter
	@Setter
	private Funcionario funcionarioEditar;
	
	@Inject
	private FuncionarioService service;
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioEditar() {
		return funcionarioEditar;
	}

	public void setFuncionarioEditar(Funcionario funcionarioEditar) {
		this.funcionarioEditar = funcionarioEditar;
	}

	public String editar() {
		this.funcionario = this.funcionarioEditar;
		return "/pages/funcionarios/addFuncionarios.xhtml";
	}
	
	public String excluir() throws Exception {
		this.funcionario = this.funcionarioEditar;
		this.service.deletarFuncionario(this.funcionario.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/funcionarios/funcionarios.xhtml?faces-redirect=true";
	}
	
	public String novoFuncionario() {
		this.funcionario = new Funcionario();
		return "/pages/funcionarios/addFuncionarios.xhtml?faces-redirect=true";
	}
	
	public String addFuncionario() {
		
		if(this.service.validaEmail(this.funcionario.getEmail()) == true){
			warnEmail();
			System.out.println("Entrou aqui");
			return null;	
		}else if(this.service.validaCpf(this.funcionario.getCPF()) == true) {
			warnCpf();
			return null; 
			
		}else if(this.service.validaTelefone(this.funcionario.getTelefone()) == true) {
			warnTel();
			return null;
		}else {
			this.service.salvarFuncionario(this.funcionario);
			return "/pages/funcionarios/funcionarios.xhtml?faces-redirect=true";
		}
	}
	
	public List<Funcionario> listaFuncionarios(){
		this.funcionarios = this.service.listaFuncionario();
		return funcionarios;
	}
	
	public void warnCpf() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Funcionario cadastrado com esse CPF."));
    }
	
	public void warnEmail() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Funcionario cadastrado com esse Email."));
    }
	
	public void warnTel() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Funcionario cadastrado com esse Telefone."));
    }
}
