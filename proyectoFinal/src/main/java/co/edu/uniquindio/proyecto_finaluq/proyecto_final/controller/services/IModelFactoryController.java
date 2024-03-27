package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Reserva;

import java.util.List;

public interface IModelFactoryController {
    List<EmpleadoDto> obtenerEmpleados();
boolean agregarEmpleado(EmpleadoDto empleadoDto);
boolean eliminarEmpleado(String id);
boolean modificarEmpleado(String idActual, EmpleadoDto empleadoDto);

List<UsuarioDto> obtenerUsuarios();
boolean agregarUsuario(UsuarioDto usuarioDto);
boolean eliminarUsuario(String id);
boolean modificarUsuario(String idActual, UsuarioDto usuarioDto);

List<ReservaDto> obtenerReservas();
boolean agregarReserva(ReservaDto reservaDto);
boolean elimiarReserva(String id);
boolean modificarReserva(String idActual,ReservaDto reservaDto);

List<EventoDto> obtenerEventos();
boolean agregarEvento(EventoDto eventoDto);
boolean eliminarEvento(String id);
boolean modificarEvento(String idActual, EventoDto eventoDto);

}
