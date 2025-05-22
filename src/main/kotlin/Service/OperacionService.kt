package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.data.dao.IOperacionDAO
import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.Fecha

/**
 * Servicio que gestiona operaciones y llama al dao para insertar o consultar en la base de datos..
 *
 * @property operacionDao Objeto DAO para acceso a datos de operaciones.
 */
class OperacionService(val operacionDao: IOperacionDAO): IOperacionService {

    /**
     * Inserta una nueva operación en la base de datos.
     *
     * @param numero1 Primer número de la operación.
     * @param operador Operador de la operación (por ejemplo, "+", "-", "*", "/").
     * @param numero2 Segundo número de la operación.
     * @param operacion Resultado de la operación.
     */
    override fun insertar( numero1: Double, operador: String, numero2: Double, operacion: Double ) {
        val id = Fecha.obtenerFechaActualFormateada()
        operacionDao.insertar( id, numero1, operador, numero2, operacion )
    }

    /**
     * Consulta la última operación guardada.
     *
     * @return Objeto Operacion con los datos de la última operación o null si no hay.
     */
    override fun consultarOperacion(): Operacion? {
       return operacionDao.consultarOperacion()
    }
}