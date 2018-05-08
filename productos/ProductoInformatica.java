package productos;


/**
 * Define los productos que pertenecen a la categoria de informatica.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class ProductoInformatica extends Producto{
    
    //Frecuencia en MHz
    private int frecuencia;
    
    //Numero de nucleos del procesador
    public int numeroNucleos;
    
    //Capacidad de almacenamiento en GB
    public float capacidadAlmacenamiento;
    
    //Metodo constructor
    public ProductoInformatica(){
        //Valores por defecto: 200 MHz, 2 Nucleos y 100 GBs.
        frecuencia = 200;
        numeroNucleos = 2;
        capacidadAlmacenamiento = 100;
    }
    
    /**
     * Asigna o reasigna la frecuencia del producto informatico.
     * 
     * Medida en MHz.
     * 
     * @param nuevaFrecuencia La nueva frecuencia del producto
     */
    public void asignarFrecuencia(int nuevaFrecuencia){
        frecuencia = nuevaFrecuencia;
    }
    
    /**
     * Devuelve la frecuencia en MHz.
     * 
     * @return frecuencia Frecuencia en MHz
     */
    public int obtenerFrecuencia(){
        return frecuencia;
    }
    
    /**
     * Asigna o reasigna el numero de nucleos del producto
     * informatico.
     * 
     * @param nuevaCantidad Nuevo numero de nucleos
     */
    public void asignarNumeroNucleos(int nuevaCantidad){
        numeroNucleos = nuevaCantidad;
    }
    
    /**
     * Devuelve el numero de nucleos de la CPU o procesador
     * del producto informatico.
     * 
     * @return numeroNucleos Numero de nucleos de la CPU
     */
    public int obtenerNumeroNucleos(){
        return numeroNucleos;
    }
    
    /**
     * Asigna o reasigna la capacidad de almacenamiento en GB.
     * 
     * @param nuevaCapacidad Nueva capacidad de almacenamiento (en GB)
     */
    public void asignarCapacidadAlmacenamiento(float nuevaCapacidad){
        capacidadAlmacenamiento = nuevaCapacidad;
    }
    
    /**
     * Devuelve la capacidad de almacenamiento en GB.
     * 
     * @return capacidadAlmacenamiento Numero de GBs que puede almacenar
     */
    public float obtenerCapacidadAlmacenamiento(){
        return capacidadAlmacenamiento;
    }

}
