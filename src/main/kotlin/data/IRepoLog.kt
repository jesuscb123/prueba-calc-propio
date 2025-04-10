package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import java.io.File

interface IRepoLog {

  fun escribirLog(rutaFichero: String, texto: Operacion)


   fun leerLog(fichero: File): List<String>


    fun obtenerUltimoLog(rutaArchivo: String): File


}