/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemaMenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JDufo
 */
public class Commande {
    private Integer id;
    private String idClient;
    private ArrayList<Plat> idPlats;
    private ArrayList<String> idFilms;
    private String noAdrLivraison;
    private String rueAdrLivraison;
    private String cpAdrLivraison;
    private String villeAdrLivraison;
    private double prix;
    private String date;

    public Commande(Integer id, String idClient, ArrayList<Plat> idPlats, ArrayList<String> idFilms, String noAdrLivraison, String rueAdrLivraison, String cpAdrLivraison, String villeAdrLivraison, double prix) {
        this.id = id;
        this.idClient = idClient;
        this.idPlats = idPlats;
        this.idFilms = idFilms;
        this.noAdrLivraison = noAdrLivraison;
        this.rueAdrLivraison = rueAdrLivraison;
        this.cpAdrLivraison = cpAdrLivraison;
        this.villeAdrLivraison = villeAdrLivraison;
        this.prix = prix;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.date = format.format(Calendar.getInstance().getTime());
    }
    public Commande(Integer id, String idClient, ArrayList<Plat> idPlats, ArrayList<String> idFilms, String noAdrLivraison, String rueAdrLivraison, String cpAdrLivraison, String villeAdrLivraison, double prix, String date) {
        this.id = id;
        this.idClient = idClient;
        this.idPlats = idPlats;
        this.idFilms = idFilms;
        this.noAdrLivraison = noAdrLivraison;
        this.rueAdrLivraison = rueAdrLivraison;
        this.cpAdrLivraison = cpAdrLivraison;
        this.villeAdrLivraison = villeAdrLivraison;
        this.prix = prix;
        this.date = date;
    }

    
    public Commande(){
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public ArrayList<Plat> getIdPlats() {
        return idPlats;
    }

    public void setIdPlats(ArrayList<Plat> idPlats) {
        this.idPlats = idPlats;
    }

    public ArrayList<String> getIdFilms() {
        return idFilms;
    }

    public void setIdFilms(ArrayList<String> idFilms) {
        this.idFilms = idFilms;
    }

    public String getNoAdrLivraison() {
        return noAdrLivraison;
    }

    public void setNoAdrLivraison(String noAdrLivraison) {
        this.noAdrLivraison = noAdrLivraison;
    }

    public String getRueAdrLivraison() {
        return rueAdrLivraison;
    }

    public void setRueAdrLivraison(String rueAdrLivraison) {
        this.rueAdrLivraison = rueAdrLivraison;
    }

    public String getCpAdrLivraison() {
        return cpAdrLivraison;
    }

    public void setCpAdrLivraison(String cpAdrLivraison) {
        this.cpAdrLivraison = cpAdrLivraison;
    }

    public String getVilleAdrLivraison() {
        return villeAdrLivraison;
    }

    public void setVilleAdrLivraison(String villeAdrLivraison) {
        this.villeAdrLivraison = villeAdrLivraison;
    }

    
    
    @Override
    public String toString() {
        String res = "{\"id\":" + "\"" + this.getId() + "\"" + 
                ", \"date\":" +  "\"" + this.getDate() + "\"" + 
                ", \"prix\":" +  prix + 
                //",\n \"idClient\":" + "\"" + this.getIdClient() + "\"" + 
                ", \"plats\":" + "[";
                
       for(Plat p : idPlats){
            res += p.toString() + ",";
        }
                res = res.substring(0, res.length()-1);
                res += "], \"films\":" + " [";
        for(String idFilm : this.idFilms) {
            res += idFilm + ",";
        }
         res = res.substring(0, res.length()-1);
         res += "]"+
                ", \"adresse\":"+" { \n"+
                " \"ville\":" + "\"" + this.getVilleAdrLivraison() + "\"" +
                ", \"cp\":" + "\"" + this.getCpAdrLivraison() + "\"" +
                ", \"rue\":" + "\"" + this.getRueAdrLivraison() + "\"" +
                ", \"numero\":" + "\"" + this.getNoAdrLivraison() + "\" } }";
         
         return res;
    }
    
    
}
