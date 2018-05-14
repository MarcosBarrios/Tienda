package backend;


/**
 * Una operacion define una accion realizada en el programa. Los productos
 * y los tecnicos guardan un historial de operaciones.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class Operacion{
    
    //Describe la operacion
    private String descripcion;
    
    //Fecha de la operacion
    private int dia, mes, año;
    
    //Empleado responsable de la operacion
    private Empleado responsable;
    
    //Metodo constructor
    public Operacion(){}
    
    //Metodo constructor
    public Operacion(Empleado responsable, String descripcion, int dia, int mes, int año){
        this.responsable = responsable;
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }
    
    /**
     * Asigna o reasigna un empleado responsable de la operacion
     *
     * @param responsable Empleado que ha realizado la accion
     */
    public void asignarResponsable(Empleado responsable){
        this.responsable = responsable;
    }
    
    /**
     * Devuelve el empleado responsable de la operacion
     * 
     * @return responsable Empleado responsable de llevar a cabo la operacion
     */
    public Empleado obtenerResponsable(){
        return responsable;
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
