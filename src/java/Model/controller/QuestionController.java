/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.ChoicesJpaController;
import jpa.QuestionsJpaController;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Choices;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;

/**
 *
 * @author surface
 */
public class QuestionController {

    private final QuestionsJpaController qtjc;
    private final ChoicesJpaController cjc;

    public QuestionController(EntityManagerFactory emf, UserTransaction utx) {
        this.qtjc = new QuestionsJpaController(utx, emf);
        this.cjc = new ChoicesJpaController(utx, emf);
    }

    public List<Questions> findAllQuestions() {
        return qtjc.findQuestionsEntities();
    }

    public Questions findByID(int id) {
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
    
    public List<Questions> findAllQuestionByQuiz(Quizes quiz){
        List<Questions> questionList = qtjc.findQuestionsEntities();
        ArrayList<Questions> List = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if(questionList.get(i)!=null){
                if(questionList.get(i).getQuizesQuizid().toString().equals(quiz.toString())){
                    List.add(questionList.get(i));
                }
            }
        }
        return List;
    }

    public int findMostScoreChoiceInEachQuestion(Questions qt) {
        List<Choices> choicesList = this.findAllChoicesInQuestion(qt);
        int max = 0;
        for (int i = 0; i < choicesList.size(); i++) {
            if (choicesList.get(i).getPoint() > max) {
                max = choicesList.get(i).getPoint();
            }
        }
        return max;
    }

    public void addQuestion(Questions question) {
        try {
            qtjc.create(question);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
