package uitextual;

import java.util.ArrayList;

/**
 * Implementa el menu de UIEpdoTecnico
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoTecnico extends UIMenuEmpleado{
    
	//Empleado usando el programa
    private UIEpdoTecnico tecnico;
    
    public UIMenuEpdoTecnico(UIEpdoTecnico usuario){
    	super(usuario);
        tecnico = usuario;
                
        //Anade las opciones al menu
        anadirOpciones();
    }
    
    /**
     * Devuelve el empleado de PostVenta para el menu
     */
    private UIEpdoTecnico obtenerTecnico(){
        return tecnico;
    }
    
    /**
     * Anade una opcion por cada funcion que el tecnico puede realizar
     * 
     * (0) Buscar productos (Fija)
     * (1) Buscar usuarios (Fija)
     * (2) Anadir reporte a un producto
     * (3) Ver mis fichas de reparacion
     * (4) Ver el estado de un producto
     * (5) Anadir una pieza a la lista de piezas necesarias
     * (6) Eliminar una pieza de la lista de piezas necesarias
     * (7) Cerrar sesion (Fija)
     * (8) Salir del programa (Fija)
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(UIMensajes.mT_OpcionAnadirReporte());
        listaOpciones.add(UIMensajes.mT_OpcionVerFichas());
        listaOpciones.add(UIMensajes.mT_OpcionVerEstadoProducto());
        listaOpciones.add(UIMensajes.mT_OpcionAnadirPieza());
        listaOpciones.add(UIMensajes.mT_OpcionEliminarPieza());
        listaOpciones.add(UIMensajes.mT_OpcionVerPiezasNecesarias());
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
            case 2: //Anadir reporte a un producto
            obtenerTecnico().anadirReporte();
            break;
            
            case 3: //Ver mis fichas de reparacion
            obtenerTecnico().verListaFichasReparacion();
            break;
            
            case 4: //Ver el estado de un producto
            obtenerTecnico().verEstadoProducto();
            break;
            
            case 5: //Anadir una pieza a la lista de piezas necesarias
            obtenerTecnico().anadirPiezaNecesaria();
            break;
            
            case 6: //Eliminar una pieza de la lista de piezas necesarias
            obtenerTecnico().eliminarPiezaNecesaria();
            break;
            
            case 7: //Ver piezas necesarias
            obtenerTecnico().verPiezasNecesarias();
            break;
        }
        activar(); //Activar de nuevo el menu
    }
    
}
