package backend;

import productos.Productos;
import uitextual.UIEmpleado;
import uitextual.UIEpdoPostVenta;
import uitextual.UIMensajes;

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
    
    public EpdoPostVenta(Usuarios usuarios, Productos productos,
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
		return new UIEpdoPostVenta(this);
	}
    
    /**
	 * Devuelve una cadena para referenciar este tipo de empleado
	 */
	public String toString() {
		return UIMensajes.mGU_AnE_PostVenta();
	}
    
}

