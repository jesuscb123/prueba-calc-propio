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
   gestorMenu.iniciarPrograma(args, fechaFormateada)
   }


