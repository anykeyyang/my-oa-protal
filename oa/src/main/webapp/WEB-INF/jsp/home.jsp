<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %> 
<%pageContext.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.2
Version: 1.5.4
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>OA</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<!-- BEGIN GLOBAL MANDATORY STYLES -->          
	<link href="${ctx}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/plugins/select2/select2_metro.css" />
	<link href="${ctx}/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" type="text/css" rel="stylesheet" />
	<!-- END PAGE LEVEL STYLES -->
	<!-- BEGIN THEME STYLES --> 
	<link href="${ctx}/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${ctx}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet"/>
	
	<link href="${ctx}/assets/css/custom.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="${ctx}/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed">
	<!-- BEGIN HEADER -->   
	<%@include file="/common/topnavbar.jsp" %>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->               
		<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">
						Widget settings form goes here
					</div>
					<div class="modal-footer">
						<button type="button" class="btn blue">Save changes</button>
						<button type="button" class="btn default" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
		<!-- BEGIN SIDEBAR1 -->
		<%@include file="/common/sidemenu.jsp" %>
		<!-- END SIDEBAR1 -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler"></div>
				<div class="toggler-close"></div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>THEME COLOR</span>
						<ul>
							<li class="color-black current color-default" data-style="default"></li>
							<li class="color-blue" data-style="blue"></li>
							<li class="color-brown" data-style="brown"></li>
							<li class="color-purple" data-style="purple"></li>
							<li class="color-grey" data-style="grey"></li>
							<li class="color-white color-light" data-style="light"></li>
						</ul>
					</div>
					<div class="theme-option">
						<span>Layout</span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Header</span>
						<select class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Sidebar</span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Footer</span>
						<select class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END BEGIN STYLE CUSTOMIZER --> 
			<div class="page-content-body">
				<!-- HERE WILL BE LOADED AN AJAX CONTENT -->
			</div>
		</div>
		<!-- BEGIN PAGE -->     
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">
			2013 &copy; Metronic by keenthemes.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="fa fa-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->   
	<!--[if lt IE 9]>
	<script src="${ctx}/assets/plugins/respond.min.js"></script>
	<script src="${ctx}/assets/plugins/excanvas.min.js"></script> 
	<![endif]-->   
	<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${ctx}/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>      
	<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<script src="${ctx}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${ctx}/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<script type="text/javascript" src="${ctx}/assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/assets/plugins/data-tables/DT_bootstrap.js"></script>
	<script type="text/javascript" src="${ctx}/assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js"></script>
	 
	<script  type="text/javascript" src="${ctx}/assets/scripts/table-editable.js"></script>    
	<script  type="text/javascript" src="${ctx}/assets/scripts/app.js"></script> 
	<script  type="text/javascript" src="${ctx}/assets/scripts/table-editable.js"></script>    
	<script  type="text/javascript" src="${ctx}/assets/scripts/table-managed.js"></script>  
	<script  type="text/javascript" src="${ctx}/assets/scripts/calendar.js"></script>    
	<script>
		jQuery(document).ready(function() {    
		   App.init();
		   $('.page-sidebar .ajaxify.start').click(); // load the content for the dashboard page.
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>