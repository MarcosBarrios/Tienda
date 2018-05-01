package productos;


/**
 * Contiene un titulo y una descripcion de una caracteristica.
 * 
 * Sirve para añadir dinamicamente nuevas caracteristicas a productos
 * no ya definidas como variables en las clases.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Caracteristica{
    
    //Titulo de la caracteristica (ej: CV)
    private String titulo;
    
    //Descripcion de la caracteristica (ej: 20 HP)
    private String descripcion;
    
    //Metodo constructor
    public Caracteristica(){}
    
    //Metodo constructor
    public Caracteristica(String titulo){
        this.titulo = titulo;
    }
    
    //Metodo constructor
    public Caracteristica(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    
    /**
     * Asigna/Cambia el titulo de la caracteristica
     * 
     * @param titulo Titulo de la caracteristica
     */
    public void asignarTitulo(String titulo){
        this.titulo = titulo;
    }
    
    /**
     * Devuelve el titulo de la caracteristica
     */
    public String obtenerTitulo(){
        return titulo;
    }
    
    /**
     * Asigna/Cambia la descripcion de la caracteristica
     * 
     * @param descripcion Descripcion de la caracteristica
     */
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve la descripcion de la caracteristica
     */
    public String obtenerDescripcion(){
        return descripcion;
    }
    
}
