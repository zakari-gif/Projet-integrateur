/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import cinemaMenu.Client;
import connection.TheConnection;
import java.sql.*;


public class ClientDAO extends SqlDAO<Client>{
    

    public ClientDAO() {
        super();
    }

    @Override
    public boolean delete(Client cli) {
        
        boolean res = false;
        
        try{
            this.connect.createStatement().executeUpdate(
                                "DELETE FROM Client WHERE idClient = '" + cli.getIdClient() + "'"
                        );
            
            //connect.close();
            res = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return res;
    }

    public boolean update(Client cli) {
        boolean res = false;
        
        try {
            PreparedStatement prepare = connect
                            .prepareStatement("UPDATE Client SET"
                                    + " nomClient = ?,"
                                    + " prenomClient = ?,"
                                    + " photoClient = ?,"
                                    + " emailClient = ?,"
                                    + " telClient = ?,"
                                    + " noAdrClient = ?,"
                                    + " rueAdrClient = ?,"
                                    + " cpAdrClient = ?,"
                                    + " villeAdrClient = ?"
                                    + " WHERE idClient = ?"
                );
                
                prepare.setString(1, cli.getNomClient());
                prepare.setString(2, cli.getPrenomClient());
                prepare.setString(3, cli.getPhotoClient());
                prepare.setString(4, cli.getEmailClient());
                prepare.setString(5, cli.getTelClient());
                prepare.setString(6, cli.getNoAdrClient());
                prepare.setString(7, cli.getRueAdrClient());
                prepare.setString(8, cli.getCpAdrClient());
                prepare.setString(9, cli.getVilleAdrClient());
                prepare.setString(10, cli.getIdClient());
                
                prepare.executeUpdate();
                res = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    @Override
    public boolean create(Client cli) {
        
        boolean res = false;
        
        try{
                PreparedStatement prepare = this.connect
                            .prepareStatement("INSERT INTO Client (idClient, nomClient, prenomClient, photoClient, emailClient, telClient,"
                                    + "noAdrClient, rueAdrClient, cpAdrClient, villeAdrClient) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                
                prepare.setString(1, cli.getIdClient());
                prepare.setString(2, cli.getNomClient());
                prepare.setString(3, cli.getPrenomClient());
                prepare.setString(4, cli.getPhotoClient());
                prepare.setString(5, cli.getEmailClient());
                prepare.setString(6, cli.getTelClient());
                prepare.setString(7, cli.getNoAdrClient());
                prepare.setString(8, cli.getRueAdrClient());
                prepare.setString(9, cli.getCpAdrClient());
                prepare.setString(10, cli.getVilleAdrClient());
                
                prepare.executeUpdate();
                
                prepare.close();
                
                
                res = true;
                
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return res;
    }
    
    
    @Override
    public Client read (String idClient){
        
        Client cli = new Client();
        
        try{
            ResultSet result = this.connect.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT * FROM Client WHERE idClient = '" + idClient + "'"
                                    );
            if(result.first()){
                 cli = new Client(
                                idClient,
                                result.getString("nomClient"),
                                result.getString("prenomClient"),
                                result.getString("photoClient"),
                                result.getString("emailClient"),
                                result.getString("telClient"),
                                result.getString("noAdrClient"),
                                result.getString("rueAdrClient"),
                                result.getString("cpAdrClient"),
                                result.getString("villeAdrClient")
                );
            }
            
            result.close();
            
            
        }
        
        catch (SQLException e){
            e.printStackTrace();
        }
            
        return cli;
    }
    
    
    //Static oui/non
    public static boolean exist(String idClient){
        
        boolean res = false;
        
        Connection connect = TheConnection.getInstance(); //à changer
        
        try{
            ResultSet result = connect.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT idClient FROM Client WHERE idClient = '" + idClient + "'"
                                    );
            if (result.first()){
                res = true;
            }
        
            
            result.close();
            
        }
        catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("catch");
            }
            
        return res;
       
    }
    
    public boolean existTest(String idClient){
        
        boolean res = false;
        
        //Connection connect = TheConnection.getInstance(); //à changer
        
        try{
            ResultSet result = connect.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT idClient FROM Client WHERE idClient = '" + idClient + "'"
                                    );
            if (result.first()){
                res = true;
            }
        
            
            result.close();
            
        }
        catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("catch");
            }
            
        return res;
       
    }

    
}
