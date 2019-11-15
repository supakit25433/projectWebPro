/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.BuildConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nar-u
 */
public class UserController {

    private final static String FIND
            = "select * from userpassword where username = ?";
    private final static String REGISTER
            = "insert into userpassword (username,passoword) values (? ,?)";

    public User find(String userName) {
        User u = null;
        Connection conn = BuildConnection.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND);
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void register(String id, String password) {
        Connection conn = BuildConnection.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(REGISTER);
            pstm.setString(1, id);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
        } catch(SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDetail(){
        
    }
}
