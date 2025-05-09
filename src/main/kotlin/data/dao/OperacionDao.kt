package prog2425.dam1.calculadora.data.dao

import prog2425.dam1.calculadora.data.db.Database

class OperacionDao() : IOperacionDAO {
    private val conn = Database.realizarConexion()

    override fun insertar(id: String, numero1: Double, operador: String, numero2: Double, resultado: Double) {
        val consulta = "INSERT INTO Operacion (id, numero1, operador, numero2, resultado) VALUES (?,?,?,?,?))"
        val stmt = conn?.prepareStatement(consulta)
        stmt?.setString(1, id)
        stmt?.setDouble(2, numero1)
        stmt?.setString(3, operador)
        stmt?.setDouble(4, numero2)
        stmt?.setDouble(5, resultado)
        stmt?.executeUpdate()
        stmt?.close()
        Database.realizarConexion()
        }



}
