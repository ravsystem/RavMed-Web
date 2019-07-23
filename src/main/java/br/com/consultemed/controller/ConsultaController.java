package br.com.consultemed.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultemed.model.Consulta;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.ConsultaBusiness;
import br.com.consultemed.service.MedicoBusiness;
import br.com.consultemed.service.PacienteBusiness;
import br.com.consultemed.utils.Constantes;

@WebServlet("/admin/consultas")
public class ConsultaController extends HttpServlet {

	private static final String ID_CONSULTA = "id";
	private static final String MEDICO_CONSULTA = "nomeMedico";
	private static final String PACIENTE_CONSULTA = "nomePaciente";
	private static final String DATA_CONSULTA = "dataConsulta";
	private static final String AGENDA_CONSULTA = "dataAgenda";

	@Inject
	private ConsultaBusiness business;
	
	@Inject
	private MedicoBusiness businessM;
	
	@Inject
	private PacienteBusiness businessP;

	private static final long serialVersionUID = 1L;

	public ConsultaController() {
		this.business = new ConsultaBusiness();
		this.businessM = new MedicoBusiness();
		this.businessP = new PacienteBusiness();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(Constantes.ACTION);

		try {
			switch (action) {
			case Constantes.NOVO:
				novo(request, response);
				break;
			case Constantes.DELETE:
				delete(request, response);
				break;
			case Constantes.EDITAR:
				editar(request, response);
				break;
			case Constantes.LISTAR :
				list(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.CONSULTAS);
		Collection<Consulta> consultas = this.business.listAll();
		request.setAttribute("consultas", consultas);
		rd.forward(request, response);
		
	}

	private void novo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_CONSULTAS);
		Collection<Medico> medicos = this.businessM.listAll();
		request.setAttribute("medicos", medicos);
		Collection<Paciente> pacientes = this.businessP.listAll();
		request.setAttribute("pacientes", pacientes);
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeMedico = request.getParameter(MEDICO_CONSULTA);
		String nomePaciente = request.getParameter(PACIENTE_CONSULTA);
		String dataConsulta = request.getParameter(DATA_CONSULTA);
		String dataAgenda = request.getParameter(AGENDA_CONSULTA);
		String id = request.getParameter(ID_CONSULTA);
			
		Consulta consulta = new Consulta();
		consulta.setNomeMedico(nomeMedico);
		consulta.setNomePaciente(nomePaciente);
		consulta.setDataConsulta(dataConsulta);
		consulta.setDataAgenda(dataAgenda);
		
		if(id != "") {
			consulta.setId(Long.parseLong(id));
			request.setAttribute("editado", Constantes.CONSULTA + " " + Constantes.CONSULTA_EDITADO);
		}else {
			request.setAttribute("cadastro", Constantes.CONSULTA + " " + Constantes.CONSULTA_SUCESSO);			
		}
		try {
			this.business.save(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			list(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		this.business.deleteById(Long.parseLong(request.getParameter(ID_CONSULTA)));
		request.setAttribute("remover", Constantes.CONSULTA + Constantes.CONSULTA_REMOVIDO);
		
		try {
			list(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {

		Consulta consulta = this.business.findById(Long.parseLong(request.getParameter(ID_CONSULTA)));
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_CONSULTAS);
		request.setAttribute("consulta", consulta);
		rd.forward(request, response);
	}

}
