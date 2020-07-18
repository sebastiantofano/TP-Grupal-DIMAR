<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>DIMAR · Distribucion de Insumos Médicos</title>

<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<link href="${relativePath}/css/template/bootstrap.min.css" rel="stylesheet">
<link href="${relativePath}/css/template/datatables.min.css" rel="stylesheet">
<link href="${relativePath}/css/template/animate.css" rel="stylesheet">
<link href="${relativePath}/css/template/style.css" rel="stylesheet">
<link href="${relativePath}/css/footable/footable.core.css" rel="stylesheet">
<link href="${relativePath}/css/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- Gráficos -->
<link href="${relativePath}/css/chartist/chartist-custom.css" rel="stylesheet">
<script type="text/javascript">
	<c:set var="url"
	value="${requestScope['javax.servlet.forward.request_uri']}" />
	var currentUrl = '<c:out value="${url}"/>';
</script>
</head>
<body class="top-navigation">
	<div id="wrapper">
		<div id="page-wrapper">
			<div id="page-header" class="row border-bottom white-bg">
				<nav class="navbar navbar-static-top" role="navigation">
					<div class="navbar-header">
						<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
							<i class="fa fa-reorder"></i>
						</button>
						<a href="${relativePath}/home" class="navbar-brand">DIMAR</a>
					</div>
					<div class="navbar-collapse collapse" id="navbar">
						<ul class="nav navbar-nav">
							<li id="inicioLink"><a aria-expanded="false" role="button" href="${relativePath}/home"> Inicio </a></li>
							<li id="distribucionLink"><a aria-expanded="false" role="button" href="${relativePath}/distribucion"> Distribución </a></li>
							<li id="historialDistribucionesLink"><a aria-expanded="false" role="button" href="${relativePath}/historialDistribuciones"> Historial </a></li>
							<li id="responsablesLink"><a aria-expanded="false" role="button" href="${relativePath}/responsables"> Responsables </a></li>
							<li id="donacionesLink"><a aria-expanded="false" role="button" href="${relativePath}/donaciones"> Donaciones </a></li>
							<li id="mapaLink"><a aria-expanded="false" role="button" href="${relativePath}/mapa"> Mapa </a></li>
						</ul>
						<ul class="nav navbar-top-links navbar-right">
							<li><form:form action="./logout" method="POST">
									<button type="submit" class="btn btn-link">
										<i class="fa fa-sign-out"></i> Cerrar sesión
									</button>
								</form:form></li>
						</ul>
					</div>
				</nav>
			</div>
			<div id="body">
				<jsp:doBody />
			</div>
			<br />
			<div id="page-footer">
				<div class="footer">
					<div class="pull-right">Distribucion de Insumos Medicos</div>
					<div>
						<strong>DIMAR</strong> - UNLaM - 2020
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${relativePath}/js/template/jquery-3.1.1.min.js"></script>
<script src="${relativePath}/js/template/bootstrap.min.js"></script>
<script src="${relativePath}/js/template/plugins/metisMenu/metisMenu.min.js"></script>
<script src="${relativePath}/js/template/plugins/pace/pace.min.js"></script>
<script src="${relativePath}/js/template/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${relativePath}/js/template/plugins/dataTables/datatables.min.js"></script>
<script src="${relativePath}/js/template/app/inspinia.js"></script>
<script src="${relativePath}/js/template/plugins/footable/footable.js"></script>


<!-- Gráficos -->
<script src="${relativePath}/js/chartist/chartist.js"></script>
<script type="text/javascript">
	var setearLinkActivo = function() {
		if (currentUrl.includes('home')) {
			$("#inicioLink").addClass('active');
		}

		if (currentUrl.includes('distribucion')) {
			$("#distribucionLink").addClass('active');
		}

		if (currentUrl.includes('responsables')) {
			$("#responsablesLink").addClass('active');
		}

		if (currentUrl.includes('historialDistribuciones')) {
			$("#historialDistribucionesLink").addClass('active');
		}
		if (currentUrl.includes('donaciones')) {
			$("#donacionesLink").addClass('active');
		}
		if (currentUrl.includes('mapa')) {
			$("#mapaLink").addClass('active');
		}
	};

	setearLinkActivo();

	var lenguaje = {
		"sProcessing" : "Procesando...",
		"sLengthMenu" : "Mostrar _MENU_ registros",
		"sZeroRecords" : "No se encontraron resultados",
		"sEmptyTable" : "Sin informacion disponible para esta tabla",
		"sInfo" : "Mostrando del registro _START_ al _END_ de un total de _TOTAL_",
		"sInfoEmpty" : "Mostrando del registro 0 al 0 de un total de 0",
		"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
		"sInfoPostFix" : "",
		"sSearch" : "Buscar:",
		"sUrl" : "",
		"sInfoThousands" : ",",
		"sLoadingRecords" : "Cargando...",
		"oPaginate" : {
			"sFirst" : "Primero",
			"sLast" : "Último",
			"sNext" : "Siguiente",
			"sPrevious" : "Anterior"
		},
		"oAria" : {
			"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
			"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
		}
	};
</script>
</html>