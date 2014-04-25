<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %> 
<%pageContext.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>监狱信息化综合管理平台</title>

<link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet"
	type="text/css" media="screen" />
<link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet"
	type="text/css" media="screen" />
<link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet"
	type="text/css" media="print" />
<link href="${ctx}/dwz/uploadify/css/uploadify.css"
	rel="stylesheet" type="text/css" media="screen" />
<!--如果是IE浏览器 [if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--如果是IE浏览器9 [if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${ctx}/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.cookie.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.bgiframe.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.js"
	type="text/javascript"></script>
<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${ctx}/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="${ctx}/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx}/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="${ctx}/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx}/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx}/dwz/chart/g.dot.js"></script>
<script src="${ctx}/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.util.date.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.validate.method.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.regional.zh.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.accordion.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.switchEnv.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.contextmenu.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.dialogDrag.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.pagination.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.datepicker.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.print.js" type="text/javascript"></script>
<script src="${ctx}/js/json2.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/dwz/js/dwz.regional.zh.js"
	type="text/javascript"></script>

<script type="text/javascript">
<!-- 初始化框架配置 -->
$(function(){
	DWZ.init("${ctx}/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>

</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">
					</br>
					<li><a href="${ctx}//login.action">退出</a></li>
				</ul>
			</div>

			<!-- navMenu -->

		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>点击收缩</div>
				</div>
				
				<div class="accordion" fillSpace="sidebar">
				
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>系统注册
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/integrate/system_getAllSystems.action"
								target="navTab" rel="sysreg" fresh="true">系统注册</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>用户权限
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${ctx}/users/userManage.action"
								target="navTab" rel="user" fresh="true">用户管理</a></li>
							<li><a href="${ctx}/users/userManage!getRole.action"
								target="navTab" rel="role" fresh="true">角色权限</a></li>
							<li><a href="${ctx}/users/userManage!getDept.action"
								target="navTab" rel="dept" fresh="true">部门管理</a></li>
								
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>日志审计
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/operatelog/operatelogaction_prepare.action"
								target="navTab" rel="operatelogaction" fresh="true">操作日志</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>信息查询
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/criminal/criminal_getBaseInfo.action"
								target="navTab" rel="criminal" fresh="true">罪犯综合信息</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>字典维护
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/datadictionary/datadictionaryaction_ditionarydatapre.action?checkId=30"
								target="navTab" rel="ditionarydata" fresh="true">系统字典维护</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>流程管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/process/process_getDeployedProcess.action"
								target="navTab" rel="deployedProcess" fresh="true">流程管理</a></li>
								<li><a
								href="${ctx}/process/process_getInstanceInvolvedUser.action"
								target="navTab" rel="instance" fresh="true">流程实例管理</a></li>
							<li><a
								href="${ctx}/process/process_getUserTask.action"
								target="navTab" rel="userTask" fresh="true">待办任务</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>安防配置
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a
								href="${ctx}/safeConfig/regionManage_getRegionList.action"
								target="navTab" rel="regionManage" fresh="true">区域管理</a></li>
							<li><a
								href="${ctx}/safeConfig/stationManage_getStationList.action"
								target="navTab" rel="station" fresh="true">站点管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时新增 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要新增一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要新增一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="0"
							style="margin:0px;padding:0px;">
						<img src="${ctx}/images/bg.JPG"  style="width:100%;height:99%;"/>
							
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>

	<div id="footer">
		Copyright &copy; 2013 <a href="demo_page2.html" target="dialog">技术支持：山东万博科技股份有限公司</a>
		联系电话：82311719
	</div>
</body>
</html>