<%-- 
    Document   : EditSubject
    Created on : Nov 25, 2019, 7:34:06 PM
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
        
        <title>Edit Subject</title>
        <style>
            #editsubject{
                position : relative;
                left: 20em;
                top:13em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Edit Subject"/>
        <div class="container" id="editsubject">
            <form method="post" action="EditSubject">
                <table class="table table-borderless" style="width:450px">
                    <tbody>
                        <tr>
                            <td class="text-center" colspan="2" style="font-size: 40px; font-weight: bold;">
                                Edit Subject
                            </td>
                        </tr>
                        <tr>
                            <td>
                                SUBJECTS
                            </td>
                            <td>
                                : 
                                <select name="subjectid">
                                    <c:forEach items="${subjects}" var="s">
                                        <option value="${s.subjectid}">${s.subjectname}</option>    
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                SUBJECT NAME
                            </td>
                            <td>
                                : <input type="text" name="subjectname" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                DESCRIPTION
                            </td>
                            <td>
                                : <input type="text" name="description" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="ManageSubjects">
                                    <span class="btn btn-secondary">Back</span>
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
