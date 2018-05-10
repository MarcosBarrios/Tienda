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
    
    public EpdoTecnico(String dni, String nombre, String email, String usuario, 
        String contraseña){
       super(dni, nombre, email, usuario, contraseña);
       listaFichas = new ArrayList<FichaReparacion>();
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
