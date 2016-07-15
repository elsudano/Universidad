<?php include ('includes/seguridad.inc'); ?>
<!doctype html>
<html lang="es">
	<?php include ('includes/head.inc'); ?>
	<body>
		<?php
			require_once('includes/articuloDAO.class.inc');
			require_once('includes/noticiaDAO.class.inc');
			require_once('includes/comentarioDAO.class.inc');
			include ('cabecera.php');
		?>
				<!-- Fila central contenido Starts-->
					<div class="row">
						<!-- Main Container Starts -->
							<div id="main-container">
								<div class="row">
									<form id="form_datos_articulo" action="" method="post" enctype="multipart/form-data" accept-charset="utf-8" role="form">
										<fieldset>
									<!-- Product Info Starts -->
										<br/>
										<?php
											// primero nos aseguramos de que el usuario introduzca un articulo en la dirección
											if (!empty($_GET)):
												// si hay un articulo en la dirección creamos el manejador y preguntamos por el articulo
												$miManejadorArticulos = new ArticuloDAO(TIPODB);
												$miArticulo = $miManejadorArticulos->ArticuloPorId($_GET['a']);
												if ($miArticulo === NULL)
													enviarA(RAIZ); // redirigimos al usuario por que no hay artículo
												// Como ya sabemos que hay articulo y que si existe creamos todo lo demas
												$miManejadorNoticias = new NoticiaDAO(TIPODB);
												$miManejadorComentarios = new ComentarioDAO(TIPODB);
											else:
												$_SESSION['error'] = "Fichero " . basename(__FILE__) . ": La variable del articulo seleccionado no es correcta";
												enviarA(RAIZ); // redirigimos al usuario
											endif;

											// Procesamiento de los formularios de alta y modificación de las diferentes pestañas
											if(isset($_POST['accion'])):
												switch ($_POST['accion']) {
													case "actualizar_articulo":
														// para cuando no se pone en la portada
														$portada = FALSE;
														if (isset($_POST['portada']))
															$portada = $_POST['portada'];

														if ($miManejadorArticulos->ActualizarArticulo($miArticulo,$_POST['denominacion'],$_POST['descripcion'],$_POST['precio'],$_POST['unidades'],$portada,$_POST['familia'],$_FILES)):
															echo "<div class='alert alert-success'>El Artículo: " . $miArticulo->cualEsDenominacion() . " se ha actualizado correctamente</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al actualizar el artículo: " . $miArticulo->cualEsDenominacion() . "</div>";
														endif;
														break;
													case "borrar_articulo":
														if ($miManejadorArticulos->BorrarArticulo($miArticulo)):
															echo "<div class='alert alert-success'>El Artículo: " . $miArticulo->cualEsDenominacion() . " se ha borrado correctamente</div>";
															enviarA(RAIZ); // redirigimos al usuario por que no hay artículo
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al borrar el artículo: " . $miArticulo->cualEsDenominacion() . "</div>";
														endif;
														break;
													case "anadir_articulo_relacionado":
														if ( $miManejadorArticulos->AnadirArticuloRelacionado($miArticulo,$miManejadorArticulos->ArticuloPorId($_POST['id_articulo'])) ):
															echo "<div class='alert alert-success'>Articulo relacionado</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al relacionar el artículo</div>";
														endif;
														break;
													case "quitar_articulo_relacionado":
														$articulo_relacionado = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
														if ( $miManejadorArticulos->QuitarArticuloRelacionado($miArticulo,$articulo_relacionado)):
															echo "<div class='alert alert-success'>El Artículo: " . $articulo_relacionado->cualEsDenominacion() . " se ha quitado correctamente</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al quitar el artículo: " . $articulo_relacionado->cualEsDenominacion() . " de relacionados</div>";
														endif;
														break;
													case "anadir_noticia_relacionada":
														if (isset($_POST['titulo']) AND !empty($_POST['titulo'])):
															$boolean = preg_match('#^image\/(jpeg|png|gif|tiff|)$#',$_FILES['foto']['type']);

															if ( isset($_FILES['foto']['error']) AND $_FILES['foto']['error'] === 0 AND  $boolean):
																// aqui se puede poner un gestor de errores en las siguientes versiones
																$fileName = $_FILES['foto']['name'];
																$fileSize = $_FILES['foto']['size'];
																$fileType = $_FILES['foto']['type'];
																$datos = file_get_contents($_FILES['foto']['tmp_name']);
															else:
																$datos = "Sin Foto";
															endif;

															if ( $boolean AND $miManejadorNoticias->AnadirNoticiaRelacionada($miArticulo,$_POST['titulo'], $_POST['descripcion'], $_POST['link'], $datos ) ):
																echo "<div class='alert alert-success'>La Noticia: " . $_POST['titulo'] . " se ha añadido al Artículo</div>";
															else:
																echo "<div class='alert alert-warning'>Ha ocurrido algún problema al añadir la noticia: " . $_POST['titulo'] . "</div>";
															endif;
														else:
															$noticia_relacionada = $miManejadorNoticias->NoticiaPorId($_POST['id_noticia']);
															if ($miManejadorNoticias->AnadirNoticiaRelacionada($miArticulo,$noticia_relacionada)):
																echo "<div class='alert alert-success'>La Noticia: " . $noticia_relacionada->cualEsTitulo() . " se ha añadido al Artículo</div>";
															else:
																echo "<div class='alert alert-warning'>Ha ocurrido algún problema al añadir la noticia: " . $noticia_relacionada->cualEsTitulo() . "</div>";
															endif;
														endif;
														break;
													case "quitar_noticia_relacionada":
														$noticia_relacionada = $miManejadorNoticias->NoticiaPorId($_POST['id_noticia_relacionada']);
														if ($miManejadorNoticias->QuitarNoticiaRelacionada($miArticulo,$noticia_relacionada)):
															echo "<div class='alert alert-success'>La Noticia: " . $noticia_relacionada->cualEsTitulo() . " se ha quitado del Artículo</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al quitar la noticia: " . $noticia_relacionada->cualEsTitulo() . "</div>";
														endif;
														break;
													case "anadir_comentario_articulo":
														$comentario_articulo = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
														if ($miManejadorComentarios->AnadirComentario($miUsuario,$comentario_articulo,$_POST['comentario'])):
															echo "<div class='alert alert-success'>El comentario se ha añadido al articulo: " . $comentario_articulo->cualEsDenominacion() . "</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al añadir el comentario al artículo: " . $comentario_articulo->cualEsDenominacion() . "</div>";
														endif;
														break;
													case "quitar_comentario_articulo":
														if ($miManejadorComentarios->BorrarComentario($miManejadorComentarios->ComentarioPorId($_POST['id_comentario']))):
															echo "<div class='alert alert-success'>El comentario se ha borrado con exito</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al borrar el comentario del artículo: " . $miArticulo->cualEsDenominacion() . "</div>";
														endif;
														break;
													case "borrar_foto_articulo":
														if ($miManejadorArticulos->BorrarFotoArticulo($miArticulo,$_POST['foto'])):
															echo "<div class='alert alert-success'>La foto del artículo " . $miArticulo->cualEsDenominacion() . " se ha borrado con éxito</div>";
														else:
															echo "<div class='alert alert-warning'>Ha ocurrido algún problema al borrar la foto del artículo: " . $miArticulo->cualEsDenominacion() . "</div>";
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
											endif;
										?>
											<div class="row product-info">
												<!-- Product Name Starts -->
													<?php if (($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin')){ ?>
														<div class="col-md-offset-1 col-md-10" data-container="body" data-toggle="popover" data-placement="top" data-content="Prueba de tooltip">
															<input name="denominacion" type="text" class="form-control" value="<?php echo htmlspecialchars($miArticulo->cualEsDenominacion()); ?>">
														</div>
													<?php }else{ ?>
													<div class="col-md-offset-1 col-md-10">
														<h2><?php echo $miArticulo->cualEsDenominacion(); ?></h2>
													</div>
													<?php } ?>
												<!-- Product Name Ends -->
												<!-- Left Starts -->
													<div class="col-md-offset-1 col-md-6 images-block">
													<?php if ($miArticulo->cualEsFoto1() !== NULL):?>
														<p>
															<img src="<?php echo $miArticulo->cualEsFoto1() ?>" width="auto" height="auto" alt="articulo-<?php echo $miArticulo->cualEsId() ?>-imagen" class="img-responsive" />
														</p>
														<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):?>
															<button type="button" title="Borrar Foto de Artículo" class="btn btn-danger" data-content="#borrar_foto_articulo1">
																<i class="fa fa-trash"></i>
															</button>
															<!-- Modal Borrar Foto principal del Articulo Start-->
																<div id="borrar_foto_articulo1" class="modal fade" tabindex="-1" role="dialog">
																	<div class="modal-dialog modal-sm">
																		<div class="modal-content">
																			<div class="modal-header">
																				<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																				<h4>¿Borrar la Foto del Artículo?</h4>
																			</div>
																			<div class="modal-body">
																				<p>¿Realmente quiere BORRAR la Foto de este artículo?</p>
																			</div>
																			<div class="modal-footer">
																				<form id="form_borrar_foto_articulo" action="" method="post" accept-charset="utf-8" role="form">
																					<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																						Cancelar
																						<i class="fa fa-ban"></i>
																					</button>
																					<input type="hidden" name="foto" value="1">
																					<button type="submit" name="accion" value="borrar_foto_articulo" title="Borrar Foto del Artículo" class="btn btn-danger">
																						Borrar
																						<i class="fa fa-trash"></i>
																					</button>
																				</form>
																			</div>
																		</div>
																	</div>
																</div>
															<!-- Modal Borrar Foto principal del Articulo Ends-->
														<?php endif; ?>
													<?php endif; ?>
														<ul class="list-unstyled list-inline">
														<?php if ($miArticulo->cualEsFoto2() !== NULL):?>
															<li>
																<img src="<?php echo $miArticulo->cualEsFoto2() ?>" width="auto" height="auto" alt="articulo-<?php echo $miArticulo->cualEsId() ?>-imagen" class="img-responsive" />
																<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):?>
																<button type="button" title="Borrar Foto de Artículo" class="btn btn-danger" data-content="#borrar_foto_articulo2">
																	<i class="fa fa-trash"></i>
																</button>
																<!-- Modal Borrar Fotos Secundaria del Articulo Start-->
																	<div id="borrar_foto_articulo2" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																					<h4>¿Borrar la Foto del Artículo?</h4>
																				</div>
																				<div class="modal-body">
																					<p>¿Realmente quiere BORRAR la Foto de este artículo?</p>
																				</div>
																				<div class="modal-footer">
																					<form id="form_borrar_foto_articulo" action="" method="post" accept-charset="utf-8" role="form">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<input type="hidden" name="foto" value="2">
																						<button type="submit" name="accion" value="borrar_foto_articulo" title="Borrar Foto del Artículo" class="btn btn-danger">
																							Borrar
																							<i class="fa fa-trash"></i>
																						</button>
																					</form>
																				</div>
																			</div>
																		</div>
																	</div>
																<!-- Modal Borrar Fotos Secundaria del Articulo Ends-->
																<?php endif; ?>
															</li>
															<?php endif;
															if ($miArticulo->cualEsFoto3() !== NULL):?>
															<li>
																<img src="<?php echo $miArticulo->cualEsFoto3() ?>" width="auto" height="auto" alt="articulo-<?php echo $miArticulo->cualEsId() ?>-imagen" class="img-responsive" />
																<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):?>
																<button type="button" title="Borrar Foto de Artículo" class="btn btn-danger" data-content="#borrar_foto_articulo3">
																	<i class="fa fa-trash"></i>
																</button>
																<!-- Modal Borrar Fotos Secundaria del Articulo Start-->
																	<div id="borrar_foto_articulo3" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																					<h4>¿Borrar la Foto del Artículo?</h4>
																				</div>
																				<div class="modal-body">
																					<p>¿Realmente quiere BORRAR la Foto de este artículo?</p>
																				</div>
																				<div class="modal-footer">
																					<form id="form_borrar_foto_articulo" action="" method="post" accept-charset="utf-8" role="form">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<input type="hidden" name="foto" value="3">
																						<button type="submit" name="accion" value="borrar_foto_articulo" title="Borrar Foto del Artículo" class="btn btn-danger">
																							Borrar
																							<i class="fa fa-trash"></i>
																						</button>
																					</form>
																				</div>
																			</div>
																		</div>
																	</div>
																<!-- Modal Borrar Fotos Secundaria del Articulo Ends-->
																<?php endif; ?>
															</li>
														<?php endif; ?>
														</ul>
													<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
														<input type="file" name="foto[]" placeholder="Por favor elija 3 fotos" multiple="true">
													<?php endif; ?>
													</div>
												<!-- Left Ends -->
												<!-- Right Starts -->
													<?php if ($miUsuario !== NULL){ ?>
													<div class="col-md-4 product-details">
														<!-- Manufacturer Starts -->
															<?php if ($miUsuario->cualEsNivel() === 'usuario'): ?>
															<ul class="list-unstyled manufacturer">
																<li>
																	<span>Disponibilidad:</span>
																	<?php
																	$unidades = $miArticulo->cualEsUnidades();
																	if ( $unidades > UNIDADES_MINIMAS){ ?>
																	<strong class="label label-success">Disponible</strong>
																	<?php }elseif (0 < $unidades AND $unidades <= UNIDADES_MINIMAS){ ?>
																	<strong class="label label-warning">Pocas unidades</strong>
																	<?php }else{ ?>
																	<strong class="label label-danger">Sin Stock</strong>
																	<?php } ?>
																</li>
															</ul>
															<?php
															endif;
															if ($miUsuario->cualEsNivel() === 'admin'):
															?>
															<ul class="list-unstyled manufacturer">
																<li>
																	<fieldset>
																		<label for="unidades">Unidades:</label>
																		<input class="price-new" name="unidades" type="number" value="<?php echo $miArticulo->cualEsUnidades(); ?>">
																	</fieldset>
																</li>
															</ul>
															<hr />
															<ul class="list-unstyled manufacturer">
																<li>
																	<fieldset>
																		<label for="familia">Familia:</label>
																		<select name="familia" id="familia" class="form-control">
																			<?php foreach ($miManejadorFamilias->TodosLasFamilias() as $familia): ?>
																				<option value="<?php echo $familia->cualEsId(); ?>"><?php echo $familia->cualEsDenominacion(); ?></option>
																			<?php endforeach; ?>
																		</select>
																	</fieldset>
																</li>
															</ul>
															<hr />
															<ul class="list-unstyled manufacturer">
																<li>
																	<fieldset>
																		<input type="checkbox" name="portada" value="true">
																		<label for="portada" class="control-label">Poner en la Portada</label>
																	</fieldset>
																</li>
															</ul>
															<?php endif; ?>
															<hr />
														<!-- Manufacturer Ends -->
														<!-- Price Starts -->
															<?php if ($miUsuario->cualEsNivel() === 'usuario'){ ?>
															<div class="price">
																<span class="price-head">Precio :</span>
																<span class="price-new"><?php echo $miArticulo->cualEsPrecio() . ' ' . TIPO_MONEDA; ?></span> 
															</div>
															<?php
															}
															if ($miUsuario->cualEsNivel() === 'admin'){
															?>
															<div class="price">
																<fieldset>
																	<label for="precio">Precio:</label>
																	<input class="price-new" name="precio" type="number" size="5" value="<?php echo $miArticulo->cualEsPrecio(); ?>"><?php echo ' ' . TIPO_MONEDA ?>
																</fieldset>
															</div>
															<?php } ?>
															<hr />
														<!-- Price Ends -->
														<!-- Available Options Starts -->
															<div class="options">
															<?php if ($miUsuario->cualEsNivel() === 'usuario'){	?>
																<div class="cart-button button-group">
																	<button type="button" title="Realizar un Comentario" class="btn btn-info comentario" data-content="#anadir_comentario_articulo">
																		<i class="fa fa-comments"></i>
																	</button>
																	<button type="button" title="Compra el Artículo" class="btn btn-primary">
																		Comprar
																		<i class="fa fa-shopping-cart"></i> 
																	</button>
																</div>
																<!-- Modal Comentarios del Articulo Start-->
																	<div id="anadir_comentario_articulo" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-md">
																			<div class="modal-content">
																				<form id="form_anadir_comentario_articulo" action="" method="post" accept-charset="utf-8" role="form">
																					<div class="modal-header">
																						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																						<h4>Publique un Comentario sobre:</h4>
																						<p><?php echo $miArticulo->cualEsDenominacion(); ?></p>
																					</div>
																					<div class="modal-body">
																						<input type="hidden" name="id_articulo" value="<?php echo $miArticulo->cualEsId(); ?>">
																						<textarea name="comentario" class="form-control" placeholder="Ponga aquí su comentario" rows="3"></textarea>
																					</div>
																					<div class="modal-footer">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<button type="submit" name="accion" value="anadir_comentario_articulo" title="Realizar un Comentario" class="btn btn-success">
																							Comentar
																							<i class="fa fa-comments"></i>
																						</button>
																					</div>
																				</form>
																			</div>
																		</div>
																	</div>
																<!-- Modal Comentarios del Articulo Ends-->
															<?php }
															if ($miUsuario->cualEsNivel() === 'admin'){
															?>
																<div class="button-group">
																	<button type="button" title="Borrar Artículo" class="btn btn-danger" data-content="#borrar_articulo">
																		Borrar
																		<i class="fa fa-trash"></i>
																	</button>
																	<button type="button" title="Actualizar el Artículo" class="btn btn-warning" data-content="#actualizar_articulo">
																		Actualizar
																		<i class="fa fa-refresh fa-spin"></i> 
																	</button>
																</div>
																<!-- Modal Borrar el Articulo Start-->
																	<div id="borrar_articulo" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<form id="form_borrar_articulo" action="" method="post" enctype="multipart/form-data" accept-charset="utf-8" role="form">
																					<div class="modal-header">
																						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																						<h4>¿Quitar el Artículo?</h4>
																					</div>
																					<div class="modal-body">
																						<p>¿Realmente quiere BORRAR este artículo?</p>
																					</div>
																					<div class="modal-footer">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<button type="submit" name="accion" value="borrar_articulo" title="Borrar Artículo" class="btn btn-danger">
																							Borrar
																							<i class="fa fa-trash"></i>
																						</button>
																					</div>
																				</form>
																			</div>
																		</div>
																	</div>
																<!-- Modal Borrar el Articulo Ends-->
																<!-- Modal Actualizar el Articulo Start-->
																	<div id="actualizar_articulo" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																					<h4>¿Actualizar el Artículo?</h4>
																				</div>
																				<div class="modal-body">
																					<p>¿Realmente quiere ACTUALIZAR el artículo con estos datos?</p>
																				</div>
																				<div class="modal-footer">
																					<!-- <form id="form_datos_articulo" action="" method="post" enctype="multipart/form-data" accept-charset="utf-8" role="form"> -->
																						<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<input type="hidden" name="accion" value="actualizar_articulo">
																						<button type="button" name="accion" value="actualizar_articulo" title="Actualizar el Artículo" class="btn btn-success" onclick="$('#form_datos_articulo').submit()">
																							Actualizar
																							<i class="fa fa-bolt"></i>
																						</button>
																					<!-- </form> -->
																				</div>
																			</div>
																		</div>
																	</div>
																<!-- Modal Actualizar el Articulo Ends-->
															<?php } ?>
															</div>
															<hr />
														<!-- Available Options Ends -->
													</div>
													<?php } ?>
												<!-- Right Ends -->
											</div>
									<!-- product Info Ends -->
									<!-- Product Description Left Starts -->
										<div class="col-md-6 col-sm-8">
											<div class="product-info-box">
												<h4 class="heading">Descripción</h4>
												<div class="content panel-smart">
													<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
													<fieldset form="form_datos_articulo">
														<textarea name="descripcion" class="form-control" rows="10"><?php echo $miArticulo->cualEsDescripcion(); ?></textarea>
													</fieldset>
													<?php else: ?>
													<p class="text-left"><?php echo $miArticulo->cualEsDescripcion(); ?></p>
													<?php endif; ?>
												</div>
											</div>
										</div>
									<!-- Product Description Left Ends -->
									<!-- Comments of Products Right Starts -->
										<div class="col-md-6 col-sm-8">
											<div class="product-info-box">
												<h4 class="heading">Comentarios</h4>
												<div class="content panel-smart">
													<div class="comentarios">
														<?php foreach ($miManejadorComentarios->ComentariosPorArticulo($miArticulo) as $comentario): ?>
															<div class="col-sm-10">
																<div class="row text-left text-uppercase">
																	<h5><?php echo $comentario->cualEsComentario(); ?></h5>
																</div>
																<div class="row">
																	<div class="col-sm-6 text-left text-muted">
																		<?php
																			$usuario_de_comentario = $miManejadorDeUsuarios->UsuarioPorId($comentario->cualEsUsuario()); 
																			echo $usuario_de_comentario->cualEsNombre() . ' ' . $usuario_de_comentario->cualEsApellidos();
																		?>
																	</div>
																	<div class="col-sm-6 text-right text-muted">
																		<?php echo $comentario->cualEsFecha() . ' , ' . $comentario->cualEsHora(); ?>
																	</div>
																</div>
															</div>
															<div class="col-sm-2">
																<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
																<div class="row">
																	<button type="button" title="Borrar Comentario" class="btn btn-danger btn-sm comentario" data-content="#quitar_comentario_articulo<?php echo $comentario->cualEsId(); ?>">
																		<i class="fa fa-times"></i>
																	</button>
																<!-- Modal Borrar el Comentario del Articulo  Start-->
																	<div id="quitar_comentario_articulo<?php echo $comentario->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<form action="" method="post" accept-charset="utf-8" role="form">
																					<div class="modal-header">
																						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																						<h4>¿Quitar el Comentario?</h4>
																					</div>
																					<div class="modal-body">
																						<input type="hidden" name="id_comentario" value="<?php echo $comentario->cualEsId(); ?>">
																						<p>¿Realmente quiere BORRAR este comentario?</p>
																					</div>
																					<div class="modal-footer">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<button type="submit" name="accion" value="quitar_comentario_articulo" title="Borrar Comentario" class="btn btn-danger">
																							Borrar
																							<i class="fa fa-trash"></i>
																						</button>
																					</div>
																				</form>
																			</div>
																		</div>
																	</div>
																<!-- Modal Borrar el Comentario del Articulo Ends-->
																</div>
																<?php else: ?>
																<div class="row">
																	<img src="<?php echo $usuario_de_comentario->cualEsFoto() ?>" width="50%" height="auto" alt="usuario-imagen" class="img-responsive" />
																</div>
																<?php endif; ?>

															</div>
														<?php endforeach; ?>
													</div>
													<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
													<br/>
													<button type="button" title="Realizar un Comentario" class="btn btn-info comentario" data-content="#anadir_comentario_articulo_admin">
														Comentar
														<i class="fa fa-comments"></i>
													</button>
													<!-- Modal Comentarios del Articulo Start-->
														<div id="anadir_comentario_articulo_admin" class="modal fade" tabindex="-1" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<form action="" method="post" accept-charset="utf-8" role="form">
																		<div class="modal-header">
																			<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																			<h4>Publique un Comentario sobre:</h4>
																			<p><?php echo $miArticulo->cualEsDenominacion(); ?></p>
																		</div>
																		<div class="modal-body">
																			<input type="hidden" name="id_articulo" value="<?php echo $miArticulo->cualEsId(); ?>">
																			<textarea name="comentario" class="form-control" placeholder="Ponga aquí su comentario" rows="3"></textarea>
																		</div>
																		<div class="modal-footer">
																			<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																				Cancelar
																				<i class="fa fa-ban"></i>
																			</button>
																			<button type="submit" name="accion" value="anadir_comentario_articulo" title="Realizar un Comentario" class="btn btn-success">
																				Comentar
																				<i class="fa fa-comments"></i>
																			</button>
																		</div>
																	</form>
																</div>
															</div>
														</div>
													<!-- Modal Comentarios del Articulo Ends-->
													<?php endif; ?>
												</div>
											</div>
										</div>
									<!-- Comments of Products Right Ends -->
										</fieldset>
									</form>
								</div>
								<!-- Related Products and News Starts -->
									<div class="row">
										<!-- Related Products Right Starts -->
											<div class="col-md-8">
												<div class="product-info-box">
													<h4 class="heading">Productos Relacionados</h4>
												<!-- Products Row Starts -->
													<div class="row">
													<?php 
													$cantidad_arti = 0;
													foreach ($miManejadorArticulos->ArticulosRelacionados($miArticulo) as $articulo_rela): ?>
													<!-- Product #<?php echo $articulo_rela->cualEsId(); ?> Starts -->
														<div class="col-md-4 col-sm-6">
															<div class="product-col">
																<div class="image">
																	<a href="articulo.php?a=<?php echo $articulo_rela->cualEsId(); ?>" title="Mostrar el Artículo Relacionado">
																		<img src="<?php echo $articulo_rela->cualEsFoto1() ?>" width="auto" height="30%" alt="articulo-<?php echo $articulo_rela->cualEsId() ?>-imagen" class="img-responsive" />
																	</a>
																</div>
																<div class="caption">
																	<a href="articulo.php?a=<?php echo $articulo_rela->cualEsId(); ?>" title="Mostrar el Artículo Relacionado">
																		<h4 class="title"><?php echo $articulo_rela->cualEsDenominacion(); ?></h4>
																	</a>
																	<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'usuario' OR $miUsuario->cualEsNivel() === 'admin') ): ?>
																	<a href="articulo.php?a=<?php echo $articulo_rela->cualEsId(); ?>" title="Mostrar el Artículo Relacionado">
																		<div class="price">
																			<span class="price-new"><?php echo $articulo_rela->cualEsPrecio() . ' ' . TIPO_MONEDA; ?></span> <br />
																			<span class="price-old"><?php echo $articulo_rela->cualEsPrecio()-($articulo_rela->cualEsPrecio()*POR_PRE_ANT) . ' ' . TIPO_MONEDA; ?></span>
																		</div>
																	</a>
																	<?php
																		endif;
																		if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):
																	?>
																	<br>
																	<div class="button-group">
																		<button type="button" title="Quitar un Artículo Relacionado" class="btn btn-danger" data-content="#quitar_articulo_relacionado<?php echo $articulo_rela->cualEsId(); ?>">
																			Quitar
																			<i class="fa fa-trash-o"></i>
																		</button>
																	</div>
																	<!-- Modal Baja del Articulo Relacionado Start-->
																		<div id="quitar_articulo_relacionado<?php echo $articulo_rela->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
																			<div class="modal-dialog modal-sm">
																				<div class="modal-content">
																					<form action="" method="post" accept-charset="utf-8" role="form">
																						<div class="modal-header">
																							<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																							<h4>¿Quitar el Artículo?</h4>
																						</div>
																						<div class="modal-body">
																							<input type="hidden" name="id_articulo" value="<?php echo $articulo_rela->cualEsId(); ?>">
																							<p>¿Realmente quiere quitar este artículo de los relacionados?</p>
																						</div>
																						<div class="modal-footer">
																							<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																								Cancelar
																								<i class="fa fa-ban"></i>
																							</button>
																							<button type="submit" name="accion" value="quitar_articulo_relacionado" title="Quitar el Artículo relacionado" class="btn btn-danger">
																								Quitar
																								<i class="fa fa-trash"></i>
																							</button>
																						</div>
																					</form>
																				</div>
																			</div>
																		</div>
																	<!-- Modal Baja del Articulo Relacionado Ends-->
																	<?php endif; ?>
																</div>
															</div>
														</div>
													<!-- Product #<?php echo $articulo_rela->cualEsId(); ?> Ends -->
													<?php
														$cantidad_arti += 1;
													endforeach;

														// con esto controlamos cual es el maximo de articulos relacionados que se pueden ver
														if ( ($cantidad_arti < MAX_ARTI_RELA) AND ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):
													?>
													<!-- Product #add Starts -->
														<div class="col-md-4 col-sm-6">
															<div class="product-col">
																<button type="button" title="Añadir un Artículo Relacionado" class="btn btn-success btn-md" data-content="#anadir_articulo_relacionado">
																	<i class="fa fa-plus fa-4x"></i> 
																</button>
															</div>
														</div>
														<!-- Modal Alta del Articulo Relacionado Start-->
															<div id="anadir_articulo_relacionado" class="modal fade" tabindex="-1" role="dialog">
																<div class="modal-dialog modal-sm">
																	<div class="modal-content">
																		<form action="" method="post" accept-charset="utf-8" role="form">
																			<div class="modal-header">
																				<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																				<h4>Seleccione el articulo:</h4>
																			</div>
																			<div class="modal-body">
																				<select name="id_articulo" class="form-control">
																					<?php
																						// recorro todos los articulos
																						foreach ($miManejadorArticulos->TodosLosArticulos() as $articulo_origen):
																							// variable que me sirve para saber si tengo que pintar la opción
																							$muestra_opcion = TRUE;
																							//compruebo que el articulo que quiero añadir no es el mismo en el que me encuentro
																							if ($miArticulo->cualEsId() !== $articulo_origen->cualEsId()):
																								// recorro todos los articulos que ya tiene relacionado el articulo en donde estoy
																								foreach ($miManejadorArticulos->ArticulosRelacionados($miArticulo) as $articulo_relacion):
																									// compuebo que el articulo a pintar no esta en los relacionados
																									if ($articulo_origen->cualEsId() === $articulo_relacion->cualEsId()):
																										$muestra_opcion = FALSE;
																									endif;
																								endforeach;
																								if ($muestra_opcion): ?>
																					<option value="<?php echo $articulo_origen->cualEsId(); ?>"><?php echo $articulo_origen->cualEsDenominacion(); ?></option>
																					<?php 		endif;
																							endif;
																						endforeach;
																					?>
																				</select>
																			</div>
																			<div class="modal-footer">
																				<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																					Cancelar
																					<i class="fa fa-ban"></i>
																				</button>
																				<button type="submit" name="accion" value="anadir_articulo_relacionado" title="Añadir un Artículo relacionado" class="btn btn-success">
																					Aplicar
																					<i class="fa fa-bolt"></i>
																				</button>
																			</div>
																		</form>
																	</div>
																</div>
															</div>
														<!-- Modal Alta del Articulo Relacionado Ends-->
													<!-- Product #add Ends -->
													<?php endif; ?>
													</div>
												<!-- Products Row Ends -->
												</div>
											</div>
										<!-- Related Products Right Ends -->
										<!-- News of Products Left Starts -->
											<div class="col-md-4">
												<div class="product-info-box">
													<h4 class="heading">Noticias</h4>
													<?php
													$cantidad_noti = 0;
													foreach ($miManejadorNoticias->NoticiasRelacionadas($miArticulo) as $noticia):
													?>
														<div class="col-md-12 col-sm-12">
															<div class="product-col">
																<a href="<?php echo $noticia->cualEsLink(); ?>" target="_blank" alt="noticia-<?php echo $noticia->cualEsId() ?>">
																<img src="<?php echo $noticia->cualEsFoto() ?>" width="auto" height="20%" alt="noticia-<?php echo $noticia->cualEsId() ?>-imagen" class="img-responsive" />
																<p><?php echo $noticia->cualEsTitulo(); ?></p></a>
															<?php if ( ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ): ?>
																<button type="button" title="Quitar Noticia Relacionada" class="btn btn-danger" data-content="#quitar_noticia_relacionada<?php echo $noticia->cualEsId() ?>">
																	Quitar
																	<i class="fa fa-trash-o"></i> 
																</button>
																<!-- Modal Quitar de Noticia Relacionada Start-->
																	<div id="quitar_noticia_relacionada<?php echo $noticia->cualEsId() ?>" class="modal fade" tabindex="-1" role="dialog">
																		<div class="modal-dialog modal-sm">
																			<div class="modal-content">
																				<form action="" method="post" accept-charset="utf-8" role="form">
																					<div class="modal-header">
																						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																						<h4>Quitar la Noticia Relacionada:</h4>
																					</div>
																					<div class="modal-body">
																						<input type="hidden" name="id_noticia_relacionada" value="<?php echo $noticia->cualEsId() ?>">
																						<p>¿Realmente quiere quitar la noticia relacionada de este Artículo?</p>
																					</div>
																					<div class="modal-footer">
																						<button type="button" title="Cerrar la Ventana" class="btn btn-default" data-dismiss="modal">
																							Cancelar
																							<i class="fa fa-ban"></i>
																						</button>
																						<button type="submit" name="accion" value="quitar_noticia_relacionada" title="Añadir una Noticia relacionado" class="btn btn-danger">
																							Quitar
																							<i class="fa fa-trash"></i>
																						</button>
																					</div>
																				</form>
																			</div>
																		</div>
																	</div>
																<!-- Modal Quitar de Noticia Relacionada Ends-->
															<?php endif;?>
															</div>
														</div>
													<?php
														$cantidad_noti += 1;
													endforeach;

													if ( ($cantidad_noti < MAX_NOTI_RELA) AND ($miUsuario !== NULL) AND ($miUsuario->cualEsNivel() === 'admin') ):
													?>
													<!-- News #add Starts -->
														<div class="col-md-12 col-sm-12">
															<div class="product-col">
																<button type="button" title="Añadir una Noticia Relacionada" class="btn btn-success btn-md" data-content="#anadir_noticia_relacionada">
																	<i class="fa fa-plus fa-4x"></i> 
																</button>
															</div>
														</div>
														<!-- Modal Alta de Noticia Relacionada Start-->
															<div id="anadir_noticia_relacionada" class="modal fade" tabindex="-1" role="dialog">
																<div class="modal-dialog modal-md">
																	<div class="modal-content">
																		<form action="" class="form-horizontal" method="post" enctype="multipart/form-data" accept-charset="utf-8" role="form">
																			<div class="modal-header">
																				<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																				<h4>Alta de Noticia Relacionada:</h4>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="id_noticia" class="col-sm-4 control-label text-left">Seleccione una noticia:</label>
																					<div class="col-sm-8">
																						<select name="id_noticia" class="form-control">
																							<?php
																								// recorro todos los articulos
																								foreach ($miManejadorNoticias->TodasLasNoticias() as $noticia_origen):
																									// variable que me sirve para saber si tengo que pintar la opción
																									$muestra_opcion = TRUE;
																									// recorro todas las noticias que ya estan relacionadas con el articulo
																									foreach ($miManejadorNoticias->NoticiasRelacionadas($miArticulo) as $noticia_relacion):
																										// compuebo que la noticia a pintar no esta en las relacionadas
																										if ($noticia_origen->cualEsId() === $noticia_relacion->cualEsId()):
																											$muestra_opcion = FALSE;
																										endif;
																									endforeach;
																									if ($muestra_opcion): ?>
																							<option value="<?php echo $noticia_origen->cualEsId(); ?>"><?php echo $noticia_origen->cualEsTitulo(); ?></option>
																							<?php 	endif;
																								endforeach;
																							?>
																						</select>
																					</div>
																				</div>
																				<hr>
																				<strong>Ó puede crear una:</strong>
																				<div class="form-group">
																					<label for="titulo" class="col-sm-2 control-label">Titulo</label>
																					<div class="col-sm-10">
																						<input type="text" class="form-control" name="titulo" id="titulo" placeholder="Titulo...">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="descripcion" class="col-sm-2 control-label">Descripción</label>
																					<div class="col-sm-10">
																						<textarea name="descripcion" id="descripcion" class="form-control" rows="3" placeholder="Descripción..."></textarea>
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="link" class="col-sm-2 control-label">Enlace</label>
																					<div class="col-sm-10">
																						<input type="url" class="form-control" name="link" id="link" placeholder="Enlace...">
																					</div>
																				</div>
																				<div class="form-group">
																					<label for="foto" class="col-sm-2 control-label">Foto</label>
																					<div class="col-sm-10">
																						<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo TAMANO_MAXIMO_FOTO ?>" />
																						<input type="file" name="foto" class="form-control" placeholder="Imagen de la Noticia">
																					</div>
																				</div>
																			</div>
																			<div class="modal-footer">
																				<button type="button" title="Cerrar la Ventana" class="btn btn-danger" data-dismiss="modal">
																					Cancelar
																					<i class="fa fa-ban"></i>
																				</button>
																				<button type="submit" name="accion" value="anadir_noticia_relacionada" title="Añadir una Noticia relacionado" class="btn btn-success">
																					Aplicar
																					<i class="fa fa-bolt"></i>
																				</button>
																			</div>
																		</form>
																	</div>
																</div>
															</div>
														<!-- Modal Alta de Noticia Relacionada Ends-->
													<!-- News #add Ends -->
													<?php endif?>
												</div>
											</div>
										<!-- News of Products Left Ends -->
									</div>
								<!-- Related Products and News Ends -->
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