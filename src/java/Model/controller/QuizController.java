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
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import jpa.QuestionsJpaController;
import jpa.QuizesJpaController;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;

/**
 *
 * @author Gamer
 */
public class QuizController {
    
    @PersistenceUnit(unitName="WebProjectInt303PU")
    EntityManagerFactory emf;
    
    @Resource
    UserTransaction utx;

    private final QuizesJpaController qjc;
    private final QuestionsJpaController qtjc;

    public QuizController(EntityManagerFactory emf, UserTransaction utx) {
        this.qjc = new QuizesJpaController(utx, emf);
        this.qtjc = new QuestionsJpaController(utx, emf);
    }

    public List<Quizes> findAllQuizes() {
        return qjc.findQuizesEntities();
    }

    public Quizes findByID(int id) {
        return qjc.findQuizes(id);
    }

    public Quizes findByQuizName(String quizname) {
        List<Quizes> quizList = qjc.findQuizesEntities();
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).getQuizname().equals(quizname)) {
                return quizList.get(i);
            }
        }
        return null;
    }

    public List<Questions> findAllQuestionsInQuiz(Quizes q) {
        List<Questions> questionsList = qtjc.findQuestionsEntities();
        ArrayList<Questions> questionsSubList = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            if (questionsList.get(i).getQuizesQuizid() != null) {
                if (questionsList.get(i).getQuizesQuizid().toString().equals(q.toString())) {
                    questionsSubList.add(questionsList.get(i));
                }
            }
        }
        return questionsSubList;
    }

    public int getTotalScore(Quizes q) {
        List<Questions> questionsList = this.findAllQuestionsInQuiz(q);
        int total = 0;
        for (int i = 0; i < questionsList.size(); i++) {
            QuestionController qtc = new QuestionController(emf, utx);
            int score = qtc.findMostScoreChoiceInEachQuestion(questionsList.get(i));
            total = total + score;
        }
        return total;
    }

    public void addQuiz(Quizes quiz) {
        try {
            qjc.create(quiz);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
