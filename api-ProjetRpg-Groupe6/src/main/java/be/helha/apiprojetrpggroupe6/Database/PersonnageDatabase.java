package be.helha.apiprojetrpggroupe6.Database;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe PersonnageDatabase intéragit directement avec la base de données
 *
 * @author David-Lumu Dewever
 * @see be.helha.apiprojetrpggroupe6.Database.PersonnageDatabase
 */
public class PersonnageDatabase {

    /**
     * la connection à la db
     */
    private ConnectionDB connectionDB;
    /**
     * Constructeur de la classe
     * @param connectionDB Connection à la db
     */
    public PersonnageDatabase(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Fonction qui récupère tout les personnages de la db
     * @return une liste de personnageDTO contenant tout les personnages
     * @throws SQLException
     */
    public List<PersonnageDTO> getAllPersonnages() throws SQLException {
        List<PersonnageDTO> personnages = new ArrayList<>();
        String query = "SELECT * FROM personnage";

        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                PersonnageDTO personnage = new PersonnageDTO(resultSet.getInt("Id_personnage"), nom);
                personnages.add(personnage);
            }
        }

        return personnages;
    }

    /**
     * Fonction pour ajouter un personnage dans la db
     * @param perso à ajouter en db
     * @return Le personnage ajouté en db
     * @throws SQLException
     */
    public Personnage add(Personnage perso) throws SQLException {
        String query = "INSERT INTO personnage (nom, pv, mana) VALUES ('" + perso.getNom() + "', " + perso.getPv() + ", " + perso.getMana() + ")";
        connectionDB.executeUpdate(query);
        ResultSet res =  connectionDB.executeQuery("select max(Id_personnage) from personnage");
        res.next();
        perso.setId(res.getInt(1));
        return perso;
    }

    /**
     * Fonction qui retrouve un personnage depuis une id
     * @param id du personnage à retrouver
     * @return le personnage si il éxiste, si pas return null
     * @throws SQLException
     */
    public Personnage getPersonnageById(int id) throws SQLException {
        String query = "SELECT * FROM personnage WHERE Id_personnage = " + id;
        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
            if (resultSet.next()) {
                return new Personnage(resultSet.getInt("Id_personnage"), resultSet.getString("nom"), resultSet.getInt("mana"), resultSet.getInt("pv"));
            }
        }
        return null;
    }

    /**
     * Fonction qui retrouve un personnage depuis un nom
     * @param name
     * @return le personnage si il éxiste, si pas return null
     * @throws SQLException
     */
    public Personnage getPersonnageByName(String name) throws SQLException {
        String query = "SELECT * FROM personnage WHERE nom = '" + name + "'";
        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
            if (resultSet.next()) {
                return new Personnage(resultSet.getInt("Id_personnage"), resultSet.getString("nom"), resultSet.getInt("mana"), resultSet.getInt("pv"));
            }
        }
        return null;
    }

    /**
     * Fonction qui met à jour un personnage
     * @param perso
     * @return 0 si il y a un problème
     * @throws SQLException
     */
    public int update(Personnage perso) throws SQLException {
        String query = "UPDATE personnage SET pv = " + perso.getPv() + ", mana = " + perso.getMana() + ", nom = '" + perso.getNom() + "' WHERE Id_personnage = " + perso.getId();
        return connectionDB.executeUpdate(query);
    }

    /**
     * Fonction qui supprime un personnage
     * @param id
     * @return 0 si il y a un problème
     * @throws SQLException
     */
    public int deletePersonnageById(int id) throws SQLException {
        String query = "DELETE FROM personnage WHERE Id_personnage = " + id;
        return connectionDB.executeUpdate(query);
    }
    /**
     * Fonction qui supprime tout les personnages
     * @throws SQLException
     */
    public void clearAll() throws SQLException {
        String query = "DELETE FROM personnage";
        connectionDB.executeUpdate(query);
    }
}
