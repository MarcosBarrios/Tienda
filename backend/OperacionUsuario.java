package backend;


/**
 * Define una operacion sobre un usuario.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class OperacionUsuario extends Operacion{
    
    //Usuario sobre le cual se ha hecho la operacion
    private Usuario usuario;
    
    //Metodo constructor
    public OperacionUsuario(Usuario usuario, Empleado responsable, String descripcion, 
    int dia, int mes, int ano){
        super(responsable, descripcion, dia, mes, ano);
        this.usuario = usuario;
    }
    
    /**
     * Asigna o reasigna el usuario sobre el cual se hace
     * la operacion
     * 
     * @param usuario Usuario asignado a la operacion
     */
    public void asignarUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    /**
     * Devuelve el usuario asignado a la operacion
     * 
     * @return usuario Usuario asignado a la operacion
     */
    public Usuario obtenerUsuario(){
        return usuario;
    }
    
}
