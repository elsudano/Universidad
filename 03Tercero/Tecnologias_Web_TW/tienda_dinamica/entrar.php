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
									Acceso o Creación de una cuenta nueva
								</h2>
							<!-- Main Heading Ends -->
							<!-- Login Form Section Starts -->
								<section class="login-area">
									<div class="row">
										<!-- Left column Starts -->
											<div class="col-sm-6">
											<!-- Login Panel Starts -->
												<div class="panel panel-smart">
													<div class="panel-heading">
														<h3 class="panel-title">Acceso de Usuarios</h3>
													</div>
													<div class="panel-body">
														<p>
															Por favor utilice una cuenta existente
														</p>
													<!-- Login Form Starts -->
														<form method="post" class="form" role="form">
															<div class="form-group">
																<label class="sr-only" for="input_usuario">Usuario</label>
																<input name="usuario" type="text" class="form-control" id="input_usuario" placeholder="Usuario">
															</div>
															<div class="form-group">
																<label class="sr-only" for="input_contrasena">Contrase&ntilde;a</label>
																<input name="contrasena" type="password" class="form-control" id="input_contrasena" placeholder="Contrase&ntilde;a">
															</div>
															<button name="validar" value="TRUE" type="submit" class="btn btn-black">
																Validar
															</button>
														</form>
													<!-- Login Form Ends -->
													</div>
												</div>
											<!-- Login Panel Ends -->
											</div>
										<!-- Left column Ends -->
										<!-- Right column Starts -->
											<div class="col-sm-6">
											<!-- Account Panel Starts -->
												<div class="panel panel-smart">
													<div class="panel-heading">
														<h3 class="panel-title">
															Crear una cuenta de Usuario
														</h3>
													</div>
													<div class="panel-body">
														<p>Para ver los precios de los artículos y poder realizar comentarios sobre los mismos es necesario crear una cuenta de usuario.</p>
														<a href="register.html" class="btn btn-black">Registrarse</a>
													</div>
												</div>
											<!-- Account Panel Ends -->
											<!-- Errores de acceso Starts-->
												<?php if ($_SESSION['error'] !== "") { ?>
												<div class="alert alert-danger" role="alert">
													<p><?php echo $_SESSION['error']; ?></p>
												</div>
												<?php } ?>
											<!-- Errores de acceso Ends-->
											</div>
										<!-- Right column Ends -->
									</div>
								</section>
							<!-- Login Form Section Ends -->
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