package backend;

import productos.Productos;
import uitextual.UIEmpleado;
import uitextual.UIEpdoFinanciacion;
import uitextual.UIMensajes;

/**
 * Clase que implementa las funciones de los empleados de financiacion.
 * 
 * tendra metodos para dar de alta a clientes en el sistema con sus datos 
 * personales y actualizar los datos de clientes del sistema.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoFinanciacion extends Empleado{
    
    public EpdoFinanciacion(Usuarios usuarios, Productos productos,
    		int diaActual, int mesActual, int anoActual, String dni,
    		String nombre, String email, String usuario, String contrasena){
        super(usuarios, productos, diaActual, mesActual, anoActual,
        		dni, nombre, email, usuario, contrasena);
    }
    
    public void darAltaCliente(Cliente cliente) {
    	//Anadimos el cliente a la base de datos de clientes
        obtenerUsuarios().anadirUsuario(cliente);
        
        //Dejamos constancia de la operacion realizada
        dejarConstancia(cliente, EnumOperaciones.mF_DARALTACLIENTE,
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Devuelve la clase que implementa las funciones para este empleado
     * 
     * @return UIEmpleado clase UI para este empleado
     */
	public UIEmpleado obtenerUI() {
		return new UIEpdoFinanciacion(this);
	}
    
    /**
	 * Devuelve una cadena para referenciar este tipo de empleado
	 */
	public String toString() {
		return UIMensajes.mGU_AnE_Financiacion();
	}
}
