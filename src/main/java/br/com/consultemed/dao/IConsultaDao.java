package br.com.consultemed.dao;

import java.util.Collection;

import br.com.consultemed.model.Consulta;

public interface IConsultaDao extends GenericDao<Consulta, Long>{

	public void save(Consulta consulta) throws Exception;
	
	public Consulta findById(Long id) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
	public void update(Consulta consulta) throws Exception;
	
	public Collection<Consulta> listAll() throws Exception;
	
	public int countConsulta() throws Exception;
}
