package prog2425.dam1.calculadora.model

import prog2425.dam1.calculadora.utils.Fecha

/**
 * Clase que representa una operación matemática entre dos números.
 *
 * @property id identificador para la base de datos.
 * @property numero El primer número que se va a usar en la operación.
 * @property signo El operador que se va a aplicar (puede ser "+", "-", "*", "/").
 * @property numero2 El segundo número de la operación.
 * @property resultado El resultado final después de aplicar el operador entre los dos números.
 */
data class Operacion(val id: String,
                     val numero1: Double,
                     val signo: String,
                     val numero2: Double,
                     val resultado: Double) {

    override fun toString(): String {
        return "id: $id, Primer número: $numero1, Operador: $signo, Segundo número: $numero2, Resultado: $resultado\n"
    }
}