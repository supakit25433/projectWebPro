/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register;

import DB.BuildConnection;
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
public class RegisterController {
    
    private final static String REGISTER = 
            "INSERT INTO WEB.USERS (USERNAME,PASSWORD,FULLNAME) VALUES (?,?,?)";
    
    public void register(String username, String password, String fullname){
        Connection conn = BuildConnection.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(REGISTER);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, fullname);
            pstm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
