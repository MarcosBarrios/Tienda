package uitextual;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

import backend.Cliente;
import backend.Empleado;
import backend.Usuario;
import productos.Producto;

/**
 * Clase especifica para la parte textual del programa que implementa
 * metodos que los empleados del programa necesitan.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIEmpleado extends UIUsuario {
	
	//Metodo constructor
	public UIEmpleado(Empleado empleado) {
		super(empleado);
	}
    
	/**
	 * Devuelve el empleado utilizando el programa
	 * 
	 * @return (Empleado) obtenerUsuario()
	 */
	protected Empleado obtenerUsuario() {
		return (Empleado) obtenerUsuario();
	}
	
	/**
     * Imprime una lista de usuarios 
     * 
     */
	protected void imprimirBusquedaUsuarios(){
		//Informa sobre los valores que tienen que tener los datos para ser ignorados
		//en la busqueda y pregunta si realizar la busqueda sobre productos comprados o almacenados
        imprimirInformacionBusqueda();
        
        //Obtenemos los datos necesarios para una busqueda
        String dni = formatearEntradaCadena(UIMensajes.g_DNI(), true);
        String nombre = formatearEntradaCadena(UIMensajes.g_Nombre(), true);
        String email = formatearEntradaCadena(UIMensajes.g_Email(), true);
        
        //Obtenemos una lista de usuarios mediante una busqueda
        ArrayList<Usuario> listaEncontrados = obtenerUsuario().buscarUsuarios(dni, nombre, email);
        
        //Iteramos los usuarios obtenidos
        Iterator<Usuario> itr = listaEncontrados.iterator();
        while(itr.hasNext()){
            Usuario temp = itr.next(); //Obtenemos un usuario
            
            //Imprime una lista compacta con los datos de un usuario
            imprimirDatosUsuario(temp, 3);
        }
    }
	
	/**
	 * Devuelve un mapa con los datos del usuario que se quieren imprimir.
	 * 
	 * @param usuario Usuario con los datos a imprimir
	 * 
	 * @return datos Mapa con los datos a imprimir
	 */
	private void imprimirDatosUsuario(Usuario usuario, int m) {
		//Obtenemos un mapa con los datos del producto a imprimir
		HashMap<String, Object> mapaDatos = mapaDatosUsuario(usuario);
		
		////Imprime todos los datos tal que cada linea contiene m datos
		imprimirLineasDatos(mapaDatos, m);
	}
	
	/**
	 * Devuelve un mapa con los datos del usuario que se quieren imprimir.
	 * 
	 * @param usuario Usuario con los datos a imprimir
	 * 
	 * @return datos Mapa con los datos a imprimir
	 */
	private HashMap<String, Object> mapaDatosUsuario(Usuario usuario){
		HashMap<String, Object> datos = new HashMap<String, Object>();
		
		/*
		 * Datos añadidos al mapa: DNI, nombre, email
		 */
		
		datos.put(UIMensajes.g_Usuario(), usuario);
    	datos.put(UIMensajes.g_DNI(), usuario.obtenerDNI());
        datos.put(UIMensajes.g_Nombre(), usuario.obtenerNombreUsuario());
        datos.put(UIMensajes.g_Email(), usuario.obtenerEmailUsuario());
        
        return datos;
	}
    
    /**
     * Realiza una busqueda y devuelve una lista de productos encontrados.
     * 
     * Para ello pregunta si se quiere realizar la busqueda sobre los productos
     * almacenados o sobre los productos que han sido comprados por clientes.
     */
	protected void imprimirBusquedaProductos(){
		//Informa sobre los valores que tienen que tener los datos para ser ignorados
		//en la busqueda y pregunta si realizar la busqueda sobre productos comprados o almacenados
		imprimirInformacionBusqueda();
		
		//Obtiene del usuario los campos de busqueda
		float camposBusqueda[] = obtenerCamposBusquedaProducto();
		
		//"Buscar en clientes?"
        boolean buscarEnClientes = formatearEntradaBoolean(UIMensajes.b_BuscarEnCliente());
		
        //"Descripcion"
        String descripcion = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
        
        if(buscarEnClientes){
        	//Imprime los datos de productos comprados por clientes
            imprimirDatosProductosClientes(camposBusqueda, descripcion);
        }else{
        	//Imprime los datos de productos almacenados en la tienda
            imprimirDatosProductos(camposBusqueda, descripcion);
        }
    }
	
	private void imprimirInformacionBusqueda() {
		//"Indicar a continuacion los datos de busqueda"
        formatearCadena(UIMensajes.b_IndicarDatos(), true, true);
        
        //"Escribir -1 para no incluir el dato en la busqueda"
        System.out.println("(" + UIMensajes.b_ValoresNegativos() + ")");
        
        //"No escribir nada para no incluir un dato textual en la busqueda"
        System.out.println("(" + UIMensajes.b_ValoresPredeterminados() + ")");
	}
	
	private float[] obtenerCamposBusquedaProducto() {
        //Creamos un formulario para las entradas numericas
        String[] entradas = new String[8];
        entradas[0] = UIMensajes.mC_AnP_Cantidad();
        entradas[1] = UIMensajes.mC_AnP_Peso();
        entradas[2] = UIMensajes.mC_AnP_Precio();
        entradas[3] = UIMensajes.mC_AnP_Dia();
        entradas[4] = UIMensajes.mC_AnP_Mes();
        entradas[5] = UIMensajes.mC_AnP_Ano();
        entradas[6] = UIMensajes.mC_AcP_TiempoGarantia();
        entradas[7] = UIMensajes.mC_ILP_NumeroCaja();
        float[] salidas = formularioDecimales(entradas);
        return salidas;
	}
	
	/**
	 * Imprime una lista compacta con los productos almacenados en la tienda.
	 * 
	 * @param entradas Campos de busqueda especificados por un empleado
	 * @param descripcion Descripcion del producto a buscar
	 */
	private void imprimirDatosProductos(float[] salidas, String descripcion) {
		//"Buscando en la base de datos de la tienda"
        formatearCadena(UIMensajes.b_BuscandoEnBaseDeDatos(), true, true);
        
        //Obtenemos la lista de productos que cumplen con las caracteristicas especificadas
        //buscarProductos(int cantidad, float precio, float peso, String descripcion, int dia,
        //int mes, int ano, int tiempoGarantia, int numeroCaja)
        ArrayList<Producto> productosEncontrados = obtenerUsuario().buscarProductos(
        		(int)salidas[0], salidas[1], salidas[2], descripcion,
        		(int)salidas[3], (int)salidas[4], (int)salidas[5], (int)salidas[6], 
        		(int)salidas[7]);
        
        //Imprimir lista compacta con los datos de productosEncontrados
        imprimirProductosEncontrados(productosEncontrados, false, "");
	}
	
	/**
	 * Imprime una lista compacta con los productos comprados por todos los clientes
	 * registrados en la tienda.
	 * 
	 * @param entradas Campos de busqueda especificados por un empleado
	 * @param descripcion Descripcion del producto a buscar
	 */
	private void imprimirDatosProductosClientes(float[] entradas, String descripcion) {
		//"Buscando en clientes"
        formatearCadena(UIMensajes.b_BuscandoEnClientes(), true, true);
        
        //Obtenemos la lista de clientes registrados
        ArrayList<Cliente> listaClientes = obtenerUsuario().obtenerListaClientes();
        Iterator<Cliente> itr = listaClientes.iterator();
        while(itr.hasNext()) {
        	Cliente cliente = itr.next();
        	
            //Obtenemos la lista de productos que cumplen con las caracteristicas especificadas
            ArrayList<Producto> productosEncontradosCliente = obtenerUsuario().buscarProductosEnCliente(
            		cliente, (int)entradas[0], entradas[1], entradas[2], descripcion,
            		(int)entradas[3], (int)entradas[4], (int)entradas[5], (int)entradas[6], 
            		(int)entradas[7]);
            
            //Imprimir lista compacta con los datos de productosEncontradosCliente
            imprimirProductosEncontrados(productosEncontradosCliente, true, cliente.obtenerNombreUsuario());
        }
	}
	
	/**
	 * Imprime una lista compacta con los datos de todos los productos encontrados por una 
	 * busqueda de productos.
	 * 
	 * @param imprimirCliente true=Imprime el nombre del cliente antes del producto
	 * @param nombreCliente Nombre del cliente propietario del producto (Si imprimirCliente)
	 * @param productosEncontrados Lista con los productos encontrados
	 */
	private void imprimirProductosEncontrados(ArrayList<Producto> productosEncontrados, boolean imprimirCliente, 
			String nombreCliente) {
		//Iteramos la lista e imprimimos los datos de cada producto
        Iterator<Producto> itr = productosEncontrados.iterator();
        while(itr.hasNext()){
            Producto producto = itr.next();
            
            if(imprimirCliente) {
            	//Imprime el nombre del cliente
            	System.out.println(); //Primera linea
                System.out.print("\t");
                System.out.print(UIMensajes.g_Nombre() + ": ");
                System.out.print(nombreCliente);
            }
            
            //Imprime los datos del producto en lista compacta
            imprimirDatosProducto(producto, 3);
            System.out.println("...");
        }
	}
	
	/**
	 * Imprime los datos de un producto mediante una lista compacta
	 * de m datos por linea.
	 * 
	 * @param producto Producto con los datos a imprimir
	 * @param m Numero de datos por linea imprimida
	 */
	private void imprimirDatosProducto(Producto producto, int m) {
		//Obtenemos un mapa con los datos del producto a imprimir
		HashMap<String, Object> mapaDatos = mapaDatosProducto(producto);
		
		//Imprime todos los datos tal que cada linea contiene m datos
		imprimirLineasDatos(mapaDatos, m);
	}
	
	/**
	 * Devuelve un mapa con los datos del producto que se quieren imprimir.
	 * 
	 * @param producto Producto con los datos a imprimir
	 * 
	 * @return datos Mapa con los datos a imprimir
	 */
	private HashMap<String, Object> mapaDatosProducto(Producto producto){
		HashMap<String, Object> datos = new HashMap<String, Object>();
		
		/*
		 * Datos añadidos al mapa: categoria, cantidad, precio,
		 * peso, dia, mes, año, tiempo de garantia, numero de caja,
		 * descripcion 
		 */
		
    	datos.put(UIMensajes.mC_ITP_CategoriaProducto(), producto);
        datos.put(UIMensajes.mC_AnP_Cantidad(), producto.obtenerCantidad());
        datos.put(UIMensajes.mC_AnP_Precio(), producto.obtenerPrecio());
        datos.put(UIMensajes.mC_AnP_Peso(), producto.obtenerPeso());
        datos.put(UIMensajes.mC_AnP_Dia(), producto.obtenerDiaCompra());
        datos.put(UIMensajes.mC_AnP_Mes(), producto.obtenerMesCompra());
        datos.put(UIMensajes.mC_AnP_Ano(), producto.obtenerAnoCompra());
        datos.put(UIMensajes.mC_AcP_TiempoGarantia(), producto.obtenerTiempoGarantia());
        datos.put(UIMensajes.mC_ILP_NumeroCaja(), producto.obtenerNumeroCaja());
        datos.put(UIMensajes.mC_AnP_Descripcion(), producto.obtenerDescripcion());
        
        return datos;
	}
	
	/**
	 * Imprime n lineas de m datos.
	 * 
	 * Ejemplo de tres datos por linea:
	 * 		Nombre: Marcos Barrios | Email: marcuslaskardhones@gmail.com | Telefono: 432878123342
	 * 
	 * @param datos Conjuntos titulo-valor de cada dato
	 * @param m Numero de datos por linea
	 */
	protected void imprimirLineasDatos(HashMap<String, Object> datos, int m) {
		//Obtenemos los titulos de cada dato
		String[] titulos = (String[]) datos.keySet().toArray();
		
		//Primera linea
		System.out.println("\t");
		
		int nDatosLinea = 0;
		//Por cada dato que se quiera imprimir
		for(int i = 0; i < datos.size(); i++) {
			//titulo: dato | titulo: dato(...)
			System.out.print(titulos[0] + ": ");
			System.out.print(datos.get(titulos[0]));
			if(i!=datos.size()-1) {
				System.out.println(" | ");
			}
			
			//Incrementamos numero de datos en la linea
			nDatosLinea++;
			
			//Si el numero de datos en la linea es igual a m entonces saltar linea
			if(nDatosLinea==m) {
				System.out.println("\t");
			}
		}
	}
	
	/**
	 * Devuelve un menu accionable con las opciones que el empleado
	 * pueda necesitar.
	 * 
	 * @return UIMenuAccionable menu
	 */
	public abstract UIMenuEmpleado obtenerMenu();
}
