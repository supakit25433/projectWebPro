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
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>${user.username}'s Profile</title>
        <style>
            #profile{
                position:relative;
                top:15em
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Profile"/>
        <!--        <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-3 col-md-3"></div>
                        <div class="col-sm-3 col-md-3 text-center" style="font-size:36px; background-color: lightgray">Your Profile</div>
                        <div class="col-sm-2 col-md-2 text-center" style="background-color: lightgray">
                            <div style="position: relative; top:8px">
                                <a>
                                    <button class="btn btn-primary">EDIT PROFILE</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="font-size: 24px">
                        <div class="col-sm-3 col-md-3"></div>
                        <div class="col-sm-5 col-md-5" style="background-color: lightgray">USERNAME : ${user.username}<br></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <div class="row" style="font-size: 24px">
                        <div class="col-sm-3 col-md-3"></div>
                        <div class="col-sm-5 col-md-5" style="background-color: lightgray">E-MAIL : ${user.emailaddress}<br></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <div class="row" style="font-size: 24px">
                        <div class="col-sm-3 col-md-3"></div>
                        <div class="col-sm-5 col-md-5" style="background-color: lightgray">FULL NAME : ${user.fullname}<br></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                </div>-->
        <div class="container-fluid" id="profile">
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-4 col-md-4">
                    <table class="table table-borderless" style="width: 500px">
                        <tr>
                            <td colspan="6" style="font-size: 30px; font-weight: bold;">
                                Your profile
                            </td>
                            <td colspan="2" style="padding-top:22px;">
                                <a href="/projectWebPro/EditProfile.jsp">
                                    <span class="btn btn-primary">edit profile</span>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">USERNAME</td>
                            <td colspan="4">: ${username}</td>
                        </tr>
                        <tr>
                            <td colspan="2">E-MAIL</td>
                            <td colspan="4">: ${email}</td>
                        </tr>
                        <tr>
                            <td colspan="2">FULLNAME</td>
                            <td colspan="4">: ${fullname}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-3 col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-4 col-md-4">
                    <a href="Enroll"><button class="btn btn-primary">Subscribed Subjects</button></a> &nbsp;
                    <a href="ManageSubjects"><button class="btn btn-primary">Manage Subject</button></a>
                </div>
                <div class="col-sm-3 col-md-3"></div>
            </div>
        </div>
    </body>
</html>
