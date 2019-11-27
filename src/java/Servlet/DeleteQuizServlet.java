/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
import jpa.ChoicesJpaController;
import jpa.QuestionsJpaController;
import jpa.QuizesJpaController;
import jpa.QuizrecordJpaController;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Choices;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Quizrecord;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class DeleteQuizServlet extends HttpServlet {

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
        SubjectController sc = new SubjectController(emf, utx);
        QuizController qc = new QuizController(emf, utx);
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        List<Subjects> subjects = sc.findAllSubjectByUserID(user);
        ArrayList<Quizes> quizes = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            Subjects sub = sc.findByID(subjects.get(i).getSubjectid());
            List<Quizes> quizList = sc.findAllQuizesInSubject(sub);
            for (int j = 0; j < quizList.size(); j++) {
                quizes.add(quizList.get(j));
            }
        }

        if (request.getParameter("subjectid") == null || request.getParameter("subjectid").isEmpty()) {
            request.setAttribute("subjects", subjects);
            request.setAttribute("quizes", quizes);
            getServletContext().getRequestDispatcher("/DeleteQuiz.jsp").forward(request, response);
        } else {
            int quizid = Integer.valueOf(request.getParameter("quizid"));
            int subjectid = Integer.valueOf(request.getParameter("subjectid"));
            Subjects subject = sc.findByID(subjectid);
            Quizes quiz = qc.findByQuizIDAndSubject(quizid, subject);
            List<Questions> quesList = null;
            if (quiz != null) {
                quesList = qc.findAllQuestionsInQuiz(quiz);
            }
            if (quesList == null) {
                QuizesJpaController qjc = new QuizesJpaController(utx, emf);
                if (qjc.findQuizes(quizid) == null) {
                    request.setAttribute("message", "can not this quiz. Please select other.");
                    request.setAttribute("subjects", subjects);
                    getServletContext().getRequestDispatcher("/DeleteQuiz.jsp").forward(request, response);
                } else {
                    if (!qjc.findQuizes(quizid).getSubjectsSubjectid().equals(subject)) {
                        request.setAttribute("message", "Please match quiz and subject correctly");
                        request.setAttribute("subjects", subjects);
                        request.setAttribute("quizes", quizes);
                        getServletContext().getRequestDispatcher("/DeleteQuiz.jsp").forward(request, response);
                    } else {
                        qjc.destroy(quizid);
                        request.setAttribute("subjects", subjects);
                        request.setAttribute("quizes", quizes);
                        request.setAttribute("message", "Delete Quiz Complete");
                        getServletContext().getRequestDispatcher("/DeleteQuiz.jsp").forward(request, response);
                    }
                }
            } else {
                QuestionController quc = new QuestionController(emf, utx);
                QuizRecordController qr = new QuizRecordController(emf, utx);
                QuizrecordJpaController qrjc = new QuizrecordJpaController(utx, emf);
                List<Quizrecord> record = qr.findAllRecordByQuiz(quiz);
                for (int i = 0; i < record.size(); i++) {
                    qrjc.destroy(record.get(i).getQuizrecordid());
                }
                for (int i = 0; i < quesList.size(); i++) {
                    List<Choices> ques = quc.findAllChoicesInQuestion(quesList.get(i));
                    for (int j = 0; j < ques.size(); j++) {
                        ChoicesJpaController cjc = new ChoicesJpaController(utx, emf);
                        cjc.destroy(ques.get(j).getChoiceid());
                    }
                    QuestionsJpaController qjc = new QuestionsJpaController(utx, emf);
                    qjc.destroy(quesList.get(i).getQuestionid());
                }
                QuizesJpaController qjc = new QuizesJpaController(utx, emf);
                qjc.destroy(quizid);
                ArrayList<Quizes> newquizes = new ArrayList<>();
                for (int i = 0; i < subjects.size(); i++) {
                    Subjects sub = sc.findByID(subjects.get(i).getSubjectid());
                    List<Quizes> quizList = sc.findAllQuizesInSubject(sub);
                    for (int j = 0; j < quizList.size(); j++) {
                        newquizes.add(quizList.get(j));
                    }
                }
                request.setAttribute("subjects", subjects);
                request.setAttribute("quizes", newquizes);
                request.setAttribute("message", "Delete Quiz Complete");
                getServletContext().getRequestDispatcher("/DeleteQuiz.jsp").forward(request, response);
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
            Logger.getLogger(DeleteQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DeleteQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DeleteQuizServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
