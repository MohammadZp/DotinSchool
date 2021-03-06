<%@ page import="view.LeaveView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/19/2021
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>مشاهدی درخواست</title>
</head>
<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>
<% LeaveView leaveView= (LeaveView) request.getAttribute("LeaveView");%>
<p><b>نام کاربری:<%=leaveView.getLeaveRequesterUsername()%></b></p>
<p><b>نام:<%=leaveView.getLeaveRequesterFirstName()%></b></p>
<p><b>نام خانوادگی:<%=leaveView.getLeaveRequesterLastName()%></b></p>
<p>وضعیت مرخصی:<%=leaveView.getLeaveStatus()%></p>
<p>تاریخ ایجاد درخواست:<%=leaveView.getCreationDate()%></p>
<p>تاریخ آخرین به روز رسانی<%=leaveView.getModificationDate()%></p>
<p>تاریخ شروع مرخصی:<%=leaveView.getFromDate()%></p>
<p>تاریخ اتمام مرخصی:<%=leaveView.getToDate()%></p>
<p>ساعت شروع مرخصی:<%=leaveView.getFromTime()%></p>
<p>ساعت اتمام مرخصی:<%=leaveView.getToTime()%></p>
</body>
</html>
