<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="Index">QuizAnt</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="Index">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Subjects">Subjects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Enroll">Enrolled</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="History">History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Feedback">Feedback/Report</a>
                </li>
            </ul>
        </div>
        <div class="container-fluid" style="width:360px;">
            <span style="font-weight: bold;">Current page</span>: ${param.page}
            <!--<span style="font-weight: bold;">Name</span> : ${user.fullname} &nbsp; &nbsp;-->
            <!--<a href="Logout" style="color:red; font-weight: bold; font-size: 20px; text-shadow:2px 2px red"><button>Logout</button></a>-->
            <div>
                <div class="dropdown">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                        ${user.fullname}
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="Profile">Profile</a>
                        <a class="dropdown-item" href="Logout">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>
