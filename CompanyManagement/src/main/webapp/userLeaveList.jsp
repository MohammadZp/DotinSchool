<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="view.UserView" %>
<%@ page import="view.LeaveView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>لیست درخواست های مرخصی ثبت شده توسط کاربر</title>
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
<%User user= (User) session.getAttribute("user");%>
<%long id=user.getId();%>
<div align="center">
    <table border="1"  id="table" cellpadding="5">
        <tr>
            <th>نام کاربری درخواست دهنده ی مرخصی</th>
            <th>نام درخواست دهنده ی مرخصی</th>
            <th>نام خانوادگی درخواست دهنده ی مرخصی</th>
            <th>تاریخ شروع مرخصی</th>
            <th>تاریخ اتمام مرخصی</th>
            <th>ساعت شروع مرخصی</th>
            <th> ساعت اتمام مرخصی</th>
            <th>تاریخ ایجاد درخواست</th>
            <th>تاریخ آخرین به روزرسانی</th>
            <th>وضعیت درخواست</th>
            <th>ایجاد تغییرات</th>
            <th> حذف درخواست</th>
        </tr>
        <% ArrayList<LeaveView> leaveViews =
                (ArrayList<LeaveView>) request.getAttribute("leaveViewList");
            for (LeaveView leaveView : leaveViews) {%>
        <%if(leaveView.isEnable()){%>
        <tr <%if(!leaveView.isActive()){%> bgcolor="red" <%}%>>

            <td><%=leaveView.getLeaveRequesterUsername()%>
            </td>
            <td><%=leaveView.getLeaveRequesterFirstName()%>
            </td>
            <td><%=leaveView.getLeaveRequesterLastName()%>
            </td>
            <td><%=leaveView.getFromDate()%>
            </td>
            <td><%=leaveView.getToDate()%>
            </td>
            <td><%=leaveView.getFromTime()%>
            </td>
            <td><%=leaveView.getToTime()%>
            </td>
            <td><%=leaveView.getCreationDate()%>
            </td>
            <td><%=leaveView.getModificationDate()%>
            </td>
            <td><%=leaveView.getLeaveStatus()%>
            </td>
            <td>
                <a href="edit?id=<%=leaveView.getId()%>">ویرایش</a>
            </td>
            <td>
                <a href="delete?id=<%=leaveView.getId()%>">حذف</a>
            </td>
        </tr>
        <td>
        </td>
        <%}%>
        <%}%>
        </tr>

    </table>
</div>
</body>
</html>
