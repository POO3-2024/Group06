package be.helha.projetrpg_groupe6.service;

import be.helha.projetrpg_groupe6.arme.Arme;
import be.helha.projetrpg_groupe6.config.LectureJson;
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
/**
 * Service pour gérer les opérations CRUD des armes en utilisant des requêtes HTTP.
 */
public class ArmeService {

    private String apiUrl = LectureJson.getInstance().getApiUrl();
    private Gson gson;
    /**
     * Constructeur par défaut qui initialise l'instance de Gson.
     */
    public ArmeService() {
        this.gson = new Gson();
    }
    /**
     * Récupère la liste des armes depuis l'API.
     *
     * @return une liste d'objets Arme.
     */
    public List<Arme> getArme(){
        List<Arme> listArme = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl+"/armes")).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Type armeListType = new TypeToken<List<Arme>>(){}.getType();

           listArme = gson.fromJson(response.body(),armeListType);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listArme;
    }
    /**
     * Récupère une arme par son ID depuis l'API.
     *
     * @param id l'ID de l'arme.
     * @return un objet Arme.
     */
    public Arme getArmeById(int id){

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl+"/armes/"+id)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

            Arme arme = gson.fromJson(response.body(), Arme.class);
            return arme;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Supprime une arme par son ID depuis l'API.
     *
     * @param id l'ID de l'arme.
     * @return true si l'arme a été supprimée avec succès.
     */
    public boolean DeleteArme(int id){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl+"/armes/"+id)).DELETE().build();
        try {
           HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
           return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Met à jour une arme via l'API.
     *
     * @param arme l'objet Arme à mettre à jour.
     * @return true si l'arme a été mise à jour avec succès.
     */
    public boolean UpdateArme(Arme arme){
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(arme);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json,StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl+"/armes")).PUT(bodyPublisher).header("Content-Type", "application/json").build();
        try {
           HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
           return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Ajoute une nouvelle arme via l'API.
     *
     * @param nom le nom de l'arme.
     * @param degats les dégâts de l'arme.
     * @return true si l'arme a été ajoutée avec succès.
     */
    public boolean postArme(String nom, int degats){
        Arme arme = new Arme(nom,degats);
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = gson.toJson(arme);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl + "/armes")).POST(bodyPublisher).header("Content-Type", "application/json").build();

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
