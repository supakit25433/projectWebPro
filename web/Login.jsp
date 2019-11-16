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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>QuizAnt Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>
        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->
        <style>
            .login{
                position:relative;
                top:17em;
            }
            body {
                background-color: lightgray;
            }
            input {
                font-size:18px;
                padding:10px 10px 10px 5px;
                display:block;
                width:300px;
                border:none;
                border-bottom:1px solid #757575;
                box-sizing: border-box;
            }
            input:focus{ 
                outline:none; 
            }
            .group{ 
                position:relative; 
                margin-top: 20px;
                margin-bottom:20px; 
            }
            label{
                color:#999; 
                font-size:18px;
                font-weight:normal;
                position:absolute;
                pointer-events:none;
                left:5px;
                top:10px;
                transition:0.2s ease all; 
                -moz-transition:0.2s ease all; 
                -webkit-transition:0.2s ease all;
            }
            input:focus ~ label, input:valid ~ label   {
                top:-20px;
                font-size:14px;
                color:#5264AE;
            }
            .bar{ 
                position:relative; 
                display:block; 
                width:300px; 
            }
            .bar:before, .bar:after  {
                content:'';
                height:2px; 
                width:0;
                bottom:1px; 
                position:absolute;
                background:#5264AE; 
                transition:0.2s ease all; 
                -moz-transition:0.2s ease all; 
                -webkit-transition:0.2s ease all;
            }
            .bar:before {
                left:50%;
            }
            .bar:after {
                right:50%; 
            }
            input:focus ~ .bar:before, input:focus ~ .bar:after {
                width:50%;
            }
        </style>
    </head>
    <body>
        <div class='container-fluid login'>
            <div class='row'>
                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'></div>
                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'>
                    <div class='container-fluid'>
                        <div style='text-align:center'><img src='images/amazon.jpg' style='height:5cm; width:6cm;'></div>
                    </div>
                    <div class='container-fluid text-center'>
                        <form method='post' action='Login' >
                            <hr width='50%'>
                            <div style='position:relative; left:140px'>
                                <div>
                                    <div class='group'>
                                        <input type='text' style='width:8cm' name='username' required>
                                        <span class='bar'></span>
                                        <label>Username</label>
                                    </div>
                                </div>
                                <div class='group'>
                                    <input type='password' style='width:8cm' name='password' required>
                                    <span class='bar'></span>
                                    <label>Password</label>
                                </div>
                            </div>
                            <div class='container' style='position:relative; left:138px'>
                                <input type='submit' style='width: 50%;'>
                            </div>
                            <div class='text-center'>
                                <br><h6 style='color:red;'>${message}</h6>
                            </div>
                        </form>
<!--                        <div class="container-fluid">
                            Click here to register  <a href="Register.jsp">Register</a>
                        </div>-->
                    </div>
                </div>
                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'></div>
            </div>
        </div>
    </body>
</html>
