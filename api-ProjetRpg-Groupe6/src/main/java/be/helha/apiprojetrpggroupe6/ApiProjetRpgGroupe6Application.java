package be.helha.apiprojetrpggroupe6;

import be.helha.apiprojetrpggroupe6.Config.LectureJson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ApiProjetRpgGroupe6Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiProjetRpgGroupe6Application.class, args);
        LectureJson.getInstance().getDbPath();
    }

}
