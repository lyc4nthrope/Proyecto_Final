package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Admin {
    private List<Reserva> reservasAsignados;

    public Usuario(String id, String nombre, String correo, String contrasenia, ArrayList<Reserva> reservasAsignados) {
        super(id, nombre, correo, contrasenia);
        setReservasAsignados(reservasAsignados);
    }

    public int cantidadReservas (){
        return getReservasAsignados().size();
    }
    public ArrayList<Reserva> getReservasAsignados() {
        return new ArrayList<>(reservasAsignados);
    }

    public void setReservasAsignados(ArrayList<Reserva> reservasAsignados) {
        this.reservasAsignados = (reservasAsignados == null) ? new ArrayList<>() :new ArrayList<>(reservasAsignados);
    }
}
