package backend;

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
    
    public Empleado(String nombre, String email, String usuario, String contraseña){
        super(nombre, email);
        this.usuario = usuario;
        this.contraseña = contraseña;
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
