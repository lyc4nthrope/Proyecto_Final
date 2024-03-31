package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;



import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Evento;

import java.util.ArrayList;

public record EmpleadoDto(
        String id,
        String nombre,

        String correo,
        String contrasenia,

        ArrayList<Evento> eventosAsignados) {
}
