package be.helha.projetrpg_groupe6.services;

import be.helha.projetrpg_groupe6.personnage.Personnage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class PersonnageService {
    private Gson gson;

    public PersonnageService() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public List<Personnage> getPersonnage() {
        List<Personnage> listPersonnage = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/personnages")).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Type personnageListType = new TypeToken<List<Personnage>>() {}.getType();
            listPersonnage = gson.fromJson(response.body(), personnageListType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listPersonnage;
    }

    public boolean updatePersonnage(Personnage personnage) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(personnage);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/personnages")).PUT(bodyPublisher).header("Content-Type", "application/json").build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean postPersonnage(String nom, int pv, int mana) {
        Personnage personnage = new Personnage(nom, pv, mana);
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(personnage);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/personnages")).POST(bodyPublisher).header("Content-Type", "application/json").build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Code de statut : " + response.statusCode());
            return response.statusCode() == 201;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
