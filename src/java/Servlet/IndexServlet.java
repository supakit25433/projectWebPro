/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.controller.QuestionController;
import Model.controller.QuizController;
import Model.controller.SubjectController;
import Model.controller.UserController;
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
import jpa.QuestionsJpaController;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author surface
 */
public class IndexServlet extends HttpServlet {

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

        UserController uc = new UserController(emf, utx);
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "user not found");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            ArrayList<Quizes> quizesListReverse = new ArrayList<>();
            
            List<Subjects> userSubList = uc.findUserSubjectSubscription(user);
            for (int i = userSubList.size() - 1; i >= 0 ; i--) {
                SubjectController sc = new SubjectController(emf, utx);
                Subjects s = sc.findByID(userSubList.get(i).getSubjectid());
                List<Quizes> quizesList = sc.findAllQuizesInSubject(s);

                for (int j = quizesList.size() - 1; j >= 0; j--) {
                    quizesListReverse.add(quizesList.get(j));
                }
            }
            request.setAttribute("quizzes", quizesListReverse);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

//        UserController uc = new UserController(emf, utx);
//        HttpSession session = request.getSession(false);
//        Users user = (Users) session.getAttribute("user");
//        List<Subjects> userSubList = uc.findUserSubjectSubscription(user);
//        ArrayList<Quizes> quizesListReverse = new ArrayList<>();
//        for (int i = 0; i < userSubList.size(); i++) {
//            if (userSubList.get(i) == null) {
//                request.setAttribute("message", "You doesn't have any quiz from subscribed subject");
//                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//            } else {
//                Subjects s = userSubList.get(i);
//                SubjectController sc = new SubjectController(emf, utx);
//                List<Quizes> quizesListFromSubject = sc.findAllQuizesInSubject(s);
////                for (int j = quizesListFromSubject.size(); j >= 0; j--) {
////                    quizesListReverse.add(quizesListFromSubject.get(j));
////                }
//                request.setAttribute("quizzes", quizesListFromSubject);
//                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//            }
//        }
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
