<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Login Acesso</title>



<style type="text/css">

@import url(https://fonts.googleapis.com/css?family=Roboto:300);
@import url(https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css);

.login-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
    font-family: "Roboto", sans-serif;
    text-transform: uppercase;
    outline: 0;
    background: #1789af;
    width: 100%;
    border: 0;
    padding: 15px;
    color: #FFFFFF;
    font-size: 14px;
    -webkit-transition: all 0.3 ease;
    transition: all 0.3 ease;
    cursor: pointer;
}

.form button:hover, .form button:active, .form button:focus {
	background: #43A047;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
    color: #64c364;
    text-decoration: none;
}

.form .register-form {
	display: none;
}

.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

body {
    background: #76b852;
    background: -webkit-linear-gradient(right, #76b852, #8DC26F);
    background: -moz-linear-gradient(right, #76b852, #8DC26F);
    background: -o-linear-gradient(right, #76b852, #8DC26F);
    background: linear-gradient(to left, #5284b8, #e2ecef);
    font-family: "Roboto", sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}
</style>



</head>
<body>
	<div class="login-page">
	
		<div class="form">
			<h4>
		     	<c:if test="${ateLogo != null}">
			       	<div class="alert alert-info" role="alert">
				 		 ${ateLogo}
					</div>
		       </c:if>
		     </h4>
		     
		      	<c:if test="${solicitacao != null}">
			       	<div class="alert alert-success" role="alert">
				 		 ${solicitacao}
					</div>
		       </c:if>
		     </h4>
		
			<form class="login-form" action="loginController" method="POST">

				<input type="text"  name="login" placeholder="Login" /> 
				<input type="password"  name="senha" placeholder="Senha" />

				<button type="submit">Login</button>
				
				 <hr/>
				
				 <h4>
			      	<c:if test="${erro.erro != null}">
			        	<div class="alert alert-danger" role="alert">
					 		 ${erro.erro}
						</div>
			        </c:if>
			      </h4>
			      
			      <h4>
			      	<c:if test="${erro.login != null}">
			        	<div class="alert alert-danger" role="alert">
					 		 ${erro.login}
						</div>
			        </c:if>
			      </h4>
			      
			      <h4>
			      	<c:if test="${erro.senha != null}">
			        	<div class="alert alert-danger" role="alert">
					 		 ${erro.senha}
						</div>
			        </c:if>
			      </h4>
			      
			      <h4>
			      	<c:if test="${erro.inativo != null}">
			        	<div class="alert alert-danger" role="alert">
					 		 ${erro.inativo}
						</div>
			        </c:if>
			</form>
		      </h4> 
		</div>
		
						
		
	</div>

</body>

</html>