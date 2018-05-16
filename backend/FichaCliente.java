package backend;

import productos.Producto;

import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;

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
    
    //Historial de facturas
    private Stack<Factura> historialFacturas;
    
    //Lista de solicitudes
    private ArrayList<Solicitud> listaSolicitudes;
    
    //Historial de operaciones
    private Stack<Operacion> historialOperaciones;
    
    //Metodo constructor
    public FichaCliente(){
        //Iniciamos las colecciones
        listaProductosComprados = new Stack<Producto>();
        historialFacturas = new Stack<Factura>();
        listaSolicitudes = new ArrayList<Solicitud>();
        historialOperaciones = new Stack<Operacion>();
    }
    
    /**
     * Añade una operacion al historial
     * 
     * @param operacion Operacion a añadir
     */
    public void añadirOperacion(Operacion operacion){
        historialOperaciones.add(operacion);
    }
    
    /**
     * Devuelve una operacion en la posicion id
     * 
     * @param id Posicion de la operacion en la coleccion
     */
    public Operacion obtenerOperacion(int id){
        return historialOperaciones.get(id);
    }
    
    /**
     * Devuelve el numero de operaciones almacenadas en
     * el historial de operaciones.
     * 
     * @return historialOperaiones.size()
     */
    public int obtenerNumeroOperaciones(){
        return historialOperaciones.size();
    }
    
    /**
     * Añade una solicitud a la lista de solicitudes
     * 
     * @param solicitud Solicitud a añadir
     */
    public void añadirSolicitud(Solicitud solicitud){
        listaSolicitudes.add(solicitud);
    }
    
    /**
     * Eliminar una solicitud de la lista de solicitudes
     * 
     * @param id Posicion de la solicitud en la lista
     */
    public void eliminarSolicitud(int id){
        listaSolicitudes.remove(id);
    }
    
    /**
     * Obtiene una solicitud de la colección de solicitudes
     * 
     * 
     * @param id Posicion en coleccion (false) / Numero de solicitud (true)
     * @param usarNumeroSolicitud true: id=numeroSolicitud, false: id=pos(ArrayList)
     */
    public Solicitud obtenerSolicitud(int id, boolean usarNumeroSolicitud){
        
        if(usarNumeroSolicitud){
            Iterator<Solicitud> itr = listaSolicitudes.iterator();
            while(itr.hasNext()){
                Solicitud temp = itr.next();
                if(temp.obtenerNumeroSolicitud()==id){
                    return temp;
                }else{
                    return null;
                }
            }
        }else{
            return listaSolicitudes.get(id);
        }
        
        return null;
    }
    
    /**
     * Devuelve el numero de solicitudes almacenadas
     * 
     * @return listaSolicitudes.size()
     */
    public int obtenerNumeroSolicitudes(){
        return listaSolicitudes.size();
    }
    
    /**
     * Añade una factura
     * 
     * @param factura Factura a añadir
     */
    public void añadirFactura(Factura factura){
        historialFacturas.add(factura);
    }
    
    /**
     * Elimina una factura
     * 
     * @param id Posicion en la coleccion de la factura a eliminar
     */
    public void eliminarFactura(int id){
        historialFacturas.get(id);
    }
    
    /**
     * Obtiene una factura utilizando su posicion en la lista
     * 
     * @param id Posicion en la coleccion de la factura a obtenerinar
     */
    public Factura obtenerFactura(int id ){
        return historialFacturas.get(id);
    }
    
    /**
     * Devuelve el numero de facturas almacenadas en el historial
     * de facturas del cliente.
     * 
     * @return historialFacturas.size()
     */
    public int obtenerNumeroFacturas(){
        return historialFacturas.size();
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
     * Devuelve la cantidad de productos comprados por el cliente
     * 
     * @return listaProductosComprados.size();
     */
    public int obtenerNumeroProductosComprados(){
        return listaProductosComprados.size();
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
