package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.ArmeDatabase;
import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testArme {
    private static final ConnectionDB connection = ConnectionDB.getConnection(true);
    private ArmeDatabase armeDatabase = new ArmeDatabase(connection);
    private Arme arme = new Arme("test1",25);
    private Arme arme2 = new Arme("test2",48);
    private Arme arme3 = new Arme("test3",85);
    @BeforeEach
    public void setUp() throws Exception {
        armeDatabase.deleteArme(Optional.empty());
        armeDatabase.addArme(arme);
        armeDatabase.addArme(arme2);
        armeDatabase.addArme(arme3);
    }
    @AfterEach
    public void end(){
        armeDatabase.deleteArme(Optional.empty());
    }

    @Test
    public void testGetArme() throws SQLException {
        List<ArmeDTO> list = armeDatabase.getArme();
        assertEquals(3,list.size());
        assertTrue(list.get(0).getNom().equals(arme.getNom()));
        assertTrue(list.get(1).getNom().equals(arme2.getNom()));
        assertTrue(list.get(2).getNom().equals(arme3.getNom()));
    }

    @Test
    public void testInsertFalse() throws Exception {
        Arme arme4= new Arme("test1",25);
        Exception exception = assertThrows(Exception.class, () -> {
            armeDatabase.addArme(arme4);
        });

        // Verify the exception message
        assertEquals("Arme déjà existante", exception.getMessage());
        List<ArmeDTO> list = armeDatabase.getArme();
        assertEquals(3, list.size());
    }

    @Test
    public void testDelete1Arme() throws SQLException {
        List<ArmeDTO> list = armeDatabase.getArme();
        armeDatabase.deleteArme(Optional.of(list.get(0).getId()));
        list = armeDatabase.getArme();
        assertEquals(2,list.size());

    }
    @Test
    public void testDeleteArmeNonExist(){
        Arme armeTest = new Arme(68,"test89",52);
        assertEquals(0,armeDatabase.deleteArme(Optional.of(armeTest.getId())));
    }

    @Test
    public void testGetByID() throws SQLException {
        List<ArmeDTO> list = armeDatabase.getArme();
        Arme arme = armeDatabase.getArmeByID(list.get(0).getId());
        assertTrue(arme.getNom().equals("test1"));
        assertEquals(25,arme.getDegats());
    }

    @Test
    public void testUpdateArme() throws Exception {
        List<ArmeDTO> list = armeDatabase.getArme();
        Arme arme1 = armeDatabase.getArmeByID(list.get(0).getId());
        Arme arme2 = armeDatabase.getArmeByID(list.get(1).getId());
        Arme arme3 = armeDatabase.getArmeByID(list.get(2).getId());
        arme1.setNom("NewName");
        arme2.setDegats(28);
        arme3.setNom("NewName2");
        arme3.setDegats(65);
        armeDatabase.updateArme(arme1);
        armeDatabase.updateArme(arme2);
        armeDatabase.updateArme(arme3);
        list = armeDatabase.getArme();
        assertTrue(list.get(0).getNom().equals("NewName"));
        assertTrue(armeDatabase.getArmeByID(list.get(1).getId()).getDegats() == 28);
        assertTrue(list.get(2).getNom().equals("NewName2") && armeDatabase.getArmeByID(list.get(2).getId()).getDegats() == 65);
    }

    @Test
    public void testUpdateArmeNonFonctionnel() throws Exception {
        Arme armeTest = new Arme("test89",52);
        assertEquals(0, armeDatabase.updateArme(armeTest)) ;

    }


}
