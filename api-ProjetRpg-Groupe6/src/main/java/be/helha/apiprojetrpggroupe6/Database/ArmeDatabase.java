package be.helha.apiprojetrpggroupe6.Database;


import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe fournit des méthodes pour interagir avec la base de données des armes.
 */
public class ArmeDatabase {


    private ConnectionDB connection;
    /**
     * Constructeur pour ArmeDatabase.
     *
     * @param connection la connexion à la base de données à utiliser.
     */
    public ArmeDatabase (ConnectionDB connection) {

        this.connection = connection;
    }
    /**
     * Récupère la liste de toutes les armes dans la base de données.
     *
     * @return une liste d'objets Arme.
     * @throws SQLException si une erreur de base de données survient.
     */
    public List<Arme> getArme() throws SQLException {
        List<Arme> list = new ArrayList<>();
        String query = "SELECT * FROM arme";

        try {
            ResultSet resultSet = connection.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("Id_arme");
                String nom = resultSet.getString("Nom");
                int degats = resultSet.getInt("Degats");
                Arme arme =  new Arme(id,nom,degats);
                list.add(arme);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return list;
    }
    /**
     * Ajoute une nouvelle arme à la base de données.
     *
     * @param arme l'objet Arme à ajouter.
     * @throws SQLException si une erreur de base de données survient.
     */
    public void addArme(Arme arme) throws SQLException {
        String query = "INSERT INTO arme (Nom, Degats) VALUES ('"+arme.getNom()+"','"+arme.getDegats()+"')";
        try {
            connection.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("SQL Exception during insert: " + e.getMessage());
            throw e;
        }
    }

}
