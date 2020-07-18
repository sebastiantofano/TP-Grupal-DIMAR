<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-lg-6">
				<div class="ibox">
					<div class="ibox-title">
						<h5>Historial de distribuciones</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<table class="table table-hover grilla-dataTable">
							<thead>
								<tr>
									<th>Estado</th>
									<th>Fecha de Solicitud</th>
									<th>Fecha de Entrega</th>
									<th>Tipo de Distribucion</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${distribuciones}" var="item">
									<tr>
										<td><c:choose>
												<c:when test="${item.estado.getId() == 1}">
													<span class="label label-warning">${item.estado.getDescripcion()}</span>
												</c:when>
												<c:when test="${item.estado.getId() == 2}">
													<span class="label label-info">${item.estado.getDescripcion()}</span>
												</c:when>
												<c:when test="${item.estado.getId() == 3}">
													<span class="label label-primary">${item.estado.getDescripcion()}</span>
												</c:when>
												<c:when test="${item.estado.getId() == 4}">
													<span class="label label-success">${item.estado.getDescripcion()}</span>
												</c:when>
												<c:when test="${item.estado.getId() == 5}">
													<span class="label label-danger">${item.estado.getDescripcion()}</span>
												</c:when>
												<c:when test="${item.estado.getId() == 6}">
													<span class="label label-danger">${item.estado.getDescripcion()}</span>
												</c:when>
											</c:choose></td>
										<td><i class="fa fa-clock-o"></i> &nbsp;${item.fechaSolicitud}</td>
										<td><i class="fa fa-clock-o"></i> &nbsp;${item.fechaEntrega != null ? item.fechaEntrega : "Sin definir"}</td>
										<td class="text-navy">${item.tipoDistribucion.nombre}&nbsp;<a href="${relativePath}/distribucion/${item.getId()}"><i class="fa fa-random"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="ibox">
					<div class="ibox-title">
						<h5>Cantidad de tipos de distribuciones</h5>
					</div>
					<div class="ibox-content">
						<div id="ct-chart4" class="ct-perfect-fourth"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:layout>
<script src="${relativePath}/js/vistas/historialDistribuciones.js"></script>
<script>
	$(document).ready(
			function() {
				// Stocked horizontal bar

				new Chartist.Bar('#ct-chart4', {
					labels : [ 'Equitativa', 'Combinada', 'Capacidad',
							'Ocupacion', 'Zona' ],
					series : [ [${cantidadPorEquitativo},${cantidadPorCombinada},${cantidadPorCapacidad},${cantidadPorOcupacion},${cantidadPorZona} ] ]
				}, {
					seriesBarDistance : 1,
					reverseData : true,
					horizontalBars : true,
					axisY : {
						offset : 70
					},
					axisX : {
						// The offset of the chart drawing area to the border of the container
						offset : 30,
						// Position where labels are placed. Can be set to `start` or `end` where `start` is equivalent to left or top on vertical axis and `end` is equivalent to right or bottom on horizontal axis.
						position : 'end',
						// Allows you to correct label positioning on this axis by positive or negative x and y offset.
						labelOffset : {
							x : 0,
							y : 0
						},
						// If labels should be shown or not
						showLabel : true,
						// If the axis grid should be drawn or not
						showGrid : true,
						// Use only integer values (whole numbers) for the scale steps
						onlyInteger : true
					}
				});
			});
</script>
