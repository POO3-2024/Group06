package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.ArmeDatabase;
import be.helha.apiprojetrpggroupe6.Database.PersonnageDatabase;
import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.Services.ArmeService;
import be.helha.apiprojetrpggroupe6.Services.AttaqueService;
import be.helha.apiprojetrpggroupe6.Services.PersonnageService;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests unitaires pour le service d'attaque
 */
@SpringBootTest
public class AttaqueServiceTest {

    private AttaqueService attaqueService = new AttaqueService(true);
    private PersonnageService personnageService = new PersonnageService(true);
    private ArmeService armeService = new ArmeService(true);
    private Personnage perso = new Personnage("Test",100,30);
    private Arme arme = new Arme("Baton", 10);
    private Arme arme2 = new Arme("Epee", 50);

    /**
     * Initialise le nécéssaire pour les tests
     */
    @BeforeEach
    public void init() {
        try {
            this.perso = personnageService.addPersonnage(perso);
            this.arme = armeService.addArme(arme);
            this.arme2 = armeService.addArme(arme2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Nettoie la base de données après chaques tests
     */
    @AfterEach
    public void clean() {
        try {
            personnageService.deletePersonnage(perso.getId());
            armeService.deleteArme(arme.getId());
            armeService.deleteArme(arme2.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme la connexion à la base de données après tous les tests
     */
    @AfterAll
    public static void cleanAll(){
        ConnectionDB.getConnection(true).closeConnection();
    }

    /**
     * Teste l'attaque d'un personnage
     */
    @Test
    public void attaquerTest() {
        try {
            perso = attaqueService.attaquer(perso.getId(), arme.getId());
            assertEquals(20, perso.getPv());
            perso = attaqueService.attaquer(perso.getId(), arme.getId());
            assertEquals(10, perso.getPv());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Teste l'attaque d'un personnage lorsque la vie est inférieure aux dégats
     */
    @Test
    public void attaquerLorsqueVieInferieurADegat() {
        try {
            perso = attaqueService.attaquer(perso.getId(), arme2.getId());
            assertEquals(0, perso.getPv());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
