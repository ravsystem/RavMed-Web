package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

public class FuncionarioRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Funcionario> listaFuncionarios() {
		Query query = this.factory.createQuery("SELECT object(f) FROM Funcionario as f");
		return query.getResultList();
	}

	public Collection<Funcionario> listarFuncionarios() throws Exception {
		this.factory = emf.createEntityManager();
		List<Funcionario> contatos = new ArrayList<Funcionario>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Funcionario> query = factory.createNamedQuery("Funcionario.findAll", Funcionario.class);
			contatos = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return contatos;
	}

	public void salvarFuncionario(Funcionario funcionario) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (funcionario.getId() == null) {
				factory.persist(funcionario);
			} else {
				factory.merge(funcionario);
			}
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

	public void deleteById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		Funcionario funcionario = new Funcionario();

		try {

			funcionario = factory.find(Funcionario.class, id);
			factory.getTransaction().begin();
			factory.remove(funcionario);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}
	
	public boolean validaCpf(String cpf) {
		
		boolean resultado = false;
		
		this.factory = emf.createEntityManager();
		Query query = factory.createQuery("FROM Funcionario f where f.CPF = :CPF ");
		query.setParameter("CPF", cpf);
		
		List<Funcionario> listaFuncCpf = query.getResultList();
		
		if (listaFuncCpf.size() > 0) resultado = true;	
        
		return resultado;
	}
	
	public boolean validaEmail(String email) {
		
		boolean resultado = false;
		
		this.factory = emf.createEntityManager();
		Query query = factory.createQuery("FROM Funcionario func where func.email = :email ");
		query.setParameter("email", email);
		
		List<Funcionario> listaFuncEmail = query.getResultList();
		
		Query query2 = factory.createQuery("FROM Paciente paci where paci.email = :email ");
		query2.setParameter("email", email);
		
		List<Paciente> listaPacienteEmail = query2.getResultList();
		
		if (listaFuncEmail.size() > 0 && listaPacienteEmail.size() > 0) resultado = true;	
        
		return resultado;
	}

	public boolean validaTelefone(String telefone) {
	
	boolean resultado = false;
	
	this.factory = emf.createEntityManager();
	Query query = factory.createQuery("FROM Funcionario fc where fc.telefone = :telefone ");
	query.setParameter("telefone", telefone);
	
	List<Funcionario> listaFuncTelefone = query.getResultList();
	
	if (listaFuncTelefone.size() > 0) resultado = true;	
    
	return resultado;
	}

}
