package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.models.Partie;
import be.helha.projetrpg_groupe6.service.ArmeService;
import be.helha.projetrpg_groupe6.services.CombatService;
import be.helha.projetrpg_groupe6.services.PersonnageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Label endMessageLabel;
    @FXML
    private AnchorPane endMessageContainer;

    private Partie partie;
    private ArmeService armeService = new ArmeService();
    private PersonnageService personnageService = new PersonnageService();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partie = CombatService.getPartie();
        initialisation();
        verifWin();
    }

    private void initialisation() {
        if(partie.getPersonnage1() == null || partie.getPersonnage2() == null || partie.getArme1() == null || partie.getArme2() == null){
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(true);
        }else if(partie.getJoueur1_actif()){
            attackButtonPlayer1.setDisable(false);
            attackButtonPlayer2.setDisable(true);
        } else {
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(false);
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
        int idAttaque = 0;
        int idArme = 0;
        if(partie.getJoueur1_actif()){
            idAttaque = partie.getPersonnage2().getId();
            idArme = partie.getArme1().getId();
            partie.setPersonnage2(combatService.attaquer(idAttaque, idArme));
            labelCharacterHpPlayer2.setText(String.valueOf(partie.getPersonnage2().getPv()));
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(false);
        }else {
            idAttaque = partie.getPersonnage1().getId();
            idArme = partie.getArme2().getId();
            partie.setPersonnage1(combatService.attaquer(idAttaque, idArme));
            labelCharacterHpPlayer1.setText(String.valueOf(partie.getPersonnage1().getPv()));
            attackButtonPlayer1.setDisable(false);
            attackButtonPlayer2.setDisable(true);
        }
        verifWin();


        partie.skipTurn();
    }

    private void verifWin() {
        if(partie.getPersonnage1().getPv() <= 0){
            endMessageLabel.setText("Le joueur 2 a gagné !");
            endMessageContainer.setVisible(true);
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(true);
        }else if(partie.getPersonnage2().getPv() <= 0){
            endMessageLabel.setText("Le joueur 1 a gagné !");
            endMessageContainer.setVisible(true);
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(true);
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
