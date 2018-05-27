package uitextual;

import backend.EpdoGestionUsuarios;
import backend.EpdoTecnico;
import backend.Empleado;
import backend.FichaReparacion;
import backend.Cliente;
import backend.Solicitud;
import backend.FichaCliente;
import backend.Operacion;
import backend.OperacionUsuario;
import backend.OperacionProducto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Parte funcional del menu de gestion de usuarios.
 * 
 *  A la hora de acceder al menu de gestion de usuarios el menu principal
 *  solo pedira la contrasena.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIGestionUsuarios extends UIEmpleado{
    
    //Metodo constructor
    public UIGestionUsuarios(EpdoGestionUsuarios usuario){
        super(usuario);
    }
    
    /**
     * Devuelve el empleado gestor usando la interfaz de gestion
     * de usuarios.
     * 
     * @return (EpdoGestionUsuarios) obtenerUsuario()
     */
    private EpdoGestionUsuarios obtenerGestor() {
    	return (EpdoGestionUsuarios) obtenerUsuario();
    }
    
    /**
     * Imprime el historial de operaciones de un usuario
     * 
     * @param DNI del usuario con el historial de operaciones
     */
    public void verHistorialUsuario(){
    	//"Introducir DNI del usuario"
        String DNI = formatearEntradaCadena(UIMensajes.mGU_VHU_IntroducirDNIUsuario(), true);
        
        //Iteramos todas las operaciones que tenga el cliente
        ArrayList<Operacion> listaOperaciones = obtenerGestor().obtenerOperaciones(DNI);
        Iterator<Operacion> itr = listaOperaciones.iterator();
        while(itr.hasNext()) {
        	Operacion operacion = itr.next();
        	
    		//Imprimimos los datos de la operacion siendo iterada
        	 System.out.println();
             System.out.print("\t" + UIMensajes.mGU_VHU_EmpleadoResponsable());
             if(operacion instanceof OperacionUsuario){
            	 //Si la operacion es de usuario
            	 OperacionUsuario opUsuario = (OperacionUsuario) operacion;
            	 System.out.print(" | " + UIMensajes.mGU_VHU_ClienteAfectado());
                 System.out.print(": " + opUsuario.obtenerUsuario().obtenerNombreUsuario());
             }else {
            	 //Si la operacion es de producto
            	 OperacionProducto opProducto = (OperacionProducto) operacion;
            	 System.out.print(" | " + UIMensajes.mC_LP_NumeroProducto());
                 System.out.print(": " + opProducto.obtenerNumeroProducto());
             }
             System.out.print(" | " + UIMensajes.mGU_VHU_FechaOperacion());
             System.out.print(": " + operacion.obtenerDia() + "/");
             System.out.print(operacion.obtenerMes() + "/");
             System.out.print(operacion.obtenerAno());
             System.out.println();
             System.out.print("\t" + UIMensajes.mC_AnP_Descripcion() + ": ");
             System.out.print(operacion.obtenerDescripcion());
        }
    }
    
    /**
     * Anade una solicitud sobre un cliente
     * 
     */
    public void anadirSolicitud(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
                
        //Obtenemos la descripcion de la solicitud
        String descripcionSolicitud = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
        
        //Anadimos la solicitud a la ficha del cliente cuyo dni es el especificado
        boolean anadido = obtenerGestor().anadirSolicitud(dniCliente, descripcionSolicitud);
        if(anadido) {
        	//"Solicitud anadida con exito"
            System.out.println(UIMensajes.mGU_AS_SolicitudAnadida());
        }else {
        	//"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Acepta una solicitud utilizando su respectivo numero de solicitud
     * 
     */
    public void aceptarSolicitud(){
        //"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
                
        //"Indicar el numero de la solicitud a aceptar"
        int numeroSolicitud = (int)formatearEntradaDecimal(UIMensajes.mGU_AS_IndicarNumeroSolicitud());
        
        //Anadimos la solicitud a la ficha del cliente cuyo dni es el especificado
        boolean aceptada = obtenerGestor().aceptarSolicitud(dniCliente, numeroSolicitud);
        if(aceptada) {
        	//"Solicitud aceptada"
            System.out.println(UIMensajes.mGU_VLS_SolicitudAceptada());
        }else {
        	//"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
        
    }
    
    /**
     * Imprime una lista de solicitudes de todos los clientes
     * 
     */
    public void verListaSolicitudes(){
    
    	//Obtenemos la lista de clientes e imprimimos sus datos
        ArrayList<Cliente> listaClientes = obtenerGestor().obtenerListaClientes();
        Iterator<Cliente> itr = listaClientes.iterator();
        while(itr.hasNext()) {
        	Cliente cliente = itr.next();
        	FichaCliente fc = cliente.obtenerFichaCliente();
            
            //Imprimimos el nombre del cliente
            System.out.println();
            System.out.print("\t" + UIMensajes.g_Nombre()+": ");
            System.out.print(cliente.obtenerNombreUsuario());
            
            //Iteramos todas las solicitudes del cliente
            for(int j = 0; j < fc.obtenerNumeroSolicitudes(); j++){
                //Obtenemos una solicitud mediante posicion en lista de solicitudes
                Solicitud s = fc.obtenerSolicitud(0, false);
                
                //Imprimimos el estado seguido de la descripcion
                System.out.println();
                if(s.obtenerAceptada()){
                    //"Solicitud aceptada"
                    System.out.print("\t" + UIMensajes.mGU_VLS_SolicitudAceptada());
                }else{
                    //"Solicitud pendiente"
                    System.out.print("\t" + UIMensajes.mGU_VLS_SolicitudPendiente());
                }
                System.out.print(" | ");
                System.out.print(UIMensajes.mC_AnP_Descripcion()+": ");
                System.out.print(s.obtenerDescripcion());
            }
        }
    }
    
    /**
     * Imprime una lista con todas las fichas de reparacion y sus
     * tecnicos asignados.
     * 
     */
    public void verFichasReparacion(){
    	//Obtenemos la lista de empleados e imprimimos sus datos
        ArrayList<Empleado> listaEmpleados = obtenerGestor().obtenerListaEmpleados();
        Iterator<Empleado> itr = listaEmpleados.iterator();
        while(itr.hasNext()) {
        	Empleado empleado = itr.next();
        	if(empleado instanceof EpdoTecnico) {
        		EpdoTecnico tecnico = (EpdoTecnico) empleado;
        		
        		//Imprimimos el nombre del tecnico
                System.out.println();
                System.out.print(UIMensajes.g_Nombre() + ": ");
                System.out.print(tecnico.obtenerNombreUsuario());
                for(int j = 0; j < tecnico.obtenerNumeroFichas(); j++){
                    //Imprimimos el nombre del propietario y el numero del producto
                    FichaReparacion fr = tecnico.obtenerFichaReparacion(j);
                    System.out.print(UIMensajes.g_Nombre() + ": ");
                    System.out.print(fr.obtenerPropietario().obtenerNombreUsuario() + " | ");
                    System.out.print(UIMensajes.mC_LP_NumeroProducto() + ": ");
                    System.out.print(fr.obtenerProducto().obtenerNumeroProducto());
                }
        	}
        }
    }
    
    /**
     * Registra un empleado en la base de datos de usuarios del programa
     * 
     */
    public void registrarEmpleado(){
        //"Especificar a continuacion el nombre, email, usuario" +
        //"y la contrasena del empleado a registrar"
        System.out.println(UIMensajes.mGU_EspecificarDatos());
        
        //Creamos un formulario para las entradas no complejas
        String[] entradas = new String[7];
        entradas[0] = UIMensajes.g_DNI();
        entradas[1] = UIMensajes.g_Nombre();
        entradas[2] = UIMensajes.g_Email();
        entradas[3] = UIMensajes.g_Usuario();
        entradas[4] = UIMensajes.g_Contrasena();
        String[] salidas = formularioCadenas(entradas);
        
        //Obtenemos una cadena que represente el tipo de empleado
        //a registrar
        String tipoEmpleado = obtenerCadenaTipoEmpleado();
        
        boolean registrado = obtenerGestor().registrarEmpleado(tipoEmpleado,
        		salidas[0], salidas[1], salidas[2], salidas[3], salidas[4]);
        if(registrado) {
        	//"Empleado registrado con exito"
            System.out.println(UIMensajes.mGU_AnE_EmpleadoRegistrado());
        }else {
        	//"Ya existe un usuario con los datos especificados"
            System.out.println(UIMensajes.mGU_AnE_UsuarioYaExistente());
        }
    }
    
    /**
     * Devuelve una cadena que representa a un tipo de empleado.
     * 
     * @return Cadena que representa a un tipo de empleado
     */
    private String obtenerCadenaTipoEmpleado() {
    	ArrayList<String> listaTiposEmpleado = new ArrayList<String>();
    	listaTiposEmpleado.add(UIMensajes.mGU_AnE_PostVenta());
    	listaTiposEmpleado.add(UIMensajes.mGU_AnE_Cajero());
    	listaTiposEmpleado.add(UIMensajes.mGU_AnE_Financiacion());
    	listaTiposEmpleado.add(UIMensajes.mGU_AnE_Tecnico());
    	listaTiposEmpleado.add(UIMensajes.mGU_AnE_Comercial());
    	return UIEntradas.obtenerCadenaLimitada(listaTiposEmpleado, false);
    }
    
    /**
     * Actualiza los datos de un empleado de la tienda.
     */
    public void actualizarDatosEmpleado(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Creamos e imprimimos un menu para que el usuario elija el dato
        //del empleado que quiere modificar
        menuModificacionDatoUsuario(dniCliente);
    }

	/**
	 * Utiliza un menu para que el usuario pueda elegir el dato 
	 * del empleado que quiere modificar. (nombre, email,...)
	 * 
	 * @param DNI del empleado a modificar
	 * 
	 */
	private void menuModificacionDatoUsuario(String dni) {
		//Menu con una opcion por cada dato del empleado
		//que se pueden modificar
		UIMenu menuOpciones = anadirOpcionesModificacion();
		
		menuOpciones.imprimirOpciones(); //Imprime las opciones
		
		//Obtenemos el numero de opcion elegida por el usuario
        int entrada = UIEntradas.obtenerEntero(0, menuOpciones.obtenerNumeroOpciones());
        
        //Implementacion de cada opcion del menu
        implementacionMenuOpciones(entrada, dni);
	}
	
	/**
	 * Implementa las opciones del menu para modificar los datos
	 * de un empleado.
	 * 
	 * @param entrada Opcion del menu elegida
	 * @param dni DNI del empleado a modificar
	 */
	private void implementacionMenuOpciones(int entrada, String dni) {
		boolean actualizado = false;
		switch(entrada) {
			case 0: //Nombre
				String nuevoNombre = formatearEntradaCadena(UIMensajes.g_Nombre(),  true);
				actualizado = obtenerGestor().actualizarNombreEmpleado(dni, nuevoNombre);
				break;
				
			case 1: //Email
				String nuevoEmail = formatearEntradaCadena(UIMensajes.g_Email(),  true);
				actualizado = obtenerGestor().actualizarEmailEmpleado(dni, nuevoEmail);
				break;
				
			case 2: //Usuario
				String nuevoUsuario = formatearEntradaCadena(UIMensajes.g_Usuario(),  true);
				actualizado = obtenerGestor().actualizarUsuarioEmpleado(dni, nuevoUsuario);
				break;
				
			case 3: //Contrasena
				String nuevaContrasena = formatearEntradaCadena(UIMensajes.g_Contrasena(),  true);
				actualizado = obtenerGestor().actualizarContrasenaEmpleado(dni, nuevaContrasena);
				break;
		}
		if(actualizado) {
			//"Empleado actualizado con exito"
        	System.out.println(UIMensajes.mGU_AcE_EmpleadoActualizado());
		}else {
			//"Empleado no encontrado"
        	System.out.println(UIMensajes.mGU_AnE_EmpleadoNoEncontrado());
		}
	}
	
	/**
	 * Devuelve un menu con opciones para modificar los
	 * datos de un empleado: nombre, email, telefono,
	 * domicilio, usuario y contrasena.
	 * 
	 * @return Menu con opciones para modificar los datos de un empleado
	 */
	private UIMenu anadirOpcionesModificacion() {
		UIMenu menuModOpciones = new UIMenu();
        
        menuModOpciones.anadirOpcion(UIMensajes.g_Nombre());
        menuModOpciones.anadirOpcion(UIMensajes.g_Email());
        menuModOpciones.anadirOpcion(UIMensajes.g_Usuario());
        menuModOpciones.anadirOpcion(UIMensajes.g_Contrasena());
        
        return menuModOpciones;
	}
		
    
    /**
     * Imprime una lista con los empleados de la tienda.
     * 
     */
    public void imprimirListaEmpleados(){
        System.out.println();
        System.out.print("\t" + UIMensajes.g_Nombre() + "  " +
            UIMensajes.g_Email() + "  " + 
            UIMensajes.g_Usuario() + "  " +
            UIMensajes.g_Contrasena());
        
        //Obtenemos la lista de empleados e imprimimos sus datos
        ArrayList<Empleado> listaEmpleados = obtenerGestor().obtenerListaEmpleados();
        Iterator<Empleado> itr = listaEmpleados.iterator();
        while(itr.hasNext()) {
        	Empleado empleado = itr.next();
        	
        	//Imprimimos dni, nombre, email, usuario y contrasena.
            System.out.println();
            System.out.print("\t" + UIMensajes.g_DNI() + ": ");
            System.out.println(empleado.obtenerDNI());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Nombre() + ": ");
            System.out.print(empleado.obtenerNombreUsuario());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Email() + ": ");
            System.out.print(empleado.obtenerEmailUsuario());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Usuario() + ": ");
            System.out.print(empleado.obtenerUsuario());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Contrasena() + ": ");
            System.out.print(empleado.obtenerContrasena());
        }
        System.out.println();
    }
    
    /**
     * Imprime una lista con los empleados de la tienda.
     * 
     */
    public void imprimirListaClientes(){
        System.out.println();
        System.out.print("\t" + UIMensajes.g_Nombre() + "  " +
            UIMensajes.g_Email() + "  " + 
            UIMensajes.g_Usuario() + "  " +
            UIMensajes.g_Contrasena());
        
        //Obtenemos la lista de clientes e imprimimos sus datos
        ArrayList<Cliente> listaClientes = obtenerGestor().obtenerListaClientes();
        Iterator<Cliente> itr = listaClientes.iterator();
        while(itr.hasNext()) {
        	Cliente cliente = itr.next();
        	
        	//Imprimimos dni, nombre y email
            System.out.println();
            System.out.print("\t" + UIMensajes.g_DNI() + ": ");
            System.out.print(cliente.obtenerDNI());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Nombre() + ": ");
            System.out.print(cliente.obtenerNombreUsuario());
            System.out.print(" | ");
            System.out.print(UIMensajes.g_Email() + ": ");
            System.out.print(cliente.obtenerEmailUsuario());
        }
        System.out.println();
    }
    
    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuGestionUsuarios(this);
	}
}
