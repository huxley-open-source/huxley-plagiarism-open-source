<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.form.min.js" />"></script>
</head>
<body>
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
			  		</ul>			    
			  	</div>
			</nav>			
		</div>
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<div class="alert alert-danger">ERROR: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
		</c:if>
		<p>To obtain an account, send a mail message to diogo@4techlabs.com.br.</p>
		<form role="form" method="post" class="form-horizontal" action="<spring:url value="/login" />">
			<div class="form-group">
				<label for="file" class="col-sm-2 control-label">Username:</label>
				<div class="col-sm-2">
					<input class="form-control" name="username" type="text" />
				</div>
			</div>
			<div class="form-group">
				<label for="file" class="col-sm-2 control-label">Password:</label>
				<div class="col-sm-2">
					<input class="form-control" name="password" type="password" />
				</div>
			</div>
			<div class="form-group">
	    		<div class="col-sm-offset-2 col-sm-10">
	      			<input class="btn btn-primary" type="submit" />
	      		</div>
			</div>
		</form>
	</div>
</body>
</html>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-61468887-1', 'auto');
  ga('send', 'pageview');

</script>
