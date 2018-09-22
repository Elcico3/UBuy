<%@ page import="java.io.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.*"%>
<%@ page import="bean.*"%>
<%@ page import="controller.*"%>
<%@ page isErrorPage="true"%>
<%
	if (request.getParameter("btnBackHome") != null) {
%>
	<jsp:forward page="index.jsp"></jsp:forward>
<%
	}
%>




<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<jsp:useBean id="ub" scope="session" class="bean.UtenteBean" />
<jsp:useBean id="pb" scope="session" class="bean.ProdottoBean" />
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

<body id="page-top">
<body
	style="background: url(img/header-bg.jpg) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">

	<div class="col-lg-12 text-center">
		<div class="intro-lead-in">
			<p style="color: white">Errore</p>
		</div>
		<div class="intro-heading">
			<p style="color: white">
				Siamo spiacenti, si è verificato un errore durante l'esecuzione:<br />
				<br />
				<%=exception.getMessage()%></p>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="col-lg-12 text-center">
		<div id="success"></div>
		<form>
		<button type="submit" class="btn btn-xl" name="btnBackHome">Torna
			alla home</button>
	</form>
	</div>