/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.QuizController;
import Model.controller.SubjectController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import jpa.QuizesJpaController;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class CreateQuizServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String quizname = request.getParameter("quizname");
        String description = request.getParameter("description");
        SubjectController sc = new SubjectController(emf, utx);
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        List<Subjects> subjects = sc.findAllSubjectByUserID(user);

        if (request.getParameter("subjectid") == null || request.getParameter("subjectid").isEmpty()) {
            request.setAttribute("subjects", subjects);
            getServletContext().getRequestDispatcher("/CreateQuiz.jsp").forward(request, response);
        } else {
            if (quizname.trim().isEmpty() || description.trim().isEmpty()) {
                request.setAttribute("message", "Please enter every box");
                getServletContext().getRequestDispatcher("/CreateQuiz.jsp").forward(request, response);
            } else {
                if (user == null) {
                    request.setAttribute("message", "Please log-in again!!");
                    getServletContext().getRequestDispatcher("/CreateQuiz.jsp").forward(request, response);
                } else {
                    QuizController qc = new QuizController(emf, utx);
                    if (qc.findByQuizName(quizname) == null) {
                        QuizesJpaController qjc = new QuizesJpaController(utx, emf);
                        int subjectid = Integer.valueOf(request.getParameter("subjectid"));
                        Subjects subject = sc.findByID(subjectid);
                        Quizes quiz = new Quizes(quizname, description, subject);
                        qjc.create(quiz);
                        request.setAttribute("message", "Add Quiz Completed.");
                        getServletContext().getRequestDispatcher("/CreateQuiz.jsp").forward(request, response);
                    } else {
                        request.setAttribute("message", "Quiz name is not avaliable");
                        getServletContext().getRequestDispatcher("/CreateQuiz.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
