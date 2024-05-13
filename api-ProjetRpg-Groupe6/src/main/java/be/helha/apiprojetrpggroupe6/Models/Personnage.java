package be.helha.apiprojetrpggroupe6.Models;

import be.helha.apiprojetrpggroupe6.Config.LimitValues;

public class Personnage {

    private int id;
    private String nom;
    private int mana;
    private int pv;


    public Personnage(int id, String nom, int mana, int pv) {
        this.id = id;
        this.nom = nom;
        this.setMana(mana);
        this.setPv(pv);
    }

    public Personnage(String nom, int mana, int pv) {
        this.nom = nom;
        this.setPv(pv);
        this.setMana(mana);
    }
    public Personnage() {}

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
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana < LimitValues.MIN_CHARACTER_MANA ? LimitValues.MIN_CHARACTER_MANA : mana > LimitValues.MAX_CHARACTER_MANA ? LimitValues.MAX_CHARACTER_MANA : mana;
    }
    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv < LimitValues.MIN_CHARACTER_HP ? LimitValues.MIN_CHARACTER_HP : pv > LimitValues.MAX_CHARACTER_HP ? LimitValues.MAX_CHARACTER_HP : pv;
    }
}
