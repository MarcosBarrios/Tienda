package uitextual;

import backend.Usuario;
/**
 * Clase especifica para la parte textual del programa que implementa
 * metodos que los usuarios del programa necesitan.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIUsuario extends UIObjeto{
    
    //Clase a la que representa UIUsuario
    private Usuario usuario;
    
    //Metodo constructor
    public UIUsuario(Usuario usuario){
    	this.usuario = usuario;
    }
    
    /**
     * Devuelve el usuario utilizando el programa
     * 
     * @return usuario Usuario utilizando el programa
     */
    protected Usuario obtenerUsuario(){
        return usuario;
    }
    
}
