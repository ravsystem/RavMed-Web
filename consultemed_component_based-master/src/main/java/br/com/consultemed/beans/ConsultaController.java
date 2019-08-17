package br.com.consultemed.beans;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Consulta;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.services.ConsultaService;
import br.com.consultemed.services.MedicoService;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class ConsultaController {

	private Date data_ag;
	private Date data_cs;
	
	@Getter
	@Setter
	private List<Consulta> consultas;
	
	@Getter
	@Setter
	private List<Medico> medicos;
	
	@Getter
	@Setter
	private List<Paciente> pacientes;

	@Inject
	@Getter
	@Setter
	private Consulta consulta;
	
	@Getter
	@Setter
	private Consulta consultaEditar;
	
	@Inject
	@Getter
	@Setter
	private Medico medico;
	
	@Inject
	@Getter
	@Setter
	private Paciente paciente;
	
	@Inject
	private ConsultaService service;
	
	@Inject
	private MedicoService MService;
	
	@Inject
	private PacienteService PService;
	
	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Consulta getConsultaEditar() {
		return consultaEditar;
	}

	public void setConsultaEditar(Consulta consultaEditar) {
		this.consultaEditar = consultaEditar;
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Date getData_ag() {
		return data_ag;
	}

	public void setData_ag(Date data_ag) {
		this.data_ag = data_ag;
	}

	public Date getData_cs() {
		return data_cs;
	}

	public void setData_cs(Date data_cs) {
		this.data_cs = data_cs;
	}

	public String editar() {
		this.consulta = this.consultaEditar;
		return "/pages/consultas/addConsultas.xhtml";
	}
	
	public String excluir() throws Exception {
		this.consulta = this.consultaEditar;
		this.service.deletarConsulta(this.consulta.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/consultas/consultas.xhtml?faces-redirect=true";
	}
	
	public String novaConsulta() {
		this.consulta = new Consulta();
		return "/pages/consultas/addConsultas.xhtml?faces-redirect=true";
	}
	
	public String addConsulta() {

		Calendar c = Calendar.getInstance();
		String cod2 = String.valueOf(c.get(Calendar.SECOND));
		String cod2_2 =  String.valueOf(c.get(Calendar.MILLISECOND));
		
		String cod3 = this.consulta.getPaciente().toUpperCase();

		char [] letras3 = cod3.toCharArray();
		
		System.out.println(cod2 +cod2_2 +letras3[0] +letras3[1]);
		
		String codigo = cod2 +cod2_2 +letras3[0] +letras3[1];
		
		this.consulta.setCodigo(codigo);
		
		this.service.salvarConsulta(this.consulta);
		return "/pages/consultas/consultas.xhtml?faces-redirect=true";
	}
	
	public List<Consulta> listaConsultas(){
		this.consultas = this.service.listaConsulta();
		return consultas;
	}
	
	public List<Medico> listaMedicos(){
		this.medicos = this.MService.selecionaNomeMedicos();
		return medicos;
	}
	
	public List<Paciente> listaPacientes(){
		this.pacientes = this.PService.selecionaNomepacientes();
		return pacientes;
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
