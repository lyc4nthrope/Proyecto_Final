package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;

import java.util.List;

public interface IEventoControllerService {

    List<EventoDto> obtenerEventos();

    boolean agregarEvento(EventoDto eventoDto);

    boolean eliminarEvento(String id);

    boolean actualizarEvento(String id, EventoDto eventoDto);
}
