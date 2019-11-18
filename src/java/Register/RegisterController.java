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
            "INSERT INTO WEB.USERS (USERID,USERNAME,PASSWORD,FULLNAME,TYPENAME) VALUES (?,?,?,?)";
    
    public void register(int number, String username, String password, String fullname, String type){
        Connection conn = BuildConnection.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(REGISTER);
            pstm.setInt(1, number);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.setString(4, fullname);
            pstm.setString(5, type);
            pstm.executeQuery();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
