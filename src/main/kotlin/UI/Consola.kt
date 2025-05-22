package prog2425.dam1.calculadora.UI

import java.lang.NumberFormatException
import java.util.InputMismatchException
import java.util.Scanner

/**
 * Clase que se encarga de toda la interacción con el usuario por consola.
 * Implementa la interfaz IentradaSalida
 * Básicamente, aquí se lee lo que el usuario mete y se muestra lo que le queremos enseñar.
 */
class Consola() : IEntradaSalida {
    private val escaner = Scanner(System.`in`)

    /**
     * Muestra un mensaje por pantalla.
     * Si es String le quita los espacios sobrantes. Si es Double, lo muestra tal cual.
     *
     * @param msj El mensaje que se quiere mostrar.
     */
    override fun <T> mostrar(msj: T) {
        if (msj is String) {
            println(msj.trim())
        }else if(msj is Double){
            println(msj)
        }
    }

    /**
     * Muestra un mensaje de error con el prefijo "ERROR -".
     *
     * @param msj El mensaje de error.
     */
    override fun mostrarError(msj: String) {
        println("ERROR - ${msj.trim()}")
    }

    /**
     * Pide un signo (por ejemplo, un operador) y comprueba que sea válido.
     *
     * @param msj Mensaje que se muestra al usuario.
     * @param debeCumplir Función lambda para comprobar si el signo es válido.
     * @return El signo introducido.
     * @throws IllegalArgumentException Si el signo no cumple la condición.
     */
    override fun pedirSigno(msj: String, debeCumplir: (String) -> Boolean): String {
        val signo = pedirInfo(msj).trim()
        require(debeCumplir(signo)) {"Debes introducir un operador."}
        return signo
    }

    /**
     * Pide un número decimal (Double) y repite hasta que el usuario meta uno válido.
     *
     * @param msj Mensaje que se le muestra al usuario.
     * @return El número introducido.
     */
    override fun pedirDouble(msj: String): Double {
        var numeroCorrecto = false
        var numero = 0.0
        do{
            try{
                mostrar(msj)
                numero = escaner.nextDouble()
                numeroCorrecto = true
            }catch (e: NumberFormatException){
                mostrarError("Debes introducir un número flotante")
            }catch (e: InputMismatchException){
                mostrarError("$e")
                escaner.nextLine()
            }
        }while (!numeroCorrecto)
        return numero
    }

    /**
     * Pregunta al usuario si quiere continuar o no.
     * Acepta "s", "n", "si", "no".
     *
     * @return true si el usuario quiere seguir, false si no.
     */
    override fun preguntarTerminar(): Boolean {
        var opcionCorrecta = false
        val siONo = arrayOf("s","n", "si", "no")
        do {
            try {
                val respuesta = pedirInfo("¿Desea hacer un nuevo cálculo? (s,si,n,no)").lowercase()
                require(respuesta in siONo) {"Debes introducir sí o no."}
                when (respuesta) {
                    "si","s" -> {
                        opcionCorrecta = true
                        return true
                    }
                    else ->{
                        opcionCorrecta = true
                        return false
                    }
                }
            }catch (e: IllegalArgumentException){
                mostrarError("$e")
            }
        }while (!opcionCorrecta)
        return false
    }

    override fun pedirInfo(msj: String): String {
        mostrar(msj)
        return escaner.next()
    }

    override fun limpiarPantalla(numSaltos:Int){
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J")
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }
   override fun <T> mostrarLista(lista: List<T>){
        for (elemento in lista){
            mostrar(elemento)
        }
    }

    override fun pausar() {
        pedirInfo("Pulsa enter para continuar...")
    }
}