package backend;

/**
 * Clase que implementa las funciones de los empleados de post-venta.
 * 
 * Entre sus funciones se incluye la capacidad para modificar la informacion
 * de los empleados y clientes en la base de datos del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoPostVenta extends Empleado{
    
    public EpdoPostVenta(String nombre, String email, String usuario, 
        String contraseña){
       super(nombre, email, usuario, contraseña);
    }
    
}

