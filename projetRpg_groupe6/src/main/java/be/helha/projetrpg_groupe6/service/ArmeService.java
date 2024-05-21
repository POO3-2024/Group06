package be.helha.projetrpg_groupe6.service;

import be.helha.projetrpg_groupe6.arme.Arme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ArmeService {

    private Gson gson;

    public ArmeService() {
        this.gson = new Gson();
    }

    public List<Arme> getArme(){
        List<Arme> listArme = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/armes")).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Type armeListType = new TypeToken<List<Arme>>(){}.getType();

           listArme = gson.fromJson(response.body(),armeListType);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listArme;
    }

    public boolean postArme(String nom, int degats){
        Arme arme = new Arme(nom,degats);
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(arme);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/armes")).POST(bodyPublisher).header("Content-Type", "application/json").build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Code de statut : " + response.statusCode());
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
