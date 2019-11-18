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
import jpa.QuizesJpaController;
import jpaClasses.Questions;
import jpaClasses.Quizes;
import jpaClasses.Subjects;

/**
 *
 * @author Gamer
 */
public class QuizController {

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

    public List<Quizes> findQuizinSubject(Subjects s) {
        List<Quizes> quizesList = qjc.findQuizesEntities();
        ArrayList<Quizes> quizesInSubjectList = new ArrayList<>();
        for (int i = 0; i < quizesList.size(); i++) {
            if (s.getSubjectid().equals(quizesList.get(i).getSubjectsSubjectid())) {
                quizesInSubjectList.add(quizesList.get(i));
            }
        }

        return quizesInSubjectList;
    }

    public List<Questions> findAllQuestionsInQuiz(Quizes q) {
        List<Questions> questionsList = qtjc.findQuestionsEntities();
        ArrayList<Questions> questionSubList = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            if (questionsList.get(i).getQuizesQuizid() != null) {
                if (questionsList.get(i).getQuizesQuizid().toString().equals(q.toString())) {
                    questionSubList.add(questionsList.get(i));
                }
            }
        }
        return questionSubList;
    }

}
