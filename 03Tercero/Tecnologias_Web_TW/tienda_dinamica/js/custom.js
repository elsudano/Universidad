//MAGNIFIC POPUP
$(document).ready(function() {
	$('.images-block').magnificPopup({
		delegate: 'a', 
		type: 'image',
		gallery: {
			enabled: true
		}
	});

	$('.emergente')
		.mouseover(function(){
			$(this).popover('show');
		})
		.mouseout(function(){
			$(this).popover('hide');
		});

	// todos los objetos que tengan como clase titulo buscaran el atributo data-content
	// y el valor que contentga este atributo sera el que se le aplique el modal('toggle')
	$('.title').mouseover(function(){
		var $titulo = $(this);
		$($titulo.attr('data-content')).modal('toggle');
	});

	// todos los botones que tengan como clase comentario buscaran el atributo data-content
	// y el valor que contentga este atributo sera el que se le aplique el modal('toggle')
	$('button,.comentario').click(function(){
		var $boton = $(this);
		$($boton.attr('data-content')).modal('toggle');
	});

	// Para que las alertas que se muestren en la interfaz desaparezcan despues de unos segundos
    // esta configurado para 3000 milisegundos
    window.setTimeout(function() {
        $(".alert").fadeTo(500, 0).slideUp(500, function(){
            $(this).remove(); 
        });
    }, 3000); 

});

// Esto sirve para configurar el formulario de subida de imagenes
Dropzone.options.myDropzone = {
	dictDefaultMessage: "Arrastre aquí 3 imagenes", // mensaje antes de arrastrar nada
	maxFilesize: 0.5, // MB
	dictFileTooBig: "El tamaño del fichero es: {{filesize}} y el máximo puede ser de: {{maxFilesize}}", // para el tamaño del fichero
	addRemoveLinks: true,
	parallelUploads: 3,
	maxFiles: 3,
	dictMaxFilesExceeded: "Solo se puede subir 3 imagenes", // maximo numero de imagenes 1
	dictInvalidFileType: "Tipo de fichero no admitido", // no es un fichero admitido
	dictCancelUpload: "Cancelar", // cancelar subida
	dictCancelUploadConfirmation: "¿ Esta segúro que quiere cancelar la subida ?", // confirmar la cancelación de la subida
	dictRemoveFile: "Borrar", // borrar la imagen
	acceptedFiles: "image/*",
	autoProcessQueue: false,
	uploadMultiple: true,

	init: function() {
		var myDropzone = this;
		this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
			e.preventDefault();
			e.stopPropagation();
			myDropzone.processQueue();
		});

		// Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
		// of the sending event because uploadMultiple is set to true.
		this.on("sendingmultiple", function() {
			// Gets triggered when the form is actually being sent.
			// Hide the success button or the complete form.
		});
		this.on("successmultiple", function(files, response) {
			// Gets triggered when the files have successfully been sent.
			// Redirect user or notify of success.
		});
		this.on("errormultiple", function(files, response) {
			// Gets triggered when there was an error sending the files.
			// Maybe show form again, and notify user of error
		});
	}
}