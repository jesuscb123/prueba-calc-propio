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

    fun iniciarCalculadora(rutaFichero: String, a: String, signo: String, b: String){
        try {
            consola.limpiarPantalla()
            consola.mostrar("CALCULADORA")
            val numero1 = a.toDouble()
            val numero2 = b.toDouble()
            val resultado = obtenerResultado(numero1, signo, numero2)
            mostrarResultado(resultado)

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
        gestorFicheros.escribirLog(rutaFichero, OperacionTexto)
    }


    fun iniciarPrograma(args: Array<String>, fechaFormateada: String){
        when (args.size){
            0 -> {
                try {
                    val rutaDirectorio = "log"
                    if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                        gestorFicheros.crearDirectorio(rutaDirectorio)
                        val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                        iniciarCalculadora(fichero)
                    }else{
                        if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                            val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
                            consola.mostrarLista(lineasUltimoLog)
                            val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                            iniciarCalculadora(fichero)
                        }
                    }
                }catch (e: IllegalArgumentException){
                    consola.mostrarError("$e")
                }catch (e: Exception){
                    consola.mostrarError("$e")
                }
            }
            1 ->{
                try {
                    val rutaDirectorio = args[0]
                    if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                        gestorFicheros.crearDirectorio(rutaDirectorio)
                        val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                        iniciarCalculadora(fichero)
                    }else{
                        if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                            val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
                            consola.mostrarLista(lineasUltimoLog)
                            val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                            iniciarCalculadora(fichero)
                        }
                    }
                }catch (e: IllegalArgumentException){
                    consola.mostrarError("$e")
                }catch (e: Exception){
                    consola.mostrarError("$e")
                }

            }
            4 ->{
                try {
                    val rutaDirectorio = args[0]
                    if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
                        gestorFicheros.crearDirectorio(rutaDirectorio)
                        val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                        val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
                        consola.mostrarLista(lineasUltimoLog)
                        iniciarCalculadora(fichero,args[1],args[2].lowercase(),args[3])
                    }else{
                        if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                            val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
                            consola.mostrarLista(lineasUltimoLog)
                            val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                            iniciarCalculadora(fichero,args[1],args[2],args[3])
                        }
                    }
                }catch (e: IllegalArgumentException){
                    consola.mostrarError("$e")
                }catch (e: Exception){
                    consola.mostrarError("$e")
                }
            }
            else -> consola.mostrarError("Debes introducir un argumento o cuatro o ninguno.")
        }
    }


}