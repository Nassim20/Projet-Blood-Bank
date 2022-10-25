package Classes;

import java.sql.Date;
import java.time.LocalDate;

public class stockedBlood{
    private int num_pac;
    private String nom_donneur;
    private String group_sang;
    private String type_don;
    private LocalDate date_don;
    private int nbr_sac;

    public stockedBlood(int num_pac, String nom_donneur, String group_sang, String type_don, LocalDate date_don, int nbr_sac) {
        this.num_pac = num_pac;
        this.nom_donneur = nom_donneur;
        this.group_sang = group_sang;
        this.type_don = type_don;
        this.date_don = date_don;
        this.nbr_sac = nbr_sac;
    }

    public int getNbr_sac() {
        return nbr_sac;
    }

    public void setNbr_sac(int nbr_sac) {
        this.nbr_sac = nbr_sac;
    }

    public int getNum_pac() {
        return num_pac;
    }

    public void setNum_pac(int num_pac) {
        this.num_pac = num_pac;
    }

    public String getNom_donneur() {
        return nom_donneur;
    }

    public void setNom_donneur(String nom_donneur) {
        this.nom_donneur = nom_donneur;
    }

    public String getGroup_sang() {
        return group_sang;
    }

    public void setGroup_sang(String group_sang) {
        this.group_sang = group_sang;
    }

    public String getType_don() {
        return type_don;
    }

    public void setType_don(String type_don) {
        this.type_don = type_don;
    }

    public LocalDate getDate_don() {
        return date_don;
    }

    public void setDate_don(LocalDate date_don) {
        this.date_don = date_don;
    }
}
