<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
<h2>Admin Dashboard</h2>

<!-- Create New User Form -->
<h3>Create New User</h3>
<form action="/Assignment3_war_exploded/AdminCreateUserServlet" method="post">
    <label for="role">Role:</label>
    <select id="role" name="role">
        <option value="student">Student</option>
        <option value="teacher">Teacher</option>
    </select><br/>
    <label for="userName">Username:</label>
    <input type="text" id="userName" name="userName" required><br/>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br/>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br/>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br/>
    <input type="submit" value="Create User">
</form>

<!-- Create New Course Form -->
<h3>Create New Course</h3>
<form action="/Assignment3_war_exploded/AdminCreateCourseServlet" method="post">
    <label for="courseName">Course Name:</label>
    <input type="text" id="courseName" name="courseName" required><br/>
    <label for="semester">Semester:</label>
    <label for="semester">Semester:</label>
    <select id="semester" name="semester" required>
        <option value="1">Semester 1</option>
        <option value="2">Semester 2</option>
    </select><br/>
    <input type="submit" value="Create Course">
    <br>
    <!-- for the admin to log out -->
    <a href="/Assignment3_war_exploded/logout">log out</a>
</form>
</body>
</html>

