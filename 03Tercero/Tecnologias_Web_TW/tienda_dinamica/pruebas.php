<?php include ('includes/seguridad.inc'); ?>
<!doctype html>
<html lang="es">
<?php include ('includes/head.inc'); ?>
<body>
	<?php include ('cabecera.php'); ?>
	<!-- Fila central contenido Starts-->
	<div class="row">
		<!-- Main Container Starts -->
		<div id="main-container">
			<!-- Main Heading Starts -->
			<h2 class="main-heading text-center">
				Pruebas de ficheros
			</h2>
			<!-- Main Heading Ends -->
			<div class="row">
				<!-- Contact Details Starts -->
				<div class="col-sm-4">
					<form action="" class="form-" method="post" enctype="multipart/form-data" role="form">
						<input type="hidden" name="name" value="">
						<input type="submit" name="action" value="importar">
				</div>
				<!-- Contact Details Ends -->
			</div>
		</div>
		<!-- Main Container Ends -->
	</div>
	<!-- Fila central contenido Ends-->
	<?php
	include ('pie.php');
	include ('includes/javascript.inc');
	?>
</body>
</html>
