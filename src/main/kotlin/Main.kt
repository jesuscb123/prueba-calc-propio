package prog2425.dam1.calculadora

import prog2425.dam1.calculadora.Service.Calculadora
import prog2425.dam1.calculadora.UI.Consola
import prog2425.dam1.calculadora.app.GestorMenu
import prog2425.dam1.calculadora.utils.Fecha
import prog2425.dam1.calculadora.utils.GestorFicheros
import java.time.LocalDateTime

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
   val consola = Consola()
   val fecha = Fecha()
   val gestorFicheros = GestorFicheros(fecha)
   val gestorMenu = GestorMenu(consola, Calculadora())
   when (args.size){
      0 -> {
         val rutaDirectorio = "log"
         if (!gestorFicheros.buscarDirectorio(rutaDirectorio)){
            gestorFicheros.crearDirectorio(rutaDirectorio)
            gestorFicheros.crearFichero(rutaDirectorio,fecha.parsearFecha(LocalDate.now()).toString())
            val log = gestorFicheros.obtenerUltimoLog(rutaDirectorio)
            consola.mostrarLista(gestorFicheros.leerLog(log))
            gestorMenu.iniciarCalculadora()
         }
         }

      }
   }
