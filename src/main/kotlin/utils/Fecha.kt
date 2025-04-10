package prog2425.dam1.calculadora.utils


import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Fecha {
    fun obtenerFechaActualFormateada(): String{
       return DateTimeFormatter.ofPattern("YYYYMMddhhmmss").format(LocalDateTime.now())

    }
}