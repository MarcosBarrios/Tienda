package backend;

import productos.Producto;

import java.util.Stack;
import java.util.Iterator;

/**
 * Cada cliente va a tener asignada una ficha cliente tal que puedan
 * almacenar informacion como los productos comprados, las facturas,
 * los manuales, además de las promociones.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class FichaCliente{
    
    //Una pila es adecuada para un historial de productos
    private Stack<Producto> listaProductosComprados;
    
    //Metodo constructor
    public FichaCliente(){
        listaProductosComprados = new Stack<Producto>();
    }
    
    /**
     * Añade al historial un producto que el cliente ha comprado.
     * 
     * @param producto Producto comprado por el cliente
     */
    public void añadirProductoComprado(Producto producto){
        listaProductosComprados.add(producto);
    }
    
    /**
     * Elimina un producto del historial de productos comprados. 
     * 
     * Si usarNumeroProducto = false es necesario el uso de un metodo 
     * de busqueda para encontrar el identificador adecuado.
     * 
     * @param id Posicion en coleccion (false) / Numero de producto (true)
     * @param usarNumeroProducto true: id=numeroProducto, fase: id=pos(ArrayList)
     */
    public void eliminarProductoComprado(int id, boolean usarNumeroProducto){
        if(usarNumeroProducto){
            Iterator<Producto> itr = listaProductosComprados.iterator();
            while(itr.hasNext()){
                Producto temp = itr.next();
                if(temp.obtenerNumeroProducto()==id){
                    itr.remove();
                }
            }
        }else{
            if(id>=0 && id < listaProductosComprados.size()){
                listaProductosComprados.remove(id);
            }
        }
    }
    
    /**
     * Obtiene un producto del historial de productos comprados.
     * 
     * Si sarNumeroProducto = false es necesario el uso de un metodo 
     * de busqueda para encontrar el identificador adecuado.
     * 
     * @param id Posicion en coleccion (false) / Numero de producto (true)
     * @param usarNumeroProducto true: id=numeroProducto, fase: id=pos(ArrayList)
     */
    public Producto obtenerProductoComprado(int id, boolean usarNumeroProducto){
        
        if(usarNumeroProducto){
            Iterator<Producto> itr = listaProductosComprados.iterator();
            while(itr.hasNext()){
                Producto temp = itr.next();
                if(temp.obtenerNumeroProducto()==id){
                    return temp;
                }else{
                    return null;
                }
            }
        }
        
        return listaProductosComprados.get(id);
    }
    
}
