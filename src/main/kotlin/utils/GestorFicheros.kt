package prog2425.dam1.calculadora.utils


import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.data.IRepoLog
import prog2425.dam1.calculadora.model.Operacion
import java.io.File

/**
 * Clase que se encarga de gestionar los ficheros. Busca directorios, comprueba si existen los directorios, crea directorios, crea ficheros y los obtiene.
 */

class GestorFicheros() : IUtilFichero {

    /**
     * Busca un directorio en la ruta especificada y dice si lo encuentra.
     *
     * @param rutaDirectorio ruta del directorio.
     *
     * @return true en caso de ser encontrado o false en caso contrario.
     */
    override fun buscarDirectorio(rutaDirectorio: String): Boolean {
        if (!File(rutaDirectorio).exists()) {
            return false
        }else{
            return true
        }
    }

    /**
     * Comprueba si existen ficheros en la ruta especificada.
     *
     * @param rutaDirectorio ruta dónde buscar los fciheros.
     *
     * @return devuelve true si existen ficheros o false si no hay ficheros.
     */
    override fun comprobarFicheros(rutaDirectorio: String): Boolean{
        return if ((File(rutaDirectorio).listFiles()?.size ?: 0) > 0) true else false
    }

    /**
     * Crea un directorio en la ruta especificada.
     * @param ruta ruta dónde crear el directorio
     *
     * @throws IllegalArgumentException si no se ha podido crear el directorio.
     */
    override fun crearDirectorio(ruta: String){
        File(ruta).mkdir()
        require(buscarDirectorio(ruta)) {"El directorio no ha sido creado correctamente."}
    }

    /**
     * Crea un fichero en la ruta especificada y con el nombre del archivo especificado.
     *
     * @param rutaArchivo ruta del archivo.
     * @param nombreArchivo nombre del archivo.
     *
     * @return retorna el fichero creado.
     *
     * @throws IllegalArgumentException si el fichero no fue creado correctamente.
     */
    override fun crearFichero(rutaArchivo: String, nombreArchivo: String): String {
        val fichero = "$rutaArchivo/log$nombreArchivo.txt"
       val ficheroCreado = File(fichero).createNewFile()
        require(ficheroCreado) { "Fichero no creado correctamente." }
        return fichero
    }

    /**
     * Obtiene una lista de ficheros.
     *
     * @param rutaFichero ruta dónde se encuentran los ficheros.
     *
     * @return lista de ficheros o lista vacía si no hay ficheros.
     */
   override fun obtenerFicheros(rutaFichero: String): List<File>{
       if (comprobarFicheros(rutaFichero)){
           return File(rutaFichero).listFiles()?.toList() ?: emptyList()
       }else{
          return emptyList()
       }
    }

    /**
     * Obtiene un fichero en una ruta específica.
     *
     * @param rutaFichero ruta del fichero.
     *
     * @return retorna un fichero o nulo en caso de no haber podido obtenerlo.
     */
   override fun obtenerFichero(rutaFichero: String): File?{
        if (File(rutaFichero).exists()){
            return File(rutaFichero)
        }else{
            return null
        }
    }

    }
