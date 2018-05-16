package productos;

import backend.OperacionProducto;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase base para todos los productos de la tienda. Define métodos generales
 * que van a ser necesarios en todos los tipos de producto.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class Producto{
    
    //Cantidad de productos que hay en la tienda
    private int cantidad;
    
    //Precio del producto a la hora de venderlo/comprarlo (en euros)
    private float precio;

    //El peso del producto en si (en kg)
    private float peso;

    //Numero unico del producto (para idenficaciones)
    private int numeroProducto;

    //Necesaria para el sistema de financiacion
    private boolean financiado;

    //Descripcion del producto
    private String descripcionProducto;

    //Indica si el producto ha sido comprado o no
    private boolean comprado;

    //Fecha de compra del producto
    private int diaCompra, mesCompra, añoCompra; 
    
    //Fecha del ultimo reporte añadido
    private int diaUltimoReporte, mesUltimoReporte, añoUltimoReporte;

    //Tiempo antes de que se acabe la garantia (años)
    private int tiempoGarantia;
    
    //Numero de la caja en la que se vendio
    private int numeroCaja;

    //Contiene los estados {"INTACTO", "ROTO", "DEVUELTO"}
    private EnumEstadoProducto estadoProducto;

    //Coleccion con caracteristicas extras añadidas en ejecucion
    private ArrayList<Caracteristica> listaCaracteristicas;

    //Coleccion con el historial de reportes
    private Stack<Reporte> historialReportes;
    
    //Metodo constructor
    public Producto(){
        precio = 1f; //Precio predeterminado (euros)
        peso = 1f; //Peso predeterminado (kg)
        numeroProducto = 0; //Numero asignado al añadirlo a la base de datos

        financiado = false; //Indica si esta financiado

        estadoProducto = EnumEstadoProducto.INTACTO; //Intacto y no vendido
        
        //Iniciamos las colecciones
        listaCaracteristicas = new ArrayList<Caracteristica>();
        historialReportes = new Stack<Reporte>();
    }
    
    /**
     * Añade un reporte al historial de reportes y asigna
     * el estado del reporte al producto
     * 
     * @param reporte Nuevo reporte a añadir
     */
    public void añadirReporte(Reporte reporte){
        //Asignamos el nuevo estado del producto
        cambiarEstado(reporte.obtenerNuevoEstado());
        
        //Asignamos la fecha del ultimo reporte
        diaUltimoReporte = reporte.obtenerDiaReporte();
        mesUltimoReporte = reporte.obtenerMesReporte();
        añoUltimoReporte = reporte.obtenerAñoReporte();
        
        historialReportes.add(reporte);
    }
    
    /**
     * Devuelve el numero de reportes en el historial de
     * reportes del producto
     * 
     * @return historialReportes.size()
     */
    public int obtenerNumeroReportes(){
        return historialReportes.size();
    }
    
    /**
     * Devuelve un reporte mediante su posicion en la
     * coleccion
     * 
     * @param id Posicion del reporte en la coleccion
     */
    public Reporte obtenerReporte(int id){
        return historialReportes.get(id);
    }
    
    /**
     * Asigna la cantidad de productos que hay
     * 
     * @param cantidad Nueva cantidad de productos
     */
    public void asignarCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    /**
     * Asigna o reasigna el dia en el que se añadio el ultimo
     * reporte al producto
     * 
     * @param diaUltimoReporte Dia del ultimo reporte
     */
    public void asignarDiaUltimoReporte(int diaUltimoReporte){
        this.diaUltimoReporte = diaUltimoReporte;
    }
    
    /**
     * Devuelve el ultimo dia en el que se añadio el ultimo
     * reporte al producto.
     * 
     * @return diaUltimoReporte Dia del ultimo reporte
     */
    public int obtenerDiaUltimoReporte(){
        return diaUltimoReporte;
    }
    
    /**
     * Asigna o reasigna el mes en el que se añadio el ultimo
     * reporte al producto
     * 
     * @param mesUltimoReporte Mes del ultimo reporte
     */
    public void asignarMesUltimoReporte(int mesUltimoReporte){
        this.mesUltimoReporte = mesUltimoReporte;
    }
    
    /**
     * Devuelve el ultimo mes en el que se añadio el ultimo
     * reporte al producto.
     * 
     * @return mesUltimoReporte Mes del ultimo reporte
     */
    public int obtenerMesUltimoReporte(){
        return mesUltimoReporte;
    }
    
    /**
     * Asigna o reasigna el año en el que se añadio el ultimo
     * reporte al producto
     * 
     * @param añoUltimoReporte Año del ultimo reporte
     */
    public void asignarAñoUltimoReporte(int mesUltimoReporte){
        this.añoUltimoReporte = añoUltimoReporte;
    }
    
    /**
     * Devuelve el ultimo año en el que se añadio el ultimo
     * reporte al producto.
     * 
     * @return añoUltimoReporte Año del ultimo reporte
     */
    public int obtenerAñoUltimoReporte(){
        return añoUltimoReporte;
    }
    
    /**
     * Asigna o reasigna el numero de la caja en la que se vendio
     * el producto
     * 
     * @param numeroCaja Numero de la caja en la que se vendio el producto
     */
    public void asignarNumeroCaja(int numeroCaja){
        this.numeroCaja = numeroCaja;
    }
    
    /**
     * Obtener numero de la caja en la que se vendio el producto
     * 
     * @return numeroCaja Numero de la caja en la que se vendio el producto
     */
    public int obtenerNumeroCaja(){
        return numeroCaja;
    }
    
    /**
     * Devuelve la cantidad de productos
     * 
     * @return cantidad Cantidad de productos
     */
    public int obtenerCantidad(){
        return cantidad;
    }
    
    /**
     * Devuelve el estado de la variable financiado
     * 
     * @return financiado true=producto financiado
     */
    public boolean obtenerEstadoFinanciado(){
        return financiado;
    }

    /**
     * true: Ha sido comprado
     * false: NO ha sido comprado
     * 
     * @return comprado Indica si el producto ha sido comprado o no
     */
    public boolean obtenerEstadoComprado(){
        return comprado;
    }

    /**
     * Asigna la variable booleana que indica si el producto
     * ha sido comprado o no
     * 
     * true: ha sido comprado
     * false: NO ha sido comprado
     * 
     * @param comprado Indica si el producto ha sido comprado o no
     */
    public void cambiarComprado(boolean comprado){
        this.comprado = comprado;
    }

    /**
     * Devuelve el dia en el que se compro el producto
     * 
     * @return diaCompra Dia en el que se compro el producto
     */
    public int obtenerDiaCompra(){
        return diaCompra;
    }

    /**
     * Devuelve el mes en el que se compro el producto
     * 
     * @return mesCompra Mes en el que se compro el producto
     */
    public int obtenerMesCompra(){
        return mesCompra;
    }

    /**
     * Devuelve el año en el que se compro el producto
     * 
     * @return añoCompra Año en el que se compro el producto
     */
    public int obtenerAñoCompra(){
        return añoCompra;
    }

    /**
     * Asigna el dia en el que se compro el producto
     * 
     * @param diaCompra Dia en el que se compro el producto
     */
    public void asignarDiaCompra(int diaCompra){
        this.diaCompra = diaCompra;
    }

    /**
     * Asigna el mes en el que se compro el producto
     * 
     * @param mesCompra Mes en el que se compro el producto
     */
    public void asignarMesCompra(int mesCompra){
        this.mesCompra = mesCompra;
    }

    /**
     * Asigna el año en el que se compro el producto
     * 
     * @param añoCompra Año en el que se compro el producto
     */
    public void asignarAñoCompra(int añoCompra){
        this.añoCompra = añoCompra;
    }

    /**
     * Devuelve el tiempo en años hasta que se termine la garantia
     * del producto
     * 
     * @return tiempoGarantia Tiempo en años hasta que termine la garantia
     */
    public int obtenerTiempoGarantia(){
        return tiempoGarantia;
    }

    /**
     * Asigna el tiempo en años hasta que se termine la garantia
     * del producto
     * 
     * @param tiempoGarantia Tiempo en años hasta que termina la garantia
     */
    public void asignarTiempoGarantia(int tiempoGarantia){
        this.tiempoGarantia = tiempoGarantia;
    }

    /**
     * Añade una caracteristica a la coleccion del producto
     * 
     * @param caracteristica Caracteristica a añadir
     */
    public void añadirCaracteristica(Caracteristica caracteristica){
        listaCaracteristicas.add(caracteristica);
    }

    /**
     * Elimina una caracteristica de la coleccion del producto
     * Es necesario utilizar un metodo de busqueda para encontrar
     * el identificador (entero) del producto buscado
     * 
     * @param id Identificador (entero) del producto a eliminar
     */
    public void eliminarCaracteristica(int id){
        listaCaracteristicas.remove(id);
    }

    /**
     * Devuelve una caracteristica de la coleccion del producto
     * Es necesario utilizar un metodo de busqueda para encontrar
     * el identificador (entero) del producto buscado.
     * 
     * @param id Identificador (Entero) del producto a eliminar
     */
    public Caracteristica obtenerCaracteristica(int id){
        return listaCaracteristicas.get(id);
    }
    
    /**
     * Devuelve la cantidad de caracteristicas extra que tiene el producto
     * 
     * @return listaCaracteristicas.size()
     */
    public int obtenerNumCaracteristicas(){
        return listaCaracteristicas.size();
    }

    /**
     * Asigna el numero unico de producto
     * 
     * Es llamado cuando se añade el producto a la base de datos
     * 
     * @param numeroProducto Numero correspondiente al producto
     */
    public void asignarNumeroProducto(int numeroProducto){
        this.numeroProducto = numeroProducto;
    }

    /**
     * Devuelve el numero unico del producto
     * 
     * @return numeroProducto Numero del producto
     */
    public int obtenerNumeroProducto(){
        return numeroProducto;
    }

    /**
     * Asigna/cambia el precio del producto en euros
     * 
     * @param precio Precio del producto
     */
    public void asignarPrecio(float precio){
        this.precio = precio;
    }

    /**
     * Devuelve el precio del producto en euros
     * 
     * @return precio Precio del producto
     */
    public float obtenerPrecio(){
        return precio;
    }

    /**
     * Asigna el peso del producto en kg
     * 
     * @param peso Peso del producto
     */
    public void asignarPeso(float peso){
        this.peso = peso;
    }

    /**
     * Devuelve el peso del producto en kg
     * 
     * @return peso Peso del producto
     */
    public float obtenerPeso(){
        return peso;
    }

    /**
     * Cambia el estado de financiacion del producto
     * 
     * @param financiado Estado de financiacion
     */
    public void cambiarFinanciado(boolean financiado){
        this.financiado = financiado;
    }

    /**
     * Cambia el estado del producto
     * Posibles estados = {"INTACTO", "ROTO", "DEVUELTO"}
     * 
     * @param estadoProducto Estado del producto
     */
    public void cambiarEstado(EnumEstadoProducto estadoProducto){
        this.estadoProducto = estadoProducto;
    }

    /**
     * Devuelve el estado del producto.
     * Posibles estados = {"INTACTO", "ROTO", "DEVUELTO"}
     * 
     * @return estadoProducto Estado del producto
     */
    public EnumEstadoProducto obtenerEstadoProducto(){
        return estadoProducto;
    }

    /**
     * Asigna/Cambia una descripcion del producto
     * 
     * @param descripcionProducto Descripcion del producto
     */
    public void asignarDescripcion(String descripcionProducto){
        this.descripcionProducto =  descripcionProducto;
    }

    /** 
     * Devuelve la descripcion del producto
     * 
     * @return descripcionProducto Descripcion del producto
     */
    public String obtenerDescripcion(){
        return descripcionProducto;
    }
}
