package br.com.consultemed.service;

import java.util.Collection;

import javax.inject.Inject;

import br.com.consultemed.dao.ConsultaDaoImpl;
import br.com.consultemed.model.Consulta;

public class ConsultaBusiness {

	@Inject
	private ConsultaDaoImpl dao;

	public ConsultaBusiness() {
		this.dao = new ConsultaDaoImpl();
	}
	
	public void save(Consulta consulta) throws Exception  {
		this.dao.save(consulta);
	}
	
	public void update(Consulta consulta) throws Exception  {
		this.dao.update(consulta);
	}
	
	public Collection<Consulta> listAll() throws Exception {
		return this.dao.listAll();
	}
	
	public Consulta findById(Long id) throws Exception {
		return this.dao.findById(id);
	}
	
	public void deleteById(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public int conut() throws Exception {
		return this.dao.countConsulta();
	}
}
