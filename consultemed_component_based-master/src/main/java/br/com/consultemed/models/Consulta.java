package br.com.consultemed.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries({ @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")})
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_CONSULTA")
public class Consulta {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "CODIGO")
	private String codigo;
	
	@Getter
	@Setter
	@Column(name = "MEDICO")
	private String medico;
	
	@Getter
	@Setter
	@Column(name = "PACIENTE")
	private String paciente;
	
	@Getter
	@Setter
	@Column(name = "DATA_AG")
	private String data_ag;
	
	@Getter
	@Setter
	@Column(name = "DATA_CONSULTA")
	private String data_consulta;
	
	public Consulta() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getData_ag() {
		return data_ag;
	}

	public void setData_ag(String data_ag) {
		this.data_ag = data_ag;
	}

	public String getData_consulta() {
		return data_consulta;
	}

	public void setData_consulta(String data_consulta) {
		this.data_consulta = data_consulta;
	}

}
