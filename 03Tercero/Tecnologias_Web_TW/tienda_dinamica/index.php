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
								<!-- Product Filter Starts Si te da tiempo pon el javascript para filtrar articulos --> 
									<!-- <div class="product-filter">
										<div class="row">
											<div class="col-md-3">
												<div class="display">
													<a href="category-list.html">
														<i class="fa fa-th-list" title="List View"></i>
													</a>
													<a href="category-grid.html" class="active">
														<i class="fa fa-th" title="Grid View"></i>
													</a>
												</div>
											</div>
											<div class="col-md-2 text-right">
												<label class="control-label">Sort</label>
											</div>
											<div class="col-md-3 text-right">
												<select class="form-control">
													<option value="default" selected="selected">Default</option>
													<option value="NAZ">Name (A - Z)</option>
													<option value="NZA">Name (Z - A)</option>
												</select>
											</div>
											<div class="col-md-1 text-right">
												<label class="control-label" for="input-limit">Show</label>
											</div>
											<div class="col-md-2 text-right">
												<select id="input-limit" class="form-control">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3" selected="selected">3</option>
												</select>
											</div>
										</div>
									</div> -->
								<!-- Product Filter Ends -->
								<!-- Product Grid Display Starts -->
									<div class="row">
									<?php 
										$miManejadorArticulos = new ArticuloDAO(TIPODB);
										$miManejadorNoticias = new NoticiaDAO(TIPODB);
										$miManejadorComentarios = new ComentarioDAO(TIPODB);
									?>
									<!-- Alerts of page Starts -->
										<?php
										/*echo "<div class='alert alert-success'><br />";
										foreach ($_POST as $key => $value)
											echo "<strong>Campo:</strong> ".htmlspecialchars($key)." <strong>Contiene:</strong> ".htmlspecialchars($value)."<br>";
										echo "</div>";*/

										// Procesamiento de los formularios de alta y modificación de las diferentes pestañas
										if(isset($_POST['accion'])){
											switch ($_POST['accion']) {
												case "quita_articulo_portada":
													$articulo_para_portada = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
													if ($miManejadorArticulos->QuitarArticuloPortada($articulo_para_portada)):
														echo "<div class='alert alert-success'>El articulo: " . $articulo_para_portada->cualEsDenominacion() . " se ha quitado a la portada</div>";
													else:
														echo "<div class='alert alert-warning'>Ha ocurrido algún problema al quitar el articulo: " . $articulo_para_portada->cualEsDenominacion() . " a la portada</div>";
													endif;
													break;
												case "alta_articulo_portada":
													$articulo_para_portada = $miManejadorArticulos->ArticuloPorId($_POST['id_articulo']);
													if ($miManejadorArticulos->AnadirArticuloPortada($articulo_para_portada)):
														echo "<div class='alert alert-success'>El articulo: " . $articulo_para_portada->cualEsDenominacion() . " se ha añadido a la portada</div>";
													else:
														echo "<div class='alert alert-warning'>Ha ocurrido algún problema al añadir el articulo: " . $articulo_para_portada->cualEsDenominacion() . " a la portada</div>";
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
												default:
													echo "<div class='alert alert-success'>No hay nada que procesar...</div>";
											}
										}
										?>
									<!-- Alerts of page Ends -->
											<!-- Recorremos los articulos de portada -->
									<?php foreach ($miManejadorArticulos->ArticulosPortada() as $articulo): ?>
									<!-- Product #<?php echo $articulo->cualEsId(); ?> Starts -->
										<div class="col-md-4 col-sm-6">
											<div class="product-col">
												<div class="image">
													<a title="Mostrar el Artículo" href="articulo.php?a=<?php echo $articulo->cualEsId(); ?>">
														<img src="<?php echo $articulo->cualEsFoto1() ?>" width="auto" height="auto" alt="articulo-<?php echo $articulo->cualEsId() ?>-imagen" class="img-responsive" />
													</a>
												</div>
												<div class="caption">
													<a title="Mostrar el Artículo" href="articulo.php?a=<?php echo $articulo->cualEsId(); ?>">
														<h4 class="title" data-content="#modal<?php echo $articulo->cualEsId(); ?>"><?php echo $articulo->cualEsDenominacion();?></h4>
														<div class="description"><?php echo substr($articulo->cualEsDescripcion(),0,100) . '...'; ?></div>
													</a>
													<!-- Ventana Modal que muestra los Articulos y las noticias del Articulo <?php echo $articulo->cualEsId(); ?> Start-->
														<div id="modal<?php echo $articulo->cualEsId(); ?>" class="modal fade" tabindex="-1" role="dialog">
															<div class="modal-dialog modal-sm">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																		<h4>Noticias y Articulos Relacionados</h4>
																	</div>
																	<div class="modal-body">
																		<?php foreach ($miManejadorNoticias->NoticiasRelacionadas($articulo) as $noticia): ?>
																			<a href="<?php echo $noticia->cualEsLink(); ?>" target="_blank"><?php echo $noticia->cualEstitulo(); ?></a><br>
																		<?php endforeach ?>
																		<hr>
																		<?php foreach ($miManejadorArticulos->ArticulosRelacionados($articulo) as $articulo_rela): ?>
																			<a href="articulo.php?a=<?php echo $articulo_rela->cualEsId(); ?>"><?php echo substr($articulo_rela->cualEsDenominacion(),0,33) . '...'; ?></a><br>
																		<?php endforeach ?>
																	</div>
																</div>
															</div>
														</div>
													<!-- Ventana Modal que muestra los Articulos y las noticias del Articulo <?php echo $articulo->cualEsId(); ?> Ends-->
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
																					<input type="hidden" name="nif_usuario" value="<?php echo $miUsuario->cualEsNif(); ?>">
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
														<form action="" method="post" accept-charset="utf-8" role="form">
															<input type="hidden" name="id_articulo" value="<?php echo $articulo->cualEsId(); ?>">
															<button type="submit" name="accion" value="quita_articulo_portada" title="Quitar el Artículo de Portada" class="btn btn-danger">
																Quitar
																<i class="fa fa-trash"></i>
															</button>
														</form>
													</div>
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
										<div class="col-md-4 col-sm-6">
											<div class="product-col">
												<button type="button" title="Añadir un Artículo a la Portada" class="btn btn-success btn-lg" data-content="#alta_articulo_portada">
													<i class="fa fa-plus fa-6x"></i> 
												</button>
											</div>
										</div>
										<!-- Modal Alta del Articulo en Portada Start-->
											<div id="alta_articulo_portada" class="modal fade" tabindex="-1" role="dialog">
												<div class="modal-dialog modal-sm">
													<div class="modal-content">
														<form action="" method="post" accept-charset="utf-8" role="form">
															<fieldset>
																<div class="modal-header">
																	<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
																	<h4>Seleccione el articulo:</h4>
																</div>
																<div class="modal-body">
																	<select name="id_articulo" class="form-control">
																		<?php
																			foreach ($miManejadorArticulos->TodosLosArticulos() as $articulo):
																				if (!$articulo->estaEnPortada()):
																		?>
																			<option value="<?php echo $articulo->cualEsId(); ?>"><?php echo $articulo->cualEsDenominacion(); ?></option>
																		<?php
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
																	<button type="submit" name="accion" value="alta_articulo_portada" title="Añadir un Artículo a la Portada" class="btn btn-success">
																		Aplicar
																		<i class="fa fa-bolt"></i>
																	</button>
																</div>
															</fieldset>
														</form>
													</div>
												</div>
											</div>
										<!-- Modal Alta del Articulo en Portada Ends-->
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
