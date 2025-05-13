package prog2425.dam1.calculadora.Service

import prog2425.dam1.calculadora.model.Operacion

interface IOperacionService {
   fun insertar( numero1: Double, operador: String, numero2: Double, operacion: Double )
   fun consultarOperacion(): Operacion?
}