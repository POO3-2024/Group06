package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.service.ArmeService;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;

public class DetailArme {

    @FXML
    private Label nom;
    @FXML
    private Label degats;
    @FXML Label error;


    private int id_Arme;
    @FXML
    private TextField nomModif;
    @FXML
    private TextField degatsModif;

    public void setId_Arme(int id) {

       id_Arme = id;

    }

    public void modifArme(){
        if(nomModif.getText().isEmpty() && degatsModif.getText().isEmpty()){
            error.setText("Introduire des valeurs");
        }else{
            Arme arme;
            if(nomModif.getText()==null){
                arme = new Arme(nom.getText(),Integer.parseInt(degatsModif.getText()));
            }else{
                arme = new Arme(nomModif.getText(),Integer.parseInt(degats.getText()));
            }
            ArmeService armeService = new ArmeService();
            Boolean test = armeService.UpdateArme(arme);
            if (test){
                error.setText("c'est bon");
            }
        }
    }


    public void initialize() {
        ArmeService armeService = new ArmeService();
        System.out.println(id_Arme);
        Arme arme = armeService.getArmeById(id_Arme);
        nom.setText(arme.getNom());
        degats.setText(String.valueOf(arme.getDegats()));

    }
}
