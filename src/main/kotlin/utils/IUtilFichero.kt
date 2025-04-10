package prog2425.dam1.calculadora.utils

import java.io.File

interface IUtilFichero {
    fun buscarDirectorio(rutaDirectorio: String): Boolean


    fun comprobarFicheros(directorio: File): Boolean


        fun obtenerUltimoLog(rutaArchivo: String): File


        fun leerLog(fichero: File): List<String>


        fun crearDirectorio(ruta: String)


        fun crearFichero(rutaArchivo: String, nombreArchivo: String): String


        fun escribirLog(fichero: String, texto: String)

    }