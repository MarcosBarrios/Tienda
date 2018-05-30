package uitextual;

import java.util.ArrayList;

/**
 * Clase encargada de implementar las opciones que los empleados cajeros
 * de la tienda pueden llevar a cabo con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoCajero extends UIMenuEmpleado{
    
	//Empleado usando el programa
    private UIEpdoCajero cajero;
    
    //Metodo constructor
    public UIMenuEpdoCajero(UIEpdoCajero usuario){
    	super(usuario);
        this.cajero = usuario;
        
        //Anade las opciones al menu
        anadirOpciones();
    }
    
    /**
     * Devuelve el cajero usando el programa
     */
    private UIEpdoCajero obtenerCajero(){
        return cajero;
    }
    
    /**
     * Anade una opcion por cada funcion que el cajero puede realizar
     * 
     * (0) Buscar productos (Fija)
     * (1) Buscar usuarios (Fija)
     * (2) Vender un producto a un cliente
     * (3) Anadir un producto
     * (4) Actualizar un producto
     * (5) Ver las caracteristicas de un producto
     * (6) Lista de productos
     * (7) Cerrar sesion (Fija)
     * (8) Salir del programa (Fija)
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
    	listaOpciones.add(UIMensajes.mC_OpcionVenderProducto());
    	listaOpciones.add(UIMensajes.mC_OpcionAnadirProducto());
    	listaOpciones.add(UIMensajes.mC_OpcionActualizarProducto());
    	listaOpciones.add(UIMensajes.mC_OpcionVerDatosProducto());
    	listaOpciones.add(UIMensajes.mC_OpcionListaProductos());
    	for(int i = 0; i < listaOpciones.size(); i++) {
    		obtenerMenu().anadirOpcion(listaOpciones.get(i), i+2);
    	}
    }
    
    /**
     * Imprime las opciones y obtiene una entrada con el numero
     * de opcion que el usuario quiere usar.
     */
    public void activar(){
    	obtenerMenu().imprimirOpciones();
        obtenerEntradaUsuario();
    }
    
    /**
     * Obtiene una entrada con el numero de la opcion que el
     * usuario quiere usar.
     */
    private void obtenerEntradaUsuario() {
    	int entrada = obtenerMenu().obtenerOpcion();
    	
    	//casos 0, 1, (nOpciones-1) y nOpciones
        entradaOpcionesFija(entrada);
        
        switch(entrada){ 
            case 2: //Vender un producto a un cliente
            obtenerCajero().venderProducto();
            break;
            
            case 3: //Anadir producto a la base de datos
            obtenerCajero().anadirProducto();
            break;
                
            case 4: //Actualizar los datos de un producto
            obtenerCajero().actualizarProducto();
            break;
                
            case 5: //Ver las caracteristicas de un producto
            obtenerCajero().imprimirDatosProducto();
            break;
            
            case 6: //Imprimir lista de productos
            obtenerCajero().imprimirListaProductos();
            break;
        }
        activar(); //Activar de nuevo el menu
    }
    
}