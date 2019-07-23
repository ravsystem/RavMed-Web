package br.com.consultemed.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.PacienteBusiness;
import br.com.consultemed.utils.Constantes;

@WebServlet("/admin/pacientes")
public class PacienteController extends HttpServlet{

	private static final String ID_PACIENTE = "id";
	private static final String TELEFONE_PACIENTE = "telefone";
	private static final String EMAIL_PACIENTE = "email";
	private static final String NOME_PACIENTE = "nome";
	private static final String IDADE_PACIENTE = "idade";
	private static final String CPF_PACIENTE = "cpf";

	@Inject
	private PacienteBusiness business;

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public PacienteController() {
		this.business = new PacienteBusiness();
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
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.PACIENTES);
		Collection<Paciente> pacientes = this.business.listAll();
		request.setAttribute("pacientes", pacientes);
		rd.forward(request, response);
		
	}

	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter(NOME_PACIENTE);
		String email = request.getParameter(EMAIL_PACIENTE);
		String telefone = request.getParameter(TELEFONE_PACIENTE);
		String idade = request.getParameter(IDADE_PACIENTE);
		String cpf = request.getParameter(CPF_PACIENTE);
		String id = request.getParameter(ID_PACIENTE);
			
		Paciente paciente = new Paciente();
		paciente.setEmail(email);
		paciente.setNome(nome);
		paciente.setTelefone(telefone);
		paciente.setIdade(idade);
		paciente.setCpf(cpf);
		
		if(id != "") {
			paciente.setId(Long.parseLong(id));
			request.setAttribute("editado", Constantes.PACIENTE + " " + nome + Constantes.PACIENTE_EDITADO);
		}else {
			request.setAttribute("cadastro", Constantes.PACIENTE + " "+ nome + Constantes.PACIENTE_SUCESSO);			
		}
		try {
			this.business.save(paciente);
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
		
		this.business.deleteById(Long.parseLong(request.getParameter(ID_PACIENTE)));
		request.setAttribute("remover", Constantes.PACIENTE + Constantes.PACIENTE_REMOVIDO);
		
		try {
			list(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {

		Paciente paciente = this.business.findById(Long.parseLong(request.getParameter(ID_PACIENTE)));
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
		request.setAttribute("paciente", paciente);
		rd.forward(request, response);
	}
}
