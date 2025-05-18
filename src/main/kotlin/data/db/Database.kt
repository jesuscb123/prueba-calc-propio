package prog2425.dam1.calculadora.data.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Database {
    private const val JDBC_URL = "jdbc:h2:./data/calculadora"
    private const val USUARIO = "sa"
    private const val CONTRASENIA = ""

    fun realizarConexion(): Connection? {
        var conn :Connection? = null
        try {
            conn = DriverManager.getConnection(JDBC_URL, USUARIO, CONTRASENIA)
        }catch (e: SQLException) {
            throw IllegalArgumentException("Error al realizar la conexión con la base de datos. ${e.message}")
        }catch (e: Exception){
            throw Exception("Error inesperado: ${e.message}")
        }
        return conn
    }

    fun cerrarConexion(conn: Connection?) {
        try {
            conn?.close()
        }catch (e: SQLException) {
            throw IllegalArgumentException("Error al cerrar la conexión.")
        }catch (e: Exception){
            throw Exception("Error inesperado: ${e.message}")
        }
    }
}