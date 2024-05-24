package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class SGREUtils {
    private static UsuarioDto usuarioEnSesion;
    private static EmpleadoDto empleadoEnSesion;

    public static SGRE inicializarDatos(){
        SGRE sgre = new SGRE();

        Empleado empleado1 = new Empleado(null,null,null,null,new ArrayList<>());
        empleado1.setId("12123211");
        empleado1.setNombre("pepe");
        empleado1.setCorreo("pepe12@gmail.com");
        empleado1.setContrasenia("pepePro");

        Evento evento1=new Evento(null,null,null,null,0,null);
        evento1.setId(((int) (Math.random() * 999999))+"");
        evento1.setNombreEvento("rumba");
        evento1.setDescripcion("pa gozar");
        evento1.setFecha(LocalDateTime.now());
        evento1.setCapacidadMax(45);
        evento1.setEmpleadoEncargado(empleado1);

        sgre.getListaEmpleados().add(empleado1);

        Usuario usuario1 = new Usuario(null,null,null,null);
        usuario1.setId("12341");
        usuario1.setNombre("juan");
        usuario1.setCorreo("juan@gmail.com");
        usuario1.setContrasenia("123wweaa");

        Reserva reserva1 = new Reserva(null,null,null,null,null,0);
        reserva1.setId(((int) (Math.random() * 999999))+"");
        reserva1.setUsuario(usuario1);
        reserva1.setEvento(evento1);
        reserva1.setFechaSolicitud(LocalDateTime.now());
        reserva1.setEstado("PENDIENTE");
        reserva1.setEspaciosSolicitados(10);

        sgre.getListaEventos().add(evento1);
        sgre.getListaReservas().add(reserva1);
        sgre.getListaUsuarios().add(usuario1);

        Empleado empleado2 = new Empleado(null,null,null,null,new ArrayList<>());
        empleado2.setId("123123123");
        empleado2.setNombre("maria");
        empleado2.setCorreo("mataFuerte@gmail.com");
        empleado2.setContrasenia("marialasanta");

        Evento evento2= new Evento(null,null,null,null,0,null);
        evento2.setId(((int) (Math.random() * 999999))+"");
        evento2.setNombreEvento("concierto");
        evento2.setDescripcion("ir a disfrutar");
        evento2.setFecha(LocalDateTime.now());
        evento2.setCapacidadMax(100);
        evento2.setEmpleadoEncargado(empleado1);


        Evento evento3=new Evento(null,null,null,null,0,null);
        evento3.setId(((int) (Math.random() * 999999))+"");
        evento3.setNombreEvento("bingo");
        evento3.setDescripcion("quien gana mas");
        evento3.setFecha(LocalDateTime.now());
        evento3.setCapacidadMax(15);
        evento3.setEmpleadoEncargado(empleado2);

        sgre.getListaEventos().add(evento2);
        sgre.getListaEventos().add(evento3);
        sgre.getListaEmpleados().add(empleado2);

        System.out.println("Información del sgre creada");
        return sgre;
    }

    public static UsuarioDto getUsuarioEnSesion() {
        return SGREUtils.usuarioEnSesion;
    }

    public static void setUsuarioEnSesion(UsuarioDto usuario) {
        SGREUtils.usuarioEnSesion = usuario;
    }

    public static EmpleadoDto getEmpleadoEnSesion() {
        return SGREUtils.empleadoEnSesion;
    }
    public static void setEmpleadoEnSesion(EmpleadoDto empleado) {
        SGREUtils.empleadoEnSesion = empleado;
    }

}
