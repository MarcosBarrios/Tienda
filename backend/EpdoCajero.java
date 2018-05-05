package backend;

import productos.*;

/**
 * Los cajeros son los encargados de trabajar con los diferentes productos.
 * 
 * Entre sus tareas estan la de ordenar los electrodomesticos, asegurarse de que
 * siempre hay electrodomesticos, cobrar a los clientes, resolver dudas de clientes,
 * etc...
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoCajero extends Empleado{
    
    //Indica el numero de cada en la que se encuentra
    private int numeroCaja;
    
    //true -> esta asignado a una caja
    //false -> el cajero no se encuentra en ninguna caja
    private boolean estaOperando;
    
    //Metodo constructor
    public EpdoCajero(String dni, String nombre, String email, 
        String usuario, String contraseña){
        super(dni, nombre, email, usuario, contraseña);
    }
    
    /**
     * Cambia el estado de la variable booleana que indica si el cajero
     * tiene una caja asignada y esta efectivamente en ella.
     * 
     * @param estaOperando Nuevo estado de la variable booleana
     */
    public void cambiarEstadoOperando(boolean estaOperando){
        this.estaOperando = estaOperando;
    }
    
    /**
     * Devuelve el estado de la variable booleana que indica si el cajero
     * tiene una caja asignada y esta efectivamente en ella.
     * 
     * @return estaOperando Indica si el cajero esta efectivamente en caja y operando o no
     */
    public boolean obtenerEstadoOperando(){
        return estaOperando;
    }
    
    /**
     * Asigna el numero de caja en el que se encuentra operando el cajero.
     * 
     * @param numeroCaja Numero de caja en el que va a operar el cajero
     */
    public void asignarNumeroCaja(int numeroCaja){
        this.numeroCaja = numeroCaja;
    }
    
    /**
     * Devuelve el numero de caja en el que se encuentra operando el cajero
     * 
     * @return numeroCaja Numero de caja en el que se encuentra
     */
    public int obtenerNumeroCaja(){
        return numeroCaja;
    }
    
    /**
     * Añade un producto a la base de datos
     * 
     * @param productos Base de datos de productos
     * @param producto Producto a añadir
     */
    public void añadirProducto(Productos productos, Producto producto){
        productos.añadirProducto(producto);
    }
    
    /**
     * Metodo usado para vender un producto a un cliente.
     * 
     * El producto se añade a la coleccion de productos y a la 
     * coleccion de productos vendidos del cliente en concreto.
     * 
     * Se guarda la fecha de venta para poder calcular la garantia.
     * 
     * Se cambia el estado de la variable "comprado" a true
     * 
     * Se indica si ha sido financiado o no.
     * 
     * @param producto Producto a vender
     * @param cliente Cliente que compra el producto
     */
    public void venderProducto(Productos productos, Producto producto, 
        Cliente cliente, String fecha, boolean financiado){
        String[] aux = fecha.split("/"); //Divide la cadena (xx/xx/xxxx)
        producto.asignarDiaCompra(Integer.parseInt(aux[0]));
        producto.asignarMesCompra(Integer.parseInt(aux[1]));
        producto.asignarAñoCompra(Integer.parseInt(aux[2]));
        producto.cambiarComprado(true);
        producto.cambiarFinanciado(financiado);
        
        cliente.obtenerFichaCliente().añadirProductoComprado(producto);
        productos.añadirProducto(producto);
    }
    
    /**
     * Metodo usado para vender un producto a un cliente. Version
     * con enteros en vez de cadena de caracteres para asignar fecha.
     * 
     * El producto se añade a la coleccion de productos y a la 
     * coleccion de productos vendidos del cliente en concreto.
     * 
     * Se guarda la fecha de venta para poder calcular la garantia.
     * 
     * Se cambia el estado de la variable "comprado" a true
     * 
     * Se indica si ha sido financiado o no.
     * 
     * @param producto Producto a vender
     * @param cliente Cliente que compra el producto
     */
    public void venderProducto(Productos productos, Producto producto, 
        Cliente cliente, int diaComprado, int mesComprado,
        int añoComprado, boolean financiado){
        producto.asignarDiaCompra(diaComprado);
        producto.asignarMesCompra(mesComprado);
        producto.asignarAñoCompra(añoComprado);
        producto.cambiarComprado(true);
        producto.cambiarFinanciado(financiado);
        
        cliente.obtenerFichaCliente().añadirProductoComprado(producto);
        productos.añadirProducto(producto);
    }
    
}