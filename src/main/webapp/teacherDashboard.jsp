<%@ page import="com.assignment3.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: wulin
  Date: 2024/3/23
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Dashboard</title>
</head>
<body>
<h2>Teacher Dashboard</h2>

<!-- display the dashboard for the teacher -->
<%-- userName is stored in session after login --%>
<% String userName = (String) session.getAttribute("userName"); %>
<%-- use methods from UserService to get the enrolledCourses list and availableCourses list. --%>
<% UserService teacherService = new UserService(); %>
<% List<Course> enrolledCourses = teacherService.getEnrolledCoursesByUserName(userName); %>
<% List<Course> availableCourses = teacherService.getAvailableCoursesByUserName(userName); %>

<% if(enrolledCourses.isEmpty()){ %>
<p>You are not assigned in any courses.</p>
<% } else { %>
<!-- display the assigned courses for the teacher in a table-->
<h3>Assigned Courses</h3>
<ul>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Semester</th>
            <th>Operations</th>
        </tr>
        <% for(Course course : enrolledCourses){ %>
        <tr>
            <td><%= course.getCourseId() %></td>
            <td><%= course.getCourseName() %></td>
            <td>Semester <%= course.getSemester() %></td>
            <td>
                <form action="TeacherCheckStudentServlet" method="get">
                    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                    <input type="submit" value="Student List">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
        <% } %>
    <!-- display the available courses for the teacher in a table-->
<h3>Available Courses</h3>
<p>Select a course to register:</p>
<table border="1">
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Semester</th>
        <th>Operation</th>
    </tr>
    <% for(Course course : availableCourses){ %>
    <tr>
        <td><%= course.getCourseId() %></td>
        <td><%= course.getCourseName() %></td>
        <td>Semester <%= course.getSemester() %></td>
        <td>
            <form action="UserRegisterCourseServlet" method="post">
                <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                <input type="submit" value="Register">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<br>
    <!-- for the teacher to log out -->
    <a href="/Assignment3_war_exploded/logout">log out</a>
</body>
</html>
