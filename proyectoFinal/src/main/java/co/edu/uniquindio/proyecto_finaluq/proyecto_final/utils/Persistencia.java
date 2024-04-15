package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.SGRE;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {


    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/SGRELog.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/persistencia/archivoEmpleados.txt";
    public static final String RUTA_ARCHIVO_EVENTOS = "src/main/resources/persistencia/archivoEventos.txt";
    public static final String RUTA_ARCHIVO_RESERVAS = "src/main/resources/persistencia/archivoReservas.txt";
    public static final String RUTA_ARCHIVO_MODELO_SGRE_BINARIO = "src/main/resources/persistencia/archivoSGREBinario.txt";
    public static final String RUTA_ARCHIVO_MODELO_SGRE_XML = "src/main/resources/persistencia/archivoSGREXml.txt";


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    public static void guardarUsuario(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido += usuario.getId()+","+usuario.getNombre()+","+usuario.getCorreo()+","+usuario.getContrasenia()
                    +","+usuario.getReservasAsignados() +"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }

    public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
        String contenido = "";
        for(Empleado empleado:listaEmpleados)
        {
            contenido+= empleado.getId()+
                    ","+empleado.getNombre()+
                    ","+empleado.getCorreo()+
                    ","+empleado.getContrasenia()+
                    ","+empleado.getEventosAsignados() +"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
    }


//    public static ArrayList<Usuario> cargarUsuario() throws FileNotFoundException, IOException
//    {
//        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
//        String linea="";
//        for (int i = 0; i < contenido.size(); i++)
//        {
//            linea = contenido.get(i);
//            Usuario usuario = new Usuario();
//            usuario.setId(linea.split(",")[0]);
//            usuario.setNombre(linea.split(",")[1]);
//            usuario.setCorreo(linea.split(",")[2]);
//            usuario.setContrasenia(linea.split(",")[3]);
//            usuario.setReservasAsignados(linea.split(",")[4]);
//            usuarios.add(usuario);
//        }
//        return usuarios;
//    }

//    public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
//        ArrayList<Empleado> empleados =new ArrayList<Empleado>();
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
//        String linea="";
//        for (int i = 0; i < contenido.size(); i++)
//        {
//            linea = contenido.get(i);
//            Empleado empleado = new Empleado();
//            empleado.setId(linea.split(",")[0]);
//            empleado.setNombre(linea.split(",")[1]);
//            empleado.setCorreo(linea.split(",")[2]);
//            empleado.setContrasenia(linea.split(",")[3]);
//            empleado.setEventosAsignados(linea.split(",")[4]);
//            empleados.add(empleado);
//        }
//        return empleados;
//    }

    public static SGRE cargarRecursoSGREBinario(){
        SGRE sgre = null;

        try {
            sgre = (SGRE) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_SGRE_BINARIO);
        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sgre;
    }

    public static void guardarRecursoSGREBinario(SGRE sgre) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_SGRE_BINARIO, sgre);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static SGRE cargarRecursoSGREXML() {
        SGRE sgre = null;

        try {
            sgre = (SGRE) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SGRE_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return banco;

    }

    public static void guardarRecursoSGREXML(SGRE sgre) {
        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SGRE_XML, sgre);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
