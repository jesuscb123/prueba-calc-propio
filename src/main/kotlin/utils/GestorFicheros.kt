package prog2425.dam1.calculadora.utils


import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.data.IRepoLog
import prog2425.dam1.calculadora.model.Operacion
import java.io.File


class GestorFicheros(val repoLog: IRepoLog) : IUtilFichero {
    override fun buscarDirectorio(rutaDirectorio: String): Boolean {
        if (!File(rutaDirectorio).exists()) {
            return false
        }else{
            return true
        }
    }

    override fun comprobarFicheros(rutaDirectorio: String): Boolean{
        return if ((File(rutaDirectorio).listFiles()?.size ?: 0) > 0) true else false
    }

    override fun crearDirectorio(ruta: String){
        File(ruta).mkdir()
        require(buscarDirectorio(ruta)) {"El directorio no ha sido creado correctamente."}
    }

    override fun crearFichero(rutaArchivo: String, nombreArchivo: String): String {
        val fichero = "$rutaArchivo/log$nombreArchivo.txt"
       val ficheroCreado = File(fichero).createNewFile()
        require(ficheroCreado) { "Fichero no creado correctamente." }
        return fichero
    }

   override fun obtenerFicheros(rutaFichero: String): List<File>{
       if (comprobarFicheros(rutaFichero)){
           return File(rutaFichero).listFiles()?.toList() ?: emptyList()
       }else{
          return emptyList()
       }

    }

    }
