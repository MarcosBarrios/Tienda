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
    
    public EpdoFinanciacion(String nombre, String email, String usuario, 
        String contraseña){
        super(nombre, email, usuario, contraseña);
    }
    
    public void darAlta(Usuarios usuarios, Cliente cliente){
        usuarios.añadirUsuario(cliente);
    }
    
    public void actualizarNombreCliente(Usuarios usuarios, String nombreCliente, String nuevoNombre){
        Cliente c = (Cliente) usuarios.obtenerUsuario(nombreCliente);
        if(c!=null) c.asignarNombreUsuario(nuevoNombre);
    }
    
    public void actualizarEmailCliente(Usuarios usuarios, String emailCliente, String nuevoEmail){
        Cliente c = (Cliente) usuarios.obtenerUsuario(emailCliente);
        if(c!=null) c.asignarNombreUsuario(nuevoEmail);
    }

}
