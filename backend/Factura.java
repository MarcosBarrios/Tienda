package backend;

import uitextual.UIMensajes;

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
    
    //Dia, mes y ano en el que se genero la factura
    private int dia, mes, ano;
    
    //Metodo constructor
    public Factura(){}
    
    //Metodo Constructor
    public Factura(float coste, String descripcion, int dia, int mes, int ano){
        this.coste = coste;
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
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
     * Asigna o reasigna el ano en que se genero la factura
     * 
     * @param ano Ano generada
     */
    public void asignarAno(int ano){
        this.ano = ano;
    }
    
    /**
     * Devuelve el ano en que se genero la factura
     * 
     * @return ano Ano generada
     */
    public int obtenerAno(){
        return ano;
    }
    
    /**
     * Imprime los datos de la factura
     */
    @Override
    public String toString() {
        return "\t" + UIMensajes.mT_AR_Coste() + ": " + obtenerCoste() +
        		" |" + UIMensajes.mC_AnP_Dia() + ": " + obtenerDia() +
        		" |" + UIMensajes.mC_AnP_Mes() + ": " + obtenerMes() + 
        		" |" + UIMensajes.mC_AnP_Ano() + ": " + obtenerAno() + "\n" +
        		"\t" + UIMensajes.mC_AnP_Descripcion() + ": " + obtenerDescripcion();
    }
    
}
