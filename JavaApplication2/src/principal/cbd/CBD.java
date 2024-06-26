/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.cbd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public abstract class CBD {
    
    protected Connection conect = null;
    protected ResultSet result = null;
    protected Statement state = null;
    
    private final String user = "root";
    private final String password = "jufetape";
    private final String database = "ips";
    private final String driver = "com.mysql.jdbc.Driver";
    
    
    protected void conectBase() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(driver);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + database + "?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conect = DriverManager.getConnection(urlBaseDeDatos, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    
    protected void desconectarBase() throws Exception{
        try {
            if(result != null){
                result.close();
            }
            if(state != null){
                state.close();
            }
            if(conect != null){
                conect.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void insertModDel(String sql) throws Exception{
        try {
            conectBase();
            state = conect.createStatement();
            state.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally{
            desconectarBase();
        }
    }
    
    protected void consultarBase(String sql) throws Exception{
        try {
            conectBase();
            state = conect.createStatement();
            result = state.executeQuery(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
