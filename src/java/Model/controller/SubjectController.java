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
import jpa.QuizesJpaController;
import jpa.SubjectsJpaController;
import jpa.UsersSubscriptionJpaController;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Subjects;
import jpaClasses.Quizes;
import jpaClasses.Users;
import jpaClasses.UsersSubscription;

/**
 *
 * @author Gamer
 */
public class SubjectController {

    @PersistenceUnit(unitName = "WebProjectInt303PU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    private final SubjectsJpaController sjc;
    private final QuizesJpaController qjc;
    private final UsersSubscriptionJpaController usjc;

    public SubjectController(EntityManagerFactory emf, UserTransaction utx) {
        this.sjc = new SubjectsJpaController(utx, emf);
        this.qjc = new QuizesJpaController(utx, emf);
        this.usjc = new UsersSubscriptionJpaController(utx, emf);
    }

    public List<Subjects> findAllSubjects() {
        return sjc.findSubjectsEntities();
    }

    public Subjects findByID(int id) {
        return sjc.findSubjects(id);
    }

    public List<Quizes> findAllQuizesInSubject(Subjects s) {
        List<Quizes> quizList = qjc.findQuizesEntities();
        ArrayList<Quizes> quizSubList = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).getSubjectsSubjectid() != null) {
                if (quizList.get(i).getSubjectsSubjectid().equals(s)) {
                    quizSubList.add(quizList.get(i));
                }
            }
        }
        return quizSubList;
    }

    public List<Subjects> findAllSubjectByUserID(Users user) {
        List<Subjects> subjectList = sjc.findSubjectsEntities();
        ArrayList<Subjects> subList = new ArrayList<>();
        for (int i = 0; i < subjectList.size(); i++) {
            if (subjectList.get(i).getUsersUserid() != null) {
                if (subjectList.get(i).getUsersUserid().toString().equals(user.toString())) {
                    subList.add(subjectList.get(i));
                }
            }
        }
        return subList;
    }

    public void editSubject(Subjects subject) {
        try {
            sjc.edit(subject);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countSubjectSubscriber(Subjects subject) {
        List<UsersSubscription> allSubList = usjc.findUsersSubscriptionEntities();
        int count = 0;
        for (int i = 0; i < allSubList.size(); i++) {
            if (allSubList.get(i).getSubjectsSubjectid().equals(subject)) {
                count++;
            }
        }
        return count;
    }

    public int getTotalScore(Subjects subject) {
        List<Quizes> quizzesList = this.findAllQuizesInSubject(subject);
        int total = 0;
        for (int i = 0; i < quizzesList.size(); i++) {
            QuizController qc = new QuizController(emf, utx);
            int score = qc.getTotalScore(quizzesList.get(i));
            total = total + score;
        }
        return total;
    }
}
