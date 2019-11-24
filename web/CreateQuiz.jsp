<%-- 
    Document   : CreateQuiz
    Created on : Nov 24, 2019, 11:47:55 PM
    Author     : nar-u
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Subjects"/>
        <form method="post" action="CreateQuiz">
            Quiz Name : <input type="text" name="quizname" required>
            Description : <input type="text" name="description" required>
            Subject ID : 
            <select name="subjectid">
                <c:forEach items="${subjects}" var="s">
                    <option value="${s.subjectid}">${s.subjectname}</option>
                </c:forEach>
            </select>
            <input type="submit">
        </form>
        <a href="Subjects"><button>Back to Subjects</button></a>
        ${message}
    </body>
</html>
