/**
 * 
 */
package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Medico;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class MedicoRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Medico> listaMedicos() {
		Query query = this.factory.createQuery("SELECT object(m) FROM Medico as m");
		return query.getResultList();
	}

	public Collection<Medico> listarMedicos() throws Exception {
		this.factory = emf.createEntityManager();
		List<Medico> contatos = new ArrayList<Medico>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Medico> query = factory.createNamedQuery("Medico.findAll", Medico.class);
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

	public void salvarMedico(Medico medico) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (medico.getId() == null) {
					factory.persist(medico);		
			} else {
				factory.merge(medico);
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
		Medico medico = new Medico();

		try {

			medico = factory.find(Medico.class, id);
			factory.getTransaction().begin();
			factory.remove(medico);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}
	
	public boolean validaCrm(String crm) {
		
		boolean resultado = false;
		
		this.factory = emf.createEntityManager();
		Query query = factory.createQuery("FROM Medico m where m.crm = :crm ");
		query.setParameter("crm", crm);
		
		List<Medico> listaMedicoCrm = query.getResultList();
		
		if (listaMedicoCrm.size() > 0) resultado = true;	
        
		return resultado;
	}
	
	public boolean validaEmail(String email) {
		
		boolean resultado = false;
		
		this.factory = emf.createEntityManager();
		Query query = factory.createQuery("FROM Medico me where me.EMAIL = :email ");
		query.setParameter("EMAIL", email);
		
		List<Medico> listaMedicoEmail = query.getResultList();
		
		if (listaMedicoEmail.size() > 0) resultado = true;	
        
		return resultado;
	}
	
	public boolean validaTelefone(String telefone) {
		
		boolean resultado = false;
		
		this.factory = emf.createEntityManager();
		Query query = factory.createQuery("FROM Medico o where o.telefone = :telefone ");
		query.setParameter("telefone", telefone);
		
		List<Medico> listaMedicoEmail = query.getResultList();
		
		if (listaMedicoEmail.size() > 0) resultado = true;	
        
		return resultado;
	}
	
	public Medico selecionaMedico(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Medico p where p.id = :id");
		query.setParameter("id", id);
		
		List<Medico> medicos = query.getResultList();
		Medico medico = (Medico) medicos;
		return medico;
		
	}
	
	public List<Medico> selecionaNomeMedicos() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT med.nome FROM Medico med ");
		
		List<Medico> NomeMedicos = query.getResultList();
		return NomeMedicos;
		
	}

}
