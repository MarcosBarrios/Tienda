package backend;

import productos.*;

/**
 * Los cajeros son los encargados de trabajar con los diferentes productos.
 * 
 * Entre sus tareas estan la de ordenar los electrodomesticos, asegurarse de que
 * siempre hay electrodomesticos, cobrar a los clientes, resolver dudas de clientes,
 * etc...
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class EpdoCajero extends Empleado{
    
    //Indica el numero de cada en la que se encuentra
    private int numeroCaja;
    
    //Metodo constructor
    public EpdoCajero(String dni, String nombre, String email, 
        String usuario, String contraseña){
        super(dni, nombre, email, usuario, contraseña);
    }
    
    /**
     * Asigna el numero de caja en el que se encuentra operando el cajero.
     * 
     * @param numeroCaja Numero de caja en el que va a operar el cajero
     */
    public void asignarNumeroCaja(int numeroCaja){
        this.numeroCaja = numeroCaja;
    }
    
    /**
     * Devuelve el numero de caja en el que se encuentra operando el cajero
     * 
     * @return numeroCaja Numero de caja en el que se encuentra
     */
    public int obtenerNumeroCaja(){
        return numeroCaja;
    }
    
}