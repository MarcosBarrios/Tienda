package uitextual;

import java.util.ArrayList;

/**
 * En este menu se realizaran todas las operaciones que potencialmente
 * los duenos necesitaran llevar a cabo para el funcionamiento del
 * programa.
 * 
 * Para acceder al menu se emplea una contrasena pues el objetivo del
 * mismo es que al empezar a usar el programa sea posible asignar
 * los empleados de la tienda.
 * 
 * Entre las operaciones implementadas estan:
 * 
 *  - Anadir empleado al sistema
 *  - Actualizar nombre,email, usuario o contrasena de un empleado
 *  - Ver lista de usuarios
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuGestionUsuarios extends UIMenuEmpleado{
    
	//Empleado usando el programa
    private UIGestionUsuarios gestor;
    
    //Metodo constructor
    public UIMenuGestionUsuarios(UIGestionUsuarios usuario){
    	super(usuario);
        this.gestor = usuario;
        
        //Anade las opciones al menu
        anadirOpciones();
    }
    
    /**
     * Devuelve el gestor de usuarios
     * 
     * @return gestor Gestor de usuarios
     */
    private UIGestionUsuarios obtenerGestor(){
        return gestor;
    }
    
    /**
     * Anade las opciones con las que el usuario del programa interactuara
     * 
     * (0) Buscar productos (Fija)
     * (1) Buscar usuarios (Fija)
     * (2) Anadir empleado a la base de datos
     * (3) Actualizar los datos de un empleado
     * (4) Ver lista de empleados
     * (5) Ver lista de clientes
     * (6) Ver lista de fichas de reparacion
     * (7) Anadir solicitud a la ficha de un cliente
     * (8) Aceptar una solicitud de un cliente
     * (9) Ver una lista de solicitudes de todos los clientes
     * (10) Ver el historial de operaciones de un usuario
     * (11) Cerrar sesion (Fija)
     * (12) Salir del programa (Fija)
     * 
     */
    private void anadirOpciones(){
    	ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(UIMensajes.mGU_OpcionAnadirEmpleado());
        listaOpciones.add(UIMensajes.mGU_OpcionActualizarDatos());
        listaOpciones.add(UIMensajes.mGU_OpcionVerListaEmpleados());
        listaOpciones.add(UIMensajes.mGU_OpcionVerListaClientes());
        listaOpciones.add(UIMensajes.mGU_OpcionVerFichasReparacion());
        listaOpciones.add(UIMensajes.mGU_OpcionAnadirSolicitud());
        listaOpciones.add(UIMensajes.mGU_OpcionAceptarSolicitud());
        listaOpciones.add(UIMensajes.mGU_OpcionVerSolicitudes());
        listaOpciones.add(UIMensajes.mGU_OpcionVerHistorialUsuario());
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
            case 2: //Anadir empleado al sistema
        	obtenerGestor().registrarEmpleado();
            break;
            
            case 3: //Actualizar los datos de un empleado
        	obtenerGestor().actualizarDatosEmpleado();
            break;
            
            case 4: //Ver lista de empleados
        	obtenerGestor().imprimirListaEmpleados();
            break;
            
            case 5: //Ver lista de clientes
        	obtenerGestor().imprimirListaClientes();
            break;
            
            case 6: //Ver fichas de reparacion
        	obtenerGestor().verFichasReparacion();
            break;
            
            case 8: //Anadir solicitud a un cliente
        	obtenerGestor().anadirSolicitud();
            break;
            
            case 9: //Aceptar solicitud sobre un cliente
        	obtenerGestor().aceptarSolicitud();
            break;
            
            case 10: //Ver el historial de operaciones de un usuario
        	obtenerGestor().verHistorialUsuario();
            break;
            
            case 11: //Ver lista de solicitudes de todos los clientes
        	obtenerGestor().verListaSolicitudes();
            break;
        }
        activar(); //Activar de nuevo el menu
    }
    
}
