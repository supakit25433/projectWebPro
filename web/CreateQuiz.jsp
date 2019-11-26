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
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>Create Quiz</title>
        <style>
            #createquiz{
                position : relative;
                left:12em;
                top:5em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Add Quiz"/>
        <div class="container" id="createquiz">
            <form method="post" action="CreateQuiz">
                <table class="table table-borderless">
                    <tbody>
                        <tr>
                            <td colspan="2" style="font-size: 40px; font-weight: bold;">
                                Add Quiz
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Name
                            </td>
                            <td>
                                : <input type="text" name="quizname" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Description
                            </td>
                            <td>
                                : <input type="text" name="description" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Subject ID
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
                                <input class="btn btn-primary" type="submit">
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
