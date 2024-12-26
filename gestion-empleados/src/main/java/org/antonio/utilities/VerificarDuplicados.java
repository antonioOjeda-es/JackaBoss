package org.antonio.utilities;

import org.antonio.controllers.EmpleadoController;
import org.antonio.entities.Empleado;

import java.util.List;

public class VerificarDuplicados {

    public static boolean VerificarDuplicadosCadenaTexto(String nombre, String apellidos){
        //variable que verifica si el nombre y el apellido están vacíos
        boolean duplicado = false;

        //voy a crear una instancia de EmpleadoController
        EmpleadoController empleadoController = new EmpleadoController();

        //traigo la lista completa de empleados en una Arraylist
        List<Empleado> listaEmpleados = empleadoController.listarEmpleados();

        for (Empleado empleado : listaEmpleados){
            if(empleado.getNombre().equalsIgnoreCase(nombre) && empleado.getApellido().equalsIgnoreCase(apellidos)){
                duplicado = true;
                break;
            }
        }
        return duplicado;
    }
}
