<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %> 
<%pageContext.setAttribute("ctx", request.getContextPath());%>
<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU1 -->         
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-xs"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li>
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->                     
					<form class="sidebar-search" action="extra_search.html" method="POST">
						<div class="form-container">
							<div class="input-box">
								<a href="javascript:;" class="remove"></a>
								<input type="text" placeholder="Search..."/>
								<input type="button" class="submit" value=" "/>
							</div>
						</div>
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				<li class="start">
					<a class="ajaxify start" href="layout_ajax_content_1.html">
					<i class="fa fa-home"></i> 
					<span class="title">Dashboard</span>
					<span class="selected"></span>
					</a>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">流程管理</span>
					<span class="selected"></span>
					<span class="arrow open"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="${ctx}/process/process_test.action">
							测试                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="${ctx}/process/process_getDeployedProcess.action">
							流程部署                 
							</a>
						</li>
						<li>
							<a class="ajaxify" href="${ctx}/process/process_getInstanceInvolvedUser.action">
							 流程查询                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="${ctx}/process/process_getUserTask.action">
							待办任务                  
							</a>
						</li>
					</ul>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Ajax Submenu 2</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 1                  
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 2                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 3                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 4                    
							</a>
						</li>
					</ul>
				</li>
				<li class="last">
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Ajax Submenu 3</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 1                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 2                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 3                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							Ajax Link Sample 4                    
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU1 -->
		</div>