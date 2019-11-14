<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header style="position: fixed;">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">QuizAnt</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Subjects.jsp">Subjects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Enroll.jsp">Enrolled</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="History.jsp">History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Feedback.jsp">Feedback/Report</a>
                </li>
            </ul>
        </div>
        <div>
            <span style="font-weight: bold;">Current page</span> : ${param.page} &nbsp; &nbsp; 
            <span style="font-weight: bold;">Username</span> : ${user.username} &nbsp; &nbsp;
            <a href="Logout" style="color:black; font-weight: bold;">Logout</a>
        </div>
    </nav>
</header>
