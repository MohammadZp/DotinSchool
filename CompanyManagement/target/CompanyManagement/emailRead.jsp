<%@ page import="model.Email" %>
<%@ page import="net.bytebuddy.matcher.ElementMatcher" %>
<%@ page import="model.AttachmentsFiles" %>
<%@ page import="model.Attachment" %>
<%@ page import="systemMessages.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="view.EmailView" %><%--
  Created by IntelliJ IDEA.
  User: mohammad
  Date: 2/9/2021
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>مشاهده ی ایمیل </title>
</head>

<style>

    a{
        padding: 12px 20px;
        margin: 8px 0;
        width:500px;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .D{
        padding: 12px 20px;
        margin: 8px 0;
        width:360px;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .B{
        padding: 12px 20px;
        margin: 8px 0;
        width:330px;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .C{
        background-color:maroon;
        color:oldlace;
        width:170px;


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
<% int count=0;%>
<%EmailView emailView= (EmailView) request.getAttribute("emailView");%>
<table>
    <th>
        <tr>
            <label for="senderEmail">ارسال کننده ی ایمیل:</label>
            <a class="B" id="senderEmail" name="senderEmail"><%=emailView.getSenderUsername()%></a>
        </tr>
    </th>
    <br>
    <th>
        <tr>
            <label for="subject">موضوع:</label>
            <a class="D" type="text" id="subject" name="subject"><%=emailView.getSubject()%></a>
        </tr>
    </th>
    <br>
    <th>
        <tr>
            <label for="EmailRecievers">دریافت کنندگان ایمیل:</label>
            <a><%=Message.showListOfRecievers(emailView.getUserViewRecieved())%>"</a><br><br>
        </tr>
    </th>
    <th>
        <tr>
    <textarea rows="20" cols="80" name="emailText" >
        <%=emailView.getText()%>
         </textarea>
        </tr>
    </th>
    <br>
    <th>
        <%if(!emailView.getAttachments().isEmpty()){%>
        <tr>
            <%for(Attachment attachment:emailView.getAttachments()){%>
            <a class="C" href="/CompanyManagement_war/Download?id=<%=attachment.getId()%>">  لینک دانلود فایل ضمیمه </a><br>
        </tr>
        <%}}%>
    </th>
</table>
</body>
</html>




<!-- <%--<a>Subject:<%=email.getSubject()%></a><br>--%>
<%--<a>Sender:<%=email.getUserSender().getUsername()%></a>--%>
<%--<a>Data:<%=email.getCreationDate()%></a>--%>
<%--<textarea rows="20" cols="80"><%=email.getText()%> </textarea><br>--%>

<%--<%int count=0;%>--%>
<%--<h1><%=email.getAttachments().size()%></h1>--%>
<%--<%if(!email.getAttachments().isEmpty()){%>--%>
<%--<%for(Attachment attachment:email.getAttachments()){%>--%>
<%--<%count++;%>--%>
<%--<a href="/CompanyManagement_war/Download?id=<%=attachment.getId()%>"> file <%=count%> download link</a><br>--%>
<%--<%}}%>--%> -->