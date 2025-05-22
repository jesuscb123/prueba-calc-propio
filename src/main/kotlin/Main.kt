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

/**
 * Inicia el programa e instancia lo necesario. La consola para mostrar, el gestor de ficheros, el repositorio de log para poder recuperar o almacenar datos y el gestor menú para iniciar los programas.
 *
 * @param args argumentos que recibe por parámetros.
 */
fun main(args: Array<String>) {
   val consola = Consola()
   val gestorFicheros = GestorFicheros()
   val repoLog = RepoLog(gestorFicheros)
   val gestorMenu = GestorMenu(consola, Calculadora(), repoLog, gestorFicheros)
   gestorMenu.iniciarPrograma(args)
   }


