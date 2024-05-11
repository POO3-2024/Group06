package be.helha.projetrpg_groupe6.configNomAChanger;

import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;

public class LectureJson {
    private String dbUrl;
    public LectureJson() {
        readConfig();
    }
    private void readConfig() {
        try (FileReader reader = new FileReader("src/main/resources/config.json")) { //Tente
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject(); //Analyse le fichier json

            dbUrl = jsonObject.getAsJsonObject("DBPropreties").get("DBUrl").getAsString(); //Récupere l'url de la db dans la config
        } catch (IOException e) { //Echec
            e.printStackTrace();
        }
    }
    public String getDbUrl() { //Fonction qui retourne l'url de la db dans la config
        return dbUrl;
    }
}