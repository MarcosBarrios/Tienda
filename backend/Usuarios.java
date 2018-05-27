package backend;

import java.util.ArrayList;

/**
 * Contiene la base de datos de todos los usuarios del programa
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Usuarios{
    
    //Lista contenedora de los usuarios
    private ArrayList<Usuario> listaUsuarios;
    
    //Metodo constructor
    public Usuarios(){
        listaUsuarios = new ArrayList<Usuario>();
    }
    
    /**
     * Devuelve la lista de usuarios
     * 
     * @return listaUsuarios Lista con los usuarios del programa
     */
    private ArrayList<Usuario> obtenerListaUsuarios(){
        return listaUsuarios;
    }
    
    /**
     * Anade un usuario a la coleccion.
     * 
     * @param usuario Usuario a anadir
     */
    public void anadirUsuario(Usuario usuario){
        obtenerListaUsuarios().add(usuario);
    }
    
    /**
     * Elimina un usuario de la coleccion.
     * 
     * Es necesario el uso de un metodo de busqueda para encontrar el 
     * identificador adecuado.
     * 
     * @param id Identificador (entero) del usuario a eliminar
     */
    public void eliminarUsuario(int id){
        obtenerListaUsuarios().remove(id);
    }
    
    /**
     * Obtiene un usuario de la coleccion mediante su nombre,
     * email o dni.
     * 
     * @param nombreOemailOdni nombre, email o dni del usuario objetivo
     * @return obtenerListaUsuarios().get(aux)
     */
    public Usuario obtenerUsuario(String nombreOemailOdni){
        
        for(int i = 0; i < obtenerListaUsuarios().size(); i++){
            Usuario temp = obtenerListaUsuarios().get(i);
            String nombre = temp.obtenerNombreUsuario().toLowerCase();
            String email = temp.obtenerEmailUsuario().toLowerCase();
            String dni = temp.obtenerDNI().toLowerCase();
            if(nombre.equals(nombreOemailOdni.toLowerCase())){
                return temp;
            }else if (email.equals(nombreOemailOdni.toLowerCase())){
                return temp;
            }else if(dni.equals(nombreOemailOdni.toLowerCase())){
                return temp;
            }
        }
        
        return null;
    }
    
    /**
     * Devuelve un usuario utilizando el identificador de
     * posicion dentro de la lista que contiene la base de
     * datos de usuarios.
     * 
     * @param id Posicion del usuario en la lista
     * @return obtenerListaUsuarios().get(id)
     */
    public Usuario obtenerUsuario(int id){
        return obtenerListaUsuarios().get(id);
    }
    
    /**
     * Devuelve la cantidad de usuarios que hay en la base de datos
     * de la tienda.
     * 
     * @return listaProductos.size() Tamano de la base de datos de usuarios
     */
    public int obtenerTamano(){
        return obtenerListaUsuarios().size();
    }
    
    /**
     * Devuelve un logeador para el sistema de inicio
     * de sesion del programa.
     * 
     * @return Logeador de esta base de datos de usuarios
     */
    public Logger obtenerLogeador() {
    	return new Logger(this);
    }
    
}
