/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.QuestionController;
import Model.controller.QuizController;
import Model.controller.SubjectController;
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
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class CreateChoiceServlet extends HttpServlet {

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
        SubjectController sc = new SubjectController(emf, utx);
        int subjectid = Integer.valueOf(request.getParameter("subjectid"));
        Subjects subject = sc.findByID(subjectid);
        ArrayList<Questions> questionlist = new ArrayList<>();
        List<Quizes> quizlist = sc.findAllQuizesInSubject(subject);
        for (int i = 0; i < quizlist.size(); i++) {
            QuestionController qc = new QuestionController(emf, utx);
            List<Questions> ques = qc.findAllQuestionByQuiz(quizlist.get(i));
            for (int j = 0; j < ques.size(); j++) {
                questionlist.add(ques.get(j));
            }
        }
        String choice = request.getParameter("choicename");
        String questionid = request.getParameter("questionid");
        if (request.getParameter("point")==null) {
            request.setAttribute("questions", questionlist);
            getServletContext().getRequestDispatcher("/CreateChoice.jsp").forward(request, response);
        } else {
            int point = Integer.valueOf(request.getParameter("point"));
            if (choice.trim().isEmpty() || point < 0 || questionid.trim().isEmpty()) {
                request.setAttribute("message", "Please enter every box!!!");
                request.setAttribute("questions", questionlist);
                getServletContext().getRequestDispatcher("/CreateChoice.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession(false);
                Users user = (Users) session.getAttribute("user");
                if(user==null){
                    request.setAttribute("message", "Please log-in again!!!");
                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
                } else {
                    
                }
            }
            request.setAttribute("questions", questionlist);
            getServletContext().getRequestDispatcher("/CreateChoice.jsp").forward(request, response);
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
