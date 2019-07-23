package br.com.consultemed.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.consultemed.model.Consulta;
import br.com.consultemed.utils.JPAUtils;

public class ConsultaDaoImpl implements IConsultaDao {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = null;

	public void save(Consulta consulta) throws Exception  {

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

	public Consulta findById(Long id) throws Exception  {
		this.factory = emf.createEntityManager();
		Consulta consulta = new Consulta();
		try {
			consulta = factory.find(Consulta.class, id);
			return consulta;

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
		return null;
	}

	@Override
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

	@Override
	public void update(Consulta consulta) throws Exception {
		this.factory = emf.createEntityManager();

		try {
			factory.getTransaction().begin();
			factory.merge(consulta);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
	}

	@Override
	public Collection<Consulta> listAll() throws Exception {
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

	@Override
	public int countConsulta() throws Exception {
		
		this.factory = emf.createEntityManager();
		int numConsulta = 0;
		
		try {
			factory.getTransaction().begin();
			numConsulta = ((Number)this.factory.createNamedQuery("Consulta.findAllCount").getSingleResult()).intValue();
		     System.out.println(numConsulta);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return numConsulta;
	}

}
