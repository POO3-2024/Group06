package be.helha.projetrpg_groupe6.controller;

import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.service.ArmeService;
import be.helha.projetrpg_groupe6.services.CombatService;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @FXML
    private Label confirmation;


    private int id_Arme;
    private int compteur = 0;
    @FXML
    private TextField nomModif;
    @FXML
    private TextField degatsModif;
    @FXML
    private Button delete;
    @FXML
    private Button retour;

    private ArmeService armeService = new ArmeService();

    public void setId_Arme(int id) {

       id_Arme = id;

    }

    public void modifArme(){
        if(nomModif.getText().isEmpty() && degatsModif.getText().isEmpty()){
            error.setText("Introduire des valeurs");
        }else{
            Arme arme;
            if(nomModif.getText().isEmpty()){

                arme = new Arme(id_Arme, nom.getText(),Integer.parseInt(degatsModif.getText()));
            }else if(degatsModif.getText().isEmpty()){
                arme = new Arme(id_Arme,nomModif.getText(),Integer.parseInt(degats.getText()));
            }else{
                arme = new Arme(id_Arme,nomModif.getText(),Integer.parseInt(degatsModif.getText()));
            }
            ArmeService armeService = new ArmeService();
            Boolean test = armeService.UpdateArme(arme);
            if (test){
                error.setText("c'est bon");
            }
        }
    }


    public void initialize(int id) {
        ArmeService armeService = new ArmeService();

        Arme arme = armeService.getArmeById(id);
        id_Arme = id;
        nom.setText(arme.getNom());
        degats.setText(String.valueOf(arme.getDegats()));

        delete.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                if(compteur == 0){
                    confirmation.setText("supprimer l'objet?");
                    compteur++;
                }else if(compteur == 1){
                    ArmeService armeService = new ArmeService();
                    boolean verif = armeService.DeleteArme(id_Arme);
                    if(verif){


                        try {
                            ArmeController.getInstance().switchToGestionArmes(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        });

        retour.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                try {
                    ArmeController.getInstance().switchToGestionArmes(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public DetailArme(){


    }

    public void selecteArmeJoueur1(){
       Arme arme = this.armeService.getArmeById(id_Arme);
       CombatService.getPartie().setArme1(arme);
    }
    public void selecteArmeJoueur2(){
        Arme arme = this.armeService.getArmeById(id_Arme);
        CombatService.getPartie().setArme2(arme);
    }

}
