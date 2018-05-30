package backend;

import productos.EnumEstadoProducto;
import productos.Producto;
import productos.Productos;
import productos.Reporte;
import uitextual.UIEmpleado;
import uitextual.UIEpdoPostVenta;
import uitextual.UIMensajes;

/**
 * Clase que implementa las funciones de los empleados de post-venta.
 * 
 * Entre sus funciones se incluye la capacidad para modificar la informacion
 * de los empleados y clientes en la base de datos del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoPostVenta extends Empleado{
    
	//Metodo constructor
    public EpdoPostVenta(Usuarios usuarios, Productos productos,
			int diaActual, int mesActual, int anoActual, String dni, 
			String nombre, String email, String usuario, String contrasena){
       super(usuarios, productos, diaActual, mesActual, anoActual, dni, 
          		nombre, email, usuario, contrasena);
    }
    
    /**
     * Asigna un reporte al producto que se quiere reparar, se crea una
     * ficha de reparacion para el producto a reparar y se asigna la ficha
     * al tecnico que se encargara de arreglar el producto.
     * 
     * Ademas, el estado del producto cambia a Roto.
     * 
     * @param dniCliente DNI del cliente con el producto
     * @param numeroProducto Numero del producto del cliente
     * @param descripcionProblema Descripcion del por que se necesita reparacion
     * @param dniTecnico DNI del tecnico al que se asignara la ficha de reparacion
     * 
     * @return Devuelve verdadero si la operacion se completo con exito
     */
    public boolean repararProducto(String dniCliente, int numeroProducto,
    		String descripcionProblema, String dniTecnico){
    	//Obtenemos un cliente y un producto comprado del cliente
    	Cliente cliente = obtenerCliente(dniCliente);
    	if(cliente!=null) {
    		FichaCliente fc = cliente.obtenerFichaCliente();
        	Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
        	
        	//Obtenemos el tecnico al que se va a asignar la ficha de reparacion
        	EpdoTecnico tecnico = obtenerTecnico(dniTecnico);
        	
        	if(producto!=null) {
        		//Creamos el reporte para cambiar el estado al producto a reparar
            	Reporte reporte = crearReporteReparacion(producto.obtenerAnoCompra(), 
            			descripcionProblema);
            	producto.anadirReporte(reporte);
            	
            	//Creamos la ficha de reparacion que se va a asignar al tecnico
            	FichaReparacion fr = new FichaReparacion(producto, cliente);
            	tecnico.anadirFichaReparacion(fr);
            	
            	//Dejamos constancia de la operacion en el historial de operaciones
            	dejarConstancia(cliente, producto, this, EnumOperaciones.mPV_REPARARPRODUCTO,
            			obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
            	return true;
        	}else {
        		return false;
        	}
    	}
    	return false;
    }
    
    /**
     * Devuelve un tecnico dado un DNI
     * 
     * @param dniTecnico DNI del tecnico
     * 
     * @return Tecnico dado un DNI
     */
    private EpdoTecnico obtenerTecnico(String dniTecnico) {
    	Usuario usuario = obtenerUsuarios().obtenerUsuario(dniTecnico);
    	if(usuario!=null) {
    		if(usuario instanceof EpdoTecnico) {
    			return (EpdoTecnico) usuario;
    		}
    	}
    	return null;
    }
    
    /**
     * Devuelve un reporte para ser asignado a un producto que necesita
     * reparacion.
     * 
     * @param anoComprado Ano en el que se compro el producto al que se va
     * a asignar el reporte
     * @param descripcionProblema Descripcion de lo que se tiene que reparar
     * 
     * @return Reporte con los datos necesarios para que un producto necesite
     * reparacion.
     */
    private Reporte crearReporteReparacion(int anoComprado, String descripcionProblema) {
    	Reporte reporte = new Reporte();
    	
    	reporte.asignarEstado(EnumEstadoProducto.ROTO);
    	reporte.asignarDiaReporte(obtenerDiaActual()); //Dia
    	reporte.asignarMesReporte(obtenerMesActual()); //Mes
    	reporte.asignarAnoReporte(obtenerAnoActual()); //Ano
    	reporte.asignarDescripcion(descripcionProblema);
	      
    	//Si han pasado menos de 2 anos se repara gratuitamente
		if(Math.abs(anoComprado-2) <= 0){
			reporte.cambiarPagado(true);
		    //"El producto mantiene la garantia. Precio 0 asegurado."
		    System.out.println(UIMensajes.mPV_RP_CumpleGarantia());
		}else{
			reporte.cambiarPagado(false);
		    //"El producto no mantiene la garantia. Es necesario realizar un pago"
		    System.out.println(UIMensajes.mPV_RP_NoCumpleGarantia());
		}
    	
    	return reporte;
    }
    
    /**
     * Devuelve el ultimo reporte que se ha anadido a un producto
     * 
     * @param dniCliente dni del cliente que tiene el producto
     * @param numeroProducto Numero del producto con el reporte
     * 
     * @return Ultimo reporte de un producto
     */
    public Reporte obtenerUltimoReporte(String dniCliente, int numeroProducto) {
    	//Obtenemos el cliente y el producto con el reporte
    	Cliente cliente = obtenerCliente(dniCliente);
    	if(cliente!=null) {
    		FichaCliente fc = cliente.obtenerFichaCliente();
        	Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
        	
        	if(producto!=null) {
        		//Obtenemos el ultimo reporte anadido al producto
            	return producto.obtenerReporte(producto.obtenerNumeroReportes());
        	}
    	}
    	return null;
    }
    
    /**
     * Genera una factura y la asigna a un cliente.
     * 
     * @param dniCliente DNI del cliente al que se va a asignar
     * @param numeroProducto Numero del producto
     * 
     * @return Verdadero si la operacion se ha realizado con exito
     */
    public boolean generarFactura(String dniCliente, int numeroProducto,
    		float costeFactura, String descripcionFactura) {
    	Cliente cliente = obtenerCliente(dniCliente);
    	if(cliente!=null) {
    		FichaCliente fc = cliente.obtenerFichaCliente();
    		Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
    		
    		//Creamos la factura y se la anadimos al cliente
    		Factura f = new Factura(costeFactura, descripcionFactura, obtenerDiaActual(),
                    obtenerMesActual(), obtenerAnoActual());
            fc.anadirFactura(f);
            
            if(producto!=null) {
            	//Dejamos constancia de la operacion realizada
                dejarConstancia(cliente, producto, this, EnumOperaciones.mPV_COMPROBARESTADOPRODUCTO,
                		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
                return true;
            }
    	}
    	return false;
    }
    
    /**
     * Comprueba si el producto de un cliente cumple con los requisitos
     * de no haber sido comprado hace mas de 3 meses y, en caso de que
     * los cumpla disminuye la cantidad del producto que el cliente tiene
     * en 1 y aumenta en 1 el producto en la base de datos de productos
     * de la tienda.
     * 
     * @param dniCliente DNI del cliente propietario del producto
     * @param numeroProducto Numero del producto a tratar
     * 
     * @return Verdadero si la operacion se ha realizado con exito
     */
    public boolean devolverProducto(String dniCliente, int numeroProducto) {
    	Cliente cliente = obtenerCliente(dniCliente);
    	if(cliente!=null) {
    		FichaCliente fc = cliente.obtenerFichaCliente();
    		Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
    		
    		if(producto!=null) {
    			//Si no se ha comprado el producto hace mas de 3 meses
    			if(Math.abs(obtenerMesActual()-producto.obtenerMesCompra()) <= 3){
    				
    			    //Se acepta el producto
    				if(producto.obtenerCantidad()==1){ //Si el cliente solo ha comprado una ud. de ese producto
    				//Eliminar el producto de la coleccion de productos comprados del cliente
    				    cliente.obtenerFichaCliente().eliminarProductoComprado(numeroProducto, true);
    				}else{ //Si ha comprado mas de una unidad
    				    producto.asignarCantidad(producto.obtenerCantidad()-1);
    				}
    				//Dejamos constancia de la operacion el el historial
    			    dejarConstancia(cliente, producto, this, EnumOperaciones.mPV_DEVOLVERPRODUCTO, 
    			    		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    			    return true;
    			}
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
		return new UIEpdoPostVenta(this);
	}
    
    /**
	 * Devuelve una cadena para referenciar este tipo de usuario
	 */
	public String tipoUsuario() {
		return UIMensajes.mGU_AnE_PostVenta();
	}
	
	/**
	 * Devuelve una cadena con la informacion del empleado de 
	 * post venta
	 */
	@Override
	public String toString(){
		return  "\t" + UIMensajes.g_TipoUsuario() + ": " + tipoUsuario() +
				" | " + UIMensajes.g_DNI() + ": " + obtenerDNI() +
				" | " + UIMensajes.g_Nombre() + ": " + obtenerNombreUsuario() +
				" | " + UIMensajes.g_Email() + ": " + obtenerEmailUsuario();
	}
    
}

