package uitextual;

import java.util.ArrayList;

/**
 * Clase encargada de implementar las opciones que los empleados de
 * financiacion de la tienda pueden llevar a cabo con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoFinanciacion extends UIMenuEmpleado{
    
	//Empleado usando el programa
    private UIEpdoFinanciacion financiador;

    //Metodo constructor
    public UIMenuEpdoFinanciacion(UIEpdoFinanciacion usuario){
    	super(usuario);
        this.financiador = usuario;
        
        //Anade las opciones al menu
        anadirOpciones();
    }
    
    /**
     * Devuelve el financiador asociado al menu
     */
    private UIEpdoFinanciacion obtenerFinanciador(){
        return financiador;
    }
    
    /**
     * Anade una opcion por cada funcion que el financiador puede realizar
     * 
     * (0) Buscar productos (Fija)
     * (1) Buscar usuarios (Fija)
     * (2) Dar de alta a un cliente
     * (3) Actualizar datos de un cliente
     * (4) Ver los datos de un cliente
     * (5) Ver lista de facturas de un cliente
     * (6) Cerrar sesion (Fija)
     * (7) Salir del programa (Fija)
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(UIMensajes.mF_OpcionDarAlta());
        listaOpciones.add(UIMensajes.mF_OpcionActualizarDatos());
        listaOpciones.add(UIMensajes.mF_OpcionVerDatosCliente());
        listaOpciones.add(UIMensajes.mF_OpcionVerFacturasCliente());
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
            case 2: //Dar de alta a un cliente
            obtenerFinanciador().darAlta();
            break;
            
            case 3: //Actualizar datos de un cliente
            obtenerFinanciador().actualizarDatosCliente();
            break;
            
            case 4: //Ver los datos de un cliente
            obtenerFinanciador().imprimirDatosCliente();
            break;
            
            case 5: //Ver lista de facturas de un cliente
            obtenerFinanciador().verListaFacturas();
            break;
        }
        activar(); //Activar de nuevo el menu
    }
}
