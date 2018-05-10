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
        
        //Usuarios para testeo de funciones el durante desarrollo del programa
        Usuario cajeroPrueba = new EpdoCajero("11111111A", "Marcos Barrios", 
            "marcosloscardones@gmail.com", "unclainC", "1234");
        Usuario financiadorPrueba = new EpdoFinanciacion("11111112A",
            "Adrian Barrera", "adrian.zorro@gmail.com", "unclainF", "1234");
        Usuario postventaPrueba = new EpdoPostVenta("11111112A",
            "Postvento Barrera", "postventotolerante@gmail.com", "unclainPV", "1234");
        Usuario tecnicoPrueba = new EpdoTecnico("11111113A", 
            "Tecnico Noestupido", "tecnicoverdugo@gmail.com", "unclainT", "1234");
        
        //Usuario fijo para el menu gestion de usuarios
        Usuario gestionadorUsuarios = new EpdoFinanciacion("11111113A",
            "GESTION_USUARIOS", "GESTION_USUARIOS@gmail.com",
            "GESTION_USUARIOS", "12345");
        
        añadirUsuario(cajeroPrueba);
        añadirUsuario(financiadorPrueba);
        añadirUsuario(gestionadorUsuarios);
        añadirUsuario(postventaPrueba);
        añadirUsuario(tecnicoPrueba);
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
     * @return listaProductos.size() Tamaño de la base de datos de usuarios
     */
    public int obtenerTamaño(){
        return obtenerListaUsuarios().size();
    }
    
}
