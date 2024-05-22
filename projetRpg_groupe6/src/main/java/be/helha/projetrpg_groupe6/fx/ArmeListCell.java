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
    private Label nomLabel;
    private Label idLabel;
    private Button buttonView;

    public ArmeListCell() {
        super();
        nomLabel = new Label();
        idLabel = new Label();
        buttonView = new Button();
        HBox vBox = new HBox(idLabel, nomLabel, buttonView);
        content = new HBox(vBox);
        content.setSpacing(10);

        buttonView.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Arme arme = getItem();
                if (arme != null) {
                    try {
                        changeView(event,Integer.parseInt(arme.getId()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
    }

    @Override
    protected void updateItem(Arme arme, boolean empty) {
        super.updateItem(arme, empty);
        if (arme != null && !empty) {
            nomLabel.setText(arme.getNom());
            idLabel.setText(String.valueOf(arme.getId()));
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }

    protected void changeView(ActionEvent event,int id) throws IOException {
        ArmeController.getInstance().changeViewToDetail(event,id);
    }



}
