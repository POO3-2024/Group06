package be.helha.apiprojetrpggroupe6.Database;

import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe CRUD pour les personnages de la base de données.
 */
public class PersonnageDatabase {

    private ConnectionDB connection;

    /**
     * Constructeur de la classe PersonnageDatabase.
     * Initialise la connexion à la base de données.
     *
     * @param connectionDB Instance de la connexion à la base de données.
     * @throws SQLException Si une erreur survient lors de la connexion à la base de données.
     * @throws ClassNotFoundException Si le driver de la base de données n'est pas trouvé.
     */
    public PersonnageDatabase(ConnectionDB connectionDB) throws SQLException, ClassNotFoundException {
        // Initialisation de la connexion à la base de données
        connection = ConnectionDB.getConnection();
    }

    /**
     * Récupère tous les personnages de la base de données.
     *
     * @return Une liste de tous les personnages.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public List<Personnage> getAllPersonnages() throws SQLException {
        List<Personnage> personnages = new ArrayList<>();

        // Requête pour récupérer tous les personnages
        String query = "SELECT * FROM personnage";

        try (ResultSet resultSet = connection.executeQuery(query)) {
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                int pv = resultSet.getInt("pv");
                int mana = resultSet.getInt("mana");
                Personnage personnage = new Personnage(nom, pv, mana);
                personnages.add(personnage);
            }
        }

        return personnages;
    }

    /**
     * Ajoute un nouveau personnage dans la base de données.
     *
     * @param perso Le personnage à ajouter.
     * @return Le personnage ajouté.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public Personnage add(Personnage perso) throws SQLException {
        String query = "INSERT INTO personnage (nom, pv, mana) VALUES ('" + perso.getNom() + "', " + perso.getPv() + ", " + perso.getMana() + ")";
        connection.executeUpdate(query);
        return perso;
    }

    /**
     * Supprime tous les personnages de la base de données.
     *
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public void clearAll() throws SQLException {
        // Requête pour supprimer tous les personnages
        String query = "DELETE FROM personnage";
        connection.executeUpdate(query);
    }
}