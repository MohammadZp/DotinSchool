<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="view.UserView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>لیست کاربران و تغییرات</title>
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
<div align="center">
    <table border="1"  id="table" cellpadding="5">
        <tr>

            <th>کد ملی</th>
            <th>نام</th>
            <th>نام خانوداگی</th>
            <th>نام کاربری</th>
            <th>ایمیل</th>
            <th>تاریخ ایجاد</th>
            <th> تاریخ آخرین به روزرسانی</th>
            <th>مدیر کاربر</th>
            <th>نقش کاربر</th>
            <th>فعالسازی/غیرفعالسازی</th>
            <th> اصلاح اطلاعات کاربر</th>
            <th> حذف کاربر</th>
        </tr>
        <% ArrayList<UserView> userViewsList =
                (ArrayList<UserView>) request.getAttribute("userViewsList");
            for (UserView userView : userViewsList) {%>
<%--        <%if(userView.isEnable()){%>--%>
        <tr <%if(!userView.isActive()){%> bgcolor="red" <%}%>>

            <td><%=userView.getNationalCode()%>
            </td>
            <td><%=userView.getFirstName()%>
            </td>
            <td><%=userView.getLastName()%>
            </td>
            <td><%=userView.getUsername()%>
            </td>
            <td><%=userView.getEmailAddress()%>
            </td>
            <td><%=userView.getCreationDate()%>
            </td>
            <td><%=userView.getModificationDate()%>
            </td>
            <%if (userView.getManager() != null) {%>
            <td><%=userView.getManager()%>
            </td>
            <%} else {%>
            <td>
            </td>
            <%}%>
            <td><%=userView.getRole()%>
            </td>
            <%if (!userView.isActive()) {%>
            <td>
                <a href="activate?id=<%=userView.getId()%>">فعال سازی</a>
            </td>
            <%} else {%>
            <td>
                <a href="deactive?id=<%=userView.getId()%>">غیر فعال سازی</a>
            </td>
            <%}%>
            <td>
                <a href="edit?id=<%=userView.getId()%>">ویرایش اطلاعات</a>
            </td>
            <td>
                <a href="delete?id=<%=userView.getId()%>">حذف</a>
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
