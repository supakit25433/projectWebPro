<%-- 
    Document   : History
    Created on : Nov 10, 2019, 3:16:54 PM
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

        <title>History of ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=History"/>
        <br>
        <div class="container">
            <!--<div class="bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded">
                <h5>-->
                    <span style="font-size:40px; font-weight: bold;">History</span>
                    <br>
                    <br>
                <!--</h5>
            </div>-->
            <table class="table table-hover">
                <thead>
                    <tr style="background-color: whitesmoke;">
                        <th>
                            <!--DATE-->
                        </th>
                        <th>
                            QUIZ NAME
                        </th>
                        <th>
                            DESCRIPTION
                        </th>
                        <th>
                            SCORE
                        </th>
                        <th>
                            <!--VIEW ANSWER-->
                        </th>
                        <th>
                            DO AGAIN
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${quizzes}" var="q" varStatus="index">
                        <tr>
                            <td>
                                <!--DATE-->
                            </td>
                            <td>
                                ${q.quizesQuizid.quizname}
                            </td>
                            <td>
                                ${q.quizesQuizid.description}
                            </td>
                            <td>
                                <span style="color:LimeGreen; font-size: 20px; font-weight: bold;">${q.totalscore}</span>
                            </td>
                            <td>
                                <!--VIEW ANSWER-->
                            </td>
                            <td>
                                <a href="Quiz?quizid=${q.quizesQuizid.quizid}">
                                    <button>DO AGAIN</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="col-sm-12 my-auto">
                <span style="font-size: 30px;"><p class="text-center">${message}</p></span>
            </div>
            <%--<c:forEach items="${quizzes}" var="q" varStatus="index">
                <c:choose>
                    <c:when test="${index.index%2==0}">
                        <div class="list-group">
                            <a href="Quiz?quizid=${q.quizesQuizid.quizid}" class="list-group-item list-group-item-action">${q.quizesQuizid.quizname}<span class="badge badge-success float-right">${q.totalscore}</span></a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="list-group">
                            <a href="Quiz?quizid=${q.quizesQuizid.quizid}" class="list-group-item list-group-item-action list-group-item-secondary">${q.quizesQuizid.quizname}<span class="badge badge-success float-right">${q.totalscore}</span></a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>--%>
        </div>
    </body>
</html>
