package prog2425.dam1.calculadora

import prog2425.dam1.calculadora.Service.Calculadora
import prog2425.dam1.calculadora.Service.ErrorService
import prog2425.dam1.calculadora.Service.OperacionService
import prog2425.dam1.calculadora.UI.Consola
import prog2425.dam1.calculadora.app.GestorMenu
import prog2425.dam1.calculadora.data.dao.ErrorDao
import prog2425.dam1.calculadora.data.dao.OperacionDao


/**
 * Inicia el programa e instancia lo necesario. La consola para mostrar, la calculadora, operacionDao para gestionar las consultas para insertar operaciones en la base de datos, errorDao para gestionar consultas sobre errores, operaciónService encargada de llamar a dao para gestionar las consultas y errorService llama a errorDao. Gestor menú inicia el programa.
 *
 * @param args argumentos que recibe por parámetros.
 */
fun main(args: Array<String>) {
   val consola = Consola()
   val calculadora = Calculadora()
   val operacionDao = OperacionDao()
   val errorDao = ErrorDao()
   val operacionService = OperacionService(operacionDao)
   val errorService = ErrorService(errorDao)
   GestorMenu(consola, calculadora, operacionService, errorService).iniciarPrograma(args)
   }


