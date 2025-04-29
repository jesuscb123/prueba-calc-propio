package prog2425.dam1.calculadora.data

import prog2425.dam1.calculadora.model.Operacion
import java.io.File

interface IRepoLog {

  fun guardarOperacion(operacion: String, resultado: Double)

}