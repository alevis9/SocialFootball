<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.*"%>
<%@page import="service.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>SocialFootball - Profile</title>
<meta name="author" content="">
<meta name="description" content="155 characters">
<meta name="keywords" content="web,design,html,css,html5,development">
<link rel="publisher"
	href="https://plus.google.com/[YOUR BUSINESS G+ PROFILE HERE]" />
<link rel="icon" href="/favicon.ico">
<!--twitter-->
<meta name="twitter:card" content="summary">
<!--summary, photo or player-->
<meta name="twitter:url" content="http://">
<meta name="twitter:title" content="title">
<meta name="twitter:description" content="desc">
<meta name="twitter:image" content="http://">
<!--should be a square image no smaller than 60x60 pixels.-->
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<nav class="navbar navbar-default"
		style="border-radius: 0px; background: rgb(0, 102, 153); border: 0px; margin-bottom: 0px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="HomePage.jsp" style="color: white;">SocialFootball</a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<%
						Persona profilePersona = (Persona) request.getAttribute("ProfilePersona");
						Persona pe = (Persona) session.getAttribute("CurrentPersona");
						if (pe instanceof Giocatore)
							out.print("<li><a style=\"color: white;\" href=\"ListaGiocatoriPage.jsp\"> <span class=\"glyphicon glyphicon-globe\"></span> Lista Persone</a></li>");
					%>
					<li><a style="color: white;" href="ListaPresidentiPage.jsp">
							<span class="glyphicon glyphicon-globe"> </span> Lista Presidenti
					</a></li>
					<%
						if (pe.equals(profilePersona))
								out.print("<li><a style=\"color: white;\" href=\"ModificaPersonaPage.jsp\"><span class=\"glyphicon glyphicon-cog\"> </span> Settings</a></li>");
					%>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid"
		style="height: 350px; background: rgb(0, 82, 133); color: white;">
		<p style="text-align: center; font-size: 140%; margin-top: 15px;">
			<%
				if (profilePersona instanceof Presidente)
				 	out.print(((Presidente) profilePersona).getMotto());
			%>
		</p>
		<div style="margin-top: 15px;">
			<img src="${ProfilePersona.avatar}" class="img-circle img-responsive"
				alt="${ProfilePersona.nome} avatar"
				style="width: 220px; height: 220px; margin-left: auto; margin-right: auto;" />
			<h3 style="text-align: center;">${ProfilePersona.nome} 
			<%
			if(!pe.equals(profilePersona))
			{
			if (profilePersona instanceof Giocatore && pe instanceof Giocatore &&
					!(((Giocatore)pe).getAmici().contains(profilePersona)))
				out.print("<a href=\"FollowGiocatoreController?giocatoreDaSeguire="+profilePersona.getEmail()+
						  "\"><span class=\"glyphicon glyphicon-heart-empty\"/></a>");
			else if (profilePersona instanceof Presidente && !(pe.getPresidenteFollowing().contains(profilePersona)))
				out.print("<a href=\"FollowPresidenteController?presidenteDaSeguire="+profilePersona.getEmail()+
						  "\"><span class=\"glyphicon glyphicon-heart-empty\"/></a>");
			}
		%></h3>
			<br>
		</div>

	</div>
	<div
		style="background: rgb(229, 229, 229); margin-left: 0px; margin-bottom: 0px; margin-right: 0px; color: black; padding-left: 15px; padding-right: 15px; padding-top: 10px">
		<p>
			<%
				if (profilePersona instanceof Presidente)
					out.print(((Presidente) profilePersona).getDescrizione());
				else
					out.print(((Giocatore) profilePersona).getBiografia());
			%>
		</p>
		

		<br>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-dm-6 col-dm-offset-2" style="color: black;">
				<%
					for(Partita p : profilePersona.getPartite())
					{
						out.print("<div style=\"margin-bottom: 5px;\"> <h3>"+p.getTitolo()+
						"</h3> <p style=\"margin-left: 15px;\">"+
						p.getTesto()+"</p></div>");
					}
				%>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--[if lt IE 10]>
      <script src="js/ie10-viewport-bug-workaround.js"></script>
    <![endif]-->
</body>
</html>