package backend;


/**
 * Define una operacion sobre un empleado.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class OperacionEmpleado extends Operacion{
    
    //Empleado sobre le cual se ha hecho la operacion
    public Empleado empleado;
    
    //Metodo constructor
    public OperacionEmpleado(Empleado empleado){
        this.empleado = empleado;
    }
    
    /**
     * Asigna o reasigna el empleado sobre el cual se hace
     * la operacion
     * 
     * @param empleado Empleado asignado a la operacion
     */
    public void asignarEmpleado(Empleado empleado){
        this.empleado = empleado;
    }
    
    /**
     * Devuelve el empleado asignado a la operacion
     * 
     * @return empleado Empleado asignado a la operacion
     */
    public Empleado obtenerEmpleado(){
        return empleado;
    }
    
}
