package prog2425.dam1.calculadora.Service


enum class Operadores(signo: String) {
        SUMAR("+"), RESTAR("-"), DIVIDIR("/"), MULTIPLICAR("*");

    fun getOperador(signo: String): String{
        when(signo){
            "+" -> return "+"
            "-" -> return "-"
            "*" -> return "*"
            else -> return "/"
        }
    }

}