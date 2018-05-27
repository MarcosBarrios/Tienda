package uitextual;

import backend.Empleado;
import backend.Logger;

/**
 * Clase encargada de gestionar los inicios de sesion en los
 * diferentes menus del programa.
 * 
 * @author: Marcos Barrios
 * @version: 1.0
 */
public class UILogger{

	//Menu al que volver tras cerrar sesion
	private static UIMenuAccionable menuPrevio = null;
	
	/**
	 * Devuelve el menu que hay que activar tras cerrar sesion
	 * @return menuPrevio
	 */
	private static UIMenuAccionable obtenerMenuPrevio() {
		return menuPrevio;
	}
	
	//Evitamos que se pueda instanciar UILogger
	private UILogger() {
		throw new IllegalStateException("Clase de utilidad");
	}
	
	/**
	 * Entra al programa como un empleado especifico usando los datos
     * de su cuenta (usuario y contrasena)
     * 
     * En caso de que el inicio de sesion falle vuelve al menu previo.
     * 
     * @param logeador Parte funcional del gestor de usuarios
     * @param menuPrevio Menu desde el que se quiere iniciar sesion
     * @param diaActual Dia en el que se esta usando el programa
     * @param mesActual Mes en el que se esta usando el programa
     * @param anoActual Ano en el que se esta usando el programa
	 */
	public static void iniciarSesion(UIMenuAccionable menuPrevio, Logger logeador, 
			int diaActual, int mesActual, int anoActual) {
		//Pregunta por usuario, contrasena y busca un empleado
		//con esos datos.
		Empleado empleado = comprobarEmpleado(logeador);
        
		//Si encuentra un empleado con los datos
        if(empleado != null){
            //"Se ha entrado a la cuenta con exito"
            System.out.println(UIMensajes.mP_ExitoLogin());
        	
            //Actualiza la fecha actual en la que se esta operando
            empleado = asignarFechaActual(empleado, diaActual, mesActual, anoActual);
            
            //Activamos el menu del empleado
            UIMenuAccionable menuActivo = empleado.obtenerUI().obtenerMenu();
            menuActivo.activar();
        }else{
            //Avisa de fallo y vuelve al menu, "Usuario o contrasena incorrectos"
            System.out.println(UIMensajes.mP_FalloLogin());
            
            cerrarSesion(); //LLama de nuevo al menuPrevio
        }
	}
	
	/**
	 * Devuelve un empleado con la fecha actual de operacion del
	 * programa asignada.
	 * 
	 * @param empleado Empleado al que asignar la fecha actual
	 * 
	 * @return Empleado con la nueva fecha actual
	 */
	private static Empleado asignarFechaActual(Empleado empleado, int diaActual,
			int mesActual, int anoActual) {
		empleado.asignarDiaActual(diaActual);
		empleado.asignarMesActual(mesActual);
		empleado.asignarAnoActual(anoActual);
		return empleado;
	}
	
	/**
	 * Activa el menu desde el que se utiliza el sistema
	 * de logeo.
	 */
	public static void cerrarSesion() {
		obtenerMenuPrevio().activar();
	}
	
	/**
	 * Obtiene un usuario y una contrasena y devuelve un empleado
	 * con un usuario y una contrasena igual. 
	 * 
	 * @param logeador Parte funcional del gestor de usuarios
	 * 
	 * @return Empleado cuyo usuario y contrasena coinciden con los especificados
	 */
	private static Empleado comprobarEmpleado(Logger logeador) {
		System.out.println(UIMensajes.g_EncabezadoMenus());
		
        //"Iniciar sesion", "Usuario", "Contrasena"
        System.out.println(UIMensajes.mP_OpcionIniciarSesion());
        
        //Obtenemos el usuario
        System.out.println("\t" + UIMensajes.g_Usuario() + ": ");
        String nombreUsuario = UIEntradas.obtenerCadena(false);
        
        //Obtenemos la contrasena
        System.out.println("\t" + UIMensajes.g_Contrasena() + ": ");
        String contrasena = UIEntradas.obtenerCadena(false);

        //Devuelve un empleado con los datos previamente especificados
        return logeador.comprobarEmpleado(nombreUsuario, contrasena);
	}
	
	/**
	 * Accede a la gestion de usuarios mediante su contrasena.
	 * 
	 * @param logeador Parte funcional del gestor de usuarios
	 */
	public static void accederGestionUsuarios(Logger logeador) {
		//Preguntamos por la contrasena de gestion de usuarios
		System.out.println("\t" + UIMensajes.g_Contrasena() + ": ");
        String contrasena = UIEntradas.obtenerCadena(false);
        
        //Buscamos un posible empleado con los datos previamente especificados
        Empleado gestorUsuarios = logeador.comprobarEmpleado("GESTION_USUARIOS",
        		contrasena);
        
        if(gestorUsuarios!=null){ //Si la contrasena es correcta
        	//"Se ha entrado a la cuenta con exito"
            System.out.println(UIMensajes.mP_ExitoLogin());
            
            UIMenuAccionable menuActivo = gestorUsuarios.obtenerUI().obtenerMenu();
            menuActivo.activar();
        }else{ //Si la contrasena NO es correcta
            //Avisa de fallo y vuelve al menu, "contrasena incorrecta"
            System.out.println(UIMensajes.mP_ContrasenaIncorrecta());
        }
	}
	
}
