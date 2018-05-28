package uitextual;

import backend.EpdoFinanciacion;
import backend.Factura;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Dispone de metodos para dar de alta a clientes en el sistema con sus 
 * datos personales y actualizar los datos de clientes del sistema.
 * 
 * Los metodos estan orientados a la parte textual de la interfaz de
 * usuario del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoFinanciacion extends UIEmpleado{
    
    //Metodo constructor
    public UIEpdoFinanciacion(EpdoFinanciacion usuario){
        super(usuario);
    }
    
    /**
     * Devuelve el financiador
     * 
     * @return (EpdoFinanciacion)obtenerUsuario()
     */
    private EpdoFinanciacion obtenerFinanciador(){
        return (EpdoFinanciacion) obtenerUsuario();
    }
    
    /**
     * Actualiza los datos de un cliente mediante interrogacion
     * de datos en forma de interfaz textual.
     * 
     * @param usuarios Base de datos de usuarios
     */
    public void actualizarDatosCliente(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Creamos e imprimimos un menu para que el usuario elija el dato
        //del cliente que quiere modificar
        menuModificacionDatoCliente(dniCliente);
            
    }
    
    /**
	 * Utiliza un menu para que el usuario pueda elegir el dato 
	 * del cliente que quiere modificar. (nombre, email,...)
	 * 
	 * @param DNI del cliente a modificar
	 * 
	 */
    private void menuModificacionDatoCliente(String DNI) {
    	//Menu con una opcion por cada dato del cliente
		//que se pueden modificar
		UIMenu menuOpciones = anadirOpcionesModificacion();
		
		menuOpciones.imprimirOpciones(); //Imprime las opciones
		
		//Obtenemos el numero de opcion elegida por el usuario
        int entrada = UIEntradas.obtenerEntero(0, menuOpciones.obtenerNumeroOpciones());
        
        //Implementacion de cada opcion del menu
        implementacionMenuOpciones(entrada, DNI);
    }
    
    /**
	 * Devuelve un menu con opciones para modificar los
	 * datos de un empleado: nombre, email, telefono y
	 * domicilio.
	 * 
	 * @return Menu con opciones para modificar los datos de un cliente
	 */
	private UIMenu anadirOpcionesModificacion() {
		UIMenu menuModOpciones = new UIMenu();
        
        menuModOpciones.anadirOpcion(UIMensajes.g_Nombre());
        menuModOpciones.anadirOpcion(UIMensajes.g_Email());
        menuModOpciones.anadirOpcion(UIMensajes.g_Telefono());
        menuModOpciones.anadirOpcion(UIMensajes.g_Domicilio());
        
        return menuModOpciones;
	}
	
	/**
	 * Implementa las opciones del menu para modificar los datos
	 * de un cliente.
	 * 
	 * @param entrada Opcion del menu elegida
	 * @param dni DNI del cliente a modificar
	 */
	private void implementacionMenuOpciones(int entrada, String DNI) {
		boolean actualizado = false;
		switch(entrada) {
			case 0: //Nombre
				String nuevoNombre = formatearEntradaCadena(UIMensajes.g_Nombre(),  true);
				actualizado = obtenerFinanciador().actualizarNombreCliente(DNI, nuevoNombre);
				break;
				
			case 1: //Email
				String nuevoEmail = formatearEntradaCadena(UIMensajes.g_Email(),  true);
				actualizado = obtenerFinanciador().actualizarEmailCliente(DNI, nuevoEmail);
				break;
				
			case 2: //Telefono
				String nuevoTelefono = formatearEntradaCadena(UIMensajes.g_Telefono(),  true);
				actualizado = obtenerFinanciador().actualizarTelefonoCliente(DNI, nuevoTelefono);
				break;
				
			case 3: //Domicilio
				String nuevoDomicilio = formatearEntradaCadena(UIMensajes.g_Domicilio(),  true);
				actualizado = obtenerFinanciador().actualizarDomicilioCliente(DNI, nuevoDomicilio);
				break;
		}
		if(actualizado) {
			//"Se ha actualizado el cliente con exito"
	        System.out.println(UIMensajes.mF_ADC_ClienteActualizado());
		}else {
			//"Cliente no encontrado"
        	System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
		}
	}
    
    /**
     * Registra un cliente nuevo en la base de datos del programa
     * 
     */
    public void darAltaCliente(){
        //"Indicar a continuacion los datos del cliente a registrar"
        formatearCadena(UIMensajes.mF_DA_EspecificarDatosCliente(), true, true);

        //Obtenemos array con dni, nombre, email, telefono y domicilio
        String[] entradas = obtenerDatosCliente();
        
        //Damos de alta al cliente con los datos especificados
        boolean dadoDeAlta = obtenerFinanciador().darAltaCliente(entradas[0], entradas[1],
        		entradas[2], entradas[3], entradas[4]);
        if(dadoDeAlta) {
        	//"Se ha registrado el cliente con exito"
        	System.out.println(UIMensajes.mF_DA_RegistradoExito());
        }else {
        	//"Ya existe un usuario registrado con el DNI especificado, registro fallido"
        	System.out.println("\t" + UIMensajes.mF_DA_UsuarioYaRegistrado());
        }
    }
    
    /**
     * Utiliza un formulario para obtener los datos del
     * cliente que se quiere registrar.
     * 
     * @return Array de cadenas con los datos
     * 
     * Posiciones del array: 
     * [0] DNI
     * [1] Nombre
     * [2] Email
     * [3] Telefono
     * [4] Domicilio
     */
    private String[] obtenerDatosCliente() {
    	
    	//Creamos el array para cada dato a obtener
        String[] entradas = new String[5];
        entradas[0] = UIMensajes.g_DNI();
        entradas[1] = UIMensajes.g_Nombre();
        entradas[2] = UIMensajes.g_Email();
        entradas[3] = UIMensajes.g_Telefono();
        entradas[4] = UIMensajes.g_Domicilio();
        
        //Obtenemos las entradas para cada dato
        return formularioCadenas(entradas);
    }
    
    /**
     * Imprime los datos de un cliente
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirDatosCliente(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
    	
        //Obtenemos los datos de un cliente
        String[] datosCliente = obtenerFinanciador().obtenerDatosCliente(dniCliente);
                
        if(datosCliente!=null) {
        	//Imprimir los datos obtenidos.
            formatearCadena(UIMensajes.g_DNI(), true, true);
            System.out.print(datosCliente[0]);
            formatearCadena(UIMensajes.g_Nombre(), true, true);
            System.out.print(datosCliente[1]);
            formatearCadena(UIMensajes.g_Email(), true, true);
            System.out.print(datosCliente[2]);
            formatearCadena(UIMensajes.g_Domicilio(), true, true);
            System.out.print(datosCliente[3]);  
            formatearCadena(UIMensajes.g_Telefono(), true, true);
            System.out.print(datosCliente[4]);
        }else {
        	//"Cliente no encontrado"
        	System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Imprime las facturas de un cliente
     * 
     * @param usuarios Base de datos de usuarios 
     */
    public void verListaFacturas(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);

        //Obtenemos la lista de facturas del cliente cuyo DNI es igual a dniCliente
        ArrayList<Factura> facturasCliente = obtenerFinanciador().obtenerFacturasCliente(dniCliente);
        Iterator<Factura> itr = facturasCliente.iterator();
        while(itr.hasNext()) {
        	Factura factura = itr.next();
        	
        	//Imprimimos los datos de la factura
        	imprimirDatosFactura(factura);
        }
    }
    
    /**
     * Imprime los datos de una factura
     * 
     * @param factura a imprimir
     */
    private void imprimirDatosFactura(Factura factura) {
    	System.out.println(); //Primera linea
        System.out.print("\t" + UIMensajes.mT_AR_Coste());
        System.out.print(factura.obtenerCoste());
        System.out.print(" |" + UIMensajes.mC_AnP_Dia());
        System.out.print(factura.obtenerDia());
        System.out.print(" |" + UIMensajes.mC_AnP_Mes());
        System.out.print(factura.obtenerMes());
        System.out.print(" |" + UIMensajes.mC_AnP_Ano());
        System.out.print(factura.obtenerAno());                    
        
        System.out.println(); //Segunda linea
        System.out.print("\t" + UIMensajes.mC_AnP_DescripcionFactura());
        System.out.print(factura.obtenerDescripcion());
    }

    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuEpdoFinanciacion(this);
	}
    
}
