package be.helha.projetrpg_groupe6.personnage;

import be.helha.projetrpg_groupe6.services.PersonnageService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Classe personnage
 */
public class Personnage {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("pv")
    @Expose
    private int pv;

    @SerializedName("mana")
    @Expose
    private int mana;

    /**
     * Constructeur personnage avec les attributs
     * @param nom
     * @param pv
     * @param mana
     */
    public Personnage(String nom, int pv, int mana) {
        this.nom = nom;
        this.pv = pv;
        this.mana = mana;
    }

    /**
     * Constructeur à partir d'un autre personnage
     * @param personnage
     */
    public Personnage(Personnage personnage){
        this.id = personnage.getId();
        this.nom = personnage.getNom();
        this.pv = personnage.getPv();
        this.mana = personnage.getMana();
    }

    /**
     * Constructeur de personnage de base
     * @param nom
     */
    public Personnage(String nom) {
        this.nom = nom;
        this.pv = 10;
        this.mana = 10;
    }

    /**
     * Fonction getId
     * @return l'id
     */
    public int getId() {
        return id;
    }

    /**
     * Fonction pour setter l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Fonction getNom
     * @return le nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * Fonction pour définir le nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Fonction getPv
     * @return les pv
     */
    public int getPv() {
        return pv;
    }
    /**
     * Fonction pour définir des pv
     * @param pv
     */
    public void setPv(int pv) {
        this.pv = pv;
    }
    /**
     * Fonction pour renvoyer le mana
     * @return le mana en question
     */
    public int getMana() {
        return mana;
    }
    /**
     * Fonction pour définir le mana
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }
}
