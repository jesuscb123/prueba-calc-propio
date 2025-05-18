package prog2425.dam1.calculadora.app

import prog2425.dam1.calculadora.Service.IErrorService
import prog2425.dam1.calculadora.Service.IOperacionService
import prog2425.dam1.calculadora.Service.IServCalc
import prog2425.dam1.calculadora.Service.OperacionService
import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.Fecha

import java.lang.Exception
import java.sql.SQLException
import kotlin.math.sign


class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc, val operacionService: IOperacionService, val errorService: IErrorService) {


  fun iniciarCalculadora(){
        var terminar = false
        do{
            try {
                consola.mostrar("CALCULADORA")

                val a = pedirNumero("Introduce el primer número:")
                if (a == null) throw IllegalArgumentException("Introduce un número válido")

                val signo = pedirSigno("Introduce el operador:")

                val b = pedirNumero("Introduce el segundo número:")
                if (b == null) throw IllegalArgumentException("Introduce un número válido")

                val resultado = obtenerResultado(a,signo, b)

                mostrarResultado(resultado)

                guardarOperacion(a, signo, b, resultado)

                if (consola.preguntarTerminar()) terminar = false else terminar = true
            }catch(e: IllegalArgumentException){
                consola.mostrarError("${e.message}")
                guardarError(e.message.toString())
            }catch (e: Exception){
                consola.mostrarError("${e.message}")
                guardarError(e.message.toString())
            }
        }while (!terminar)
        consola.mostrar("Has finalizado la calculadora")
    }




    private fun pedirNumero(msj: String): Double?{
       return consola.pedirDouble(msj)
    }

    private fun pedirSigno(msj: String): String {
       return consola.pedirSigno(msj){
            it in arrayOf("+","-","*","x","/")
        }
    }

    private fun obtenerResultado (a: Double, signo: String, b: Double): Double{
        return calculadora.calculo(a,signo,b)
    }

    private fun mostrarResultado (resultado: Double) {
        consola.mostrar(resultado)
    }


    private fun consultarOperacion(): Operacion? {
        return operacionService.consultarOperacion()
    }


    private fun guardarOperacion(numero1: Double, operador: String, numero2: Double, resultado: Double){
        operacionService.insertar(numero1,operador,numero2, resultado)
        }

    private fun guardarError(mensajeError: String){
        errorService.insertarError(mensajeError)
    }

    fun iniciarPrograma(args: Array<String>){
        try{
            when (args.size){
                0 -> {
                    consola.mostrar(consultarOperacion())
                    iniciarCalculadora()
                }
                3 -> {
                    val resultado = iniciarConTresArgumentos(args)
                    consola.mostrar(consultarOperacion())
                    iniciarCalculadora()
                }
                else -> consola.mostrarError("Debes introducir un argumento o cuatro o ninguno.")
            }
        }catch (e: SQLException){
            consola.mostrar("Error, ${e.message}")
            guardarError(e.message.toString())
        }catch (e: Exception){
            consola.mostrar("Error, ${e.message}")
            guardarError(e.message.toString())
        }

    }

    private fun realizarCalculoArgumentos(numero1: Double, signo: String, numero2: Double): Double{
        val resultado =  obtenerResultado(numero1, signo, numero2)
        return resultado
    }

    private fun iniciarConTresArgumentos(args: Array<String>): Double{
        val numero1 = args[0].toDouble()
        val signo = args[1].lowercase()
        val numero2 = args[2].toDouble()
        val resultado = realizarCalculoArgumentos(numero1, signo, numero2)
        guardarOperacion(numero1, signo, numero2, resultado)
        return resultado
        }
}



