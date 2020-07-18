<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="es" class="fullscreen-bg">
<head>
<title>Login | DIMAR - Insumos Médicos</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="css/login/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="css/login/css/demo.css">
<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
<link href="css/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<c:if test="${not empty msg}">
									<div class="alert alert-${msg[1]} alert-dismissable">${msg[0]}</div>
								</c:if>
								<div class="logo text-center">
									<img src="css/login/LOGO.png" alt="DIMAR logo" style="width: 300px;">
								</div>
								<p class="lead">Sistema de Ingreso</p>
							</div>
							<form:form action="validar-login" method="POST" modelAttribute="usuario" class="form-auth-small">
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">Email</label>
									<form:input path="email" id="signin-email" type="email" class="form-control" placeholder="Ingrese su usuario" />
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<form:input path="password" type="password" id="signin-password" class="form-control" placeholder="Ingrese su contraseña" />
								</div>
								<button type="submit" class="btn btn-primary btn-lg btn-block">INGRESAR</button>
								<div class="bottom"></div>
							</form:form>
							<form action="./IniciarsesionDesarrollo" method="POST">
								<button type="submit" class="btn btn-info btn-lg btn-block">Iniciar sesión como desarrollador</button>
							</form>
							<c:if test="${not empty error}">
								<h4>
									<span class="text-danger">${error}</span>
								</h4>
								<br>
							</c:if>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 style="font-size: 40px">Sistema de distribución</h1>
							<h1 style="font-size: 30px">de Insumos Médicos</h1>
							<p class="heading">Taller Web I</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
