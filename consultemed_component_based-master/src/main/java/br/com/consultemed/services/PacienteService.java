package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.repository.repositories.PacienteRepository;

public class PacienteService {
	@Inject
	private PacienteRepository dao;
	
	public List<Paciente> listaPaciente(){
		return this.dao.listaPacientes();
	}
	
	public void salvarPaciente(Paciente paciente) {
		this.dao.salvarPaciente(paciente);
	}
	
	public void deletarPaciente(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public boolean validaCpf(String cpf) {
		boolean resultado = this.dao.validaCpf(cpf);
		return resultado;
	}
	
	public boolean validaEmail(String email) {
		boolean resultado = this.dao.validaEmail(email);
		return resultado;
	}
	
	public boolean validaTelefone(String telefone) {
		boolean resultado = this.dao.validaTelefone(telefone);
		return resultado;
	}
}
