<%@ page import="view.UserAuditLogView" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/28/2021
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>گزارش تاریخچه ی کاربران</title>
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

<%List<UserAuditLogView> userAuditLogViews= (List<UserAuditLogView>) request.getAttribute("userAuditLogViews");%>
<div align="center">
    <table border="1"  id="table" cellpadding="5">
        <tr>
            <th>نام</th>
            <th>نام خانوداگی</th>
            <th>نام کاربری</th>
            <th>نام کاربری مدیر</th>
            <th>تاریخ ایجاد</th>
            <th> تاریخ  روزرسانی</th>
            <th>نام کاربری ایجاد کننده</th>
            <th>نام کاربری اصلاح کننده</th>
        </tr>

<%
for(UserAuditLogView userAuditLogView:userAuditLogViews){
%>
        <tr <%if(userAuditLogView.getId()%2==0){%> bgcolor="#adff2f" <%}%>  >
            <td><%=userAuditLogView.getFirstname()%>
            </td>
            <td><%=userAuditLogView.getLastname()%>
            </td>
            <td><%=userAuditLogView.getUsername()%>
            </td>
            <td><%=userAuditLogView.getManagerUsername()%>
            </td>
            <td><%=userAuditLogView.getCreationDate()%>
            </td>
            <td><%=userAuditLogView.getModificationDate()%>
            </td>
            <td><%=userAuditLogView.getCreatedBy()%>
            </td>
            <td><%=userAuditLogView.getModifiedBy()%>
            </td>
        </tr>
<%}%>
    </table>
</div>
</body>
</html>
