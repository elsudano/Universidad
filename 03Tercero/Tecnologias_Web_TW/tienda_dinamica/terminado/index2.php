<!doctype html>
<html lang="es">
	<head>

		<meta charset="utf-8">
		<!--[if IE]>
			<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<![endif]-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="This is a website, for resolve, the exercise course of Web Technologies">
		<meta name="author" content="Carlos de la Torre">
		
		<title>A Tiny Web Shop</title>
		
		<!-- Bootstrap Core CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Google Web Fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto+Condensed:300italic,400italic,700italic,400,300,700" rel="stylesheet" type="text/css">
		<link href="http://fonts.googleapis.com/css?family=Oswald:400,700,300" rel="stylesheet" type="text/css">
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,700,300,600,800,400" rel="stylesheet" type="text/css">
		
		<!-- CSS Files -->
		<link href="css/font-awesome.min.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/responsive.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="js/ie8-responsive-file-warning.js"></script>
		<![endif]-->
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
		
		<!-- Fav and touch icons -->
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/fav-144.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/fav-114.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/fav-72.png">
		<link rel="apple-touch-icon-precomposed" href="images/fav-57.png">
		<link rel="shortcut icon" href="images/fav.png">
		
	</head>
	<body>
		<!-- Tablón principal de la pagina Starts-->
			<div id="tablon" class="col-md-8 col-md-offset-2">
				<!-- Columna izquierda publicidad Starts-->
					<div class="col-md-2 col3-banners">
						<a href="http://www.axe.es" target="_blank"><img src="resources/vBanner1.png" title="Tu Nueva Fragancia" alt="AXE Banner" class="img-responsive" /></a>
						<a href="http://es.wikipedia.org/wiki/Caf&eacute;_de_Colombia" target="_blank"><img src="resources/vBanner3.png" title="Cafes de Colombia" alt="Caf&eacute; Banner" class="img-responsive" /></a>
					</div>
				<!-- Columna izquierda publicidad Ends-->
				<!-- Columna central contenido Starts-->
					<div class="col-md-8">
						<!-- Fila superior cabecera Starts-->
							<div class="row">
								<!-- Header Section Starts -->
									<header id="header-area">
										<!-- Banner Superior Starts -->
											<div id="banner-top">
												<a href="http://www.avg.com/" target="_blank"><img src="resources/banner1.png" title="AVG Banner" alt="Banner de AVG-Antivirus" class="img-responsive" /></a>
											</div>
										<!-- Banner Superior Ends -->
										<!-- Logos Starts -->
											<div class="row">
												<div class="col-md-6 col-md-offset-1">
													<div id="logo">
														<a href="index.php" target="_parent"><img src="resources/logo.png" title="Mi Logo" alt="Tecno Store" class="img-responsive" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div id="social-icons">
														<a href="http://www.twitter.com" target="_blank"><img src="resources/social-icons.png" title="Social" alt="Social Icons" class="img-responsive" /></a>
													</div>
												</div>
											</div>
										<!-- Logos Starts -->
										<!-- Main Menu Starts -->
											<nav id="main-menu" class="navbar" role="navigation">
												<div class="container">
												<!-- Nav Header Starts -->
													<div class="navbar-header">
														<button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-cat-collapse">
															<span class="sr-only">Toggle Navigation</span>
															<i class="fa fa-bars"></i>
														</button>
													</div>
												<!-- Nav Header Ends -->
												<!-- Navbar Cat collapse Starts -->
													<div class="collapse navbar-collapse navbar-cat-collapse">
														<ul class="nav navbar-nav">
															<li><a href="category-list.html">Digital Cameras</a></li>
															<li class="dropdown">
																<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">
																	Sound Devices
																</a>
																<ul class="dropdown-menu" role="menu">
																	<li><a tabindex="-1" href="#">Music System</a></li>
																	<li><a tabindex="-1" href="#">DTS</a></li>
																	<li><a tabindex="-1" href="#">Home Theater</a></li> 
																</ul>
															</li>
															<li class="dropdown">
																<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">Televisions </a>
																<div class="dropdown-menu">
																	<div class="dropdown-inner">
																		<ul class="list-unstyled">
																			<li class="dropdown-header">Sub Category</li>
																			<li><a tabindex="-1" href="#">item 1</a></li>
																			<li><a tabindex="-1" href="#">item 2</a></li>
																			<li><a tabindex="-1" href="#">item 3</a></li>
																		</ul>										
																		<ul class="list-unstyled">
																			<li class="dropdown-header">Sub Category</li>
																			<li><a tabindex="-1" href="#">item 1</a></li>
																			<li><a tabindex="-1" href="#">item 2</a></li>
																			<li><a tabindex="-1" href="#">item 3</a></li>
																		</ul>
																		<ul class="list-unstyled">
																			<li class="dropdown-header">Sub Category</li>
																			<li><a tabindex="-1" href="#">item 1</a></li>
																			<li><a tabindex="-1" href="#">item 2</a></li>
																			<li><a tabindex="-1" href="#">item 3</a></li>
																		</ul>
																	</div>
																</div>
															</li>
															<li><a href="category-list.html">Computers </a></li>
															<li class="dropdown">
																<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">
																	Pages
																</a>
																<ul class="dropdown-menu" role="menu">
																	<li><a tabindex="-1" href="index.html">Home</a></li>
																	<li><a tabindex="-1" href="about.html">About</a></li>
																	<li><a tabindex="-1" href="category-list.html">Category List</a></li>
																	<li><a tabindex="-1" href="category-grid.html">Category Grid</a></li>
																	<li><a tabindex="-1" href="product.html">Product</a></li>
																	<li><a tabindex="-1" href="product-full.html">Product Full Width</a></li>
																	<li><a tabindex="-1" href="cart.html">Cart</a></li>
																	<li><a tabindex="-1" href="login.html">Login</a></li>
																	<li><a tabindex="-1" href="compare.html">Compare Products</a></li>
																	<li><a tabindex="-1" href="typography.html">Typography</a></li>
																	<li><a tabindex="-1" href="register.html">Register</a></li>
																	<li><a tabindex="-1" href="contact.html">Contact</a></li>
																	<li><a tabindex="-1" href="404.html">404</a></li>
																</ul>
															</li>
														</ul>
													</div>
												<!-- Navbar Cat collapse Ends -->
												</div>
											</nav>
										<!-- Main Menu Ends -->
									</header>
								<!-- Header Section Starts -->
							</div>
						<!-- Fila superior cabecera Ends-->
						<!-- Fila central contenido Starts-->
							<div class="row">
								<!-- Main Container Starts -->
									<div id="main-container">
										<!-- Product Grid Display Starts -->
											<div class="row">
											<!-- Product #1 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/15.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #1 Ends -->
											<!-- Product #2 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/14.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #2 Ends -->
											<!-- Product #3 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/5.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #3 Ends -->
											<!-- Product #4 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/6.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #4 Ends -->
											<!-- Product #5 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/7.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #5 Ends -->
											<!-- Product #6 Starts -->
												<div class="col-md-4 col-sm-6">
													<div class="product-col">
														<div class="image">
															<img src="images/product-images/8.jpg" alt="product" class="img-responsive" />
														</div>
														<div class="caption">
															<h4><a href="product.html">Digital Electro Goods</a></h4>
															<div class="description">
																We are so lucky living in such a wonderful time. Our almost unlimited ...
															</div>
															<div class="price">
																<span class="price-new">$199.50</span> 
																<span class="price-old">$249.50</span>
															</div>
															<div class="cart-button button-group">
																<button type="button" title="Wishlist" class="btn btn-wishlist">
																	<i class="fa fa-heart"></i>
																</button>
																<button type="button" title="Compare" class="btn btn-compare">
																	<i class="fa fa-bar-chart-o"></i>
																</button>
																<button type="button" class="btn btn-cart">
																	Add to cart
																	<i class="fa fa-shopping-cart"></i> 
																</button>									
															</div>
														</div>
													</div>
												</div>
											<!-- Product #6 Ends -->
											</div>
										<!-- Product Grid Display Ends -->
									</div>
								<!-- Main Container Ends -->
							</div>
						<!-- Fila central contenido Ends-->
						<!-- Fila inferior pie Starts-->
							<div class="row">
								<!-- Footer Section Starts -->
									<footer id="footer-area">
										<!-- Footer Links Starts -->
											<div class="footer-links row">
												<!-- Information Links Starts -->
													<div class="col-md-2 col-sm-6">
														<h5>Information</h5>
														<ul>
															<li><a href="about.html">About Us</a></li>
															<li><a href="#">Delivery Information</a></li>
															<li><a href="#">Privacy Policy</a></li>
															<li><a href="#">Terms &amp; Conditions</a></li>
														</ul>
													</div>
												<!-- Information Links Ends -->
												<!-- My Account Links Starts -->
													<div class="col-md-2 col-sm-6">
														<h5>My Account</h5>
														<ul>
															<li><a href="#">My orders</a></li>
															<li><a href="#">My merchandise returns</a></li>
															<li><a href="#">My credit slips</a></li>
															<li><a href="#">My addresses</a></li>
															<li><a href="#">My personal info</a></li>
														</ul>
													</div>
												<!-- My Account Links Ends -->					
												<!-- Customer Service Links Starts -->
													<div class="col-md-2 col-sm-6">
														<h5>Service</h5>
														<ul>
															<li><a href="contact.html">Contact Us</a></li>
															<li><a href="#">Returns</a></li>
															<li><a href="#">Site Map</a></li>
															<li><a href="#">Affiliates</a></li>
															<li><a href="#">Specials</a></li>
														</ul>
													</div>
												<!-- Customer Service Links Ends -->
												<!-- Follow Us Links Starts -->
													<div class="col-md-2 col-sm-6">
														<h5>Follow Us</h5>
														<ul>
															<li><a href="#">Facebook</a></li>
															<li><a href="#">Twitter</a></li>
															<li><a href="#">RSS</a></li>
															<li><a href="#">YouTube</a></li>
														</ul>
													</div>
												<!-- Follow Us Links Ends -->
												<!-- Contact Us Starts -->
													<div class="col-md-4 col-sm-6 last">
														<h5>Contact Us</h5>
														<ul>
															<li>My Company</li>
															<li>
																1247 LB Nagar Road, Hyderabad, Telangana - 35
															</li>
															<li>
																Email: <a href="#">info@demolink.com</a>
															</li>								
														</ul>
														<h4 class="lead">
															Tel: <span>1(234) 567-9842</span>
														</h4>
													</div>
												<!-- Contact Us Ends -->
											</div>
										<!-- Footer Links Ends -->
										<!-- Copyright Area Starts -->
											<div class="copyright row">
												<!-- Starts -->
													<p class="pull-left">&nbsp;&copy; 2015 Designed By <a href="https://www.sudano.net">Carlos de la Torre</a></p>
												<!-- Ends -->
												<!-- Payment Gateway Links Starts -->
													<ul class="list-inline">
														<li>
															<img src="resources/w3c-css.gif" alt="Valida CSS" />
														</li>
														<li>
															<img src="resources/w3c-html.png" alt="Valida HTML5" />
														</li>
													</ul>
												<!-- Payment Gateway Links Ends -->
											</div>
										<!-- Copyright Area Ends -->
									</footer>
								<!-- Footer Section Ends -->
							</div>
						<!-- Fila inferior pie Ends-->
					</div>
				<!-- Columna central contenido Ends-->
				<!-- Columna derecha publicidad Starts-->
					<div class="col-md-2 col3-banners">
						<a href="http://www.alienware.es/" target="_blank"><img src="resources/vBanner2.png" title="Ordenadores AlienWare" alt="Ordenadores AlienWare" class="img-responsive" /></a>
						<a href="http://www.soundcloud.com" target="_blank"><img src="resources/vBanner4.png" title="Música OnLine" alt="SoundCloud" class="img-responsive" /></a>
					</div>
				<!-- Columna derecha publicidad Ends-->
			</div>
		<!-- Tablón principal de la pagina Starts-->
		<!-- JavaScript Files -->
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/jquery-migrate-1.2.1.min.js"></script>	
		<script src="js/bootstrap.min.js"></script>
		<script src="js/bootstrap-hover-dropdown.min.js"></script>
		<script src="js/jquery.magnific-popup.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>
		<script src="js/custom.js"></script>
	</body>
</html>