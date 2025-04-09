package prog2425.dam1.calculadora.utils

import java.io.File

class GestorFicheros() {
    fun buscarDirectorio(rutaDirectorio: String){
        val directorio = File(rutaDirectorio)
        if (!directorio.exists()){
            directorio.mkdir()
        }else{

        }

        fun comprobarFicheros(directorio: File): Boolean{
           return if (directorio.listFiles().size > 1) true else false
        }

        fun obtenerUltimoLog(directorio: File){
            val listaFicheros = directorio.listFiles()


        }



    }
}