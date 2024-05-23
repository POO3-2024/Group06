package be.helha.projetrpg_groupe6.services;

import be.helha.projetrpg_groupe6.models.Partie;
import be.helha.projetrpg_groupe6.personnage.Personnage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CombatService {

    private static Partie partie;
    private HttpClient client;
    private Gson gson = new Gson();


    public CombatService() {
    }

    public static Partie getPartie() {
        if (partie == null) {
            partie = new Partie();
        }
        return partie;
    }

    public Personnage attaquer(String idAttaque, String idArme){
        Personnage perso = null;
        this.client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/attaques/"+idAttaque+"/"+idArme))
                .GET()
                .build();
        try {
            HttpResponse<String> res =  client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Type personnageType = new TypeToken<Personnage>() {}.getType();
            perso = gson.fromJson(res.body(), personnageType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
       return perso;
    }

}
