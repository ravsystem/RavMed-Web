package br.com.consultemed.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Consulta.findAllCount", query = "SELECT COUNT(c) FROM Consulta c"),
	@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c")
})
@Entity
@Table(name="TB_CONSULTA")
public class Consulta extends AbstractEntity <Long>{

	private String nomePaciente;
	private String nomeMedico;
	private String dataConsulta;
	private String dataAgenda;
	
	public Consulta() {
		
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDataAgenda() {
		return dataAgenda;
	}

	public void setDataAgenda(String dataAgenda) {
		this.dataAgenda = dataAgenda;
	}

	
}
