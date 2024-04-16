package co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.SGREApplication;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.services.IModelFactoryController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.exceptions.*;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.*;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.mappers.SGREMapper;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.*;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.Persistencia;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils.SGREUtils;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements IModelFactoryController {
    SGRE sgre;
SGREMapper mapper =SGREMapper.INSTANCE;


    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");

        //2. Cargar los datos de los archivos
		cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
//		cargarResourceBinario();
//		guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
//		guardarResourceXML();

        if(sgre == null){
            cargarDatosBase();
            salvarDatosPrueba();
            //guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosDesdeArchivos() {
        sgre = new SGRE();
        try {
            Persistencia.cargarDatosArchivos(sgre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEmpleados(getSGRE().getListaEmpleados());
            Persistencia.guardarUsuario(getSGRE().getListaUsuarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosBase() {
        sgre = SGREUtils.inicializarDatos();
    }

    public SGRE getSGRE(){return sgre;}
    public void setSgre(SGRE sgreAux){this.sgre=sgreAux;}


    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
        return mapper.getEmpleadosDto(sgre.getListaEmpleados());
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        try {
            if (!sgre.verficarExisteEmpleado(empleadoDto.id())){
                Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
                getSGRE().getListaEmpleados().add(empleado);
                registrarAccionesSistema("Se agrego el empleado"+ empleado.getNombre(),1,"agregarEmpleado");
            }
            return true;
        }catch (EmpleadoException e){
            e.getMessage();
            return false;
        }
    }



    @Override
    public boolean eliminarEmpleado(String id) {
        boolean eliminado =false;
        try {
            eliminado=getSGRE().eliminarEmpleado(id);
        }catch (EmpleadoException e){
            e.printStackTrace();
        }
        return eliminado;
    }
    @Override
    public boolean modificarEmpleado(String idActual, EmpleadoDto empleadoDto) {
        try{
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            getSGRE().modificarEmpleado(idActual,empleado);
            return true;
        }catch (EmpleadoException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(sgre.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try {
            if (!(sgre.verficarExisteUsuario(usuarioDto.id())) && !(sgre.datoRegistrado(usuarioDto.correo(),usuarioDto.id()))){
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSGRE().getListaUsuarios().add(usuario);
                return true;
            }
        }catch (UsuarioException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        boolean eliminado =false;
        try {
            eliminado=getSGRE().eliminarUsuario(id);
        }catch (UsuarioException e){
            e.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public boolean modificarUsuario(String idActual, UsuarioDto usuarioDto) {
        try{
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getSGRE().modificarUsuario(idActual,usuario);
            return true;
        }catch (UsuarioException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ReservaDto> obtenerReservas() {
        return mapper.getListaReservasDto(sgre.getListaReservas());
    }

    @Override
    public boolean agregarReserva(ReservaDto reservaDto) {
        try {
            if (!sgre.verficarExisteReserva(reservaDto.id())){
                Reserva reserva = mapper.reservaDtoToReserva(reservaDto);
                getSGRE().getListaReservas().add(reserva);
            }
            return true;
        }catch (ReservaException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarReserva(String id) {
        boolean eliminado =false;
        try {
            eliminado=getSGRE().eliminarReserva(id);
        }catch (ReservaException e){
            SGREApplication.mostrarMensaje("error","error","error", Alert.AlertType.ERROR);
        }
        return eliminado;
    }

    @Override
    public boolean modificarReserva(String idActual, ReservaDto reservaDto) {
        try{
            Reserva reserva = mapper.reservaDtoToReserva(reservaDto);
            getSGRE().modificarReserva(idActual,reserva);
            return true;
        }catch (ReservaException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EventoDto> obtenerEventos() {
        return new ArrayList<>(mapper.getListaEventosDto(sgre.getListaEventos()));
    }

    @Override
    public boolean agregarEvento(EventoDto eventoDto) {
        try {
            if (!sgre.verficarExisteEvento(eventoDto.id())){
                Evento evento = mapper.eventoDtoToEvento(eventoDto);
                getSGRE().getListaEventos().add(evento);
            }
            return true;
        }catch (EventoException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEvento(String id) {
        boolean eliminado =false;
        try {
            eliminado=getSGRE().eliminarEvento(id);
        }catch (EventoException e){
            e.printStackTrace();
        }
        return eliminado;
    }

    public  boolean existeReserva(String id){
        return getSGRE().existeReserva(id,false,0);
    }
    @Override
    public boolean modificarEvento(String idActual, EventoDto eventoDto) {
        try{
            Evento evento = mapper.eventoDtoToEvento(eventoDto);
            getSGRE().modificarEvento(idActual,evento);
            return true;
        }catch (EventoException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean inicioSesion(String correo, String contrasenia){
        try{
            getSGRE().registroCorrecto(correo,contrasenia);
            return true;
        }catch (InicioException e){
            SGREApplication.mostrarMensaje("error","error al iniciar sesion","Los datos ingresados no estan registrados o hubo un error al introducirlos", Alert.AlertType.ERROR);
            return false;
        }
    }

    public  UsuarioDto obtenerSesionUsuarioDto(String correo, String contrasenia){
        Usuario usuarioTry=getSGRE().registroUsuario(correo,contrasenia);
        if (usuarioTry!=null){
            return mapper.usuarioToUsuarioDto(usuarioTry);
        }
        return null;
    }

    public EmpleadoDto obtenerSesionEmpleadoDto(String correo, String contrasenia){
        Empleado empleadoTry=getSGRE().registroEmpleado(correo,contrasenia);
        if(empleadoTry!=null){
            return mapper.empleadoToEmpleadoDto(empleadoTry);
        }
        return null;
    }


    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    public UsuarioDto obtenerUsuarioId(String id){
        return mapper.usuarioToUsuarioDto(getSGRE().obtenerUsuarioId(id, null, 0,false));
    }

    public List<ReservaDto> getReservasUsuario(String idUsuario){
        return mapper.getListaReservasDto(getSGRE().reservasUsuario(idUsuario,0,new ArrayList<>()));
    }

}
