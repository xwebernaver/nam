<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
	<title>YoilTellerMVC</title>
</head>
<body>
<p>
year=<%=request.getParameter("year") %>
</p>
<h1>${myDate.year}년 ${myDate.month}월 ${myDate.day}일은 ${yoil}요일입니다.</h1>
</body>
</html>