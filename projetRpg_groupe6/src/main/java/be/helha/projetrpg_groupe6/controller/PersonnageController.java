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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PersonnageController implements Initializable {

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

    @FXML
    private Label nomLabel;

    @FXML
    private Label pvLabel;

    @FXML
    private Label manaLabel;

    @FXML
    private ListView<Personnage> listView;

    private PersonnageService personnageService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personnageService = new PersonnageService();
        refreshPersonnageList();
    }

    private void refreshPersonnageList() {
        List<Personnage> personnages = personnageService.getPersonnage();
        listView.getItems().clear();
        listView.getItems().addAll(personnages);
    }

    @FXML
    public void ajouterPersonnage() {
        String nom = nomField.getText();
        int pv = Integer.parseInt(pvField.getText());
        int mana = Integer.parseInt(manaField.getText());

        personnageService.postPersonnage(nom, pv, mana);
        refreshPersonnageList();
    }

    public void afficherPersonnage(Personnage personnage) {
        nomLabel.setText(personnage.getNom());
        pvLabel.setText(String.valueOf(personnage.getPv()));
        manaLabel.setText(String.valueOf(personnage.getMana()));
    }

    public void modifierPersonnage(Personnage personnage) {
        personnage.setNom(nomField.getText());
        personnage.setPv(Integer.parseInt(pvField.getText()));
        personnage.setMana(Integer.parseInt(manaField.getText()));
        personnageService.updatePersonnage(personnage);
        refreshPersonnageList();
    }

    public void supprimerPersonnage() {
        Personnage personnageSelectionne = listView.getSelectionModel().getSelectedItem();
        if (personnageSelectionne != null) {
            listView.getItems().remove(personnageSelectionne);
            refreshPersonnageList();
        }
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGestionPersonnages(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionPersonnages.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGestionArmes(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionArmes.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
