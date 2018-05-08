package productos;


/**
 * Define los productos que pertenezcan a la categoria de telefonia.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class ProductoTelefonia extends Producto{
    
    //Indica si tiene bateria
    public boolean tieneBateria;
    
    //Indica si es inalambrico o no
    public boolean esInalambrico;
    
    //Duracion de la bateria en horas
    public int duracion;
    
    //Metodo constructor
    public ProductoTelefonia(){
        //Valores por defecto: sin bateria, 
        //no inalambrico, 2 horas de bateria
        tieneBateria = false;
        esInalambrico = false;
        duracion = 2;
    }
    
    /**
     * Asigna o reasigna el estado de la variable booleana tieneBateria
     * 
     * @param tieneBateria Nuevo estado
     */
    public void cambiarBateria(boolean tieneBateria){
        this.tieneBateria = tieneBateria;
    }
    
    /**
     * Devuelve el estado de la variable tieneBateria
     * 
     * @return tieneBateria Estado de tieneBateria
     */
    public boolean tieneBateria(){
        return tieneBateria;
    }
    
    /**
     * Asigna o reasigna el estado de esInalambrico
     * 
     * @param esInalambrico Nuevo estado
     */
    public void cambiarInalambrico(boolean esInalambrico){
        this.esInalambrico = esInalambrico;
    }
    
    /**
     * Devuelve el estado de la variable esInalambrico
     * 
     * @return esInalambrico Estado de esInalambrico
     */
    public boolean esInalambrico(){
        return esInalambrico;
    }
    
    /**
     * Asigna o reasigna la duracion de la bateria del producto
     * 
     * @param nuevaDuracion Nueva duracion en horas
     */
    public void asignarDuracion(int nuevaDuracion){
        duracion = nuevaDuracion;
    }
    
    /**
     * Devuelve la duracion de la bateria en horas
     * 
     * @return duracion Duracion en horas del producto
     */
    public int obtenerDuracion(){
        return duracion;
    }

}

