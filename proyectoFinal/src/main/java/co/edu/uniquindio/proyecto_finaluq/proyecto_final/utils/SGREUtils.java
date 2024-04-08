package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class SGREUtils {
    private static Usuario usuarioEnSesion;

    public static SGRE inicializarDatos(){
        SGRE sgre = new SGRE();

        Empleado empleado1 = new Empleado(null,null,null,null,new ArrayList<>());
        empleado1.setId("12123211");
        empleado1.setNombre("pepe");
        empleado1.setCorreo("pepe12@gmail.com");
        empleado1.setContrasenia("pepePro");

        Evento evento1=new Evento(null,null,null,null,0,null,new ArrayList<>());
        evento1.setId(Math.random()+"");
        evento1.setNombreEvento("rumba");
        evento1.setDescripcion("pa gozar");
        evento1.setFecha(LocalDateTime.now());
        evento1.setCapacidadMax(45);
        evento1.setEmpleadoEncargado(empleado1);

        empleado1.getEventosAsignados().add(evento1);

        sgre.getListaEmpleados().add(empleado1);

        Usuario usuario1 = new Usuario(null,null,null,null,new ArrayList<>());
        usuario1.setId("12341");
        usuario1.setNombre("juan");
        usuario1.setCorreo("juan@gmail.com");
        usuario1.setContrasenia("123wweaa");

        Reserva reserva1 = new Reserva(null,null,null,null,null,0);
        reserva1.setId(""+ Math.random());
        reserva1.setUsuario(usuario1);
        reserva1.setEvento(evento1);
        reserva1.setFechaSolicitud(LocalDateTime.now());
        reserva1.setEstado("PENDIENTE");
        reserva1.setEspaciosSolicitados(10);

        evento1.getReservas().add(reserva1);
        usuario1.getReservasAsignados().add(reserva1);

        sgre.getListaEventos().add(evento1);
        sgre.getListaReservas().add(reserva1);
        sgre.getListaUsuarios().add(usuario1);

        Empleado empleado2 = new Empleado(null,null,null,null,new ArrayList<>());
        empleado2.setId("123123123");
        empleado2.setNombre("maria");
        empleado2.setCorreo("mataFuerte@gmail.com");
        empleado2.setContrasenia("marialasanta");

        Evento evento2= new Evento(null,null,null,null,0,null,new ArrayList<>());
        evento2.setId(Math.random()+"");
        evento2.setNombreEvento("concierto");
        evento2.setDescripcion("ir a disfrutar");
        evento2.setFecha(LocalDateTime.now());
        evento2.setCapacidadMax(100);
        evento2.setEmpleadoEncargado(empleado1);

        empleado1.getEventosAsignados().add(evento2);

        Evento evento3=new Evento(null,null,null,null,0,null,new ArrayList<>());
        evento3.setId(Math.random()+"");
        evento3.setNombreEvento("bingo");
        evento3.setDescripcion("quien gana mas");
        evento3.setFecha(LocalDateTime.now());
        evento3.setCapacidadMax(15);
        evento3.setEmpleadoEncargado(empleado2);

        empleado2.getEventosAsignados().add(evento3);

        sgre.getListaEventos().add(evento2);
        sgre.getListaEventos().add(evento3);
        sgre.getListaEmpleados().add(empleado2);

        System.out.println("Información del sgre creada");
        return sgre;
    }

    public static Usuario getUsuarioEnSesion() {
        return SGREUtils.usuarioEnSesion;
    }

    public static void setUsuarioEnSesion(Usuario usuario) {
        SGREUtils.usuarioEnSesion = usuario;
    }
}
