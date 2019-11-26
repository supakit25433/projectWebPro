<%-- 
    Document   : CreateSubject
    Created on : Nov 20, 2019, 4:00:22 PM
    Author     : nar-u
--%>

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
        
        <title>Create Subject</title>
        <style>
            #createsubject{
                position: relative;
                left:20em;
                top:13em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Create Subject"/>
        <div class="container" id="createsubject">
            <form method="post" action="CreateSubject">
                <table class="table table-borderless" style="width: 500px;">
                    <tbody>
                        <tr>
                            <td class="text-center" colspan="2" style="font-size:40px; font-weight: bold;">
                                Create Subject
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Subject Name
                            </td>
                            <td>
                                : <input type="text" name="subjectname" autocomplete="off">
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
                                <a href="Index">
                                    <span class="btn btn-secondary">Back to index</span>
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
                </table>           
            </form>
        </div>
    </body>
</html>
