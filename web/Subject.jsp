<%-- 
    Document   : Subject
    Created on : Nov 18, 2019, 12:27:30 AM
    Author     : surface
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="./css/style.css">

        <title>${subject.subjectname}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Subjects"/>
        <br>

        <!--================ Start About Us Area =================-->
        <section class="about_area section_gap">
            <div class="container">
                <div class="row justify-content-start align-items-center">
                    <div class="col-lg-5">
                        <div class="about_img">
                            <img class="img-fluid" src="./images/picture.jpg" alt="pic">
                        </div>
                    </div>

                    <div class="offset-lg-1 col-lg-5">
                        <div class="main_title text-left">
                            <h1 class="text-uppercase font-weight-bold">${subject.subjectname}</h1>
                            <h5 class="text-uppercase">${subject.usersUserid.fullname}</h5>
                            <p>${subject.description}</p>
                            <c:choose>
                                <c:when test="${subscription == null}">
                                    <a href="Subscribe?subjectid=${subject.subjectid}">
                                        <button class="btn btn-primary subscribe">Subscribe</button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="Subscribe?subjectid=${subject.subjectid}">
                                        <button class="btn btn-light unsubscribe">Subscribed</button>
                                    </a>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================ End About Us Area =================-->
        <br>
        <!--================ Start Statistics Area =================-->
        <section class="statistics_area">
            <div class="container">
                <div class="row justify-content-lg-start justify-content-center">
                    <div class="col-lg-2 col-md-3">
                        <div class="bg-light p-2 pb-3 pt-4 rounded text-center">
                            <h3>${subject.quizesList.size()}</h3>
                            <p class="font-weight-light">Quizzes</p>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3">
                        <div class="bg-light p-2 pb-3 pt-4 rounded text-center">
                            <h3>${subscriber}</h3>
                            <p class="font-weight-light">Subscribers</p>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3">
                        <div class="bg-light p-2 pb-3 pt-4 rounded text-center">
                            <h3></h3>
                            <p class="font-weight-light">Total Score</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================ End Statistics Area =================-->
        <br>
        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>Lasted Quiz!</h5></div>
            <c:choose>
                <c:when test="${userid == user.userid}">
                    <a href="CreateQuiz">
                        <button class="btn btn-primary">Add Quiz</button>
                    </a>
                    <a href="CreateQuestion">
                        <button class="btn btn-primary">Add Question</button>
                    </a>
                    <a href="CreateChoice">
                        <button class="btn btn-primary">Add Choice</button>
                    </a><br>
                </c:when>
            </c:choose>
            <br>
            <div class="bg-light p-5 rounded">
                <div class="row">
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
                                            <h6 class="card-text">Questions : ${q.questionsList.size()}</h6>
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
