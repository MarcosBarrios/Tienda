package productos;

import uitextual.UIMensajes;

/**
 * Clase disenada para las modificaciones que se vayan haciendo a 
 * los productos en la tienda.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Reporte{
    
    //Nuevo estado del producto al anadir el reporte
    private EnumEstadoProducto nuevoEstado;
    
    //Fecha del reporte
    private int diaReporte, mesReporte, anoReporte;
    
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
        anoReporte = 2018;
        coste = 0;
        pagado = false;
        nuevoEstado = EnumEstadoProducto.INTACTO;
    }
    
    //Metodo constructor
    public Reporte(EnumEstadoProducto nuevoEstado, float coste,
    boolean pagado, int diaReporte, int mesReporte, int anoReporte, 
    String descripcion){
        this.nuevoEstado = nuevoEstado;
        this.coste = coste;
        this.pagado = pagado;
        this.diaReporte = diaReporte;
        this.mesReporte = mesReporte;
        this.anoReporte = anoReporte;
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
     * tras anadir el Reporte a la coleccion de reportes del
     * producto.
     * 
     * @param nuevoEstado Nuevo estado del producto
     */
    public void asignarEstado(EnumEstadoProducto nuevoEstado){
        this.nuevoEstado = nuevoEstado;
    }
    
    /**
     * Devuelve el nuevo estado que el producto tendra tras anadir
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
     * Asigna o reasigna el ano en el que se realizo el Reporte
     * 
     * @param anoReporte Ano del Reporte
     */
    public void asignarAnoReporte(int anoReporte){
        this.anoReporte = anoReporte;
    }
    
    /**
     * Devuelve el ano en el que se hizo el Reporte
     * 
     * @return anoReporte Ano en el que se hizo el Reporte
     */
    public int obtenerAnoReporte(){
        return anoReporte;
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
    
    /**
     * Imprime la informacion almacenada en el reporte sin
     * la descripcion, que tendra que ser imprimida a parte.
     */
    @Override
    public String toString() {
    	return UIMensajes.mC_LP_Estado() + ": " + 
    		obtenerCadenaEstado(obtenerNuevoEstado()) +
    		" | " + UIMensajes.mC_AnP_Dia() + ": " + obtenerDiaReporte() +
    		" | " + UIMensajes.mC_AnP_Mes() + ": " + obtenerMesReporte() +
    		" | " + UIMensajes.mC_AnP_Ano() + ": " + obtenerAnoReporte() +
    		" | " + UIMensajes.mT_AR_Pagado() + ": " + 
    		obtenerCadenaBooleana(obtenerPagado());
    }
    
    /**
     * Devuelve una cadena representativa del estado del reporte
     * 
     * @param estado Estado del reporte
     * 
     * @return Cadena representativa del estado del reporte
     */
	private String obtenerCadenaEstado(EnumEstadoProducto estado) {
		switch(estado) {
		case ROTO: return EnumEstadoProducto.estadoProductoRoto();
		case INTACTO: return EnumEstadoProducto.estadoProductoIntacto();
		case DEVUELTO: return EnumEstadoProducto.estadoProductoDevuelto();
		}
    	return EnumEstadoProducto.estadoProductoIntacto();
    }
	
	/**
	 * Devuelve la cadena "Si" si obtenerPagado es verdadero y
	 * "No" si obtenerPagado es falso
	 * 
	 * @param obtenerPagado Valor booleano que se quiere pasar a cadena
	 * 
	 * @return Cadena con valor booleano "Si"/"No"
	 */
	private String obtenerCadenaBooleana(boolean obtenerPagado) {
		if(obtenerPagado) {
			return UIMensajes.g_Si();
		}
		return UIMensajes.g_No();
	}
    
}
