<%@ page contentType="text/html;charset=utf-8" isErrorPage="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error500.jsp</title>
</head>
<body>
<p>error500.jsp</p>

<h1>예외가 발생했습니다.</h1>
<%-- 발생한 예외 : ${ex}<br>
예외 메시지 : ${ex.message}<br> --%>

발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>

<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>

