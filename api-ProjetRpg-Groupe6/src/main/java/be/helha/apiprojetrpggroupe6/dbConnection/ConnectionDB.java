package be.helha.apiprojetrpggroupe6.dbConnection;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.helha.apiprojetrpggroupe6.Config.LectureJson;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

/**
 * Classe permettant la connexion à la base de données
 */
public class ConnectionDB {
    /**
     * Objet utilisé pour la connexion à la base de données
     */
    private Connection connection;
    /**
     * Instance de ConnectionDB utilisé pour le singleton
     */
    private static ConnectionDB instance;

    /**
     * Récupère l'instance de la connexion à la base de données de production
     * @return insance d'objet ConnectionDB en Singleton
     */

    public static ConnectionDB getConnection() {

        if (instance == null) {
            instance = new ConnectionDB(false);
        }
        return instance;
    }

    /**
     * Récupère l'instance de la connexion à la base de données
     * @param test : Détermine si la base de données est utilisée pour les tests ou la production
     * @return insance d'objet ConnectionDB en Singleton
     */
    public static ConnectionDB getConnection(boolean test) {

        if (instance == null) {
            instance = new ConnectionDB(test);
        }
        return instance;
    }

    /**
     * Constructeur de la connexion à la base de données
     * @param test Détermine si la base de données est utilisée pour les tests ou la production
     */
    private  ConnectionDB(boolean test) {
            try {
                SQLiteJDBCLoader.initialize();
                SQLiteDataSource dataSource = new SQLiteDataSource();
                LectureJson config =  LectureJson.getInstance();
                if (!test) {
                    dataSource.setUrl("jdbc:sqlite:" + config.getDbPath() + config.getDbProduction());
                } else {
                    dataSource.setUrl("jdbc:sqlite:" + config.getDbPath() + config.getDbTest());
                }
                connection = dataSource.getConnection();
            }
            catch (SQLException e){
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * Permet d'executer une requête SQL et d'en récupérer le résultat
     * @param query Requête SQL
     * @return ResultSet Résultat de la requête
     * @throws SQLException Renvoi une exeption de type SQLException si la requête échoue
     */
    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    /**
     * Permet d'executer une requête SQL et de récupérer le nombre de lignes affectées
     * @param query Requête SQL
     * @return int Nombre de lignes affectées par la requête
     * @throws SQLException Renvoi une exeption de type SQLException si la requête échoue
     */
    public int executeUpdate(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
                instance = null;
            }
        }
    }
}
