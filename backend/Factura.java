package backend;


/**
 * Factura generada cuando el cliente tiene que pagar algo
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Factura{
    
    //Cuanto hay que pagar
    private float coste;
    
    //Descripcion de que es lo que hay que pagar
    private String descripcion;
    
    //Dia, mes y año en el que se genero la factura
    private int dia, mes, año;
    
    //Metodo constructor
    public Factura(){}
    
    //Metodo Constructor
    public Factura(float coste, String descripcion, int dia, int mes, int año){
        this.coste = coste;
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }
    
    /**
     * Asigna o reasigna el precio a pagar
     * 
     * @param coste Precio a pagar
     */
    public void asignarCoste(float coste){
        this.coste = coste;
    }
    
    /**
     * Devuelve el precio a pagar
     * 
     * @return coste Precio a pagar
     */
    public float obtenerCoste(){
        return coste;
    }
    
    /**
     * Asigna o reasigna la descripcion de la factura
     * 
     * @param descripcion Lo que hay que pagar
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion de la factura
     * 
     * @return descripcion Lo que hay que pagar
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
    /**
     * Asigna o reasigna el dia en que se genero la factura
     * 
     * @param dia Dia generada
     */
    public void asignarDia(int dia){
        this.dia = dia;
    }
    
    /**
     * Devuelve el dia en que se genero la factura
     * 
     * @return dia Dia generada
     */
    public int obtenerDia(){
        return dia;
    }
    
    /**
     * Asigna o reasigna el mes en que se genero la factura
     * 
     * @param mes Mes generada
     */
    public void asignarMes(int mes){
        this.mes = mes;
    }
    
    /**
     * Devuelve el mes en que se genero la factura
     * 
     * @return mes Mes generada
     */
    public int obtenerMes(){
        return mes;
    }
    
    /**
     * Asigna o reasigna el año en que se genero la factura
     * 
     * @param año Año generada
     */
    public void asignarAño(int año){
        this.año = año;
    }
    
    /**
     * Devuelve el año en que se genero la factura
     * 
     * @return año Año generada
     */
    public int obtenerAño(){
        return año;
    }
    
}
