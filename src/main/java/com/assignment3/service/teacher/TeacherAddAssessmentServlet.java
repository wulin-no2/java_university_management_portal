package com.assignment3.service.teacher;

import com.assignment3.service.userService.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet for teacher to update the assessments of a student for the specific course.
 * jump to a jsp.
 */
@WebServlet(name = "TeacherAddAssessmentServlet", value = "/TeacherAddAssessmentServlet")
public class TeacherAddAssessmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("====================post method is running====================");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //get parameters from request object.
        String assignmentMark =
                request.getParameter("assignmentMark").trim();
        String quizMark =
                request.getParameter("quizMark").trim();
        String examMark =
                request.getParameter("examMark").trim();
        String studentName =
                request.getParameter("studentName").trim();
        String courseId1 =
                request.getParameter("courseId1").trim();
        Long courseId = Long.parseLong(courseId1);
        //check if marks are numbers.
        try {
            // Attempt to parse the input as an integer
            int aMark= Integer.parseInt(assignmentMark);
            int qMark = Integer.parseInt(quizMark);
            int eMark = Integer.parseInt(examMark);
            // update the assessments by a method in UserService.
            UserService userService = new UserService();
            userService.updateStudentAssessment(studentName,courseId,aMark,qMark,eMark);

            //show the result and jump to the teacher dashboard.
            out.println("<html><body>");
            out.println("<h1>Successfully update the assessment result. </h1>");
            out.println("<a href=\"teacherDashboard.jsp\">Back to teacher Dashboard</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            // If parsing fails, the input is not a valid integer
            out.println("The marks are not valid integers.");
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("teacherShowAssessment.jsp");
            requestDispatcher.include(request, response);
        }

    }
}
