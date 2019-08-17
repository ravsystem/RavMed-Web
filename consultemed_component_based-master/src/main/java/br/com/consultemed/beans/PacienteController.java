package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class PacienteController {
	
	@Getter
	@Setter
	private List<Paciente> pacientes;

	@Inject
	@Getter
	@Setter
	private Paciente paciente;
	
	@Getter
	@Setter
	private Paciente pacienteEditar;
	
	@Inject
	private PacienteService service;
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Paciente getPacienteEditar() {
		return pacienteEditar;
	}

	public void setPacienteEditar(Paciente pacienteEditar) {
		this.pacienteEditar = pacienteEditar;
	}

	public String editar() {
		this.paciente = this.pacienteEditar;
		return "/pages/pacientes/addPacientes.xhtml";
	}
	
	public String excluir() throws Exception {
		this.paciente = this.pacienteEditar;
		this.service.deletarPaciente(this.paciente.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/pacientes/pacientes.xhtml?faces-redirect=true";
	}
	
	public String novoPaciente() {
		this.paciente = new Paciente();
		return "/pages/pacientes/addPacientes.xhtml?faces-redirect=true";
	}
	
	public String addPaciente() {
		
		if(this.service.validaCpf(this.paciente.getCPF()) == true) {
			warnCpf();
			return null;
		}else if(this.service.validaEmail(this.paciente.getEmail()) == true) {
			warnEmail();
			return null;
		}else if(this.service.validaEmail2(this.paciente.getEmail()) == true) {
			warnEmail2();
			return null;
		}else if(this.service.validaTelefone(this.paciente.getTelefone()) == true) {
			warnTelefone();
			return null;
		}else {
			this.service.salvarPaciente(this.paciente);
			return "/pages/pacientes/pacientes.xhtml?faces-redirect=true";
		}
	}
	
	public List<Paciente> listaPaciente(){
		this.pacientes = this.service.listaPaciente();
		return pacientes;
	}
	
	public void warnCpf() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Paciente cadastrado com esse CPF."));
    }
	
	public void warnEmail() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Paciente cadastrado com esse EMAIL."));
    }
	
	public void warnEmail2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Funcionário cadastrado com esse EMAIL."));
    }
	
	public void warnTelefone() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Opa!", "Já existe Paciente cadastrado com esse TELEFONE."));
    }
}
