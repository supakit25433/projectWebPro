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
        <style>
            #forget {
                position : relative;
                top:19em;
            }
            body {
                background-color: lightgray;
            }
            input {
                font-size:18px;
                padding:10px 10px 10px 5px;
                display:block;
                width:325px;
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
                font-size:14px;
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
                width:325px; 
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
            #button{
                width:161px;
                outline: none;
                text-decoration: none;
            }
            a#none{
                text-decoration: none;
                outline: none;
            }
        </style>

        <title>Forget Password</title>
    </head>
    <body>
        <div class="container-fluid" id="forget">
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-3 col-md-3">
                    <form method="post" action="ForgetPass">
                        <table>
                            <tr>
                                <td colspan="2" class="text-center" style="font-size: 45px; font-weight: bold;">
                                    <div style="margin-bottom: 20px">
                                        Send password<br>to your E-mail
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div class="group">
                                        <input type="text" name="username" autocomplete="off" required>
                                        <span class="bar"></span>
                                        <label>USERNAME</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <div style="text-align: center;">
                                            <a id="none" href="/projectWebPro/Login.jsp">
                                                <input id="button" type="button" value="Back">
                                            </a>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <input id="button" type="submit">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center" style="padding-top: 15px">
                                    <span style="color:red; font-size: 16px; font-weight: bold;">${message}</span>
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
