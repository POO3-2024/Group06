package be.helha.projetrpg_groupe6.arme;

public class Arme {

    private String id;
    private String nom;
    private int degats;

    public Arme(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}
