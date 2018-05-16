package backend;


/**
 * Clase que implementa las funciones de los empleados de financiacion.
 * 
 * tendrá métodos para dar de alta a clientes en el sistema con sus datos 
 * personales y actualizar los datos de clientes del sistema.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoFinanciacion extends Empleado{
    
    public EpdoFinanciacion(String dni, String nombre, String email,
        String usuario, String contraseña){
        super(dni, nombre, email, usuario, contraseña);
    }
    
}
