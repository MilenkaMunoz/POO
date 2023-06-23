/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transacciones;

import Connetions.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author BrunoDev
 */
public class Transacciones extends Conexion {

    private Connection connection;
    private PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
    private ResultSet resultSet;

    public void Transaccion() throws SQLException {

        connection = getConexion();

        try {

            String sql1 = "INSERT INTO CuentaBancaria (nombre, apellido, noCuenta) Values (?,?,?)";
            
            preparedStatement1 = connection.prepareStatement(sql1);

            //Parametros
            preparedStatement1.setString(1, "Juan");
            
            preparedStatement1.setString(2, "Guzman");
            
            preparedStatement1.setString(3, "656846");
            //...

            //Ejecuta primera Transaccion
            preparedStatement1.executeUpdate();

            
            String sql2 = "UPDATE CuentaBancaria SET ahoro = ?, credito = ? where noCuenta = ?";
            
            preparedStatement2 = connection.prepareStatement(sql2);

            //Parametros
            preparedStatement2.setDouble(1, 80000.00);
            
            preparedStatement2.setDouble(2, 0.00);
            
            preparedStatement2.setString(3, "656846");
            //...

            //Ejecuta segunda Transaccion
            preparedStatement2.executeUpdate();

            connection.commit();

            JOptionPane.showMessageDialog(null, "Transaccion ejecutada correctamente");

        } catch (SQLException e) {
            
            connection.rollback();


        } finally {

            if (preparedStatement1 != null) {
                
                try {
                    
                    preparedStatement1.close();
                    
                } catch (SQLException e) {
                }
            }

            if (preparedStatement2 != null) {
                
                try {
                    
                    preparedStatement2.close();
                    
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                
                try {
                    
                    connection.close();
                    
                } catch (SQLException e) {
                }
            }
        }
    }
}
