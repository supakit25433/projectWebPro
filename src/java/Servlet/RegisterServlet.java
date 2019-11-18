/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Register.RegisterController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.UsersJpaController;
import jpa.exceptions.RollbackFailureException;
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
            throws ServletException, IOException, RollbackFailureException, Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String fullname = request.getParameter("fullname");
        String type = request.getParameter("type");
        String message = null;

        if (password.isEmpty()
                || confirmpassword.isEmpty()
                || username.isEmpty()
                || fullname.isEmpty()
                || type.isEmpty()) {
            message = "May be some input is null";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
        } else {
            RegisterController rc = new RegisterController();
//            if (username != ) {
                if (password.equals(confirmpassword)) {
                    rc.register(username, password, fullname, type);
                    message = "Register Successfull";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
                } else {
                    message = "password not macth confirm password";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
                }
//            } else {
//                message = "Username have same username in database";
//                request.setAttribute("message", message);
//                getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
//            }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
