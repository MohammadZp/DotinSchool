<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="view.UserView" %>
<%@ page import="view.LeaveView" %>
<%@ page import="systemMessages.Message" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>لیست درخواست های مرخصی کارمندان</title>
</head>
<%User user= (User) request.getSession().getAttribute("user");
long userId=user.getId();
%>
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

<% ArrayList<LeaveView> leaveViews =
        (ArrayList<LeaveView>) request.getAttribute("leaveViewList");
if(!leaveViews.isEmpty()){

%>

<div align="center">
    <table border="1"  id="table" cellpadding="5">
        <caption>لیست مرخصی های ثبت شده توسط کاربر</caption>
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
            <th>ویرایش</th>
            <th>فعال سازی /غیر فعالسازی</th>
        </tr>

           <% for (LeaveView leaveView : leaveViews) {%>
        <%if(leaveView.isEnable() && leaveView.getUserRequesterId()==userId){%>
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
            <%if (!leaveView.isActive()) {%>
            <td>
                <a href="activate?id=<%=leaveView.getId()%>">فعال سازی</a>
            </td>
            <%} else {%>
            <td>
                <a href="deactivate?id=<%=leaveView.getId()%>">غیر فعال سازی</a>
            </td>
            <%}%>
<%--            <td>--%>
<%--                <a href="delete?id=<%=leaveView.getId()%>">حذف</a>--%>
<%--            </td>--%>
        </tr>
        <td>
        </td>
        <%}%>
        <%}%>
        </tr>

    </table>
</div>
<%}%>
<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<% ArrayList<LeaveView> leaveViewsManager =
        (ArrayList<LeaveView>) request.getAttribute("leaveViewAsManager");%>
<%if(!leaveViewsManager.isEmpty()){%>

<div align="center">
    <table border="1"  id="table2" cellpadding="5">
        <caption>لیست مرخصی های ثبت شده توسط اعضای تیم</caption>
        <tr>
            <th>نام کاربری درخواست دهنده ی مرخصی</th>
            <th>تاریخ شروع مرخصی</th>
            <th>تاریخ اتمام مرخصی</th>
            <th>ساعت شروع مرخصی</th>
            <th> ساعت اتمام مرخصی</th>
            <th>تاریخ ایجاد درخواست</th>
            <th>تاریخ آخرین به روزرسانی</th>
            <th>وضعیت درخواست</th>
            <th>مشاهده ی درخواست</th>
            <th>موافقت </th>
            <th>عدم موافقت</th>
          <%if(user.getRole().equals(Message.ADMIN)){%>  <th> حذف درخواست</th><%}%>
        </tr>


        <%    for (LeaveView leaveView : leaveViewsManager) {
//       if(leaveView.isEnable() && leaveView.isActive()){
        %>
        <tr <%if(!leaveView.isActive()){%> bgcolor="red" <%}%>>

            <td><%=leaveView.getLeaveRequesterUsername()%>
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
                <a href="viewLeave?id=<%=leaveView.getId()%>"
                   target="popup"
                   onclick="window.open('viewLeave?id=<%=leaveView.getId()%>','popup','width=400,height=400'); return false;">
                    مشاهده
                </a>
            </td>
<%--            <%if (!leaveView.isActive()) {%>--%>
<%--            <td>--%>
<%--                <a href="activate?id=<%=leaveView.getId()%>">فعال سازی</a>--%>
<%--            </td>--%>
<%--            <%} else {%>--%>
<%--            <td>--%>
<%--                <a href="deactivate?id=<%=leaveView.getId()%>">غیر فعال سازی</a>--%>
<%--            </td>--%>
<%--            <%}%>--%>
<%--            <%if (!leaveView.isActive()) {%>--%>
            <td>
                <a href="accept?id=<%=leaveView.getId()%>">موافقت</a>
            </td>
<%--            <%} else {%>--%>
            <td>
                <a href="reject?id=<%=leaveView.getId()%>">عدم موافقت</a>
            </td>
<%--            <%}%>--%>
            <%if(user.getRole().equals(Message.ADMIN)){%>
            <td>
                <a href="delete?id=<%=leaveView.getId()%>">حذف</a>
            </td>
            <%}%>
        </tr>
        <td>
        </td>
        <%}
//}
%>

        </tr>

    </table>
    <%}%>
</div>
</body>

</html>
