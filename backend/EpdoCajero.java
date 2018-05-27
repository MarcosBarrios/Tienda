package backend;

import productos.Productos;

import uitextual.UIEmpleado;
import uitextual.UIEpdoCajero;
import uitextual.UIMensajes;

/**
 * Los cajeros son los encargados de trabajar con los diferentes productos.
 * 
 * Entre sus tareas estan la de ordenar los electrodomesticos, asegurarse de que
 * siempre hay electrodomesticos, cobrar a los clientes, resolver dudas de
 * clientes, etc...
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoCajero extends Empleado {

	// Indica el numero de cada en la que se encuentra
	private int numeroCaja;

	// Metodo constructor
	public EpdoCajero(Usuarios usuarios, Productos productos,
			int diaActual, int mesActual, int anoActual, String dni, 
			String nombre, String email, String usuario, String contrasena){
        super(usuarios, productos, diaActual, mesActual, anoActual, dni, 
        		nombre, email, usuario, contrasena);
    }

	/**
	 * Asigna el numero de caja en el que se encuentra operando el cajero.
	 * 
	 * @param numeroCaja
	 *            Numero de caja en el que va a operar el cajero
	 */
	public void asignarNumeroCaja(int numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	/**
	 * Devuelve el numero de caja en el que se encuentra operando el cajero
	 * 
	 * @return numeroCaja Numero de caja en el que se encuentra
	 */
	public int obtenerNumeroCaja() {
		return numeroCaja;
	}
	
	/**
     * Devuelve la clase que implementa las funciones para este empleado
     * 
     * @return UIEmpleado clase UI para este empleado
     */
	public UIEmpleado obtenerUI() {
		return new UIEpdoCajero(this);
	}
	
	/**
	 * Devuelve una cadena para referenciar este tipo de empleado
	 */
	public String toString() {
		return UIMensajes.mGU_AnE_Cajero();
	}

}