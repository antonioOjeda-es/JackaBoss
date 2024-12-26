package org.antonio;

import org.antonio.controllers.EmpleadoController;
import org.antonio.entities.Empleado;
import org.antonio.utilities.OperacionesEmpleado;
import org.antonio.utilities.ValidarEntradas;
import org.antonio.utilities.VerificarDuplicados;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ValidarEntradas {
        //Creo una instancia del controlador, este contiene los métodos para el crud
        EmpleadoController empleadoController = new EmpleadoController();

        //Creo un variable de lista de empleados para almacenar las listas resultantes de buscar los empleados
        List<Empleado> todosLosEmpleados;


        /* agregar un nuevo empleado: */

        System.out.println("Crear un empleado");
        System.out.println("_________________");

        //validar que los datos introducidos no sean null con try/catch
        try {
            String nombreEmpleado = "Víctor";
            String apellidoEmpleado = "pérez";
            System.out.println("-----------------------------");
            System.out.println("nombre " + nombreEmpleado);
            System.out.println("Apellido: " + apellidoEmpleado);
            System.out.println("-----------------------------");

            //aquí valoro que el nombre introducido o apellido no sean vacío o nulos
            // y además que no estén repetidos en la base de datos
            if (nombreEmpleado == null || nombreEmpleado.isEmpty() || apellidoEmpleado == null
                    || apellidoEmpleado.isEmpty()) {
                //creo una instancia del error para enviar a catch
                throw new ValidarEntradas("El nombre y apellidos no pueden estar vacíos");
            }
            if (VerificarDuplicados.VerificarDuplicadosCadenaTexto(nombreEmpleado, apellidoEmpleado)){
                throw new ValidarEntradas("El empleado ya existe en la base de datos");
            }else {
                System.out.println(VerificarDuplicados.VerificarDuplicadosCadenaTexto(nombreEmpleado, apellidoEmpleado));
                //instancio un nuevo empleado para introducirlo en la base de datos
                Empleado empleado11 = new Empleado(nombreEmpleado, apellidoEmpleado, "Data Analyst", 38000.00, LocalDateTime.of(2022, 4, 15, 9, 0));

                //método para introducir empleado
                empleadoController.agregarEmpleado(empleado11);

                System.out.println("------------------");
                System.out.println("Empleado agregado.");
                System.out.println("------------------");

                //voy a listar los empleados una vez introducido uno nuevo, la función de listar, volverá a aparecer
                todosLosEmpleados = empleadoController.listarEmpleados();
                OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);
            }
        } catch (ValidarEntradas e) {
            System.err.println("Error al introducir los datos: " + e.getMessage());
        }


        /* listar empleados: */

        System.out.println("-------------------");
        System.out.println("Lista de empleados.");
        System.out.println("-------------------");

        //uso una variable ya instanciada al principio para aprovechar la variable
        todosLosEmpleados = empleadoController.listarEmpleados();

        //he creado una clase en utilities relacionada con operaciones complementarias para la aplicación,
        // sirve para limpieza de código y mejor orden a la hora de repetir acciones
        OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);



        /* Actualizar información de un empleado: */

        //primero voy a buscar un empleado, voy a utilizar una variable para ello
        //Si el empleado no existe, mostrará un mensaje procedente de Empleado.JPA
        int empleadoBuscar = 11;
        System.out.println("_____________________________________________");
        System.out.println("Actualizar empleado con id: " + empleadoBuscar);
        System.out.println("_____________________________________________");

        //creo un empleado vacío y uso un método para encontrarlo por id
        //si existe, se imprimirá por consola, sinó, lanzará un error
        Empleado empleadoEncontrado = empleadoController.buscarEmpleado(empleadoBuscar);


        //modificar la información del empleado
        //validar los datos de nombre y apellido para que no sean nulos o cadena vacía con try/catch
        try {
            //creo las variables que me permitirán validar los datos introducidos
            String nombreEmpleadoModificar = "Alejandro";
            String apellidoEmpleadoModificar = "Pérez";

            //valoro si los datos no son nulos o cadena vacía
            if (nombreEmpleadoModificar == null || nombreEmpleadoModificar.isEmpty() ||
                    apellidoEmpleadoModificar == null || apellidoEmpleadoModificar.isEmpty()) {
                //creo una instancia del error para enviar a catch
                throw new ValidarEntradas("El nombre y apellidos no pueden estar vacíos");
            }if (VerificarDuplicados.VerificarDuplicadosCadenaTexto(nombreEmpleadoModificar, apellidoEmpleadoModificar)){
                throw new ValidarEntradas("El empleado que intenta modificar ya existe en la base de datos");
            }
            else {
                empleadoEncontrado.setNombre(nombreEmpleadoModificar);
                empleadoEncontrado.setApellido(apellidoEmpleadoModificar);
                empleadoEncontrado.setCargo("Jefa de Recursos Humanos");
                empleadoEncontrado.setSalario(60000.00);
                empleadoEncontrado.setFechaInicio(LocalDateTime.of(2018, 5, 5, 10, 0));

                //lo inserto en la base de datos después de haber seteado sus valores
                empleadoController.actualizarEmpleado(empleadoEncontrado);

                //por último voy a volver a listar los empleados, para mostrar que el empleado con el id = 2 ha sido actualizado
                //para ello reaprovecho la variable de List de todos los empleados
                todosLosEmpleados = empleadoController.listarEmpleados();

                System.out.println("------------------------------------------------");
                System.out.println("Empleado con id " + empleadoBuscar + " modificado");
                System.out.println("------------------------------------------------");

                //lo imprimo con el método que he creado en el paquete utilities
                OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);
            }
        } catch (ValidarEntradas e) {
            System.err.println("Error al actualizar Empleado: " + e.getMessage());
        }


        /* Eliminar empleado: */

        //creo una variable que contiene un id del empleado a eliminar de la base de datos
        int empleadoEliminar = 5;

        System.out.println("------------------");
        System.out.println("Eliminar empleado: " +empleadoEliminar);
        System.out.println("------------------");
        //elimino el empleado, si no existe en la base de datos mostrará una excepción por consola
        empleadoController.eliminarEmpleado(empleadoEliminar);

        //por último voy a volver a listar los empleados, para mostrar que el empleado con el id = 2 ha sido actualizado
        //para ello reaprovecho la variable de List de todos los empleados
        todosLosEmpleados = empleadoController.listarEmpleados();
        //lo imprimo con el método que he creado en el paquete utilities, el empleado 11 debe de estar eliminado
        OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);



        /* Buscar por cargo: */
        //Creo un String con el cargo a buscar
        //(la búsqueda no distingue de mayúsculas de minúsculas porque el lenguaje SQL no los distingue)
        String cargo = "jefa de recursos humanos";
        System.out.println("__________________________________________________________");
        System.out.println("Buscar empleado por cargo: " + cargo);
        System.out.println("__________________________________________________________");
        //Uso el método para buscar por cargo
        List<Empleado> empleadosPorCargo = empleadoController.buscarEmpleadoCargo(cargo);

        //por último imprimo los empleados encontrados:
        //lo imprimo con el método que he creado en el paquete utilities
        System.out.println("--------------------------");
        System.out.println("Resultados de la búsqueda:");
        OperacionesEmpleado.iterarListaEmpleados(empleadosPorCargo);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("Programa finalizado");
    }
}