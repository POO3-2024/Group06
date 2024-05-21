package be.helha.projetrpg_groupe6.fx;

import be.helha.projetrpg_groupe6.arme.Arme;
import javafx.scene.control.ListCell;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ArmeListCell extends ListCell<Arme> {
    private HBox content;
    private Label nomLabel;
    private Label idLabel;
    public ArmeListCell() {
        super();
        nomLabel = new Label();
        idLabel = new Label();
        HBox vBox = new HBox(idLabel, nomLabel);
        content = new HBox(vBox);
        content.setSpacing(10);
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
}
