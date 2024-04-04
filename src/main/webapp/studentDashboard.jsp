<%@ page import="com.assignment3.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
</head>
<body>
<h2>Student Dashboard</h2>
<!-- display the dashboard for the student -->
<%-- userName is stored in session after login --%>
<% String userName = (String) session.getAttribute("userName"); %>
<%-- use methods from UserService to get the enrolledCourses list and availableCourses list. --%>
<% UserService studentService = new UserService(); %>
<% List<Course> enrolledCourses = studentService.getEnrolledCoursesByUserName(userName); %>
<% List<Course> availableCourses = studentService.getAvailableCoursesByUserName(userName); %>

<% if(enrolledCourses.isEmpty()){ %>
<p>You are not enrolled in any courses.</p>
<% } else { %>
<!-- display the enrolled courses for the student in a table-->
<h3>Enrolled Courses</h3>
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
                <form action="StudentCheckCourseMarksServlet" method="get">
                    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                    <input type="submit" value="Check Results">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
<% } %>
<!-- display the available courses for the student in a table-->
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
<!-- for the student to log out -->
<a href="/Assignment3_war_exploded/logout">log out</a>
</body>
</html>

