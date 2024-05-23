package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.HelloApplication;
import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.fx.ArmeListCell;
import be.helha.projetrpg_groupe6.service.ArmeService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

public class ArmeController implements Initializable {
    @FXML
    private TextField nomArme;

    @FXML
    private TextField DegatsArme;

    @FXML
    private Label affichageError;

    @FXML
    private Button boutonAdd;


    @FXML
    private TableView<ArmeListCell> tvArmes;

    private ArmeService armeService = new ArmeService();

    private static ArmeController instance;

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;


    public ArmeController(){
        instance = this;
    }
    public void addArme(){
        if(nomArme.getText().isEmpty() || DegatsArme.getText().isEmpty()){
            affichageError.setText("Le champ nom ou dégats est vide");
        }else{

            try {
                int number = Integer.parseInt(DegatsArme.getText());
                boolean test = armeService.postArme(nomArme.getText(),number);
                affichageError.setText("");
                System.out.println(test);
            }catch (NumberFormatException e){
                affichageError.setText(e.getMessage());
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

    public void changeViewToDetail(ActionEvent event, int id) throws IOException {
        // Logic to change the view and use the arme parameter
        // For example, load a new FXML file and pass the arme object to the new controller
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("detailArme.fxml"));
        root = fxmlLoader.load();
        DetailArme detailArme = fxmlLoader.getController();
        System.out.println(id);
        detailArme.initialize(id);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();


        // Assuming you have a reference to your Stage

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static ArmeController getInstance(){
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ArmeListCell> listArme = FXCollections.observableArrayList();
        List<Arme> listArme2 = armeService.getArme();
        for(Arme arme : listArme2){
            ArmeListCell arme2 = new ArmeListCell(arme);
            listArme.add(arme2);
        }



        TableColumn<ArmeListCell,String> id_column = new TableColumn<>("ID");
        id_column.setCellValueFactory(new PropertyValueFactory<>("idLabel"));
        TableColumn<ArmeListCell,String> nom_column = new TableColumn<>("NOM");
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nomLabel"));
        TableColumn<ArmeListCell,Void> button_column = new TableColumn<>("DETAILS");
        button_column.setCellFactory(param -> new TableCell<>() {
            private final Button bouton = new Button("Détail");
            {
                bouton.setOnAction(event -> {
                    ArmeListCell arme = getTableView().getItems().get(getIndex());
                    try {
                        arme.changeView(event,Integer.parseInt(arme.getIdLabel() ));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bouton);
                }
            }

        });
      //  ArmeListCell test = new ArmeListCell();

        tvArmes.getColumns().add(id_column);
        tvArmes.getColumns().add(nom_column);
        tvArmes.getColumns().add(button_column);
        tvArmes.setItems(listArme);
        adjustTableColumnsWidth(tvArmes);

    }

    private void adjustTableColumnsWidth(TableView<?> tableView) {
        // Ajouter un écouteur sur la largeur de la TableView
        tableView.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidth = newWidth.doubleValue();
            double columnCount = tableView.getColumns().size();
            for (TableColumn<?, ?> column : tableView.getColumns()) {
                column.prefWidthProperty().set(tableWidth / columnCount);
            }
        });
    }
}
