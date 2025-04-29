package prog2425.dam1.calculadora.utils

import java.sql.Connection
import java.sql.DriverManager

class GestorBD : IUtilGestorBD {

    companion object{
        private const val JDBC_URL = "jdbc:h2:./calculadoraDB"
        private val USUARIO = "sa"
        private val CONTRASENIA = ""
        fun connect(): Connection {
            return DriverManager.getConnection(JDBC_URL, USUARIO, CONTRASENIA)
        }
    }


   override fun crearTabla(){
        val sql = """
                CREATE TABLE IF NOT EXISTS operaciones (
                    id PRIMARY KEY,
                    operacion VARCHAR(200),
                    resultado DOUBLE
                )
        """.trimIndent()

        connect().use { conn -> conn.createStatement().use { stmt -> stmt.execute(sql) } }
    }

    override fun guardarOperacion(operacion: String, resultado: Double) {


    }
}