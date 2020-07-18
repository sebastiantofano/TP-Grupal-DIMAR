<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />
<link href="./css/jvectormap/jquery-jvectormap.css" rel="stylesheet">
<style>

.jvectormap-legend-title {
  font-size: 12px;
}

.jvectormap-legend .jvectormap-legend-tick-sample {
  height: 26px;
}

.jvectormap-legend-icons {
  background: white;
  border: black 1px solid;
}

.jvectormap-legend-icons {
  color: black;
}

.customHeader {
	background-color: white;
	padding:20px;
	margin-top:-20px;
	margin-bottom:-40px;
}

image {
	max-width: 10px;
    max-height: 10px;
}

</style>



<t:layout>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<h2 class="customHeader">Establecimientos en Argentina</h2>
		</div>
	</div>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div id="map"></div>
		</div>
	</div>
</t:layout>

  <script src="./js/jvectormap/lib/jquery-mousewheel.js"></script>

  <script src="./js/jvectormap/src/jvectormap.js"></script>

  <script src="./js/jvectormap/src/abstract-element.js"></script>
  <script src="./js/jvectormap/src/abstract-canvas-element.js"></script>
  <script src="./js/jvectormap/src/abstract-shape-element.js"></script>

  <script src="./js/jvectormap/src/svg-element.js"></script>
  <script src="./js/jvectormap/src/svg-group-element.js"></script>
  <script src="./js/jvectormap/src/svg-canvas-element.js"></script>
  <script src="./js/jvectormap/src/svg-shape-element.js"></script>
  <script src="./js/jvectormap/src/svg-path-element.js"></script>
  <script src="./js/jvectormap/src/svg-circle-element.js"></script>
  <script src="./js/jvectormap/src/svg-image-element.js"></script>
  <script src="./js/jvectormap/src/svg-text-element.js"></script>

  <script src="./js/jvectormap/src/vml-element.js"></script>
  <script src="./js/jvectormap/src/vml-group-element.js"></script>
  <script src="./js/jvectormap/src/vml-canvas-element.js"></script>
  <script src="./js/jvectormap/src/vml-shape-element.js"></script>
  <script src="./js/jvectormap/src/vml-path-element.js"></script>
  <script src="./js/jvectormap/src/vml-circle-element.js"></script>
  <script src="./js/jvectormap/src/vml-image-element.js"></script>

  <script src="./js/jvectormap/src/map-object.js"></script>
  <script src="./js/jvectormap/src/region.js"></script>
  <script src="./js/jvectormap/src/marker.js"></script>

  <script src="./js/jvectormap/src/vector-canvas.js"></script>
  <script src="./js/jvectormap/src/simple-scale.js"></script>
  <script src="./js/jvectormap/src/ordinal-scale.js"></script>
  <script src="./js/jvectormap/src/numeric-scale.js"></script>
  <script src="./js/jvectormap/src/color-scale.js"></script>
  <script src="./js/jvectormap/src/legend.js"></script>
  <script src="./js/jvectormap/src/data-series.js"></script>
  <script src="./js/jvectormap/src/proj.js"></script>
  <script src="./js/jvectormap/src/map.js"></script>
  <script src="./js/jvectormap/jquery-jvectormap.js"></script>
  <script src="./js/jvectormap/jquery-jvectormap-ar-mill.js"></script>
  <script src="${relativePath}/js/vistas/mapa.js"></script>
