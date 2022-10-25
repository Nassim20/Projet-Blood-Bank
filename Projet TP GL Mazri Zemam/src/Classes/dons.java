package Classes;

import java.time.LocalDate;

public class dons {
    private int idDon;
    private String nomDon;
    private String nomDoc;
    private String nomHop;
    private String groupS;
    private String typeDon;
    private LocalDate dateDon;
    private int nbr_sacDon;

    public dons(int idDon, String nomDon, String nomDoc, String nomHop, String groupS, String typeDon, LocalDate dateDon, int nbr_sacDon) {
        this.idDon = idDon;
        this.nomDon = nomDon;
        this.nomDoc = nomDoc;
        this.nomHop = nomHop;
        this.groupS = groupS;
        this.typeDon = typeDon;
        this.dateDon = dateDon;
        this.nbr_sacDon = nbr_sacDon;
    }

    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public String getNomDon() {
        return nomDon;
    }

    public void setNomDon(String nomDon) {
        this.nomDon = nomDon;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getNomHop() {
        return nomHop;
    }

    public void setNomHop(String nomHop) {
        this.nomHop = nomHop;
    }

    public String getGroupS() {
        return groupS;
    }

    public void setGroupS(String groupS) {
        this.groupS = groupS;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public LocalDate getDateDon() {
        return dateDon;
    }

    public void setDateDon(LocalDate dateDon) {
        this.dateDon = dateDon;
    }

    public int getNbr_sacDon() {
        return nbr_sacDon;
    }

    public void setNbr_sacDon(int nbr_sacDon) {
        this.nbr_sacDon = nbr_sacDon;
    }
}
