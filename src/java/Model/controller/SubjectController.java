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
import jpa.SubjectsJpaController;
import jpaClasses.Subjects;
import jpaClasses.Quizes;

/**
 *
 * @author Gamer
 */
public class SubjectController {
    private final SubjectsJpaController sjc;
    private final QuizesJpaController qjc;

    public SubjectController(EntityManagerFactory emf,UserTransaction utx) {
        this.sjc = new SubjectsJpaController(utx, emf);
        this.qjc = new QuizesJpaController(utx, emf);
    }
    
    public List<Subjects> findAllSubjects(){
        return sjc.findSubjectsEntities();
    }
    
    public Subjects findByID(int id){
        return sjc.findSubjects(id);
    }
    
    public List<Quizes> findAllQuizesInSubject(Subjects s){
        List<Quizes> quizList = qjc.findQuizesEntities();
        ArrayList<Quizes> quizSubList = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) {
            /*if (s.getSubjectid().equals(quizList.get(i).getSubjectsSubjectid())) {
                quizSubList.add(quizList.get(i));
            }*/
        if (quizList.get(i).getSubjectsSubjectid() != null) {
                if (quizList.get(i).getSubjectsSubjectid().toString().equals(s.toString())) {
                    quizSubList.add(quizList.get(i));
                }
            }
        }
        return quizSubList;               
    }
    
    
}
