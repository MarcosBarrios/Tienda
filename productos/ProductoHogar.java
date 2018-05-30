package productos;

import uitextual.UIMensajes;

/**
 * Define los productos que pertenezcan a la categoria de hogar.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class ProductoHogar extends Producto{
    
    //Indica las dimensiones en metros
    private float ancho, alto;
    
    //Indica el consumo en W
    private float consumo;
    
    //Metodo constructor
    public ProductoHogar(){
        //Valores por defecto: 1x1 metros, 20 W
        ancho = 1;
        alto = 1;
        consumo = 20;
    }
    
    /**
     * Asignar o reasignar el ancho del producto.
     * 
     * @nuevoAncho El nuevo ancho del producto.
     */
    public void asignarAncho(float nuevoAncho){
        ancho = nuevoAncho;
    }
    
    /**
     * Devuelve el ancho del producto
     * 
     * @return ancho Ancho del producto
     */
    public float obtenerAncho(){
        return ancho;
    }
    
    /**
     * Asignar o reasignar el alto del producto.
     * 
     * @nuevoAlto El nuevo alto del producto.
     */
    public void asignarAlto(float nuevoAlto){
        alto = nuevoAlto;
    }
    
    /**
     * Devuelve el alto del producto
     * 
     * @return alto Alto del producto
     */
    public float obtenerAlto(){
        return alto;
    }
    
    /**
     * Asigna o reasigna el consumo del producto en W.
     * 
     * @param nuevoConsumo Nuevo consumo en W
     */
    public void asignarConsumo(float nuevoConsumo){
        consumo = nuevoConsumo;
    }
    
    /**
     * Devuelve el consumo del producto en W.
     * 
     * @return consumo Consumo en W.
     */
    public float obtenerConsumo(){
        return consumo;
    }
    
    /**
     * Devuelve una cadena para referenciar este tipo de producto
     */
    public String categoria() {
    	return UIMensajes.mC_AnP_Hogar();
    }
    
    /**
	 * Devuelve una cadena con la informacion del comercial
	 */
	@Override
    public String toString() {
		String salida = cadenaDatos();
    	return salida.concat("\n\t" + UIMensajes.mC_ICE_Ancho() + ": " + 
    			obtenerAncho() +
    			" | " + UIMensajes.mC_ICE_Alto() + ": " + 
    			obtenerAlto() +
    			" | " + UIMensajes.mC_ICE_Consumo() + ": " + 
    			obtenerConsumo());
    }

}

