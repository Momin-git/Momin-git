
<%-- 
    Document   : search
    Created on : Apr. 9, 2021, 10:00:27 a.m.
    Author     : excus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <form action="search" method="POST">

            <h1>Search For Workers      <a href="">Logout</a></h1>
            Min Salary:  <input type="text" name="minsal" value="" /> <br> <br>
            Max Salary:  <input type="text" name="maxsal" value="" /> <br> <br>
            <input type="submit" value="Search Workers" /> <br> 
            <p> Search Results </p>

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Salary</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="a" items="${list}">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.fullname}</td>
                            <td>${a.salary}</td>

                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <h3> Total Workers: ${totalworkers} </h3>
            <h3> Average Salary: ${averagesalary} </h3>
            <h3> Top Worker: ${topworker} </h3>
            <br>
            <a href="index.html">Go Back</a>
        </form>
    </body>
</html>
