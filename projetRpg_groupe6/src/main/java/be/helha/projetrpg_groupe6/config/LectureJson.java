package be.helha.projetrpg_groupe6.config;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class LectureJson {
    static final String jsonPath = "src/main/resources/config.json";
    JsonConfig jsonConfig;
    private static LectureJson instance;
    public static LectureJson getInstance() {
        if(instance == null) {
            instance = new LectureJson();
        }
        return instance;
    }
    private LectureJson() {
        Path path = Paths.get(jsonPath);
        Gson gson = new Gson();
        try(Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            jsonConfig = gson.fromJson(reader, JsonConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getApiUrl() {
        return jsonConfig.apiUrl;
    }


}