package org.antonio.utilities;

import org.antonio.entities.Empleado;

import java.util.List;

public class OperacionesEmpleado {
    //esta clase está creada para efectuar operaciones relacionadas con la aplicación y
    // además podría ser útil en diferentes partes del código

    //método para iterar empleados y mostrarlos por consola
    //además, lo creo como estático para no tener que instanciar y poder utilizar el método esta clase
    public static void iterarListaEmpleados(List<Empleado> todosLosEmpleados){
        System.out.println("Listado de empleados encontrados:");
        System.out.println("_________________________________");
        for(Empleado empleado : todosLosEmpleados){
            System.out.println(empleado);
        }
    }
}
