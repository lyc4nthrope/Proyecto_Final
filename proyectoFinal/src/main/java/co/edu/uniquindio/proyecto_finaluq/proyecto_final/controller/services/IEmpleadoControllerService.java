package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import java.util.List;

public interface IEmpleadoControllerService {

    List<EmpleadoDto> obtenerEmpleados();

    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String id);

    boolean actualizarEmpleado(String id, EmpleadoDto empleadoDto);

}
