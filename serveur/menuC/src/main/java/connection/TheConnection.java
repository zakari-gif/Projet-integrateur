/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.*;

/**
 *
 * @author JDufo
 */
public class TheConnection {
    
    private final String URL="jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima";
    private final String USER="dufourtj";
    private final String PASSWD="Djjujuly20"; 
    private static Connection connect;

    private TheConnection() {
        try{
            connect = DriverManager.getConnection(URL, USER, PASSWD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static Connection getInstance(){
        if(connect == null){
            new TheConnection();
            System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
        }
        else{
            System.out.println("CONNEXION SQL EXISTANTE ! "); 
        }
        
        return connect;
    }
}

