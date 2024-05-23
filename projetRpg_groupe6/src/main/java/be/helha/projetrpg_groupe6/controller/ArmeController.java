package be.helha.projetrpg_groupe6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ArmeController {
    @FXML
    private TextField nomArme;

    @FXML
    private TextField DegatsArme;

    @FXML
    private Label affichageError;

    @FXML
    private Button boutonAdd;

    private HttpClient httpClient;

    public void addArme(){
        if(nomArme.getText().isEmpty() || DegatsArme.getText().isEmpty()){
            affichageError.setText("Le champ nom ou dégats est vide");
        }else{

            try {
                int number = Integer.parseInt(DegatsArme.getText());
            }catch (NumberFormatException e){
                affichageError.setText(e.getMessage());
            }
        }
    }

    public void getArme(){
        httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/armes")).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
