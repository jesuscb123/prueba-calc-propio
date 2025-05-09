package prog2425.dam1.calculadora.model

import prog2425.dam1.calculadora.utils.Fecha

data class Operacion(val id: String,
                     val numero1: Double,
                     val signo: String,
                     val numero2: Double,
                     val resultado: Double) {

    override fun toString(): String {
        return "id: $id, Primer número: $numero1, Operador: $signo, Segundo número: $numero2, Resultado: $resultado\n"
    }
}