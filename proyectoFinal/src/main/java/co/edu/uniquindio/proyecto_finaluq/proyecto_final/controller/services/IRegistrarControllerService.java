package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

import java.util.List;

public interface IRegistrarControllerService {
    List<UsuarioDto> obtenerUsuario();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String id);

    boolean actualizarUsuario(String id, UsuarioDto usuarioDto);
}
