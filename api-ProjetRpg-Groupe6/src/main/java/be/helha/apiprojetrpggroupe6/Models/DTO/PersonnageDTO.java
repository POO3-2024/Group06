package be.helha.apiprojetrpggroupe6.Models.DTO;

public class PersonnageDTO {

    private int id;
    private String nom;

    public PersonnageDTO(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
