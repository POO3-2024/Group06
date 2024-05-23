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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    private ListView<String> listView;

    private PersonnageService personnageService;
    private List<Personnage> personnages;

    @FXML
    private AnchorPane detailsPane;

    @FXML
    private Label infoLabel;
    @FXML
    private Button modifierButton;
    @FXML
    private Button createButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personnageService = new PersonnageService();
        refreshPersonnageList();

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                afficherDetailsPersonnage(newValue);
                detailsPane.setVisible(true);
                infoLabel.setVisible(true);
                modifierButton.setVisible(true);
            } else {
                detailsPane.setVisible(false);
                infoLabel.setVisible(false);
                modifierButton.setVisible(false);
            }
        });

        detailsPane.setVisible(false);
        infoLabel.setVisible(false);
        modifierButton.setVisible(false);

    }

    private void refreshPersonnageList() {
        personnages = personnageService.getPersonnage();
        listView.getItems().clear();
        for (Personnage personnage : personnages) {
            listView.getItems().add(personnage.getNom());
        }


    }

    @FXML
    public void ajouterPersonnage() {
        String nom = nomField.getText();
        int pv = Integer.parseInt(pvField.getText());
        int mana = Integer.parseInt(manaField.getText());

        personnageService.postPersonnage(nom, pv, mana);
        refreshPersonnageList();
    }

    private void afficherDetailsPersonnage(String nomPersonnage) {
        for (Personnage personnage : personnages) {
            if (personnage.getNom().equals(nomPersonnage)) {
                nomLabel.setText("» " + personnage.getNom());
                pvLabel.setText(String.valueOf(personnage.getPv()) + " hp");
                manaLabel.setText(String.valueOf(personnage.getMana())  + " mana");
                break;
            }
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
    public void switchToModification(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modificationPersonnages.fxml"));
        root = fxmlLoader.load();
        ModificationPersonnageController modificationPersonnageController = fxmlLoader.getController();
        String selectedPersonnageName = listView.getSelectionModel().getSelectedItem();
        Personnage selectedPersonnage = personnages.stream().filter(p -> p.getNom().equals(selectedPersonnageName)).findFirst().orElse(null);
        modificationPersonnageController.setPersonnage(selectedPersonnage);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modificationPersonnages.fxml"));
        root = fxmlLoader.load();
        ModificationPersonnageController modificationPersonnageController = fxmlLoader.getController();
        modificationPersonnageController.setPersonnage(null);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
