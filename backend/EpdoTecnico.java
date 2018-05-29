package backend;

import java.util.ArrayList;
import java.util.Iterator;

import productos.EnumEstadoProducto;
import productos.Producto;
import productos.Productos;
import productos.Reporte;
import uitextual.UIEmpleado;
import uitextual.UIEpdoTecnico;
import uitextual.UIMensajes;

/**
 * Clase que implementa las funciones de los empleados tecnicos.
 * 
 * Empleado encargado de la reparación de productos.
 * 
 * @author Marcos Barrios 
 * @version 1.0
 */
public class EpdoTecnico extends Empleado{
    
    //Coleccion con las fichas de reparacion
    private ArrayList<FichaReparacion> listaFichas;
    
    //Coleccion con las piezas que el tecnico necesita
    private ArrayList<Pieza> piezasNecesarias;
    
    public EpdoTecnico(Usuarios usuarios, Productos productos,
			int diaActual, int mesActual, int anoActual, String dni, 
			String nombre, String email, String usuario, String contrasena){
       super(usuarios, productos, diaActual, mesActual, anoActual, dni, 
         		nombre, email, usuario, contrasena);
       listaFichas = new ArrayList<FichaReparacion>();
       piezasNecesarias = new ArrayList<Pieza>();
    }
    
    /**
     * Elimina una pieza en la posicion id de la coleccion
     * 
     * @param id Posicion de la pieza en la coleccion
     */
    public void eliminarPieza(int id){
        piezasNecesarias.remove(id);
    }
    
