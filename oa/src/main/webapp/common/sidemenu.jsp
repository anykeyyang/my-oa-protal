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
					<a class="ajaxify start" href="${ctx}/user/user_dashboard.action">
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
						<li>
							<a class="ajaxify" href="${ctx}/process/process_test.action">
							任务设置                   
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">日程管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							日程安排                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							日程设置                    
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">公文管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							公文维护                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							公文归档                   
							</a>
						</li>
					</ul>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">行政管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							会议室管理                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							车辆管理                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							资产管理                   
							</a>
						</li>
					</ul>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">人事管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							人员履历                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							请假申请                  
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							转正申请                  
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							离职申请                  
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							请假申请                  
							</a>
						</li>
					</ul>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">组织管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							机构管理                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							人员管理                   
							</a>
						</li>
					</ul>
				</li>
				
				<li class="last">
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">系统管理</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="#">
							用户管理                 
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							用户组（职务）管理                   
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							角色管理                    
							</a>
						</li>
						<li>
							<a class="ajaxify" href="#">
							权限管理                    
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU1 -->
		</div>