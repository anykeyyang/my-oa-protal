<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("ctx", request.getContextPath());
%>
<div class="pageContent">
    <img alt="流程图片" src="data:image/png;base64,${auditImageBase64}">
</div>
<c:forEach var="v" items="${activityList}">
${v.x}
<div style='position:absolute;left:${v.x}px;top:${v.y}px;width:${v.width}px;height:${v.height}px;border-style:solid;border-color:#00FF00;border-radius:10px;'></div>
</c:forEach>

