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

public class ArmeListCell extends ListCell<Arme> {
    private HBox content;
    public String nomLabel;
    public String idLabel;
    private Button buttonView;

    public ArmeListCell(Arme arme) {
        super();
        this.nomLabel = arme.getNom();
        this.idLabel = String.valueOf(arme.getId());
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

  /*  @Override
    protected void updateItem(Arme arme, boolean empty) {
        super.updateItem(arme, empty);
        if (arme != null && !empty) {
            nomLabel.setText(arme.getNom());
            idLabel.setText(String.valueOf(arme.getId()));
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }*/

    public Button getButtonView() {
        return buttonView;
    }

    public String getNomLabel() {
        return nomLabel;
    }

    public void setNomLabel(String nomLabel) {
        this.nomLabel = nomLabel;
    }

    public String getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(String idLabel) {
        this.idLabel = idLabel;
    }

    public void changeView(ActionEvent event, int id) throws IOException {
        System.out.println(id);
        ArmeController.getInstance().changeViewToDetail(event,id);
    }



}

