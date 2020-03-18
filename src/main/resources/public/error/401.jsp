<%--
  Created by IntelliJ IDEA.
  User: PhuocPT
  Date: 2/17/2017
  Time: 8:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    response.reset();
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
%>
<%@ page
        language="java"
        contentType="text/html;charset=UTF-8"
        pageEncoding="UTF-8"
        isErrorPage="true"
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json; charset=UTF-8"/>
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <title>error</title>
</head>
<body>{"error":"Authentication failed."}</body>
</html>