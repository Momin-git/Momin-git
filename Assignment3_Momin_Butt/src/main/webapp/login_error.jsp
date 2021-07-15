<%-- 
    Document   : login_error
    Created on : Apr. 9, 2021, 10:05:43 a.m.
    Author     : excus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error - Try Again</title>
    </head>
    <body>
        <h1>Login Error - Try Again</h1>
        <form action="j_security_check" method="POST">

            <p>Username: <input type="text" name="j_username" value="" /></p> <br> <br>
            <p>Password: <input type="password" name="j_password" value="" /></p> <br> <br>
            <input type="submit" value="login" /> <br>
            <p> For testing <br>
                use admin/admin or guest/guest </p>
            <a href="">Go Back</a> <br>
            <a href="">Logout</a>
        </form>
    </body>
</html>
