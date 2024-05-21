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
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

            Type armeListType = new TypeToken<List<Arme>>(){}.getType();

           listArme = gson.fromJson(response.body(),armeListType);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listArme;
    }
}
