package be.helha.apiprojetrpggroupe6.Models;

public class Arme {

    private int id;
    private String nom;
    private int degats;

    public Arme(int id, String nom, int degats) {
        this.id = id;
        this.nom = nom;
        this.setDegats(degats);
    }
    public Arme(String nom, int degats) {
        this.nom = nom;
        this.setDegats(degats);
    }
    public Arme() {}

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

        this.degats = degats < 0 ? 0 : degats > 100 ? 100 : degats;
    }
}
