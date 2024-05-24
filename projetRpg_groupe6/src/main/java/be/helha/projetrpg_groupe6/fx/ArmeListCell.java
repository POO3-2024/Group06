package be.helha.projetrpg_groupe6.fx;

import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.controller.ArmeController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

import javafx.event.EventHandler;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
/**
 * Classe ArmeListCell
 * Représente une cellule de liste personnalisée pour afficher les informations sur les armes
 */
public class ArmeListCell extends ListCell<Arme> {
    private HBox content;
    public String nomLabel;
    public String idLabel;
    private Button buttonView;
    /**
     * Constructeur pour créer une ArmeListCell avec une arme donnée.
     *
     * @param arme l'arme à afficher dans la cellule de la liste
     */
    public ArmeListCell(Arme arme) {
        super();
        this.nomLabel = arme.getNom();
        this.idLabel =String.valueOf(arme.getId()) ;
        buttonView = new Button();



        buttonView.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Arme arme = getItem();
                if (arme != null) {
                    try {
                        changeView(event,arme.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
    }
    /**
     * Getter pour le bouton de vue.
     *
     * @return le bouton de vue
     */
    public Button getButtonView() {
        return buttonView;
    }
    /**
     * Getter pour le label du nom de l'arme.
     *
     * @return le label du nom de l'arme
     */
    public String getNomLabel() {
        return nomLabel;
    }
    /**
     * Setter pour le label du nom de l'arme.
     *
     * @param nomLabel le nouveau label du nom de l'arme
     */
    public void setNomLabel(String nomLabel) {
        this.nomLabel = nomLabel;
    }
    /**
     * Getter pour le label de l'ID de l'arme.
     *
     * @return le label de l'ID de l'arme
     */
    public String getIdLabel() {
        return idLabel;
    }
    /**
     * Setter pour le label de l'ID de l'arme.
     *
     * @param idLabel le nouveau label de l'ID de l'arme
     */
    public void setIdLabel(String idLabel) {
        this.idLabel = idLabel;
    }
    /**
     * Change la vue vers les détails de l'arme sélectionnée.
     *
     * @param event l'événement déclenché par le bouton de vue
     * @param id    l'ID de l'arme à afficher
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    public void changeView(ActionEvent event, int id) throws IOException {
        ArmeController.getInstance().changeViewToDetail(event,id);
    }



}

