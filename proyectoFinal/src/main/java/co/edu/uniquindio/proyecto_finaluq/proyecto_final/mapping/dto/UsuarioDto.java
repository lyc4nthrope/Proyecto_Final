package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public record UsuarioDto(
        String id,
        String nombre,
        String correo,
        String contrasenia) {
}
