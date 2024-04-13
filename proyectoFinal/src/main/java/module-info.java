module co.edu.uniquindio.proyecto_final.proyecto_finaluq.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.mapstruct;
    requires java.logging;

    opens co.edu.uniquindio.proyecto_finaluq.proyecto_final to javafx.fxml;
    opens co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController to javafx.fxml;
    opens co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller to javafx.fxml;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.exceptions;
}