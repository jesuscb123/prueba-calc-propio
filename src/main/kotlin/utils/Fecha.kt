package prog2425.dam1.calculadora.utils


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Clase que solo tiene un método para obtener la fecha actual formateada.
 */
class Fecha {
    companion object{
        /**
         * Obtiene la fecha actual formateada.
         *@return fecha actual formateada.
         */
        fun obtenerFechaActualFormateada(): String{
            return DateTimeFormatter.ofPattern("YYYYMMddhhmmss").format(LocalDateTime.now())
        }
    }

}