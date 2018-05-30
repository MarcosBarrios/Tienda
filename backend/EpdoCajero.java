package backend;

import java.util.ArrayList;

import productos.Producto;
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
     * Devuelve un producto igual al producto especificado pero
     * de la base de datos
     * 
     * @param producto Producto a comparar
     * @param productos Base de datos de productos de la tienda
     */
    public Producto obtenerProductoIgual(Producto producto){
        for(int i = 0; i < obtenerProductos().obtenerTamano(); i++){
            Producto temp = obtenerProductos().obtenerProducto(i, false);
            if(temp.equals(producto)) {
            	return temp;
            }
        }
        
        return null;
    }
	
	/**
	 * Vende un producto a un cliente. Para ello se comprueba
	 * si el producto almacenado cuyo numero de producto coincida
	 * con el especificado tiene cantidad mayor que 0 y, en caso
	 * afirmativo se disminuye en 1 esa cantidad. 
	 * 
	 * A continuacion se anade el producto a la lista de productos
	 * comprados del cliente.
	 * 
	 * Adicionalmente, en esta version por financiacion se anade una factura al 
	 * cliente.
	 * 
	 * @param dniCliente DNI del cliente al que vender el producto
	 * @param numeroProducto Numero del producto siendo vendido
	 * 
	 * @return Verdadero si se ha realizado la operacion con exito
	 */
	public boolean financiarProducto(String dniCliente, int numeroProducto,
			String descripcionFactura) {
		Cliente cliente = obtenerCliente(dniCliente);
		if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
			
			if(producto!=null) {
				//Si se encuentra un cliente y un producto con exito
				
				producto.cambiarFinanciado(true);
				if(producto.obtenerCantidad()>0) {
					//Si hay unidades disponibles del producto
					
					//Se crea y se asigna la factura para dejar constancia de
					//que se ha financiado el producto
					Factura factura = generarFactura(producto.obtenerPrecio(),
							fc, descripcionFactura);
					fc.anadirFactura(factura);
					
					venderProducto(cliente, producto);
				}
			}
		}
		return false;
	}
	
	/**
	 * Se genera una factura y se asigna a la ficha de un cliente
	 * 
	 * @param precio Precio del producto siendo financiado
	 * @param fichaCliente Ficha del cliente
	 * @param descripcionFactura Descripcion de la factura
	 */
	private Factura generarFactura(float precio, FichaCliente fichaCliente, 
			String descripcionFactura) {
		//Creamos la factura y asignamos sus datos
        Factura factura = new Factura();
        factura.asignarCoste(precio);
        factura.asignarDia(obtenerDiaActual());
        factura.asignarMes(obtenerMesActual());
        factura.asignarAno(obtenerAnoActual());
        factura.asignarDescripcion(descripcionFactura);
        
        return factura;
	}
	
	/**
	 * Vende un producto a un cliente. Para ello se comprueba
	 * si el producto almacenado cuyo numero de producto coincida
	 * con el especificado tiene cantidad mayor que 0 y, en caso
	 * afirmativo se disminuye en 1 esa cantidad. 
	 * 
	 * A continuacion se anade el producto a la lista de productos
	 * comprados del cliente.
	 * 
	 * @param dniCliente DNI del cliente al que vender el producto
	 * @param numeroProducto Numero del producto siendo vendido
	 * 
	 * @return Verdadero si se ha realizado la operacion con exito
	 */
	public boolean venderProducto(String dniCliente, int numeroProducto) {
		Cliente cliente = obtenerCliente(dniCliente);
		if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
			
			if(producto!=null) {
				//Si se encuentra un cliente y un producto con exito
				
				if(producto.obtenerCantidad()>0) {
					//Si hay unidades disponibles del producto
					venderProducto(cliente, producto);
				}
			}
		}
		return false;
	}
	
	/**
	 * Anade el producto a vender a la lista de productos comprados del
	 * cliente y deja constancia de la operacion en el historial de
	 * operaciones.
	 * 
	 * @param cliente Comprador del producto
	 * @param productoVendido Producto a vender
	 */
	private void venderProducto(Cliente cliente, Producto productoVendido) {
		//Anadir producto a la lista de productos comprados del cliente
        cliente.obtenerFichaCliente().anadirProductoComprado(productoVendido);
        productoVendido.asignarCantidad(productoVendido.obtenerCantidad()-1);
		
		//Dejamos constancia de la operacion en el historial
        dejarConstancia(cliente, productoVendido, this, EnumOperaciones.mC_VENDERPRODUCTO, 
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
	}
	
	/**
	 * Anade un producto al almacen de la tienda y a la base de datos de productos.
	 * 
	 * @param producto Producto a anadir a la base de datos
	 */
	public void anadirProducto(Producto producto) {
		//Se comprueba si la base de datos contiene un producto igual al que se
        //va a anadir, en cuyo caso simplemente aumenta la cantidad del actual
        //producto en la base de datos en vez de anadir un producto mas a esta.
        Producto productoIgual = obtenerProductoIgual(producto);
        if(obtenerProductoIgual(producto)!=null){
        	productoIgual.asignarCantidad(productoIgual.obtenerCantidad()+1);
        }else{
            obtenerProductos().anadirProducto(producto);
        }
        
        //Dejamos constancia de la oepracion realizada en el historial
        dejarConstancia(this, EnumOperaciones.mC_ANADIRPRODUCTO, 
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
	}
	
	/**
	 * Devuelve un producto comprado o de la base de datos dependiendo
	 * del valor de la variable booleana especificada. 
	 * 
	 * Este metodo es utilizado a la hora de actualizar un producto y
	 * sirve para hacer posible el dejar constancia de la operacion.
	 * 
	 * @param dniCliente DNI del cliente en caso de que se busque
	 * un producto comprado.
	 * @param numeroProducto Numero del producto a actualizar
	 * @param buscarEnCliente Indica si buscar un producto comprado
	 * o del almacen. Si buscarEnCliente=true entonces el valor de 
	 * dniCliente no es utilizado.
	 * 
	 * @return Producto comprado o de la base de datos.
	 */
	public Producto obtenerProductoActualizar(String dniCliente, int numeroProducto,
			boolean buscarEnCliente) {
		if(buscarEnCliente){ 
			Cliente cliente = obtenerCliente(dniCliente);
			Producto producto = obtenerProductoComprado(dniCliente, numeroProducto);
			if(cliente!=null && producto!=null) {
				//Se deja constancia de la operacion en el cliente, el producto y el cajero.
				dejarConstancia(cliente, producto, this, EnumOperaciones.mC_ACTUALIZARPRODUCTO,
	                    obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
			}
			
        	//Producto comprado por un cliente
            return  obtenerProductoComprado(dniCliente, numeroProducto);
        }

		//Dejamos constancia de la operacion en el historial del cajero.
		dejarConstancia(this, EnumOperaciones.mC_ACTUALIZARPRODUCTO,
                obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
		
		//Producto de la base de datos / almacen de la tienda
		return obtenerProductoBaseDatos(numeroProducto);
	}
	
	/**
	 * Dado el numero de un producto de la base de datos de productos
	 * devuelve un producto comprado por un cliente.
	 * 
	 * @param dniCliente DNI del cliente en donde buscar
	 * @param numeroProducto Numero del producto 
	 * 
	 * @return Producto comprado por un cliente
	 */
	public Producto obtenerProductoComprado(String dniCliente, int numeroProducto) {
		Cliente cliente = obtenerCliente(dniCliente);
		if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			return  fc.obtenerProductoComprado(numeroProducto, true);
		}
		return null;
	}
	
	/**
	 * Devuelve un producto de la base de datos de productos
	 * 
	 * @param numeroProducto Numero del producto buscado
	 * 
	 * @return Producto de la base de datos de productos
	 */
	public Producto obtenerProductoBaseDatos(int numeroProducto) {
		return obtenerProductos().obtenerProducto(numeroProducto, true);
	}
	
	/**
	 * Devuelve una lista con los clientes de la tienda
	 * 
	 * @return Lista con los clientes de la tienda
	 */
	public ArrayList<Cliente> listaClientes(){
		return Util.obtenerListaClientes(obtenerUsuarios());
	}
	
	/**
	 * Devuelve una lista con los productos almacenados en la base de datos
	 * 
	 * @return Lista con los productos almacenados en la base de datos
	 */
	public ArrayList<Producto> listaProductosBaseDatos(){
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		
		//Anadimos los productos almacenados en la base de datos
		for(int i = 0; i < obtenerProductos().obtenerTamano(); i++) {
			listaProductos.add(obtenerProductos().obtenerProducto(i, false));
		}
		
		return listaProductos;
	}
	
	/**
	 * Devuelve una lista con los productos comprados por un cliente
	 * 
	 * @param cliente Cliente con los productos
	 * 
	 * @return Lista con los productos comprados por un cliente
	 */
	public ArrayList<Producto> listaProductosComprados(Cliente cliente){
		FichaCliente fc = cliente.obtenerFichaCliente();
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		
		//Anadimos los productos comprados por el cliente
		for(int i = 0; i < obtenerProductos().obtenerTamano(); i++) {
			listaProductos.add(fc.obtenerProductoComprado(i, false));
		}
		
		return null;
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
	public String tipoUsuario() {
		return UIMensajes.mGU_AnE_Cajero();
	}
	
	/**
	 * Devuelve una cadena con la informacion del cliente
	 */
	@Override
	public String toString(){
		return  "\t" + UIMensajes.g_TipoUsuario() + ": " + tipoUsuario() +
				" | " + UIMensajes.g_DNI() + ": " + obtenerDNI() +
				" | " + UIMensajes.g_Nombre() + ": " + obtenerNombreUsuario() +
				" | " + UIMensajes.g_Email() + ": " + obtenerEmailUsuario();
	}

}