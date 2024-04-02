package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.I_InicioControllerService;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.exceptions.InicioException;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

public class InicioController implements I_InicioControllerService {

    ModelFactoryController modelFactoryController;

    public InicioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public boolean inicioSesion(String correo, String contrasenia) {
        return modelFactoryController.inicioSesion(correo,contrasenia);
    }

    @Override
    public UsuarioDto sesionUsuario(String correo, String contrasenia) {
        return modelFactoryController.obtenerSesionUsuarioDto(correo,contrasenia);
    }

    @Override
    public EmpleadoDto sesionEmpleado(String correo, String contrasenia) {
        return modelFactoryController.obtenerSesionEmpleadoDto(correo,contrasenia);
    }
}
