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

/**
 * Clase encargada de gestionar el flujo del programa de calculadora,
 * incluyendo la entrada/salida por consola, cálculos, gestión de errores y persistencia de operaciones.
 *
 * @property consola Interfaz de entrada/salida para comunicación con el usuario.
 * @property calculadora encargada de realizar los cálculos..
 * @property operacionService Servicio de operaciones para guardar y consultar operaciones en la base de datos.
 * @property errorService Servicio para registrar errores ocurridos durante la ejecución.
 */
class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc, val operacionService: IOperacionService, val errorService: IErrorService) {


    /**
     * Inicia la calculadora por consola.
     * El usuario introduce números y operador, se realiza la operación,
     * se muestra el resultado y se guarda en la base de datos.
     */
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
            }catch (e: SQLException){
                consola.mostrar("${e.message}")
                guardarError(e.message.toString())
            }catch (e: Exception){
                consola.mostrarError("${e.message}")
                guardarError(e.message.toString())
            }
        }while (!terminar)
        consola.mostrar("Has finalizado la calculadora")
    }

    /**
     * Solicita al usuario un número.
     * @param msj Mensaje que se muestra al pedir el número.
     * @return Número introducido o `null` si la entrada es inválida.
     */
    private fun pedirNumero(msj: String): Double?{
       return consola.pedirDouble(msj)
    }

    /**
     * Solicita al usuario un operador válido.
     * @param msj Mensaje a mostrar.
     * @return Operador introducido (+, -, *, x, /).
     */
    private fun pedirSigno(msj: String): String {
       return consola.pedirSigno(msj){
            it in arrayOf("+","-","*","x","/")
        }
    }

    /**
     * Realiza el cálculo matemático a partir de dos operandos y un operador.
     * @param a Primer número.
     * @param signo Operador.
     * @param b Segundo número.
     * @return Resultado del cálculo.
     */
    private fun obtenerResultado (a: Double, signo: String, b: Double): Double{
        return calculadora.calculo(a,signo,b)
    }

    /**
     * Muestra el resultado de la operación al usuario.
     * @param resultado Resultado a mostrar.
     */
    private fun mostrarResultado (resultado: Double) {
        consola.mostrar(resultado)
    }

    /**
     * Consulta la última operación registrada.
     * @return La operación o `null` si no hay ninguna.
     */
    private fun consultarOperacion(): Operacion? {
        return operacionService.consultarOperacion()
    }

    /**
     * Guarda una operación en la base de datos.
     * @param numero1 Primer número.
     * @param operador Operador.
     * @param numero2 Segundo número.
     * @param resultado Resultado de la operación.
     */
    private fun guardarOperacion(numero1: Double, operador: String, numero2: Double, resultado: Double){
        operacionService.insertar(numero1,operador,numero2, resultado)
        }

    /**
     * Registra un mensaje de error en la base de datos.
     * @param mensajeError Mensaje del error.
     */
    private fun guardarError(mensajeError: String){
        errorService.insertarError(mensajeError)
    }

    /**
     * Inicia el programa en diferentes modos según los argumentos recibidos.
     * - Sin argumentos: muestra última operación y ejecuta calculadora.
     * - Con tres argumentos: realiza una operación directa e inicia la calculadora.
     * - Otro caso: muestra error.
     *
     * @param args Argumentos recibidos desde la línea de comandos.
     */
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

    /**
     * Ejecuta el cálculo entre dos números con un operador.
     * @param numero1 Primer número.
     * @param signo Operador.
     * @param numero2 Segundo número.
     * @return Resultado del cálculo.
     */
    private fun realizarCalculoArgumentos(numero1: Double, signo: String, numero2: Double): Double{
        val resultado =  obtenerResultado(numero1, signo, numero2)
        return resultado
    }

    /**
     * Realiza el cálculo a partir de tres argumentos de entrada.
     * @param args Array con número1, operador y número2.
     * @return Resultado del cálculo.
     */
    private fun iniciarConTresArgumentos(args: Array<String>): Double{
        val numero1 = args[0].toDouble()
        val signo = args[1].lowercase()
        val numero2 = args[2].toDouble()
        val resultado = realizarCalculoArgumentos(numero1, signo, numero2)
        guardarOperacion(numero1, signo, numero2, resultado)
        return resultado
        }
}



