<%@ page import="model.User" %>
<%@ page import="model.Email" %>
<%@ page import="java.util.List" %>
<%@ page import="view.EmailView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/9/2021
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>ایمیل های ارسال شده</title>
</head>
<style>
    td {
        text-align: center;
    }

    th {
        text-align: center;
    }
    tr {
        text-align: center;
    }
    #table {
        font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', 'sans-serif';
        border-collapse: collapse;
        width: 100%;
    }

    #table td, #table th {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: center;
    }

    #table tr:hover {background-color: rgb(124, 80, 80);}

    th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #4CAF50;
        color: white;
    }
</style>

<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>

<% List<EmailView> emailViews
    = (List<EmailView>) request.getAttribute("sentEmailViewList");%>
<%int count=0;%>
<table border="1" cellpadding="5" style="width:100%">
    <tr>
        <th>شماره</th>
        <th>موضوع ایمیل</th>
        <th>تاریخ ارسال</th>
        <th>وضعیت ایمیل</th>
        <th>مشاهده ی ایمیل</th>
    </tr>
    <% for(EmailView emailView:emailViews){%>
    <tr>
        <% count++;%>

        <td><%=count%></td>
        <td><%=emailView.getSubject()%></td>
        <td><%=emailView.getCreationDate()%></td>
        <td><%=emailView.getEmailStatus()%></td>
        <td>
            <a href="/CompanyManagement_war/email/read?id=<%=emailView.getId()%>">مشاهده ی ایمیل</a>
        </td>
    </tr>
    <%}%>

</table>

</body>
</html>
