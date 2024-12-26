## datos de conexión:

- Usuario: root

- Password: sin contraseña

## Montar base de datos:

- El archivo creará la base de datos, crear la tabla e insertar usuarios. Este archivo está junto a pom.xml con el nombre empleados.sql

- Hay que ejecutar el archivo empleados.mysql en una base de datos Mysql (Sepuede utilizar cualquier interfaz gráfica como MySql Workbench por ejemplo)

## Ejecución del programa:

- Simplemente se trata de ejecutar el botón "Play" del método Main, el programa ejecutará todas las instrucciones dando respuesta a las premisas de la prueba técnica.

-  No se necesitará introducir datos, también he establecido algunas validaciones como pueden ser:
  
  -  Que los datos introducidos en las variables de nombre y apellido no puedan ser nulos ni cadena vacía, tal y como establecí en la entidad empleado.
    
  -  También valido si existen registros repetidos con la base de datos.
    
  -  Por otro lado establezco control de excepciones en la clase Empleado.JPA relacionados con la comunicación con la base de datos.
    
  - Si antes de la ejecución del programa se introdujera o algún valor que arrojase alguna excepción, estos se mostrarán con un mensaje personalizado en consola en color rojo.



