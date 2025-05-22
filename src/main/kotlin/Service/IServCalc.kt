package prog2425.dam1.calculadora.Service

/**
 * Interfaz inyectada a calculadora para realizar los cálculos.
 */
interface IServCalc {
    fun calculo (a: Double, signo: String, b: Double): Double
}