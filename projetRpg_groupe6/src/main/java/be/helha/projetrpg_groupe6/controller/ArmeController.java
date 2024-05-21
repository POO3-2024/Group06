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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private ListView<Arme> lv_armes;

    private ArmeService armeService = new ArmeService();

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;
    public void addArme(){
        if(nomArme.getText().isEmpty() || DegatsArme.getText().isEmpty()){
            affichageError.setText("Le champ nom ou dégats est vide");
        }else{

            try {
                int number = Integer.parseInt(DegatsArme.getText());
                boolean test = armeService.postArme(nomArme.getText(),number);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Arme> listArme = FXCollections.observableArrayList();
        List<Arme> listArme2 = armeService.getArme();
        for(Arme arme : listArme2){
            listArme.add(arme);
        }
        lv_armes.setItems(listArme);
        lv_armes.setCellFactory(param -> new ArmeListCell());
    }
}
