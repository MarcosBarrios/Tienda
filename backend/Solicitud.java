package backend;


/**
 * Una solicitud esta formada por el destinatario de la solicitud y
 * su descripcion ademas de una booleana que indique si se ha aceptado
 * o no.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Solicitud{
	
	//Numero de solicitud unico
    public static int numeroSolicitudes = 0;
    
    /**
     * Aumentamos el numero de solicitud para poder distinguir
     * entre solicitudes
     */
    public static void aumentarNumeroSolicitudes(){
        numeroSolicitudes++;
    }
    
    //Cliente que tiene que aceptar la solicitud
    private FichaCliente fcliente;
    
    //Descripcion de la solicitud
    private String descripcion;
    
    //Estado de la solicitud
    private boolean aceptada;
    
    //Numero de solicitud
    private int numeroSolicitud;
    
    public Solicitud(){}
    
    public Solicitud(boolean aceptada, FichaCliente fcliente, String descripcion){
        this.aceptada = aceptada;
        this.descripcion = descripcion;
        this.fcliente = fcliente;
    }
    
    /**
     * Asigna el numero de solicitudes al numero de solicitud.
     * 
     * Utilizado al anadir una nueva solicitud a un cliente.
     * 
     * @param numeroSolicitud Numero de la solicitud
     */
    public void asignarNumeroSolicitud(){
        this.numeroSolicitud = numeroSolicitudes;
    }
    
    /**
     * Devuelve el numero de solicitud por el cual
     * se hara referencia a la solicitud.
     * 
     * @return numeroSolicitud Numero de solicitud asignado a la solicitud
     */
    public int obtenerNumeroSolicitud(){
        return numeroSolicitud;
    }
    
    /**
     * Asigna o reasigna el cliente asociado a la solicitud
     * 
     * @param cliente Cliente asociado
     */
    public void asignarCliente(FichaCliente fcliente){
        this.fcliente = fcliente;
    }
    
    /**
     * Devuelve el cliente asociado a la solicitud
     * 
     * @return cliente Cliente asociado
     */
    public FichaCliente obtenerCliente(){
        return fcliente;
    }
    
    /**
     * Asigna o reasigna el estado de la variable booleana
     * que indica si se ha aceptado o no la solicitud
     * 
     * @param aceptada Estado de la solicitud
     */
    public void cambiarAceptada(boolean aceptada){
        this.aceptada = aceptada;
    }
    
    /**
     * Devuelve el valor de la variable que indica si se ha
     * aceptado la solicitud o no
     * 
     * @return aceptada Estado de la solicitud
     */
    public boolean obtenerAceptada(){
        return aceptada;
    }
    
    /**
     * Asigna o reasigna la descripcion de la solicitud
     * 
     * @param descripcion Descripcion de la solicitud
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion de la solicitud
     * 
     * @return descripcion Descripcion de la solicitud
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
}
