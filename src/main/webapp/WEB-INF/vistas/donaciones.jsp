<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class=" container-fluid wrapper wrapper-content animated fadeInRight">
		<c:choose>
			<c:when test="${not empty msg}">
				<div class="alert alert-${msg[1]} alert-dismissable">
					<button aria-hidden="true" data-dismiss="alert" class="close"
						type="button">×</button>
					${msg[0]}
				</div>
			</c:when>
		</c:choose>
		<div class="col-sm-5">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h1>Stock de insumos actual</h1>
					<div class="ibox-tools ">
						<a class="collapse-link "> <i class="fa fa-chevron-up "></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="table-responsive">
						<table class="table table-striped table-hover grilla-dataTable">
							<thead class="">
								<tr>
									<th>Nombre</th>
									<th>Precio Unidad</th>
									<th>Cantidad</th>
									<th>Costo</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty insumosDisponibles}">
									<c:forEach items="${insumosDisponibles}" var="item">
										<tr>
											<td>${item.getNombre()}</td>
											<td>$ ${item.precioUnidad}</td>
											<td>${item.getCantidad()}</td>
											<td class="text-center">$ <fmt:formatNumber
													type="number" maxFractionDigits="2" value="${item.pxq}" /></td>

										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="col-sm-3">
			<div class="ibox">
				<div class="ibox-title">
					<h5>Cargar donación</h5>
				</div>
				<div class="ibox-content">
					<div>
						<h4>Seleccionar insumo a cargar</h4>
						<form:form action="cargar-donacion" id="formCargaDonacion"
							onsubmit="cargarDonacion()" modelAtributte="insumo"
							commandName="insumo">
							<div class="form-group">
								<form:select path="id" class="form-control">
									<form:options items="${insumosDisponibles}" itemLabel="nombre"
										itemValue="id"></form:options>
								</form:select>
							</div>
							<div class="form-group">
								<label for="cantidad" class="control-label sr-only">Ingresar
									cantidad a donar</label>
								<form:input path="cantidad" id="cantidad" type="number"
									class="form-control" placeholder="Ingrese cantidad" min="0" />
							</div>
							<div class="input-group">
								<button type="submit" class="btn btn-primary font-weight-bold">
									Confirmar donación <i class="fa fa-check"></i>
								</button>

							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<div class="col-sm-4 mb-5">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Recibir donaciones</h5>
					<div class="ibox-tools ">
						<a class="collapse-link "> <i class="fa fa-chevron-up "></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="table-responsive">
						<table class="table table-striped table-hover ">
							<thead class="">
								<tr>
									<th>Insumo</th>
									<th>Cantidad</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty insumosRecibidos}">
									<c:forEach items="${insumosRecibidos}" var="item">
										<tr>
											<td>${item.key.getNombre()}</td>
											<td>${item.value}</td>
											<td><form:form action="recibir-donacion" method="POST"
													modelAtributte="insumo" commandName="insumo">
													<input type="hidden" name="id" value="${item.key.id} ">
													<input type="hidden" name="cantidad" value="${item.value}">
													<div class="input-group">
														<button type="Submit" class="btn btn-primary">
															Recibir donación <i class="fa fa-hand-paper-o"
																aria-hidden="true"></i>
														</button>
													</div>
												</form:form></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>




</t:layout>

<script src="${relativePath}/js/vistas/donacion.js"></script>

