<%-- 
    Document   : EditProfile
    Created on : Nov 24, 2019, 1:54:58 PM
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
        <title>EDIT PROFILE</title>
        <style>
            #profile{
                position:relative;
                top:10em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Edit Profile"/>
        <div class="container-fluid" id="profile">
            <div class="row">
                <div class="col-sm-4 col-md-4"></div>
                <div class="col-sm-4 col-md-4">
                    <form method="post" action="EditProfile">
                        <table class="table table-borderless" style="width: 500px">
                            <tr>
                                <td colspan="6" style="font-size: 30px">
                                    your profile
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">USERNAME</td>
                                <td colspan="4">: <input type="text" name="username" value="${user.username}" readonly></td>
                            </tr>
                            <tr>
                                <td colspan="2">E-MAIL</td>
                                <td colspan="4">: <input type="text" name="email" value="${user.emailaddress}" required></td>
                            </tr>
                            <tr>
                                <td colspan="2">FULLNAME</td>
                                <td colspan="4">: <input type="text" name="fullname" value="${user.fullname}" required> </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="col-sm-4 col-md-4"></div>
            </div>
        </div>
    </body>
</html>
