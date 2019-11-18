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

        <style>
            #register {
                position : relative;
                top:14em;
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
                margin-top: 3px;
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
        <div class="container-fluid" id="register">
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-3 col-md-3">
                    <form method="post" action="Register">
                        <table>
                            <tr>
                                <td class="text-center" style="font-size: 50px; font-weight: bold; ">
                                    <div style="margin-bottom: 20px">
                                        REGISTER
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="group">
                                        <input type="text" name="username" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>USERNAME</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="group">
                                        <input type="text" name="password" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>PASSWORD</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="group">
                                        <input type="text" name="confirmpassword" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>CONFIRM PASSWORD</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="group">
                                        <input type="text" name="fullname" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>FULLNAME</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="group">
                                        <input type="text" name="type" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>TYPE</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit">
                                </td>
                            </tr>
                        </table>
                    </form>
                    <div class="row">
                        <div style="padding-left: 52px">
                            <span style="color:red;">${message}</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-md-4"></div>
            </div>
        </div>
    </body>
</html>
