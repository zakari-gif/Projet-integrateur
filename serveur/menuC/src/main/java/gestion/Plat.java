/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Plat 
{
    private String idPlat;
    private String typePlat;
    private double prixPlat;
    private String photo;
    private ArrayList<String> ingredients;
    
    // --- getters ---
    
    public Plat()
    {
        
    }

    public String getIdPlat() 
    {
        return idPlat;
    }

    public String getTypePlat() 
    {
        return typePlat;
    }

    public double getPrixPlat() 
    {
        return prixPlat;
    }

    public String getPhoto() 
    {
        return photo;
    }

    public ArrayList<String> getIngredients() 
    {
        return ingredients;
    }
    
    // --- setters ---

    public void setIdPlat(String idPlat) 
    {
        this.idPlat = idPlat;
    }

    public void setTypePlat(String typePlat) 
    {
        this.typePlat = typePlat;
    }

    public void setPrixPlat(double prixPlat) 
    {
        this.prixPlat = prixPlat;
    }

    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public void setIngredients(ArrayList<String> ingredients) 
    {
        this.ingredients = ingredients;
    }
    
    public void ajoutIngredient(String ingredient)
    {
        this.ingredients.add(ingredient);
    }
}
