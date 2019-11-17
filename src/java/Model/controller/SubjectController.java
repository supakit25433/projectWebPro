/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.SubjectsJpaController;

/**
 *
 * @author Gamer
 */
public class SubjectController {
    private final SubjectsJpaController sjc;

    public SubjectController(EntityManagerFactory emf,UserTransaction utx) {
        this.sjc = new SubjectsJpaController(utx, emf);
        
    }
    
    
    
    
}
