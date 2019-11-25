/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.QuizController;
import Model.controller.SubjectController;
import Model.controller.UserController;
import Model.controller.UsersSubscriptionController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.QuizesJpaController;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;
import jpaClasses.UsersSubscription;

/**
 *
 * @author surface
 */
public class SubjectServlet extends HttpServlet {

    @PersistenceUnit(unitName="WebProjectInt303PU")
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
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        SubjectController sc = new SubjectController(emf, utx);
        Subjects s = sc.findByID(id);
        List<Quizes> quizzesList = sc.findAllQuizesInSubject(s);
        
        Users subid = (Users)s.getUsersUserid();
        
        ArrayList<Quizes> quizzesListReverse = new ArrayList<>();
        for (int i = quizzesList.size() - 1; i >= 0 ; i--) {
            quizzesListReverse.add(quizzesList.get(i));
        }
        
        int subscriber = sc.countSubjectSubscriber(s);
        
        /*HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        UsersSubscriptionController usc = new UsersSubscriptionController(emf, utx);
        List<UsersSubscription> us = usc.findAllUsersSubscriptions();
        for (int i = 0; i < us.size(); i++) {
            if (us.get(i).getUsersUserid().getUserid()) {
                
            }
        }*/
        
        request.setAttribute("subscriber", subscriber);
        request.setAttribute("subject", s);
        request.setAttribute("userid", subid.getUserid());
        request.setAttribute("quizzes", quizzesListReverse);
        getServletContext().getRequestDispatcher("/Subject.jsp").forward(request, response);
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
