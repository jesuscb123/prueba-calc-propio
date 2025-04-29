package prog2425.dam1.calculadora

import prog2425.dam1.calculadora.Service.Calculadora
import prog2425.dam1.calculadora.UI.Consola
import prog2425.dam1.calculadora.app.GestorMenu
import prog2425.dam1.calculadora.data.RepoLog
import prog2425.dam1.calculadora.utils.Fecha
import prog2425.dam1.calculadora.utils.GestorBD
import prog2425.dam1.calculadora.utils.GestorFicheros
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.cos

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
   val consola = Consola()
   val gestorBD = GestorBD()
   gestorBD.crearTabla()
   val repoLog = RepoLog(gestorBD)
   val gestorMenu = GestorMenu(consola, Calculadora(), repoLog, gestorBD)
   gestorMenu.iniciarPrograma(args)
   }


