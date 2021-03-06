<%@ page import="model.Email" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>

<form action="send" method="post" enctype="multipart/form-data">
    <input type="text" name="text">
    <input type="file" name="file" multiple>
    <input type="submit" value="send">
</form>
</body>
</html>
