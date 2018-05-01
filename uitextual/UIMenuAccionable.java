package uitextual;

import backend.Usuarios;
import productos.Productos;

/**
 * Base para todas las clases de menu que van a implementar las funciones
 * del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIMenuAccionable{
    
    //Menu contenedor de las opciones
    private UIMenu menu;
    
    //Usuario utilizando el menu
    private UIUsuario usuario;
    
    //Base de datos de usuarios del programa
    private Usuarios usuarios;
    
    //Base de datos de productos del programa
    private Productos productos;
    
    //Metodo constructor
    public UIMenuAccionable(Usuarios usuarios, Productos productos,
    UIUsuario usuario){
        this.usuarios = usuarios;
        this.usuario = usuario;
        this.productos = productos;
        
        menu = new UIMenu();
    }
    
    /**
     * Devuelve el menu con la lista de opciones
     * 
     * @return menu Contenedor de las opciones
     */
    protected UIMenu obtenerMenu(){
        return menu;
    }
    
    /**
     * Devuelve el usuario que esta usando el menu
     * 
     * @return usuario Usuario en el menu
     */
    protected UIUsuario obtenerUsuario(){
        return this.usuario;
    }
    
    /**
     * Devuelve la base de datos de usuarios
     * 
     * @return Usuarios base de datos de usuarios
     */
    protected Usuarios obtenerUsuarios(){
        return this.usuarios;
    }
    
    /**
     * Devuelve la base de datos de productos
     * 
     * @return Productos base de datos de productos
     */
    protected Productos obtenerProductos(){
        return this.productos;
    }
}
