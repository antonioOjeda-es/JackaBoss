package org.antonio.controllers;

import org.antonio.entities.Empleado;
import org.antonio.persistence.EmpleadoJPA;

import java.util.List;

public class EmpleadoController {
    //aquí se encuentran los métodos que se encuentran en este controlador para gestionar a los empleados

    //para ello, creo una instancia para que este use sus métodos y se comunique con la base de datos
    EmpleadoJPA empleadoJPA = new EmpleadoJPA();


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
