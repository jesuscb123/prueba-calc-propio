package prog2425.dam1.calculadora.data.dao

import prog2425.dam1.calculadora.data.db.Database
import prog2425.dam1.calculadora.model.Operacion
import java.sql.ResultSet

class OperacionDao() : IOperacionDAO {


    init{
      crearTablaOperacion()
    }
    override fun insertar(id: String, numero1: Double, operador: String, numero2: Double, resultado: Double) {
        val consulta = "INSERT INTO Operacion (id, numero1, operador, numero2, resultado) VALUES (?,?,?,?,?)"
        Database.realizarConexion().use{conn ->
            val stmt = conn?.prepareStatement(consulta)
            stmt?.setString(1, id)
            stmt?.setDouble(2, numero1)
            stmt?.setString(3, operador)
            stmt?.setDouble(4, numero2)
            stmt?.setDouble(5, resultado)
            stmt?.executeUpdate()
        }
        }

    override fun consultarOperacion(): Operacion?{
        var operacion: Operacion? = null
        val consulta = """
                    SELECT * FROM Operacion
                     ORDER BY id DESC
                     LIMIT 1;
        """.trimIndent()
        Database.realizarConexion().use {conn ->
          val stmt = conn?.createStatement()
           val rs = stmt?.executeQuery(consulta)
            if (rs != null){
                operacion = obtenerOperacionH2(rs)
        }
        }
        return operacion
    }

    private fun obtenerOperacionH2(rs: ResultSet): Operacion?{
        var operacion: Operacion? = null
        while (rs.next()){
            operacion = Operacion(
                id = rs.getString("id"),
                numero1 = rs.getDouble("numero1"),
                signo = rs.getString("operador"),
                numero2 = rs.getDouble("numero2"),
                resultado = rs.getDouble("resultado")
            )
        }
        return operacion
    }

    private fun crearTablaOperacion(){
        val consulta = """
                   CREATE OR REPLACE TABLE IF NOT EXISTS Operacion (
                  id VARCHAR(200) PRIMARY KEY,
                  numero1 NUMERIC NOT NULL,
                  operador VARCHAR(5) NOT NULL,
                  numero2 NUMERIC NOT NULL,
                  resultado NUMERIC NOT NULL
            );
        """.trimIndent()
        Database.realizarConexion().use {conn ->
            val stmt = conn?.createStatement()
            stmt?.executeUpdate(consulta)
        }
    }
}
