package Classes;

public class donneurs {
    private int id_donneur;
    private String nom_donneur;
    private int contact_donneur;
    private String mail_donneur;
    private int age_donneur;
    private String gender_donneur;
    private String sang_donneur;

    public donneurs(int id_donneur, String nom_donneur, int contact_donneur, String mail_donneur, int age_donneur, String gender_donneur, String sang_donneur) {
        this.id_donneur = id_donneur;
        this.nom_donneur = nom_donneur;
        this.contact_donneur = contact_donneur;
        this.mail_donneur = mail_donneur;
        this.age_donneur = age_donneur;
        this.gender_donneur = gender_donneur;
        this.sang_donneur = sang_donneur;
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

    public int getContact_donneur() {
        return contact_donneur;
    }

    public void setContact_donneur(int contact_donneur) {
        this.contact_donneur = contact_donneur;
    }

    public String getMail_donneur() {
        return mail_donneur;
    }

    public void setMail_donneur(String mail_donneur) {
        this.mail_donneur = mail_donneur;
    }

    public int getAge_donneur() {
        return age_donneur;
    }

    public void setAge_donneur(int age_donneur) {
        this.age_donneur = age_donneur;
    }

    public String getGender_donneur() {
        return gender_donneur;
    }

    public void setGender_donneur(String gender_donneur) {
        this.gender_donneur = gender_donneur;
    }

    public String getSang_donneur() {
        return sang_donneur;
    }

    public void setSang_donneur(String sang_donneur) {
        this.sang_donneur = sang_donneur;
    }
}
