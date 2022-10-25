package Classes;

import java.time.LocalDate;

public class dateHistoriques {
    private int id_donneur;
    private String nom_donneur;
    private String Hopital;
    private String groupe_sanguin;
    private String typeDon;
    private LocalDate date_collecte;

    public dateHistoriques(int id_donneur, String nom_donneur, String hopital, String groupe_sanguin,  String typeDon ,LocalDate date_collecte) {
        this.id_donneur = id_donneur;
        this.nom_donneur = nom_donneur;
        Hopital = hopital;
        this.groupe_sanguin = groupe_sanguin;
        this.typeDon = typeDon;
        this.date_collecte = date_collecte;
    }

    public int getId_donneur() {
        return id_donneur;
    }

    public void setId_donneur(int id_donneur) {
        this.id_donneur = id_donneur;
    }

    public String getNom_donneur() {
        return nom_donneur;
    }

    public void setNom_donneur(String nom_donneur) {
        this.nom_donneur = nom_donneur;
    }

    public String getHopital() {
        return Hopital;
    }

    public void setHopital(String hopital) {
        Hopital = hopital;
    }

    public String getGroupe_sanguin() {
        return groupe_sanguin;
    }

    public void setGroupe_sanguin(String groupe_sanguin) {
        this.groupe_sanguin = groupe_sanguin;
    }

    public LocalDate getDate_collecte() {
        return date_collecte;
    }

    public void setDate_collecte(LocalDate date_collecte) {
        this.date_collecte = date_collecte;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }
}
