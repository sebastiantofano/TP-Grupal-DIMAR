var actualizarEstado = function(estado) {
	const form = $("#estadoForm");
	const field = $("#estadoField");
	field.val(estado);
	form.submit();
};

$(document).ready(function() {
	$('.grilla-dataTable').DataTable({
		pageLength : 10,
		responsive : true,
		dom : '<"html5buttons"B>lTfgitp',
		buttons : [],
		language : lenguaje
	});

	jQuery(function($) {
		$('.tablez').footable();
	});
});
