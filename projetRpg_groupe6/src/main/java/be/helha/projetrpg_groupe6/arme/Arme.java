package be.helha.projetrpg_groupe6.arme;

public class Arme {

    private int id;
    private String nom;
    private int degats;

    public Arme(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public Arme(int id,String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
