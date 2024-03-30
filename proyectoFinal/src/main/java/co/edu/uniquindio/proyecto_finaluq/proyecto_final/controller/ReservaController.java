package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;

import java.util.List;

public class ReservaController {
    ModelFactoryController modelFactoryController;

    public ReservaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<ReservaDto> obtenerReserva() {
        return modelFactoryController.obtenerReservas();
    }

    public boolean agregarReserva(ReservaDto reservaDto) {
        return modelFactoryController.agregarEmpleado(reservaDto);
    }

    @Override
    public boolean eliminarReserva(String id) {
        return modelFactoryController.eliminarReserva(id);
    }

    @Override
    public boolean actualizarReserva(String idActual, ReservaDto reservaDto) {
        return modelFactoryController.modificarEmpleado(idActual, reservaDto);
    }
}
