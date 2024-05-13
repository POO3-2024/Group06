package be.helha.apiprojetrpggroupe6.Models;

import be.helha.apiprojetrpggroupe6.Config.LimitValues;

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

        this.degats = degats < LimitValues.MIN_WEAPON_DAMAGE ? LimitValues.MIN_WEAPON_DAMAGE : degats > LimitValues.MAX_WEAPON_DAMAGE ? LimitValues.MAX_WEAPON_DAMAGE : degats;
    }
}
