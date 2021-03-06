<%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/3/2021
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>صفحه ی ورود</title>
</head>
<style>
    .required:before {
        content:"*";
        color: #e32;
        position: absolute;
        margin: 0px 0px 0px -20px;
        padding: 0 5px 0 0; }
    input[type=text], input[type=password] {
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    form {
        position: absolute;
        left: 42%;
        top: 150px;
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

<div style="text-align: center;">
    <h2 style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">به صفحه ی ورود خوش
        آمدید</h2>
    <form action="login" method="post">
        <table align="=center">
            <tr>
                <td align="right"><input required
                        style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif"
                        name="username" type="text" placeholder="نام کاربری خود را وارد کنید"></td>
                <td class="required" align="left" style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">
                    :نام کاربری
                </td>
            </tr>
            <tr>
                <td align="right"><input required
                        style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif"
                        name="password" type="password" placeholder="کلمه ی عبور خود را وارد کنید"></td>
                <td class="required" align="left" style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">
                    :کلمه ی عبور
                </td>
            </tr>
            <tr>
                <td align="center">
                    <button style="width: 100px;" type="submit"
                            style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif">ورود
                    </button>
                </td>
            </tr>
            <br>
            <br>
            <%String error = (String) request.getAttribute("error");%>
            <%if (error != null) {%>
            <tr>
                <td align="center"><a style="width: 100px;"
                                      style="font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif"><%=error%>
                </a></td>
            </tr>
            <%}%>
        </table>
    </form>

</div>
</body>
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
</html>
<%--<form action="login" method="post">--%>
<%--    <input name="username" type="text" placeholder="username">--%>
<%--    <input name="password" type="password" placeholder="password">--%>
<%--    <input type="submit" value="login">--%>
<%--</form>--%>