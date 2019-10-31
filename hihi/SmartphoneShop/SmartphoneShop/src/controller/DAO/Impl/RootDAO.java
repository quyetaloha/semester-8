package controller.DAO.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RootDAO {
    public Connection getJDBCConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;dataBaseName=phone;user=sa;password=12345");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RootDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
