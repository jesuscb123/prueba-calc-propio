package prog2425.dam1.calculadora

import prog2425.dam1.calculadora.Service.Calculadora
import prog2425.dam1.calculadora.Service.ErrorService
import prog2425.dam1.calculadora.Service.OperacionService
import prog2425.dam1.calculadora.UI.Consola
import prog2425.dam1.calculadora.app.GestorMenu
import prog2425.dam1.calculadora.data.dao.ErrorDao
import prog2425.dam1.calculadora.data.dao.OperacionDao



fun main(args: Array<String>) {
   val consola = Consola()
   val calculadora = Calculadora()
   val operacionDao = OperacionDao()
   val errorDao = ErrorDao()
   val operacionService = OperacionService(operacionDao)
   val errorService = ErrorService(errorDao)
   GestorMenu(consola, calculadora, operacionService, errorService).iniciarPrograma(args)
   }


