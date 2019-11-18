/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.ChoicesJpaController;
import jpaClasses.Choices;

/**
 *
 * @author surface
 */
public class ChoiceController {
    private final ChoicesJpaController cjc;

    public ChoiceController(EntityManagerFactory emf,UserTransaction utx) {
        this.cjc = new ChoicesJpaController(utx, emf);
    }
    
    public List<Choices> findAllChoices(){
        return cjc.findChoicesEntities();
    }
    
    public Choices findByID(int id){
        return cjc.findChoices(id);
    }
    
}
