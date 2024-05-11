package be.helha.projetrpg_groupe6.personnage;

public class Personnage {

   private String id;
   private String nom;
   private int pv;
   private int mana;

    public Personnage(String nom, int pv, int mana) {
        this.nom = nom;
        this.pv = pv;
        this.mana = mana;
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

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
