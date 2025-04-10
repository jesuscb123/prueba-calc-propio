package prog2425.dam1.calculadora

import prog2425.dam1.calculadora.Service.Calculadora
import prog2425.dam1.calculadora.UI.Consola
import prog2425.dam1.calculadora.app.GestorMenu
import prog2425.dam1.calculadora.data.RepoLog
import prog2425.dam1.calculadora.utils.Fecha
import prog2425.dam1.calculadora.utils.GestorFicheros
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.cos

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
   val consola = Consola()
   val fechaFormateada = Fecha.obtenerFechaActualFormateada()
   val repoLog = RepoLog()
   val gestorFicheros = GestorFicheros(repoLog)
   val gestorMenu = GestorMenu(consola, Calculadora(), gestorFicheros)
   when (args.size){
      0 -> {
         try {
            val rutaDirectorio = "log"
            if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
               gestorFicheros.crearDirectorio(rutaDirectorio)
               val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
               gestorMenu.iniciarCalculadora(fichero)
            }else{
               if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
                  val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
                  consola.mostrarLista(lineasUltimoLog)
                  val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
                  gestorMenu.iniciarCalculadora(fichero)
               }
            }
         }catch (e: IllegalArgumentException){
            consola.mostrarError("$e")
         }catch (e: Exception){
            consola.mostrarError("$e")
         }
         }
      1 ->{
         val rutaDirectorio = args[0]
         if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
            gestorFicheros.crearDirectorio(rutaDirectorio)
            val fichero = gestorFicheros.crearFichero(rutaDirectorio,fechaFormateada)
            gestorMenu.iniciarCalculadora(fichero)
         }else{
            if (gestorFicheros.comprobarFicheros(rutaDirectorio)){
               val lineasUltimoLog = gestorFicheros.leerLog(gestorFicheros.obtenerUltimoLog(rutaDirectorio))
               consola.mostrarLista(lineasUltimoLog)
               gestorMenu.iniciarCalculadora(rutaDirectorio)
            }
         }
      }
      4 ->{
         val calculadora = Calculadora()


      }
      }
   }
