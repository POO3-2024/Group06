package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Database.ArmeDatabase;
import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class testArme {
    private static final ConnectionDB connection = ConnectionDB.getConnection(true);
    private ArmeDatabase armeDatabase = new ArmeDatabase(connection);
    private Arme arme = new Arme("test1",25);
    private Arme arme2 = new Arme("test2",48);
    @BeforeEach
    public void setUp() throws SQLException {
        armeDatabase.addArme(arme);
        armeDatabase.addArme(arme2);
    }

    @Test
    public void testGetArme() throws SQLException {
        List<Arme> list = armeDatabase.getArme();
        assertEquals(2,list.size());
        assertTrue(list.get(0).getNom().equals(arme.getNom()));
        assertTrue(list.get(1).getNom().equals(arme2.getNom()));
    }


}
