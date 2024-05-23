package be.helha.apiprojetrpggroupe6.Config;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe qui lit le fichier de config json
 *
 * @author Pusic Filip
 */
public class LectureJson {

    /**
     * Le chemin d'accès vers le fichier
     */
    static final String jsonPath = "src/main/resources/config.json";
    /**
     * La classe qui va contenir les infos du json
     */
    JsonConfig jsonConfig;
    /**
     * Instance de notre classe singleton
     */
    private static LectureJson instance;

    /**
     * Si aucune instance éxiste, la fonction en crée une, si pas elle renvoie simplement l'instance éxistante
     * @return l'instance de notre singleton
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
     * Fonction qui renvoie le nom de la db de test du fichier de config
     * @return le nom de la db de test
     */
    public String getDbTest() {
        return jsonConfig.dbTest;
    }
    /**
     * Fonction qui renvoie le nom de la db du fichier de config
     * @return le nom de la db
     */
    public String getDbProduction() {
        return jsonConfig.dbProduction;
    }
    /**
     * Fonction qui renvoie le chemin d'accès des db du fichier de config
     * @return le chemin d'accès des db
     */
    public String getDbPath() {
        return jsonConfig.dbPath;
    }
}
