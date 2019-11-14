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
        <meta name="viewport" content="initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>
        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->

        <title>Welcome ${user.username} to Quiz Ant</title>

    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Home Page"/>
        <br />

        <div class="container">
            <div id="demo" class="carousel slide" data-ride="carousel">

                <!-- Indicators -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                </ul>

                <!-- The slideshow -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="./images/picture.jpg" alt="pic" width="1500px" height="200px">
                    </div>
                    <div class="carousel-item">
                        <img src="./images/amazon.jpg" alt="amazon" width="1500px" height="200px">
                    </div>
                    <div class="carousel-item">
                        <img src="./images/universe.jpg" alt="universe" width="1500px" height="200px">
                    </div>
                </div>

                <!-- Left and right controls -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
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
