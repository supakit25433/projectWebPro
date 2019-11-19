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
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="ForgetPass">
            USERNAME : <input type="text" name="username" required><br>
            E-MAIL : <input type="text" name="email" required>
            <input type="submit">
            <a href="/projectWebPro/Login.jsp">
                <input type="button" value="back">
            </a>
        </form>
        ${message}
    </body>
</html>
