$(document).ready(function() {
	
	  $.ajax({
		  type : "GET",
		  contentType : "application/json",
		  url: "./getData", 
		  success: function(data) {
			  console.log(data);
			  
			  var objeto = "{";
			  for (z = 0; z < data.length; z++) {
				  var plength = data[z].zona.provincias.length;
				  for (p = 0; p < plength; p++) {
						objeto += '"' + data[z].zona.provincias[p].codigo + '":"' +  data[z].zona.puntaje + '",'; 
					}
			  	}
			  objeto = objeto.replace(/,\s*$/, "");
			  objeto += "}";
			  console.log(objeto);
				  
			  console.log(JSON.parse(objeto));
			 
			  
			  var arrEstablecimientos = data.map(function(h){
				  return {name: h.nombre, latLng:  [Number(h.ubicacion.split(",")[0]), Number(h.ubicacion.split(",")[1]) ]} 
			  });

				$(function(){
					  new jvm.Map(
					   {
					    container: $('#map'),
					    map: 'ar_mill',
					    markers: arrEstablecimientos,
					    series: {
					      markers: [{
					        attribute: 'fill',
					        scale: ['#000000', '#000000'],
					        normalizeFunction: 'polynomial'
					      },{
					        attribute: 'image',
					        scale: {
					          def: './img/hospital2.png'
					        },
					        values: data.reduce(function(p, c, i){ p[i] = 'def'; return p }, {}),
					      }],
					      regions: [{
					        scale: {
					          "0":  '#ffffff',
					          "10": '#F9EBEA',
					          "20": '#F2D7D5',
					          "30": '#E6B0AA',
					          "40": '#EC7063',
					          "50": '#CD6155',
					          "60": '#C0392B',
					          "70": '#A93226',
					          "80": '#922B21',
					          "90": '#7B241C',
					          "100":'#641E16'
					        },
					        attribute: 'fill',
					        values: JSON.parse(objeto),
					        legend: {
					        	vertical:true,
					          horizontal: true,
					          title: 'Riesgo por puntaje'
					        }
					      }]
					    },
				      onMarkerTipShow: function(event, label, index){
				          label.html(
				                  '<b>'+data[index].nombre+'</b><br/>'+
				                  '<b>Capacidad: </b>'+data[index].capacidad+'</br>'+
				                  '<b>Ocupacion: </b>'+data[index].ocupacion+'</br>'+
				                  '<b>Responsable: </b>'+data[index].responsable.apellido + ',' +data[index].responsable.nombre +'</br>'+
				                  '<b>Zona: </b>'+data[index].zona.nombre+'</br>'
				                );
				        },
			        onRegionTipShow: function(event, label, code){
			        	  label.html(
			        	    '<b>'+label.html()+'</b></br>'
			        	  );
			        	}
					  });
					});
				
		  },
		  error : function(e) {
			  console.log("ERROR: ", e);
		  }
	  });

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
	
	
	
	var zonasValues = {
	          "AR-Z": '0',
	          "AR-B": '10',
	          "AR-M": '100',
	          "AR-N": '30'
	}

});

