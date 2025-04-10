package prog2425.dam1.calculadora.UI

interface IEntradaSalida {
    fun <T> mostrar (msj: T)
    fun mostrarError (msj: String)
    fun pedirSigno (msj: String, debeCumplir: (String) -> Boolean): String
    fun pedirDouble(msj: String): Double
    fun preguntarTerminar(): Boolean
    fun pedirInfo(msj: String): String
    fun limpiarPantalla(numSaltos:Int = 20)
    fun <T> mostrarLista(lista: List<T>)
    fun pausar()
}