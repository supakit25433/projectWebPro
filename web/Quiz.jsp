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
        <title>Quiz of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Quiz"/>
        <br>
        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>${quiz.quizname}</h5><br><h6>${quiz.description}</h6></div>
            <form action="Quiz" method="post">
                <c:forEach items="${questions}" var="qu" varStatus="questionIndex">
                    <div class="container-fluid">
                        <div class=" bg-light p-5 mb-4 rounded">
                            <div class="mb-4">
                                <h5 class="font-weight-bold">${questionIndex.index+1}. ${qu.question}</h5>
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
                                </div>
                            </c:if>
                            <a href="../src/java/jpa/QuizesJpaController.java"></a>
                        </div>
                    </div>
                </c:forEach>
                <input name="quizid" hidden="true" value="${quiz.quizid}"/>
                <input type="submit" value="Submit the answers!">
            </form>
        </div>
    </body>
</html>
