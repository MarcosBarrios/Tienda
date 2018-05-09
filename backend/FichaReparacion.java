package backend;

import productos.Producto;

/**
 * Contiene un producto y el propietario del producto. Necesaria
 * para que los tecnicos trabajen con dicha informacion accesible.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class FichaReparacion{
    
    private Producto producto;
    
    private Cliente propietario;
    
    //Metodo constructor
    public FichaReparacion(Producto producto, Cliente propietario){
        this.producto = producto;
        this.propietario = propietario;
    }
    
    /**
     * Asigna o reasigna el producto
     * 
     * @param producto
     */
    public void asignarProducto(Producto producto){
        this.producto = producto;
    }
    
    /**
     * Devuelve el producto
     * 
     * @return producto
     */
    public Producto obtenerProducto(){
        return producto;
    }
    
    /**
     * Asigna o reasigna el propietario del producto
     * 
     * @param propietario
     */
    public void asignarPropietario(Cliente propietario){
        this.propietario = propietario;
    }
    
    /**
     * Devuelve el propietario del producto
     * 
     * @return propietario
     */
    public Cliente obtenerPropietario(){
        return propietario;
    }
    
}
