package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.util.ArrayList;

public class Usuario extends Admin {
    private ArrayList<Reserva> reservasAsignados;

    public Usuario(String id, String nombre, String correo, String contrasenia, ArrayList<Reserva> reservasAsignados) {
        super(id, nombre, correo, contrasenia);
        this.reservasAsignados = reservasAsignados;
    }

    public int cantidadReservas (){
        return reservasAsignados.size();
    }
    public ArrayList<Reserva> getReservasAsignados() {
        return reservasAsignados;
    }

    public void setReservasAsignados(ArrayList<Reserva> reservasAsignados) {
        this.reservasAsignados = reservasAsignados;
    }
}
