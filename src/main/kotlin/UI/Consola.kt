package prog2425.dam1.calculadora.UI

import java.lang.NumberFormatException
import java.util.InputMismatchException
import java.util.Scanner

class Consola() : IEntradaSalida {
    private val escaner = Scanner(System.`in`)
    override fun <T> mostrar(msj: T) {
        println(msj)
    }

    override fun mostrarError(msj: String) {
        println("ERROR - ${msj.trim()}")
    }

    override fun pedirSigno(msj: String, debeCumplir: (String) -> Boolean): String {
        val signo = pedirInfo(msj).trim()
        require(debeCumplir(signo)) {"Debes introducir un operador."}
        return signo
    }

    override fun pedirDouble(msj: String): Double? {
        var numero: Double? = null
            try{
                mostrar(msj)
                numero = escaner.nextDouble()
            }catch (e: NumberFormatException){
                throw NumberFormatException("Introduce un número váido")
            }catch (e: InputMismatchException){
                escaner.nextLine()
                throw java.lang.IllegalArgumentException("Introduce un número válido")
            }
        return numero
    }

    override fun preguntarTerminar(): Boolean {
        var opcionCorrecta = false
        val siONo = arrayOf("s","n", "si", "no")
        do {
            try {
                val respuesta = pedirInfo("¿Desea hacer un nuevo cálculo? (s,si,n,no)")
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