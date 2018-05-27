package uitextual;

import java.util.ArrayList;

/**
 * Menu para los empleados de post venta.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoPostVenta extends UIMenuEmpleado{
    
	//Empleado usando el programa
    private UIEpdoPostVenta postVenta;

    //Metodo constructor
    public UIMenuEpdoPostVenta(UIEpdoPostVenta usuario){
    	super(usuario);
        postVenta = usuario;
        
        //Anade las opciones al menu
        anadirOpciones();
        
    }
    
    /**
     * Devuelve el empleado de PostVenta para el menu
     */
    private UIEpdoPostVenta obtenerPostVenta(){
        return postVenta;
    }
    
    /**
     * Anade una opcion por cada funcion que el financiador puede realizar
     * 
     * (0) Buscar productos (Fija)
     * (1) Buscar usuarios (Fija)
     * (2) Reparar un producto
     * (3) Devolver un producto
     * (4) Comprobar el estado de un producto
     * (5) Cerrar sesion (Fija)
     * (6) Salir del programa (Fija)
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(UIMensajes.mPV_OpcionRepararProducto());
        listaOpciones.add(UIMensajes.mPV_OpcionDevolverProducto());
        listaOpciones.add(UIMensajes.mPV_OpcionComprobarEstadoProducto());
        obtenerMenu().anadirListaOpciones(listaOpciones);
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
    private void obtenerEntradaUsuario(){
        int entrada = obtenerMenu().obtenerOpcion();
        switch(entrada){ 
            case 2: //Reparar un producto
            obtenerPostVenta().repararProducto();
            break;
            
            case 3: //Devolver un producto
            obtenerPostVenta().devolverProducto();
            break;
            
            case 4: //"Comprobar el estado de un producto"
            obtenerPostVenta().comprobarEstadoProducto();
            break;
        }
        activar(); //Activar de nuevo el menu
    }
}
