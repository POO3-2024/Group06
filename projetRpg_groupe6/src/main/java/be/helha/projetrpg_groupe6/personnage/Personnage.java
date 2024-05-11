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
}
