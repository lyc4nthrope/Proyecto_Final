package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento {

    private String id;
    private String nombreEvento;
    private String descripcion;
    private LocalDateTime fecha;
    private int capacidadMax;
    private Empleado empleadoEncargado;


    public Evento(String id, String nombreEvento, String descripcion, LocalDateTime fecha, int capacidadMax, Empleado empleadoEncargado) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.capacidadMax = capacidadMax;
        this.empleadoEncargado = empleadoEncargado;
    }

    public Evento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Empleado getEmpleadoEncargado() {
        return empleadoEncargado;
    }

    public void setEmpleadoEncargado(Empleado empleadoEncargado) {
        this.empleadoEncargado = empleadoEncargado;
    }

}
