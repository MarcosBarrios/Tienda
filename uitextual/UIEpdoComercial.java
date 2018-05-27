package uitextual;

import backend.EpdoComercial;

/**
 * Implementa las funciones de los empleados comerciales en la interfaz
 * textual.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoComercial extends UIEmpleado{

	//Metodo constructor
	public UIEpdoComercial(EpdoComercial comercial) {
		super(comercial);
	}

	public UIMenuEmpleado obtenerMenu() {
		return null;
	}
	
}
