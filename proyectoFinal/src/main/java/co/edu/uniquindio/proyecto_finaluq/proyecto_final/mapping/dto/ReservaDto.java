package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;

import java.time.LocalDateTime;

public record ReservaDto(
        String id,
        UsuarioDto usuario,
        EventoDto evento,
        LocalDateTime fechaSolicitud,
        String estado,
        int espaciosSolicitados
){
}
