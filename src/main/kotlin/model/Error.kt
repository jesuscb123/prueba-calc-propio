package prog2425.dam1.calculadora.model

/**
 * Representa un error registrado en el sistema.
 *
 * @property id Identificador único del error.
 * @property mensajeError Texto descriptivo del error.
 */
data class Error(val id: Int, val mensajeError: String)