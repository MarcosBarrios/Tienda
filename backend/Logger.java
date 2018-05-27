package backend;

/**
 * Clase encargada de implementar las funciones de inicio de sesion
 * de empleados.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Logger {

	//Base de datos de usuarios
	private Usuarios usuarios;
	
	//Metodo constructor
	public Logger(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Devuelve la base de datos de usuarios
	 * 
	 * @return usuarios Base de datos de usuarios
	 */
	private Usuarios obtenerUsuarios() {
		return usuarios;
	}
	
	/**
	 * Devuelve un empleado cuyos datos coincidan con los especificados.
	 * 
	 * @param usuario Usuario de la cuenta del empleado
	 * @param contrasena Contrasena de la cuenta del empleado
	 * @return Empleado con los datos de inicio de sesion especificados
	 */
	public Empleado comprobarEmpleado(String usuario, String contrasena) {
		return Util.buscarCuentaEmpleado(obtenerUsuarios(), usuario, contrasena);
	}
	
}
