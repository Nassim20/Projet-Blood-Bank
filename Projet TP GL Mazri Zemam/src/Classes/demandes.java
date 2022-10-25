package Classes;

import java.time.LocalDate;

public class demandes {
    String id_Demande;
    String nom_Prenom_Demande;
    String hopital_demande;
    String type_demande;
    String groupeS_demande;
    String state_demande;
    String urgentetat_demande;
    int nbrSac_dem;
    int contact_dem;
    LocalDate date_dem;

    public demandes(String id_Demande, String nom_Prenom_Demande, String hopital_demande, String type_demande, String groupeS_demande,
                    String state_demande, String urgentetat_demande, int nbrSac_dem, int contact_dem, LocalDate date_dem) {
        this.id_Demande = id_Demande;
        this.nom_Prenom_Demande = nom_Prenom_Demande;
        this.hopital_demande = hopital_demande;
        this.type_demande = type_demande;
        this.groupeS_demande = groupeS_demande;
        this.state_demande = state_demande;
        this.urgentetat_demande = urgentetat_demande;
        this.nbrSac_dem = nbrSac_dem;
        this.contact_dem = contact_dem;
        this.date_dem = date_dem;
    }

    public String getId_Demande() {
        return id_Demande;
    }

    public void setId_Demande(String id_Demande) {
        this.id_Demande = id_Demande;
    }

    public String getNom_Prenom_Demande() {
        return nom_Prenom_Demande;
    }

    public void setNom_Prenom_Demande(String nom_Prenom_Demande) {
        this.nom_Prenom_Demande = nom_Prenom_Demande;
    }

    public String getHopital_demande() {
        return hopital_demande;
    }

    public void setHopital_demande(String hopital_demande) {
        this.hopital_demande = hopital_demande;
    }

    public String getType_demande() {
        return type_demande;
    }

    public void setType_demande(String type_demande) {
        this.type_demande = type_demande;
    }

    public String getGroupeS_demande() {
        return groupeS_demande;
    }

    public void setGroupeS_demande(String groupeS_demande) {
        this.groupeS_demande = groupeS_demande;
    }

    public String getState_demande() {
        return state_demande;
    }

    public void setState_demande(String state_demande) {
        this.state_demande = state_demande;
    }

    public String getUrgentetat_demande() {
        return urgentetat_demande;
    }

    public void setUrgentetat_demande(String urgentetat_demande) {
        this.urgentetat_demande = urgentetat_demande;
    }

    public int getNbrSac_dem() {
        return nbrSac_dem;
    }

    public void setNbrSac_dem(int nbrSac_dem) {
        this.nbrSac_dem = nbrSac_dem;
    }

    public int getContact_dem() {
        return contact_dem;
    }

    public void setContact_dem(int contact_dem) {
        this.contact_dem = contact_dem;
    }

    public LocalDate getDate_dem() {
        return date_dem;
    }

    public void setDate_dem(LocalDate date_dem) {
        this.date_dem = date_dem;
    }
}
