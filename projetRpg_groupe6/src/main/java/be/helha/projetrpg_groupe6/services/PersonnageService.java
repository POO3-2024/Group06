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

/**
 * Service pour gérer les opérations CRUD sur les objets Personnage.
 */
public class PersonnageService {
    private Gson gson;

    /**
     * Constructeur par défaut qui initialise le parseur Gson.
     */
    public PersonnageService() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    /**
     * Récupère la liste de tous les personnages.
     *
     * @return une liste de personnages
     */
    public List<Personnage> getPersonnage() {
        List<Personnage> listPersonnage = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/personnages"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Type personnageListType = new TypeToken<List<Personnage>>() {}.getType();
            listPersonnage = gson.fromJson(response.body(), personnageListType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listPersonnage;
    }

    /**
     * Met à jour les informations d'un personnage existant.
     *
     * @param personnage le personnage à mettre à jour
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updatePersonnage(Personnage personnage) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(personnage);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/personnages"))
                .PUT(bodyPublisher)
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Code de statut : " + response.statusCode());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ajoute un nouveau personnage.
     *
     * @param nom le nom du personnage
     * @param pv les points de vie du personnage
     * @param mana les points de mana du personnage
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean postPersonnage(String nom, int pv, int mana) {

        Personnage personnage = new Personnage(nom, pv, mana);
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(personnage);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/personnages"))
                .POST(bodyPublisher)
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Code de statut : " + response.statusCode());
            return response.statusCode() == 201;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère un personnage par son ID.
     *
     * @param id l'ID du personnage
     * @return le personnage correspondant à l'ID, ou null si non trouvé
     */
    public Personnage getPersonnageById(int id) {
        Personnage personnage = null;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/personnages/" + id))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                personnage = gson.fromJson(response.body(), Personnage.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return personnage;
    }

    public boolean deletePersonnageById(int id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/personnages/" + id))
                .DELETE()
                .build();
        try {
            HttpResponse<Void> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }



}
