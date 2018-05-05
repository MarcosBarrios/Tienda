package backend;

/**
 * Define la base de los métodos y variables que todos los usuarios 
 * necesitaran. 
 * 
 * Entre otras cosas dispone de datos personales como nombre, apellidos.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class Usuario{
    
    //Nombre completo del usuario
    private String nombreUsuario;
    
    //Email del usuario
    private String emailUsuario;
    
    //DNI del usuario
    private String dni; 
    
    //Matodo constructor
    public Usuario(String dni, String nombreUsuario, String emailUsuario){
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
    }
    
    /**
     * Asigna o cambia el dni del usuario
     * 
     * @param nuevoDNI Nuevo DNI del usuario
     */
    public void asignarDNI(String nuevoDNI){
        this.dni = nuevoDNI;
    }
    
    /**
     * Devuelve el DNI del usuario
     * 
     * @return dni DNI del usuario
     */
    public String obtenerDNI(){
        return dni;
    }
    
    /**
     * Asigna el nombre de usuario.
     * 
     * @param nombreUsuario El nombre del usuario
     */
    public void asignarNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Devuelve el nombre del usuario asignado.
     * 
     * @return nombreUsuario nombre del usuario asignado
     */
    public String obtenerNombreUsuario(){
        return nombreUsuario;
    }
    
    /**
     * Asigna el email del usuario.
     * 
     * @param emailUsuario El email del usuario
     */
    public void asignarEmailUsuario(String emailUsuario){
        this.emailUsuario = emailUsuario;
    }
    
    /**
     * Devuelve el email del usuario asignado.
     * 
     * @return emailUsuario email del usuario asignado
     */
    public String obtenerEmailUsuario(){
        return emailUsuario;
    }
    
}

