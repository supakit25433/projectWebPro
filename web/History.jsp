<%-- 
    Document   : History
    Created on : Nov 10, 2019, 3:16:54 PM
    Author     : nar-u
--%>

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
        
        <title>History of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=History"/>
        <br>
        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>New Quiz!</h5></div>
            <div class=" bg-light p-5 rounded">
                <div class="row">
                    <c:forEach items="${quizrecord}" var="qr">
                        <div class="col-3 mb-4">
                            <div class="card-deAck">
                                <div class="card">
                                    <a href="/Quiz.jsp">
                                        <img class="card-img-top" src="./images/picture.jpg" alt="Card image" style="width:100%">
                                        <div class="card-body">
                                            <h4 class="card-title">${qr.getQuizId().getQuizid().getQuizName()}</h4>
                                            <h5 class="card-text">${qr.getQuizId().getSubjectsSubjectid().getSubjectname()}</h5>
                                            <h6 class="card-text">${qr.getQuizId().getSubjectsSubjectid().getUsersUserid()}</h6>
                                            <p class="card-text">${qr.getQuizId().getDesctiption()}</p>
                                            <h6 class="card-text">Amount of question</h6>
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
