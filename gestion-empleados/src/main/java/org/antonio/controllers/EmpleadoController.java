package org.antonio.controllers;

import org.antonio.entities.Empleado;
import org.antonio.persistence.EmpleadoJPA;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {
    //aquí se encuentran lo métodos que se encontrarán en este controlador para gestionar a los empleados

    //para ello, creo una instancia de Empleado.JPA para que este use sus métodos y se comunique con la base de datos
    EmpleadoJPA empleadoJPA = new EmpleadoJPA();

    //uso em método de agregar Empleado para que se agregue automáticamente a la lista de empleado
    public void agregarListaEmpleado(List<Empleado> empleados) {

        empleadoJPA.addAll(empleados);

        System.out.println("Todos los empleados creados han sido agregados");
    }

    public List<Empleado> listarEmpleados() {
        return empleadoJPA.listarEmpleados();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleadoJPA.agregarEmpleado(empleado);
    }

    public Empleado buscarEmpleado(int empleadoBuscar) {
        return empleadoJPA.buscarEmpleado(empleadoBuscar);
    }

    public void actualizarEmpleado(Empleado empleado){
        empleadoJPA.actualizarEmpleado(empleado);
    }

    public void eliminarEmpleado(int idEmpleadoEliminar){
        empleadoJPA.eliminarEmpleado(idEmpleadoEliminar);
    }

    public List<Empleado> buscarEmpleadoCargo(String cargo){
        return empleadoJPA.buscarEmpleadoCargo(cargo);
    }
}
