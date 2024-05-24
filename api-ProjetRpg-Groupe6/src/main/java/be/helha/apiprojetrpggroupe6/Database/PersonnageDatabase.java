package be.helha.apiprojetrpggroupe6.Database;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de gérer les requêtes SQL pour la table personnage.
 */
public class PersonnageDatabase {

    /**
     * La connexion à la base de données à utiliser.
     */
    private ConnectionDB connectionDB;

    /**
     * Constructeur pour PersonnageDatabase.
     * @param connectionDB
     */
    public PersonnageDatabase(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Récupère la liste de tous les personnages dans la base de données.
     * @return une liste d'objets PersonnageDTO.
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
     * Ajoute un personnage à la base de données.
     * @param perso le personnage à ajouter.
     * @return Personnage le personnage ajouté.
     * @throws SQLException si une erreur de base de données survient.
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
     * Récupère un personnage par son id.
     * @param id l'id du personnage.
     * @return Personnage le personnage correspondant à l'id.
     * @throws SQLException si une erreur de base de données survient.
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
     * Récupère un personnage par son nom.
     * @param name le nom du personnage.
     * @return Personnage l'objet Personna ge correspondant au nom.
     * @throws SQLException si une erreur de base de données survient.
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
     * Met à jour un personnage.
     * @param perso le personnage à mettre à jour.
     * @return int le nombre de lignes modifiées.
     * @throws SQLException     si une erreur de base de données survient.
     */
    public int update(Personnage perso) throws SQLException {
        String query = "UPDATE personnage SET pv = " + perso.getPv() + ", mana = " + perso.getMana() + ", nom = '" + perso.getNom() + "' WHERE Id_personnage = " + perso.getId();
        return connectionDB.executeUpdate(query);
    }

    /**
     * Supprime un personnage par son id.
     * @param id l'id du personnage à supprimer.
     * @return int le nombre de lignes supprimées.
     * @throws SQLException si une erreur de base de données survient.
     */
    public int deletePersonnageById(int id) throws SQLException {
        String query = "DELETE FROM personnage WHERE Id_personnage = " + id;
        return connectionDB.executeUpdate(query);
    }

    /**
     * Supprime tous les personnages de la base de données.
     * @throws SQLException si une erreur de base de données survient.
     */
    public void clearAll() throws SQLException {
        String query = "DELETE FROM personnage";
        connectionDB.executeUpdate(query);
    }
}
