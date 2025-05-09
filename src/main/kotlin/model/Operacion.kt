package prog2425.dam1.calculadora.model

import prog2425.dam1.calculadora.utils.Fecha

data class Operacion(val numero: Double, val signo: String, val numero2: Double, val resultado: Double) {
    val id = Fecha.obtenerFechaActualFormateada()

    override fun toString(): String {
        return "Primer número: $numero, Operador: $signo, Segundo número: $numero2, Resultado: $resultado\n"
    }
}