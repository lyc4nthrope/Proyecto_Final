package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Admin  implements Serializable {
    private static final long serialVersionUID=1L;

    public Usuario(String id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
    }

    public Usuario() {
        super();
    }
}
