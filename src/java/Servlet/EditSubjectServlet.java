/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
import jpa.SubjectsJpaController;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author nar-u
 */
public class EditSubjectServlet extends HttpServlet {

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
        String subjectname = request.getParameter("subjectname");
        String description = request.getParameter("description");
        
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        SubjectController sc = new SubjectController(emf, utx);
        List<Subjects> subjects = sc.findAllSubjectByUserID(user);
        
        if(request.getParameter("subjectid")==null 
                || request.getParameter("subjectid").isEmpty() ){
            request.setAttribute("subjects", subjects);
            getServletContext().getRequestDispatcher("/EditSubject.jsp").forward(request, response);
        }else {
            if(subjectname.trim().isEmpty() || description.trim().isEmpty()){
                request.setAttribute("message", "Please enter every box!!!");
                getServletContext().getRequestDispatcher("/EditSubject.jsp").forward(request, response);
            } else {
                if(user == null){
                    request.setAttribute("message", "Please log-in again!!!");
                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
                } else {
                    int subjectid = Integer.valueOf(request.getParameter("subjectid"));
                    Subjects subject = sc.findByID(subjectid);
                    subject.setSubjectname(subjectname);
                    subject.setDescription(description);
                    SubjectsJpaController sjc = new SubjectsJpaController(utx, emf);
                    sjc.edit(subject);
                    subjects = sc.findAllSubjectByUserID(user);
                    request.setAttribute("subjects", subjects);
                    request.setAttribute("message", "Edit Subject Complete");
                    getServletContext().getRequestDispatcher("/EditSubject.jsp").forward(request, response);
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
        } catch (RollbackFailureException ex) {
            Logger.getLogger(EditSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
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
