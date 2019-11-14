<%-- 
    Document   : index
    Created on : Sep 19, 2019, 8:54:40 AM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>
        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->

        <title>Welcome ${user.username} to Quiz Ant</title>

        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Home Page"/>
        <br />

        <div class="container-fluid">
            <div class="text-center">
                <img height="150px" width="1000px">
            </div>
        </div>

        <div class="slidershow middle">
            <div class="slides">
                <input type="radio" name="r" id="r1" checked>
                <input type="radio" name="r" id="r2">
                <input type="radio" name="r" id="r3">
                <input type="radio" name="r" id="r4">
                <input type="radio" name="r" id="r5">
                <div class="slide s1">
                    <img src="images/1.jpg" alt="">
                </div>
                <div class="slide">
                    <img src="images/2.jpg" alt="">
                </div>
                <div class="slide">
                    <img src="images/3.jpg" alt="">
                </div>
                <div class="slide">
                    <img src="images/4.jpg" alt="">
                </div>
                <div class="slide">
                    <img src="images/5.jpg" alt="">
                </div>
            </div>

            <div class="navigation">
                <label for="r1" class="bar"></label>
                <label for="r2" class="bar"></label>
                <label for="r3" class="bar"></label>
                <label for="r4" class="bar"></label>
                <label for="r5" class="bar"></label>
            </div>
        </div>

        <br />
        <div class="container-fluid">
            Each Subject will be show in here.
            <%--<c:forEach>--%>
            <!-- ใช้การวนลูป list ออกมาจากข้อมูลที่ได้ อาจจะใช้รูปแบบเดียวกันกับที่สอบรายบุคคล -->
            <%--</c:forEach>--%>
            <table>
                <tr>
                    <c:forEach items="" var="q" varStatus="s">
                        <td></td>
                        <c:if test="s%4==0">
                            <tr>
                            </tr>
                        </c:if>
                    </c:forEach>
            </table>
        </div>
    </body>
</html>
