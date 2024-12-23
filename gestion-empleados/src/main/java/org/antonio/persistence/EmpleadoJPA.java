package org.antonio.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.antonio.entities.Empleado;

import java.util.List;

public class EmpleadoJPA {

    //aquí van todos los métodos para comunicarnos con la base de datos:

    //uso este método para agregar una lista de Empleados
    public void addAll(List<Empleado> empleados) {

        //itero la lista para agregar a cada empleado por cada iteración de la Lista creada en el método Main
        for (Empleado empleado : empleados) {

            //usar try cuando pueda haber riesgo en este fragmento de código y poder hacer rollback si algo sale mal.
            //hay que usar config.jpa.get.getEntityManager en cada método para comunicarme con la base de datos.
            EntityManager em = ConfigJPA.getEntityManager();
            try {
                // guardar algo en la DB, usamos begin y commit cuando se va a producir algún cambio en la base de datos
                em.getTransaction().begin(); // Iniciar una transaccion
                em.persist(empleado);//método de la interfaz EntityManager para guardar en base de datos
                em.getTransaction().commit();//guarda persistencia si todo ha salido bien

            } finally {
                //si todo ha salido bien, se cierra la conexión.
                em.close();
            }
        }
    }

    //método para listar todos los empleados
    public List<Empleado> listarEmpleados() {

        EntityManager em = ConfigJPA.getEntityManager();

        try {
            //en este caso no hay que iniciar transaction puesto que no vamos a hacer cambios en la base de datos
            //hay que crear una query personalizada ya que jpa no dispone de un método específico para ello
            TypedQuery<Empleado> query = em.createQuery("SELECT p FROM empleados p", Empleado.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    //método para agregar un empleado
    public void agregarEmpleado(Empleado empleado) {

        EntityManager em = ConfigJPA.getEntityManager();
        try {
            // guardar algo en la DB, usamos begin y commit cuando se va a producir algún cambio en la base de datos
            em.getTransaction().begin(); // Iniciar una transaccion
            em.persist(empleado);//método de la interfaz EntityManager para guardar en base de datos
            em.getTransaction().commit();//guarda persistencia si todo ha salido bien

        } finally {
            //si todo ha salido bien, se cierra la conexión.
            em.close();
        }
    }

    //método para actualizar un empleado
    public void actualizarEmpleado(Empleado empleado) {

        EntityManager em = ConfigJPA.getEntityManager();
        try {
            // guardar algo en la DB, usamos begin y commit cuando se va a producir algún cambio en la base de datos
            em.getTransaction().begin(); // Iniciar una transaccion
            em.merge(empleado);//método de la interfaz EntityManager para guardar en base de datos
            em.getTransaction().commit();//guarda persistencia si todo ha salido bien

        } finally {
            //si todo ha salido bien, se cierra la conexión.
            em.close();
        }
    }

    //buscar un empleado por su id
    public Empleado buscarEmpleado(int empleadoBuscar) {
        EntityManager em = ConfigJPA.getEntityManager();

        //voy a crear una instancia vacía para devolverlo después
        Empleado empleadoEncontrado = new Empleado();

        try {
            //se introduce la clase y el parámetro para buscar
            empleadoEncontrado = em.find(Empleado.class, empleadoBuscar);
            if (empleadoEncontrado == null) {
                System.out.println("Empleado con el id " + empleadoBuscar +
                        " no existe en la base de datos de empleados");
            } else {
                System.out.println("Empleado encontrado");
                System.out.println("___________________");
            }
        } finally {
            //si todo ha salido bien, se cierra la conexión.
            em.close();
        }
        return empleadoEncontrado;
    }

    //eliminar un empleado por su id
    public void eliminarEmpleado(Integer idEmpleadoEliminar) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin(); // Iniciar una transaccion
            Empleado empleadoEliminar = em.find(Empleado.class, idEmpleadoEliminar);
            if (empleadoEliminar != null) {
                em.remove(empleadoEliminar);
            } else {
                System.err.println("El id del empleado " + idEmpleadoEliminar + " No existe");
            }
            em.getTransaction().commit();
        } finally {
            System.out.println("Empleado eliminado de la base de datos");
            em.close();
        }
    }

    //método para listar todos los empleados
    public List<Empleado> buscarEmpleadoCargo(String cargo) {
        EntityManager em = ConfigJPA.getEntityManager();

        try {
            //en este caso no hay que iniciar transaction puesto que no vamos a hacer cambios en la base de datos
            //hay que crear una query personalizada ya que jpa no dispone de un método específico para ello
            //SQL: no distingue mayusculas y minúsculas
            TypedQuery<Empleado> query = em.createQuery("SELECT p FROM empleados p where p.cargo=?1",
                    Empleado.class);
            //seteo el parámetro de ?1 de la query persoalizada para que agregue la variable cargo en ella
            query.setParameter(1, cargo);
            List<Empleado> empleadosEncontrados = query.getResultList();

            //Si la lista no está vacía, la devuelvo
            if (!empleadosEncontrados.isEmpty()) {
                return empleadosEncontrados;
            } else {
                System.err.println("No existen empleados con este cargo: " + cargo);
                return empleadosEncontrados;//se devuelve la lista vacía
            }

        } finally {
            em.close();
        }
    }
}
