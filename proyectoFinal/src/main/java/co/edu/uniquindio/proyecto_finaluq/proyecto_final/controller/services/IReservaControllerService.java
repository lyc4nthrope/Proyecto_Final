package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;

public interface IReservaControllerService {
    public boolean agregarReserva(ReservaDto reservaDto);
    public boolean eliminarReserva(String id);
    public boolean actualizarReserva(String idActual, ReservaDto reservaDto);
}
