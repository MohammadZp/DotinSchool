<%@ page import="model.Leave" %>
<%@ page import="view.LeaveAuditLogView" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/28/2021
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>گزارش تاریخچه ی مرخصی</title>
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

<%List<LeaveAuditLogView> leaveAuditLogViews= (List<LeaveAuditLogView>) request.getAttribute("leaveAuditLogViews");%>
<div align="center">
    <table border="1"  id="table" cellpadding="5">
        <tr>
            <th>نام</th>
            <th>نام خانوداگی</th>
            <th>نام کاربری</th>
            <th>تاریخ شروع مرخصی</th>
            <th>تاریخ اتمام مرخصی</th>
            <th>ساعت شروع مرخصی</th>
            <th>ساعت اتمام مرخصی</th>
            <th>تاریخ ایجاد</th>
            <th> تاریخ  روزرسانی</th>
        </tr>

        <%
            for(LeaveAuditLogView leaveAuditLogView:leaveAuditLogViews){
        %>
        <tr <%if(leaveAuditLogView.getRequesterId()%2==0){%> bgcolor="#adff2f" <%}%> >
            <td><%=leaveAuditLogView.getFirstname()%>
            </td>
            <td><%=leaveAuditLogView.getLastname()%>
            </td>
            <td><%=leaveAuditLogView.getUsername()%>
            </td>
            <td><%=leaveAuditLogView.getFromDate()%>
            </td>
            <td><%=leaveAuditLogView.getToDate()%>
            </td>
            <td><%=leaveAuditLogView.getFromTime()%>
            </td>
            <td><%=leaveAuditLogView.getToTime()%>
            </td>
            <td><%=leaveAuditLogView.getCreationDate()%>
            </td>
            <td><%=leaveAuditLogView.getModificationDate()%>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
