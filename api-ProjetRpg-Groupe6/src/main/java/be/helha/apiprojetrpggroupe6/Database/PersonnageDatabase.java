package be.helha.apiprojetrpggroupe6.Database;

import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnageDatabase {

    private ConnectionDB connectionDB;

    public PersonnageDatabase(ConnectionDB connectionDB) throws SQLException, ClassNotFoundException {
        this.connectionDB = ConnectionDB.getConnection();
    }

    public List<Personnage> getAllPersonnages() throws SQLException {
        List<Personnage> personnages = new ArrayList<>();
        String query = "SELECT * FROM personnage";

        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
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

    public Personnage add(Personnage perso) throws SQLException {
        String query = "INSERT INTO personnage (nom, pv, mana) VALUES ('" + perso.getNom() + "', " + perso.getPv() + ", " + perso.getMana() + ")";
        connectionDB.executeUpdate(query);
        return perso;
    }

    public Personnage update(Personnage perso) throws SQLException {
        String query = "UPDATE personnage SET pv = " + perso.getPv() + ", mana = " + perso.getMana() + " WHERE nom = '" + perso.getNom() + "'";
        connectionDB.executeUpdate(query);
        return perso;
    }

    public void delete(String nom) throws SQLException {
        String query = "DELETE FROM personnage WHERE nom = '" + nom + "'";
        connectionDB.executeUpdate(query);
    }

    public void clearAll() throws SQLException {
        String query = "DELETE FROM personnage";
        connectionDB.executeUpdate(query);
    }
}
