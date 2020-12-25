package l3m;

import cinemaMenu.Client;
import cinemaMenu.Commande;
import dao.ClientDAO;
import dao.CommandeDAO;
import java.sql.*;
import java.util.ArrayList;

// Je suis passé par l'itération 0 de la BD...
public class BdAccess {
    
    private static ClientDAO clientDao = new ClientDAO();
    private static CommandeDAO commandeDao = new CommandeDAO();
    static String authentifyUser(String userId) throws SQLException {

        String res = "";
        
        clientDao.read(userId);

        res = clientDao.read(userId).toString();

        return res;
    }
    
    static String addUser(Client cli) throws SQLException {

        String res = "";
                
        clientDao.create(cli);
        
        res = cli.toString();

        return res;
    }
    
    static String updateUser(Client cli) throws SQLException {
        String res = "";
        
        clientDao.update(cli);
        
        res = cli.toString();
        
        return res;
    }
    
    static String addCommande(Commande cmd) throws SQLException {
        String res = "";
        
        commandeDao.create(cmd);
        
        res = "Votre commande a été enregistrée|"+cmd.getId()+"|"+cmd.getDate();
        return res;
    }
    
    static String getClientCommandes(String idClient) throws SQLException{
        String res = "[";
        
        ArrayList<Commande> commandes = commandeDao.readCommandeClient(idClient);
        if(commandes.size()>0) {
            for(Commande c : commandes) {
            res+=c.toString()+",";
        }
        res = res.substring(0,res.length()-1);
        }
        
        res += "]";
        return res;
    }
    
    
    

}
