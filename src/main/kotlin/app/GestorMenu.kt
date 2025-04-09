package prog2425.dam1.calculadora.app

import prog2425.dam1.calculadora.Service.IServCalc
import prog2425.dam1.calculadora.UI.IEntradaSalida
import kotlin.math.sign

class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc) {

  fun iniciarCalculadora(){
        var terminar = false
        do{
            try {
                consola.mostrar("CALCULADORA")
                val a = pedirNumero("Introduce el primer número:")
                val signo = pedirSigno("Introduce el operador:")
                val b = pedirNumero("Introduce el segundo número:")
                mostrarResultado(obtenerResultado(a, b, signo))
                if (consola.preguntarTerminar()) terminar = false else terminar = true
            }catch(e: IllegalArgumentException){
                consola.mostrarError("$e")
            }
        }while (!terminar)
        consola.mostrar("Has finalizado la calculadora")
    }

    private fun pedirNumero(msj: String): Double{
        consola.limpiarPantalla()
       return consola.pedirDouble(msj)
    }

    private fun pedirSigno(msj: String): String {
        consola.limpiarPantalla()
       return consola.pedirSigno(msj){
            it in arrayOf("+","-","*","/")
        }
    }

    private fun obtenerResultado (a: Double, b: Double, signo: String): Double{
        consola.limpiarPantalla()
        return calculadora.calculo(a, b, signo)
    }

    private fun mostrarResultado (resultado: Double) {
        consola.limpiarPantalla()
        consola.mostrar(resultado)
    }

}