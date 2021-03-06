<%@ page import="model.User" %>
<%@ page import="model.CategoryElement" %>
<%@ page import="java.util.List" %>
<%@ page import="view.UserView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 12/27/2020
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>ویرایش اطلاعات</title>
</head>
<style>
    .required::before {
        content:"*";
        color: #e32;
        position: absolute;
        margin: 0px 0px 0px -20px;
        padding: 0 5px 0 0; }
    input[name=username], input[name=password],input[name=nationalcode],input[name=email],input[name=firstName],input[name=lastName]{
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
        width:300px;
    }
    .A{
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
        color:#000500;
        width: 300px;
        height:30px

    }
    form {
        border-color: steelblue;
        left:50%;
        top: 150px;
        width: 100%;

    }
    button:hover {
        opacity: 0.8;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
    }
</style>
<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>
<% UserView userView = (UserView) request.getAttribute("userView");%>
<div style="text-align: center;">
    <br>
    <br>
    <form action="/CompanyManagement_war/user/update" method="post">
        <table style="width: 400px;" align="center">
            <tr hidden>
                <td align="right">id:</td>
                <td align="right"><input value="<%=userView.getId()%>" readonly type="number" name="id"></td>
            </tr>
            <tr>
                <td align="left">کد ملی:</td>
                <td align="right"><span style="color: red;">*</span><input required type="number" id="nationalcode" value="<%=userView.getNationalCode()%>" placeholder="کد ملی"
                                         name="nationalcode"></td>
            </tr>
            <tr>
                <td align="left">نام:</td>
                <td align="right"><span style="color: red;">*</span><input required type="text" value="<%=userView.getFirstName()%>" placeholder="نام"
                                         name="firstName"></td>
            </tr>
            <tr>
                <td align="left">نام خانوادگی:</td>
                <td align="right"><span style="color: red;">*</span><input required type="text" value="<%=userView.getLastName()%>" placeholder="نام خانوادگی"
                                         name="lastName"></td>
            </tr>
            <tr>
                <td align="left">نام کاربری:</td>
                <td align="right"><span style="color: red;">*</span><input required type="text" value="<%=userView.getUsername()%>" placeholder="نام کاربری"
                                         name="username"></td>
            </tr>
            <tr>
                <td align="left">کلمه ی عبور:</td>
                <td align="right"><span style="color: red;">*</span><input required type="password" value="<%=userView.getPassword()%>" placeholder="کلمه ی عبور"
                                         name="password"></td>
            </tr>
            <tr>
                <td align="left">ایمیل:</td>
                <td align="right"><span style="color: red;">*</span><input required type="text" value="<%=userView.getEmailAddress()%>" placeholder="آدرس ایمیل"
                                         name="email"></td>
            </tr>
<%--            <tr hidden>--%>
<%--                <select name="activeCheck">--%>
<%--                    <option value="active" <%if (userView.isActive()) {%>--%>
<%--                            selected <%}%>>active--%>
<%--                    </option>--%>
<%--                    <option value="deActive" <%if(!userView.isActive()){%>selected <%}%>>deActive</option>--%>

<%--                </select>--%>
<%--            </tr>--%>
<%--            <tr hidden>--%>
<%--                <select name="enableCheck">--%>
<%--                    <option value="enable" <%if (userView.isEnable()) {%>--%>
<%--                            selected <%}%>>enable--%>
<%--                    </option>--%>
<%--                    <option value="disable"<%if(!userView.isEnable()){%>selected <%}%>>disable</option>--%>
<%--                </select>--%>
<%--            </tr>--%>
            <%--            <%if (user.getManager() != null) {%>--%>
            <%--            <tr>--%>
            <%--                <td align="right">manager id:</td>--%>
            <%--                <td align="left"><input type="number" value="<%=user.getManager().getId()%>" placeholder="manager id"--%>
            <%--                                        name="managerId"></td>--%>
            <%--            </tr>--%>
            <%--            <%} else {%>--%>
            <%--            <tr>--%>
            <%--                <td align="right">manager id:</td>--%>
            <%--                <td align="left"><input type="number" value=" " placeholder="manager id" name="managerId"></td>--%>
            <%--            </tr>--%>
            <%--            <%}%>--%>

            <tr>
                <td align="left">انتخاب مدیر:</td>
                <td align="right">
                    <%List<UserView> userViews = (List<UserView>) request.getAttribute("userViews");%>
                    <select class="A"  name="managerId" id="N">
                        <%for (UserView user : userViews) {%>
                        <%if(userView.isEnable()){%>
                        <option value="<%=user.getId()%>">
                            <%=user.getUsername()%>
                        </option>
                        <%}}%>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="left">نقش کاربر:</td>
                <td align="right">
                    <%List<String> roleList = (List<String>) request.getAttribute("roles");%>
                    <select class="A"  name="role" id="M">
                        <%for (String role : roleList) {%>
                        <option value="<%=role%>">
                            <%=role%>
                        </option>
                        <%}%>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="left"></td>
                <td align="right">
                    <BUTTON style="width: 300px;" type="submit">به روز رسانی</BUTTON>
                </td>
                </td>
            </tr>
        </table>

    </form>
</div>
</div>
<%List<String> stringList= (List<String>) request.getAttribute("errors");%>
<div align="center" style="color: #800505;">
    <%if (stringList != null) {%>
    <%for(String s:stringList){%>
    <a style="width: 100px;"
       style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">
        <%=s%>
    </a>
    <br>
    <%}}%>

</div>
<script>
    x = document.getElementById("N");
    x.options[x.selectedIndex].text = "<%=userView.getManager()%>";
        x = document.getElementById("M");
    x.options[x.selectedIndex].text = "<%=userView.getRole()%>";
</script>
 <script>
    document.addEventListener("DOMContentLoaded", function() {
        var elements = document.getElementsByTagName("INPUT");
        for (var i = 0; i < elements.length; i++) {
            elements[i].oninvalid = function(e) {
                e.target.setCustomValidity("");
                if (!e.target.validity.valid) {
                    e.target.setCustomValidity("این فیلد نمی تواند خالی باشد");
                }
            };
            elements[i].oninput = function(e) {
                e.target.setCustomValidity("");
            };
        }
    })
</script>
</body>
</html>