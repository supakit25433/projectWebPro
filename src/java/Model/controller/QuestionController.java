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
import jpa.ChoicesJpaController;
import jpa.QuestionsJpaController;
import jpaClasses.Choices;
import jpaClasses.Questions;
import jpaClasses.Quizes;

/**
 *
 * @author surface
 */
public class QuestionController {
    private final QuestionsJpaController qtjc;
    private final ChoicesJpaController cjc;

    public QuestionController(EntityManagerFactory emf,UserTransaction utx) {
        this.qtjc = new QuestionsJpaController(utx, emf);
        this.cjc = new ChoicesJpaController(utx, emf);
    }
    
    public List<Questions> findAllQuestions(){
        return qtjc.findQuestionsEntities();
    }
    
    public Questions findByID(int id){
        return qtjc.findQuestions(id);
    }
    
    public List<Choices> findAllChoicesInQuestion(Questions qt) {
        List<Choices> choicesList = cjc.findChoicesEntities();
        ArrayList<Choices> choicesSubList = new ArrayList<>();
        for (int i = 0; i < choicesList.size(); i++) {
            if (choicesList.get(i).getQuestionsQuestionid() != null) {
                if (choicesList.get(i).getQuestionsQuestionid().toString().equals(qt.toString())) {
                    choicesSubList.add(choicesList.get(i));
                }
            }
        }
        return choicesSubList;
    }
    
}
