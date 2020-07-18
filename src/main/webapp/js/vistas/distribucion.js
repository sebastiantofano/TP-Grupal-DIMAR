var distribuirInsumos = function(estrategia) {
	const form = $("#distribucionForm");
	const strategyField = $("#strategyField");
	strategyField.val(estrategia);
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
