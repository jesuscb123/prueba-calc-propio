package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.IUtilFichero
import java.io.File

class RepoLog(val gestorFicheros: IUtilFichero) : IRepoLog {

    override fun escribirLog(rutaFichero: String, texto: String) {
        val fichero = obtenerUltimoLog(rutaFichero)
        if (fichero != null){
            fichero.appendText(texto)
        }
    }

    override fun leerLog(rutaFichero: String): List<String>{
       val fichero = obtenerUltimoLog(rutaFichero)
        if (fichero != null){
           return fichero.readLines()
        }else{
            return emptyList()
        }
    }

    override fun obtenerUltimoLog(rutaArchivo: String): File?{
        val listaFicheros = gestorFicheros.obtenerFicheros(rutaArchivo).filterNotNull()
        return listaFicheros.maxByOrNull { it.lastModified() }
}

}