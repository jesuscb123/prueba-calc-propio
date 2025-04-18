package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import java.io.File

interface IRepoLog {

  fun escribirLog(fichero: String, texto: String)


   fun leerLog(fichero: String): List<String>


    fun obtenerUltimoLog(rutaArchivo: String): File?


}