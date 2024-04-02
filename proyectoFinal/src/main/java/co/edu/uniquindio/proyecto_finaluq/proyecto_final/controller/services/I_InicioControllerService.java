package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

public interface I_InicioControllerService {
    public boolean inicioSesion(String correo, String contrasenia);
    public UsuarioDto sesionUsuario(String correo, String contrasenia);
    public EmpleadoDto sesionEmpleado(String correo, String contrasenia);
}
