package com.assignment3.service.teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for teacher to get the students list for the specific course.
 * jump to a jsp.
 */
@WebServlet(name = "TeacherCheckStudentServlet", value = "/TeacherCheckStudentServlet")
public class TeacherCheckStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("====================post method is running====================");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("teacherCheckStudentsList.jsp");
        requestDispatcher.include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    }
}

