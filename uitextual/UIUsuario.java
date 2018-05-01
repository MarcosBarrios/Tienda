package uitextual;


import backend.Usuario;
import backend.Usuarios;
import java.util.Iterator;

/**
 * Clase especifica para la parte textual del programa que implementa
 * metodos que los usuarios del programa necesitan.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIUsuario{
    
    //Clase a la que representa UIUsuario
    private Usuario usuario;
    
    //Metodo constructor
    public UIUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    /**
     * Imprime los datos del usuario (nombre y email)
     * 
     * Ejemplo formato: 
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  Nombre: Marcos Barrios
     *  Email: marcosloscardones@gmail.com
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    public void imprimirDatos(){
        System.out.println(UIMensajes.menuEncabezado());
        System.out.println(UIMensajes.informacionNombre() + ": " +
            usuario.obtenerNombreUsuario());
        System.out.println(UIMensajes.informacionEmail() + ": " +
            usuario.obtenerEmailUsuario());
        System.out.println(UIMensajes.menuEncabezado());
    }
    
    /**
     * Imprime en forma de lista los nombres y emails de los usuarios
     * que se encuentran en la base de datos del programa.
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirListaUsuarios(Usuarios usuarios){
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            System.out.println(UIMensajes.menuEncabezado());
            System.out.println(UIMensajes.informacionNombre() + ": " +
                usuarios.obtenerUsuario(i).obtenerNombreUsuario() + 
                "  " + usuarios.obtenerUsuario(i).obtenerEmailUsuario());
        }
    }
}
