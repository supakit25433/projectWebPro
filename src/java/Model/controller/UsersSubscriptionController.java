/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.SubjectsJpaController;
import jpa.UsersJpaController;
import jpa.UsersSubscriptionJpaController;
import jpaClasses.Subjects;
import jpaClasses.Users;
import jpaClasses.UsersSubscription;

/**
 *
 * @author nar-u
 */
public class UsersSubscriptionController {
    
    private final UsersJpaController ujc;
    private final SubjectsJpaController sjc;
    private final UsersSubscriptionJpaController usjc;

    public UsersSubscriptionController(EntityManagerFactory emf,UserTransaction utx) {
        this.ujc = new UsersJpaController(utx, emf);
        this.sjc = new SubjectsJpaController(utx, emf);
        this.usjc = new UsersSubscriptionJpaController(utx, emf);
    }
    
    public UsersSubscription findBySubjectIDandUser(Subjects subject,Users user){
        List<UsersSubscription> subList = usjc.findUsersSubscriptionEntities();
        for (int i = 0; i < subList.size(); i++) {
            if(subList.get(i).getSubjectsSubjectid().toString().equals(subject.toString())){
                if(subList.get(i).getUsersUserid().toString().equals(user.toString())){
                    return subList.get(i);
                }
            }
        }
        return null;
    }
    
    public List<UsersSubscription> findAllUsersSubscriptions() {
        return usjc.findUsersSubscriptionEntities();
    }
    
    
}
