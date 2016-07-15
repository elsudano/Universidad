<?php include ('includes/seguridad.inc'); ?>
<!doctype html>
<html lang="es">
	<?php include ('includes/head.inc'); ?>
	<body>
		<?php
			require_once('includes/familiaDAO.class.inc');
			require_once('includes/articuloDAO.class.inc');
			require_once('includes/comentarioDAO.class.inc');
			include ('cabecera.php');
		?>
				<!-- Fila central contenido Starts-->
					<div class="row">
					<?php
						// primero nos aseguramos de que el usuario introduzca un articulo en la dirección
						if (!empty($_GET)){
							// si hay un articulo en la dirección creamos el manejador y preguntamos por el articulo
							// $miManejadorFamilias = new FamiliaDAO(TIPODB); Esta linea siempre va ha estar mientras haya menu
							$miFamilia = $miManejadorFamilias->FamiliaPorId($_GET['f']);
							if ($miFamilia === NULL)
								enviarA(RAIZ); // redirigimos al usuario por que no hay artículo
							// Como ya sabemos que hay articulo y que si existe creamos todo lo demas
							$miManejadorArticulos = new ArticuloDAO(TIPODB);
							$miManejadorComentarios = new ComentarioDAO(TIPODB);
						}else{
							$_SESSION['error'] = "Fichero " . basename(__FILE__) . ": La variable del articulo seleccionado no es correcta";
							enviarA(RAIZ); // redirigimos al usuario por que no hay artículo
						}

						// Procesamiento de los formularios de alta y modificación de las diferentes pestañas
						if(isset($_POST['accion'])){
							switch ($_POST['accion']) {
								case "alta_articulo":
									if ( $miManejadorArticulos->AltaArticulo($_POST['denominacion'], $_POST['descripcion'], $_POST['precio'], $_POST['unidades'], $_POST['portada'], $_POST['familia'], $_FILES ) ):
										echo "<div class='alert alert-success'>El Articulo se ha dado de alta correctamente</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al dar de alta el artículo</div>";
									endif;

									break;
								case "alta_familia":
									$boolean = preg_match('#^image\/(jpeg|png|gif|tiff|)$#',$_FILES['foto']['type']);

									// Cuando subimos ficheros hay un array que contiene los datos del fichero subido $_FILES
									// todos los valores qu contiene son los que vienen a continuación:
									if ( isset($_FILES['foto']['error']) AND $_FILES['foto']['error'] === 0 AND  $boolean):
										// aqui se puede poner un gestor de errores en las siguientes versiones
										$fileName = $_FILES['foto']['name'];
										$fileSize = $_FILES['foto']['size'];
										$fileType = $_FILES['foto']['type'];
										$datos = file_get_contents($_FILES['foto']['tmp_name']);
									else:
										$datos = "Sin Foto";
									endif;

									if ( $boolean AND $miManejadorFamilias->AltaFamilia($_POST['denominacion'], $_POST['descripcion'], $_POST['color'], $datos) ):
										echo "<div class='alert alert-success'>La Familia se ha dado de alta correctamente</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al dar de alta la Familia</div>";
									endif;

									break;
								case "comentario_articulo":
									$comentario_articulo = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
									if ($miManejadorComentarios->AnadirComentario($miUsuario,$comentario_articulo,$_POST['comentario'])):
										echo "<div class='alert alert-success'>El comentario se ha añadido al articulo: " . $comentario_articulo->cualEsDenominacion() . "</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al añadir el comentario al artículo: " . $comentario_articulo->cualEsDenominacion() . "</div>";
									endif;
									break;
								case "borrar_articulo":
									$articulo_borrado = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
									if ($miManejadorArticulos->BorrarArticulo($articulo_borrado)):
										echo "<div class='alert alert-success'>El Articulo: " . $articulo_borrado->cualEsDenominacion() . " se ha borrado correctamente</div>";
									else:
										echo "<div class='alert alert-warning'>Ha ocurrido algún problema al borrar el artículo: " . $articulo_borrado->cualEsDenominacion() . "</div>";
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
								<!-- Category Intro Content Starts -->
									<div class="row cat-intro">
									<!-- Main Heading Starts -->
										<h2 class="main-heading2"><?php echo $miFamilia->cualEsDenominacion(); ?></h2>
									<!-- Main Heading Ends -->
										<div class="col-sm-3">
											<img src="<?php echo $miFamilia->cualEsFoto(); ?>" width="50%" height="auto" alt="imagen-<?php echo $miFamilia->cualEsId(); ?>-familia" class="img-responsive img-thumbnail" />
										</div>
										<div class="col-sm-9 cat-body">
											<p><?php echo $miFamilia->cualEsDescripcion(); ?></p>
										</div>
									</div>					
								<!-- Category Intro Content Ends -->
								<!-- Product Grid Display Starts -->
									<div class="row">
									<?php foreach ($miManejadorArticulos->ArticulosPorFamilia($miFamilia) as $articulo): ?>
									<!-- Product #<?php echo $articulo->cualEsId(); ?> Starts -->
										<div class="col-md-3 col-sm-5">
											<div class="product-col">
												<div class="image">
													<a title="Mostrar el Artículo" href="articulo.php?a=<?php echo $articulo->cualEsId(); ?>">
														<img src="<?php echo $articulo->cualEsFoto1() ?>" width="auto" height="auto" alt="articulo-<?php echo $articulo->cualEsId() ?>-imagen" class="img-responsive" />
													</a>
												</div>
												<div class="caption">
													<a title="Mostrar el Artículo" href="articulo.php?a=<?php echo $articulo->cualEsId(); ?>">
														<h4 class="title" data-content="#modal<?php echo $articulo->cualEsId(); ?>"><?php echo substr($articulo->cualEsDenominacion(),0,70) . ' ...' ?></h4>
													</a>
													<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'usuario' OR $miUsuario->cualEsNivel() === 'admin') ): ?>
													<a title="Mostrar el Artículo" href="articulo.php?a=<?php echo $articulo->cualEsId(); ?>">
														<div class="price">
															<span class="price-new"><?php echo $articulo->cualEsPrecio() . ' ' . TIPO_MONEDA; ?></span> 
															<span class="price-old"><?php echo $articulo->cualEsPrecio()-(($articulo->cualEsPrecio()*POR_PRE_ANT)/100) . ' ' . TIPO_MONEDA; ?></span>
														</div>
													</a>
													<?php endif; ?>
													<div class="cart-button button-group">
														<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'usuario' OR $miUsuario->cualEsNivel() === 'admin') ): ?>
														<button type="button" title="Realizar un Comentario" class="btn btn-info comentario" data-content="#comentario<?php echo $articulo->cualEsId(); ?>">
															Comentar
															<i class="fa fa-comments"></i>
														</button>
														<!-- Modal Comentarios del Articulo <?php echo $articulo->cualEsId(); ?> Start-->
															<div id="comentario<?php echo $articulo->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
																<div class="modal-dialog modal-md">
																	<div class="modal-content">
																		<form action="" method="post" accept-charset="utf-8" role="form">
																			<fieldset>
																				<div class="modal-header">
																					<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																					<h4>Publique un Comentario sobre:</h4>
																					<p><?php echo $articulo->cualEsDenominacion(); ?></p>
																				</div>
																				<div class="modal-body">
																					<input type="hidden" name="id_articulo" value="<?php echo $articulo->cualEsId(); ?>">
																					<textarea name="comentario" class="form-control" placeholder="Ponga aquí su comentario" rows="5"></textarea>
																				</div>
																				<div class="modal-footer">
																					<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																						Cancelar
																						<i class="fa fa-ban"></i>
																					</button>
																					<button type="submit" name="accion" value="comentario_articulo" title="Realizar un Comentario" class="btn btn-success">
																						Comentar
																						<i class="fa fa-comments"></i>
																					</button>
																				</div>
																			</fieldset>
																		</form>
																	</div>
																</div>
															</div>
														<!-- Modal Comentarios del Articulo <?php echo $articulo->cualEsId(); ?> Ends-->
														<?php endif; ?>
													</div>
													<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
													<div class="cart-button button-group">
														<button type="button" title="Quitar el Artículo de Portada" class="btn btn-danger" data-content="#borrar_articulo<?php echo $articulo->cualEsId(); ?>">
															Borrar
															<i class="fa fa-trash"></i>
														</button>
													</div>
													<!-- Modal Confirmación de Borrado de Artículo Start-->
														<div id="borrar_articulo<?php echo $articulo->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
															<div class="modal-dialog">
																<div class="modal-content">
																	<form action="" method="post" class="form-horizontal" accept-charset="utf-8" role="form">
																		<fieldset>
																			<div class="modal-header">
																				<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																				<h4>Borrar el Artículo</h4>
																			</div>
																			<div class="modal-body">
																				<input type="hidden" name="id_articulo" value="<?php echo $articulo->cualEsId(); ?>">
																				<p>¿ Esta usted segúro de que desea borrar el articulo: <?php echo $articulo->cualEsDenominacion(); ?> ?</p>
																			</div>
																			<div class="modal-footer">
																				<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																					Cancelar
																					<i class="fa fa-ban"></i>
																				</button>
																				<button type="submit" name="accion" value="borrar_articulo" title="Borrar el Artículo" class="btn btn-danger">
																					Borrar
																					<i class="fa fa-trash-o"></i>
																				</button>
																			</div>
																		</fieldset>
																	</form>
																</div>
															</div>
														</div>
													<!-- Modal Confirmación de Borrado de Artículo Ends-->
													<?php endif; ?>
												</div>
											</div>
										</div>
									<!-- Product #<?php echo $articulo->cualEsId(); ?> Ends -->
									<?php 
										endforeach;
										if ( ($miUsuario !== NULL ) AND ($miUsuario->cualEsNivel() === 'admin') ):
									?>
									<!-- Product #add in home Starts -->
										<div class="col-md-3 col-sm-5">
											<div class="product-col">
												<button type="button" title="Añadir un Artículo a la Portada" class="btn btn-success btn-lg" data-content="#alta_articulo">
													<i class="fa fa-plus fa-6x"></i> 
												</button>
											</div>
										</div>
										<!-- Modal Alta del Artículo Start-->
											<div id="alta_articulo" class="modal fade" tabindex="-1" role="dialog">
												<div class="modal-dialog modal-md">
													<div class="modal-content">
														<form action="" method="post" class="form-horizontal" enctype="multipart/form-data" accept-charset="utf-8" role="form">
															<fieldset>
																<div class="modal-header">
																	<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																	<h4>Alta de Artículo</h4>
																</div>
																<div class="modal-body">
																	<div class="form-group">
																		<label for="denominacion" class="col-sm-2 control-label">Denominación</label>
																		<div class="col-sm-10">
																			<input type="text" class="form-control" name="denominacion" id="denominacion" placeholder="Denominación...">
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="descripcion" class="col-sm-2 control-label">Descripción</label>
																		<div class="col-sm-10">
																			<textarea name="descripcion" id="descripcion" class="form-control" rows="3" placeholder="Descripción..."></textarea>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="precio" class="col-sm-2 control-label">Precio</label>
																		<div class="col-sm-4">
																			<input type="number" class="form-control" name="precio" id="precio" placeholder="Precio...">
																		</div>
																		<label for="unidades" class="col-sm-2 control-label">Unidades</label>
																		<div class="col-sm-4">
																			<input type="number" class="form-control" name="unidades" id="unidades" placeholder="Unidades...">
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="familia" class="col-sm-2 control-label">Familia</label>
																		<div class="col-sm-4">
																			<select name="familia" id="familia" class="form-control">
																				<?php
																					foreach ($miManejadorFamilias->TodosLasFamilias() as $familia):
																						if ($miFamilia->cualEsId() === $familia->cualEsId()):?>
																							<option value="<?php echo $familia->cualEsId(); ?>" selected><?php echo $familia->cualEsDenominacion(); ?></option>
																				<?php 	else: ?>
																							<option value="<?php echo $familia->cualEsId(); ?>"><?php echo $familia->cualEsDenominacion(); ?></option>
																				<?php 	endif;
																					endforeach;
																				?>
																			</select>
																		</div>
																		<div class="col-sm-6">
																			<label for="portada" class="control-label">Poner en la Portada</label>
																			<input id="portada" type="checkbox" name="portada" value="TRUE">
																			<input id="portadaOculto" type="hidden" name="portada" value="FALSE">
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="foto" class="col-sm-2 control-label">Foto</label>
																		<div class="col-sm-10">
																			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo TAMANO_MAXIMO_FOTO ?>" />
																			<!-- <input type="file" name="foto" class="form-control" placeholder="Por favor elija 3 fotos"> -->
																			<input type="file" name="foto[]" class="form-control" placeholder="Por favor elija 3 fotos" multiple="true">
																		</div>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" title="Añadir una Familia" class="btn btn-primary" data-content="#alta_familia">
																		Añadir Familia
																		<i class="fa fa-sitemap"></i>
																	</button>
																	<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																		Cancelar
																		<i class="fa fa-ban"></i>
																	</button>
																	<button type="submit" name="accion" value="alta_articulo" title="Añadir un Artículo a la Portada" class="btn btn-success">
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
										<!-- Modal Alta de la Familia Start-->
											<div id="alta_familia" class="modal fade" tabindex="-1" role="dialog">
												<div class="modal-dialog modal-md">
													<div class="modal-content">
														<form action="" method="post" class="form-horizontal" enctype="multipart/form-data" accept-charset="utf-8" role="form">
															<fieldset>
																<div class="modal-header">
																	<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																	<h4>Alta de Familia</h4>
																</div>
																<div class="modal-body">
																	<div class="form-group">
																		<label for="denominacion" class="col-sm-2 control-label">Denominación</label>
																		<div class="col-sm-6">
																			<input type="text" class="form-control" name="denominacion" id="denominacion" placeholder="Denominación...">
																		</div>
																		<label for="color" class="col-sm-2 control-label">Color</label>
																		<div class="col-sm-2">
																			<input type="color" class="form-control" name="color" id="color" placeholder="Color...">
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="descripcion" class="col-sm-2 control-label">Descripción</label>
																		<div class="col-sm-10">
																			<textarea name="descripcion" id="descripcion" class="form-control" rows="3" placeholder="Descripción..."></textarea>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="foto" class="col-sm-2 control-label">Foto</label>
																		<div class="col-sm-10">
																			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo TAMANO_MAXIMO_FOTO ?>" />
																			<input type="file" name="foto" class="form-control" placeholder="Elija la foto del Artículo">
																		</div>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																		Cancelar
																		<i class="fa fa-ban"></i>
																	</button>
																	<button type="submit" name="accion" value="alta_familia" title="Añadir una Familia" class="btn btn-success">
																		Alta
																		<i class="fa fa-upload"></i>
																	</button>
																</div>
															</fieldset>
														</form>
													</div>
												</div>
											</div>
										<!-- Modal Alta de la Familia Ends-->
									<!-- Product #add in home Ends -->
									<?php endif; ?>
									</div>
								<!-- Product Grid Display Ends -->
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