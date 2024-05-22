package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.personnage.Personnage;
import be.helha.projetrpg_groupe6.services.PersonnageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificationPersonnageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    @FXML
    private TextField nomField;

    @FXML
    private TextField pvField;

    @FXML
    private TextField manaField;

    private Personnage personnage;
    private PersonnageService personnageService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personnageService = new PersonnageService();
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
        if (personnage != null) {
            nomField.setText(personnage.getNom());
            pvField.setText(String.valueOf(personnage.getPv()));
            manaField.setText(String.valueOf(personnage.getMana()));
        }else {
            nomField.setText("");
            pvField.setText("");
            manaField.setText("");
        }
    }

    @FXML
    public void modifierPersonnage(ActionEvent event) throws IOException {
        if (personnage == null) {
            personnageService.postPersonnage(nomField.getText(), Integer.parseInt(pvField.getText()), Integer.parseInt(manaField.getText()));
        } else {
            personnage.setNom(nomField.getText());
            personnage.setPv(Integer.parseInt(pvField.getText()));
            personnage.setMana(Integer.parseInt(manaField.getText()));
           personnageService.updatePersonnage(personnage);
        }
            switchToGestionPersonnages(event);
    }

    @FXML
    public void annulerModification(ActionEvent event) throws IOException {
        switchToGestionPersonnages(event);
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
