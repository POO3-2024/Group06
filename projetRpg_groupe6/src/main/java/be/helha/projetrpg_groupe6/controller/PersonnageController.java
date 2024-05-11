package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.personnage.Personnage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PersonnageController {

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

    @FXML
    public void ajouterPersonnage() { //Ajoute un nouveau personnage
        String nom = nomField.getText();
        int pv = Integer.parseInt(pvField.getText());
        int mana = Integer.parseInt(manaField.getText());

        Personnage nouveauPersonnage = new Personnage(nom, pv, mana);
        // Ajouter le nouveau personnage à votre liste de personnages
    }

    public void afficherPersonnage(Personnage personnage) { //Affiche le Personnage
        nomLabel.setText(personnage.getNom());
        pvLabel.setText(String.valueOf(personnage.getPv()));
        manaLabel.setText(String.valueOf(personnage.getMana()));
    }

    public void modifierPersonnage(Personnage personnage) { //Modifie le personnage
        personnage.setNom(nomField.getText());
        personnage.setPv(Integer.parseInt(pvField.getText()));
        personnage.setMana(Integer.parseInt(manaField.getText()));
        // Mettre à jour le personnage dans votre liste de personnages
    }
    public void supprimerPersonnage() { //Supprime le personnage
        Personnage personnageSelectionne = listView.getSelectionModel().getSelectedItem();
    }
}

