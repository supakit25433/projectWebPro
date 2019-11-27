<%-- 
    Document   : DeleteQuiz
    Created on : Nov 27, 2019, 3:53:57 PM
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
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>Delete Quiz</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Delete Quiz"/>
        <div class="container" id="deletequiz">
            <form method="post" action="DeleteQuiz">
                <table class="table table-borderless">
                    <tbody>
                        <tr>
                            <td colspan="2" style="font-size: 40px; font-weight: bold;">
                                Delete Quiz
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Name
                            </td>
                            <td>
                                : <select name="quizid">
                                    <c:forEach items="${quizes}" var="q">
                                        <option value="${q.quizid}">${q.quizname}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Subject Name
                            </td>
                            <td>
                                : <select name="subjectid">
                                    <c:forEach items="${subjects}" var="s">
                                        <option value="${s.subjectid}">${s.subjectname}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="Subjects">
                                    <span class="btn btn-secondary">Back to Subjects</span>
                                </a>
                            </td>
                            <td>
                                <input class="btn btn-danger" type="submit" value="Delete!">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                ${message}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
