package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IUsuarioControllerService;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {

    ModelFactoryController modelFactoryController;

    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactoryController.obtenerUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return modelFactoryController.eliminarUsuario(id);
    }

    @Override
    public boolean actualizarUsuario(String idActual, UsuarioDto usuarioDto) {
        return modelFactoryController.modificarUsuario(idActual, usuarioDto);
    }

    public UsuarioDto obtenerUsuarioId(String id){
        return  modelFactoryController.obtenerUsuarioId(id);
    }

}
