package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.models.Partie;
import be.helha.projetrpg_groupe6.services.CombatService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CombatController implements Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    @FXML
    private Label labelCharacterNamePlayer1;
    @FXML
    private Label labelCharacterNamePlayer2;
    @FXML
    private Label labelCharacterHpPlayer1;
    @FXML
    private Label labelCharacterHpPlayer2;
    @FXML
    private Label labelCharacterManaPlayer1;
    @FXML
    private Label labelCharacterManaPlayer2;
    @FXML
    private Label labelWeaponNamePlayer1;
    @FXML
    private Label labelWeaponNamePlayer2;
    @FXML
    private Label labelWeaponDamagePlayer1;
    @FXML
    private Label labelWeaponDamagePlayer2;
    @FXML
    private Button attackButtonPlayer1;
    @FXML
    private Button attackButtonPlayer2;

    private Partie partie;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partie = CombatService.getPartie();
        if(partie.getPersonnage1() == null || partie.getPersonnage2() == null || partie.getArme1() == null || partie.getArme2() == null){
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(true);
        }
        if(partie.getPersonnage1() != null){
            labelCharacterNamePlayer1.setText(partie.getPersonnage1().getNom());
            labelCharacterHpPlayer1.setText(String.valueOf(partie.getPersonnage1().getPv()));
            labelCharacterManaPlayer1.setText(String.valueOf(partie.getPersonnage1().getMana()));
        }
        if(partie.getPersonnage2() != null){
            labelCharacterNamePlayer2.setText(partie.getPersonnage2().getNom());
            labelCharacterHpPlayer2.setText(String.valueOf(partie.getPersonnage2().getPv()));
            labelCharacterManaPlayer2.setText(String.valueOf(partie.getPersonnage2().getMana()));
        }
        if(partie.getArme1() != null){
            labelWeaponNamePlayer1.setText(partie.getArme1().getNom());
            labelWeaponDamagePlayer1.setText(String.valueOf(partie.getArme1().getDegats()));
        }
        if(partie.getArme2() != null){
            labelWeaponNamePlayer2.setText(partie.getArme2().getNom());
            labelWeaponDamagePlayer2.setText(String.valueOf(partie.getArme2().getDegats()));
        }
    }

    public void attaquer(){
        CombatService combatService = new CombatService();
        String idAttaque = "0";
        String idArme = "0";
        if(partie.getJoueur1_actif()){
            idAttaque = partie.getPersonnage1().getId();
            idArme = partie.getArme1().getId();
            partie.setPersonnage2(combatService.attaquer(idAttaque, idArme));
            labelCharacterHpPlayer2.setText(String.valueOf(partie.getPersonnage2().getPv()));
        }else {
            idAttaque = partie.getPersonnage2().getId();
            idArme = partie.getArme2().getId();
            partie.setPersonnage1(combatService.attaquer(idAttaque, idArme));
            labelCharacterHpPlayer1.setText(String.valueOf(partie.getPersonnage1().getPv()));
        }
    }
    public void switchToGestionPersonnages(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionPersonnages.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGestionArmes(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionArmes.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}