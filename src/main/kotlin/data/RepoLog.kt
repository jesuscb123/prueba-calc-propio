package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import prog2425.dam1.calculadora.utils.IUtilFichero
import prog2425.dam1.calculadora.utils.IUtilGestorBD
import java.io.File

class RepoLog(val gestorDB: IUtilGestorBD) : IRepoLog {
    override fun guardarOperacion(operacion: String, resultado: Double) {
        gestorDB.guardarOperacion(operacion, resultado)
        }
    }
