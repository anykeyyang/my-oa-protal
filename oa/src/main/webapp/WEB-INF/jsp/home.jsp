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
	<title>Metronic | Admin Dashboard Template</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<%@include file="/common/head.jsp" %>
	<!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
	<link href="${ctx}/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/>
	<link rel="shortcut icon" href="${ctx}/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->   
	<%@include file="/common/topnavbar.jsp" %>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<%@include file="/common/sidemenu.jsp" %>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Dashboard <small>statistics and more</small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="index.html">Home</a> 
							<i class="fa fa-angle-right"></i>
						</li>
						<li><a href="#">Dashboard</a></li>
						<li class="pull-right">
							<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
								<i class="fa fa-calendar"></i>
								<span></span>
								<i class="fa fa-angle-down"></i>
							</div>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<div class="row ">
				<div class="col-md-6 col-sm-6">
					<!-- BEGIN PORTLET-->
					<div class="portlet box blue calendar">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-calendar"></i>Calendar</div>
						</div>
						<div class="portlet-body light-grey">
							<div id="calendar"></div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
				<div class="col-md-6 col-sm-6">
					<!-- BEGIN PORTLET-->
					<div class="portlet">
						<div class="portlet-title line">
							<div class="caption"><i class="fa fa-comments"></i>Chats</div>
							<div class="tools">
								<a href="" class="collapse"></a>
								<a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="" class="reload"></a>
								<a href="" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body" id="chats">
							<div class="scroller" style="height: 435px;" data-always-visible="1" data-rail-visible1="1">
								<ul class="chats">
									<li class="in">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar1.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Bob Nilson</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="out">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar2.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Lisa Wong</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="in">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar1.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Bob Nilson</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="out">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar3.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Richard Doe</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="in">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar3.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Richard Doe</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="out">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar1.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Bob Nilson</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="in">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar3.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Richard Doe</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, 
											sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
											</span>
										</div>
									</li>
									<li class="out">
										<img class="avatar img-responsive" alt="" src="${ctx}/assets/img/avatar1.jpg" />
										<div class="message">
											<span class="arrow"></span>
											<a href="#" class="name">Bob Nilson</a>
											<span class="datetime">at Jul 25, 2012 11:09</span>
											<span class="body">
											Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. sed diam nonummy nibh euismod tincidunt ut laoreet.
											</span>
										</div>
									</li>
								</ul>
							</div>
							<div class="chat-form">
								<div class="input-cont">   
									<input class="form-control" type="text" placeholder="Type a message here..." />
								</div>
								<div class="btn-cont"> 
									<span class="arrow"></span>
									<a href="" class="btn blue icn-only"><i class="fa fa-check icon-white"></i></a>
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
		</div>
		<!-- END PAGE -->
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
	<script src="assets/plugins/respond.min.js"></script>
	<script src="assets/plugins/excanvas.min.js"></script> 
	<![endif]-->   
	<%@include file="/common/foot_js.jsp" %>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${ctx}/assets/plugins/flot/jquery.flot.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/flot/jquery.flot.resize.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>     
	<script src="${ctx}/assets/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
	<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
	<script src="${ctx}/assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery.sparkline.min.js" type="text/javascript"></script>  
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${ctx}/assets/scripts/app.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/index.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/tasks.js" type="text/javascript"></script>        
	<!-- END PAGE LEVEL SCRIPTS -->  
	<script>
		jQuery(document).ready(function() {    
		   App.init(); // initlayout and core plugins
		   Index.init();
		   Index.initCalendar(); // init index page's custom scripts
		   Index.initCharts(); // init index page's custom scripts
		   Index.initChat();
		   Index.initMiniCharts();
		   Index.initDashboardDaterange();
		   Index.initIntro();
		   Tasks.initDashboardWidget();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>