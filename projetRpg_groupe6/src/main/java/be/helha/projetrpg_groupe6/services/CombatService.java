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

/**
 * Service pour gérer les opérations de combat.
 */
public class CombatService {

    /**
     * Instance de la partie en cours.
     */
    private static Partie partie;
    /**
     * Client HTTP pour les requêtes REST.
     */
    private HttpClient client;
    /**
     * Parseur Gson pour les objets JSON.
     */
    private Gson gson = new Gson();


    /**
     * Constructeur par défaut.
     */
    public CombatService() {
    }

    /**
     * Récupère l'instance de la partie en cours.
     * @return Partie partie en cours
     */

    public static Partie getPartie() {
        if (partie == null) {
            partie = new Partie();
        }
        return partie;
    }

    /**
     * Service permettant d'attaquer un personnage.
     * @param idAttaque l'identifiant de l'attaquant
     * @param idArme l'identifiant de l'arme utilisée
     * @return le personnage attaqué
     */
    public Personnage attaquer(int idAttaque, int idArme){
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
