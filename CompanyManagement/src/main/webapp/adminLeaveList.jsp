<%@ page import="view.LeaveView" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/23/2021
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>لیست تمام مرخصی های ثبت شده در سیستم</title>
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

    #table2 {
        font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', 'sans-serif';
        border-collapse: collapse;
        width: 100%;
    }

    #table2 td, #table2 th {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: center;
    }

    #table2 tr:hover {background-color: rgb(124, 80, 80);}
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
<div align="center">
    <table border="1" id="table" cellpadding="5">
        <caption>لیست مرخصی های ثبت شده توسط تمام کاربران سیستم</caption>
        <tr>
            <th>نام کاربری</th>
            <th>نام</th>
            <th>نام خانوادگی</th>
            <th>تاریخ شروع مرخصی</th>
            <th>تاریخ اتمام مرخصی</th>
            <th>ساعت شروع مرخصی</th>
            <th> ساعت اتمام مرخصی</th>
            <th>تاریخ ایجاد درخواست</th>
            <th>تاریخ آخرین به روزرسانی</th>
            <th>وضعیت درخواست</th>
<%--            <th>ویرایش</th>--%>
            <th>وضعیت غعال بودن درخواست</th>
            <th>حذف</th>
        </tr>
        <% ArrayList<LeaveView> leaveViews =
                (ArrayList<LeaveView>) request.getAttribute("leaveAdminViewList");
            for (LeaveView leaveView : leaveViews) {%>
        <tr <%if (!leaveView.isActive()) {%> bgcolor="red" <%}%>>
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
<%--            <td>--%>
<%--                <a href="edit?id=<%=leaveView.getId()%>">ویرایش</a>--%>
<%--            </td>--%>
          <%if (leaveView.isActive()) {%>
            <td>
               فعال
            </td>
            <%}else{%>
            <td>
                غیر غعال
            </td>

            <%}%>
<%--            <%} else {%>--%>
<%--            <td>--%>
<%--                <a href="deactivate?id=<%=leaveView.getId()%>">غیر فعال سازی</a>--%>
<%--            </td>--%>
<%--            <%}%>--%>
            <td>
                <a href="delete?id=<%=leaveView.getId()%>">حذف</a>
            </td>
        </tr>
        <td>
        </td>
        <%}%>
<%--        <%}%>--%>
        </tr>

    </table>
</div>
</body>
</html>
