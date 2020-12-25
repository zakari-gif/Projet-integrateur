/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemaMenu;

import java.util.List;

/**
 *
 * @author Acer
 */
public class Carte {
    private List<Plat> plats;

    public Carte(List<Plat> plats) {
        this.plats = plats;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }
    
    @Override
    public String toString() {
        String res = "{ \"plats\" : [";
        for(Plat p : plats) {
            res += p.toString()+ ", ";
        }
        res = res.substring(0,res.length()-2);
        res += "] }";
        return res;
    }
    
}