<%@ page errorPage = "PaginaErrore.jsp" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.*"%>
<%@ page import="bean.*"%>
<%@ page import="controller.*"%>

<jsp:useBean id="ub" scope="session" class="bean.UtenteBean" />
<jsp:useBean id="pb" scope="session" class="bean.ProdottoBean" />
<jsp:useBean id="beanModificaProdotto" scope="session"
	class="bean.ProdottoBean" />
<jsp:setProperty name="pb" property="*" />
<%
	if(!ub.isLog()){	
%> <jsp:forward page="ripeterelogin.jsp"></jsp:forward>
<% }
%>
<%
	String vecchioNome = beanModificaProdotto.getNome();
	//System.out.println("prova request: "+request.getParameter("btnProdottoModificato"));
	if (request.getParameter("btnProdottoModificato") != null) {
		//creo il Bean
		if (request.getParameter("nome") != null && !request.getParameter("nome").equals("")) {
			beanModificaProdotto.setNome(request.getParameter("nome"));
			//System.out.println(request.getParameter("nome"));
		}
		if (request.getParameter("prezzo")!=null && !request.getParameter("prezzo").equals("")) {
			beanModificaProdotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
			//System.out.println(request.getParameter("prezzo"));
		}
		if (request.getParameter("optradioCategoria") == null){
			%><jsp:forward page="noCategoria.jsp"></jsp:forward> <%
		}
		if (request.getParameter("descrizione") != null && !request.getParameter("descrizione").equals("")) {
			beanModificaProdotto.setDescrizione(request.getParameter("descrizione"));
		}
		
// 		if(!request.getParameter("optradioCategoria").isEmpty()){
// 			beanModificaProdotto.setCategorie(request.getParameter("optradioCategoria"));
// 		}
		if (request.getParameter("optradioCategoria").equals("Vestiario") || request.getParameter("optradioCategoria").equals("Libri/Cd") || request.getParameter("optradioCategoria").equals("Casa")) {
			beanModificaProdotto.setCategorie(request.getParameter("optradioCategoria"));
			//System.out.println(request.getParameter("optradio"));
 		}
		beanModificaProdotto.iniziaModificaProdotto(vecchioNome, beanModificaProdotto,ub);
		//pb.stampaBean();
		//pb.inserisciProdotto(ub);
%><jsp:forward page="GestioneProdotto.jsp" />
<%
	/*
%>
<jsp:forward page="index.jsp" />
<%
	*/
	}
%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Ubuy</title>

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- Theme CSS -->
<link href="css/agency.min.css" rel="stylesheet">

<!-- Custom CSS -->
<style>
/* Ugly hacks to fix the container within navbar functionality - should be fixed in Bootstrap 4 beta */
.navbar-toggler {
	z-index: 1;
}

