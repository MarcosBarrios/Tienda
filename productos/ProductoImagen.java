package productos;

import uitextual.UIMensajes;

/**
 * Define los productos que pertenezcan a la categoria de imagen.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class ProductoImagen extends Producto{
    
    private float pulgadas;
    
    private int anchoResolucion, altoResolucion;
    
    //Metodo constructor
    public ProductoImagen(){
        pulgadas = 2;
        anchoResolucion = 900;
        altoResolucion = 1600;
    }
    
    /**
     * Asigna o reasigna el nuevo de pulgadas del producto
     * 
     * @param nuevasPulgadas Nueva cantidad de pulgadas
     */
    public void asignarPulgadas(float nuevasPulgadas){
        pulgadas = nuevasPulgadas;
    }
    
    /**
     * Devuelve el numero de pulgadas del producto
     * 
     * @return pulgadas Numero de pulgadas
     */
    public float obtenerPulgadas(){
        return pulgadas;
    }
    
    /**
     * Asigna o reasigna el numero de pixeles en horizontal
     * 
     * @nuevoAncho Numero de pixeles horizontales
     */
    public void asignarAnchoResolucion(int nuevoAncho){
        anchoResolucion = nuevoAncho;
    }
    
    /**
     * Devuelve el numero de pixeles horizontales
     * 
     * @return anchoResolucion Numero de pixeles horizontales
     */
    public int obtenerAnchoResolucion(){
        return anchoResolucion;
    }
    
    /**
     * Asigna o reasigna el numero de pixeles en verticales
     * 
     * @nuevoAlto Numero de pixeles verticales
     */
    public void asignarAltoResolucion(int nuevoAlto){
        altoResolucion = nuevoAlto;
    }
    
    /**
     * Devuelve el numero de pixeles verticales
     * 
     * @return nuevoAlto Numero de pixeles verticales
     */
    public int obtenerAltoResolucion(){
        return altoResolucion;
    }

    /**
     * Devuelve una cadena para referenciar este tipo de producto
     */
    public String toString() {
    	return UIMensajes.mC_AnP_Imagen();
    }
    
}

