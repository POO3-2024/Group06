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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe de modification de personnages
 */
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

    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personnageService = new PersonnageService();
    }

    /**
     * Fonction pour définir les attributs du personnage
     * @param personnage
     */
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

    /**
     * Fonction pour modifier le personnage
     * @param event
     * @throws IOException
     */
    @FXML
    public void modifierPersonnage(ActionEvent event) throws IOException {
        String nom = nomField.getText();

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez introduire un nom.");
            alert.showAndWait();
            return;
        }

        if (personnage == null) {
            String spv = pvField.getText();
            String smana = manaField.getText();

            int pv = !spv.isEmpty() ? Integer.parseInt(spv) : 0;
            int mana = !smana.isEmpty() ? Integer.parseInt(smana) : 0;

            personnageService.postPersonnage(nom, pv, mana);
        } else {
            personnage.setNom(nom);

            String pvString = pvField.getText();
            int pv = !pvString.isEmpty() ? Integer.parseInt(pvString) : 0;
            personnage.setPv(pv);

            String manaString = manaField.getText();
            int mana = !manaString.isEmpty() ? Integer.parseInt(manaString) : 0;
            personnage.setMana(mana);

            personnageService.updatePersonnage(personnage);
        }
        switchToGestionPersonnages(event);
    }


    /**
     * Fonction pour supprimer un personnage
     * @param event
     * @throws IOException
     */
    @FXML
    public void supprimerPersonnage(ActionEvent event) throws IOException {
        personnageService.deletePersonnageById(personnage.getId());
        switchToGestionPersonnages(event);
    }

    /**
     * Fonction pour annuler et retourner en arrière
     * @param event
     * @throws IOException
     */
    @FXML
    public void annulerModification(ActionEvent event) throws IOException {
        switchToGestionPersonnages(event);
    }

    /**
     * Fonction pour changer de page
     * @param event
     * @throws IOException
     */
    public void switchToGestionPersonnages(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionPersonnages.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fonction pour switch de page
     * @param event
     * @throws IOException
     */
    public void switchToMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fonction pour switch de page
     * @param event
     * @throws IOException
     */
    public void switchToGestionArmes(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionArmes.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
