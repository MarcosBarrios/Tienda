package uitextual;

import java.util.ArrayList;

import backend.Logger;

/**
 * Clase encargada de imprimir y obtener entradas para el menu que se abre
 * automaticamente tras iniciar el programa para permitir la interaccion
 * usuario-programa
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuPrincipal extends UIMenuAccionable{

	//Fecha actual
    private int diaActual, mesActual, anoActual;
    
    //Parte funcional del gestor de cuentas
    private Logger logeador;
	
    //Metodo constructor
    public UIMenuPrincipal(Logger logeador, int diaActual,
    		int mesActual, int anoActual){
    	super();
        //Añade las opciones al menu
        anadirOpciones();
    }
    
    /**
     * Devuelve la parte funcional del gestor de cuentas
     * 
     * @return logeador Parte funcional del gestor de cuentas
     */
    private Logger obtenerLogeador() {
    	return logeador;
    }
    
    /**
     * Devuelve el dia actual en el que se esta usando el programa.
     * 
     * @return diaActual Dia actual
     */
    private int obtenerDiaActual(){
        return diaActual;
    }
    
    /**
     * Devuelve el mes actual en el que se esta usando el programa.
     * 
     * @return mesActual Mes actual
     */
    private int obtenerMesActual(){
        return mesActual;
    }
    
    /**
     * Devuelve el ano actual en el que se esta usando el programa.
     * 
     * @return anoActual Ano actual
     */
    private int obtenerAnoActual(){
        return anoActual;
    }
    
    /**
     * Añade una opcion por cada funcion que el usuario puede
     * usar.
     * 
     * (0) Iniciar sesion
     * (1) Gestionar usuarios
     * (2) Salir del programa
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(UIMensajes.mP_OpcionIniciarSesion());
        listaOpciones.add(UIMensajes.mP_OpcionGestionarUsuarios());
        listaOpciones.add(UIMensajes.g_OpcionSalir());
        obtenerMenu().anadirListaOpciones(listaOpciones);
    }
    
    /**
     * Imprime las opciones y obtiene una entrada con el numero
     * de opcion que el usuario quiere usar.
     */
    public void activar() {
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
            case 0: //Iniciar sesion
        	UILogger.iniciarSesion(this, obtenerLogeador(), 
        			obtenerDiaActual(), obtenerMesActual(),
        			obtenerAnoActual());
            break;
            
            case 1: //"Gestionar usuarios"
        	UILogger.accederGestionUsuarios(obtenerLogeador());
            break;
            
            case 2: //Salir del programa
            System.exit(0);
            break;
        }
    }
}