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
			Form Layouts <small>form layouts</small>
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
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>Form Sample
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a> <a
						href="#portlet-config" data-toggle="modal" class="config"></a> <a
						href="javascript:;" class="reload"></a> <a href="javascript:;"
						class="remove"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form action="#" class="horizontal-form">
					<div class="form-body">
						<h3 class="form-section">Person Info</h3>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">First Name</label> <input
										type="text" id="firstName" class="form-control"
										placeholder="Chee Kin"> <span class="help-block">This
										is inline help</span>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-6">
								<div class="form-group has-error">
									<label class="control-label">Last Name</label> <input
										type="text" id="lastName" class="form-control"
										placeholder="Lim"> <span class="help-block">This
										field has error.</span>
								</div>
							</div>
							<!--/span-->
						</div>
					</div>
					<div class="form-actions right">
						<button type="button" class="btn default">Cancel</button>
						<button type="submit" class="btn blue">
							<i class="fa fa-check"></i> Save
						</button>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12 col-sm-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-user"></i>Table
				</div>
				<div class="actions">
					<a href="#" class="btn blue"><i class="fa fa-pencil"></i> Add</a>
					<div class="btn-group">
						<a class="btn green" href="#" data-toggle="dropdown"> <i
							class="fa fa-cogs"></i> Tools <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right">
							<li><a href="#"><i class="fa fa-pencil"></i> Edit</a></li>
							<li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
							<li><a href="#"><i class="fa fa-ban"></i> Ban</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="i"></i> Make admin</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover"
					id="sample_2">
					<thead>
						<tr>
							<th style="width1: 8px;"><input type="checkbox"
								class="group-checkable" data-set="#sample_2 .checkboxes" /></th>
							<th>任务标识</th>
							<th>任务名称</th>
							<th>流程标识</th>
							<th>描述</th>
							<th>创建时间</th>
							<th>处理人</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="v" items="${tasks}">
							<tr>
								<td><input type="checkbox" class="checkboxes"
									value="${v.id}" /></td>
								<td>${v.id}</td>
								<td>${v.name}</td>
								<td>${v.processInstanceId}</td>
								<td>${v.description}</td>
								<td>${v.createTime}</td>
								<td>${v.assignee}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
	FormSamples.init();
	TableManaged.init();
</script>