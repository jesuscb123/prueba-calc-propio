package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.IUtilFichero
import java.io.File

class RepoLog() : IRepoLog {

    override fun escribirLog(rutaFichero: String, texto: Operacion) {
        File(rutaFichero).appendText("$texto\n")
    }

    override fun leerLog(fichero: File): List<String>{
        return fichero.readLines()
    }

    override fun obtenerUltimoLog(rutaArchivo: String): File{
        val listaFicheros = File(rutaArchivo).listFiles()
        return listaFicheros.maxBy { it.lastModified() }
    }
}