@media ( max-width : 576px) {
	nav>.container {
		width: 100%;
	}
}
</style>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


	<!-- Navigation -->
	<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse"
		id="mainNav">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<div class="container">
			<a class="navbar-brand page-scroll" href="#page-top">Ubuy</a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link page-scroll"
						href="inserisciProd.jsp">Inserisci Prodotto</a></li>
					<li class="nav-item"><a class="nav-link page-scroll"
						href="ModificaProdotto.jsp">Modifica Prodotto</a></li>
					<li class="nav-item"><a class="nav-link page-scroll"
						href="EliminaProdotto.jsp">Elimina Prodotto</a></li>
				</ul>
			</div>
		</div>
	</nav>






	<!-- Contact Section -->
	<section id="contact"
		style="background: url(img/header-bg.jpg) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">

		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Modifica il prodotto</h2>
					<h3 class="section-subheading text-muted">
						Inserisci qui le modifiche per il prodotto:
						<%=beanModificaProdotto.getNome()%></h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form action="" method="POST">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="nome" style="color: white">Nome Prodotto:</label>
									<h3 class="section-subheading text-muted">
										<p style="color: White"><%=beanModificaProdotto.getNome()%></p>
									</h3>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label for="prezzo" style="color: white">Prezzo
										Prodotto:</label>
									<h3 class="section-subheading text-muted">
										<p style="color: White"><%=beanModificaProdotto.getPrezzo()%>
											Euro
										</p>
									</h3>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label for="descrizione" style="color: white">Descrizione
										Prodotto:</label>
									<h3 class="section-subheading text-muted">
										<p style="color: White"><%=beanModificaProdotto.getDescrizione()%></p>
									</h3>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<h3 class="section-subheading text-muted">
										<p style="color: White">
											Categoria:
											<%=beanModificaProdotto.getCategorie()%></p>
									</h3>
								</div>


							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="nome" style="color: white">Nuovo Nome:</label> <input
										type="text" class="form-control"
										placeholder="Inserisci il nome del prodotto" name="nome"
										id="nome" >
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label for="prezzo" style="color: white">Nuovo Prezzo:</label>
									<input type="number" min="0.00" step="0.01"
										class="form-control"
										placeholder="Inserisci il prezzo del prodotto" name="prezzo"
										id="prezzo" >
									<p class="help-block text-danger"></p>
								</div>
								<div>
									<label for="descrizione" style="color: white">Descrizione
										Prodotto:</label>
									<textarea class="form-control" name="descrizione"
										id="descrizione"
										placeholder="Inserisci qui la descrizione del prodotto"
										id="message" ></textarea>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group" align="center">
									<h1>...</h1>
									<label class="radio-inline" style="color: white" required
										data-validation-required-message="Perfavore inserisci il prezzo del prodotto"><input
										type="radio" name="optradioCategoria" value="Vestiario">
										<p style="color: white">Vestiario</p></label> <label
										class="radio-inline"><input type="radio"
										name="optradioCategoria" value="Casa">
										<p style="color: white">Casa</p></label> <label class="radio-inline"><input
										type="radio" name="optradioCategoria" value="Libri/Cd" >
										<p style="color: white">Libri/Cd</p></label>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="col-lg-12 text-center">
							<div id="success"></div>
							<button type="submit" class="btn btn-xl"
								name="btnProdottoModificato">Modifica prodotto</button>

						</div>
					</form>
				</div>

			</div>
		</div>

	</section>



	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">Copyright &copy; Your Website 2016</span>
				</div>

				<div class="col-md-4" align="right">
					<ul class="list-inline quicklinks">
						<li class="list-inline-item"><a href="#">Privacy Policy</a></li>
						<li class="list-inline-item"><a href="#">Terms of Use</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<!-- Portfolio Modals -->
	<!-- Use the modals below to showcase details about your portfolio projects! -->

	<!-- Portfolio Modal 1 -->
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<!-- Project Details Go Here -->
								<h2>Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/roundicons-free.png" alt="">
								<p>Use this area to describe your project. Lorem ipsum dolor
									sit amet, consectetur adipisicing elit. Est blanditiis dolorem
									culpa incidunt minus dignissimos deserunt repellat aperiam
									quasi sunt officia expedita beatae cupiditate, maiores
									repudiandae, nostrum, reiciendis facere nemo!</p>
								<p>
									<strong>Want these icons in this portfolio item
										sample?</strong>You can download 60 of them for free, courtesy of <a
										href="https://getdpd.com/cart/hoplink/18076?referrer=bvbo4kax5k8ogc">RoundIcons.com</a>,
									or you can purchase the 1500 icon set <a
										href="https://getdpd.com/cart/hoplink/18076?referrer=bvbo4kax5k8ogc">here</a>.
								</p>
								<ul class="list-inline">
									<li>Date: July 2014</li>
									<li>Client: Round Icons</li>
									<li>Category: Graphic Design</li>
								</ul>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Portfolio Modal 2 -->
	<div class="portfolio-modal modal fade" id="portfolioModal2"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<h2>Project Heading</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/startup-framework-preview.png" alt="">
								<p>
									<a href="http://designmodo.com/startup/?u=787">Startup
										Framework</a> is a website builder for professionals. Startup
									Framework contains components and complex blocks (PSD+HTML
									Bootstrap themes and templates) which can easily be integrated
									into almost any design. All of these components are made in the
									same style, and can easily be integrated into projects,
									allowing you to create hundreds of solutions for your future
									projects.
								</p>
								<p>
									You can preview Startup Framework <a
										href="http://designmodo.com/startup/?u=787">here</a>.
								</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Portfolio Modal 3 -->
	<div class="portfolio-modal modal fade" id="portfolioModal3"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<!-- Project Details Go Here -->
								<h2>Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/treehouse-preview.png" alt="">
								<p>
									Treehouse is a free PSD web template built by <a
										href="https://www.behance.net/MathavanJaya">Mathavan Jaya</a>.
									This is bright and spacious design perfect for people or
									startup companies looking to showcase their apps or other
									projects.
								</p>
								<p>
									You can download the PSD template in this portfolio sample item
									at <a
										href="http://freebiesxpress.com/gallery/treehouse-free-psd-web-template/">FreebiesXpress.com</a>.
								</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Portfolio Modal 4 -->
	<div class="portfolio-modal modal fade" id="portfolioModal4"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<!-- Project Details Go Here -->
								<h2>Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/golden-preview.png" alt="">
								<p>
									Start Bootstrap's Agency theme is based on Golden, a free PSD
									website template built by <a
										href="https://www.behance.net/MathavanJaya">Mathavan Jaya</a>.
									Golden is a modern and clean one page web template that was
									made exclusively for Best PSD Freebies. This template has a
									great portfolio, timeline, and meet your team sections that can
									be easily modified to fit your needs.
								</p>
								<p>
									You can download the PSD template in this portfolio sample item
									at <a
										href="http://freebiesxpress.com/gallery/golden-free-one-page-web-template/">FreebiesXpress.com</a>.
								</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Portfolio Modal 5 -->
	<div class="portfolio-modal modal fade" id="portfolioModal5"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<!-- Project Details Go Here -->
								<h2>Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/escape-preview.png" alt="">
								<p>
									Escape is a free PSD web template built by <a
										href="https://www.behance.net/MathavanJaya">Mathavan Jaya</a>.
									Escape is a one page web template that was designed with
									agencies in mind. This template is ideal for those looking for
									a simple one page solution to describe your business and offer
									your services.
								</p>
								<p>
									You can download the PSD template in this portfolio sample item
									at <a
										href="http://freebiesxpress.com/gallery/escape-one-page-psd-web-template/">FreebiesXpress.com</a>.
								</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Portfolio Modal 6 -->
	<div class="portfolio-modal modal fade" id="portfolioModal6"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 offset-lg-2">
							<div class="modal-body">
								<!-- Project Details Go Here -->
								<h2>Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid img-centered"
									src="img/portfolio/dreams-preview.png" alt="">
								<p>
									Dreams is a free PSD web template built by <a
										href="https://www.behance.net/MathavanJaya">Mathavan Jaya</a>.
									Dreams is a modern one page web template designed for almost
									any purpose. Itâs a beautiful template thatâs designed with
									the Bootstrap framework in mind.
								</p>
								<p>
									You can download the PSD template in this portfolio sample item
									at <a
										href="http://freebiesxpress.com/gallery/dreams-free-one-page-web-template/">FreebiesXpress.com</a>.
								</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>

	<!-- Tether -->
	<script src="vendor/tether/tether.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/agency.min.js"></script>

</body>

</html>

