package prog2425.dam1.calculadora.utils

import java.io.File
import java.time.LocalDateTime

class GestorFicheros(private val gestorFecha: IUtilFecha) {
    fun buscarDirectorio(rutaDirectorio: String): Boolean {
        if (!File(rutaDirectorio).exists()) {
            return false
        }else{
            return true
        }
    }

    fun comprobarFicheros(directorio: File): Boolean{
       return if ((directorio.listFiles()?.size ?: 0) > 0) true else false
    }

    fun obtenerUltimoLog(rutaArchivo: String): File{
        val listaFicheros = File(rutaArchivo).listFiles()
        return listaFicheros.maxBy { it.lastModified() }
    }

    fun leerLog(fichero: File): List<String>{
        return fichero.readLines()
    }

    fun crearDirectorio(ruta: String){
        File(ruta).mkdir()
    }

    fun crearFichero(rutaArchivo: String, nombreArchivo: String) {
        File("$rutaArchivo/$nombreArchivo.txt")
    }

    fun escribirLog(fichero: File, texto: String){
        fichero.writeText(texto)
    }

    }
