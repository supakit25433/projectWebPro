/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.ChoiceController;
import Model.controller.QuestionController;
import Model.controller.QuizController;
import Model.controller.QuizRecordController;
import Model.controller.SubjectController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import jpa.QuizrecordJpaController;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Choices;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Quizrecord;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author surface
 */
public class QuizServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        QuizController qc = new QuizController(emf, utx);
        int quizID = Integer.parseInt(request.getParameter("quizid"));
        Quizes q = qc.findByID(quizID);
        List<Questions> questionsList = qc.findAllQuestionsInQuiz(q);

        ArrayList<List<Choices>> allChoices = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            QuestionController qtc = new QuestionController(emf, utx);
            int questionID = questionsList.get(i).getQuestionid();
            Questions qt = qtc.findByID(questionID);
            List<Choices> choicesList = qt.getChoicesList();                    //ชอยส์ทั้งหมดของคำถามนั้นๆ
            allChoices.add(choicesList);
        }

        ArrayList<String> allPoints = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            QuestionController qtc = new QuestionController(emf, utx);
            int point = qtc.findMostScoreChoiceInEachQuestion(questionsList.get(i));
            allPoints.add(String.valueOf(point));
        }

        request.setAttribute("points", allPoints);
        request.setAttribute("quiz", q);
        request.setAttribute("questions", questionsList);
        request.setAttribute("choices", allChoices);
        getServletContext().getRequestDispatcher("/Quiz.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        int count = 0;
        ArrayList<String> answers = new ArrayList<>();

        QuizController qc = new QuizController(emf, utx);
        int quizID = Integer.parseInt(request.getParameter("quizid"));
        Quizes q = qc.findByID(quizID);
        List<Questions> questionsList = qc.findAllQuestionsInQuiz(q);
        for (int i = 0; i < questionsList.size(); i++) {;
            String order = String.valueOf(i);
            int value = Integer.parseInt(request.getParameter(order));
            String textAreaValue = request.getParameter("TEXT" + order);
            answers.add(textAreaValue);
            count = count + value;
        }

        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        QuizRecordController qrc = new QuizRecordController(emf, utx);
        Quizrecord old = qrc.findByUserID(user, q);
        if (old == null) {
            Quizrecord qr = new Quizrecord(count, q, user);
            QuizrecordJpaController qjc = new QuizrecordJpaController(utx, emf);
            try {
                qjc.create(qr);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(QuizServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuizServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            old.setQuizesQuizid(q);
            old.setTotalscore(count);
            old.setUsersUserid(user);
            QuizrecordJpaController qjc = new QuizrecordJpaController(utx, emf);
            try {
                qjc.edit(old);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(QuizServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuizServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("test", count);
        request.setAttribute("text", answers);
        getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
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
