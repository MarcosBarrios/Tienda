package backend;

import productos.Productos;
import uitextual.UIEmpleado;
import uitextual.UIEpdoComercial;
import uitextual.UIMensajes;

/**
 * Implementa la parte funcional de la interfaz de los empleados comerciales
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoComercial extends Empleado{
    
	//Metodo constructor
    public EpdoComercial(Usuarios usuarios, Productos productos,
			int diaActual, int mesActual, int anoActual, String dni, 
			String nombre, String email, String usuario, String contrasena){
       super(usuarios, productos, diaActual, mesActual, anoActual, dni, 
       		nombre, email, usuario, contrasena);
    }
    
    /**
     * Devuelve la clase que implementa las funciones para este empleado
     * 
     * @return UIEmpleado clase UI para este empleado
     */
	public UIEmpleado obtenerUI() {
		return new UIEpdoComercial(this);
	}
    
    /**
	 * Devuelve una cadena para referenciar este tipo de usuario
	 */
	public String tipoUsuario() {
		return UIMensajes.mGU_AnE_Comercial();
	}
	
	/**
	 * Devuelve una cadena con la informacion del comercial
	 */
	@Override
	public String toString(){
		return  "\t" + UIMensajes.g_TipoUsuario() + ": " + tipoUsuario() +
				" | " + UIMensajes.g_DNI() + ": " + obtenerDNI() +
				" | " + UIMensajes.g_Nombre() + ": " + obtenerNombreUsuario() +
				" | " + UIMensajes.g_Email() + ": " + obtenerEmailUsuario();
	}
}
