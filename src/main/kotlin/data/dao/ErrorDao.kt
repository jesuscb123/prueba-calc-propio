package prog2425.dam1.calculadora.data.dao

import prog2425.dam1.calculadora.data.db.Database

class ErrorDao() : IErrorDao {

    init{
        crearTablaError()
    }


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