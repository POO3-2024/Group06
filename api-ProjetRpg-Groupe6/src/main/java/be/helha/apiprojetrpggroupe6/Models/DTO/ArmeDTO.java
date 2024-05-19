package be.helha.apiprojetrpggroupe6.Models.DTO;

/**
 * DTO de la classe Arme
 */
public class ArmeDTO {

    private int id;
    private String nom;

    /**
     * Constructeur
     * @param id id de l'arme
     * @param nom nom de l'arme
     */
    public ArmeDTO(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    /** Getter Nom ArmeDTO
     *
     * @return nom de l'arme
     */
    public String getNom() {
        return this.nom;
    }

    /** Setters Nom ArmeDTO
     *
     * @param nom nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /** Getter Id ArmeDTO
     *
     * @return id de l'arme
     */
    public int getId() {
        return this.id;
    }
    /**Setters Id ArmeDTO
     *
     * @param id id de l'arme
     */
    public void setId(int id) {
        this.id = id;
    }
}
