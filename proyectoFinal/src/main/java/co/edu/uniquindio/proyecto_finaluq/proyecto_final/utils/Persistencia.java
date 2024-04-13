package co.edu.uniquindio.proyecto_finaluq.proyecto_final.utils;

public class Persistencia {


    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/SGRELog.txt";

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }
}

