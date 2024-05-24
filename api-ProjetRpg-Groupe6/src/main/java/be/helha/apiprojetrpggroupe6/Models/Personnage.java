package be.helha.apiprojetrpggroupe6.Models;

import be.helha.apiprojetrpggroupe6.Config.LimitValues;

/**
 * Classe Arme
 */
public class Personnage {

    private int id;
    private String nom;
    private int mana;
    private int pv;


    /**
     * Constructeur
     * @param id id du personnage
     * @param nom nom du personnage
     * @param mana mana du personnage
     * @param pv points de vie du personnage
     */
    public Personnage(int id, String nom, int mana, int pv) {
        this.id = id;
        this.nom = nom;
        this.mana = mana;
        this.pv = pv;
    }

    /**
     * Constructeur
     * @param nom nom du personnage
     * @param mana mana du personnage
     * @param pv points de vie du personnage
     */
    public Personnage(String nom, int mana, int pv) {
        this.nom = nom;
        this.mana = mana;
        this.pv = pv;
    }
    /**
     * Constructeur vide
     */
    public Personnage() {}

    /** Getter Id Personnage
     *
     * @return id du personnage
     */
    public int getId() {
        return id;
    }
    /** Setters Id Personnage
     *
     * @param id id du personnage
     */
    public void setId(int id) {
        this.id = id;
    }
    /** Getters Nom Personnage
     *
     * @return nom du personnage
     */
    public String getNom() {
        return nom;
    }
    /** Setters Nom Personnage
     *
     * @param nom nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /** Getter Mana Personnage
     *
     * @return mana du personnage
     */
    public int getMana() {
        return mana;
    }
    /**
     * Set le mana du personnage
     * Le niveau de mana est limité entre deux valeurs
     * @see LimitValues
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana < LimitValues.MIN_CHARACTER_MANA ? LimitValues.MIN_CHARACTER_MANA : mana > LimitValues.MAX_CHARACTER_MANA ? LimitValues.MAX_CHARACTER_MANA : mana;
    }
    /** Getter Pv Personnage
     *
     * @return points de vie du personnage
     */
    public int getPv() {
        return pv;
    }
    /**
     * Set les points de vie du personnage
     * Les points de vie sont limités entre deux valeurs
     * @see LimitValues
     * @param pv
     */
    public void setPv(int pv) {
        this.pv = pv < LimitValues.MIN_CHARACTER_HP ? LimitValues.MIN_CHARACTER_HP : pv > LimitValues.MAX_CHARACTER_HP ? LimitValues.MAX_CHARACTER_HP : pv;
    }
    /**
     * Diminue les points de vie du personnage
     * @param degats Valeur des dégats infligés
     */
    public void infligerDegats(int degats){
        this.pv = this.pv - degats < 0 ? 0 : this.pv - degats;
    }
}
