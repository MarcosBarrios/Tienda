package backend;


/**
 * Clase que implementa las funciones de los empleados comerciales.
 * 
 * Se encargará de modificar los estados de los productos de un
 * cliente mediante la creación de reportes.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoComercial extends Empleado{
    
    public EpdoComercial(String dni, String nombre, String email, String usuario, 
        String contraseña){
       super(dni, nombre, email, usuario, contraseña);
    }
    
}
