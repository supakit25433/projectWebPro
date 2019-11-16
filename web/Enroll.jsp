<%-- 
    Document   : Enroll
    Created on : Nov 10, 2019, 3:25:47 PM
    Author     : nar-u
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <title>Enroll of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Enrolled"/>
        <br>
        <div class="container">
            <div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded"><h5>Subjects</h5></div>
            <div class=" bg-light p-5 rounded">
                <div class="row">
                    <c:foreach items="enrolled" var="e">
                        <div class="col-3 mb-4">
                            <div class="card-deAck">
                                <div class="card">
                                    <a href="/Quiz.jsp">
                                        <img class="card-img-top" src="./images/picture.jpg" alt="Card image" style="width:100%">
                                        <div class="card-body">
                                            <h4 class="card-title">${e.getSubjectid().getSubjectName()}</h4>
                                            <h5 class="card-text">${e.getUserid()}</h5>
                                            <p class="card-text">${e.getSubjectid().getDescription()}</p>
                                        </div>
                                        <div class="overlay"></div>
                                        <div class="text">${e.getSubjectid().getSubjectName()}</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:foreach> 
                </div>
            </div>
        </div>
    </body>
</html>
