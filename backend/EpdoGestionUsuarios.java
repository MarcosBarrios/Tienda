package backend;

import java.util.ArrayList;

import productos.Productos;
import uitextual.UIEmpleado;
import uitextual.UIGestionUsuarios;
import uitextual.UIMensajes;

/**
 * Implementa las funciones base de la interfaz de gestion de usuarios
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoGestionUsuarios extends Empleado {

	//Metodo constructor
	public EpdoGestionUsuarios(Usuarios usuarios, Productos productos, 
			int diaActual, int mesActual, int anoActual, String dni, 
			String nombre, String email, String usuario, String contrasena) {
		super(usuarios, productos, diaActual, mesActual, anoActual, 
				dni, nombre, email, usuario, contrasena);
	}

	/**
	 * Devuelve una lista con las operaciones almacenadas en
	 * un usuario
	 * 
	 * @param DNI del usuario con las operaciones
	 * 
	 * @return listaOperaciones lista con las operaciones del empleado
	 */
	public ArrayList<Operacion> obtenerOperaciones(String DNI){
		ArrayList<Operacion> listaOperaciones = new ArrayList<Operacion>();
		Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Empleado) {
				//Si el usuario encontrado es un empleado
				Empleado empleado = (Empleado) usuario;
				
				//Iteramos las operaciones y las anadimos a listaOperaciones
				for(int i = 0; i < empleado.obtenerNumeroOperaciones(); i++){
                    listaOperaciones.add(obtenerOperacion(i));
                }
			}else if(usuario instanceof Cliente) {
				//Si el usuario encontrado es un cliente
				Cliente cliente = (Cliente) usuario;
				FichaCliente fc = cliente.obtenerFichaCliente();
				
				//Iteramos las operaciones y las anadimos a listaOperaciones
				for(int i = 0; i < fc.obtenerNumeroOperaciones(); i++){
                    listaOperaciones.add(fc.obtenerOperacion(i));
				}
			}
		}
		return listaOperaciones;
	}
	
	public boolean aceptarSolicitud(String DNI, int numeroSolicitud) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				//Si el usuario encontrado es un cliente
				Cliente cliente = (Cliente) usuario;
				FichaCliente fc = cliente.obtenerFichaCliente();
				
				//Obtenemos la solicitud utilizando el numero de solicitud
		        Solicitud s = fc.obtenerSolicitud(numeroSolicitud, true);
		        
		        s.cambiarAceptada(true); //Aceptamos la solicitud
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Anade una solicitud a la ficha de un cliente. La solicitud 
	 * contiene una descripcion ademas de una booleana que indica si
	 * ha sido aceptada o no.
	 * 
	 * @param DNI del cliente al que anadir la solicitud
	 * @param descripcionSolicitud Descripcion de la solicitud a anadir
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean anadirSolicitud(String DNI, String descripcionSolicitud) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				//Si el usuario encontrado es un cliente
				Cliente cliente = (Cliente) usuario;
				FichaCliente fc = cliente.obtenerFichaCliente();
				
			 	//Creamos la solicitud
				Solicitud s = new Solicitud(false, fc, descripcionSolicitud);
        		s.asignarNumeroSolicitud(); //Asignamos su numero de solicitud correspondiente
                
                //Anadimos la solicitud a la ficha del cliente
                fc.anadirSolicitud(s);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Registra un empleado en la base de datos de usuarios del programa.
	 * 
	 * Para ello pregunta por una cadena tipoEmpleado que represente
	 * al tipo de empleado a registrar ademas de los datos que todo
	 * empleado tiene que tener asignados (DNI, nombre,...).
	 * 
	 * @param tipoEmpleado Tipo de empleado a registrar(Cajero, PostVenta,...)
	 * @param DNI del empleado a registrar.
	 * @param nombre Nombre del empleado a registrar
	 * @param email Email del empleado a registrar
	 * @param telefono Telefono del empleado a registrar
	 * @param domicilio Domicilio del empleado a registrar
	 * @param usuario Usuario que el empleado usara para iniciar sesion
	 * @param contrasena Contrasena que el empleado usara para iniciar sesion
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean registrarEmpleado(String tipoEmpleado, String DNI,
			String nombre, String email, String usuario, 
			String contrasena) {
		
		if(obtenerUsuarios().obtenerUsuario(DNI)==null) {
			//Si no existe ningun usuario registrado con el DNI especificado
			
			//Obtenemos un instancia de empleado segun la cadena tipoEmpleado
			//y le asignamos los datos obtenidos
			Empleado empleado = obtenerTipoEmpleado(tipoEmpleado,
					DNI, nombre, email, usuario, contrasena);
			
			//Anadimos el empleado a la base de datos de usuarios
			obtenerUsuarios().anadirUsuario(empleado);
			return true;
		}
		return false;
	}
	
	/**
	 * Devuelve un empleado segun la cadena tipoEmpleado con los
	 * datos especificados asignados.
	 * 
	 * @param tipoEmpleado Tipo de empleado a registrar(Cajero, PostVenta,...)
	 * @param DNI del empleado a registrar.
	 * @param nombre Nombre del empleado a registrar
	 * @param email Email del empleado a registrar
	 * @param telefono Telefono del empleado a registrar
	 * @param domicilio Domicilio del empleado a registrar
	 * @param usuario Usuario que el empleado usara para iniciar sesion
	 * @param contrasena Contrasena que el empleado usara para iniciar sesion
	 * 
	 * @return Empleado segun el tipo especificado con los datos asignados
	 */
	private Empleado obtenerTipoEmpleado(String tipoEmpleado, String DNI,
			String nombre, String email, String usuario, String contrasena) {
		if(tipoEmpleado.equalsIgnoreCase(UIMensajes.mGU_AnE_PostVenta())){
			//Si tipoEmpleado es "postventa"
            return new EpdoPostVenta(obtenerUsuarios(), obtenerProductos(), 
            		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual(),
            		DNI, nombre, email, usuario, contrasena);
        }else if(tipoEmpleado.equalsIgnoreCase(UIMensajes.mGU_AnE_Cajero())){
        	//Si tipoEmpleado es "cajero"
            return new EpdoCajero(obtenerUsuarios(), obtenerProductos(), 
            		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual(),
            		DNI, nombre, email, usuario, contrasena);
        }else if(tipoEmpleado.equalsIgnoreCase(UIMensajes.mGU_AnE_Financiacion())){
        	//Si tipoEmpleado es "financiacion"
        	 return new EpdoFinanciacion(obtenerUsuarios(), obtenerProductos(), 
             		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual(),
             		DNI, nombre, email, usuario, contrasena);
        }else if(tipoEmpleado.equalsIgnoreCase(UIMensajes.mGU_AnE_Tecnico())){
        	//Si tipoEmpleado es "tecnico"
        	 return new EpdoTecnico(obtenerUsuarios(), obtenerProductos(), 
             		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual(),
             		DNI, nombre, email, usuario, contrasena);
        }else if(tipoEmpleado.equalsIgnoreCase(UIMensajes.mGU_AnE_Comercial())){
        	//Si tipoempleado es "comercial"
        	 return new EpdoComercial(obtenerUsuarios(), obtenerProductos(), 
             		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual(),
             		DNI, nombre, email, usuario, contrasena);
        }
		return null;
	}
	
	/**
	 * Actualiza el nombre de un empleado cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del empleado a actualizar
	 * @param nuevoNombre Nuevo nombre del empleado
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarNombreEmpleado(String dni, String nuevoNombre) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Empleado) {
				Empleado empleado = (Empleado) usuario;
				
				//Actualizamos el nombre del empleado
				empleado.asignarNombreUsuario(nuevoNombre);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Actualiza el email de un empleado cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del empleado a actualizar
	 * @param nuevoEmail Nuevo email del empleado
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarEmailEmpleado(String dni, String nuevoEmail) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Empleado) {
				Empleado empleado = (Empleado) usuario;
				
				//Actualizamos el email del empleado
				empleado.asignarEmailUsuario(nuevoEmail);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Actualiza el usuario de un empleado cuyo dni coincida con el
	 * especificado.
	 * 
	 * Nota: El usuario es el nombre de la cuenta con la que empleado
	 * inicia sesion al programa.
	 * 
	 * @param dni DNI del empleado a actualizar
	 * @param nuevoUsuario Nuevo usuario del empleado
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarUsuarioEmpleado(String dni, String nuevoUsuario) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Empleado) {
				//Si el usuario encontrado es un empleado
				Empleado empleado = (Empleado) usuario;
				
				//Actualizamos el usuario del empleado
				empleado.asignarUsuario(nuevoUsuario);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve una lista con los empleados registrados en el programa
	 * 
	 * @return Util.obtenerListaEmpleados(obtenerUsuarios())
	 */
	public ArrayList<Empleado> obtenerListaEmpleados(){
		return Util.obtenerListaEmpleados(obtenerUsuarios());
	}

	/**
	 * Devuelve una lista con los clientes registrados en el programa
	 * 
	 * @return Util.obtenerListaClientes(obtenerUsuarios())
	 */
	public ArrayList<Cliente> obtenerListaClientes(){
		return Util.obtenerListaClientes(obtenerUsuarios());
	}
	
	/**
	 * Actualiza la contrasena de un empleado cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del empleado a actualizar
	 * @param nuevaContrasena Nueva contrasena del empleado
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarContrasenaEmpleado(String dni, String nuevaContrasena) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Empleado) {
				Empleado empleado = (Empleado) usuario;
				
				//Actualizamos la contrasena del empleado
				empleado.asignarContrasena(nuevaContrasena);
				return true;
			}
		}
		return false;
	}
	
	public UIEmpleado obtenerUI() {
		return new UIGestionUsuarios(this);
	}

}
