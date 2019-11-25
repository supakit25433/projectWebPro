<%-- 
    Document   : Enroll
    Created on : Nov 10, 2019, 3:25:47 PM
    Author     : nar-u
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>Subscribe of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Subscribe"/>
        <br>
        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>Subscribed</h5></div>
            <div class=" bg-light p-5 rounded">
                <div class="row">
                    <div class="col-sm-12 my-auto">
                        <span style="font-size: 30px;"><p class="text-center">${message}</p></span>
                    </div>
                    <c:forEach items="${enrolled}" var="e">
                        <div class="col-3 mb-4">
                            <div class="card-deck">
                                <div class="card">
                                    <a href="Subject?id=${e.subjectid}">
                                        <img class="card-img-top" src="./images/picture.jpg" alt="Card image" style="width:100%">
                                        <div class="card-body">
                                            <h4 class="card-title">${e.subjectname}</h4>
                                            <h5 class="card-text">${e.usersUserid.fullname}</h5>
                                            <p class="card-text">${e.description}</p>
                                            <h6 class="card-text">enroll?</h6>
                                        </div>
                                        <div class="overlay"></div>
                                        <div class="text">${e.subjectname}</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    
                </div>
            </div>
        </div>
    </body>
</html>
