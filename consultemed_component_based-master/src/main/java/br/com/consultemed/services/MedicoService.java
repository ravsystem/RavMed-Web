/**
 * 
 */
package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Medico;
import br.com.consultemed.repository.repositories.MedicoRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class MedicoService {

	@Inject
	private MedicoRepository dao;
	
	public List<Medico> listaMedico(){
		return this.dao.listaMedicos();
	}
	
	public void salvarMedico(Medico medico) {
		this.dao.salvarMedico(medico);
	}
	
	public void deletarMedico(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public boolean validaCrm(String crm) {
		
		boolean resultado = this.dao.validaCrm(crm);
		return resultado;
	}
	
	public boolean validaEmail(String email) {
		
		boolean resultado = this.dao.validaCrm(email);
		return resultado;
	}
	
	public boolean validaTelefone(String telefone) {
		boolean resultado = this.dao.validaTelefone(telefone);
		return resultado;
	}
	
	public Medico selecionaMedico(Long codigo) {
		
		Medico medico = this.dao.selecionaMedico(codigo);
		return medico;
	}
	
	public List<Medico> selecionaNomeMedicos(){
		return this.dao.selecionaNomeMedicos();
	}
}
