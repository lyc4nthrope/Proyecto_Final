package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IReservaControllerService;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;

import java.util.List;

public class ReservaController implements IReservaControllerService {
    ModelFactoryController modelFactoryController;

    public ReservaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<ReservaDto> obtenerReserva() {
        return modelFactoryController.obtenerReservas();
    }

    @Override
    public boolean agregarReserva(ReservaDto reservaDto) {
        return modelFactoryController.agregarReserva(reservaDto);
    }

    @Override
    public boolean eliminarReserva(String id) {
        return modelFactoryController.eliminarReserva(id);
    }

    @Override
    public boolean actualizarReserva(String idActual, ReservaDto reservaDto) {
        return modelFactoryController.modificarReserva(idActual, reservaDto);
    }

    public boolean existeReserva(String id){return modelFactoryController.existeReserva(id);}

    public List<ReservaDto> reservasUsuario(UsuarioDto usuario){
        return modelFactoryController.reservasUsuario(usuario.id());
    }

}
