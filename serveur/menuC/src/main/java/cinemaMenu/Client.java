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
public class Client {
    private String idClient;
    private String nomClient;
    private String prenomClient;
    private String photoClient;
    private String emailClient;
    private String telClient;
    private String noAdrClient;
    private String rueAdrClient;
    private String cpAdrClient;
    private String villeAdrClient;

    public Client(String idClient, String nomClient, String prenomClient, String photoClient, String emailClient, String telClient, String noAdrClient, String rueAdrClient, String cpAdrClient, String villeAdrClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.photoClient = photoClient;
        this.emailClient = emailClient;
        this.telClient = telClient;
        this.noAdrClient = noAdrClient;
        this.rueAdrClient = rueAdrClient;
        this.cpAdrClient = cpAdrClient;
        this.villeAdrClient = villeAdrClient;
    }
    
    public Client(String idClient, String nomClient, String prenomClient, String photoClient, String emailClient, String telClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.photoClient = photoClient;
        this.emailClient = emailClient;
        this.telClient = telClient;
    }

    @Override
    public String toString() {
        return "{\"id\":" + "\"" + this.getIdClient() + "\"" + 
                ", \"mail\":" +  "\"" + this.getEmailClient() + "\"" + 
                ", \"nom\":" + "\"" + this.getNomClient() + "\"" + 
                ", \"prenom\":" + "\"" + this.getPrenomClient() + "\"" +
                ", \"tel\":" + "\"" + this.getTelClient() + "\"" +
                ", \"photo\":" + "\"" + this.getPhotoClient() + "\"" +
                ", \"adresse\":"+" { "+
                " \"ville\":" + "\"" + this.getVilleAdrClient() + "\"" +
                ", \"cp\":" + "\"" + this.getCpAdrClient() + "\"" +
                ", \"rue\":" + "\"" + this.getRueAdrClient() + "\"" +
                ", \"numero\":" + "\"" + this.getNoAdrClient() + "\" } }";
    }
    
    public Client() {
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getPhotoClient() {
        return photoClient;
    }

    public void setPhotoClient(String photoClient) {
        this.photoClient = photoClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public String getNoAdrClient() {
        return noAdrClient;
    }

    public void setNoAdrClient(String noAdrClient) {
        this.noAdrClient = noAdrClient;
    }

    public String getRueAdrClient() {
        return rueAdrClient;
    }

    public void setRueAdrClient(String rueAdrClient) {
        this.rueAdrClient = rueAdrClient;
    }

    public String getCpAdrClient() {
        return cpAdrClient;
    }

    public void setCpAdrClient(String cpAdrClient) {
        this.cpAdrClient = cpAdrClient;
    }

    public String getVilleAdrClient() {
        return villeAdrClient;
    }

    public void setVilleAdrClient(String villeAdrClient) {
        this.villeAdrClient = villeAdrClient;
    }

    
    
}