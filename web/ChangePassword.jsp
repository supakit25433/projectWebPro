<%-- 
    Document   : ChangePassword
    Created on : Nov 27, 2019, 12:17:10 PM
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
            #changepassword{
                position:relative;
                top: 14em;
            }
        </style>
        
        <link rel="stylesheet" href="./css/style.css">
        
        <title>Change password ${user.username}</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/PageHeader.jsp?page=Change Password"/>
        <div class="container-fluid" id="changepassword">
            <div class="row">
                <div class="col-sm-5 col-md-5"></div>
                <div class="col-sm-4 col-md-4">
                    <form method="post" action="ChangePassword">
                        <table class="table table-borderless" style="width:500px">
                            <tr>
                                <td colspan="6" style="font-size: 30px; font-weight: bold;">
                                    Change Password
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">OLD PASSWORD</td>
                                <td colspan="4"><input type="text" name="oldpass" required autocomplete="off"></td>
                            </tr>
                            <tr>
                                <td colspan="2">NEW PASSWORD</td>
                                <td colspan="4"><input type="text" name="newpass" required autocomplete="off"></td>
                            </tr>
                             <tr>
                                <td colspan="2">CONFIRM NEW PASSWORD</td>
                                <td colspan="4"><input type="text" name="confirmnewpass" required autocomplete="off"></td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="Profile"><span class="btn btn-secondary">Back</span></a>
                                    <input type="submit" class="btn btn-primary">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    ${message}
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="col-sm-3 col-md-3"></div>
            </div>
        </div>
    </body>
</html>
