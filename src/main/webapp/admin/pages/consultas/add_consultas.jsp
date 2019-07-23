<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.consultemed.service.*"%>
<%@page import="br.com.consultemed.filter.*"%>
<%@page import="br.com.consultemed.controller.*"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="br.com.consultemed.model.*" %>
<%@page import="br.com.consultemed.dao.*" %>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:admin_tamplate title="Dashboard">
	
	
	<jsp:attribute name="content">
	
		<section class="content">
		
		
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Home</a></li>
		    <li class="breadcrumb-item active" aria-current="page">Consulta</li>
		  </ol>
		</nav>
		 
		<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Nova Consulta</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            
            <form role="form" method="POST" action="${pageContext.request.contextPath}/admin/consultas?action=list">
              <div class="box-body">
              
              	<div class="form-group col-md-12">
					<input type="hidden" name="id" value="${consulta.id}" readonly="readonly" required="required" class="form-control" id="id" placeholder="Digite o nome">
				</div>
              
                <div class="form-group">
                  <label for="nomeMedico">Médico</label>
                  	<br/>
                	<select class="form-control" id="nomeMedico">
                		<c:forEach var="n" items="${medicos}">
	                		<option value="${consulta.nomeMedico}">
	                		
	                			${n.nome}
	                		
	                		</option>
                		</c:forEach>
                	</select>
                		    
                </div>
                
                <div class="form-group">
                  <label for="nomePaciente">Paciente</label>
                  	<br/>
                	<select class="form-control" id="nomePaciente">
                		<c:forEach var="p" items="${pacientes}">
	                		<option value="${consulta.nomePaciente}">
	                		
	                			${p.nome}
	                		
	                		</option>
                		</c:forEach>
                	</select>
                		    
                </div>

                <div class="form-group">
				  <label for="example-date-input" class="col-2 col-form-label">Data de Consulta</label>
				  <div class="col-10">
				    <input class="form-control" type="date" name="dataConsulta" value="${consulta.dataConsulta}" id="example-date-input">
				  </div>
				</div>
                
                <div class="form-group">
				  <label for="example-date-input" class="col-2 col-form-label">Data de Retorno</label>
				  <div class="col-10">
				    <input class="form-control" type="date" name="dataAgenda" value="${consulta.dataAgenda}" id="example-date-input">
				  </div>
				</div>

              <div class="box-footer">
					<c:if test="${consulta.id != null}">
						<button type="submit" class="btn btn-warning">Editar</button>
	            	</c:if>
					
					<c:if test="${consulta.id == null}">
						<button name="enviar" type="submit" class="btn btn-primary">Salvar</button>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<a href="${pageContext.request.contextPath}/admin/consultas?action=list" class="btn btn-sm btn-default">Consultas</a>&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
            </form>
      	</div>
			
	 </section>
  
	</jsp:attribute>
</mt:admin_tamplate>
