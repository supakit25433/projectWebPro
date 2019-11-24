<%-- 
    Document   : Result
    Created on : Nov 19, 2019, 10:05:07 PM
    Author     : surface
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
        <title>Result</title>
        <style>
            #result{
                position:relative;
                top:20em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Result"/>
        
        <div class="container-fluid" id="result">
            <div class="row">
                <div class="col-sm-4 col-md-4"></div>
                <div class="col-sm-4 col-md-4 text-center">
                    <span style="font-weight: bold; font-size: 50px;">QUIZ ENDED !!!</span>
                </div>
                <div class="col-sm-4 col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-4"></div>
                <div class="col-sm-4 col-md-4 text-center">
                    <span style="font-size: 40px">
                        Your score is <span style="color:red; font-size: 45px; font-weight: bold;">${test}</span>
                    </span>
                </div>
                <div class="col-sm-4 col-md-4"></div>
            </div>
        </div>
    </body>
</html>
