module co.edu.uniquindio.proyecto_final.proyecto_finaluq.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.mapstruct;

    opens co.edu.uniquindio.proyecto_finaluq.proyecto_final to javafx.fxml;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final;
    exports co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;
}