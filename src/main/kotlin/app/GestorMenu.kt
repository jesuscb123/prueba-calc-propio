package prog2425.dam1.calculadora.app

import prog2425.dam1.calculadora.Service.IServCalc
import prog2425.dam1.calculadora.UI.IEntradaSalida
import prog2425.dam1.calculadora.data.IRepoLog
import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.Fecha
import prog2425.dam1.calculadora.utils.GestorFicheros

import prog2425.dam1.calculadora.utils.IUtilFichero
import kotlin.math.sign

/**
 * Clase que se encarga de controlar el menú principal de la calculadora.
 * Permite el flujo entre consola, cálculos y logs.
 *
 * @property consola Encargada de mostrar y pedir datos al usuario.
 * @property calculadora Servicio que realiza las operaciones.
 * @property repoLog Guarda las operaciones realizadas en un fichero log.
 * @property gestorFicheros Se encarga de crear, buscar y gestionar ficheros y carpetas.
 */
class GestorMenu(val consola: IEntradaSalida, val calculadora: IServCalc, val repoLog: IRepoLog, val gestorFicheros: IUtilFichero) {

    /**
     * Inicia la calculadora con bucle de operaciones hasta que el usuario decide salir.
     * Usa la ruta del fichero para guardar cada operación en un log.
     *
     * @param rutaFichero Ruta del fichero donde se guardarán las operaciones.
     */
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

    /**
     * Pide un número al usuario por consola.
     *
     * @param msj Mensaje que se muestra al usuario.
     * @return Número introducido.
     */
    private fun pedirNumero(msj: String): Double{
       return consola.pedirDouble(msj)
    }

    /**
     * Pide un operador al usuario por consola y lo valida.
     *
     * @param msj Mensaje a mostrar.
     * @return Operador como cadena: "+", "-", "*", "x" o "/".
     */
    private fun pedirSigno(msj: String): String {
       return consola.pedirSigno(msj){
            it in arrayOf("+","-","*","x","/")
        }
    }

    /**
     * Realiza la operación con los valores introducidos.
     *
     * @param a Primer número.
     * @param signo Operador.
     * @param b Segundo número.
     * @return Resultado del cálculo.
     */
    private fun obtenerResultado (a: Double, signo: String, b: Double): Double{
        return calculadora.calculo(a,signo,b)
    }

    /**
     * Muestra el resultado por consola.
     *
     * @param resultado Resultado a mostrar.
     */
    private fun mostrarResultado (resultado: Double) {
        consola.mostrar(resultado)
    }

    /**
     * Crea un objeto Operacion con los datos del cálculo.
     *
     * @param a Primer número.
     * @param signo Operador.
     * @param b Segundo número.
     * @param resultado Resultado del cálculo.
     * @return Operacion creada.
     */
    private fun obtenerOperacion(a: Double, signo: String, b: Double, resultado: Double): Operacion{
        return Operacion(a, signo, b, resultado)
    }

    /**
     * Guarda la operación en el fichero de log.
     *
     * @param rutaFichero Ruta del fichero log.
     * @param numero1 Primer número.
     * @param signo Operador.
     * @param numero2 Segundo número.
     * @param resultado Resultado de la operación.
     */
    private fun guardarOperacion(rutaFichero: String, numero1: Double, signo: String, numero2: Double, resultado: Double){
        val OperacionTexto = obtenerOperacion(numero1, signo, numero2, resultado)
        repoLog.escribirLog(rutaFichero, OperacionTexto.toString())
    }


    /**
     * Inicia el programa según los argumentos:
     * - 0: sin argumentos, usa carpeta "log"
     * - 1: usa una ruta específica.
     * - 4: ruta del fichero, operación directa con dos números y operador.
     *
     * @param args Argumentos recibidos al ejecutar el programa.
     */
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

    /**
     * Inicia el programa sin argumentos, usando carpeta por defecto ("log"). Si el directorio no existe, crea uno nuevo y luego crea el fichero con el nombre que será logfechaactualformateada, luego inicia la calculadora. Sino, comprueba si el directorio existente ya tiene ficheros y muestra el último log creado pra mostrar las últimas operaciones.
     */
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

    /**
     * Inicia el programa con un argumento (ruta de la carpeta).
     *
     * @param args Argumentos donde args[0] es la ruta del directorio.
     */
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


    /**
     * Realiza el cálculo a partir de los argumentos sin pedir datos por consola.
     *
     * @param numero1 Primer número.
     * @param signo Operador.
     * @param numero2 Segundo número.
     * @return Resultado del cálculo.
     */
    private fun realizarCalculoArgumentos(numero1: Double, signo: String, numero2: Double): Double{
        val resultado =  calculadora.calculo(numero1, signo, numero2)
        return resultado
    }

    /**
     * Inicia el programa con 4 argumentos: carpeta, número1, operador y número2.
     * Muestra resultado directo y permite realizar más cálculos.
     *
     * @param args Argumentos: ruta, número1, operador, número2.
     */
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