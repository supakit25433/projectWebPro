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

        <title>${subject}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Subjects"/>
        <br>

        <br>

        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded">
                <h5>
                    ${subject}
                </h5>
            </div>
            <div class=" bg-light p-5 rounded">
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
