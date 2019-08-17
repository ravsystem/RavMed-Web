/**
 * 
 */
package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Medico;
import br.com.consultemed.services.MedicoService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@RequestScoped
public class MedicoController{
	
	@Getter
	@Setter
	private List<Medico> medicos;

	@Inject
	@Getter
	@Setter
	private Medico medico;
	
	@Getter
	@Setter
	private Medico medicoEditar;
	
	@Inject
	private MedicoService service;
	
	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medico getMedicoEditar() {
		return medicoEditar;
	}

	public void setMedicoEditar(Medico medicoEditar) {
		this.medicoEditar = medicoEditar;
	}

	public String editar() {
		this.medico = this.medicoEditar;
		return "/pages/medicos/addMedicos.xhtml";
	}
	
	public String excluir() throws Exception {
		this.medico = this.medicoEditar;
		this.service.deletarMedico(this.medico.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/medicos/medicos.xhtml?faces-redirect=true";
	}
	
	public String novoMedico() {
		this.medico = new Medico();
		return "/pages/medicos/addMedicos.xhtml?faces-redirect=true";
	}
	
	public String addMedico() {
		
		System.out.println(this.medico.getEmail());
		
		if(this.service.validaEmail(this.medico.getEmail()) == true){
			warnEmail();
			System.out.println("Entrou aqui");
			return null;
			
		}else if(this.service.validaCrm(this.medico.getCrm()) == true) {
			warnCrm();
			return null; 
			
		}else if(this.service.validaTelefone(this.medico.getTelefone()) == true) {
			warnTel();
			return null; 
			
		}else {
		this.service.salvarMedico(this.medico);
		return "/pages/medicos/medicos.xhtml?faces-redirect=true";
		
		}
	}
	
	public List<Medico> listaMedicos(){
		this.medicos = this.service.listaMedico();
		return medicos;
	}
	
	public void warnCrm() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe medico cadastrado com esse CRM."));
    }
	
	public void warnEmail() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe medico cadastrado com esse Email."));
    }
	
	public void warnTel() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe medico cadastrado com esse Telefone."));
    }
}
