<%-- 
    Document   : add
    Created on : Apr. 9, 2021, 9:49:00 a.m.
    Author     : excus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Worker</title>
    </head>
    <body>
        <h1>Add a worker</h1>
        <form action="add" method="POST">
        <h3>${message}</h3>
        
        ID:     <input type="text" name="id" value="" /> <br> <br>
        Full Name:      <input type="text" name="fullname" value="" /> <br> <br>
        Salary:         <input type="text" name="salary" value="" /> <br> <br>
        <input type="submit" value="Add to DB" />
        <a href="index.html">Go Back</a>
        </form>
        </body>
</html>
