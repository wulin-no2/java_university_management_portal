package com.assignment3.service.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for the admin to create a new user.
 */
@WebServlet(name = "AdminCreateUserServlet", value = "/AdminCreateUserServlet")
public class AdminCreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("==================== Admin create user post method is running====================");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //get parameters from request object.
        String userName =
                request.getParameter("userName").trim();
        String password =
                request.getParameter("password").trim();
        String name =
                request.getParameter("name").trim();
        String phone =
                request.getParameter("phone").trim();
        String role =
                request.getParameter("role").trim();

        //check for null and empty values.
        if(userName == null || userName.equals("")
                || password == null || password.equals("")
                || name == null || name.equals("")
                || phone == null || phone.equals("")
                || role == null || role.equals("")
        ){
            // display the error message and let the admin jump to dashboard.
            out.print("Please enter all the information about the user. " +
                    ". <br/><br/>");
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("adminDashboard.jsp");
            requestDispatcher.include(request, response);
        }
        //Check for valid username and password;
        else{
            AdminService adminService = new AdminService();
            adminService.addUser(userName, password,name,phone,role);
            // display the successful result and let the admin jump to dashboard.
            out.println("<html><body>");
            out.println("<h1>Successfully creat the user: </h1>");
            out.println("<h2> username: " + userName + "</h2>");
            out.println("<h2> name: " + name + "</h2>");
            out.println("<h2> phone: " + phone + "</h2>");
            out.println("<h2> role: " + role + "</h2>");
            out.println("<a href=\"adminDashboard.jsp\">Back to Dashboard</a>");

            out.println("</body></html>");
        }
    }
}

