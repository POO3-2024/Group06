package be.helha.projetrpg_groupe6.models;

import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.personnage.Personnage;

public class Partie {

    private Personnage personnage_joueur1;
    private Personnage personnage_joueur2;
    private Boolean joueur1_actif;
    private Arme arme_joueur1;
    private Arme arme_joueur2;

    public Partie(){
        this.joueur1_actif = true;
    }
    public Personnage getPersonnage1() {
        return personnage_joueur1;
    }
    public void setPersonnage1(Personnage personnage1) {
        this.personnage_joueur1 = personnage1;
    }
    public Personnage getPersonnage2() {
        return personnage_joueur2;
    }
    public void setPersonnage2(Personnage personnage2) {
        this.personnage_joueur2 = personnage2;
    }
    public Boolean getJoueur1_actif() {
        return joueur1_actif;
    }
    public void skipTurn() {
        this.joueur1_actif = !this.joueur1_actif;
    }
    public Arme getArme1() {
        return arme_joueur1;
    }
    public void setArme1(Arme arme1) {
        this.arme_joueur1 = arme1;
    }
    public Arme getArme2() {
        return arme_joueur2;
    }
    public void setArme2(Arme arme2) {
        this.arme_joueur2 = arme2;
    }
}
