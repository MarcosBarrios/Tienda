package backend;


/**
 * Pieza necesaria para realizar una reparacion
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Pieza{
    
    //Nombre y descripcion de la pieza
    private String nombre, descripcion;
    
    //Precio de la pieza
    private float precio;
    
    //Metodo constructor
    public Pieza(float precio, String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    /**
     * Asigna o reasigna la descripcion de la pieza
     * 
     * @param descripcion Descripcion de la pieza
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion de la pieza
     * 
     * @return descripcion Descripcion de la pieza
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
    /**
     * Asigna o reasigna el nombre de la pieza
     * 
     * @param nombre Nuevo nombre de la pieza
     */
    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Devuelve el nombre de la pieza
     * 
     * @return nombre Nombre de la pieza
     */
    public String obtenerNombre(){
        return nombre;
    }
    
    /**
     * Asigna o reasigna el precio de la pieza
     * 
     * @param precio Precio de la pieza
     */
    public void asignarPrecio(float precio){
        this.precio = precio;
    }
    
    /**
     * Devuelve el precio de la pieza
     * 
     * @return precio Precio de la pieza
     */
    public float obtenerPrecio(){
        return precio;
    }

}
