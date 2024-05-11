module be.helha.projetrpg_groupe6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens be.helha.projetrpg_groupe6 to javafx.fxml;
    exports be.helha.projetrpg_groupe6;
    exports be.helha.projetrpg_groupe6.controller;
    opens be.helha.projetrpg_groupe6.controller to javafx.fxml;
}