package prog2425.dam1.calculadora.app

import prog2425.dam1.calculadora.Service.IServCalc
import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.model.Operacion

import prog2425.dam1.calculadora.utils.IUtilFichero
import kotlin.math.sign


class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc, val gestorFicheros: IUtilFichero) {

  fun iniciarCalculadora(rutaFichero: String){
        var terminar = false
        do{
            try {
                consola.limpiarPantalla()
                consola.mostrar("CALCULADORA")
                val a = pedirNumero("Introduce el primer número:")
                val signo = pedirSigno("Introduce el operador:")
                val b = pedirNumero("Introduce el segundo número:")
                val resultado = obtenerResultado(a,signo, b)
                mostrarResultado(resultado)
                val operacionTexto = obtenerOperacion(a, signo, b, resultado)
                gestorFicheros.escribirLog(rutaFichero,operacionTexto)
                if (consola.preguntarTerminar()) terminar = false else terminar = true
            }catch(e: IllegalArgumentException){
                consola.mostrarError("$e")
            }
        }while (!terminar)
        consola.mostrar("Has finalizado la calculadora")
    }

    fun iniciarCalculadora(rutaFichero: String, a: String, signo: String, b: String){
        try {
            consola.limpiarPantalla()
            consola.mostrar("CALCULADORA")
            val a = a.toDouble()
            val b = b.toDouble()
            val resultado = obtenerResultado(a, signo, b)
            mostrarResultado(resultado)
            val OperacionTexto = obtenerOperacion(a, signo, b, resultado)
            gestorFicheros.escribirLog(rutaFichero, OperacionTexto)
        }catch(e: NumberFormatException){
            consola.mostrarError("Debes introducir un número.")
        }catch (e: Exception){
            consola.mostrarError("$e")
        }
    }

    private fun pedirNumero(msj: String): Double{
       return consola.pedirDouble(msj)
    }

    private fun pedirSigno(msj: String): String {
       return consola.pedirSigno(msj){
            it in arrayOf("+","-","*","/")
        }
    }

    private fun obtenerResultado (a: Double, signo: String, b: Double): Double{
        return calculadora.calculo(a,signo,b)
    }

    private fun mostrarResultado (resultado: Double) {
        consola.mostrar(resultado)
    }

    private fun obtenerOperacion(a: Double, signo: String, b: Double, resultado: Double): Operacion{
        return Operacion(a, signo, b, resultado)
    }
}