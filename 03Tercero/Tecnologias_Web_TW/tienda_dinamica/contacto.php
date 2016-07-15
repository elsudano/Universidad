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
									Contacta con Nosotros
								</h2>
							<!-- Main Heading Ends -->
							<div class="row">
							<!-- Contact Details Starts -->
								<div class="col-sm-4">
									<div class="panel panel-smart">
										<div class="panel-heading">
											<h3 class="panel-title">Detalles de Contacto</h3>
										</div>
										<div class="panel-body">
											<ul class="list-unstyled contact-details">
												<li class="clearfix">
													<i class="fa fa-home pull-left"></i>
													<span class="pull-left">
														(ETSIIT) Escuela Técnica Superior de <br>
														Ingeniería Informática y Telecomunicaciónes<br />
														Periodista Daniel Salcedo Aranda,<br />
														18014 Granada Granada
													</span>
												</li>
												<li class="clearfix">
													<i class="fa fa-phone pull-left"></i>
													<span class="pull-left">
														958 24 28 02 <br />
													</span>
												</li>
												<li class="clearfix">
													<i class="fa fa-envelope-o pull-left"></i>
													<span class="pull-left">
														info@correo.ugr.es <br />
														admin@correo.ugr.es <br />
														soporte@correo.ugr.es
													</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
							<!-- Contact Details Ends -->
							<!-- Google Map Starts -->
								<div class="col-sm-8" id="map-wrapper">
									<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3178.145091073313!2d-3.6248956!3d37.1967831!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd71fc54f0f7fea1%3A0x5463cb956f4f4595!2sCalle+Periodista+Daniel+Salcedo+Aranda%2C+18014+Granada!5e0!3m2!1ses!2ses!4v1433606457455" width="400" height="300" frameborder="0" style="border:0"></iframe>
								</div>
							<!-- Google Map Ends -->
							</div>
							<div class="row">
							<!-- Contact Form Starts -->
								<div class="col-sm-12">
									<div class="panel panel-smart">
										<div class="panel-heading">
											<h3 class="panel-title">Manda un Correo</h3>
										</div>
										<div class="panel-body">
											<form action="" data-toggle="validator" class="form-horizontal" role="form">
												<div class="form-group">
													<label for="name" class="col-sm-2 control-label">
														Nombre
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="name" id="name" placeholder="Name" required>
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="col-sm-2 control-label">
														Email
													</label>
													<div class="col-sm-10">
														<input type="email" class="form-control" pattern="^[A-z0-9._-]+@[A-z0-9._-]+\.[A-z]{2,4}$" name="email" placeholder="Email" data-error="Introduzca un email valido" required>
													</div>
													<span class="help-block with-errors"></span>
												</div>
												<div class="form-group">
													<label for="subject" class="col-sm-2 control-label">
														Asunto
													</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" required>
													</div>
												</div>
												<div class="form-group">
													<label for="message" class="col-sm-2 control-label">
														Mensaje
													</label>
													<div class="col-sm-10">
														<textarea name="message" id="message" class="form-control" rows="5" placeholder="Message" required></textarea>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<input type="hidden" class="form-control" name="accion" value="envia_correo">
														<button type="submit" class="btn btn-black text-uppercase">Enviar</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							<!-- Contact Form Ends -->
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
