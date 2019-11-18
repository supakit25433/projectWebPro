<%-- 
    Document   : Register.jsp
    Created on : Oct 17, 2019, 9:12:54 AM
    Author     : nar-u
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-3 col-md-3">
                    <form method="post" action="Register">
                        <table>
                            <tr>
                                <td>USERNAME : </td>
                                <td><input type="text" name="username" autocomplete="off" required></td>
                            </tr>
                            <tr>
                                <td>PASSWORD : </td>
                                <td><input type="text" name="password" autocomplete="off" required></td>
                            </tr>
                            <tr>
                                <td>COMFIRM PASSWORD : </td>
                                <td><input type="text" name="confirmpassword" autocomplete="off" required></td>
                            </tr>
                            <tr>
                                <td>FULLNAME : </td>
                                <td><input type="text" name="fullname" autocomplete="off" required></td>
                            </tr>
                            <tr>
                                <td>TYPE : </td>
                                <td><input type="text" name="type" autocomplete="off" required></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit"></td>
                            </tr>
                        </table>
                    </form>
                    <div class="row">
                        <div style="padding-left: 30px">
                            <span style="color:red;">${message}</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-md-4"></div>
            </div>
        </div>
    </body>
</html>
