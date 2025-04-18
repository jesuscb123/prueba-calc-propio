package prog2425.dam1.calculadora.utils

import prog2425.dam1.calculadora.model.Operacion
import java.io.File

interface IUtilFichero {
    fun buscarDirectorio(rutaDirectorio: String): Boolean


    fun comprobarFicheros(directorio: String): Boolean


    fun crearDirectorio(ruta: String)


    fun crearFichero(rutaArchivo: String, nombreArchivo: String): String

    fun obtenerFicheros(rutaFichero: String): List<File?>

    fun obtenerFichero(rutaArchivo: String): File?
    }