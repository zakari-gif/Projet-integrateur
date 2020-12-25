/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemaMenu;

/**
 *
 * @author JDufo
 */
public enum Ingredient {
    TOMATE(1,"tomate"),
    MOZZARELLA(2, "mozzarella"),
    SALADE(3,"salade"),
    OIGNONS(4,"oignons"),
    OLIVES(5,"olive"),
    THON(6,"thon");
    private int idIngre;
    private String nomIngre;
    
    
    //a voir

    private Ingredient(int idIngre, String nomIngre) {
        this.idIngre = idIngre;
        this.nomIngre = nomIngre;
    }

    public int getIdIngre() {
        return idIngre;
    }

    public void setIdIngre(int idIngre) {
        this.idIngre = idIngre;
    }

    public String getNomIngre() {
        return nomIngre;
    }

    public void setNomIngre(String nomIngre) {
        this.nomIngre = nomIngre;
    }

    public static Ingredient getTOMATE() {
        return TOMATE;
    }

    public static Ingredient getMOZZARELLA() {
        return MOZZARELLA;
    }

    public static Ingredient getSALADE() {
        return SALADE;
    }

    public static Ingredient getOIGNONS() {
        return OIGNONS;
    }

    public static Ingredient getOLIVES() {
        return OLIVES;
    }

    public static Ingredient getTHON() {
        return THON;
    }

    
    
    
    
    
}