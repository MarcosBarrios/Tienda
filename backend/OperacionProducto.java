package backend;

import productos.Producto;

/**
 * Define una operacion sobre un producto
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class OperacionProducto extends Operacion{
    
    //Producto sobre le cual se ha hecho la operacion
    public Producto producto;
    
    //Metodo constructor
    public OperacionProducto(Producto producto){
        this.producto = producto;
    }
    
    /**
     * Asigna o reasigna el producto sobre el cual se hace
     * la operacion
     * 
     * @param producto Producto asignado a la operacion
     */
    public void asignarProducto(Producto producto){
        this.producto = producto;
    }
    
    /**
     * Devuelve el producto asignado a la operacion
     * 
     * @return producto Producto asignado a la operacion
     */
    public Producto obtenerProducto(){
        return producto;
    }
    
}
