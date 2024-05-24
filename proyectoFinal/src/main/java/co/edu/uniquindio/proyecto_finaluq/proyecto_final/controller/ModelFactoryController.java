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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements IModelFactoryController {
    SGRE sgre;
    SGREMapper mapper =SGREMapper.INSTANCE;

    Thread hilo1GuardarXml;
    Thread hilo2SalvarLog;
    Thread hilo3CargarDatosBase;
    Thread hilo4SalvarDatosPrueba;
    Thread hilo5CargarDatosDesdeArchivos;
    Thread hilo6CargarResourceBinario;
    Thread hilo7GuardarResourceBinario;
    Thread hilo8cargarResourceXML;


    String mensaje = "";
    int nivel = 0;
    String accion = "";
    BoundedSemaphore semaphore = new BoundedSemaphore(1);

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");
        cargarDatosBase();
        salvarDatosPrueba();
        //2. Cargar los datos de los archivos
        cargarDatosDesdeArchivos();
        salvarDatosPrueba();
        //3. Guardar y Cargar el recurso serializable binario
		cargarResourceBinario();
		guardarResourceBinario();
		//4. Guardar y Cargar el recurso serializable XML
		cargarResourceXML();
        guardarResourceXML();
        if(sgre == null){
            cargarDatosBase();
            guardarResourceXML();
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
            Persistencia.guardarUsuarios(getSGRE().getListaUsuarios());
            Persistencia.guardarEventos(getSGRE().getListaEventos());
            Persistencia.guardarReservas(getSGRE().getListaReservas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarResourceXML() {
        sgre = Persistencia.cargarRecursoSGREXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoSGREXML(sgre);
    }

    private void cargarResourceBinario() {
        sgre = Persistencia.cargarRecursoSGREBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoSGREBinario(sgre);
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
//                salvarDatosPrueba();
//                guardarResourceBinario();
                guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//                salvarDatosPrueba();
//                guardarResourceBinario();
                guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//                salvarDatosPrueba();
//                guardarResourceBinario();
                guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
//                salvarDatosPrueba();
//                guardarResourceBinario();
                guardarResourceXML();
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
//            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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
            salvarDatosPrueba();
//            guardarResourceBinario();
            guardarResourceXML();
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

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        ocupar();
        if(hiloActual == hilo1GuardarXml){
            Persistencia.guardarRecursoSGREXML(sgre);
            liberar();
        }
        if(hiloActual == hilo2SalvarLog){
            Persistencia.guardaRegistroLog(mensaje, nivel, accion);
            liberar();
        }
        try {
            if (hiloActual == hilo3CargarDatosBase) {
                Persistencia.cargarDatosArchivos(sgre);
                liberar();
            }
        }catch (FileNotFoundException e){

        }
        catch (IOException e){

        }
        if(hiloActual == hilo4SalvarDatosPrueba){
            salvarDatosPrueba();
            liberar();
        }
        try {
            if (hiloActual == hilo5CargarDatosDesdeArchivos) {
                Persistencia.cargarDatosArchivos(sgre);
                liberar();
            }
        }catch (FileNotFoundException e){

        }
        catch (IOException e){

        }
        if(hiloActual == hilo6CargarResourceBinario){
            Persistencia.cargarRecursoSGREBinario();
            liberar();
        }
        if(hiloActual == hilo7GuardarResourceBinario){
            Persistencia.guardarRecursoSGREBinario(sgre);
            liberar();
        }
        if(hiloActual == hilo8cargarResourceXML){
            Persistencia.cargarRecursoSGREXML();
            liberar();
        }
    }

    private void liberar() {
        try {
            semaphore.liberar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void ocupar() {
        try {
            semaphore.ocupar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
