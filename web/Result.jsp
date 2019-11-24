<%-- 
    Document   : Result
    Created on : Nov 19, 2019, 10:05:07 PM
    Author     : surface
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
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Result"/>
        <h2>Quiz ended!!!</h2><br>
        <h4>Your score is ${test}</h4>
        <a href="Index"><button>Back to index</button></a>
        <c:forEach items="${text}" var="t">
            <br>${t}
        </c:forEach>
    </body>
</html>
