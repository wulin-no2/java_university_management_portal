<%--
  Created by IntelliJ IDEA.
  User: wulin
  Date: 2024/3/23
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="com.assignment3.entity.UserCourse" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h2>Student List</h2>

<%
    //get parameters from request object.
    String courseIdString = request.getParameter("courseId");
    Long courseId1 = Long.parseLong(courseIdString);

    //use methods from UserService to get the enrolledCourses list and availableCourses list.
    UserService teacherService = new UserService();
    List<UserCourse> students = teacherService.getStudentsByCourseId(courseId1);
%>
<!-- display the students list for the teacher in a table-->
<table border="1">
    <thead>
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Student Phone</th>
        <th>Operations</th>
    </tr>
    </thead>
    <tbody>
    <% for(UserCourse student : students) { %>
    <tr>
        <td><%= student.getCourse().getCourseId()%></td>
        <td><%= student.getCourse().getCourseName()%></td>
        <td><%= student.getUser().getUserId() %></td>
        <td><%= student.getUser().getName() %></td>
        <td><%= student.getUser().getPhone()%></td>
        <td>
            <form action="TeacherShowAssessmentServlet" method="post">
                <input type="hidden" name="userName" value="<%= student.getUser().getUsername() %>">
                <input type="hidden" name="courseId" value="<%= student.getCourse().getCourseId() %>">
                <input type="submit" value="Add Assessment">
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<a href="teacherDashboard.jsp">Back to teacherDashboard</a>

</body>
</html>
