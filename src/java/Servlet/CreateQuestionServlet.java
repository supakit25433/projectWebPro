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
public class CreateQuestionServlet extends HttpServlet {
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
        SubjectController sc = new SubjectController(emf, utx);
        int subjectid = Integer.valueOf(request.getParameter("subjectid"));
        Subjects subject = sc.findByID(subjectid);
        List<Quizes> quizlist = sc.findAllQuizesInSubject(subject);
        request.setAttribute("quiz", quizlist);
        if (request.getParameter("questionname").trim()==null
                ||request.getParameter("questionname").trim().isEmpty()) {
            request.setAttribute("message", "Please fill in the Question");
            getServletContext().getRequestDispatcher("/CreateQuestion.jsp").forward(request, response);   
        } else {
            QuizController qc = new QuizController(emf, utx);
            QuestionController  qtc = new QuestionController(emf, utx);
            int quizid = Integer.valueOf(request.getParameter("quizid"));
            Quizes quiz = qc.findByID(quizid);
            String questionName = request.getParameter("questionname");
            String type = request.getParameter("type");
            String description = request.getParameter("description");
            if (request.getParameter("description").trim()==null ||
                    request.getParameter("description").trim().isEmpty()) {
                Questions question = new Questions(questionName, type, quiz);
                qtc.addQuestion(question);
                request.setAttribute("message", "Question has been added");
                getServletContext().getRequestDispatcher("/CreateQuestion.jsp").forward(request, response);
            } else {
                Questions question = new Questions(questionName, type, description, quiz);
                qtc.addQuestion(question);
                request.setAttribute("message", "Question has been added");
                getServletContext().getRequestDispatcher("/CreateQuestion.jsp").forward(request, response);
            }
        }       
        getServletContext().getRequestDispatcher("/CreateQuestion.jsp").forward(request, response);    
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
