package be.helha.apiprojetrpggroupe6.Models;

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
        this.mana = mana < 0 ? 0 : mana > 100 ? 100 : mana;
    }
    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv < 0 ? 0 : pv > 1000 ? 1000 : pv;
    }
}
