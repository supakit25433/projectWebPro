<%-- 
    Document   : Profile
    Created on : Nov 20, 2019, 12:16:12 AM
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
        <title>Profile</title>
        <style>
            td{
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Subjects"/>
        <table border="1">
            <tr class="text-center">
                <th colspan="2" style="font-size: 36px">
                    Profile
                </th> 
            </tr>
            <tr class="text-center">
                <td>
                    Username : ${user.username}
                </td>
                <td>
                    Full Name : ${user.fullname}
                </td>
            </tr>
        </table>
    </body>
</html>
