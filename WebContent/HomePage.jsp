<%@page import="model.*"%>
<%@page import="javax.ejb.EJB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>SocialFootball - HomePage</title>
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
<!--should be a square image no smaller than 60×60 pixels.-->
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
<body style="background-color:#edecec">
	<nav class="navbar navbar-default"
		style="border-radius: 0px; background: rgb(0, 102, 153); border: 0px; margin-bottom: 0px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="HomePage.jsp" style="color: white;">SocialStartup</a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<%
					Persona pe = (Persona) session.getAttribute("CurrentPersona");
						if (pe instanceof Giocatore)
							out.print("<li><a style=\"color: white;\" href=\"ListaGiocatoriPage.jsp\"> <span class=\"glyphicon glyphicon-user\"></span> Lista Utenti</a></li>");
					%>
					<li><a style="color: white;" href="ListaPresidentiPage.jsp">
							<span class="glyphicon glyphicon-globe"> </span> Lista Presidenti
					</a></li>

					<li><a style="color: white;"
						href="ProfilePageController?emailProfile=${CurrentPersona.email}">
							<img src="${CurrentPersona.avatar}"
							style="width: 30px; height: 30px;" /> ${CurrentPersona.nome}
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2"
				style="background-color: white; color: #262626; margin-bottom:0px">
				<br> <a
					href="ProfilePageController?emailProfile=${CurrentUser.email}">
					<img src="${CurrentPersona.avatar}" class="img-circle img-responsive"
					alt="${CurrentPersona.nome} avatar" style="width: 200px; height:200px; ">
				</a><br>
				<h3>${CurrentPersona.nome}</h3>
				<p>
				<%					
					if (pe instanceof Presidente) {
						Presidente pr = (Presidente) pe;
						out.print("<p style=\"padding:5px; background-color:#edecec \">"+pr.getDescrizioneBreve()+"</p>");
					}
				%>
				</p>
				<p style="padding:5px; background-color:#edecec ">
				<a href="${CurrentPersona.facebook}">facebook</a><br>
				</p>
				
				<p  style="padding:5px; background-color:#edecec ">
				<a href="${CurrentPersona.twitter}">twitter</a><br>
				</p>
	
				<p style="padding:5px; background-color:#edecec ">
				<a href="${CurrentPersona.linkedIn}">linkedIn</a><br>
				</p>
				
				<a class="img-rounded" href="NewPartita.jsp"
					style="background-color: white;"> <span
					class="glyphicon glyphicon-plus" style="color: rgb(0, 102, 153)"></span>
				</a>
				<br>
				<!-- Lista ultime 5 Partite -->
				<div style="margin-left: 15px;">
					<c:forEach var="partita" begin="0" end="4"
						items="${CurrentPersona.partita}">
						<div style="margin-bottom: 5px;">
							<p style="font-size: 60%">${partita.titolo}</p>
							<p style="font-size: 40%; margin-left: 7px;">${partita.testo}</p>
						</div>
					</c:forEach>
				</div>
				<br><br>
			</div>

			<div class="col-sm-10" >
				<div style="margin-left: 10px;">
					<c:forEach var="attivita" begin="0" end="99"
						items="${Societa.partita}">
						<div style="background-color: white; color: #262626; margin-bottom: 10px">
							<h3>
								<a
									href="ProfilePageController?emailProfile=${partita.persona.email}">
									${partita.persona.nome}</a> ${partita.titolo}
							</h3>
							<p style="margin-left: 15px;">${partita.testo}</p>
							<Table>
							<tr>
							<td style="width:10px"></td>
							<td style="width:30px"><a href="${partita.url}"><span class="glyphicon glyphicon-link"> </span></a> </td>
							<td style="width:30px"><a href="${partita.urlImg}"><span class="glyphicon glyphicon-picture"> </span></a> </td>
							<td style="width:30px"><a href="${partita.urlVideo}"><span class="glyphicon glyphicon-facetime-video"> </span></a> </td>
							</tr>
							</Table>
						</div>
					</c:forEach>
				</div>
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