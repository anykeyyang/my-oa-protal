<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="ico/favicon.ico">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

</head>

<body>

	<div class="container">

		<form class="form-signin" role="form" action="<%=basePath%>web/user.action">
			<h2 class="form-signin-heading">Please sign in</h2>
			<input type="email" name="email" class="form-control" placeholder="Email address" 
				required autofocus> <input type="password" name="password"
				class="form-control" placeholder="Password" required> <label
				class="checkbox"> <input type="checkbox" value="remember-me">
				Remember me
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>
	<script src="js/jquery/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>