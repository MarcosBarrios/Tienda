package backend;

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
	 * Dado el numero de un producto de la base de datos de productos
	 * devuelve un producto igual comprado por un cliente.
	 * 
	 * @param dniCliente DNI del cliente en donde buscar
	 * @param numeroProducto Numero del producto del cual se quiere
	 * obtener un producto comprado igual
	 * 
	 * @return Producto comprado igual a un producto de la base de 
	 * datos de productos
	 */
	public Producto obtenerProductoCompradoIgual(String dniCliente, int numeroProducto) {
		Cliente cliente = obtenerCliente(dniCliente);
		if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
			
			if(producto!=null) {
				for(int i = 0; i < i; i++) {
					Producto temp = fc.obtenerProductoComprado(i, false);
					if(temp.equals(producto)) {
						return temp;
					}
				}
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
	
	public void anadirProducto(String categoriaProducto) {
		
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
	@Override
	public String toString() {
		return UIMensajes.mGU_AnE_Cajero();
	}

}