    /**
     * Elimina una pieza de la lista de piezas necesarias
     * 
     * @param pieza Pieza a eliminar
     */
    public void eliminarPieza(String nombrePieza) {
    	//Obtenemos la pieza cuyo nombre sea nombrePieza
    	Pieza pieza = obtenerPiezaPorNombre(nombrePieza);
    	
    	//Eliminamos de piezasNecesarias las piezas con el mismo nombre 
    	piezasNecesarias.remove(pieza);
    	
    	//Dejamos constancia de la operacion en el historial del tecnico
        dejarConstancia(this, EnumOperaciones.mT_ELIMINARPIEZA,
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Itera todas las piezas necesarias del tecnico y devuelve
     * la que tenga el mismo nombre que el especificado como
     * parametro.
     * 
     * @param nombrePieza Nombre de la pieza buscada
     * 
     * @return Pieza cuyo nombre coincide con nombrePieza
     */
    private Pieza obtenerPiezaPorNombre(String nombrePieza) {
    	Iterator<Pieza> itr = piezasNecesarias.iterator();
    	while(itr.hasNext()) {
    		Pieza pieza = itr.next();
    		if(pieza.obtenerNombre().equalsIgnoreCase(nombrePieza)) {
    			return pieza;
    		}
    	}
    	return null;
    }
    
    /**
     * Obtiene una pieza en la posicion id de la coleccion
     * 
     * @param id Posicion de la pieza en la coleccion
     */
    public Pieza obtenerPieza(int id){
        return piezasNecesarias.get(id);
    }
    
    /**
     * Devuelve el numero de piezas necesarias
     * 
     * @return piezasNecesarias.size();
     */
    public int obtenerNumeroPiezas(){
        return piezasNecesarias.size();
    }
    
    /**
     * Anade una ficha de reparacion al la lista de fichas de reparacion
     * del tecnico
     * 
     * @param fichaReparacion Ficha de reparacion a anadir
     */
    public void anadirFichaReparacion(FichaReparacion fichaReparacion){
        listaFichas.add(fichaReparacion);
    }
    
    /**
     * Obtiene una ficha de reparacion de la coleccion de fichas de
     * reparacion del tecnico
     * 
     * @param id Posicion en la coleccion de la ficha de reparacion
     */
    public FichaReparacion obtenerFichaReparacion(int id){
        return listaFichas.get(id);
    }
    
    /**
     * Devuelve el numero de fichas de reparacion asignadas al tecnico
     * 
     * @return listaFichas.size();
     */
    public int obtenerNumeroFichas() {
        return listaFichas.size();
    }
    
    /**
     * Devuelve una lista de piezas que el tecnico necesita
     * 
     * @return listaPiezas que el tecnico necesita
     */
    public ArrayList<Pieza> obtenerListaPiezasNecesarias(){
    	ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();
    	
    	for(int i = 0; i < obtenerNumeroPiezas(); i++) {
    		listaPiezas.add(obtenerPieza(i));
    	}
    	
    	//Dejamos constancia de la operacion en el historial del tecnico
        dejarConstancia(this, EnumOperaciones.mT_VERPIEZAS,
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    	
    	return listaPiezas;
    }
   
    
    /**
     * Anade un reporte para cambiar el estado de un producto. Los
     * reportes son elementales para el sistema de reparacion pues
     * guardan informacion esencial que los tecnicos necesitan.
     * 
     * @param DNI del tecnico
     * @param numeroProducto del producto
     * @param descripcionReporte Descripcion del problema
     * @param costeReporte Precio de reparacion del producto
     * @param nuevoEstado Nuevo estado del producto al anadir el reporte
     * 
     * @return Verdadero si se ha realizado con exito la operacion
     */
    public boolean anadirReporte(String DNI, int numeroProducto,
    		String descripcionReporte, float costeReporte, 
    		String nuevoEstado) {
		Cliente cliente = obtenerCliente(DNI);
		if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			
			//Obtenemos el producto de la lista de productos comprados del cliente
			Producto producto = fc.obtenerProductoComprado(numeroProducto, true);

			if(producto!=null) {
				//Creamos un reporte con los datos especificados y lo anadimos
				//al producto
		        producto.anadirReporte(crearReporte(producto, descripcionReporte, 
						nuevoEstado, costeReporte));
				
				//Dejamos constancia de la operacion en el historial del tecnico
		        dejarConstancia(cliente, producto, this, EnumOperaciones.mT_ANADIRREPORTE,
		        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				return true;
			}
		}
		return false;
    }
    
    /**
     * Anade una pieza a la lista de piezas que el tecnico necesita.
     * 
     * @param precioPieza de la pieza a anadir
     * @param nombrePieza de la pieza a anadir
     * @param descripcionPieza de la pieza a anadir
     */
    public void anadirPieza(float precioPieza, String nombrePieza,
    		String descripcionPieza) {
    	//Creamos la pieza utilizando los datos especificados
        Pieza p = new Pieza(precioPieza, nombrePieza, descripcionPieza);
        
        //Anade la pieza a la coleccion
        piezasNecesarias.add(p);
        
        //Dejamos constancia de la operacion en el historial
        dejarConstancia(this, EnumOperaciones.mT_ANADIRPIEZA,
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Devuelve la lista de fichas de reparacion del tecnico
     * 
     * @return listaFichas con las fichas de reparacion del tecnico
     */
    public ArrayList<FichaReparacion> obtenerListaFichasReparacion(){
    	ArrayList<FichaReparacion> listaFichas = new ArrayList<FichaReparacion>();
    	
    	//Iteramos todas las fichas de reparacion del tecnico
		//y las anadimos a listaFichas
		for(int i = 0; i < obtenerNumeroFichas(); i++){
            listaFichas.add(obtenerFichaReparacion(i));
        }
		
		//Dejamos constancia de la operacion en el historial
        dejarConstancia(this, EnumOperaciones.mT_VERLISTAFICHASREPARACION,
        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    	
		return listaFichas;
    }
    
    /**
     * Devuelve el ultimo reporte anadido a un producto
     * 
     * @param DNI del cliente que ha comprado el producto
     * @param numeroProducto Numero del producto que contiene el reporte
     * 
     * @return Ultimo reporte del producto con numeroProducto
     */
    public Reporte obtenerUltimoReporteProducto(String DNI, int numeroProducto) {
    	Cliente cliente = obtenerCliente(DNI);
    	if(cliente!=null) {
			FichaCliente fc = cliente.obtenerFichaCliente();
			Producto producto = fc.obtenerProductoComprado(numeroProducto, true);
			
			if(producto!=null) {
				//Dejamos constancia de la operacion en el historial
				//del tecnico y del producto
		        dejarConstancia(cliente, producto, this, 
		        		EnumOperaciones.mT_VERESTADOPRODUCTO,
		        		obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
				
				return producto.obtenerReporte(producto.obtenerNumeroReportes()-1);
			}
    	}
		return null;
    }
    
    /**
     * Crea un reporte con los datos especificados.
     * 
     * @param producto Producto en el que almacenar el reporte
     * @param descripcionReporte Descripcion del reporte
     * @param costeReporte Coste de reparacion
     * 
     * @return Reporte con los datos especificados
     */
    private Reporte crearReporte(Producto producto, String descripcionReporte,
    		String nuevoEstado, float costeReporte){
    	Reporte reporte = new Reporte();
    	
    	//Asignamos los datos al reporte
    	reporte.asignarDiaReporte(obtenerDiaActual());
        reporte.asignarMesReporte(obtenerMesActual());
        reporte.asignarAnoReporte(obtenerAnoActual());
        reporte.asignarDescripcion(descripcionReporte);
        reporte.asignarCoste(costeReporte);
    	
    	//Obtenemos el ultimo reporte del producto
        Reporte rAnterior = producto.obtenerReporte(producto.obtenerNumeroReportes()-1);
        
        //Asigna el nuevo estado al reporte
        reporte = asignarEstadoReporte(nuevoEstado, reporte);
        
        //Mantenemos la variable de coste por financiacion
        if(rAnterior.obtenerPagado()){
        	reporte.cambiarPagado(true);
        }
        
        return reporte;
    }
    
    /**
     * Asigna a un reporte un estado dependiendo del valor
     * de la cadena nuevoEstado. 
     * @param nuevoEstado Representacion del nuevo estado del reporte
     * @param reporte Reporte al que cambiar el estado
     * 
     * @return Reporte con el nuevo estado asignado
     */
    private Reporte asignarEstadoReporte(String nuevoEstado, Reporte reporte) {
        if(nuevoEstado.equalsIgnoreCase(EnumEstadoProducto.estadoProductoDevuelto())){
        	//Si nuevoEstado es devuelto
        	reporte.asignarEstado(EnumEstadoProducto.DEVUELTO);
        }else if(nuevoEstado.equalsIgnoreCase(EnumEstadoProducto.estadoProductoRoto())){
            //Si nuevoEstado es roto
        	reporte.asignarEstado(EnumEstadoProducto.ROTO);
        }else{
            //Si nuevoEstado es intacto
        	reporte.asignarEstado(EnumEstadoProducto.INTACTO);
        }
        return reporte;
    }
    
    /**
     * Devuelve la clase que implementa las funciones para este empleado
     * 
     * @return UIEmpleado clase UI para este empleado
     */
	public UIEmpleado obtenerUI() {
		return new UIEpdoTecnico(this);
	}
    
    /**
	 * Devuelve una cadena para referenciar este tipo de empleado
	 */
	@Override
	public String toString() {
		return UIMensajes.mGU_AnE_Tecnico();
	}
}
