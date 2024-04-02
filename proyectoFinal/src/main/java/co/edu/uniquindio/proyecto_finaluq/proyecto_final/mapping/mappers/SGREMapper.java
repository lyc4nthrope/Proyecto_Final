package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Evento;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Reserva;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SGREMapper {

    SGREMapper INSTANCE = Mappers.getMapper(SGREMapper.class);

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

@IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

    @Named("empleadoToEmpleadoDto")
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);
    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);

    @Named("reservaToResevaDto")
    ReservaDto reservaToReservaDto(Reserva reserva);
    Reserva reservaDtoToReserva(ReservaDto reservaDto);
    @IterableMapping(qualifiedByName = "reservaToReservaDto")
    List<ReservaDto> getListaReservasDto(List<Reserva> listaReservas);

    @Named("eventoToEventoDto")
    EventoDto eventoToEventoDto (Evento evento);
    Evento eventoDtoToEvento (EventoDto eventoDto);
    @IterableMapping(qualifiedByName = "eventoToEventoDto")
    List<EventoDto> getListaEventosDto (List<Evento> listaEventos);
}
