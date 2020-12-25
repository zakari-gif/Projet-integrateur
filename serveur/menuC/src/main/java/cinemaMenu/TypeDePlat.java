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
public enum TypeDePlat {
    ENTREE(1,"entree"),
    PLAT(2, "plat"),
    DESSERT(3, "dessert"),
    BOISSON(4, "boisson");
    
    private int idTypePlat;
    private String nomTypePlat;

    private TypeDePlat(int idTypePlat, String nomTypePlat) {
        this.idTypePlat = idTypePlat;
        this.nomTypePlat = nomTypePlat;
    }

    public int getIdTypePlat() {
        return idTypePlat;
    }

    public void setIdTypePlat(int idTypePlat) {
        this.idTypePlat = idTypePlat;
    }

    public String getNomTypePlat() {
        return nomTypePlat;
    }

    public void setNomTypePlat(String nomTypePlat) {
        this.nomTypePlat = nomTypePlat;
    }

    public static TypeDePlat getENTREE() {
        return ENTREE;
    }

    public static TypeDePlat getPLAT() {
        return PLAT;
    }

    public static TypeDePlat getDESSERT() {
        return DESSERT;
    }

    public static TypeDePlat getBOISSON() {
        return BOISSON;
    }
    
    
    
}
