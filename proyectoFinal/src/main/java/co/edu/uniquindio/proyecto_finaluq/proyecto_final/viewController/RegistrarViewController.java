package co.edu.uniquindio.proyecto_finaluq.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.EmpleadoController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.UsuarioController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.AdminDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrarViewController{

    @FXML
    private TextField txtConfimarContraseña;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;


    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {
        if (!txtNombre.getText().trim().isEmpty() && !txtId.getText().trim().isEmpty() && !txtCorreo.getText().trim().isEmpty() && !txtContraseña.getText().trim().isEmpty() && !txtConfimarContraseña.getText().trim().isEmpty()){
            if (!txtContraseña.getText().equals(txtConfimarContraseña.getText())){
            SGREApplication.mostrarMensaje("Error","Contraseñas no coinciden", "Las contraseñas no coinciden asegurese de que ingreso correctamente", Alert.AlertType.ERROR );
            }else {
                // registrar usuario con comprobacion
                UsuarioController usuarioController = new UsuarioController();
                if (usuarioController.agregarUsuario(obtenerUsuario())){
                    vaciarCampos();
                    SGREApplication.mostrarMensaje("Registro completo", "El registro se realizo correctamente", "Se registro correctamente, ahora inicia sesion!", Alert.AlertType.INFORMATION);
                    volver();
                }else{
                    SGREApplication.mostrarMensaje("Error al registrar", "Hubo un error al registrar", "Se genero un error al registrar, posiblemente tu cedula ya este registrado o tu correo ya este en uso", Alert.AlertType.ERROR);
                }
            }
        }else {
            SGREApplication.mostrarMensaje("Error", "Campos vacios", "Algun campo no ha sido llenado, por favor ingrese todos los datos", Alert.AlertType.ERROR);
        }
    }


    private void registrarEmpleado(ActionEvent event) throws IOException{
        if (!txtNombre.getText().trim().isEmpty() && !txtId.getText().trim().isEmpty() && !txtCorreo.getText().trim().isEmpty() && !txtContraseña.getText().trim().isEmpty() && !txtConfimarContraseña.getText().trim().isEmpty()){
            if (!txtContraseña.getText().equals(txtConfimarContraseña.getText())){
                SGREApplication.mostrarMensaje("Error","Contraseñas no coinciden", "Las contraseñas no coinciden asegurese de que ingreso correctamente", Alert.AlertType.ERROR );
            }else {
                // registrar empleado con comprobacion
                EmpleadoController empleadoController = new EmpleadoController();
                if (empleadoController.agregarEmpleado(obtenerEmpleado())){
                    vaciarCampos();
                    SGREApplication.mostrarMensaje("Registro completo", "El registro se realizo correctamente", "Se registro correctamente, ahora inicia sesion!", Alert.AlertType.INFORMATION);
                    volver();
                }else{
                    SGREApplication.mostrarMensaje("Error al registrar", "Hubo un error al registrar", "Se genero un error al registrar, posiblemente tu cedula ya este registrado o tu correo ya este en uso", Alert.AlertType.ERROR);
                }
            }
        }else {
            SGREApplication.mostrarMensaje("Error", "Campos vacios", "Algun campo no ha sido llenado, por favor ingrese todos los datos", Alert.AlertType.ERROR);
        }
    }

    private void registrarAdmin(ActionEvent event) throws IOException{
        if (!txtNombre.getText().trim().isEmpty() && !txtId.getText().trim().isEmpty() && !txtCorreo.getText().trim().isEmpty() && !txtContraseña.getText().trim().isEmpty() && !txtConfimarContraseña.getText().trim().isEmpty()){
            if (!txtContraseña.getText().equals(txtConfimarContraseña.getText())){
                SGREApplication.mostrarMensaje("Error","Contraseñas no coinciden", "Las contraseñas no coinciden asegurese de que ingreso correctamente", Alert.AlertType.ERROR );
            }else {
                // registrar admin con comprobacion

                //AdminController adminController = new AdminController();
//                if (adminController.agregarAdmin(obtenerAdmin())){
//                    vaciarCampos();
//                    SGREApplication.mostrarMensaje("Registro completo", "El registro se realizo correctamente", "Se registro correctamente, ahora inicia sesion!", Alert.AlertType.INFORMATION);
//                    volver();
//                }else{
//                    SGREApplication.mostrarMensaje("Error al registrar", "Hubo un error al registrar", "Se genero un error al registrar, posiblemente tu cedula ya este registrado o tu correo ya este en uso", Alert.AlertType.ERROR);
//                }
            }
        }else {
            SGREApplication.mostrarMensaje("Error", "Campos vacios", "Algun campo no ha sido llenado, por favor ingrese todos los datos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void volver() throws IOException {
        SGREApplication.changeScene("Inicio.fxml",null,null);
    }


    private UsuarioDto obtenerUsuario(){
        String nombreUsuario = txtNombre.getText();
        String cedulaUsuario = txtId.getText();
        String correoUsuario = txtCorreo.getText();
        String contraseñaUsuario = txtContraseña.getText();
        return new UsuarioDto(cedulaUsuario,nombreUsuario,correoUsuario,contraseñaUsuario);
    }

    private EmpleadoDto obtenerEmpleado(){
        String nombreEmpleado = txtNombre.getText();
        String cedulaEmpleado = txtId.getText();
        String correoEmpleado = txtCorreo.getText();
        String contraseñaEmpleado = txtContraseña.getText();
        return new EmpleadoDto(cedulaEmpleado, nombreEmpleado, correoEmpleado, contraseñaEmpleado);
    }

    private AdminDto obtenerAdmin(){
        String nombreAdmin = txtNombre.getText();
        String cedulaAdmin = txtId.getText();
        String correoAdmin = txtCorreo.getText();
        String contraseñaAdmin = txtContraseña.getText();
        return new AdminDto(cedulaAdmin, nombreAdmin, correoAdmin, contraseñaAdmin);
    }

    private void vaciarCampos(){
        txtNombre.setText("");
        txtContraseña.setText("");
        txtCorreo.setText("");
        txtId.setText("");
        txtConfimarContraseña.setText("");
    }
}

