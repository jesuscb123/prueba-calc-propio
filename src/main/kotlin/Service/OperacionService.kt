package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.data.dao.IOperacionDAO
import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.Fecha

class OperacionService(val operacionDao: IOperacionDAO): IOperacionService {

    override fun insertar( numero1: Double, operador: String, numero2: Double, operacion: Double ) {
        val id = Fecha.obtenerFechaActualFormateada()
        operacionDao.insertar( id, numero1, operador, numero2, operacion )
    }

    override fun consultarOperacion(): Operacion? {
       return operacionDao.consultarOperacion()
    }
}