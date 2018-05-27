package productos;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contiene la base de datos de productos de la tienda.
 * 
 * @author Marcos Barrios 
 * @version 1.0
 */
public class Productos{
    //Encargada de asignar un entero unico a cada producto
    private static int contadorProductos = 0;
    
    //Lista contenedora de los elementos
    private ArrayList<Producto> listaProductos;
    
    //Metodo constructor
    public Productos(){
        listaProductos = new ArrayList<Producto>();
    }
    
    /**
     * Devuelve la base de datos de productos del programa
     * 
     * @return listaProductos Base de datos de productos del programa
     */
    private ArrayList<Producto> obtenerListaProductos(){
        return listaProductos;
    }
    
    /**
     * Anade un producto a la coleccion.
     * 
     * Aumenta en 1 el contador de productos cada ves que se
     * anade un producto.
     * 
     * @param producto Producto a anadir
     */
    public void anadirProducto(Producto producto){
        contadorProductos++;
        producto.asignarNumeroProducto(contadorProductos);
        obtenerListaProductos().add(producto);
    }
    
    /**
     * Nota (10/03/2018): Puede que no sea necesario el uso de este metodo
     * 
     * Elimina un producto de la coleccion.
     * 
     * Es necesario el uso de un metodo de busqueda para encontrar el 
     * identificador adecuado.
     * 
     * @param id Identificador (entero) del producto a eliminar
     */
    public void eliminarElemento(int id){
        obtenerListaProductos().remove(id);
    }
    
    /**
     * Obtiene un producto de la colección.
     * 
     * 
     * @param id Posicion en coleccion (false) / Numero de producto (true)
     * @param usarNumeroProducto true: id=numeroProducto, false: id=pos(ArrayList)
     */
    public Producto obtenerProducto(int id, boolean usarNumeroProducto){
        
        if(usarNumeroProducto){
            Iterator<Producto> itr = obtenerListaProductos().iterator();
            while(itr.hasNext()){
                Producto temp = itr.next();
                if(temp.obtenerNumeroProducto()==id){
                    return temp;
                }else{
                    return null;
                }
            }
        }else{
            return obtenerListaProductos().get(id);
        }
        
        return null;
    }
    
    /**
     * Devuelve la cantidad de productos que hay en la base de datos
     * de la tienda.
     * 
     * @return listaProductos.size() Tamano de la base de datos de productos
     */
    public int obtenerTamano(){
        return obtenerListaProductos().size();
    }
    
}