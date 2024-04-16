package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Admin {

    public Usuario(String id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
    }

    public Usuario() {
        super();
    }
}
