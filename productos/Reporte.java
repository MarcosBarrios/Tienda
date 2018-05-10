package productos;


/**
 * Clase diseñada para las modificaciones que se vayan haciendo a 
 * los productos en la tienda.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Reporte{
    
    //Nuevo estado del producto al añadir el reporte
    private EnumEstadoProducto nuevoEstado;
    
    //Fecha del reporte
    private int diaReporte, mesReporte, añoReporte;
    
    //Cuanto ha costado la modificacion
    private float coste;
    
    //Indica si se ha pagado o no
    private boolean pagado;
    
    //Descripcion de lo que se ha hecho
    private String descripcion;
    
    //Metodo constructor
    public Reporte(){
        //Valores por defecto
        descripcion = "";
        diaReporte = 1;
        mesReporte = 1;
        añoReporte = 2018;
        coste = 0;
        pagado = false;
        nuevoEstado = EnumEstadoProducto.INTACTO;
    }
    
    //Metodo constructor
    public Reporte(EnumEstadoProducto nuevoEstado, float coste,
    boolean pagado, int diaReporte, int mesReporte, int añoReporte, 
    String descripcion){
        this.nuevoEstado = nuevoEstado;
        this.coste = coste;
        this.pagado = pagado;
        this.diaReporte = diaReporte;
        this.mesReporte = mesReporte;
        this.añoReporte = añoReporte;
        this.descripcion = descripcion;
    }
    
    /**
     * Indica si se tiene que pagar o no la reparacion
     * 
     * @param pagado Nuevo estado de la variable booleana
     */
    public void cambiarPagado(boolean pagado){
        this.pagado = pagado;
    }
    
    /**
     * Devuelve el valor de la variable pagado
     * 
     * @return pagado Indica si se tiene que pagar o no
     */
    public boolean obtenerPagado(){
        return pagado;
    }
    
    /**
     * Asigna o reasigna el coste en caso de que se haya
     * realizado algun tipo de modificacion en el producto.
     * 
     * @param coste Coste de la potencial modificacion
     */
    public void asignarCoste(float coste){
        this.coste = coste;
    }
    
    /**
     * Devuelve el coste (si ha costado algo) de la modificacion
     * en caso de que la haya habido.
     * 
     * @return coste Coste de la modificacion
     */
    public float obtenerCoste(){
        return coste;
    }
    
    /**
     * Asigna o reasigna el nuevo estado que el producto tendra
     * tras añadir el Reporte a la coleccion de reportes del
     * producto.
     * 
     * @param nuevoEstado Nuevo estado del producto
     */
    public void asignarEstado(EnumEstadoProducto nuevoEstado){
        this.nuevoEstado = nuevoEstado;
    }
    
    /**
     * Devuelve el nuevo estado que el producto tendra tras añadir
     * el Reporte a la coleccion de Reportes del producto.
     * 
     * @return nuevoEstado Nuevo estado del producto
     */
    public EnumEstadoProducto obtenerNuevoEstado(){
        return nuevoEstado;
    }
    
    /**
     * Asigna o reasigna el dia en el que se realizo el Reporte
     * 
     * @param diaReporte Dia del Reporte
     */
    public void asignarDiaReporte(int diaReporte){
        this.diaReporte = diaReporte;
    }
    
    /**
     * Devuelve el dia en el que se hizo el Reporte
     * 
     * @return diaReporte Dia en el que se hizo el Reporte
     */
    public int obtenerDiaReporte(){
        return diaReporte;
    }
    
    /**
     * Asigna o reasigna el mes en el que se realizo el Reporte
     * 
     * @param mesReporte Mes del Reporte
     */
    public void asignarMesReporte(int mesReporte){
        this.mesReporte = mesReporte;
    }
    
    /**
     * Devuelve el mes en el que se hizo el Reporte
     * 
     * @return mesReporte Mes en el que se hizo el Reporte
     */
    public int obtenerMesReporte(){
        return mesReporte;
    }
    
    /**
     * Asigna o reasigna el año en el que se realizo el Reporte
     * 
     * @param añoReporte Año del Reporte
     */
    public void asignarAñoReporte(int añoReporte){
        this.añoReporte = añoReporte;
    }
    
    /**
     * Devuelve el año en el que se hizo el Reporte
     * 
     * @return añoReporte Año en el que se hizo el Reporte
     */
    public int obtenerAñoReporte(){
        return añoReporte;
    }
    
    /**
     * Asigna o reasigna la descripcion del Reporte
     * 
     * @param descripcion Descripcion del Reporte
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion del Reporte
     * 
     * @return descripcion Descripcion del Reporte
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
}
