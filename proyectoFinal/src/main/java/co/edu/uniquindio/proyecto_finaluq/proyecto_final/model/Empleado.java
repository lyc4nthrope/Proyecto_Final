package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.util.ArrayList;

public class Empleado extends Admin{
    public Empleado(String id, String nombre, String correo, String contrasenia, ArrayList<Evento> eventosAsignados) {
        super(id, nombre, correo, contrasenia);
    }

    public Empleado() {

    }
}
