package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.InicioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioViewController {

    InicioController inicioController = new InicioController();
    @FXML
    public TextField txtContrasenia;

    @FXML
    public TextField txtCorreo;

    public void switchUsuarioUse(ActionEvent event, UsuarioDto usuario) throws IOException {
        SGREApplication m = new SGREApplication();
        m.changeScene("UsuarioUseView.fxml");
    }

    public void switchEmpleadoUse(ActionEvent event, EmpleadoDto empleadoDto) throws IOException {
        SGREApplication m = new SGREApplication();
        //controller.sesionUsuario=usuario;
        //Scene scene = new Scene(root);
       // stage.setScene(scene);
        //stage.show();
        m.changeScene(".fxml");
    }

    public void iniciarSesion(ActionEvent event) throws IOException {
        String correoAux= txtCorreo.getText();
        String contraseniaAux = txtContrasenia.getText();
        if (inicioController.inicioSesion(correoAux, contraseniaAux)){
            UsuarioDto usuarioDtoAux = inicioController.sesionUsuario(correoAux,contraseniaAux);
            EmpleadoDto empleadoDtoAux = inicioController.sesionEmpleado(correoAux,contraseniaAux);
            if (usuarioDtoAux!=null){
                switchUsuarioUse(event,usuarioDtoAux);
            }
            if (empleadoDtoAux!=null){
                switchEmpleadoUse(event, empleadoDtoAux);
            }
        }
    }

    @FXML
    void onRegistrarseButtonAction(ActionEvent event) throws IOException{
        SGREApplication m = new SGREApplication();
        m.changeScene("UsuarioRegistrarView.fxml");
    }

    public void salir(){
System.exit(0);
    }
}
