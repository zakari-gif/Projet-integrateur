/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemaMenu;

import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Plat 
{
    private String id;
    //private TypeDePlat type;
    private String nom;
    private String type;
    private double prix;
    private String photo;
    private ArrayList<String> ingredients;
    private int quantite;
    
    public Plat()
    {
        
    }
    

    public Plat(String id, String type, double prix, String photo, ArrayList<String> ingredients, int quantite) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.photo = photo;
        this.ingredients = ingredients;
        this.quantite = quantite;
    }
    
    public Plat(String id,String type, double prix, String photo,ArrayList<String> ingredients) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.photo = photo;
        this.ingredients = ingredients;
        quantite = 0;
    }
    
    public Plat(String id, int quantite, double prix) {
        this.id = id;
        this.quantite = quantite;
        type = null;
        this.prix = prix;
        photo = null;
        ingredients = null;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrix() {
        return prix;
    }

    public String getPhoto() {
        return photo;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    
    
    
    @Override
    public String toString() {
        String res = "{ ";
        res += "\"nom\" : \""+id+"\", ";
        res += "\"type\" : \""+type+"\", ";
        res += "\"prix\" : "+prix+", ";
        res += "\"photo\" : \""+photo+"\", ";
        res += "\"quantite\" : "+quantite+", ";
        res += "\"ingredients\" : [ ";
        for(String ing : ingredients) {
            res += "\""+ing+"\", ";
        }
        res = res.substring(0, res.length()-2);
        res += "] }";
        return res;
    }
}

