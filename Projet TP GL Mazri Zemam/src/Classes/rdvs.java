package Classes;

import java.time.LocalDate;

public class rdvs {
    private String idRdv;
    private String nomRdv;
    private String prenomRdv;
    private LocalDate dateRdv;
    private String heurRdv;

    public rdvs(String idRdv, String nomRdv, String prenomRdv, LocalDate dateRdv, String heurRdv) {
        this.idRdv = idRdv;
        this.nomRdv = nomRdv;
        this.prenomRdv = prenomRdv;
        this.dateRdv = dateRdv;
        this.heurRdv = heurRdv;
    }

    public String getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(String idRdv) {
        this.idRdv = idRdv;
    }

    public String getNomRdv() {
        return nomRdv;
    }

    public void setNomRdv(String nomRdv) {
        this.nomRdv = nomRdv;
    }

    public String getPrenomRdv() {
        return prenomRdv;
    }

    public void setPrenomRdv(String prenomRdv) {
        this.prenomRdv = prenomRdv;
    }

    public LocalDate getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(LocalDate dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getHeurRdv() {
        return heurRdv;
    }

    public void setHeurRdv(String heurRdv) {
        this.heurRdv = heurRdv;
    }
}
