package co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Evento;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Reserva;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Usuario;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SGREMapper {

    SGREMapper INSTANCE = Mappers.getMapper(SGREMapper.class);

    @Named("usuarioToUsuarioDto")
        // Mapeo principal de Usuario a UsuarioDto
    @Mapping(target = "reservasAsignados", ignore = true)
        UsuarioDto usuarioToUsuarioDto(Usuario usuario);
        // Método para establecer la longitud del ArrayList de reservas
        @AfterMapping
        default void establecerLongitudReservas(Usuario usuario, @MappingTarget UsuarioDto usuarioDto) {
            usuarioDto.cantidadReservas(usuario.cantidadReservas());
        }



    @IterableMapping(qualifiedByName = "eventoToEventoDto")
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);



    @Named("empleadoToEmpleadoDto")
    @IterableMapping(qualifiedByName = "eventoToEventoDto")
    @ValueMapping(target = "cantidadEventos",source = "empleado.eventosAsignados.size()")
    EmpleadoDto empleadoToEmpleadoDto (Empleado empleado);

    @IterableMapping(qualifiedByName = "eventoDtoToEvento")
    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);



    @Named("reservaToReservaDto")
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
