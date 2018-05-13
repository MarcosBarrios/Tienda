package backend;

import java.util.Stack;

/**
 * Clase encargada de definir los metodos comunes a todos los empleados
 * que utilizan el sistema. 
 * 
 * @author: Marcos Barrios
 * @version: 1.0
 */
public abstract class Empleado extends Usuario{
    
    //Datos con los que el empleado debe iniciar sesion
    private String usuario, contraseña;
    
    //Historial de operaciones
    public Stack<Operacion> historialOperaciones;
    
    public Empleado(String dni, String nombre, String email, String usuario, String contraseña){
        super(dni, nombre, email);
        this.usuario = usuario;
        this.contraseña = contraseña;
        historialOperaciones = new Stack<Operacion>();
    }
    
    /**
     * Añade una operacion al historial
     * 
     * @param operacion Operacion a añadir
     */
    public void añadirOperacion(Operacion operacion){
        historialOperaciones.add(operacion);
    }
    
    /**
     * Devuelve una operacion en la posicion id
     * 
     * @param id Posicion de la operacion en la coleccion
     */
    public Operacion obtenerOperacion(int id){
        return historialOperaciones.get(id);
    }
    
    /**
     * Devuelve el numero de operaciones almacenadas en
     * el historial de operaciones.
     * 
     * @return historialOperaiones.size()
     */
    public int obtenerNumeroOperaciones(){
        return historialOperaciones.size();
    }
    
    /**
     * Devuelve la contraseña asignada al empleado.
     * 
     * @return contraseña Contraseña para iniciar sesion
     */
    public String obtenerContraseña(){
        return contraseña;
    }
    
    /**
     * Asigna la contraseña que va a utilizar el empleado
     * para poder iniciar sesion en el programa.
     * 
     * @param contraseña Contraseña para iniciar sesion
     */
    public void asignarContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    
    /**
     * Devuelve el usuario asignado al empleado.
     * 
     * @return usuario Usuario para iniciar sesion
     */
    public String obtenerUsuario(){
        return usuario;
    }
    
    /**
     * Asigna el usuario que va a utilizar el empleado
     * para poder iniciar sesion en el programa.
     * 
     * @param usuario Usuario para iniciar sesion
     */
    public void asignarUsuario(String usuario){
        this.usuario = usuario;
    }
    
}
