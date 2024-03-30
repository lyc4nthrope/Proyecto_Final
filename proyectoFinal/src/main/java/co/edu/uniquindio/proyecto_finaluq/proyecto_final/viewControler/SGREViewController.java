package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewControler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SGREViewController implements Initializable {

    SGREControllerService sgreControllerService;

    @FXML
    void initialize() {sgreControllerService = new SGREController();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
