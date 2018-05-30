package productos;

import uitextual.UIMensajes;

/**
 * Define los productos que pertenezcan a la categoria de sonido.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class ProductoSonido extends Producto{
    
    //Indica si tiene un cable o no como forma de conexion
    private boolean inalambrico;
    
    //Indica si puede sumergirse bajo el agua
    private boolean resistenteAgua;
    
    //Indica si detecta el tipo de conexion bluetooth
    private boolean bluetooth;
    
    //Frecuencia de respuesta en Hz (Hertzios) (normalmente entre 20-20000 Hz)
    private float frecuencia;
    
    //Metodo constructor
    public ProductoSonido(){
        frecuencia = 20f; //Frecuencia de respuesta predeterminada
        
        //Todas las booleanas estan en falso predeterminadamente
        inalambrico = false; 
        resistenteAgua = false;
        bluetooth = false;
    }

    /**
     * Cambia el estado de la variable que indica si el dispositivo
     * es inalambrico o no.
     * 
     * @param inalambrico Nuevo estado
     */
    public void cambiarInalambrico(boolean inalambrico){
        this.inalambrico = inalambrico;
    }
    
    /**
     * Devuelve la variable que indica si es inalambrico o no.
     */
    public boolean inalambrico(){
        return inalambrico;
    }
    
    /**
     * Cambia el estado de la variable que indica si el dispositivo
     * resiste el agua / puede sumergirse o no.
     * 
     * @param resistenteAgua Nuevo estado
     */
    public void cambiarResistenteAgua(boolean resistenteAgua){
        this.resistenteAgua = resistenteAgua;
    }
    
    /**
     * Devuelve la variable que indica si es resistente al agua o no.
     */
    public boolean resistenteAgua(){
        return resistenteAgua;
    }
    
    /**
     * Cambia el estado de la variable que indica si el dispositivo
     * detecte bluetooth o no.
     * 
     * @param bluetooth Nuevo estado
     */
    public void cambiarBluetooth(boolean bluetooth){
        this.bluetooth = bluetooth;
    }
    
    /**
     * Devuelve la variable que indica si detecta bluetooth o no.
     */
    public boolean bluetooth(){
        return bluetooth;
    }
    
    /**
     * Asigna/Cambia la frecuencia de respuesta del producto en Hz (Hertzios),
     * normalmente entre 20-20000 Hz
     * 
     * @param frecuencia Frecuencia de respuesta
     */
    public void asignarFrecuencia(float frecuencia){
        this.frecuencia = frecuencia;
    }
    
    /**
     * Devuelve la frecuencia de respuesta del producto en Hz (Hertzios)
     */
    public float obtenerFrecuencia(){
        return frecuencia;
    }
    
    /**
     * Devuelve una cadena para referenciar este tipo de producto
     */
    public String categoria() {
    	return UIMensajes.mC_AnP_Sonido();
    }
    
    /**
	 * Devuelve una cadena con la informacion del comercial
	 */
	@Override
    public String toString() {
		String salida = cadenaDatos();
    	return salida.concat("\n\t" + UIMensajes.mC_ICE_Inalambrico() + ": " + 
    			obtenerCadenaBooleana(inalambrico()) +
    			" | " + UIMensajes.mC_ICE_ResistenteAgua() + ": " + 
    			obtenerCadenaBooleana(resistenteAgua()) +
    			" | " + UIMensajes.mC_ICE_Bluetooth() + ": " + 
    			obtenerCadenaBooleana(bluetooth()) +
    			" | " + UIMensajes.mC_ICE_Frecuencia() + ": " + 
    			obtenerFrecuencia());
    }
}
