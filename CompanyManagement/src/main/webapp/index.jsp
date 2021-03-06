<%@ page import="model.User" %>
<%@ page import="systemMessages.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title> پروفایل شخصی</title>
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
    }

    li {
        float: left;
    }

    li a, .dropbtn {
        display: inline-block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }
    .topnav .login-container button {
        float: right;
        padding: 6px 10px;
        margin-top: 8px;
        margin-right: 16px;
        background-color: #555;
        color: white;
        font-size: 17px;
        border: none;
        cursor: pointer;
    }
    li a:hover, .dropdown:hover .dropbtn {
        background-color: red;
    }

    li.dropdown {
        display: inline-block;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }

    .dropdown-content a:hover {background-color: #f1f1f1;}

    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>
<body>
<%
    String username="";
    String role="";
    User manager=null;
    response.setHeader("Cache-Control","no-cahce,no-store,must-revalidate");
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }else {
        HttpSession httpSession = (HttpSession) request.getSession();
        User user = (User) httpSession.getAttribute("user");
         username = user.getUsername();
         role=user.getRole().getCode();
         manager=user.getManager();
%>
<%}%>
<ul>
    <!-- <li style="float:right"><a href="#home">Home</a></li>
    <li style="float:right"><a href="#news">News</a></li>
    <li style="float:right" class="dropdown"> -->
<%if(role.equals(Message.ADMIN)||manager==null){%>
    <li style="float: right;" class="dropdown">
        <a  href="javascript:void(0)" class="dropbtn">مدیریت کاربر</a>
        <div class="dropdown-content">
            <a  href="/CompanyManagement_war/user/register">ثبت کاربر جدید</a>
            <a  href="/CompanyManagement_war/user/list"> مشاهده ی لیست کاربران و ایجاد تغییرات</a>
        </div>
    </li>
<%}%>
    <li  style="float: right;" class="dropdown">
        <a  href="javascript:void(0)" class="dropbtn">مدیریت مرخصی</a>
        <div class="dropdown-content">
            <a  href="/CompanyManagement_war/leaveRequest"> ثبت درخواست مرخصی</a>
            <a  href="/CompanyManagement_war/leave/list"> مشاهده درخواست های مرخصی </a>
            <%if(role.equals(Message.ADMIN)||manager==null){%>
            <a  href="/CompanyManagement_war/leave/adminViewlist"> مشاهده تمام درخواست مرخصی کارمندان </a>
            <%}%>
        </div>
    </li>

    <li style="float: right;"  class="dropdown">
        <a  href="javascript:void(0)" class="dropbtn">مدیریت ایمیل ها</a>
        <div class="dropdown-content">
            <a  href="/CompanyManagement_war/composeEmail">ارسال ایمیل</a>
            <a  href="/CompanyManagement_war/email/inboxEmail">مشاهده ی  لیست ایمیل های دریافتی</a>
            <a  href="/CompanyManagement_war/email/sentEmail"> مشاهده ی لیست ایمیل های ارسال شده</a>
        </div>
    </li>
    <li style="float: right;"  class="dropdown">
        <a  href="javascript:void(0)" class="dropbtn">گزارش تاریخچه</a>
        <div class="dropdown-content">
            <a  href="/CompanyManagement_war/log/user">تاریخچه کاربر</a>
            <a  href="/CompanyManagement_war/log/leave">تاریخچه مرخصی کاربر</a>
        </div>
    </li>
    <div style="float:left">
        <form action="/CompanyManagement_war/logout" method="POST">
            <button  style="float: left;" style="height:5px; right: 80%;" type="submit">خروج از سیستم</button>
        </form>
    </div>
</ul>

<h3> <%=username%> خوش آمدید!</h3>
















<!-- <a href="/CompanyManagement_war/register">user register</a><br>
<a href="/CompanyManagement_war/user/list">user list</a><br>
<a href="/CompanyManagement_war/leaveRequest">leave request</a><br>
<a href="/CompanyManagement_war/leave/list">leave list</a><br>
<a href="/CompanyManagement_war/composeEmail">Compose Email</a><br>
<a href="/CompanyManagement_war/email/inboxEmail">inbox Emails</a><br>
<a href="/CompanyManagement_war/email/sentEmail">sent Emails</a><br>
<form action="/CompanyManagement_war/logout" method="post">
    <input type="submit" value="Logout">
</form> -->
</body>
</html>


<%--<body>--%>
<%--<%--%>
<%--    if (session.getAttribute("user") == null) {--%>
<%--        response.sendRedirect("loginPage.jsp");--%>
<%--    }--%>
<%--    HttpSession httpSession= (HttpSession) request.getSession();--%>
<%--    User user= (User) httpSession.getAttribute("user");--%>
<%--%>--%>
<%--<a href="/CompanyManagement_war/register">user register</a><br>--%>
<%--<a href="/CompanyManagement_war/user/list">user list</a><br>--%>
<%--<a href="/CompanyManagement_war/leaveRequest">leave request</a><br>--%>
<%--<a href="/CompanyManagement_war/leave/list">leave list</a><br>--%>
<%--<a href="/CompanyManagement_war/composeEmail">Compose Email</a><br>--%>
<%--<a href="/CompanyManagement_war/email/inboxEmail">inbox Emails</a><br>--%>
<%--<a href="/CompanyManagement_war/email/sentEmail">sent Emails</a><br>--%>
<%--<form action="/CompanyManagement_war/logout" method="post">--%>
<%--    <input type="submit" value="Logout">--%>
<%--</form>--%>
<%--</body>--%>