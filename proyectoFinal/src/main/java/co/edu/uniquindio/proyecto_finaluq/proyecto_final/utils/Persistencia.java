package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

import co.edu.uniquindio.proyecto_finaluq.proyecto_final.controller.ModelFactoryController;
import co.edu.uniquindio.proyecto_finaluq.proyecto_final.model.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Persistencia {
     static ModelFactoryController modelFactoryController;

    public Persistencia(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/log/SGRELog.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/Persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/Persistencia/archivoEmpleados.txt";
    public static final String RUTA_ARCHIVO_EVENTOS = "src/main/resources/Persistencia/archivoEventos.txt";
    public static final String RUTA_ARCHIVO_RESERVAS = "src/main/resources/Persistencia/archivoReservas.txt";
    public static final String RUTA_ARCHIVO_MODELO_SGRE_BINARIO = "src/main/resources/Persistencia/archivoSGREBinario.dat";
    public static final String RUTA_ARCHIVO_MODELO_SGRE_XML = "src/main/resources/Persistencia/archivoSGREXml.xml";
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void cargarDatosArchivos(SGRE sgre) throws FileNotFoundException, IOException {
        //cargar archivo de clientes
        ArrayList<Usuario> usuariosCargados = cargarUsuarios();
        if(usuariosCargados.size() > 0)
            sgre.getListaUsuarios().addAll(usuariosCargados);

        //cargar archivos empleados
        ArrayList<Empleado> empleadosCargados = cargarEmpleados();
        if(empleadosCargados.size() > 0)
            sgre.getListaEmpleados().addAll(empleadosCargados);

        //cargar archivo eventos
        ArrayList<Evento> eventosCargados = cargarEventos(sgre);
        if(eventosCargados.size() > 0)
            sgre.getListaEventos().addAll(eventosCargados);


        //cargar archivo reservas
        ArrayList<Reserva> reservasCargadas = cargarReservas(sgre);
        if(reservasCargadas.size() > 0)
            sgre.getListaReservas().addAll(reservasCargadas);


    }


    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido += usuario.getId()+","+
                    usuario.getNombre()+","+
                    usuario.getCorreo()+","+
                    usuario.getContrasenia() +"\n";
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
                    ","+empleado.getContrasenia()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
    }

    public static void guardarReservas(ArrayList<Reserva> listaReservas) throws IOException {
        String contenido = "";
        for(Reserva reserva:listaReservas)
        {
            contenido+= reserva.getId()+
                    ","+reserva.getUsuario().getId()+
                    ","+reserva.getEvento().getId()+
                    ","+reserva.getFechaSolicitud()+
                    ","+reserva.getEstado()+
                    ","+reserva.getEspaciosSolicitados()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_RESERVAS, contenido, false);
    }

    public static void guardarEventos(ArrayList<Evento> listaEventos) throws IOException {
        String contenido = "";
        for(Evento evento:listaEventos)
        {
            contenido+= evento.getId()+
                    ","+evento.getNombreEvento()+
                    ","+evento.getDescripcion()+
                    ","+evento.getFecha()+
                    ","+evento.getCapacidadMax()+
                    ","+evento.getEmpleadoEncargado().getId()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EVENTOS, contenido, false);
    }




    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = abrirArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setId(linea.split(",")[0]);
            usuario.setNombre(linea.split(",")[1]);
            usuario.setCorreo(linea.split(",")[2]);
            usuario.setContrasenia(linea.split(",")[3]);
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
        ArrayList<Empleado> empleados =new ArrayList<Empleado>();
        ArrayList<String> contenido = abrirArchivo(RUTA_ARCHIVO_EMPLEADOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Empleado empleado = new Empleado();
            empleado.setId(linea.split(",")[0]);
            empleado.setNombre(linea.split(",")[1]);
            empleado.setCorreo(linea.split(",")[2]);
            empleado.setContrasenia(linea.split(",")[3]);
            empleados.add(empleado);
        }
        return empleados;
    }

    public static ArrayList<Reserva> cargarReservas(SGRE sgre) throws FileNotFoundException, IOException
    {
        ArrayList<Reserva> reservas =new ArrayList<>();
        ArrayList<String> contenido = abrirArchivo(RUTA_ARCHIVO_RESERVAS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Reserva reserva = new Reserva();
            reserva.setId(linea.split(",")[0]);

            String idUsuario = linea.split(",")[1];
            Usuario usuarioAux = sgre.obtenerUsuarioId(idUsuario,null,0,false);
            reserva.setUsuario(usuarioAux);

            String idEvento = linea.split(",")[2];
            Evento eventoAux = sgre.obtenerEventoId(idEvento,null,0,false);
            reserva.setEvento(eventoAux);

            reserva.setFechaSolicitud(LocalDateTime.parse(linea.split(",")[3]));
            reserva.setEstado(linea.split(",")[4]);
            reserva.setEspaciosSolicitados(Integer.parseInt(linea.split(",")[5]));
            reservas.add(reserva);
        }
        return reservas;
    }

    public static ArrayList<Evento> cargarEventos(SGRE sgre) throws FileNotFoundException, IOException
    {
        ArrayList<Evento> eventos =new ArrayList<>();
        ArrayList<String> contenido = abrirArchivo(RUTA_ARCHIVO_EVENTOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Evento evento = new Evento();
            evento.setId(linea.split(",")[0]);
            evento.setNombreEvento(linea.split(",")[1]);
            evento.setDescripcion(linea.split(",")[2]);
            evento.setFecha(LocalDateTime.parse(linea.split(",")[3]));
            evento.setCapacidadMax(Integer.parseInt(linea.split(",")[4]));

            String idEmpleado = linea.split(",")[5];
            Empleado empleadoAux = sgre.obtenerEmpleadoId(idEmpleado,null,0,false);
            evento.setEmpleadoEncargado(empleadoAux);
            eventos.add(evento);
        }
        return eventos;
    }

    public static ArrayList<String> abrirArchivo(String ruta){
        try{
            return ArchivoUtil.leerArchivo(ruta);
        }catch (IOException e){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, false));
                return new ArrayList<>();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }



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
        return sgre;

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
