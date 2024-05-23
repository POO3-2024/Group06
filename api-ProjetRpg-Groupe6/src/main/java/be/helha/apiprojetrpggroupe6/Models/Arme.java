package be.helha.apiprojetrpggroupe6.Models;

import be.helha.apiprojetrpggroupe6.Config.LimitValues;

/**
 * Classe Arme
 */
public class Arme {

    private int id;
    private String nom;
    private int degats;

    /**
     * Constructeur
     * @param id id de l'arme
     * @param nom nom de l'arme
     * @param degats dégats de l'arme
     */
    public Arme(int id, String nom, int degats) {
        this.id = id;
        this.nom = nom;
        this.setDegats(degats);
    }
    /**
     * Constructeur
     * @param nom nom de l'arme
     * @param degats dégats de l'arme
     */
    public Arme(String nom, int degats) {
        this.nom = nom;
        this.setDegats(degats);
    }
    /**
     * Constructeur vide
     */
    public Arme() {}

    /** Getter Id Arme
     *
     * @return id de l'arme
     */
    public int getId() {
        return id;
    }
    /**cSetters Id Arme
     *
     * @param id id de l'arme
     */
    public void setId(int id) {
        this.id = id;
    }
    /** Getters Nom Arme
     *
     * @return nom de l'arme
     */
    public String getNom() {
        return nom;
    }
    /** Setters Nom Arme
     *
     * @param nom nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /** Getters Degats Arme
     *
     * @return dégats de l'arme
     */
    public int getDegats() {
        return degats;
    }
    /**
     * Set les dégats de l'arme
     * Les dégats sont limités entre deux valeurs
     * @see LimitValues
     * @param degats
     */
    public void setDegats(int degats) {

        this.degats = degats < LimitValues.MIN_WEAPON_DAMAGE ? LimitValues.MIN_WEAPON_DAMAGE : degats > LimitValues.MAX_WEAPON_DAMAGE ? LimitValues.MAX_WEAPON_DAMAGE : degats;
    }
}
