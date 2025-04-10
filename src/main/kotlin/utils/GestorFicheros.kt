package prog2425.dam1.calculadora.utils

import java.io.File
import java.time.LocalDateTime

class GestorFicheros() : IUtilFichero {
    override fun buscarDirectorio(rutaDirectorio: String): Boolean {
        if (!File(rutaDirectorio).exists()) {
            return false
        }else{
            return true
        }
    }

    override fun comprobarFicheros(directorio: File): Boolean{
       return if ((directorio.listFiles()?.size ?: 0) > 0) true else false
    }

    override fun obtenerUltimoLog(rutaArchivo: String): File{
        val listaFicheros = File(rutaArchivo).listFiles()
        return listaFicheros.maxBy { it.lastModified() }
    }

    override fun leerLog(fichero: File): List<String>{
        return fichero.readLines()
    }

    override fun crearDirectorio(ruta: String){
        File(ruta).mkdir()
    }

    override fun crearFichero(rutaArchivo: String, nombreArchivo: String): String {
        val fichero = "$rutaArchivo/log$nombreArchivo.txt"
        File(fichero).createNewFile()
        return fichero

    }

    override fun escribirLog(rutaFichero: String, texto: String){
        File(rutaFichero).appendText("$texto\n")
    }

    }
