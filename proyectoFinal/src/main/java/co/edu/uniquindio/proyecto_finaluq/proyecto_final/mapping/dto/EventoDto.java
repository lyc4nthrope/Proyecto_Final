package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record EventoDto(
String id,String nombreEvento,String descripcion,
LocalDateTime fecha,
int capacidadMax,
EmpleadoDto empleadoEncargado,
ArrayList<ReservaDto> reservas

) {
}
