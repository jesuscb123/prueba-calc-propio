package prog2425.dam1.calculadora.utils

interface IUtilGestorBD {

    fun crearTabla()

    fun guardarOperacion(operacion: String, resultado: Double)
}