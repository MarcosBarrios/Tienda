package backend;

import java.util.ArrayList;
import java.util.Iterator;

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
        Usuario cajeroPrueba = new EpdoCajero("Marcos Barrios", 
            "marcosloscardones@gmail.com", "unclainC", "1234");
        Usuario financiadorPrueba = new EpdoFinanciacion(
            "Adrian Barrera", "adrian.zorro@gmail.com", "unclainF", "1234");
        añadirUsuario(cajeroPrueba);
        añadirUsuario(financiadorPrueba);
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
     * Añade un usuario a la coleccion.
     * 
     * @param usuario Usuario a añadir
     */
    public void añadirUsuario(Usuario usuario){
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
     * Obtiene un usuario de la coleccion mediante su nombre o
     * email.
     * 
     * @param nombreOemail nombre o email del usuario objetivo
     * @return obtenerListaUsuarios().get(aux)
     */
    public Usuario obtenerUsuario(String nombreOemail){
        
        for(int i = 0; i < obtenerListaUsuarios().size(); i++){
            Usuario temp = obtenerListaUsuarios().get(i);
            if(temp.obtenerNombreUsuario().equals(nombreOemail)){
                return temp;
            }else if (temp.obtenerEmailUsuario().equals(nombreOemail)){
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
     * @return obtenerListaUsuarios().get(id)
     */
    public Usuario obtenerUsuario(int id){
        return obtenerListaUsuarios().get(id);
    }
    
    /**
     * Devuelve la cantidad de usuarios que hay en la base de datos
     * de la tienda.
     * 
     * @return listaProductos.size() Tamaño de la base de datos de usuarios
     */
    public int obtenerTamaño(){
        return obtenerListaUsuarios().size();
    }
    
}
