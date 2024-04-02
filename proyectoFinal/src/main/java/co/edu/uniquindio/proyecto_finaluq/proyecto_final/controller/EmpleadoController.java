package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IEmpleadoControllerService;

import java.util.List;

public class EmpleadoController implements IEmpleadoControllerService {

    ModelFactoryController modelFactoryController;

    public EmpleadoController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<EmpleadoDto> obtenerEmpleados() {
        return modelFactoryController.obtenerEmpleados();
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        return modelFactoryController.agregarEmpleado(empleadoDto);
    }

    @Override
    public boolean eliminarEmpleado(String id) {
        return modelFactoryController.eliminarEmpleado(id);
    }

    @Override
    public boolean actualizarEmpleado(String idActual, EmpleadoDto empleadoDto) {
        return modelFactoryController.modificarEmpleado(idActual, empleadoDto);
    }
}
