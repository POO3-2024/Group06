package be.helha.apiprojetrpggroupe6.dbConnection;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;


public class ConnectionDB {

    private Connection connection;
    private static ConnectionDB instance;

    public static ConnectionDB getConnection() {

        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;

    }

    private  ConnectionDB() {
            try{
                SQLiteJDBCLoader.initialize();
                SQLiteDataSource dataSource = new SQLiteDataSource();
                dataSource.setUrl("jdbc:sqlite:C:\\sqlite\\db\\poo3.db");
                connection = dataSource.getConnection();
            }
            catch (SQLException e){
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }
    public int executeUpdate(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }
}
