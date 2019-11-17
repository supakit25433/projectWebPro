/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.QuizesJpaController;
import jpaClasses.Quizes;
import jpaClasses.Subjects;

/**
 *
 * @author Gamer
 */
public class QuizController {
    private final QuizesJpaController qjc;
    
    
    
    public QuizController(EntityManagerFactory emf,UserTransaction utx) {
        this.qjc = new QuizesJpaController(utx, emf);
    }
    
    public Quizes findByID(int id){
        return qjc.findQuizes(id);
    }
    
    public List<Quizes> findQuizinSubject(Subjects s){
        List<Quizes> quizList = qjc.findQuizesEntities();
        ArrayList<Quizes> quizInSubjectList = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) {
            if () {
                
            }
        }
        
        return quizInSubjectList;
    }
    
    
    
    
}
