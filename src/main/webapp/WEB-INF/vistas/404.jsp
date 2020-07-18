<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class="middle-box text-center animated fadeInDown">
		<div class="ibox">
			<div class="ibox-content">
				<h1>404</h1>
				<h3 class="font-bold">Pagina no encontrada</h3>
				<div class="error-desc"></div>
			</div>
		</div>

	</div>

</t:layout>
<script src="${relativePath}/js/vistas/404.js"></script>
