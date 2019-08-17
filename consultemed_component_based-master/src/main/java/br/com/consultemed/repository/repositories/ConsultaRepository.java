package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Consulta;
import br.com.consultemed.utils.JPAUtils;

public class ConsultaRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Consulta> listaConsultas() {
		Query query = this.factory.createQuery("SELECT object(f) FROM Consulta as f");
		return query.getResultList();
	}
	
	public Collection<Consulta> listarConsultas() throws Exception {
		this.factory = emf.createEntityManager();
		List<Consulta> consultas = new ArrayList<Consulta>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Consulta> query = factory.createNamedQuery("Consulta.findAll", Consulta.class);
			consultas = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return consultas;
	}
	
	public void salvarConsulta(Consulta consulta) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (consulta.getId() == null) {
				factory.persist(consulta);
			} else {
				factory.merge(consulta);
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
		Consulta consulta = new Consulta();

		try {

			consulta = factory.find(Consulta.class, id);
			factory.getTransaction().begin();
			factory.remove(consulta);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}
	
	
}
