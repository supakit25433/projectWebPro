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
import jpa.QuizrecordJpaController;
import jpa.UsersJpaController;
import jpaClasses.Quizrecord;
import jpaClasses.Users;

/**
 *
 * @author Gamer
 */
public class QuizRecordController {
    private final QuizrecordJpaController qrjc;
    
    public QuizRecordController(EntityManagerFactory emf,UserTransaction utx) {
        this.qrjc = new QuizrecordJpaController(utx, emf);
    }
    
    public List<Quizrecord> findAllRecord(){
        return qrjc.findQuizrecordEntities();
    }
    
    public Quizrecord findByID(int id){
        return qrjc.findQuizrecord(id);
    }
    
    public List<Quizrecord> findAllRecorddOfUser(Users user) {        
        List<Quizrecord> recordList =  qrjc.findQuizrecordEntities();
        ArrayList<Quizrecord> userRecordList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getUsersUserid().equals(user)) {
               userRecordList.add(recordList.get(i));
            }     
        }
        return userRecordList;
    }
            
            
    
    
}
