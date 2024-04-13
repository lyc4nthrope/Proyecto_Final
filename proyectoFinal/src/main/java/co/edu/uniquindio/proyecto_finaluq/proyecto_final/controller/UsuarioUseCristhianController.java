package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IUsuarioUseCristhian;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioUseCristhianController implements IUsuarioUseCristhian {
    ModelFactoryController modelFactoryController;

    public UsuarioUseCristhianController(){
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
}
