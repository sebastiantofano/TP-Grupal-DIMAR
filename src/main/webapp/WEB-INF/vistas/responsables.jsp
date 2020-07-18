<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class="row wrapper border-bottom white-bg page-heading">
		<div class="col-lg-9">
			<h2>Responsables de los Establecimientos</h2>
			<ol class="breadcrumb">
			</ol>
		</div>
	</div>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<c:if test="${not empty establecimientos}">
				<c:forEach items="${establecimientos}" var="item">
					<div class="col-lg-3">
						<div class="contact-box">
							<a>
								<div class="col-sm-4">
									<div class="text-center">
										<img src="img/responsable.png" style="width: 80px" />
										<div class="m-t-xs font-bold">${item.responsable.titulo}</div>
									</div>
								</div>
								<div class="col-sm-8">
									<h3>
										<strong>${item.responsable.nombre} ${item.responsable.apellido}</strong>
									</h3>
									<p>
										<i class="fa fa-map-marker"></i> ${item.nombre}<br />${item.zona.nombre}<br />${item.ubicacion}
									</p>
									<address>
										<strong>Datos Personales:</strong><br> Edad: ${item.responsable.edad}<br> Identificador: ${item.responsable.id} <br> Zona: ${item.responsable.zona.nombre}
									</address>
								</div>
								<div class="clearfix"></div>
							</a>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</t:layout>
<script src="${relativePath}/js/vistas/responsables.js"></script>
