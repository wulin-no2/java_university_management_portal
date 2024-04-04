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
 * Servlet for teacher to get the assessment dashboard of a student for the specific course.
 * It will jump to a jsp.
 */
@WebServlet(name = "TeacherShowAssessmentServlet", value = "/TeacherShowAssessmentServlet")
public class TeacherShowAssessmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
//        System.out.println("====================post method is running====================");
        PrintWriter out = response.getWriter();
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("teacherShowAssessment.jsp");
        requestDispatcher.include(request, response);

    }
}

