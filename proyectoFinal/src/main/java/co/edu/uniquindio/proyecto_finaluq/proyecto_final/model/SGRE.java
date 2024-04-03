package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.exceptions.*;

import java.util.ArrayList;

public class SGRE {
    ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    ArrayList<Reserva> listaReservas = new ArrayList<>();
    ArrayList<Evento> listaEventos = new ArrayList<>();

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(ArrayList<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    //Para empleados
    public boolean modificarEmpleado(String idActual, Empleado empleado) throws EmpleadoException{
        Empleado empleadoActual=obtenerEmpleadoId(idActual,null,0,false);
        if (empleadoActual==null){
            throw new EmpleadoException("Empleado a modificar no existe");
        }else {
            empleadoActual.setId(empleado.getId());
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setCorreo(empleado.getCorreo());
            empleadoActual.setContrasenia(empleado.getContrasenia());
            empleadoActual.setEventosAsignados(empleado.getEventosAsignados());
            return true;
        }
    }

    public boolean eliminarEmpleado(String id)throws EmpleadoException{
        if (!existeEmpleado(id,false,0)){
            throw new EmpleadoException("El empleado con id "+id+" NO esta registrado");
        }else {
            Empleado empleadoEliminar = obtenerEmpleadoId(id,null,0,false);
            listaEmpleados.remove(empleadoEliminar);
            return true;
        }
    }

    public boolean existeEmpleado(String id, boolean existe, int i){
        if (existe || i>=listaEmpleados.size()){
            return existe;
        }else {
            if(listaEmpleados.get(i).getId().equals(id)){
                existe=true;
            }
        }
        return existeEmpleado(id,existe,i+1);
    }

    public boolean existeEmpleadoCorreo(String correo, boolean existe, int i){
        if (existe || i>=listaEmpleados.size()){
            return existe;
        }else {
            if(listaEmpleados.get(i).getCorreo().equals(correo)){
                existe=true;
            }
        }
        return existeEmpleadoCorreo(correo,existe,i+1);
    }

    public boolean verficarExisteEmpleado(String id) throws EmpleadoException{
        if (existeEmpleado(id,false,0)){
            throw new EmpleadoException("El empleado con id "+id+" ya esta registrado");
        }else {
            return false;
        }
    }

    public Empleado obtenerEmpleadoId(String id,Empleado empleado, int i, boolean encontrado){
        if (encontrado || i>=listaEmpleados.size()){
            return empleado;
        }else{
            if (listaEmpleados.get(i).getId().equals(id)){
                empleado = listaEmpleados.get(i);
                encontrado=true;
            }
        }
        return obtenerEmpleadoId(id,empleado,i+1,encontrado);
    }


//Para usuarios
    public boolean existeUsuario(String id, boolean existe, int i){
        if (existe || i>= listaUsuarios.size()){
            return existe;
        }else {
            if(listaUsuarios.get(i).getId().equals(id)){
                existe=true;
            }
        }
        return existeUsuario(id,existe,i+1);
    }

    public boolean existeUsuarioCorreo(String correo, boolean existe, int i){
        if (existe || i>= listaUsuarios.size()){
            return existe;
        }else {
            if(listaUsuarios.get(i).getCorreo().equals(correo)){
                existe=true;
            }
        }
        return existeUsuarioCorreo(correo,existe,i+1);
    }
    public boolean verficarExisteUsuario(String id) throws UsuarioException{
        if (existeEmpleado(id,false,0)){
            throw new UsuarioException("El usuario con id "+id+" ya esta registrado");
        }else {
            return false;
        }
    }

    public Usuario obtenerUsuarioId(String id,Usuario usuario, int i, boolean encontrado){
        if (encontrado){
            return usuario;
        }else{
            if (listaUsuarios.get(i).getId().equals(id)){
                usuario = listaUsuarios.get(i);
                encontrado=true;
            }
        }
        return obtenerUsuarioId(id,usuario,i+1,encontrado);
    }

    public boolean eliminarUsuario(String id) throws UsuarioException{
        if (!existeUsuario(id,false,0)){
            throw new UsuarioException("El usuario con id "+id+" NO esta registrado");
        }else {
            Usuario usuarioEliminar = obtenerUsuarioId(id,null,0,false);
            listaUsuarios.remove(usuarioEliminar);
            return true;
        }
    }

    public boolean modificarUsuario(String idActual, Usuario usuario) throws UsuarioException{
        Usuario usuarioActual=obtenerUsuarioId(idActual,null,0,false);
        if (usuarioActual==null){
            throw new UsuarioException("Empleado a modificar no existe");
        }else {
            usuarioActual.setId(usuario.getId());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setCorreo(usuario.getCorreo());
            usuarioActual.setContrasenia(usuario.getContrasenia());
            usuarioActual.setReservasAsignados(usuario.getReservasAsignados());
            return true;
        }
    }

    //Para eventos
    public boolean existeEvento(String id, boolean existe, int i){
        if (existe || i>= listaEventos.size()){
            return existe;
        }else {
            if(listaEventos.get(i).getId().equals(id)){
                existe=true;
            }
        }
        return existeEvento(id,existe,i+1);
    }
    public boolean verficarExisteEvento(String id) throws EventoException{
        if (existeEvento(id,false,0)){
            throw new EventoException("El evento con id "+id+" ya esta registrado");
        }else {
            return false;
        }
    }

    public Evento obtenerEventoId(String id,Evento evento, int i, boolean encontrado){
        if (encontrado){
            return evento;
        }else{
            if (listaEventos.get(i).getId().equals(id)){
                evento = listaEventos.get(i);
                encontrado=true;
            }
        }
        return obtenerEventoId(id,evento,i+1,encontrado);
    }

    public boolean eliminarEvento(String id) throws EventoException{
        if (!existeEvento(id,false,0)){
            throw new EventoException("El evento con id "+id+" NO esta registrado");
        }else {
            Evento eventoEliminar = obtenerEventoId(id,null,0,false);
            listaEventos.remove(eventoEliminar);
            return true;
        }
    }

    public boolean modificarEvento(String idActual, Evento evento) throws EventoException{
        Evento eventoActual=obtenerEventoId(idActual,null,0,false);
        if (eventoActual==null){
            throw new EventoException("Empleado a modificar no existe");
        }else {
            eventoActual.setId(evento.getId());
            eventoActual.setNombreEvento(eventoActual.getNombreEvento());
            eventoActual.setDescripcion(eventoActual.getDescripcion());
            eventoActual.setFecha(eventoActual.getFecha());
            eventoActual.setCapacidadMax(eventoActual.getCapacidadMax());
            eventoActual.setEmpleadoEncargado(evento.getEmpleadoEncargado());
            eventoActual.setReservas(evento.getReservas());
            return true;
        }
    }

    //Para Reservas
    public boolean existeReserva(String id, boolean existe, int i){
        if (existe || i>= listaReservas.size()){
            return existe;
        }else {
            if(listaReservas.get(i).getId().equals(id)){
                existe=true;
            }
        }
        return existeUsuario(id,existe,i+1);
    }
    public boolean verficarExisteReserva(String id) throws ReservaException{
        if (existeReserva(id,false,0)){
            throw new ReservaException("El reversa con id "+id+" ya esta registrado");
        }else {
            return false;
        }
    }

    public Reserva obtenerReservaId(String id,Reserva reserva, int i, boolean encontrado){
        if (encontrado){
            return reserva;
        }else{
            if (listaReservas.get(i).getId().equals(id)){
                reserva = listaReservas.get(i);
                encontrado=true;
            }
        }
        return obtenerReservaId(id,reserva,i+1,encontrado);
    }

    public boolean eliminarReserva(String id) throws ReservaException{
        if (!existeReserva(id,false,0)){
            throw new ReservaException("El reserva con id "+id+" NO esta registrado");
        }else {
            Reserva reservaEliminar = obtenerReservaId(id,null,0,false);
            listaReservas.remove(reservaEliminar);
            return true;
        }
    }

    public boolean modificarReserva(String idActual, Reserva reserva) throws ReservaException{
        Reserva reservaActual=obtenerReservaId(idActual,null,0,false);
        if (reservaActual==null){
            throw new ReservaException("Empleado a modificar no existe");
        }else {
            reservaActual.setId(reserva.getId());
            reservaActual.setUsuario(reservaActual.getUsuario());
            reservaActual.setEvento(reservaActual.getEvento());
            reservaActual.setFechaSolicitud(reservaActual.getFechaSolicitud());
            reservaActual.setEstado(reservaActual.getEstado());
            return true;
        }
    }

    public boolean registroCorrecto(String correo, String contrasenia) throws InicioException{
        if(registroEmpleado(correo,contrasenia)==null && registroUsuario(correo,contrasenia)==null){
            throw new InicioException("Correo o contrasenia incorrecto, intente de nuevo");
        }else {
            return true;
        }
    }

    public Usuario registroUsuario(String correo, String contrasenia){
        Usuario usuarioTry=obtenerUsuarioCorreo(correo,null,0);
        if (usuarioTry!=null){
            if (usuarioTry.getContrasenia().equals(contrasenia)){
                return usuarioTry;
            }
        }
        return null;
    }

    public Empleado registroEmpleado(String correo, String contrasenia){
        Empleado empleadoTry=obtenerEmpleadoCorreo(correo,null,0);
        if (empleadoTry!=null){
            if (empleadoTry.getContrasenia().equals(contrasenia)){
                return empleadoTry;
            }
        }
        return null;
    }

    public Usuario obtenerUsuarioCorreo(String correo, Usuario usuario, int i){
        if (usuario!=null || i>=listaUsuarios.size()){
            return usuario;
        }else {
            if (correo.equals(listaUsuarios.get(i).getCorreo())){
                usuario=listaUsuarios.get(i);
            }
        }
        return obtenerUsuarioCorreo(correo,usuario,i+1);
    }

    public Empleado obtenerEmpleadoCorreo(String correo, Empleado empleado, int i){
        if (empleado!=null || i>=listaEmpleados.size()){
            return empleado;
        }else {
            if (correo.equals(listaEmpleados.get(i).getCorreo())){
                empleado=listaEmpleados.get(i);
            }
        }
        return obtenerEmpleadoCorreo(correo,empleado,i+1);
    }

    public boolean datoRegistrado(String correo, String id){
        return existeUsuarioCorreo(correo, false, 0) && obtenerUsuarioId(id, null, 0, false) != null;
    }

    public boolean hayCupo(int cantCupos, Evento evento){
        return cantCupos>evento.getCapacidadMax();
    }


}
