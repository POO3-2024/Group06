package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Database.PersonnageDatabase;
import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion de personnage
 */
@Service
public class PersonnageService {

    private PersonnageDatabase personnageDatabase;

    /**
     * Constructeur par défaut
     */
    public PersonnageService() {
        this.personnageDatabase = new PersonnageDatabase(ConnectionDB.getConnection());
    }

    /**
     * Constructeur pour les tests
     * @param test true si test
     */
    public PersonnageService(boolean test) {
        this.personnageDatabase = new PersonnageDatabase(ConnectionDB.getConnection(true));
    }
    /**
     * Récupère tous les personnages de la base de données
     * Renvoi uniquement l'id et le nom des personnages
     * @return Renvoi la liste des personnages
     */
    public List<PersonnageDTO>getPersonnages() throws SQLException {
        return this.personnageDatabase.getAllPersonnages();
    }
    /**
     * Récupère un personnage par son id
     * @param id id du personnage
     * @return Personnage Renvoi le personnage correspondant à l'id
     */
    public Personnage getPersonnageById(int id) throws SQLException {

        return this.personnageDatabase.getPersonnageById(id);
    }
    /**
     * Ajoute un personnage à la base de données
     * @param personnage Personnage à ajouter
     * @return Personnage Renvoi le personnage ajouté
     */
    public Personnage addPersonnage(Personnage personnage) throws SQLException {

        return this.personnageDatabase.add(personnage);
    }
    /**
     * Met à jour un personnage
     * @param personnage Personnage à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    public int updatePersonnage(Personnage personnage) throws SQLException {
        Personnage personnage1 = this.personnageDatabase.getPersonnageByName(personnage.getNom());
        if(personnage1 == null || personnage1.getId() == personnage.getId()){
            return this.personnageDatabase.update(personnage);
        }else{
            throw new SQLException("Personnage déjà existant");
        }
    }
    /**
     * Supprime un personnage
     * @param id id du personnage à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    public int deletePersonnage(int id) throws SQLException {
        return this.personnageDatabase.deletePersonnageById(id);
    }
}
