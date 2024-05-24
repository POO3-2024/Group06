package be.helha.projetrpg_groupe6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    static AnchorPane root;

    static List<GridPane> grid = new ArrayList<GridPane>();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("rpg_gestion");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}