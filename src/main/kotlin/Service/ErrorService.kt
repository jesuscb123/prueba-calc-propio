package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.data.dao.IErrorDao

/**
 * Servicio encargado de gestionar los errores y su persistencia.
 *
 * @property errorDao DAO encargado de insertar los errores en la base de datos o sistema de almacenamiento.
 */
class ErrorService(val errorDao: IErrorDao) : IErrorService {
    /**
     * Inserta un mensaje de error.
     *
     * @param mensajeError Mensaje de error a insertar. No puede estar vacío.
     * @throws IllegalArgumentException si el mensaje de error está vacío.
     */
    override fun insertarError(mensajeError: String) {
        require(mensajeError.isNotEmpty())
        errorDao.insertarError(mensajeError)
    }
}