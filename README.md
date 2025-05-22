# PROYECTO CALCULADORA KOTLIN (FICHEROS O H2)
## Introducción
El proyecto consiste en crear una calculadora que permita almacenar las operaciones realizadas y los errores en ficheros y en una base de datos h2.
Para ver el prouecto gestionando ficheros pulsa aquí: [CALCULADORA FICHEROS](https://github.com/jesuscb123/prueba-calc-propio/tree/main)

### ¿Cómo gestiona la calculadora una base de datos h2?
- Al iniciar el programa instancia la consola, la calculadora para realizar los cálculos, operacionDao para gestionar las consultas de operaciones en la base de datos h2, errorDao para gestionar consultas de errores en la base de datos h2. OperacionService y errorService que llama al dao para realizar las consultas y gestor menú que inicia el programa.

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/Main.kt#L12-L25
- El primer método que se inicia es *iniciarPrograma* que según el número de argumentos llamará al método correspondiente. En este caso si tiene 0 realiza una consulta para consultar la última operación almacenada en la base de datos, se muestra y luego inicia la calculadora. Si tiene tres argumentos, llama al método *iniciarConTresArgumentos* y luego inicia la calculadora. *Tiene tres argumentos a diferencia de ficheros que tiene cuatro, debido a qué aquí no existe ruta de ficheros.*

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/app/GestorMenu.kt#L130-L160

- *iniciarConTresArgumentos*
  - Intenta convertir el primer argumento en double debido a qué es el primer número de la operación. El segundo es el operador y el tercero el segundo número.
  - Realiza el cálculo y guarda la operación en la base de datos.

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/app/GestorMenu.kt#L174-L186

- *guardarOperacion*
  - llama al servicio de la operación para insentar la operación en la base de datos.

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/app/GestorMenu.kt#L111-L121

- *consultarOperacion*
  - Llama al servicio de operación para realizar la consulta.

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/app/GestorMenu.kt#L103-L109
- Iniciar calculadora pide un número, un signo y otro número, si se lanza una excepción, se captura y sigue el bucle. Si todo está bien, muestra el resultado de la operación y la guarda en la base de datos, en caso de error lo muestra y guarda el error en la base de datos.

https://github.com/jesuscb123/prueba-calc-propio/blob/7a108ab68a6fedc7b778567ecd63588115a9314e/src/main/kotlin/app/GestorMenu.kt#L27-L62

*NOTA: Todas las consultas están controladas debido a que tengo un try-catch en iniciarPrograma e iniciarCalculadora, si se lanza una excepción, se captura en inciciarCalculadora si los métodos han sido llamados desde ahí o desde el otro método.

- Para crear las tablas de error y de operación, cuándo se instancia cada Dao se crean las tablas si no existen.

https://github.com/jesuscb123/prueba-calc-propio/blob/5eb1a0dfa1e59d0be7453115fe489313ee0c15a3/src/main/kotlin/data/dao/OperacionDao.kt#L7-L12

https://github.com/jesuscb123/prueba-calc-propio/blob/5eb1a0dfa1e59d0be7453115fe489313ee0c15a3/src/main/kotlin/data/dao/OperacionDao.kt#L57-L71

- *OperacionService*
  - Inserta la operación y hace que el id de la operación sea la fecha actual formateada.
  
    https://github.com/jesuscb123/prueba-calc-propio/blob/778e11f8dec3fa1ced17513c8f41e7f2c751349a/src/main/kotlin/Service/OperacionService.kt#L14-L25
#### operacionDao
- Inserta en una base de datos llamado al método *realizarConexion* de dataBase. 

https://github.com/jesuscb123/prueba-calc-propio/blob/5eb1a0dfa1e59d0be7453115fe489313ee0c15a3/src/main/kotlin/data/dao/OperacionDao.kt#L13-L24

- Consulta la última operación realizada. Se sabe cuál es la última debido a que el id es la fecha actual formateada, con horas y segundos.

https://github.com/jesuscb123/prueba-calc-propio/blob/4527883f50009ceaa8d9197eac84e155a2c6ed87/src/main/kotlin/data/dao/OperacionDao.kt#L37-L57

- Obtiene una operación o nulo en caso de no haber podido recuperar la operación.

https://github.com/jesuscb123/prueba-calc-propio/blob/4527883f50009ceaa8d9197eac84e155a2c6ed87/src/main/kotlin/data/dao/OperacionDao.kt#L59-L77

#### errorDao
- Inserta los errores en la base de datos.

  https://github.com/jesuscb123/prueba-calc-propio/blob/4527883f50009ceaa8d9197eac84e155a2c6ed87/src/main/kotlin/data/dao/ErrorDao.kt#L15-L29
