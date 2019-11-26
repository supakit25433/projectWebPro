/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.SubjectController;
import Model.controller.UsersSubscriptionController;
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
import jpa.UsersSubscriptionJpaController;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Subjects;
import jpaClasses.Users;
import jpaClasses.UsersSubscription;

/**
 *
 * @author nar-u
 */
public class SubscribeServlet extends HttpServlet {

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
        int subjectid = Integer.valueOf(request.getParameter("subjectid"));
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        SubjectController sc = new SubjectController(emf, utx);
        Subjects subject = sc.findByID(subjectid);
        UsersSubscriptionController usc = new UsersSubscriptionController(emf, utx);
        UsersSubscriptionJpaController usjc = new UsersSubscriptionJpaController(utx, emf);
        UsersSubscription userSub = new UsersSubscription(subject, user);
        if (usc.findBySubjectIDandUser(subject,user) == null){
//            usjc.destroy(usc.findBySubjectIDandUser(subject,user).getSubscriptionid());
            usjc.create(userSub);
            response.sendRedirect("/projectWebPro/Subject?id=" + subjectid);
        } else {
//            usjc.create(userSub);
            usjc.destroy(usc.findBySubjectIDandUser(subject,user).getSubscriptionid());
            response.sendRedirect("/projectWebPro/Subject?id=" + subjectid);
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
            Logger.getLogger(SubscribeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SubscribeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
