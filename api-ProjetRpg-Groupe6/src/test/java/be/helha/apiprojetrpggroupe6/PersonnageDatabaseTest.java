package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.PersonnageDatabase;
import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests unitaires pour la base de données de personnage
 */
@SpringBootTest
public class PersonnageDatabaseTest {
    private static final ConnectionDB connectionDB = ConnectionDB.getConnection(true);
    private PersonnageDatabase personnageDatabase = new PersonnageDatabase(connectionDB);
    private Personnage perso = new Personnage("Gandalf",100,100);
    private PersonnageDTO persoDto = new PersonnageDTO(perso.getId(), perso.getNom());
    private Personnage perso1 = new Personnage("Gimli",90,800);
    private PersonnageDTO perso1Dto = new PersonnageDTO(perso1.getId(), perso1.getNom());
    private Personnage perso2 = new Personnage("Legolas",600,35);
    private PersonnageDTO perso2Dto = new PersonnageDTO(perso2.getId(), perso2.getNom());


    public PersonnageDatabaseTest() throws SQLException, ClassNotFoundException {
    }

    /**
     * Initialise le nécéssaire pour les tests
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        this.perso = personnageDatabase.add(perso);
        this.perso1 = personnageDatabase.add(perso1);
        this.perso2 = personnageDatabase.add(perso2);
//        personnages.add(this.persoDto);
//        personnages.add(this.perso1Dto);
//        personnages.add(this.perso2Dto);
    }

    /**
     * Nettoir la base de données après chaque test
     * @throws SQLException
     */
    @AfterEach
    public void clean() throws SQLException {
        personnageDatabase.clearAll();
    }
    @Test
    public void testGetAllPersonnages() throws Exception {
        List<PersonnageDTO> personnageDTOS = personnageDatabase.getAllPersonnages();
        assertEquals(personnageDTOS.size(), 3);
        assertEquals(personnageDTOS.get(0).getNom(), perso.getNom());
        assertEquals(personnageDTOS.get(1).getNom(), perso1.getNom());
        assertEquals(personnageDTOS.get(2).getNom(), perso2.getNom());
    }
    @Test
    public void testAdd() throws SQLException {
        assertEquals(perso.getNom(), personnageDatabase.add(perso).getNom());
    }
    @Test
    public void testGetPersonnageById() throws SQLException {
        assertEquals(perso.getId(), personnageDatabase.getPersonnageById(perso.getId()).getId());
    }
    @Test
    public void testGetPersonnageByName() throws SQLException {
        assertEquals(perso.getNom(), personnageDatabase.getPersonnageByName(perso.getNom()).getNom());
    }
    @Test
    public void testUpdate() throws SQLException {
        assertNotEquals(0, personnageDatabase.update(perso));
    }
    @Test
    public void testDeletePersonnageById() throws SQLException {
        assertNotEquals(0, personnageDatabase.deletePersonnageById(perso.getId()));
    }
    @AfterAll
    public static void cleanall(){
        connectionDB.closeConnection();
    }

}
