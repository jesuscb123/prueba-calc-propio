package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.UI.IEntradaSalida
import java.util.InputMismatchException

class Calculadora() : IServCalc {

    override fun calculo(a: Double, signo: String, b: Double): Double {
        when (signo){
            "+" -> return a + b
            "-" -> return a - b
            "*", "x" -> return a * b
            else -> return a / b
        }
    }




}