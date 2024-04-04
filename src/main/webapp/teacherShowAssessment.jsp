<%@ page import="com.assignment3.entity.Assessment" %>
<%@ page import="com.assignment3.service.userService.UserService" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Assessment</title>
</head>
<body>
<h2>Assessment of the student</h2>

<!-- display the assessments in a table -->
<%
    //get parameters from request object.
    String courseIdString = request.getParameter("courseId");
    Long courseId1 = Long.parseLong(courseIdString);
    String studentName = request.getParameter("userName");

    // use a method from UserService to get assessments:
    UserService userService = new UserService();
    List<Assessment> assessments = userService.getAssessmentByCourseIdAndUserName(studentName, courseId1);
%>

<table border="1">
    <thead>
    <tr><th>Course ID</th>
        <th>Course Name</th>
        <th>Assessment Type</th>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Marks</th>
    </tr>
    </thead>
    <tbody>
    <% for(Assessment assessment : assessments) { %>
    <tr>
        <td><%= assessment.getCourse().getCourseId() %></td>
        <td><%= assessment.getCourse().getCourseName() %></td>
        <td><%= assessment.getAssessmentType() %></td>
        <td><%= assessment.getUser().getUserId() %></td>
        <td><%= assessment.getUser().getUsername() %></td>
        <td><%= assessment.getMarks()%></td>
    </tr>
    <% } %>
    </tbody>
</table>

<!-- Update Assessment Form -->
<h2>Update Student Assessment</h2>
<form action="/Assignment3_war_exploded/TeacherAddAssessmentServlet" method="post">
    <label for="assignmentMark">Assignment mark:</label>
    <input type="text" id="assignmentMark" name="assignmentMark" required><br/>
    <label for="quizMark">Quiz mark:</label>
    <input type="text" id="quizMark" name="quizMark" required><br/>
    <label for="examMark">Final exam mark:</label>
    <input type="text" id="examMark" name="examMark" required><br/>
    <input type="hidden" name="studentName" value="<%=studentName%>">
    <input type="hidden" name="courseId1" value="<%=courseId1%>">
        <br/>
    <input type="submit" value="Update Assessment">
</form>
<br>
<!-- for the teacher to jump to dashboard. -->
<a href="teacherDashboard.jsp">Back to teacher dashboard</a>
</body>
</html>
