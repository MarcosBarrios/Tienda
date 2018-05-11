package backend;

import java.util.ArrayList;

/**
 * Clase que implementa las funciones de los empleados tecnicos.
 * 
 * Empleado encargado de la reparación de productos.
 * 
 * @author Marcos Barrios 
 * @version 1.0
 */
public class EpdoTecnico extends Empleado{
    
    //Coleccion con las fichas de reparacion
    private ArrayList<FichaReparacion> listaFichas;
    
    //Coleccion con las piezas que el tecnico necesita
    private ArrayList<Pieza> piezasNecesarias;
    
    public EpdoTecnico(String dni, String nombre, String email, String usuario, 
        String contraseña){
       super(dni, nombre, email, usuario, contraseña);
       listaFichas = new ArrayList<FichaReparacion>();
       piezasNecesarias = new ArrayList<Pieza>();
    }
    
    /**
     * Añade una pieza a la lista de piezas necesarias
     */
    public void añadirPieza(Pieza pieza){
        piezasNecesarias.add(pieza);
    }
    
    /**
     * Elimina una pieza en la posicion id de la coleccion
     * 
     * @param id Posicion de la pieza en la coleccion
     */
    public void eliminarPieza(int id){
        piezasNecesarias.remove(id);
    }
    
    /**
     * Obtiene una pieza en la posicion id de la coleccion
     * 
     * @param id Posicion de la pieza en la coleccion
     */
    public Pieza obtenerPieza(int id){
        return piezasNecesarias.get(id);
    }
    
    /**
     * Devuelve el numero de piezas necesarias
     * 
     * @return piezasNecesarias.size();
     */
    public int obtenerNumeroPiezas(){
        return piezasNecesarias.size();
    }
    
    /**
     * Añade una ficha de reparacion al la lista de fichas de reparacion
     * del tecnico
     * 
     * @param fichaReparacion Ficha de reparacion a añadir
     */
    public void añadirFichaReparacion(FichaReparacion fichaReparacion){
        listaFichas.add(fichaReparacion);
    }
    
    /**
     * Obtiene una ficha de reparacion de la coleccion de fichas de
     * reparacion del tecnico
     * 
     * @param id Posicion en la coleccion de la ficha de reparacion
     */
    public FichaReparacion obtenerFichaReparacion(int id){
        return listaFichas.get(id);
    }
    
    /**
     * Devuelve el numero de fichas de reparacion asignadas al tecnico
     * 
     * @return listaFichas.size();
     */
    public int obtenerNumeroFichas(){
        return listaFichas.size();
    }
}
