package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.personnage.Personnage;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller pour gérer les interactions avec l'interface utilisateur des personnages.
 */
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
    @FXML
    private ImageView avatarImageView;

    @FXML
    private Button selectionJoueur1;
    @FXML
    private Button selectionJoueur2;

    private Personnage selectedPersonnage;


    /**
     * Initialise le contrôleur. Charge la liste des personnages et configure les gestionnaires d'événements.
     *
     * @param url l'URL utilisée pour résoudre les chemins relatifs pour l'objet racine, ou null si non connu.
     * @param resourceBundle les ressources utilisées pour localiser l'objet racine, ou null si non connu.
     */
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
                selectionJoueur1.setVisible(true);
                selectionJoueur2.setVisible(true);
            } else {
                detailsPane.setVisible(false);
                infoLabel.setVisible(false);
                modifierButton.setVisible(false);
                selectionJoueur1.setVisible(false);
                selectionJoueur2.setVisible(false);
            }
        });

        detailsPane.setVisible(false);
        infoLabel.setVisible(false);
        modifierButton.setVisible(false);
    }

    /**
     * Rafraîchit la liste des personnages affichés dans la ListView.
     */
    private void refreshPersonnageList() {
        personnages = personnageService.getPersonnage();
        listView.getItems().clear();
        for (Personnage personnage : personnages) {
            listView.getItems().add(personnage.getNom());
        }
    }

    /**
     * Ajoute un nouveau personnage en utilisant les informations saisies dans les champs de texte.
     */
    @FXML
    public void ajouterPersonnage() {
        String nom = nomField.getText();
        int pv = Integer.parseInt(pvField.getText());
        int mana = Integer.parseInt(manaField.getText());

        personnageService.postPersonnage(nom, pv, mana);
        refreshPersonnageList();
    }

    /**
     * Récupère l'ID d'un personnage par son nom.
     *
     * @param nomPersonnage le nom du personnage
     * @return l'ID du personnage, ou -1 si non trouvé
     */
    private int getPersonnageIdByName(String nomPersonnage) {
        for (Personnage personnage : personnages) {
            if (personnage.getNom().equals(nomPersonnage)) {
                this.selectedPersonnage = personnage;
                return personnage.getId();
            }
        }
        return -1; // ou une autre valeur par défaut ou lancer une exception si nécessaire
    }

    /**
     * Affiche les détails d'un personnage dans les labels correspondants.
     *
     * @param id l'ID du personnage
     */
    private void afficherDetailsPersonnage(String id) {
        int idPersonnage = getPersonnageIdByName(id);
        if (idPersonnage != -1) {
            Personnage personnage = personnageService.getPersonnageById(idPersonnage);
            if (personnage != null) {
                nomLabel.setText("» " + personnage.getNom());
                pvLabel.setText(String.valueOf(personnage.getPv()) + " hp");
                manaLabel.setText(String.valueOf(personnage.getMana()) + " mana");
                this.selectedPersonnage = personnage;
                avatarImageView.setImage(new Image("https://mineskin.eu/helm/" + personnage.getNom() + "/100"));
            }
        }
    }

    /**
     * Sélectionne un personnage pour le joueur 1 pour la partie en cours
     */
    public void selectionJoueur1(){
        CombatService.getPartie().setPersonnage1(new Personnage(selectedPersonnage));
        System.out.println(selectedPersonnage.getNom());
    }

    /**
     * Sélectionne un personnage pour le joueur 2 pour la partie en cours
     */
    public void selectionJoueur2(){
        CombatService.getPartie().setPersonnage2(new Personnage(selectedPersonnage));
        System.out.println(selectedPersonnage.getNom());

    }

    /**
     * Change de scène pour afficher la page principale.
     *
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
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
     * Change de scène pour afficher la gestion des personnages.
     *
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
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
     * Change de scène pour afficher la gestion des armes.
     *
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToGestionArmes(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionArmes.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change de scène pour afficher la modification d'un personnage.
     *
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToModification(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modificationPersonnages.fxml"));
        root = fxmlLoader.load();
        ModificationPersonnageController modificationPersonnageController = fxmlLoader.getController();
        String selectedPersonnageName = listView.getSelectionModel().getSelectedItem();
        int selectedPersonnageId = getPersonnageIdByName(selectedPersonnageName);
        Personnage selectedPersonnage = personnageService.getPersonnageById(selectedPersonnageId);
        modificationPersonnageController.setPersonnage(selectedPersonnage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change de scène pour afficher la création d'un personnage.
     *
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToCreation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modificationPersonnages.fxml"));
        root = fxmlLoader.load();
        ModificationPersonnageController modificationPersonnageController = fxmlLoader.getController();
        modificationPersonnageController.setPersonnage(null);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
