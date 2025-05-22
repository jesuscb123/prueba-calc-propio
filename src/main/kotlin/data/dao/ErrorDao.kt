package prog2425.dam1.calculadora.data.dao

import prog2425.dam1.calculadora.data.db.Database

/**
 * Implementación de la interfaz [IErrorDao] encargada de gestionar los errores
 * almacenados en la base de datos.
 */
class ErrorDao() : IErrorDao {

    init{
        crearTablaError()
    }

    /**
     * Inserta un mensaje de error en la tabla `Error` de la base de datos.
     *
     * @param mensajeError Mensaje descriptivo del error que se desea registrar.
     */
    override fun insertarError(mensajeError: String) {
        val consulta = """
                    INSERT INTO Error (mensaje) VALUES (?)
        """.trimIndent()
        Database.realizarConexion().use{conn ->
            val stmt = conn?.prepareStatement(consulta)
            stmt?.setString(1, mensajeError)
            stmt?.executeUpdate()
        }
    }

    /**
     * Crea la tabla `Error` en la base de datos si no existe.
     *
     * La tabla contiene:
     * - `id`: clave primaria autoincremental.
     * - `mensaje`: campo de texto para almacenar el mensaje del error.
     */
    private fun crearTablaError(){
        val consulta = """
                   CREATE OR REPLACE TABLE IF NOT EXISTS Error (
                  id INT AUTO_INCREMENT PRIMARY KEY,
                  mensaje VARCHAR(500) NOT NULL
            );
        """.trimIndent()
        Database.realizarConexion().use { conn ->
            val stmt = conn?.createStatement()
            stmt?.executeUpdate(consulta)
        }
    }
}