package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.IUtilFichero
import java.io.File

class RepoLog(val gestorFicheros: IUtilFichero) : IRepoLog {

    override fun escribirLog(rutaFichero: String, texto: Operacion) {
        val fichero = gestorFicheros.comprobarFicheros(rutaFichero)

    }

    override fun leerLog(fichero: File): List<String>{

    }

    override fun obtenerUltimoLog(rutaArchivo: String): File?{
        val listaFicheros = gestorFicheros.obtenerFicheros(rutaArchivo).filterNotNull()
        return listaFicheros.maxByOrNull { it.lastModified() }
}

}