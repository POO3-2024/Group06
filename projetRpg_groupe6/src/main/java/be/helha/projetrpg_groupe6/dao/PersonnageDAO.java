package be.helha.projetrpg_groupe6.dao;

import be.helha.projetrpg_groupe6.configNomAChanger.LectureJson;
import be.helha.projetrpg_groupe6.personnage.Personnage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnageDAO {
    private Connection connection;

    public PersonnageDAO() throws SQLException, ClassNotFoundException {
        // Initialize de la connexion à la db
        //connection = DriverManager.getConnection();
        Class.forName("org.sqlite.JDBC");
        LectureJson fichierConfig = new LectureJson();
        connection = DriverManager.getConnection(fichierConfig.getDbUrl()); // Initialisez la variable de classe connection
    }

    public List<Personnage> getAllPersonnages() throws SQLException {
        List<Personnage> personnages = new ArrayList<>();

        //Requete pour recup tout les perso
        String query = "SELECT * FROM personnage";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
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
}
