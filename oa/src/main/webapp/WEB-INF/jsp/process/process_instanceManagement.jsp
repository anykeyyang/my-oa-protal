<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	pageContext.setAttribute("ctx", request.getContextPath());
%>
<form id="pagerForm" method="post" onsubmit="return divSearch(this);"
	action="${ctx}/process/process_getInstanceInvolvedUser.action">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="${ctx}/process/process_getInstanceInvolvedUser.action"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>罪犯编号：</label> <input type="text" name="zfbh"
						value="${zfbh}" /></td>
					<td><label>罪犯姓名：</label> <input type="text" name="xm"
						value="${xm}" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138" rel="criminal">
		<thead>
			<tr>
				<th width="10%" align="center">流程标识</th>
				<th width="15%" align="center">业务标识</th>
				<th width="20%" align="center">发起人</th>
				<th width="10%" align="center">开始时间</th>
				<th width="20%" align="center">结束时间</th>
				
				<th width="10%" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${hiProcessInstance}">
				<tr target="sid_obj" rel="${v.id}">
					<td>${v.id}</td>
					<td>${v.businessKey}</td>
					<td>${v.startUserId}</td>
					<td>${v.startTime}</td>
					<td>${v.endTime}</td>
					<td>
					<a title="流程追踪" class="btnEdit"
					   href="${ctx}/process/process_getAuditImage.action?processDefinitionId=${v.processDefinitionId}&processInstanceid=${v.id}"
					   target="dialog" rel="dlg_page10" mask="true"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" ${numPerPage eq 20?"selected":"" }>20</option>
				<option value="50" ${numPerPage eq 50?"selected":"" }>50</option>
				<option value="100" ${numPerPage eq 100?"selected":"" }>100</option>
				<option value="200" ${numPerPage eq 200?"selected":"" }>200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}">
		</div>
	</div>
</div>