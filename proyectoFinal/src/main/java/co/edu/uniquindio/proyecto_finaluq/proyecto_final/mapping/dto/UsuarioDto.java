package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;
import java.util.ArrayList;

public record UsuarioDto(
        String id,
        String nombre,
        String correo,

        ArrayList<ReservaDto> reservasAsignados) {
}
