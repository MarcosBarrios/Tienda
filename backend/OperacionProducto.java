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
    private int numeroProducto;
    
    //Metodo constructor
    public OperacionProducto(int numeroProducto, Empleado responsable, String descripcion, 
    int dia, int mes, int año){
        super(responsable, descripcion, dia, mes, año);
        this.numeroProducto = numeroProducto;
    }
    
    /**
     * Asigna o reasigna el numero de producto sobre el cual se hace
     * la operacion
     * 
     * @param numeroProducto Numero del producto asignado a la operacion
     */
    public void asignarNumeroProducto(int numeroProducto){
        this.numeroProducto = numeroProducto;
    }
    
    /**
     * Devuelve el producto asignado a la operacion
     * 
     * @return nuevoProducto Numero del producto asignado a la operacion
     */
    public int obtenerNumeroProducto(){
        return numeroProducto;
    }
    
}
