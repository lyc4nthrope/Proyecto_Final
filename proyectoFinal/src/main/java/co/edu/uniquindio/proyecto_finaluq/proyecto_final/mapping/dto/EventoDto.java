package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;

public record EventoDto(
        String id, String nombreEvento, String descripcion,
        String fecha,
        String capacidadMax,
        String empleadoEncargado,
        String reservas

) {
}
