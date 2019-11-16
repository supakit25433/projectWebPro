/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.UsersJpaController;
import jpaClasses.Users;

/**
 *
 * @author Gamer
 */
public class UserController {
    private final UsersJpaController ujc;

    public UserController(EntityManagerFactory emf,UserTransaction utx) {
        this.ujc = new UsersJpaController(utx, emf);
    }
    
    public Users findByUserName(String username){
        return ujc.findUserByUsername(username);
    }
    
}
