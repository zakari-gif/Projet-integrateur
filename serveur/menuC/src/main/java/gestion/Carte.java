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
public class Carte 
{
    private ArrayList<Plat> lesPlats;

    public ArrayList<Plat> getLesPlats() 
    {
        return lesPlats;
    }

    public void setLesPlats(ArrayList<Plat> lesPlats) 
    {
        this.lesPlats = lesPlats;
    }
    
    public void ajoutePlat(Plat lePlat)
    {
        this.lesPlats.add(lePlat);
    }
    
    
}
