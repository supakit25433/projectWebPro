<%-- 
    Document   : CreateQuestion
    Created on : Nov 26, 2019, 10:09:16 PM
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
        <style>
            #createquestion{
                position:relative;
                left:12em;
                top:10em;
            }
        </style>
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>Create Question</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Add Question"/>
        <div class="container" id="createquestion">
            <form method="post" action="CreateQuestion">
                <table class="table table-borderless">
                    <tbody>
                        <tr>
                            <td colspan="2" style="font-size:40px; font-weight: bold;">
                                Add Question
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Question
                            </td>
                            <td>
                                : <input type="text" name="questionname" autocomplete="off" required style="width:30em;">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Type
                            </td>
                            <td>
                                :
                                <select name="type">
                                    <option value="multiple choices">Multiple Choices</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Description
                            </td>
                            <td>
                                : <input type="text" name="description" autocomplete="off" style="width:30em;">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Name    
                            </td>
                            <td>
                                : 
                                <select name="quizid">
                                    <c:forEach items="${quiz}" var="q"> 
                                        <option value="${q.quizid}">${q.quizname}</option>
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
