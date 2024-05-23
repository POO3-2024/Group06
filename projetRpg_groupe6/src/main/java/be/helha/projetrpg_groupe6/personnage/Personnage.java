package be.helha.projetrpg_groupe6.personnage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public Personnage(String nom, int pv, int mana) {
        this.nom = nom;
        this.pv = pv;
        this.mana = mana;
    }

    public Personnage(String nom) {
        this.nom = nom;
        this.pv = 10;
        this.mana = 10;
    }

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
