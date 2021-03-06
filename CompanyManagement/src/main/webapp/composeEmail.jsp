<%@ page import="model.User" %>
<%@ page import="view.UserView" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>ارسال ایمیل</title>
</head>

<style>
    input[name=subject], input[name=EmailRecievers],input[name=attachments],input[name=email],input[name=firstName],input[name=lastName]{
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .A{
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
        color:#000500;
        width: 200px;
        height:30px

    }
    form {
        border-color: steelblue;
        position: absolute;
        left:42%;
        top: 150px;
    }
    button:hover {
        opacity: 0.8;
    }

    button,.labelButton{
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
    }
    .center {
        margin: 0 auto;
        padding: 10px;
    }
</style>





<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>
<% HttpSession httpSession= (HttpSession) request.getSession();%>
<% User user= (User) httpSession.getAttribute("user");%>

<h3>ارسال ایمیل</h3>
<table>
    <form action="email/send" method="post" class="center" enctype="multipart/form-data">
        <th>
            <tr>
                <label for="senderEmail">ارسال کننده ی ایمیل:</label>
                <a id="senderEmail" name="senderEmail"><%=user.getUsername()%></a>
            </tr>
        </th>
        <br>
        <th>
            <tr>
                <label for="subject">موضوع:</label>
                <input type="text" id="subject" name="subject" placeholder="موضوع ایمیل خود را مشخص کنید">
            </tr>
        </th>
        <br>
        <th>
            <tr>
                <%List<UserView> userViews = (List<UserView>) request.getAttribute("userViews");%>
                <label for="users">لیست کاربران موجود در سیستم:</label>
                <select class="A" id="users">
                    <%for (UserView userView : userViews) {%>
                    <option value="<%=userView.getUsername()%>">
                        <%=userView.getUsername()%>
                    </option>
                    <%}%>
                </select>
                <a>    </a>
                <button type="button" style="width:250px; " onclick="myFunction()">افزودن به لیست دریافت کنندگان ایمیل</button>
            </tr>
        </th>
        <br>
        <th>
            <tr>
                <label for="EmailRecievers">دریافت کنندگان ایمیل:</label>
                <input type="text" id="EmailRecievers" name="EmailRecievers" placeholder="دریافت کنندگان ایمیل"  id="recievers"><br><br>
            </tr>
        </th>
        <th>
            <tr>
    <textarea rows="20" cols="80" name="emailText">
         </textarea>
            </tr>
        </th>
        <br>
        <th>
            <tr>
                <label style="width: 200px;" class="labelButton" for="files" class="btn">افزودن فایل</label>
                <input type="file" id="files" style="visibility:hidden;" name="attachments" multiple/>
                <button style="width: 185px;" type="submit"> ارسال </button>
            </tr>
        </th>
    </form>
</table>
</body>


<script>
    function myFunction() {
        var x=document.getElementById("users").value;
        var y=","+document.getElementById("EmailRecievers").value
        document.getElementById("EmailRecievers").value =x+y
    }
</script>

</html>