package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;

import java.util.List;

public interface IReservaControllerService {

    List<ReservaDto> obtenerReserva();

    boolean agregarReserva(ReservaDto reservaDto);

    boolean eliminarReserva(String id);

    boolean actualizarReserva(String id, ReservaDto reservaDto);

}
