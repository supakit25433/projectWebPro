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

        <link rel="stylesheet" href="./css/style.css">

        <title>Welcome ${user.username} to Quiz Ant</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Home Page"/>
        <br />

        <!--================ Start Slide Show Area =================-->
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
        <!--================ End Slide Show Area =================-->

        <br>

        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>New Quiz!</h5></div>
            <div class=" bg-light p-5 rounded">
                <div class="row">
                    <div class="col-sm-12 my-auto">
                        <span style="font-size: 30px;"><p class="text-center">${message}</p></span>
                    </div>
                    <c:forEach items="${quizzes}" var="q">
                        <div class="col-3 mb-4">
                            <div class="card-deck">
                                <div class="card">
                                    <a href="Quiz?quizid=${q.quizid}">
                                        <img class="card-img-top" src="./images/picture.jpg" alt="Card image" style="width:100%">
                                        <div class="card-body">
                                            <h4 class="card-title">${q.quizname}</h4>
                                            <h5 class="card-text">${q.subjectsSubjectid.subjectname}</h5>
                                            <h6 class="card-text">${q.subjectsSubjectid.usersUserid.fullname}</h6>
                                            <p class="card-text">${q.description}</p>
                                            <h6 class="card-text">Quizzes: ${q.questionsList.size()}</h6>
                                        </div>
                                        <div class="overlay"></div>
                                        <div class="text">Start Quiz</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach> 
                </div>
            </div>
        </div>
    </body>
</html>
