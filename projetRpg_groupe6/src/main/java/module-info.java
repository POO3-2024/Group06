module be.helha.projetrpg_groupe6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;

    opens be.helha.projetrpg_groupe6 to javafx.fxml;
    opens be.helha.projetrpg_groupe6.personnage to com.google.gson;
    exports be.helha.projetrpg_groupe6;
    exports be.helha.projetrpg_groupe6.controller;
    opens be.helha.projetrpg_groupe6.controller to javafx.fxml;
    opens be.helha.projetrpg_groupe6.arme to com.google.gson;
    opens be.helha.projetrpg_groupe6.config to com.google.gson;
    opens be.helha.projetrpg_groupe6.fx to javafx.base;
}