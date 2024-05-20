package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.PersonnageDatabase;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public class PersonnageDatabaseTest {
    private static final ConnectionDB connectionDB = ConnectionDB.getConnection(true);
    private PersonnageDatabase personnageDatabase = new PersonnageDatabase(connectionDB);
    private Personnage perso = new Personnage("Gandalf",100,100);
    private Personnage perso1 = new Personnage("Gimli",90,800);
    private Personnage perso2 = new Personnage("Legolas",600,35);

    public PersonnageDatabaseTest() throws SQLException, ClassNotFoundException {
    }

    @BeforeEach
    public void init() throws SQLException {
        this.perso = personnageDatabase.add(perso);
        this.perso1 = personnageDatabase.add(perso1);
        this.perso2 = personnageDatabase.add(perso2);
    }

    @AfterEach
    public void clean() throws SQLException {
        personnageDatabase.clearAll();
    }
    @AfterAll
    public static void cleanall(){
        connectionDB.closeConnection();
    }

}
