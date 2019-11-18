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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <title>Quiz of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Quiz"/>
        <br>
        <c:forEach items="${test}" var="t">
            ${t}
        </c:forEach>
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
                                        <p class="font-weight-normal"><input type="radio" name="id" value="${c.choiceid}"> ${c.choice}</p>
                                    </div>                              
                                </c:forEach>
                            </c:if>
                            <c:if test="${qu.typename=='answer'}">
                                <div class="ml-4">
                                    asd
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            <input type="submit" value="Submit the answers!">
            </form>
        </div>
    </body>
</html>
