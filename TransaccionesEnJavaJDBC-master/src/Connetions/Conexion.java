package Connetions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//<editor-fold defaultstate="collapsed" desc="comment">
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BrunoDev
 */
public class Conexion {
    
    protected Connection connection;
    private final String USER;
    private final String PASS_WORD;
    private final String URL;
    private final String JDBC_DRIVER;
    
    public Conexion() {
        //URL jdbc Driver//localhost or Direccion ip//port//database=databaseName
        URL = "jdbc:sqlserver://localhost:1433;database=BD;";
        USER = "root;";
        PASS_WORD = "admin;";
        JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
    
    public boolean conectar() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL + USER + PASS_WORD);
            connection.setAutoCommit(false);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean desConectar() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                System.err.println("Error: " + e);
                return false;
            }
        }
        return false;
    }
    
    public Connection getConexion() {
        try {
            conectar();
            return connection;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
//</editor-fold>
