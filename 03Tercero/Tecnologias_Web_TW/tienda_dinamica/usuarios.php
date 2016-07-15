<?php include ('includes/seguridad.inc'); ?>
<!doctype html>
<html lang="es">
	<?php include ('includes/head.inc'); ?>
	<body>
		<?php
			include ('cabecera.php');
		?>
				<!-- Fila central contenido Starts-->
					<div class="row">
					<?php
						// esta pagina es solo para usuarios administradores
						if ( $miUsuario === NULL OR $miUsuario->cualEsNivel() !== 'admin' )
								enviarA(RAIZ); // redirigimos al usuario por que no hay artículo

						// Procesamiento de los formularios de alta y modificación de las diferentes pestañas
						if(isset($_POST['accion'])){
							switch ($_POST['accion']) {
								case "alta_usuario":
									$resultado = $miManejadorDeUsuarios->AltaDeUsuario($_POST['nombre'], $_POST['apellidos'], $_POST['dni'], $_POST['contrasena'], 'invitado', 0, $_POST['email'], $_FILES['foto']);
									if ($resultado === TRUE):
										echo "<div class='alert alert-success'>El Usuario se ha dado de alta correctamente</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al dar de alta el Usuario " . $resultado . " </div>";
									endif;

									break;
								case "borrar_usuario":
									$usuario_seleccionado = $miManejadorDeUsuarios->GetUsuario($_POST['nif']);
									if ($miManejadorDeUsuarios->BorrarUsuario($usuario_seleccionado)):
										echo "<div class='alert alert-success'>El Usuario: " . $usuario_seleccionado->cualEsNombre() . " se ha borrado correctamente</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al borrar el Usuario " . $usuario_seleccionado->cualEsNombre() . " </div>";
									endif;
									break;
								case "cambia_permisos":
									$usuario_seleccionado = $miManejadorDeUsuarios->GetUsuario($_POST['nif']);
									if ($miManejadorDeUsuarios->CambiaEstado($usuario_seleccionado,$_POST['permisos'],$_POST['nivel'])):
										echo "<div class='alert alert-success'>Ahora el Usuario " . $usuario_seleccionado->cualEsNombre() . " esta Activo y puede validarse</div>";
									else:
										echo "<div class='alert alert-warning'>Ahora el Usuario " . $usuario_seleccionado->cualEsNombre() . " esta Activo y <strong>NO</strong> puede validarse</div>";
									endif;
									break;
								default:
									echo "<div class='alert alert-success'>No hay nada que procesar...</div>";
							}
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
						}
					?>
						<!-- Main Container Starts -->
							<div id="main-container">
								<!-- Usuario Grid Display Starts -->
									<div class="row">
									<?php foreach ($miManejadorDeUsuarios->TodosLosUsuarios() as $usuario): ?>
									<!-- Usuario #<?php echo $usuario->cualEsId(); ?> Starts -->
										<div class="col-md-3 col-sm-5">
											<div class="product-col">
												<div class="image">
													<a title="Mostrar el Usuario" href="usuario.php?u=<?php echo $usuario->cualEsId(); ?>">
														<img src="<?php echo $usuario->cualEsFoto() ?>" width="auto" height="auto" alt="usuario-<?php echo $usuario->cualEsId() ?>-imagen" class="img-responsive" />
													</a>
												</div>
												<div class="caption">
													<a title="Mostrar el Usuario" href="usuario.php?u=<?php echo $usuario->cualEsId(); ?>">
														<h4 class="title" data-content="#modal<?php echo $usuario->cualEsId(); ?>"><?php echo $usuario->cualEsNombre() ?></h4>
													</a>
													<form action="" id="form_actualiza_usuario<?php echo $usuario->cualEsId(); ?>" method="post" accept-charset="utf-8">
														<div class="form-group">
															<div class="col-sm-12">
																<select name="nivel" class="form-control" onchange="$('#form_actualiza_usuario<?php echo $usuario->cualEsId(); ?>').submit()">
																	<?php 
																	$valores = array('admin', 'usuario', 'invitado');
																	foreach ($valores as $valor):
																	    if ($valor === $usuario->cualEsNivel()):
																	    	echo "<option value='" . $valor . "' selected>" . strtoupper($valor) . "</option>";
																	    else:
																	    	echo "<option value='" . $valor . "'>" . strtoupper($valor) . "</option>";
																	    endif;
																	endforeach;
																	?>
																</select>
															</div>
															<div class="col-sm-12">
																<input type="hidden" name="nif" value="<?php echo $usuario->cualEsNif(); ?>"></input>
																<input type="hidden" name="accion" value="cambia_permisos"></input>
																<div class="checkbox">
																	<?php if ($usuario->cualEsValidado()): ?>
																	<label><input type="checkbox" checked="TRUE" onchange="$('#form_actualiza_usuario<?php echo $usuario->cualEsId(); ?>').submit()"> Permitir el Acceso</label>
																	<input type="hidden" name="permisos" value=""></input>
																	<?php else: ?>
																	<label><input type="checkbox" name="permisos" value="TRUE" onchange="$('#form_actualiza_usuario<?php echo $usuario->cualEsId(); ?>').submit()"> Permitir el Acceso</label>
																	<?php endif; ?>
																</div>
															</div>
														</div>
													</form>
													<div class="button-group">
														<button type="button" title="Borrar el Usuario" class="btn btn-danger" data-content="#borrar_usuario<?php echo $usuario->cualEsId(); ?>">
															Borrar
															<i class="fa fa-trash"></i>
														</button>
													</div>
													<!-- Modal Confirmación de Borrado de Usuario Start-->
														<div id="borrar_usuario<?php echo $usuario->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<form action="" method="post" class="form-horizontal" accept-charset="utf-8" role="form">
																		<fieldset>
																			<div class="modal-header">
																				<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																				<h4>Borrar el Usuario</h4>
																			</div>
																			<div class="modal-body">
																				<input type="hidden" name="nif" value="<?php echo $usuario->cualEsNif(); ?>">
																				<p>¿ Esta usted segúro de que desea borrar el usuario: <?php echo $usuario->cualEsNombre(); ?> ?</p>
																			</div>
																			<div class="modal-footer">
																				<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																					Cancelar
																					<i class="fa fa-ban"></i>
																				</button>
																				<button type="submit" name="accion" value="borrar_usuario" title="Borrar el Usuario" class="btn btn-danger">
																					Borrar
																					<i class="fa fa-trash-o"></i>
																				</button>
																			</div>
																		</fieldset>
																	</form>
																</div>
															</div>
														</div>
													<!-- Modal Confirmación de Borrado de Usuario Ends-->
												</div>
											</div>
										</div>
									<!-- Usuario #<?php echo $usuario->cualEsId(); ?> Ends -->
									<?php endforeach;	?>
									<!-- Usuario #add in home Starts -->
										<div class="col-md-3 col-sm-5">
											<div class="product-col">
												<button type="button" title="Añadir un Artículo a la Portada" class="btn btn-success btn-lg" data-content="#alta_usuario">
													<i class="fa fa-plus fa-6x"></i> 
												</button>
											</div>
										</div>
										<!-- Modal Alta del Usuario Start-->
											<div id="alta_usuario" class="modal fade" tabindex="-1" role="dialog">
												<div class="modal-dialog modal-md">
													<div class="modal-content">
														<form action="" method="post" class="form-horizontal" data-toggle="validator" enctype="multipart/form-data" accept-charset="utf-8" role="form">
															<fieldset>
																<div class="modal-header">
																	<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																	<h4>Alta de Usuario</h4>
																</div>
																<div class="modal-body">
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
																	<div class="form-group">
																		<label for="foto" class="col-sm-3 control-label">Foto :</label>
																		<div class="col-sm-9">
																			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo TAMANO_MAXIMO_FOTO ?>" />
																			<input type="file" name="foto" class="form-control" id="foto" placeholder="Foto...">
																		</div>
																		<span class="help-block with-errors">Imágenes png/jpg</span>
																	</div>
																	<div class="form-group">
																		<label for="nivel" class="col-sm-3 control-label text-left">Nivel de Acceso :</label>
																		<div class="col-sm-6">
																			<select name="nivel" class="form-control">
																				<!-- <option value="admin">Administrador</option>
																				<option value="usuario">Usuario</option>
																				<option value="invitado">Invitado</option> -->
																			</select>
																		</div>
																		<div class="checkbox">
																			<label for="validado" class="col-sm-3 control-label text-left">
																				<input type="checkbox" name="validado"> Permitir el Acceso
																			</label>
																		</div>
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
																		<label for="repetir" class="col-sm-3 control-label">Repetir :</label>
																		<div class="col-sm-9">
																			<input type="password" class="form-control" id="repetir" placeholder="Repetir..." data-match="#contrasena" data-match-error="Las contraseñas no coinciden" required>
																		</div>
																		<span class="help-block with-errors"></span>
																	</div>
																<!-- Password Area Ends -->
																</div>
																<div class="modal-footer">
																	<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																		Cancelar
																		<i class="fa fa-ban"></i>
																	</button>
																	<button type="submit" name="accion" value="alta_usuario" title="Dar de alta al Usuario" class="btn btn-success">
																		Alta
																		<i class="fa fa-upload"></i>
																	</button>
																</div>
															</fieldset>
														</form>
													</div>
												</div>
											</div>
										<!-- Modal Alta del Artículo Ends-->
									<!-- Usuario #add in home Ends -->
									</div>
								<!-- Usuario Grid Display Ends -->
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