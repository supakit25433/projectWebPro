/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.ChoiceController;
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
import jpaClasses.Choices;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;

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

        /*ArrayList<Choices> allChoices = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            QuestionController qtc = new QuestionController(emf, utx);
            int questionID = questionsList.get(i).getQuestionid();
            Questions qt = qtc.findByID(questionID);                            //
            List<Choices> choicesList = qtc.findAllChoicesInQuestion(qt);       //ชอยส์ทั้งหมดของคำถามนั้นๆ
            if (questionsList.get(i).getQuizesQuizid().equals(q.getQuizid())) { //เช็คว่าคำถามนั้นๆตรงกับquizนี้ไหม
//                allChoices.add(choicesList);
                for (int j = 0; j < choicesList.size(); j++) {
                    allChoices.add(choicesList.get(j));                         //addชอยส์ทั้งหมดของคำถามนี้ไปเก็บในallChoices
                }
            }
        }*/
        
        ArrayList<List<Choices>> allChoices = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            QuestionController qtc = new QuestionController(emf, utx);
            int questionID = questionsList.get(i).getQuestionid();
            Questions qt = qtc.findByID(questionID);
            List<Choices> choicesList = qt.getChoicesList();                    //ชอยส์ทั้งหมดของคำถามนั้นๆ
            allChoices.add(choicesList);
            /*for (int j = 0; j < choicesList.size(); j++) {                      
                allChoices.add(choicesList.get(j));
            }*/
        }
        

        request.setAttribute("quiz", q);
        request.setAttribute("questions", questionsList);
        request.setAttribute("choices", allChoices);
        getServletContext().getRequestDispatcher("/Quiz.jsp").forward(request, response);

        /*
        QuizController qc = new QuizController(emf, utx);
        int quizID = Integer.parseInt(request.getParameter("quizid"));
        Quizes q = qc.findByID(quizID);
        List<Questions> questionsList = qc.findAllQuestionsInQuiz(q);
        ArrayList<Choices> choiceList = new ArrayList<>();
        ChoiceController cc = new ChoiceController(emf, utx);
        List<Choices> allChoiceList = cc.findAllChoices();
        for (int i = 0; i < questionsList.size(); i++) {
            if (questionsList.get(i).getQuizesQuizid().equals(q.getQuizid())) {
                 for (int j = 0; j < allChoiceList.size(); j++) {
                     if (questionsList.get(i).getQuestionid().equals(allChoiceList.get(j).getQuestionsQuestionid())) {
                         choiceList.add(allChoiceList.get(j));
                     }
                }
            
            }
        }
        request.setAttribute("quiz", q);
        request.setAttribute("questions", questionsList);
        request.setAttribute("choices", choiceList);
        getServletContext().getRequestDispatcher("/Quiz.jsp").forward(request, response);
         */
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
