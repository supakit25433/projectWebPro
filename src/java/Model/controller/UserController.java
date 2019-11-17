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
import jpa.UsersJpaController;
import jpa.UsersSubscriptionJpaController;
import jpaClasses.Quizes;
import jpaClasses.Subjects;
import jpaClasses.Users;
import jpaClasses.UsersSubscription;

/**
 *
 * @author Gamer
 */
public class UserController {
    private final UsersJpaController ujc;
    private final SubjectsJpaController sjc;
    private final UsersSubscriptionJpaController usjc;
    private final QuizesJpaController qjc;

    public UserController(EntityManagerFactory emf,UserTransaction utx) {
        this.ujc = new UsersJpaController(utx, emf);
        this.sjc = new SubjectsJpaController(utx, emf);
        this.usjc = new UsersSubscriptionJpaController(utx, emf);
        this.qjc = new QuizesJpaController(utx, emf);
    }
    
    public Users findByUserName(String username){
        return ujc.findUserByUsername(username);
    }
    
    public List<Subjects> getUserSubjectSubscription(Users user){
        //return all subject that this user enrolled
        List<Subjects> subjectList = sjc.findSubjectsEntities();
        List<UsersSubscription> subscription = usjc.findUsersSubscriptionEntities();
        ArrayList<Subjects> userSubscriotion = new ArrayList<>();
        for (int i = 0; i < subscription.size(); i++) {
            if(user.getUserid().equals(subscription.get(i).getUsersUserid())){
                for (int j = 0; j < subjectList.size(); j++) {
                    if (subscription.get(i).getSubjectsSubjectid().equals(subjectList.get(j).getSubjectid())) {
                        userSubscriotion.add(subjectList.get(j));
                    }
                }
            }
        } 
        return userSubscriotion;
    }

}
