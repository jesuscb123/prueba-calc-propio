package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.UI.IEntradaSalida
import java.util.InputMismatchException

/**
 * Clase encargada de realizar cálculos.
 */
class Calculadora() : IServCalc {

    /**
     * Se encarga de recibir dos números y un signo, en función del signo realiza la operación correspondiente.
     *
     * @param a primer número.
     * @param signo operador.
     * @param b segundo número.
     *
     * @return resultado de la operación.
     */
    override fun calculo(a: Double, signo: String, b: Double): Double {
        when (signo){
            "+" -> return a + b
            "-" -> return a - b
            "*", "x" -> return a * b
            else -> return a / b
        }
    }




}