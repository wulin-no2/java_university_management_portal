<%--
  Created by IntelliJ IDEA.
  User: wulin
  Date: 2024/3/23
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.entity.Assessment" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="com.assignment3.service.student.StudentCheckCourseMarksServlet" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<html>
<head>
    <title>Course Assessments</title>
</head>
<body>
<h2>Course Assessments</h2>
<!-- get the assessments result for the student -->
<%
    //get parameters from request object and the session attribute.
    String courseIdString = request.getParameter("courseId");
    Long courseId1 = Long.parseLong(courseIdString);
    HttpSession requestSession = request.getSession();
    String username = (String)requestSession.getAttribute("userName");

    // use a method from UserService to get assessments:
    UserService studentService = new UserService();
    List<Assessment> assessments = studentService.getAssessmentByCourseIdAndUserName(username, courseId1);
%>
<!-- display the assessments result for the student in a table-->
<table border="1">
    <thead>
    <tr>
        <th>Course Name</th>
        <th>Assessment Type</th>
        <th>Student Name</th>
        <th>Marks</th>
    </tr>
    </thead>
    <tbody>
    <% for(Assessment assessment : assessments) { %>
    <tr>
        <td><%= assessment.getCourse().getCourseName() %></td>
        <td><%= assessment.getAssessmentType() %></td>
        <td><%= assessment.getUser().getUsername() %></td>
        <td><%= assessment.getMarks()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<!-- for the student to jump to dashboard. -->
<a href="studentDashboard.jsp">Back to StudentDashboard</a>

</body>
</html>
