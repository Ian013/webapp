package com.epam.training.application.controller;


import com.epam.training.application.dao.jdbc.StudentDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(WelcomeServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentDaoImpl s = new StudentDaoImpl();

        request.setAttribute("list",s.getStudents());
        request.getRequestDispatcher("/WEB-INF/view/welcome.jsp").forward(request, response);

    }

}
