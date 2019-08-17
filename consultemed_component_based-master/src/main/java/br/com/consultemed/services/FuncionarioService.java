package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.repository.repositories.FuncionarioRepository;

public class FuncionarioService {
	@Inject
	private FuncionarioRepository dao;
	
	public List<Funcionario> listaFuncionario(){
		return this.dao.listaFuncionarios();
	}
	
	public void salvarFuncionario(Funcionario funcionario) {
		this.dao.salvarFuncionario(funcionario);
	}
	
	public void deletarFuncionario(Long id) throws Exception {
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
