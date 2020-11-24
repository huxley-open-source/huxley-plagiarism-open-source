<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Plagiarism</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.form.min.js" />"></script>
<decorator:head />
</head>
<body>
	<security:authentication property="principal" var="user" />
	<div class="container">
		<div class="page-header">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
			    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar-collapse-1">
			      		<span class="sr-only">Toggle navigation</span>
			      		<span class="icon-bar"></span>
			      		<span class="icon-bar"></span>
			      		<span class="icon-bar"></span>
			    	</button>
			    	<a class="navbar-brand" href="<c:url value="/" />">Plagiarism Detection</a>
			  	</div>			
			  	<div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
			    	<ul class="nav navbar-nav navbar-right">
			    		<li><a href="<c:url value="/logout" />">Logout</a></li>
			  		</ul>			    
			  	</div>
			</nav>			
		</div>		
		<decorator:body />
	</div>
</body>
</html>