package prog2425.dam1.calculadora.model

/**
 * Clase que representa una operación matemática entre dos números.
 *
 * @property numero El primer número que se va a usar en la operación.
 * @property signo El operador que se va a aplicar (puede ser "+", "-", "*", "/").
 * @property numero2 El segundo número de la operación.
 * @property resultado El resultado final después de aplicar el operador entre los dos números.
 */
data class Operacion(val numero: Double, val signo: String, val numero2: Double, val resultado: Double) {

    /**
     * Devuelve la operación como un string para mostrar por pantalla.
     * Por ejemplo: "Primer número: 5.0, Operador: +, Segundo número: 3.0, Resultado: 8.0"
     */
    override fun toString(): String {
        return "Primer número: $numero, Operador: $signo, Segundo número: $numero2, Resultado: $resultado\n"
    }
}