<%-- 
    Document   : ManageSubject
    Created on : Nov 20, 2019, 4:04:48 PM
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
        <title>Manage Subject</title>
        <style>
            #managesubject{
                position:relative;
                top:5em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Manage Subject"/>
        <br>
        <div class="container" id="managesubject">
            <span style="font-size:40px; font-weight: bold;">Manage Subject</span><br><br>
            <a href="CreateSubject.jsp">
                <button class="btn btn-primary">
                    Create Subject
                </button>
            </a>
            <br>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>
                            SUBJECT_ID
                        </th>
                        <th>
                            SUBJECT_NAME
                        </th>
                        <th>
                            DESCRIPTION
                        </th>
                        <th>
                            Edit Subject
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${subjects}" var="s">
                        <tr>
                            <td>
                                ${s.subjectid}
                            </td>
                            <td>
                                ${s.subjectname}
                            </td>
                            <td>
                                ${s.description}
                            </td>
                            <td>
                                <a href="EditSubject">
                                    <button>Edit Subjects</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
