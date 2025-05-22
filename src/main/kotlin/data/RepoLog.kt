package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.IUtilFichero
import java.io.File

/**
 * Clase que se encarga de gestionar los logs (archivos donde se guarda texto tipo historial o registros).
 *
 * @property gestorFicheros Interfaz que usamos para trabajar con los ficheros (obtener el último log, leer y escribir logs.).
 */
class RepoLog(val gestorFicheros: IUtilFichero) : IRepoLog {

    /**
     * Escribe una línea de texto en el log indicado por parámetro..
     * Si el fichero existe, le añade el texto al final (sin borrar lo que ya había).
     *
     * @param rutaFichero Ruta donde se encuentra el log.
     * @param texto Texto que se quiere guardar en el log.
     */
    override fun escribirLog(rutaFichero: String, texto: String) {
        val fichero = gestorFicheros.obtenerFichero(rutaFichero)
        if (fichero != null){
            fichero.appendText(texto)
        }
    }

    /**
     * Lee el log más reciente en la ruta indicada y devuelve su contenido línea a línea.
     * Si no encuentra ningún fichero, devuelve una lista vacía.
     *
     * @param rutaFichero Ruta donde se deben buscar los logs.
     * @return Lista con cada línea del log más reciente, o vacía si no hay nada.
     */
    override fun leerLog(rutaFichero: String): List<String>{
       val fichero = obtenerUltimoLog(rutaFichero)
        if (fichero != null){
           return fichero.readLines()
        }else{
            return emptyList()
        }
    }

    /**
     * Busca el fichero más reciente dentro de una ruta, basándose en la última fecha de modificación.
     *
     * @param rutaArchivo Ruta donde están los ficheros.
     * @return El fichero más nuevo o null si no hay ninguno válido.
     */
    override fun obtenerUltimoLog(rutaArchivo: String): File?{
        val listaFicheros = gestorFicheros.obtenerFicheros(rutaArchivo).filterNotNull()
        return listaFicheros.maxByOrNull { it.lastModified() }
}

}