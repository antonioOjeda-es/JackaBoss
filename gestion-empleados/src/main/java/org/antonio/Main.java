package org.antonio;

import org.antonio.controllers.EmpleadoController;
import org.antonio.entities.Empleado;
import org.antonio.utilities.OperacionesEmpleado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Creo una instancia del controlador, este contiene los métodos para el crud
        EmpleadoController empleadoController = new EmpleadoController();

        //listar empleados en el caso de que si ya está introducidos, no los vuelva a introducir en la base de datos
        if(empleadoController.listarEmpleados().isEmpty()){

            //creo una lista de tipo empleado:
            List<Empleado> empleados = new ArrayList<>();

            //Agregar instancias de Empleado empleados a la lista empleados
            empleados.add(new Empleado("Carlos", "Rodríguez", "Director General", 85000.00, LocalDateTime.of(2020, 1, 15, 9, 0)));
            empleados.add(new Empleado("Ana", "Martínez", "Jefa de Desarrollo", 65000.00, LocalDateTime.of(2020, 3, 1, 9, 0)));
            empleados.add(new Empleado("David", "López", "Desarrollador Senior", 48000.00, LocalDateTime.of(2021, 2, 15, 9, 0)));
            empleados.add(new Empleado("Laura", "García", "Desarrolladora Junior", 28000.00, LocalDateTime.of(2023, 6, 1, 9, 0)));
            empleados.add(new Empleado("María", "Sánchez", "Responsable RRHH", 42000.00, LocalDateTime.of(2021, 9, 1, 9, 0)));
            empleados.add(new Empleado("Pablo", "Fernández", "Diseñador UX/UI", 35000.00, LocalDateTime.of(2022, 3, 15, 9, 0)));
            empleados.add(new Empleado("Elena", "Ruiz", "Contable", 32000.00, LocalDateTime.of(2021, 11, 1, 9, 0)));
            empleados.add(new Empleado("Miguel", "Torres", "Project Manager", 45000.00, LocalDateTime.of(2022, 1, 10, 9, 0)));
            empleados.add(new Empleado("Sara", "Navarro", "QA Tester", 32000.00, LocalDateTime.of(2022, 8, 1, 9, 0)));
            empleados.add(new Empleado("Javier", "Moreno", "DevOps Engineer", 52000.00, LocalDateTime.of(2021, 5, 1, 9, 0)));

            //Agrego todos los empleados
            empleadoController.agregarListaEmpleado(empleados);
        }



        /* listar empleados: */
        //Creo un variable de lista de empleados para almacenar las listas resultantes de buscar los empleados
        List<Empleado> todosLosEmpleados;

        //uso una variable ya instanciada al principio para aprovechar la variable
        todosLosEmpleados = empleadoController.listarEmpleados();

        //he creado una clase en utilities relacionada con operaciones complementarias para la aplicación,
        // sirve para limpieza de código y mejor orden a la hora de repetir acciones
        OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);



        /* crear un empleado: */
        System.out.println("Crear un empleado");
        System.out.println("_________________");

        //instancio un nuevo empleado para introducirlo en la base de datos
        Empleado empleado11 = new Empleado("Lucía", "González", "Data Analyst", 38000.00, LocalDateTime.of(2022, 4, 15, 9, 0));

        //método para introducir empleado
        empleadoController.agregarEmpleado(empleado11);

        //voy a listar los empleados una vez introducido uno nuevo
        todosLosEmpleados = empleadoController.listarEmpleados();
        OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);



        /* Actualizar información de un empleado: */
        //primero voy a buscar un empleado, voy a utilizar una variable para ello
        int empleadoBuscar = 2;
        System.out.println("Buscar empleado con id: " + empleadoBuscar);
        System.out.println("_________________________________________");

        //creo un empleado vacío y uso un método para encontrarlo por id
        Empleado empleadoEncontrado = empleadoController.buscarEmpleado(empleadoBuscar);
        //imprimo el empleado encontrado
        System.out.println(empleadoEncontrado);

        //modificamos la información del empleado
        empleadoEncontrado.setNombre("Emilia");
        empleadoEncontrado.setApellido("Pardo");
        empleadoEncontrado.setCargo("Jefa de Recursos Humanos");
        empleadoEncontrado.setSalario(60000.00);
        empleadoEncontrado.setFechaInicio(LocalDateTime.of(2018, 5, 5, 10, 0));

        //lo inserto en la base de datos después de haber seteado sus valores
        empleadoController.actualizarEmpleado(empleadoEncontrado);

        //por último voy a volver a listar los empleados, para mostrar que el empleado con el id = 2 ha sido actualizado
        //para ello reaprovecho la variable de List de todos los empleados
        todosLosEmpleados = empleadoController.listarEmpleados();
        //lo imprimo con el método que he creado en el paquete utilities
        OperacionesEmpleado.iterarListaEmpleados(todosLosEmpleados);



        /* Eliminar empleado: */
        //creo una variable que contendrá el id del empleado a eliminar de la base de datos
        int empleadoEliminar = 11;
        //elimino el empleado
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
        System.out.println("Buscar empleado por cargo: " + cargo);
        System.out.println("___________________________________");
        //Uso el método para buscar por cargo
        List<Empleado> empleadosPorCargo = empleadoController.buscarEmpleadoCargo(cargo);

        //por último imprimo los empleados encontrados:
        //lo imprimo con el método que he creado en el paquete utilities
        OperacionesEmpleado.iterarListaEmpleados(empleadosPorCargo);
    }
}