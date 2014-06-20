<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	pageContext.setAttribute("ctx", request.getContextPath());
%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			主页 <small>个人办公页面</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group">
				<button type="button" class="btn blue dropdown-toggle"
					data-toggle="dropdown" data-hover="dropdown" data-delay="1000"
					data-close-others="true">
					<span>Actions</span> <i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu pull-right" role="menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</li>
			<li><i class="fa fa-home"></i> <a href="index.html">Home</a> <i
				class="fa fa-angle-right"></i></li>
			<li><a href="#">Form Stuff</a> <i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Form Layouts</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-6 col-sm-6">
		<!-- BEGIN PORTLET-->
		<div class="portlet box blue calendar">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-calendar"></i>待办任务
				</div>
			</div>
			<div class="portlet-body light-grey">待办任务</div>
		</div>
		<!-- END PORTLET-->
	</div>

	<div class="col-md-6 col-sm-6">
		<!-- BEGIN PORTLET-->
		<div class="portlet box blue calendar">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-calendar"></i>Calendar
				</div>
			</div>
			<div class="portlet-body light-grey">
				<div id="calendar"></div>

			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>
<script>
	Calendar.init();
</script>