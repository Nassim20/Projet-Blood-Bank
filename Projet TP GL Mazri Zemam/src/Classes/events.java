package Classes;

import java.time.LocalDate;

public class events {


    private String id_Event;
    private String nom_Event;
    private String nom_Res;
    private String location;
    private String type_event;
    private LocalDate date_event;

    public events(String id_Event, String nom_Event, String nom_Res, String location, String type_event, LocalDate date_event, String heur_event) {
        this.id_Event = id_Event;
        this.nom_Event = nom_Event;
        this.nom_Res = nom_Res;
        this.location = location;
        this.type_event = type_event;
        this.date_event = date_event;
        this.heur_event = heur_event;
    }

    public String getId_Event() {
        return id_Event;
    }

    public void setId_Event(String id_Event) {
        this.id_Event = id_Event;
    }

    public String getNom_Event() {
        return nom_Event;
    }

    public void setNom_Event(String nom_Event) {
        this.nom_Event = nom_Event;
    }

    public String getNom_Res() {
        return nom_Res;
    }

    public void setNom_Res(String nom_Res) {
        this.nom_Res = nom_Res;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public LocalDate getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDate date_event) {
        this.date_event = date_event;
    }

    public String getHeur_event() {
        return heur_event;
    }

    public void setHeur_event(String heur_event) {
        this.heur_event = heur_event;
    }

    private String heur_event;



}
