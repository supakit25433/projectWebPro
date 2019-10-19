<%-- 
    Document   : login
    Created on : Sep 26, 2019, 11:39:25 AM
    Author     : nar-u
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QuizAnt Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>
        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->
        <style>
            body {
                background-color: lightgray;
            }
        </style>
    </head>
    <body>
        <br><br><br><br><br><br><br><br>
        <div class='container-fluid'>
            <div class='row'>
                <div class='col-sm-5 col-md-5 col-lg-5 col-xl-5'></div>
                <div class='col-sm-3 col-md-3 col-lg-3 col-xl-3'>
                    <div class='container'>
                        <div style='text-align:center'><img src='images/amazon.jpg' style='height:5cm; width:6cm;'></div>
                    </div>
                    <div class='container-fluid'>
                        <form method='post' action='Login' >
                        <br>Username&nbsp; : <input type='text' style='width:6.4cm' name='userName' required><br><hr>
                        Password &nbsp; : <input type='password' style='width:6.4cm' name='password'><br>
                        <div class='container text-center'>
                            <br><input type='submit'>
                        </div>
                        <div class='text-center'>
                            <br><h6 style='color:red;'>${message}</h6>
                        </div>
                        </form>
                        <div class="container-fluid">
                             Click here to register  <a href="Register.jsp">Register</a>
                        </div>
                    </div>
                </div>
                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'></div>
            </div>
        </div>
    </body>
</html>
