/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class RegisterServlet extends HttpServlet {

    @PersistenceUnit(unitName = "WebProjectInt303PU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        if (username.trim().isEmpty() || password.trim().isEmpty()
                || confirmpassword.trim().isEmpty()
                || fullname.trim().isEmpty()
                || email.trim().isEmpty()) {
            request.setAttribute("message", "Please fill in every box");
            getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
        } else {
            if (password.trim().length() <= 6) {
                request.setAttribute("message", "Password should have more than 6 character");
                getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
            } else {
                if (!password.equals(confirmpassword)) {
                    request.setAttribute("message", "Password and Confirm Password not match");
                    getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
                } else {
                    UserController uc = new UserController(emf, utx);
                    if (uc.findByUserName(username) != null) {
                        request.setAttribute("message", "username is not available");
                        getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
                    } else {
                        Users user = new Users(username, password, fullname, email);
                        uc.createUser(user);
                        request.setAttribute("message", "register successfully");
                        getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
