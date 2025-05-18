package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.data.dao.IErrorDao

class ErrorService(val errorDao: IErrorDao) : IErrorService {
    override fun insertarError(mensajeError: String) {
        require(mensajeError.isNotEmpty())
        errorDao.insertarError(mensajeError)
    }
}