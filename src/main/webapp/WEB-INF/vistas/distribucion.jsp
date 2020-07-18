<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-content">
						<h2>Distribucion - ${estrategia}</h2>
						<p>
							Asignacion de insumos a establecimientos.<br>Selecciona el
							metodo de Distribucion de la derecha.
						</p>
						<div class="clients-list">
							<div class="table-responsive">
								<table data-show-toggle="false"
									class="tablez table table-striped table-hover grilla-dataTable">
									<thead>
										<tr>
											<th>Prioridad</th>
											<th>Establecimiento</th>
											<th>Distribucion</th>
											<th>Detalle</th>
											<th data-breakpoints="all" data-title="Insumos:"></th>
											<th data-breakpoints="all" data-title="Parametros:"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${MapaDistribuido}" var="MapElement"
											varStatus="loop">
											<tr>
												<td><c:choose>
														<c:when test="${estrategia == 'Equitativo'}">
															<p class="text-success">${establecMayorOcupacion.nombre == MapElement.key.getNombre() ? "Insumos Extra" : "~"}</p>
														</c:when>
														<c:otherwise>
															<fmt:formatNumber type="number" maxFractionDigits="2"
																value="${MapElement.key.getPrioridad()}" />
														</c:otherwise>
													</c:choose></td>
												<td><i class="fa fa-hospital-o"></i>
													${MapElement.key.getNombre()}</td>
												<c:set var="total" value="${0}" />
												<c:forEach items="${MapElement.value}" var="listElement">
													<c:set var="total"
														value="${total + listElement.getCantidad()}" />
												</c:forEach>
												<td><span class="label label-success"
													style="font-size: 13px">Total: ${total}</span></td>
												<td class="client-status" style="text-align: center;"><a
													style="margin-left: -25px;" data-toggle="modal"
													data-target="#${loop.index}"> <i class="fa fa-eye">
													</i>
												</a>
													<div class="modal inmodal" id="${loop.index}" tabindex="-1"
														role="dialog" aria-hidden="true" style="display: none;">
														<div class="modal-dialog">
															<div class="modal-content animated flipInY">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal">
																		<span aria-hidden="true">x</span><span class="sr-only">Close</span>
																	</button>
																	<i class="fa fa-hospital-o modal-icon"></i>
																	<h4 class="modal-title">${MapElement.key.getNombre()}</h4>
																</div>
																<div class="modal-body">
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Responsable:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.responsable.getNombre()},${MapElement.key.responsable.getApellido()}</div>
																		<div class="col-md-2">
																			<strong>Ubicacion:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.getUbicacion()}</div>
																	</div>
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Capacidad:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.getCapacidad()}</div>
																		<div class="col-md-2">
																			<strong>Ocupacion:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.getOcupacion()}</div>
																	</div>
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Zona:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.zona.nombre}</div>
																		<div class="col-md-2">
																			<strong>Puntaje de zona:</strong>
																		</div>
																		<div class="col-md-4">${MapElement.key.zona.puntaje}</div>
																	</div>
																	<div>
																		<hr>
																		<br>
																		<c:forEach items="${MapElement.value}"
																			var="listElement">
																			<span
																				style="margin: 0px 0px 10px 0px; padding: 5px; display: inline-block; font-size: 13px;"
																				class="label label-warning">
																				${listElement.getNombre()}:
																				${listElement.getCantidad()} </span>
																		</c:forEach>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-primary"
																		data-dismiss="modal">Cerrar</button>
																</div>
															</div>
														</div>
													</div></td>
												<td><c:forEach items="${MapElement.value}"
														var="listElement">
														<span
															style="margin: 0px 0px 10px 0px; padding: 5px; display: inline-block; font-size: 13px;"
															class="label label-info">
															${listElement.getNombre()}: ${listElement.getCantidad()}
														</span>
													</c:forEach></td>
												<td>
													<div class="d-flex align-items-center">
														<span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Capacidad
																Total: </strong> ${MapElement.key.capacidad}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Ocupacion:
														</strong> ${MapElement.key.ocupacion}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Zona: </strong>
															${MapElement.key.zona.nombre}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Puntaje
																de la zona: </strong> ${MapElement.key.zona.puntaje}
														</span>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<form:form action="./confirmarDistribucion" method="POST">
								<input type="hidden" name="strategy" value="${estrategia}">
								<button type="submit" class="btn btn-success btn-sm btn-block">
									<i class="fa fa-check"></i> Solicitar Distribucion
								</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<c:choose>
					<c:when test="${estrategia == 'Equitativo'}">
						<div class="ibox">
							<div class="ibox-title">
								<h5>Insumos sobrantes en distribucion equitativa</h5>
							</div>
							<div class="ibox-content">
								<div>
									<dl class="dl-horizontal">
										<dt>Insumos sobrantes:</dt>
										<dd>${insumosSobrantes}</dd>
										<dt>Asignados a:</dt>
										<dd class="text-navy">${establecMayorOcupacion.nombre}</dd>
									</dl>
								</div>
								<div>
									<h4>Cambiar establecimiento de asignacion</h4>
									<form:form action="cambiarInsumos" method="POST"
										modelAttribute="establecimiento">
										<div class="input-group">
											<form:select path="id" name="id" class="form-control"
												id="establecimiento">
												<c:forEach items="${MapaDistribuido}" var="est">
													<form:option value="${est.key.id}">${est.key.nombre}</form:option>
												</c:forEach>
											</form:select>
											<span class="input-group-btn">
												<button type="Submit" class="btn btn-primary">Cambiar
													asignacion</button>
											</span>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribucion por ocupacion <a
											data-toggle="modal" data-target="#modalOcupacion"
											style="float: right; font-size: 20px;"> <i
												class="fa fa-info-circle"></i>
										</a>
										</strong>
										<p>Los establecimientos definen su prioridad en base a su
											porcentaje de ocupacion. Estos se agrupan en 3 grupos
											dependiendo su indice de riesgo.</p>
										<button onclick="distribuirInsumos('OCUPACION')"
											class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-random"></i> Distribuir insumos
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribucion por capacidad total <a
											data-toggle="modal" data-target="#modalCapacidadTotal"
											style="float: right; font-size: 20px;"> <i
												class="fa fa-info-circle"></i>
										</a>
										</strong>
										<p>Los insumos se distribuyen en base a la prioridad
											establecida por la capacidad total del establecimiento.</p>
										<button onclick="distribuirInsumos('CAPACIDAD')"
											class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-random"></i> Distribuir insumos
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribucion por zona <a data-toggle="modal"
											data-target="#modalZona"
											style="float: right; font-size: 20px;"> <i
												class="fa fa-info-circle"></i>
										</a></strong>
										<p>Los insumos se distribuyen en base a la prioridad
											establecida por el puntaje de su zona</p>
										<button onclick="distribuirInsumos('ZONA')"
											class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-random"></i> Distribuir insumos
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribucion combinada <a
											data-toggle="modal" data-target="#modalCombinada"
											style="float: right; font-size: 20px;"> <i
												class="fa fa-info-circle"></i>
										</a>
										</strong>
										<p>Los establecimientos definen su prioridad en base a la
											ocupacion sobre la capacidad , la capacidad total y la
											puntuacion de la zona.</p>
										<button onclick="distribuirInsumos('COMBINADO')"
											class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-random"></i> Distribuir insumos
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribucion equitativa <a
											data-toggle="modal" data-target="#modalEquitativa"
											style="float: right; font-size: 20px;"> <i
												class="fa fa-info-circle"></i>
										</a></strong>
										<p>Los insumos se distribuyen equitativamente entre todos
											los establecimientos sin la determinacion de un ondice de
											riesgo para cada uno de ellos.</p>
										<button onclick="distribuirInsumos('EQUITATIVO')"
											class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-random"></i> Distribuir insumos
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<form action="./distribucion" method="POST" id="distribucionForm">
					<input type="hidden" name="strategy" id="strategyField"> <input
						type="hidden" name="establecimiento" id="establecimientoField">
				</form>
			</div>
		</div>
		<div class="modal inmodal" id="modalOcupacion" tabindex="-1"
			role="dialog" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Distribucion por ocupacion</h4>
					</div>
					<div class="modal-body">
						<p>Los establecimientos definen su prioridad en base a su
							porcentaje de ocupacion. Estos se agrupan en 3 grupos dependiendo
							su indice de riesgo.</p>
						<ul>
							<li>El grupo 1 recibe el 60% de cada tipo de insumo. <br>
								<i>El establecimiento con mayor prioridad dentro de este
									grupo recibe el 60% de cada tipo de insumo + insumos extras.</i></li>
							<li>El grupo 2 recibe el 30% de cada tipo de insumo.</li>
							<li>El grupo 3 recibe el 10% de cada tipo de insumo.</li>
						</ul>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal inmodal" id="modalCapacidadTotal" tabindex="-1"
			role="dialog" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Distribucion por capacidad total</h4>
					</div>
					<div class="modal-body">
						<p>Los insumos se distribuyen en base a la prioridad
							establecida por la capacidad total del establecimiento.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal inmodal" id="modalZona" tabindex="-1" role="dialog"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Distribucion por zona</h4>
					</div>
					<div class="modal-body">
						<p>Los insumos se distribuyen en base a la prioridad
							establecida por el puntaje de su zona.</p>
						<p>Zona con puntaje:</p>
						<ul>
							<li>Entre 80 y 100 pts - Recibe el 40% de cada tipo de
								insumo.</li>
							<li>Entre 50 y 79 pts - Recibe el 30% de cada tipo de
								insumo.</li>
							<li>Entre 20 y 49 pts - Recibe el 20% de cada tipo de
								insumo.</li>
							<li>Entre 00 y 19 pts - Recibe el 10% de cada tipo de
								insumo.</li>
						</ul>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal inmodal" id="modalCombinada" tabindex="-1"
			role="dialog" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Distribucion combinada</h4>
					</div>
					<div class="modal-body">
						<p>Los establecimientos definen su prioridad en base a la
							ocupacion sobre la capacidad , la capacidad total y la puntuacion
							de la zona. Luego los establecimientos se dividen en 5 grupos de
							igual cantidad y los restantes se suman al último grupo.</p>
						<ul>
							<li>El grupo 1 recibe el 40% de cada tipo de insumo.</li>
							<li>El grupo 2 recibe el 28% de cada tipo de insumo.</li>
							<li>El grupo 3 recibe el 17% de cada tipo de insumo.</li>
							<li>El grupo 4 recibe el 10% de cada tipo de insumo.</li>
							<li>El grupo 5 recibe el 05% de cada tipo de insumo.</li>
						</ul>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal inmodal" id="modalEquitativa" tabindex="-1"
			role="dialog" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">x</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Distribucion equitativa</h4>
					</div>
					<div class="modal-body">
						<p>Los insumos se distribuyen equitativamente entre todos los
							establecimientos sin la determinacion de un ondice de riesgo para
							cada uno de ellos.</p>
						<p>Al establecimiento con mayor cantidad de infectados se le
							otorgaron los insumos sobrantes, los cuales no pudieron ser
							distribuidos equitativamente entre la totalidad de los
							establecimientos.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:layout>
<script src="${relativePath}/js/vistas/distribucion.js"></script>