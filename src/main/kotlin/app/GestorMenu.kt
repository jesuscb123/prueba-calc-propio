package prog2425.dam1.calculadora.app

import prog2425.dam1.calculadora.Service.IServCalc
import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.data.IRepoLog
import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.Fecha
import prog2425.dam1.calculadora.utils.GestorFicheros

import prog2425.dam1.calculadora.utils.IUtilFichero
import kotlin.math.sign


class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc, val repoLog: IRepoLog, val gestorFicheros: IUtilFichero) {

  fun iniciarCalculadora(rutaFichero: String){
        var terminar = false
        do{
            try {
                consola.mostrar("CALCULADORA")
                val a = pedirNumero("Introduce el primer número:")
                val signo = pedirSigno("Introduce el operador:")
                val b = pedirNumero("Introduce el segundo número:")
                val resultado = obtenerResultado(a,signo, b)
                mostrarResultado(resultado)
                guardarOperacion(rutaFichero, a, signo, b, resultado)
                if (consola.preguntarTerminar()) terminar = false else terminar = true
            }catch(e: IllegalArgumentException){
                consola.mostrarError("$e")
            }
        }while (!terminar)
        consola.mostrar("Has finalizado la calculadora")
    }




    private fun pedirNumero(msj: String): Double{
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

    private fun obtenerOperacion(a: Double, signo: String, b: Double, resultado: Double): Operacion{
        return Operacion(a, signo, b, resultado)
    }
    private fun guardarOperacion(rutaFichero: String, numero1: Double, signo: String, numero2: Double, resultado: Double){
        val OperacionTexto = obtenerOperacion(numero1, signo, numero2, resultado)
        repoLog.escribirLog(rutaFichero, OperacionTexto.toString())
    }


    fun iniciarPrograma(args: Array<String>){
        when (args.size){
            0 -> {
               iniciarSinArgumentos()
            }
            1 ->{
                iniciarConUnArgumento(args)
            }
            4 ->{
                iniciarConCuatroArgumentos(args)
            }
            else -> consola.mostrarError("Debes introducir un argumento o cuatro o ninguno.")
        }
    }

    private fun iniciarSinArgumentos(){
        try {
            val rutaDirectorio = "log"
            if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                gestorFicheros.crearDirectorio(rutaDirectorio)
                val fichero = gestorFicheros.crearFichero(rutaDirectorio,Fecha.obtenerFechaActualFormateada())
                iniciarCalculadora(fichero)
            }else{
                if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                    val lineasUltimoLog = repoLog.leerLog(rutaDirectorio)
                    consola.mostrarLista(lineasUltimoLog)
                    val fichero = gestorFicheros.crearFichero(rutaDirectorio, Fecha.obtenerFechaActualFormateada())
                    iniciarCalculadora(fichero)
                }
            }
        }catch (e: IllegalArgumentException){
            consola.mostrarError("$e")
        }catch (e: Exception){
            consola.mostrarError("$e")
        }
    }
    private fun iniciarConUnArgumento(args: Array<String>){
        try {
            val rutaDirectorio = args[0]
            if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                gestorFicheros.crearDirectorio(rutaDirectorio)
                val fichero = gestorFicheros.crearFichero(rutaDirectorio,Fecha.obtenerFechaActualFormateada())
                iniciarCalculadora(fichero)
            }else{
                if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                    val lineasUltimoLog = repoLog.leerLog(rutaDirectorio)
                    consola.mostrarLista(lineasUltimoLog)
                    val fichero = gestorFicheros.crearFichero(rutaDirectorio,Fecha.obtenerFechaActualFormateada())
                    iniciarCalculadora(fichero)
                }
            }
        }catch (e: IllegalArgumentException){
            consola.mostrarError("$e")
        }catch (e: Exception){
            consola.mostrarError("$e")
        }
    }
    private fun realizarCalculoArgumentos(numero1: Double, signo: String, numero2: Double): Double{
        val resultado =  calculadora.calculo(numero1, signo, numero2)
        return resultado
    }
    private fun iniciarConCuatroArgumentos(args: Array<String>){
        try {
            val rutaDirectorio = args[0]
            val numero1 = args[1].toDouble()
            val signo = args[2].lowercase()
            val numero2 = args[3].toDouble()
            if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                gestorFicheros.crearDirectorio(rutaDirectorio)
                gestorFicheros.crearFichero(rutaDirectorio, Fecha.obtenerFechaActualFormateada())
                val lineasUltimoLog = repoLog.leerLog(rutaDirectorio)
                consola.mostrarLista(lineasUltimoLog)
                val resultado = realizarCalculoArgumentos(numero1, signo, numero2)
                mostrarResultado(resultado)
                guardarOperacion(rutaDirectorio, numero1, signo, numero2, resultado)
                iniciarCalculadora(rutaDirectorio)
            }else{
                if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                    val lineasUltimoLog = repoLog.leerLog(rutaDirectorio)
                    consola.mostrarLista(lineasUltimoLog)
                    val fichero = gestorFicheros.crearFichero(rutaDirectorio,Fecha.obtenerFechaActualFormateada())
                    val resultado = realizarCalculoArgumentos(numero1, signo, numero2)
                    mostrarResultado(resultado)
                    guardarOperacion(fichero, numero1, signo, numero2, resultado)
                    iniciarCalculadora(fichero)
                }
            }
        }catch (e: IllegalArgumentException){
            consola.mostrarError("$e")
        }catch (e: Exception){
            consola.mostrarError("$e")
        }
}
}