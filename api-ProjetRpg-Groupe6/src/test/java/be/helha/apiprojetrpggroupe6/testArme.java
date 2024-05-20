package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.ArmeDatabase;
import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
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

    @Test
    public void testGetArme() throws SQLException {
        List<Arme> list = armeDatabase.getArme();
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
        List<Arme> list = armeDatabase.getArme();
        assertEquals(3, list.size());
    }

    @Test
    public void testDelete1Arme() throws SQLException {
        List<Arme> list = armeDatabase.getArme();
        armeDatabase.deleteArme(Optional.of(list.get(0)));
        list = armeDatabase.getArme();
        assertEquals(2,list.size());

    }

    @Test
    public void testUpdateArme() throws SQLException {
        List<Arme> list = armeDatabase.getArme();
        list.get(0).setNom("NewName");
        list.get(1).setDegats(28);
        list.get(2).setNom("NewName2");
        list.get(2).setDegats(65);
        armeDatabase.updateArme(list.get(0));
        armeDatabase.updateArme(list.get(1));
        armeDatabase.updateArme(list.get(2));
        list = armeDatabase.getArme();
        assertTrue(list.get(0).getNom().equals("NewName"));
        assertTrue(list.get(1).getDegats() == 28);
        assertTrue(list.get(2).getNom().equals("NewName2") && list.get(2).getDegats() == 65);
    }


}
