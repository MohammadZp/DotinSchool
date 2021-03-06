<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>درخواست مرخصی </title>
    <link rel="stylesheet" href="js-persian-cal.css">
    <script src="js-persian-cal.min.js"></script>
</head>
<meta charset="utf-8">
<style>
    .required::before {
        content:"*";
        color: #e32;
        position: absolute;
        margin: 0px 0px 0px -20px;
        padding: 0 5px 0 0; }
    select{
        padding: 12px 20px;
        margin: 8px 0;
        border: 1px solid #ccc;
        box-sizing: border-box;
        width: 100px;
    }
    input{
        padding: 12px 20px;
        margin: 8px 0;
        border: 1px solid #ccc;
        box-sizing: border-box;
        align-items: center;

        width: 360px;
    }

    .checkbox{
        box-sizing: sizing 60px;

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
        width: 380px;
    }
</style>




<body>
<br>
<br>
<br>
<br>
<br>
<br>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
    }
%>
<div style="text-align: center;">
    <% HttpSession httpSession= (HttpSession) request.getSession();%>
    <% User user= (User) httpSession.getAttribute("user");%>
    <form action="/CompanyManagement_war/leave/add" method="post">
        <table style="width: 500px;" align="center">
            <%--            <tr>--%>
            <%--                <td align="right">user Id:</td>--%>
            <%--            <td align="left"><input type="number" value="<%=user.getId()%>" placeholder="userId" name="userId" hidden></td>--%>
            <%--            &lt;%&ndash;            </tr>&ndash;%&gt;--%>
            <tr>
                <td align="right">تاریخ شروع:</td>
                <td align="right"><span style="color: red;">*</span><input  readonly name="startDate" required type="text" id="pcal1" class="pdate"></td>
            </tr>
            <tr>
                <td align="right">تاریخ اتمام:</td>
                <td align="right"><span style="color: red;">*</span><input  readonly name="finishDate" required type="text" id="pcal2" class="pdate"></td>
            </tr>
            <%--            <tr >--%>
            <%--                <td>تاریخ شروع:</td>--%>
            <%--                <td align="right">--%>
            <%--                    <label for="selectDay">روز</label>--%>
            <%--                    <select name="selectStartDay" id="selectDay">--%>
            <%--                        <option>انتخاب روز</option>--%>
            <%--                    </select>--%>

            <%--                    <label for="selectMonth"> ماه</label>--%>
            <%--                    <select name="selectStartMonth" id="selectMonth">--%>
            <%--                        <option>انتخاب ماه</option>--%>
            <%--                    </select>--%>

            <%--                    <label for="selectYear"> سال</label>--%>
            <%--                    <select name="selectStartYear" id="selectYear">--%>
            <%--                        <option >انتخاب سال</option>--%>
            <%--                    </select></td>--%>
            <%--            </tr>--%>
            <%--            <tr>--%>
            <%--                <td>تاریخ پایان:--%>
            <%--                <td align="right">--%>
            <%--                    <label for="selectDay">روز</label>--%>
            <%--                    <select name="selectFinishDay" id="Day">--%>
            <%--                        <option>انتخاب روز</option>--%>
            <%--                    </select>--%>

            <%--                    <label for="selectMonth"> ماه</label>--%>
            <%--                    <select name="selectFinishMonth" id="Month">--%>
            <%--                        <option>انتخاب ماه</option>--%>
            <%--                    </select>--%>
            <%--                    <label for="selectYear"> سال</label>--%>
            <%--                    <select name="selectFinishYear" id="Year">--%>
            <%--                        <option >انتخاب سال</option>--%>
            <%--                    </select>--%>
            <%--                </td>--%>

            <%--            </tr>--%>
            <tr>
                <td align="right">مرخصی روزانه:</td>
                <td align="right"><input class="checkbox" type="checkbox" name="checkboxDailyLeave"></td>
            </tr>
            <tr>
                <td align="right">مرخصی ساعتی:</td>
                <td align="right"><input  class="checkbox" onchange="enable()" type="checkbox" name="checkboxHourLeave"></td>

            </tr>
            <tr>
                <td align="right">ساعت شروع:</td>
                <td align="right"><input id="C" disabled type="time"  name="startTime"></td>
            </tr>
            <tr>
                <td align="right">ساعت پایان:</td>
                <td align="right"><input id="D" disabled type="time" placeholder="finish Time" name="finishTime"></td>
            </tr>
            <tr>
                <td align="right"></td>
                <td align="right"><BUTTON type="submit">ثبت درخواست مرخصی</BUTTON></td></td>
            </tr>
        </table>
    </form>
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
</body>
<%--<script>--%>
<%--    var select = document.getElementById("selectYear");--%>
<%--    var year = [];--%>
<%--    var k=1399;--%>
<%--    for(var i=0;i<100;i++){--%>
<%--        year[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < year.length; i++) {--%>
<%--        var opt = year[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>

<%--    var select = document.getElementById("selectMonth");--%>
<%--    var month = [];--%>
<%--    var k=1;--%>
<%--    for(var i=0;i<12;i++){--%>
<%--        month[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < month.length; i++) {--%>
<%--        var opt = month[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>

<%--    var select = document.getElementById("selectDay");--%>
<%--    var day = [];--%>
<%--    var k=1;--%>
<%--    for(var i=0;i<30;i++){--%>
<%--        day[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < day.length; i++) {--%>
<%--        var opt = day[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>





<%--    var select = document.getElementById("Year");--%>
<%--    var year = [];--%>
<%--    var k=1399;--%>
<%--    for(var i=0;i<100;i++){--%>
<%--        year[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < year.length; i++) {--%>
<%--        var opt = year[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>

<%--    var select = document.getElementById("Month");--%>
<%--    var month = [];--%>
<%--    var k=1;--%>
<%--    for(var i=0;i<12;i++){--%>
<%--        month[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < month.length; i++) {--%>
<%--        var opt = month[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>

<%--    var select = document.getElementById("Day");--%>
<%--    var day = [];--%>
<%--    var k=1;--%>
<%--    for(var i=0;i<30;i++){--%>
<%--        day[i]=k;--%>
<%--        k++;--%>

<%--    }--%>
<%--    select.innerHTML = "";--%>
<%--    for(var i = 0; i < day.length; i++) {--%>
<%--        var opt = day[i];--%>
<%--        select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";--%>
<%--    }--%>

<%--</script>--%>

<script>
    function enable() {
        if(document.getElementById("C").disabled==false &&  document.getElementById("D").disabled==false){
            document.getElementById("D").disabled=true
            document.getElementById("C").disabled=true
        }else{

            document.getElementById("D").disabled=false
            document.getElementById("C").disabled=false

        }
    }
    function disable() {
        document.getElementById("C").disabled=true;
    }


</script>


<script>
    var objCal1 = new AMIB.persianCalendar( 'pcal1' );
    var objCal1 = new AMIB.persianCalendar( 'pcal2' );
</script>
</html>
