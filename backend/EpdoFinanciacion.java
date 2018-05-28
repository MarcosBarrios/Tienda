package backend;

import java.util.ArrayList;

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
    
	//Metodo constructor
    public EpdoFinanciacion(Usuarios usuarios, Productos productos,
    		int diaActual, int mesActual, int anoActual, String dni,
    		String nombre, String email, String usuario, String contrasena){
        super(usuarios, productos, diaActual, mesActual, anoActual,
        		dni, nombre, email, usuario, contrasena);
    }
    
    /**
     * Da de alta un cliente siempre y cuando no se haya registrado
     * previamente un usuario en la tienda con el DNI especificado.
     * 
     * @param DNI del cliente
     * @param nombre del cliente
     * @param email del cliente
     * @param telefono del cliente
     * @param domicilio del cliente 
     * 
     * @return Verdadero si se ha dado de alta al cliente con exito
     */
    public boolean darAltaCliente(String DNI, String nombre,
    		String email, String telefono, String domicilio) {
    	Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
    	if(usuario==null) {
    		//Si no se ha registrado ya un usuario con el DNI especificado
    		
    		//Creamos el cliente con los datos especificados
    		Cliente cliente = new Cliente(DNI, nombre, email, telefono, domicilio);
    		
    		//Dejamos constancia de la operacion realizada
            dejarConstancia(cliente, EnumOperaciones.mF_DARALTACLIENTE,
            		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
            return true;
    	}
    	return false;
    }
    
    /**
	 * Actualiza el nombre de un cliente cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del cliente a actualizar
	 * @param nuevoNombre Nuevo nombre del cliente
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarNombreCliente(String dni, String nuevoNombre) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				
				//Dejamos constancia de la operacion en el historial
				dejarConstancia(cliente, EnumOperaciones.mF_ACTUALIZARCLIENTE,
						obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Actualizamos el nombre del cliente
				cliente.asignarNombreUsuario(nuevoNombre);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Actualiza el email de un cliente cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del cliente a actualizar
	 * @param nuevoEmail Nuevo email del cliente
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarEmailCliente(String dni, String nuevoEmail) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				
				//Dejamos constancia de la operacion en el historial
				dejarConstancia(cliente, EnumOperaciones.mF_ACTUALIZARCLIENTE,
						obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Actualizamos el email del cliente
				cliente.asignarEmailUsuario(nuevoEmail);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Actualiza el telefono de un cliente cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del cliente a actualizar
	 * @param nuevoTelefono Nuevo telefono del cliente
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarTelefonoCliente(String dni, String nuevoTelefono) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				
				//Dejamos constancia de la operacion en el historial
				dejarConstancia(cliente, EnumOperaciones.mF_ACTUALIZARCLIENTE,
						obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Actualizamos el telefono del cliente
				cliente.asignarTelefono(nuevoTelefono);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene un array con los datos de un cliente
	 * 
	 * @param DNI del cliente
	 * 
	 * @return Array con los datos de un cliente
	 */
	public String[] obtenerDatosCliente(String DNI) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				
				//Dejamos constancia de la operacion en el historial
				dejarConstancia(cliente, EnumOperaciones.mF_IMPRIMIRCLIENTE,
						obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Obtenemos los datos del cliente
				String[] datos = new String[5];
				datos[0] = cliente.obtenerDNI();
				datos[1] = cliente.obtenerNombreUsuario();
				datos[2] = cliente.obtenerEmailUsuario();
				datos[3] = cliente.obtenerTelefono();
				datos[4] = cliente.obtenerDomicilio();
				return datos;
			}
		}
		return null;
	}
	
	public ArrayList<Factura> obtenerFacturasCliente(String DNI){
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		
		//Obtenemos un cliente cuyo dni sea igual a DNI
		Usuario usuario = obtenerUsuarios().obtenerUsuario(DNI);
		if(usuario!=null) {
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				FichaCliente fc = cliente.obtenerFichaCliente();
				
				//Dejamos constancia de la operacion en el historial
                dejarConstancia(cliente, EnumOperaciones.mF_IMPRIMIRCLIENTE,
                		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Guardamos todas las facturas del cliente en listaFacturas
				for(int i = 0; i < fc.obtenerNumeroFacturas(); i++){
					listaFacturas.add(fc.obtenerFactura(i));
				}
			}
		}
		
		return listaFacturas;
	}
	
	/**
	 * Actualiza el domicilio de un cliente cuyo dni coincida con el
	 * especificado.
	 * 
	 * @param dni DNI del cliente a actualizar
	 * @param nuevoDomicilio Nuevo domicilio del cliente
	 * 
	 * @return Verdadero si la operacion se ha realizado con exito
	 */
	public boolean actualizarDomicilioCliente(String dni, String nuevoDomicilio) {
		Usuario usuario = obtenerUsuarios().obtenerUsuario(dni);
		if(usuario!=null) {
			//Si existe un usuario registrado con el DNI especificado
			if(usuario instanceof Cliente) {
				Cliente cliente = (Cliente) usuario;
				
				//Dejamos constancia de la operacion en el historial
				dejarConstancia(this, EnumOperaciones.mF_ACTUALIZARCLIENTE,
						obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				//Actualizamos el domicilio del cliente
				cliente.asignarDomicilio(nuevoDomicilio);
				return true;
			}
		}
		return false;
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
