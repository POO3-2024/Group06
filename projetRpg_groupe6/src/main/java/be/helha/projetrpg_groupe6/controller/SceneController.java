package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.personnage.Personnage;
import be.helha.projetrpg_groupe6.services.PersonnageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de la page d'acueil
 */
public class SceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    @FXML
    private ListView<Personnage> lv_personnages;

    private PersonnageService personnageService;

    private ArmeController armeController;

    /**
     * Fonction initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        armeController = new ArmeController();
    }

    /**
     * Fonction pour switch de pages
     * @param event
     * @throws IOException
     */
    public void switchToMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Fonction pour switch de pages
     * @param event
     * @throws IOException
     */
    public void switchToGestionPersonnages(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionPersonnages.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Fonction pour switch de pages
     * @param event
     * @throws IOException
     */
    public void switchToGestionArmes(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionArmes.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Fonction pour switch de pages
     * @param event
     * @throws IOException
     */
    public void switchToLancerCombat(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("combat.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
