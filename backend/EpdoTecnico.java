package backend;


/**
 * Clase que implementa las funciones de los empleados tecnicos.
 * 
 * Empleado encargado de la reparación de productos.
 * 
 * @author Marcos Barrios 
 * @version 1.0
 */
public class EpdoTecnico extends Empleado{
    
    public EpdoTecnico(String dni, String nombre, String email, String usuario, 
        String contraseña){
       super(dni, nombre, email, usuario, contraseña);
    }
}
