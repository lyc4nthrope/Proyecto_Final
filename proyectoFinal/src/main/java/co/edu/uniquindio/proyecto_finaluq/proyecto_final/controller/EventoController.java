package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IEventoControllerService;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;

import java.util.ArrayList;
import java.util.List;

public class EventoController implements IEventoControllerService {

    ModelFactoryController modelFactoryController;

    public EventoController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<EventoDto> obtenerEvento() {
        return modelFactoryController.obtenerEventos();
    }


    @Override
    public ArrayList<EventoDto> obtenerEventos() {
        return null;
    }

    @Override
    public boolean agregarEvento(EventoDto eventoDto) {
        return modelFactoryController.agregarEvento(eventoDto);
    }

    @Override
    public boolean eliminarEvento(String id) {
        return modelFactoryController.eliminarEvento(id);
    }

    @Override
    public boolean actualizarEvento(String idActual, EventoDto eventoDto) {
        return modelFactoryController.modificarEvento(idActual, eventoDto);
    }



}
