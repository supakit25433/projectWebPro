<%-- 
    Document   : Quiz
    Created on : Nov 14, 2019, 7:43:45 PM
    Author     : nar-u
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

        <title>Quiz of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Quiz"/>
        <br>
        <div class="container">
            <!--<div class="bg-info text-white pl-3 pt-2 pb-1 mb-4 rounded">-->
                <h1 class="text-uppercase font-weight-bold">${quiz.quizname}</h1>
                <h5 class="text-uppercase">Created by : ${quiz.subjectsSubjectid.usersUserid.fullname}</h5>
                <p>Description : ${quiz.description}</p>
                <br>
            <!--</div>-->
            <form action="Quiz" method="post">
                <c:forEach items="${questions}" var="qu" varStatus="questionIndex">
                    <div class="container-fluid">
                        <div class=" bg-light p-5 mb-4 rounded">
                            <div class="mb-4 sameLine">
                                <h5 class="font-weight-bold">${questionIndex.index+1}. ${qu.question}</h5> <p class="font-weight-light float-right">${score.get(questionIndex.index)} Points</p>
                                <br>
                                <p class="font-weight-light">${qu.description}</p>
                            </div>
                            <c:if test="${qu.typename=='multiple choices'}">
                                <c:forEach items="${choices.get(questionIndex.index)}" var="c">
                                    <div class="ml-4">
                                        <label><p class="font-weight-normal"><input type="radio" name="${questionIndex.index}" value="${c.point}"required> ${c.choice}</p></label>
                                    </div>                              
                                </c:forEach>
                            </c:if>
                            <c:if test="${qu.typename=='answer'}">
                                <div class="ml-4">
                                    <input name="${questionIndex.index}" hidden="true" value="0"/>
                                    <!--<textarea name="${questionIndex.index}" rows="5" cols="120" placeholder="Insert answer here." required></textarea>-->
                                </div>
                            </c:if>
                            <a href="../src/java/jpa/QuizesJpaController.java"></a>
                        </div>
                    </div>
                </c:forEach>
                <input name="quizid" hidden="true" value="${quiz.quizid}"/>
                <input type="submit" value="Submit the answers!" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
