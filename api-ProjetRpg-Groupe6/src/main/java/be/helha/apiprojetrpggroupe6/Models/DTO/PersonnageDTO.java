package be.helha.apiprojetrpggroupe6.Models.DTO;

/**
 * DTO de la classe Personnage
 */
public class PersonnageDTO {

    private int id;
    private String nom;

    /**
     * Constructeur
     * @param id id du personnage
     * @param nom nom du personnage
     */
    public PersonnageDTO(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    /** Getter Nom PersonnageDTO
     *
     * @return nom du personnage
     */
    public String getNom() {
        return this.nom;
    }
    /** Setters Nom PersonnageDTO
     *
     * @param nom nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /** Getter Id PersonnageDTO
     *
     * @return id du personnage
     */
    public int getId() {
        return this.id;
    }
    /**Setters Id PersonnageDTO
     *
     * @param id id du personnage
     */

    public void setId(int id) {
        this.id = id;
    }
}
