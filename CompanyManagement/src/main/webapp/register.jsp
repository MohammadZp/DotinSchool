<!-- <%@ page import="java.util.ArrayList" %>
<%@ page import="model.CategoryElement" %>
<%@ page import="java.util.List" %>
<%@ page import="view.UserView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> -->
<html dir="rtl">
<meta charset="UTF-8">
<style>
    required:before {
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
        width: 300px;
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
        width: 300px;
    }
</style>
<body>
<!-- <%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%> -->
<div style="text-align: center;">
    <br>
    <br>
    <form action="/CompanyManagement_war/user/add" method="post">
        <table style="width: 400px;" align="center">
            <tr>
                <td align="left">کد ملی:</td>
                <td align="right"><span style="color: red;">*</span><input class="required" required type="number"  placeholder="کد ملی" name="nationalcode"></td>

            </tr>
            <tr>
                <td align="left">نام:</td>
                <td  align="right"><span style="color: red;">*</span><input class="required" required type="text"   placeholder="نام" name="firstName"></td>

            </tr>
            <tr>
                <td align="left">نام خانوادگی:</td>
                <td align="right"><span style="color: red;">*</span><input  required type="text"   placeholder="نام خانوادگی" name="lastName"></td>
            </tr>
            <tr>
                <td align="left">نام کاربری:</td>
                <td align="right"><span style="color: red;">*</span><input  required type="text"   placeholder="نام کاربری" name="username"></td>

            </tr>
            <tr>
                <td align="left">کلمه ی عبور</td>
                <td align="right"><span style="color: red;">*</span><input  required type="password"  placeholder="کلمه ی عبور"
                                        name="password"></td>
            </tr>
            <tr>
                <td align="left">ایمیل:</td>
                <td align="right"><span style="color: red;">*</span><input required type="email"  placeholder="ایمیل" name="email"></td>
            </tr>
            <tr>
                <td align="left">انتخاب مدیر:</td>
                <td align="right">
                    <%List<UserView> userViews = (List<UserView>) request.getAttribute("userViews");%>
                    <select class="A" name="managerId" id="categoryElementList">
                        <%for (UserView userView : userViews) {%>
                        <%if(userView.isEnable()){%>
                        <option value="<%=userView.getId()%>">
                            <%=userView.getUsername()%>
                        </option>
                        <%}}%>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="left">نقش:</td>
                <td align="right">
                    <%List<String> roleList = (List<String>) request.getAttribute("roles");%>
                    <select class="A" name="role" id="categoryElementList">
                        <%for (String role : roleList) {%>
                        <option value="<%=role%>">
                            <%=role%>
                        </option>
                        <%}%>
                    </select>
                </td>
            </tr>


            <!-- <tr>
                <td align="center">
                    <button style="width: 100px;" type="submit"
                            style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">ورود
                    </button>
                </td>
            </tr> -->


            <tr>
                <td align="left"></td>
                <td align="right">
                    <BUTTON style="width: 300px;" onclick="myFunction()" style="width:200px;   font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif" type="submit">ثبت کاربر</BUTTON>
                </td>
            </tr>

            <%List<String> stringList= (List<String>) request.getAttribute("errors");%>

            <tr>
                <td align="left"></td>
                <td align="left"><p id="demo"></p></td>
            </tr>

        </table>
    </form>
</div>
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

<!--
<%--<tr>--%>
<%--    <td align="right">manager id:</td>--%>
<%--    <td align="left"><input type="number" placeholder="manager id" name="managerId"></td>--%>
<%--</tr>--%> -->
<!--
<%--<tr>--%>
<%--    <td align="right">manager id:</td>--%>
<%--    <td align="left"><input type="number" placeholder="manager id" name="managerId"></td>--%>
<%--</tr>--%> -->






<!--
<%--<tr>--%>
<%--    <td align="right">manager id:</td>--%>
<%--    <td align="left"><input type="number" placeholder="manager id" name="managerId"></td>--%>
<%--</tr>--%> -->



<%--<%@ page import="java.util.ArrayList" %>--%>
<%--<%@ page import="model.CategoryElement" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="view.UserView" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<meta charset="UTF-8">--%>
<%--<body>--%>
<%--<%--%>
<%--    if (session.getAttribute("user") == null) {--%>
<%--        response.sendRedirect("loginPage.jsp");--%>
<%--    }--%>
<%--%>--%>
<%--<div style="text-align: center;">--%>
<%--    <h1>Register</h1>--%>
<%--    <form action="user/add" method="post">--%>
<%--        <table align="center">--%>
<%--            <tr>--%>
<%--                <td align="right">nationalcode:</td>--%>
<%--                <td align="left"><input type="number" required placeholder="national code" name="nationalcode"></td>--%>
<%--            </tr>--%>

<%--            <tr>--%>
<%--                <td align="right">username:</td>--%>
<%--                <td align="left"><input type="text" max="20" required placeholder="username" name="username"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="right">password:</td>--%>
<%--                <td align="left"><input type="password" min="100" required="required" placeholder="password"--%>
<%--                                        name="password"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="right">email:</td>--%>
<%--                <td align="left"><input type="email" required="required" placeholder="email" name="email"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="left">--%>
<%--                    <%List<UserView> userViews = (List<UserView>) request.getAttribute("userViews");%>--%>
<%--                    <select name="managerId" id="categoryElementList">--%>
<%--                        <%for (UserView userView : userViews) {%>--%>
<%--                        <%if(userView.isEnable()){%>--%>
<%--                        <option value="<%=userView.getId()%>">--%>
<%--                            <%=userView.getUsername()%>--%>
<%--                        </option>--%>
<%--                        <%}}%>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="left">--%>
<%--                    <%List<String> roleList = (List<String>) request.getAttribute("roles");%>--%>
<%--                    <select name="categoryElement" id="categoryElementList">--%>
<%--                        <%for (String role : roleList) {%>--%>
<%--                        <option value="<%=role%>">--%>
<%--                            <%=role%>--%>
<%--                        </option>--%>
<%--                        <%}%>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">--%>
<%--                    <BUTTON type="submit">register</BUTTON>--%>
<%--                </td>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <td align="right">manager id:</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <td align="left"><input type="number" placeholder="manager id" name="managerId"></td>&ndash;%&gt;--%>
<%--&lt;%&ndash;</tr>&ndash;%&gt;--%>