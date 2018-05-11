package backend;


/**
 * Una operacion define una accion realizada en el programa. Los productos
 * y los tecnicos guardan un historial de operaciones.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class Operacion{
    
    //Describe la operacion
    public String descripcion;
    
    //Fecha de la operacion
    public int dia, mes, año;
    
    //Metodo constructor
    public Operacion(){}
    
    public Operacion(String descripcion, int dia, int mes, int año){
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }
    
    /**
     * Asigna o reasigna la descripcion de la accion realizada
     * 
     * @param descripcion Accion realizada
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion de la operacion
     * 
     * @return descripcion Accion realizada
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
    /**
     * Asigna o reasigna el dia en que se realizo la operacion
     * 
     * @param dia Dia de la operacion
     */
    public void asignarDia(int dia){
        this.dia = dia;
    }
    
    /**
     * Devuelve el dia en que se realizo la operacion
     * 
     * @return dia Dia de la operacion
     */
    public int obtenerDia(){
        return dia;
    }
    
    /**
     * Asigna o reasigna el mes en que se realizo la operacion
     * 
     * @param mes Mes de la operacion
     */
    public void asignarMes(int mes){
        this.mes = mes;
    }
    
    /**
     * Devuelve el mes en que se realizo la operacion
     * 
     * @return mes Mes de la operacion
     */
    public int obtenerMes(){
        return mes;
    }
    
    /**
     * Asigna o reasigna el año en que se realizo la operacion
     * 
     * @param año Año de la operacion
     */
    public void asignarAño(int año){
        this.año = año;
    }
    
    /**
     * Devuelve el año en que se realizo la operacion
     * 
     * @return año Año de la operacion
     */
    public int obtenerAño(){
        return año;
    }
    
}
