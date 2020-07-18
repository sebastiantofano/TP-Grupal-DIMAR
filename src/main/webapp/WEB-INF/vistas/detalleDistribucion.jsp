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
			<div class="col-md-4">
				<div class="ibox">
					<div class="ibox-title">
						<h5>Panel de acciones</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<c:if test="${distribucion.estado.getId() != 4}">
									<p>Seleccione la accion deseada:</p>
								</c:if>
								<div class="row">
									<c:choose>
										<c:when test="${distribucion.estado.getId() == 1}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(2)">
													<i class="fa fa-check"></i>&nbsp; Revisar
												</button>
											</div>
											<div class="col-sm-6">
												<button class="btn btn-warning btn-block" type="button" onclick="actualizarEstado(5)">
													<i class="fa fa-times"></i>&nbsp; Problemas en Revision
												</button>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 2}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(3)">
													<i class="fa fa-check"></i>&nbsp; Coordinar Entrega
												</button>
											</div>
											<div class="col-sm-6">
												<button class="btn btn-warning btn-block" type="button" onclick="actualizarEstado(6)">
													<i class="fa fa-times"></i>&nbsp; Problemas en Coordinacion
												</button>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 3}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(4)">
													<i class="fa fa-check"></i>&nbsp; Entregar
												</button>
											</div>
											<div class="col-sm-6">
												<button class="btn btn-warning btn-block" type="button" onclick="actualizarEstado(7)">
													<i class="fa fa-times"></i>&nbsp; Problemas en Entrega
												</button>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 4}">
											<div class="widget">
												<div class="row">
													<div class="col-xs-4 text-center">
														<i class="fa fa-check fa-5x"></i>
													</div>
													<div class="col-xs-8 text-right">
														<span> Entregado el: </span>
														<h2 class="font-bold">${distribucion.getFechaEntrega()}</h2>
													</div>
												</div>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 5}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(2)">
													<i class="fa fa-check"></i>&nbsp; Revisar
												</button>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 6}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(3)">
													<i class="fa fa-check"></i>&nbsp; Coordinar Entrega
												</button>
											</div>
										</c:when>
										<c:when test="${distribucion.estado.getId() == 7}">
											<div class="col-sm-6">
												<button class="btn btn-primary btn-block" type="button" onclick="actualizarEstado(4)">
													<i class="fa fa-check"></i>&nbsp; Entregar
												</button>
											</div>
										</c:when>
									</c:choose>
								</div>


								<form:form id="estadoForm" action="${relativePath}/distribucion/${distribucion.getId()}/estado" method="POST">
									<input id="estadoField" type="hidden" name="estado">
								</form:form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-title">
						<h2>
							Distribucion <strong>#${distribucion.getId()}</strong>
						</h2>
					</div>
					<div class="ibox-content">
						<h3>Detalle:</h3>

						<dl class="dl-horizontal">
							<dt>Fecha de Solicitud</dt>
							<dd>${distribucion.getFechaSolicitud()}</dd>
							<dt>Fecha de Revision</dt>
							<dd>${distribucion.getFechaRevision() != null ? distribucion.getFechaRevision() : "Sin definir"}</dd>
							<dt>Fecha de Entrega</dt>
							<dd>${distribucion.getFechaEntrega() != null ? distribucion.getFechaEntrega() : "Sin definir"}</dd>
							<dt>Tipo de Distribucion:</dt>
							<dd>
								<span class="text-navy"> ${distribucion.getTipoDistribucion().getNombre()}</span>
							</dd>
							<dt>Estado:</dt>
							<dd>
								<span class="text-navy"> ${distribucion.estado.getDescripcion()}</span>
							</dd>
						</dl>

						<hr />
						<h3>Detalle de Insumos:</h3>

						<div class="table-responsive">
							<table data-show-toggle="false" class="tablez table table-striped table-hover grilla-dataTable">
								<thead>
									<tr>
										<th>Establecimiento</th>
										<th>Distribucion</th>
										<th>Detalle</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${detalles}" var="detalle" varStatus="loop">
										<tr>
											<td><i class="fa fa-hospital-o"></i> ${detalle.key.getNombre()}</td>
											<td><c:set var="total" value="${0}" /> <c:forEach items="${detalle.value}" var="listElement">
													<c:set var="total" value="${total + listElement.getCantidad()}" />
												</c:forEach> <span class="label label-success">Total: ${total}</span></td>
											<td class="client-status"><a data-toggle="modal" data-target="#${loop.index}"> <i class="fa fa-eye"> </i>
											</a>
												<div class="modal inmodal" id="${loop.index}" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
													<div class="modal-dialog">
														<div class="modal-content animated flipInY">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">x</span><span class="sr-only">Close</span>
																</button>
																<i class="fa fa-hospital-o modal-icon"></i>
																<h4 class="modal-title">${detalle.key.getNombre()}</h4>
															</div>
															<div class="modal-body">
																<div class="row">
																	<div class="col-md-2">
																		<strong>Responsable:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.responsable.getNombre()},${detalle.key.responsable.getApellido()}</div>
																	<div class="col-md-2">
																		<strong>Ubicacion:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.getUbicacion()}</div>
																</div>
																<div class="row">
																	<div class="col-md-2">
																		<strong>Capacidad:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.getCapacidad()}</div>
																	<div class="col-md-2">
																		<strong>Ocupacion:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.getOcupacion()}</div>
																</div>
																<div class="row">
																	<div class="col-md-2">
																		<strong>Zona:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.zona.nombre}</div>
																	<div class="col-md-2">
																		<strong>Puntaje de zona:</strong>
																	</div>
																	<div class="col-md-4">${detalle.key.zona.puntaje}</div>
																</div>
																<div>
																	<hr>
																	<br>
																	<c:forEach items="${detalle.value}" var="listElement">
																		<span style="margin: 0px 0px 10px 0px; padding: 5px; display: inline-block; font-size: 13px;" class="label label-warning"> ${listElement.insumo.getNombre()}:
																			${listElement.insumo.getCantidad()} </span>
																	</c:forEach>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
															</div>
														</div>
													</div>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="ibox">
					<div class="ibox-title">
						<h2>Historial</h2>
					</div>
					<div class="ibox-content">
						<div class="activity-stream">
							<c:forEach items="${historial}" var="item">
								<div class="stream">
									<div class="stream-badge">
										<i class="fa fa-circle"></i>
									</div>
									<div class="stream-panel">
										<div class="stream-info">
											<span>El usuario: ${item.usuario}</span> - <span class="date">El dia ${item.fecha}</span>
										</div>
										${item.descripcion}
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:layout>
<script src="${relativePath}/js/vistas/detalleDistribucion.js"></script>
