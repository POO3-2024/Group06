package be.helha.projetrpg_groupe6.config;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

/**
 * La classe LectureJson lit le fichier de config json
 * C'est un singleton
 *
 * @author Pusic Filip
 */
public class LectureJson {
    /**
     * Le chemin d'accès vers le fichier config
     */
    static final String jsonPath = "src/main/resources/config.json";
    /**
     * Classe contenant les infos du fichier une fois lu
     */
    JsonConfig jsonConfig;
    /**
     * Instance de LectureJson car c'est un singleton
     */
    private static LectureJson instance;

    /**
     * Fonction pour instancier ou renvoyer l'instance de LectureJson
     * @return l'instance si elle existe et en crée une si elle n'éxiste pas
     */
    public static LectureJson getInstance() {
        if(instance == null) {
            instance = new LectureJson();
        }
        return instance;
    }

    /**
     * Constructeur privé de notre singleton
     */
    private LectureJson() {
        Path path = Paths.get(jsonPath);
        Gson gson = new Gson();
        try(Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            jsonConfig = gson.fromJson(reader, JsonConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return l'url de notre fichier json
     */
    public String getApiUrl() {
        return jsonConfig.apiUrl;
    }

}