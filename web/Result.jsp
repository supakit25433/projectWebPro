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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        ${test}<br>
        <c:forEach items="${result}" var="r">
            ${r.typename}
        </c:forEach>
    </body>
</html>
