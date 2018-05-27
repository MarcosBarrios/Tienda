package uitextual;

import java.util.ArrayList;

import backend.Usuarios;

import productos.Productos;

/**
 * Base para todos los menus que utilizaran los empleados del programa
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIMenuEmpleado extends UIMenuAccionable{
	
	private UIEmpleado uiempleado;
	
    //Base de datos de usuarios
    private Usuarios usuarios;
    
    //Base de datos de productos
    private Productos productos;
	
	public UIMenuEmpleado(UIEmpleado uiempleado) {
		super();
		this.uiempleado = uiempleado;
		
		//Añadimos las opciones fijas para todo menu empleado
		anadirOpcionesFijas();
	}
	
	/**
     * Devuelve la base de datos de usuarios
     * 
     * @return usuarios Base de datos de usuarios
     */
    protected Usuarios obtenerUsuarios() {
    	return usuarios;
    }
    
    /**
     * Devuelve la base de datos de productos
     * 
     * @return productos Base de datos de productos
     */
    protected Productos obtenerProductos() {
    	return productos;
    }
	
    /**
     * Devuelve el empleado utilizando el menu
     * 
     * @return Empleado utilizando el menu
     */
	protected UIEmpleado obtenerUIEmpleado() {
		return uiempleado;
	}
	
	/**
	 * Anade las opciones que todo menu de empleado tiene que tener
	 */
	private void anadirOpcionesFijas() {
		ArrayList<String> listaOpciones = new ArrayList<String>();
    	listaOpciones.add(UIMensajes.b_OpcionBuscarProductos());
    	listaOpciones.add(UIMensajes.b_OpcionBuscarUsuarios());
    	listaOpciones.add(UIMensajes.g_CerrarSesion());
    	listaOpciones.add(UIMensajes.g_OpcionSalir());
    	obtenerMenu().anadirListaOpciones(listaOpciones);
	}
	
	/**
	 * Imprime el menu y activa la entrada de usuario
	 */
	protected abstract void activar();
	
	/**
	 * Implementa las opciones que todos los menus de empleado necesitan
	 * 
	 * @param entrada Opcion elegida por el usuario
	 */
	protected void entradaOpcionesFija(int entrada) {
		int nOpciones = obtenerMenu().obtenerNumeroOpciones();
		if(entrada==0) { //"Buscar productos"
			obtenerUIEmpleado().imprimirBusquedaProductos();
			activar();
		}else if(entrada==1) { //"Buscar usuarios"
			obtenerUIEmpleado().imprimirBusquedaUsuarios();
			activar();
		}else if(entrada==(nOpciones-1)) { //"Cerrar sesion"
			UILogger.cerrarSesion();
		}else if(entrada==nOpciones) { //"Salir del programa"
			System.exit(0);
		}
	}
}
