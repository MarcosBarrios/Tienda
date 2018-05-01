package productos;

import java.util.ArrayList;


/**
 * Contiene los estados en los que se puede encontrar un producto.
 * 
 * Para facilitar el añadido de nuevos estados se emplea un sistema
 * que permite añadir los nuevos estados aqui dejando que el resto del
 * programa se adapte.
 * 
 * La desventaja es que a la hora de traducir se tienen que traducir tambien
 * los enums.
 * 
 * Referencia:
 * https://stackoverflow.com/questions/32089114/how-to-store-value-of-enum-in-arraylist
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public enum EnumEstadoProducto{
    INTACTO(estadoProductoIntacto()), //Intacto
    ROTO(estadoProductoRoto()), //Roto
    DEVUELTO(estadoProductoDevuelto()); //Devuelto
    
    //Lista contenedora de los estados
    private static final ArrayList<String> listaEstados;
    
    private final String estado;
    
    static {
        listaEstados = new ArrayList<String>();
        
        for(EnumEstadoProducto estados : EnumEstadoProducto.values()){
            listaEstados.add(estados.estado);
        }
        
    }
    
    //Metodo constructor
    private EnumEstadoProducto(String estado){
        this.estado = estado;
    }
    
    /**
     * Devuelve la lista con los posibles estados de un producto
     * 
     * @return listaEstados lista con los estados en el enum
     */
    public static ArrayList<String> obtenerEstados(){
        return listaEstados;
    }
    
    public static String estadoProductoIntacto(){
        return "Intacto";
    }
    
    public static String estadoProductoRoto(){
        return "Roto";
    }
    
    public static String estadoProductoDevuelto(){
        return "Devuelto";
    }
    
}
    

