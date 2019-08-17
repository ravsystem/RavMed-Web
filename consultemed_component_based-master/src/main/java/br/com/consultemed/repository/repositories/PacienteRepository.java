package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

public class PacienteRepository {
	
	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Paciente> listaPacientes() {
		Query query = this.factory.createQuery("SELECT object(p) FROM Paciente as p");
		return query.getResultList();
	}

	public Collection<Paciente> listarPacientes() throws Exception {
		this.factory = emf.createEntityManager();
		List<Paciente> contatos = new ArrayList<Paciente>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Paciente> query = factory.createNamedQuery("Paciente.findAll", Paciente.class);
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

	public void salvarPaciente(Paciente paciente) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (paciente.getId() == null) {
				factory.persist(paciente);
			} else {
				factory.merge(paciente);
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
		Paciente paciente = new Paciente();

		try {

			paciente = factory.find(Paciente.class, id);
			factory.getTransaction().begin();
			factory.remove(paciente);
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
		Query query = factory.createQuery("FROM Paciente pp where pp.cpf = :cpf ");
		query.setParameter("cpf", cpf);
		
		List<Medico> listaMedicoEmail = query.getResultList();
		
		if (listaMedicoEmail.size() > 0) resultado = true;	
        
		return resultado;
	}

	public boolean validaEmail(String email) {
	
	boolean resultado = false;
	
	this.factory = emf.createEntityManager();
	Query query = factory.createQuery("FROM Paciente pa where pa.email = :email ");
	query.setParameter("email", email);
	
	List<Medico> listaMedicoEmail = query.getResultList();
	
	if (listaMedicoEmail.size() > 0) resultado = true;	
    
	return resultado;
	}

	public boolean validaTelefone(String telefone) {
	
	boolean resultado = false;
	
	this.factory = emf.createEntityManager();
	Query query = factory.createQuery("FROM Paciente pc where pc.telefone = :telefone ");
	query.setParameter("telefone", telefone);
	
	List<Medico> listaMedicoEmail = query.getResultList();
	
	if (listaMedicoEmail.size() > 0) resultado = true;	
    
	return resultado;
	}

}
