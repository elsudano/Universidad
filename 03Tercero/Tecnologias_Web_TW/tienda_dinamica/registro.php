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
									Registro <br />
									<span>Crear una cuenta nueva</span>
								</h2>
							<!-- Main Heading Ends -->
							<?php

							// Procesamiento de los formularios de alta de usuarios
							if(isset($_POST['accion'])):
								switch ($_POST['accion']):
									case "alta_usuario":
										$resultado = $miManejadorDeUsuarios->AltaDeUsuario($_POST['nombre'], $_POST['apellidos'], $_POST['dni'], $_POST['contrasena'], 'invitado', 0, $_POST['email'], $_FILES['foto']);
										if ($resultado === TRUE):
											echo "<div class='alert alert-success'>El Usuario: " . $_POST['nombre'] . ' ' . $_POST['apellidos'] . " se ha dado de alta</div>";
										else:
											echo "<div class='alert alert-warning'>Ha ocurrido algún problema al dar de alta al usuario: " . $_POST['nombre'] . ' ' . $_POST['apellidos'] . ' ' . $resultado . "</div>";
										endif;
										break;

									default:
										echo "<div class='alert alert-success'>No hay nada que procesar...</div>";
								endswitch;
								/*echo "<div class='alert alert-warning'>";
								foreach ($_POST as $key => $value)
									echo "<strong>Campo:</strong> ".htmlspecialchars($key)." <strong>Contiene:</strong> ".htmlspecialchars($value)."<br>";
								echo "</div>";

								echo "<div class='alert alert-warning'>";
								foreach ($_FILES as $key => $value):
									echo "<strong>Campo:</strong> ".$key." <strong>Contiene:</strong> <br>";
									print_r($value);
								endforeach;
								echo "</div>";*/
							endif;
							?>
							<!-- Registration Section Starts -->
								<section class="registration-area">
									<div class="row">
										<!-- Registration Block Starts -->
											<div class="panel panel-smart">
												<div class="panel-heading">
													<div class="col-md-6">
														<h3 class="panel-heading inner">Información Obligatoria</h3>
													</div>
													<div class="col-md-6">
														<h3 class="panel-heading inner">Información Adicional</h3>
													</div>
												</div>
												<div class="panel-body">
												<!-- Registration Form Starts -->
													<form action=""  data-toggle="validator" class="form-horizontal" method="post" enctype="multipart/form-data" role="form">
														<div class="col-md-6">
															<!-- Personal Information Starts -->
																<div class="form-group">
																	<label for="nombre" class="col-sm-3 control-label">Nombre :</label>
																	<div class="col-sm-9">
																		<input type="text" name="nombre" class="form-control" pattern="^([A-z]){3,20}$" minlength="3" maxlength="20" id="nombre" placeholder="Nombre..." required>
																	</div>
																	<span class="help-block with-errors"></span>
																</div>
																<div class="form-group">
																	<label for="apellidos" class="col-sm-3 control-label">Apellidos :</label>
																	<div class="col-sm-9">
																		<input type="text" name="apellidos" class="form-control" id="apellidos" placeholder="Apellidos..." required>
																	</div>
																	<span class="help-block with-errors"></span>
																</div>
																<div class="form-group">
																	<label for="email" class="col-sm-3 control-label">Email :</label>
																	<div class="col-sm-9">
																		<input type="email" name="email" pattern="^[A-z0-9._-]+@[A-z0-9._-]+\.[A-z]{2,4}$" class="form-control" id="email" placeholder="Email..." required>
																	</div>
																	<span class="help-block with-errors"></span>
																</div>
																<div class="form-group">
																	<label for="dni" class="col-sm-3 control-label">DNI :</label>
																	<div class="col-sm-9">
																		<input type="text" name="dni" pattern="^[0-9]{8,8}[A-Z]{1,1}$" class="form-control" minlength="9" maxlength="9" id="dni" placeholder="DNI..." data-match-error="El DNI no es Válido" required>
																	</div>
																	<span class="help-block with-errors">8 numeros y la letra en Mayúscula</span>
																</div>
															<!-- Personal Information Ends -->
															<!-- Password Area Starts -->
																<h3 class="panel-heading inner">
																	Contraseña
																</h3>
																<div class="form-group">
																	<label for="contrasena" class="col-sm-3 control-label">Contraseña :</label>
																	<div class="col-sm-9">
																		<input type="password" name="contrasena" pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" class="form-control" minlength="6" id="contrasena" placeholder="Contraseña..." required>
																	</div>
																	<span class="help-block with-errors">Una mayúscula, una minúscula, un numero, un símbolo, mínimo 8</span>
																</div>
																<div class="form-group">
																	<label for="valida" class="col-sm-3 control-label">Validar:</label>
																	<div class="col-sm-9">
																		<input type="password" class="form-control" id="valida" placeholder="Validar..." data-match="#contrasena" data-match-error="Las contraseñas no coinciden" required>
																	</div>
																	<span class="help-block with-errors"></span>
																</div>
																<div class="form-group">
																	<div class="col-sm-offset-3 col-sm-9">
																		<div class="checkbox">
																			<label>
																				<input type="checkbox" name="acepto" data-error="Acepte las condiciones" required> He leido y acepto las condiciones
																			</label>
																			<span class="help-block with-errors"></span>
																		</div>
																	</div>
																</div>
															<!-- Password Area Ends -->
														</div>
														<div class="col-md-6">
															<!-- Delivery Information Starts -->
																<div class="form-group">
																	<label for="compania" class="col-sm-3 control-label">Compañia :</label>
																	<div class="col-sm-9">
																		<input type="text" name="compania" class="form-control" id="compania" placeholder="Compañia...">
																	</div>
																</div>
																<div class="form-group">
																	<label for="direccion" class="col-sm-3 control-label">Deirección :</label>
																	<div class="col-sm-9">
																		<input type="text" name="direccion" class="form-control" id="direccion" placeholder="Deirección...">
																	</div>
																</div>
																<div class="form-group">
																	<label for="ciudad" class="col-sm-3 control-label">Ciudad :</label>
																	<div class="col-sm-9">
																		<input type="text" name="ciudad" class="form-control" id="ciudad" placeholder="Ciudad...">
																	</div>
																</div>
																<div class="form-group">
																	<label for="codpost" class="col-sm-3 control-label">Código Postal:</label>
																	<div class="col-sm-9">
																		<input type="number" name="codpost" class="form-control" id="codpost" placeholder="Código Posta...">
																	</div>
																</div>
																<div class="form-group">
																	<label for="telefono" class="col-sm-3 control-label">Teléfono :</label>
																	<div class="col-sm-9">
																		<input type="text" name="telefono" pattern="^\+[0-9]{2,3}\ [0-9]{9,9}$" class="form-control" minlength="12" maxlength="13" id="telefono" placeholder="Teléfono...">
																	</div>
																	<span class="help-block with-errors">+xx xxxxxxxxx</span>
																</div>
																<div class="form-group">
																	<label for="foto" class="col-sm-3 control-label">Foto :</label>
																	<div class="col-sm-9">
																		<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo TAMANO_MAXIMO_FOTO ?>" />
																		<input type="file" name="foto" class="form-control" id="foto" placeholder="Foto...">
																	</div>
																	<span class="help-block with-errors">Imágenes png/jpg</span>
																</div>
															<!-- Delivery Information Ends -->
														</div>
														<div class="form-group">
															<div class="col-sm-offset-3 col-sm-9">
																<button type="submit" name="accion" value="alta_usuario" class="btn btn-black">
																	Registrarse
																</button>
															</div>
														</div>
													</form>
												<!-- Registration Form Starts -->
												</div>							
											</div>
										<!-- Registration Block Ends -->
									</div>
								</section>
							<!-- Registration Section Ends -->
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
