<?php
require_once('includes/familiaDAO.class.inc'); // para poder representar el menu

$miManejadorFamilias = new FamiliaDAO(TIPODB);
?>
<!-- Main Menu Starts -->
<nav id="main-menu" class="navbar" role="navigation">
	<div class="container">
		<!-- Nav Header Starts -->
		<div class="navbar-header">
			<button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-cat-collapse">
				<span class="sr-only">Navegador</span>
				<i class="fa fa-bars"></i>
			</button>
		</div>
		<!-- Nav Header Ends -->
		<!-- Navbar Cat collapse Starts -->
		<div class="collapse navbar-collapse navbar-cat-collapse">
			<ul class="nav navbar-nav">
				<li><a href="index.php">Portada</a></li>
				<?php foreach ($miManejadorFamilias->TodosLasFamilias() as $familia): ?>
					<li><a href="articulos.php?f=<?php echo $familia->cualEsId(); ?>"><?php echo $familia->cualEsDenominacion(); ?></a></li>
				<?php endforeach;?>
				<!-- <li class="dropdown">
					<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">
						Paginas
					</a>
					<ul class="dropdown-menu" role="menu">
						<li><a tabindex="-1" href="index.php">Home</a></li>
						<li><a tabindex="-1" href="articulos.php">Articulos-Familias</a></li>
						<li><a tabindex="-1" href="articulo.php">Articulo</a></li>
						<li><a tabindex="-1" href="entrar.php">Validarse</a></li>
						<li><a tabindex="-1" href="registro.php">Registro</a></li>
						<li><a tabindex="-1" href="contacto.php">Contacto</a></li>
					</ul>
				</li> -->
				<li class="dropdown">
					<?php if ($_SESSION['logueado']): ?>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10"><?php echo $miUsuario->cualEsNombre() . ' (' . $miUsuario->cualEsNivel() . ')'; ?></a>
						<ul class="dropdown-menu" role="menu">
							<?php if ( $miUsuario !== NULL AND $miUsuario->cualEsNivel() === 'admin' ): ?>
								<li><a href="familias.php">Familias</a></li>
								<li><a href="noticias.php">Noticias</a></li>
								<li><a href="usuarios.php">Usuarios</a></li>
							<?php endif;?>
							<li>
								<form method="post" class="form-horizontal" role="form">
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-9">
											<input type="hidden" name="ruta" value="<?php
											// Esto es para poder recoger todas las variables get que hay en la URL
											// sea cual sea esta de esa manera siempre puedo volver a la pagina en
											// donde el usuario realizo el logueo
											echo $_SERVER['PHP_SELF'] . '?';
											foreach($_GET as $key => $value){
												echo $key . '=' . $value;
											}
											?>">
											<br />
											<button name="salir" value="TRUE" type="submit" class="btn btn-black">Salir</button>
										</div>
									</div>
								</form>
							</li>
						</ul>
					<?php else:	?>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">Entrar</a>
						<ul class="dropdown-menu" role="menu">
							<li>
								<form method="post" class="form-horizontal" role="form">
									<div class="form-group">
										<div class="col-sm-offset-1 col-sm-10">
											<br />
											<input name="usuario" type="text" class="form-control" id="input_usuario_menu" placeholder="Usuario" required>
											<br />
											<input name="contrasena" type="password" class="form-control" id="input_contrasena_menu" placeholder="Contraseña" required>
											<input type="hidden" name="ruta" value="<?php
											echo $_SERVER['PHP_SELF'] . '?';
											foreach($_GET as $key => $value){
												echo $key . '=' . $value;
											}
											?>">
											<br />
										</div>
										<div class="col-sm-offset-1 col-sm-12">
											<button name="validar" value="TRUE" type="submit" class="btn btn-black">Validar</button>
											<a href="registro.php" class="btn btn-sm" role="button">Alta</a>
										</div>
									</div>
								</form>
							</li>
						</ul>
					<?php endif; ?>
				</li>
				<li><a href="pruebas.php">Pruebas</a></li>
			</ul>
		</div>
		<!-- Navbar Cat collapse Ends -->
	</div>
</nav>
<!-- Main Menu Ends -->
