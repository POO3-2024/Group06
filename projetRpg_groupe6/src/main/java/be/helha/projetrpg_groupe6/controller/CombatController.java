package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.models.Partie;
import be.helha.projetrpg_groupe6.personnage.Personnage;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller pour gérer les interactions avec l'interface utilisateur du combat.
 */
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
    private ImageView avatarImageView1;
    @FXML
    private ImageView avatarImageView2;
    @FXML
    private Label endMessageLabel;
    @FXML
    private AnchorPane endMessageContainer;
    @FXML
    private ProgressBar progress1;
    @FXML
    private ProgressBar progress2;

    private int baseHpPlayer1;
    private int baseHpPlayer2;

    private Partie partie;
    private ArmeService armeService = new ArmeService();
    private PersonnageService personnageService = new PersonnageService();
    private CombatService combatService = new CombatService();

    /**
     * Initialise le contrôleur après que son élément racine ait été complètement traité.
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partie = CombatService.getPartie();
        this.baseHpPlayer1 = partie.getPersonnage1().getPv();
        this.baseHpPlayer2 = partie.getPersonnage2().getPv();
        initialisation();
        verifWin();
    }

    /**
     * Initialise les éléments de l'interface utilisateur.
     */
    private void initialisation() {
        if (partie.getPersonnage1() == null || partie.getPersonnage2() == null || partie.getArme1() == null || partie.getArme2() == null) {
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(true);
            showAlert("Erreur", "Les personnages et les armes doivent être définis.");
        } else if (partie.getJoueur1_actif()) {
            attackButtonPlayer1.setDisable(false);
            attackButtonPlayer2.setDisable(true);
        } else {
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(false);
        }
        updateCharacterInfo();
    }

    /**
     * Met à jour les informations des personnages et des armes.
     * Si un personnage ou une arme n'est pas défini, les boutons d'attaque sont désactivés.
     * Si le joueur 1 est actif, le bouton d'attaque du joueur 1 est activé.
     * Si le joueur 2 est actif, le bouton d'attaque du joueur 2 est activé.
     * Si un personnage n'a plus de points de vie, le message de fin de partie est affiché.
     * La partie est terminée si un des deux personnages n'a plus de points de vie.
     * Si un personnage est attaqué, les points de vie et la barre de progression sont mis à jour.
     */
    private void updateCharacterInfo() {
        if (partie.getPersonnage1() != null) {
            labelCharacterNamePlayer1.setText(partie.getPersonnage1().getNom());
            avatarImageView1.setImage(new Image("https://mineskin.eu/armor/bust/" + partie.getPersonnage1().getNom() + "/100"));
            labelCharacterHpPlayer1.setText(String.valueOf(partie.getPersonnage1().getPv()) + " hp");
            labelCharacterManaPlayer1.setText(String.valueOf(partie.getPersonnage1().getMana()) + " mana");
            progress1.setProgress(partie.getPersonnage1().getPv() / (double) this.baseHpPlayer1);
        }
        if (partie.getPersonnage2() != null) {
            labelCharacterNamePlayer2.setText(partie.getPersonnage2().getNom());
            avatarImageView2.setImage(new Image("https://mineskin.eu/armor/bust/" + partie.getPersonnage2().getNom() + "/100"));
            labelCharacterHpPlayer2.setText(String.valueOf(partie.getPersonnage2().getPv()) + " hp");
            labelCharacterManaPlayer2.setText(String.valueOf(partie.getPersonnage2().getMana()) + " mana");
            progress2.setProgress(partie.getPersonnage2().getPv() / (double) this.baseHpPlayer2);
        }
        if (partie.getArme1() != null) {
            labelWeaponNamePlayer1.setText(partie.getArme1().getNom());
            labelWeaponDamagePlayer1.setText(String.valueOf(partie.getArme1().getDegats()));
        }
        if (partie.getArme2() != null) {
            labelWeaponNamePlayer2.setText(partie.getArme2().getNom());
            labelWeaponDamagePlayer2.setText(String.valueOf(partie.getArme2().getDegats()));
        }
    }

    /**
     * Attaque le personnage adverse.
     * Si le joueur 1 est actif, le personnage 2 est attaqué.
     * Si le joueur 2 est actif, le personnage 1 est attaqué.
     * Les boutons d'attaque sont désactivés après l'attaque.
     * Si un personnage n'a plus de points de vie, le message de fin de partie est affiché.
     * La partie est terminée si un des deux personnages n'a plus de points de vie.
     *
     */
    @FXML
    public void attaquer() {
        int idAttaque;
        int idArme;
        if (partie.getJoueur1_actif()) {
            idAttaque = partie.getPersonnage2().getId();
            idArme = partie.getArme1().getId();
            partie.setPersonnage2(combatService.attaquer(idAttaque, idArme));
            updateCharacterInfo();
            attackButtonPlayer1.setDisable(true);
            attackButtonPlayer2.setDisable(false);
        } else {
            idAttaque = partie.getPersonnage1().getId();
            idArme = partie.getArme2().getId();
            partie.setPersonnage1(combatService.attaquer(idAttaque, idArme));
            updateCharacterInfo();
            attackButtonPlayer1.setDisable(false);
            attackButtonPlayer2.setDisable(true);
        }
        verifWin();
        partie.skipTurn();
    }

    /**
     * Vérifie si un des deux personnages n'a plus de points de vie.
     * Si c'est le cas, affiche le message de fin de partie.
     */
    private void verifWin() {
        if (partie.getPersonnage1().getPv() <= 0) {
            endMessageLabel.setText(partie.getPersonnage2().getNom()+" a gagné !");
            endMessageContainer.setVisible(true);
            disableAttackButtons();
            resetHp();
        } else if (partie.getPersonnage2().getPv() <= 0) {
            endMessageLabel.setText(partie.getPersonnage1().getNom()+" a gagné !");
            endMessageContainer.setVisible(true);
            disableAttackButtons();
            resetHp();
        }
    }

    private void resetHp() {
        partie.getPersonnage1().setPv(baseHpPlayer1);
        partie.getPersonnage2().setPv(baseHpPlayer2);
        this.personnageService.updatePersonnage(partie.getPersonnage2());
        this.personnageService.updatePersonnage(partie.getPersonnage1());
        updateCharacterInfo();
    }

    /**
     * Désactive les boutons d'attaque.
     */
    private void disableAttackButtons() {
        attackButtonPlayer1.setDisable(true);
        attackButtonPlayer2.setDisable(true);
    }

    /**
     * Affiche une alerte avec un titre et un message.
     * @param title le titre de l'alerte
     * @param message le message de l'alerte
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Change la scène vers la gestion des personnages.
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToGestionPersonnages(ActionEvent event) throws IOException {
        switchScene(event, "gestionPersonnages.fxml");
    }

    /**
     * Change la scène vers la page principale.
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScene(event, "mainPage.fxml");
    }

    /**
     * Change la scène vers la gestion des armes.
     * @param event l'événement de l'action
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    public void switchToGestionArmes(ActionEvent event) throws IOException {
        switchScene(event, "gestionArmes.fxml");
    }

    /**
     * Change la scène vers la gestion des combats.
     * @param event l'événement de l'action
     * @param fxmlFile le fichier fxml à charger
     * @throws IOException en cas d'erreur de chargement de la scène
     */
    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
