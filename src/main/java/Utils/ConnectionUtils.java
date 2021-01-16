/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import model.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto343
 */
public class ConnectionUtils {
  private static java.sql.Connection _conn=null;
    
    public static java.sql.Connection connect(Connection c) throws ClassNotFoundException, SQLException{
        java.sql.Connection conn=null;
        
        if(c==null){
            return null;
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://"+c.getHost()+"/"+c.getDb()
                +"?useLegacyDatetimeCode=false&serverTimezone=UTC",c.getUser(),c.getPassword());
        
        return conn;
    }
    
    public static java.sql.Connection getConnection(){
        if(_conn==null){
            Connection c=new Connection();
            c.loadDataXML();
            try {
                _conn=connect(c);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _conn;
    }
    
}