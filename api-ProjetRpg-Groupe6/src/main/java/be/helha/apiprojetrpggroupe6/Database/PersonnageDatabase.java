package be.helha.apiprojetrpggroupe6.Database;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnageDatabase {

    private ConnectionDB connectionDB;

    public PersonnageDatabase(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

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

    public Personnage add(Personnage perso) throws SQLException {
        String query = "INSERT INTO personnage (nom, pv, mana) VALUES ('" + perso.getNom() + "', " + perso.getPv() + ", " + perso.getMana() + ")";
        connectionDB.executeUpdate(query);
        ResultSet res =  connectionDB.executeQuery("select max(Id_personnage) from personnage");
        res.next();
        perso.setId(res.getInt(1));
        return perso;
    }
    public Personnage getPersonnageById(int id) throws SQLException {
        String query = "SELECT * FROM personnage WHERE Id_personnage = " + id;
        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
            if (resultSet.next()) {
                return new Personnage(resultSet.getInt("Id_personnage"), resultSet.getString("nom"), resultSet.getInt("mana"), resultSet.getInt("pv"));
            }
        }
        return null;
    }

    public int update(Personnage perso) throws SQLException {
        String query = "UPDATE personnage SET pv = " + perso.getPv() + ", mana = " + perso.getMana() + " WHERE nom = '" + perso.getNom() + "'";
        return connectionDB.executeUpdate(query);
    }

    public int deleteByNom(String nom) throws SQLException {
        String query = "DELETE FROM personnage WHERE nom = '" + nom + "'";
        return connectionDB.executeUpdate(query);
    }
    public int deletePersonnageById(int id) throws SQLException {
        String query = "DELETE FROM personnage WHERE Id_personnage = " + id;
        return connectionDB.executeUpdate(query);
    }

    public void clearAll() throws SQLException {
        String query = "DELETE FROM personnage";
        connectionDB.executeUpdate(query);
    }
}
