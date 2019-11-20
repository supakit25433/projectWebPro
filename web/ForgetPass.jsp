<%-- 
    Document   : ForgetPass
    Created on : Nov 19, 2019, 5:50:28 PM
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
        
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="ForgetPass">
            USERNAME : <input type="text" name="username" required><br>
            <input type="submit">
            <a href="/projectWebPro/Login.jsp">
                <input type="button" value="back">
            </a>
        </form>
        ${message}
    </body>
</html>
