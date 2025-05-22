# PROYECTO CALCULADORA KOTLIN (FICHEROS O H2)

## Introducción
El proyecto consiste en crear una calculadora que permita almacenar las operaciones realizadas y los errores en ficheros y en una base de datos h2.

Para ver el proyecto gestionando una base de datos h2 pulsa aquí: [CALCULADORA H2](https://github.com/jesuscb123/prueba-calc-propio/tree/calcBD)

### ¿Cómo gestiona la calculadora los ficheros?
- Al ejecutar, instancia la consola para mostrar, el gestor de ficheros, el repositorio de logs y el gestor menú que inicia el programa.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/Main.kt#L13-L24

- Una vez iniciado, verifica el total de argumentos que tiene args ya que es un array. Si tiene 0 inicia el programa sin argumentos, si tiene un argumento lo inicia con uno y si tiene cuatro inicia con 4. En caso de que haya 2 o tres o más, dará error.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L120-L142

- *IniciarSinArgumentos*
  - Primero declara una variable con la ruta del directorio que es *log*. Si no encuentra el directorio, crea uno nuevo y luego crea un fichero dentro de ese dierctorio que se llama logFechaActualFormateada. Luego inicia la calculadora.
  - Si el directorio existe, obtiene el último log y lo muestra por pantalla. Luego crea otro fichero con llamada logFechaActualFormateada y luego inicia la calculadora.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L143-L166

- *iniciarConUnArgumento*
  - Declara una variable que contiene la ruta, ya que el argumento del array contiene la ruta. 
  - Vuelve a hacer lo mismo que el anterior.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L168-L193

- *iniciarConCuatroArgumentos*
  - El primer argumento es la ruta, el segundo es el primer número y lo intenta convertir a Double, si no puede lanza una excepción. El tercero es el operador y el cuarto es el segundo número y lo intenta convertir a Double.
  - Luego hace lo mismo que el otro, solo que la diferencia es que muestra (además del resultado del último log en caso de haber encontrado el directorio) el resultado de la operación que ha introducido por parámetros.

  - El cálculo se realiza llamando al método *realizarCalculoArgumentos* ya que como la calculadora no se ha iniciado, llama a este método para realizar el calculo de los números y el operador que recibe por argumentos.
  
https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L209-L247

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L196-L207

- Una vez inicia la calculadora con el método *iniciarCalculadora* hace un bucle para pedir al usuario un número, el operador y otro número. Obtiene el resultado y guarda la operación utilizando la ruta que recibe por parámetros. Luego pregunta si quiere realizar otro cálculo o no.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L24-L47
    
- *GuardarOperacion*
  - obtiene la operación y lo escribe en el fichero llamando al repoLog.

https://github.com/jesuscb123/prueba-calc-propio/blob/73ae80efa283866b7003e278213c90bfec9d8dc6/src/main/kotlin/app/GestorMenu.kt#L105-L117

- *escribirLog*
  - Es un método de *repoLog* que obtiene un fichero y si el fichero no es nulo, añade una línea más al fichero con el texto que recibe por parámetro. De esta manera, consigue almacenar los cálculos si sigue realizando operaciones. Para obtener el fichero llama al gestor de base de datos que se encarga de buscar el fichero y devolverlo.

    https://github.com/jesuscb123/prueba-calc-propio/blob/c594578ec58c879d05997dd016d78ef410196744/src/main/kotlin/data/RepoLog.kt#L14-L26