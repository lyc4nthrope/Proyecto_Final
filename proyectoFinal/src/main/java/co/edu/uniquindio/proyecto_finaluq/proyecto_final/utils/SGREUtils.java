package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.SGRE;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Usuario;

public class SGREUtils {

    public static SGRE inicializarDatos(){
        SGRE sgre = new SGRE();

        Usuario usuario = new Usuario();
        usuario.setId("12341");
        usuario.setNombre("juan");
        usuario.setCorreo("@juan");
        usuario.setContrasenia("123wweaa");
        usuario.setReservasAsignados();
        sgre.getListaUsuarios().add(usuario);

        System.out.println("Información del banco creada");
        return sgre;
    }
}
