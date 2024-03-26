package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.util.ArrayList;

public class Empleado extends Admin{
    private ArrayList<Evento> eventosAsignados;

    public Empleado(String id, String nombre, String correo, String contrasenia, ArrayList<Evento> eventosAsignados) {
        super(id, nombre, correo, contrasenia);
        this.eventosAsignados = eventosAsignados;
    }

    public ArrayList<Evento> getEventosAsignados() {
        return eventosAsignados;
    }

    public void setEventosAsignados(ArrayList<Evento> eventosAsignados) {
        this.eventosAsignados = eventosAsignados;
    }
}
