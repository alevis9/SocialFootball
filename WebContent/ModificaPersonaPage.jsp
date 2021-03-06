<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>SocialFootball - Modifica Profilo</title>
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
		style="border-radius: 0px; background: rgb(0, 102, 153); border: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="HomePage.jsp" style="color: white;">SocialStartup</a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<%
						Persona pe = (Persona) session.getAttribute("CurrentPersona");
						if (pe instanceof Giocatore)
							out.print("<li><a style=\"color: white;\" href=\"ListaGiocatoriPage.jsp\"> <span class=\"glyphicon glyphicon-globe\"></span> Lista Persone</a></li>");
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

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-1">
				<form action="ModificaUtenteController" method="get">
					<table>
						<%
							if (pe instanceof Presidente) {
								Presidente pr = (Presidente) pe;
								out.print("<tr><td>Descrizione breve:</td><td colspan=\"3\"><input row=\"2\" type=\"text\" name=\"descBreve\" value=\""
										+ pr.getDescrizioneBreve() + "\"></input></td></tr>");
								out.print("<tr><td>Descrizione:</td><td colspan=\"3\"><input row=\"5\" type=\"text\" name=\"desc\" value=\""
										+ pr.getDescrizione() + "\"></input></td></tr>");
								out.print("<tr><td>Motto:</td><td><input type=\"text\" name=\"motto\" value=\""
										+ pr.getMotto() + "\"></td></tr>");
							} else {
								Giocatore g = (Giocatore) pe;
								out.print("<tr><td>Biografia:</td><td><input type=\"text\" name=\"biografia\" value=\""
										+ g.getBiografia() + "\"></input></td></tr>");
								out.print("<tr><td>Anno di nascita:</td><td><input type=\"text\" name=\"annoNascita\" value=\""
										+ g.getAnnoDiNascita() + "\"></input></td></tr>");
							}
						%>

						<tr>
							<td>Url Avatar:</td>
							<td><input type="text" name="urlImg"
							value="${CurrentPersona.avatar}"/></td>
						</tr>

						<tr>
							<td>Sito Web:</td>
							<td><input type="text" name="sito"
							value="${CurrentPersona.sitoWeb}"/></td>
						</tr>
						<tr>
							<td>Facebook:</td>
							<td><input type="text" name="facebook"
							value="${CurrentPersona.facebook}"/></td>
						</tr>
						<tr>
							<td>Twitter:</td>
							<td><input type="text" name="twitter"
								value="${CurrentPersona.twitter}" /></td>
						</tr>
						<tr>
							<td>Linkedin:</td>
							<td><input type="text" name="linkedIn"
							value="${CurrentPersona.linkedIn}" /></td>
						</tr>
					</table>
					<br> <input type="submit" name="sumbit" value="salva" />
				</form>
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