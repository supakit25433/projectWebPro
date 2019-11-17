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
import jpa.QuestionsJpaController;
import jpaClasses.Questions;
import jpaClasses.Quizes;

/**
 *
 * @author surface
 */
public class QuestionController {
    private final QuestionsJpaController qtjc;

    public QuestionController(EntityManagerFactory emf,UserTransaction utx) {
        this.qtjc = new QuestionsJpaController(utx, emf);
    }
    
    public List<Questions> findAllQuestions(){
        return qtjc.findQuestionsEntities();
    }
    
    public Questions findByID(int id){
        return qtjc.findQuestions(id);
    }
    
    public List<Questions> findAllQuestionsInQuizes(Quizes q){
        List<Questions> questionList = qtjc.findQuestionsEntities();
        ArrayList<Questions> questionSubList = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (q.getQuizid().equals(questionList.get(i).getQuizesQuizid())) {
                questionSubList.add(questionList.get(i));
            }
        }
        return questionSubList;               
    }
}
