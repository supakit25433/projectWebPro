/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.UsersJpaController;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class ChangePasswordServlet extends HttpServlet {

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
            throws ServletException, IOException, NonexistentEntityException, RollbackFailureException, Exception {
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String confirmnewpass = request.getParameter("confirmnewpass");

        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");

        if (oldpass.isEmpty() || oldpass.trim().isEmpty()
                || newpass.isEmpty() || newpass.trim().isEmpty()
                || confirmnewpass.isEmpty() || confirmnewpass.trim().isEmpty()) {
            request.setAttribute("message", "Please enter every box");
            getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
        } else {
            if (user.getPassword().equals(oldpass)) {
                if (newpass.equals(confirmnewpass)) {
                    UsersJpaController ujc = new UsersJpaController(utx, emf);
                    Users newuser = user;
                    newuser.setPassword(newpass);
                    ujc.edit(newuser);
                    session.setAttribute("user", newuser);
                    request.setAttribute("message", "Change password complete");
                    getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Please enter confirm password again");
                    getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Please enter correct old Password");
                getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
