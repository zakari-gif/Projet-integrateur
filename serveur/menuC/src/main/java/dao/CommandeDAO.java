/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import cinemaMenu.Client;
import cinemaMenu.Commande;
import cinemaMenu.Plat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Acer
 */
public class CommandeDAO extends SqlDAO<Commande> {

    public CommandeDAO() {
        super();
    }
    
    public ArrayList<Commande> readCommandeClient(String idClient) {
        ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
        
        try {
            PreparedStatement stCom = this.connect.prepareStatement("SELECT * FROM Commande"
                    + " WHERE idClient = ?");
            stCom.setString(1, idClient);
            ResultSet rsCom = stCom.executeQuery();
            while(rsCom.next()) {
                int idCom = rsCom.getInt("idCommande");
                String date = rsCom.getString("dateCommande");
                String noAdr = rsCom.getString("noAdrLivraison");
                String rue = rsCom.getString("rueAdrLivraison");
                String cp = rsCom.getString("cpAdrLivraison");
                String ville = rsCom.getString("villeAdrLivraison");
                double prix = rsCom.getDouble("prixCommande");
                ArrayList<String> films = new ArrayList<String>();
                PreparedStatement stFCom = this.connect.prepareStatement("SELECT * FROM Film_Commandes"
                    + " WHERE idCommande = ? ORDER BY idCommande");
                
                stFCom.setInt(1, idCom);
                ResultSet rsFCom = stFCom.executeQuery();
                while(rsFCom.next()) {
                    films.add(rsFCom.getString("idFilmCommande"));
                }
                stFCom.close();
                rsFCom.close();
                
                ArrayList<Plat> plats = new ArrayList<Plat>();
                PreparedStatement stPCom = this.connect.prepareStatement("SELECT * FROM Plat_Commandes"
                    + " WHERE idCommande = ? ORDER BY idCommande");
                
                stPCom.setInt(1, idCom);
                ResultSet rsPCom = stPCom.executeQuery();
                DomPlatDAO pDao = new DomPlatDAO();
                while(rsPCom.next()) {
                    Plat plat = pDao.read(rsPCom.getString("idPlat"));
                    plat.setQuantite(rsPCom.getInt("quantite"));
                    plats.add(plat);
                }
                stPCom.close();
                rsPCom.close();
                
                lesCommandes.add(
                        new Commande(idCom,idClient,plats,films,noAdr,rue,cp,ville,prix,date));
            }
            stCom.close();
            rsCom.close();
          
            
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return lesCommandes;
    }

    @Override
    public boolean delete(Commande cmd) {
        
        boolean res = false;
        
        try{
            
            for(String f : cmd.getIdFilms()){
                    this.connect.createStatement().executeUpdate(
                                "DELETE FROM Film_Commandes WHERE idCommande = '" + cmd.getId() + "' AND idFilmCommande ="
                                        + " '" + Integer.parseInt(f) + "'"
                        );
            }
            
            for(Plat p : cmd.getIdPlats()){
                    this.connect.createStatement().executeUpdate(
                                "DELETE FROM Plat WHERE idCommande = '" + cmd.getId() + "' AND idPlat ="
                                        + " '" + p.getId() + "'"
                        );
            }
            
            this.connect.createStatement().executeUpdate(
                                "DELETE FROM Commande WHERE idCommande = '" + cmd.getId() + "'"
                        );
            
            //connect.close();
            res = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return res;
    }

    @Override
    public boolean update(Commande cmd) {
        
        boolean res = false;
        
        try {
            PreparedStatement prepare = connect
                            .prepareStatement("UPDATE Commande SET"
                                    + " dateCommande = ?,"
                                    + " noAdrLivraison = ?,"
                                    + " rueAdrLivraison = ?,"
                                    + " cpAdrLivraison = ?,"
                                    + " villeAdrLivraison = ?"
                                    + " WHERE idCommande = ?"
                );
                
                prepare.setString(1, cmd.getDate());
                prepare.setString(3, cmd.getNoAdrLivraison());
                prepare.setString(4, cmd.getRueAdrLivraison());
                prepare.setString(5, cmd.getCpAdrLivraison());
                prepare.setString(6, cmd.getVilleAdrLivraison());
                prepare.setInt(7, cmd.getId());
                
                prepare.executeUpdate();
                prepare.close();
                /*
                
                for(String f : cmd.getIdFilms()){
                    PreparedStatement prepare2 = connect
                            .prepareStatement("UPDATE Film_Commandes SET"
                                    + " idFilmCommande = ?"
                                    + " WHERE idCommande = ?"
                                    + " AND idFilmCommande = ?"
                );
                
                prepare2.setInt(1, Integer.parseInt(f));
                prepare2.setInt(2, cmd.getId());
                prepare2.setInt(3, ?);
                
                prepare2.executeUpdate();
                }
                
                for(String p : cmd.getIdPlats()){
                    PreparedStatement prepare3 = connect
                            .prepareStatement("UPDATE Plat SET"
                                    + " idPlat = ?"
                                    + " WHERE idCommande = ?"
                                    + " AND idPlat = ?"
                );
                
                prepare3.setString(1, p);
                prepare3.setInt(2, cmd.getId());
                prepare3.setString(3, p);
                
                prepare3.executeUpdate();
                }
*/
 
                res = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }

    @Override
    public boolean create(Commande cmd) {
        
        boolean res = false;
        
        try{
            this.connect.setAutoCommit(false);
            
            ResultSet result = this.connect
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT (MAX(idCommande)+1) as IdCommande FROM Commande"
                                    );
			if(result.first()){
				int id = result.getInt("idCommande");
                                cmd.setId(id);
                PreparedStatement prepare = this.connect
                            .prepareStatement("INSERT INTO Commande (idCommande, idClient, dateCommande, prixCommande, noAdrLivraison, rueAdrLivraison,"
                                    + "cpAdrLivraison, villeAdrLivraison) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
                
                prepare.setInt(1, id);
                prepare.setString(2, cmd.getIdClient());
                prepare.setString(3, cmd.getDate());
                prepare.setDouble(4, cmd.getPrix());
                prepare.setString(5, cmd.getNoAdrLivraison());
                prepare.setString(6, cmd.getRueAdrLivraison());
                prepare.setString(7, cmd.getCpAdrLivraison());
                prepare.setString(8, cmd.getVilleAdrLivraison());
                
                prepare.executeUpdate();
                
                prepare.close();
                
                ArrayList<String> films = cmd.getIdFilms();
                
                for(String f : films){
                    PreparedStatement prepare2 = this.connect
                            .prepareStatement("INSERT INTO Film_Commandes (idFilmCommande, idCommande) VALUES (?, ?)"
                );
                    
                    
                prepare2.setInt(1, Integer.parseInt(f));
                prepare2.setInt(2, id);
                
                
                prepare2.executeUpdate();
                prepare2.close();
                }
                
                ArrayList<Plat> plats = cmd.getIdPlats();
                
                
                for(Plat p : plats){
                    PreparedStatement prepare3 = this.connect.prepareStatement("INSERT INTO Plat_Commandes (idPlat, idCommande, quantite) VALUES (?, ?, ?)"
                );
                

                prepare3.setString(1, p.getId());
                prepare3.setInt(2, id);
                prepare3.setInt(3, p.getQuantite());
                
                
                prepare3.executeUpdate();
                
                prepare3.close();

                    
                    
                }
                
                this.connect.commit();
                
                        }
                
                
                res = true;
                
        }
        catch(SQLException e){
            e.printStackTrace();
            try {
                this.connect.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally{
            try {
                this.connect.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return res;
    }

    
    @Override
    
    public Commande read(String id) {
        
        
        Commande c1 = new Commande();
        
        int idCommande = Integer.parseInt(id);
        
        try{
            ResultSet result = this.connect.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT * FROM Commande WHERE idCommande = '" + idCommande + "'"
                                    );
            
            ResultSet resultPlat = this.connect.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT idPlat FROM PLAT WHERE idCommande= '"+ idCommande +"'"
                                    );
            
            List<Plat> lPlat = new ArrayList();
            while(resultPlat.next()){
                //lPlat.add();
            }
            
            ResultSet resultFilm = this.connect.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                            "SELECT idFilmCommande FROM Film_Commandes WHERE idCommande= '"+ idCommande +"'"
                                    );
           
            List<String> lFilm = new ArrayList();
            while(resultFilm.next()){
                lFilm.add(resultFilm.getString("idFilmCommande"));
            }
            /*
            if(result.first()){
                // c1 = new Commande(
                                idCommande,
                                result.getString("dateCommande"),
//                                result.getString("idClient"),
                                lPlat,
                                lFilm,
                                result.getString("noAdrLivraison"),
                                result.getString("rueAdrLivraison"),
                                result.getString("cpAdrLivraison"),
                                result.getString("villeAdrLivraison")
                );
            }*/
            
            result.close();
            resultPlat.close();
            resultFilm.close();
            
        }
        
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return c1;
       
    }
    
    
    
    public boolean existTest(int idCommande){
        
        boolean res = false;
        
        //Connection connect = TheConnection.getInstance(); //à changer
        
        try{
            ResultSet result = connect.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "SELECT idCommande FROM Commande WHERE idCommande = '" + idCommande + "'"
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
    
    public boolean updateFilms(Commande cmd1, Commande cmd2) {
        
        boolean res = false;
        
        try {
            
            for(String f : cmd1.getIdFilms()){
                    this.connect.createStatement().executeUpdate(
                                "DELETE FROM Film_Commandes WHERE idCommande = '" + cmd2.getId() + "' AND idFilmCommande ="
                                        + " '" + Integer.parseInt(f) + "'"
                        );
            }
            
            for(String f : cmd2.getIdFilms()){
                    PreparedStatement prepare2 = this.connect
                            .prepareStatement("INSERT INTO Film_Commandes (idFilmCommande, idCommande, prixFilm) VALUES (?, ?, ?)"
                );
                    
                    
                prepare2.setInt(1, Integer.parseInt(f));
                prepare2.setInt(2, cmd2.getId());
                prepare2.setDouble(3, 3.75);
                
                
                prepare2.executeUpdate();
                prepare2.close();
            
            
            }
 
                res = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
    
    public boolean updatePlats(Commande cmd1, Commande cmd2) {
        
        boolean res = false;
        
        try {
            
            for(Plat f : cmd1.getIdPlats()){
                    this.connect.createStatement().executeUpdate(
                                "DELETE FROM Plat WHERE idPlat = '" + f.getId() + "' AND idCommande ="
                                        + " '" + cmd2.getId() + "'"
                        );
            }
            
            for(Plat f : cmd2.getIdPlats()){
                    PreparedStatement prepare2 = this.connect
                            .prepareStatement("INSERT INTO Plat (idPlat, idCommande) VALUES (?, ?)"
                );
                    
                    
                prepare2.setString(1, f.getId());
                prepare2.setInt(2, cmd2.getId());
                
                prepare2.executeUpdate();
                prepare2.close();
            
            
            }
 
                res = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }


    
    
    
}
