/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nar-u
 */
public class BuildConnection {

    private static DataSource ds = null;
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/QuizAnt";
    private static final String USERNAME = "web";
    private static final String PASSWORD = "web";
    private static boolean isLoad = false;

    public static Connection getConnection() {
        Connection conn = null;
//        if (! isLoad) {
//            try {
//                Class.forName(DRIVER);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(BuildConnection.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            isLoad = true ;
//        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(BuildConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static Connection getConnectionFromPool() {

        Connection conn = null;
        if (ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource) ctx.lookup("jdbc/web");
            } catch (NamingException ex) {
                Logger.getLogger(BuildConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            conn = ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BuildConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

}
