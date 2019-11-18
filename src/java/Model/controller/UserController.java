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
import jpa.QuizesJpaController;
import jpa.SubjectsJpaController;
import jpa.UsersJpaController;
import jpa.UsersSubscriptionJpaController;
import jpa.exceptions.RollbackFailureException;
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
        List<Users> userList = ujc.findUsersEntities();
        for (int i=0;i<userList.size();i++) {
            if(userList.get(i).getUsername().equals(username)){
                return userList.get(i);
            }
        }
        return null;
    }
    
    public List<Users> findAllUsers(){
        return ujc.findUsersEntities();
    }
    
    public List<Subjects> findUserSubjectSubscription(Users user){
        //return all subject that this user enrolled
        List<Subjects> subjectList = sjc.findSubjectsEntities();
        List<UsersSubscription> subscription = usjc.findUsersSubscriptionEntities();
        ArrayList<Subjects> userSubscriotion = new ArrayList<>();
        for (int i = 0; i < subscription.size(); i++) {
            if(user.toString().equals(subscription.get(i).getUsersUserid().toString())){
                for (int j = 0; j < subjectList.size(); j++) {
                    if (subscription.get(i).getSubjectsSubjectid().toString().
                            equals(subjectList.get(j).toString())) {
                        userSubscriotion.add(subjectList.get(j));                                                  
                    }
                }
            }
        } 
        return userSubscriotion;
    }

    public void createUser(Users user){
        try {
            ujc.create(user);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
