package uitextual;

import backend.Usuario;
import backend.Usuarios;

import backend.Util;

import java.util.Iterator;

/**
 * Clase especifica para la parte textual del programa que implementa
 * metodos que los usuarios del programa necesitan.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIUsuario{
    
    //Clase a la que representa UIUsuario
    private Usuario usuario;
    
    //Metodo constructor
    public UIUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    /**
     * Pide una entrada para cada cadena de caracteres en nombresEntradas.
     * 
     * Las entradas obtenidas son lineas completas (UIEntradas.obtenerCadena(true))
     * 
     * @param nombresEntradas Array con los nombres para las diferentes entradas
     * @return salidas Array con entradas obtenidas del usuario
     */
    public String[] formularioCadenas(String[] nombresEntradas){
        int numeroEntradas = nombresEntradas.length;
        String [] salidas = new String[numeroEntradas];
        for(int i = 0; i < numeroEntradas; i++){
            formatearCadena(nombresEntradas[i], true, true);
            String entrada = UIEntradas.obtenerCadena(true);
            salidas[i] = entrada;
        }
        
        return salidas;
    }
    
    /**
     * Pide una entrada para cada entero o decimal en nombresEntradas.
     * 
     * @param nombresEntradas Array con los nombres para las diferentes entradas
     * @return salidas Array con entradas obtenidas del usuario
     */
    public float[] formularioDecimales(String[] nombresEntradas){
        int numeroEntradas = nombresEntradas.length;
        float [] salidas = new float[numeroEntradas];
        for(int i = 0; i < numeroEntradas; i++){
            formatearCadena(nombresEntradas[i], true, true);
            float entrada = UIEntradas.obtenerDecimal(0, Util.MAXIMACANTIDAD);
            salidas[i] = entrada;
        }
        
        return salidas;
    }
    
    /**
     * Obtiene una cadena de caracteres usando texto formateado
     */
    public String formatearEntradaCadena(String nombreEntrada, boolean lineaCompleta){
        formatearCadena(nombreEntrada, true, true);
        return UIEntradas.obtenerCadena(lineaCompleta);
    }
    
    /**
     * Obtiene un numero decimal usando texto formateado
     * 
     * Puede ser convertido a entero para obtener entradas enteras
     */
    public float formatearEntradaDecimal(String nombreEntrada){
        formatearCadena(nombreEntrada, true, true);
        return UIEntradas.obtenerDecimal(0, Util.MAXIMACANTIDAD);
    }
    
    /**
     * Obtiene un estado booleano usando texto formateado
     */
    public boolean formatearEntradaBoolean(String nombreEntrada){
        formatearCadena(nombreEntrada, true, false);
        System.out.print(" [true (si)/false (no)]: ");
        return UIEntradas.obtenerBooleana();
    }
    
    /**
     * Da formato a una cadena. 
     * 
     * @param nombreEntrada Nombre de la entrada
     * @param tabular Si tabular=true pone "\t" antes de nombreEntrada
     * @param puntoComa Si puntoComa=true pone ": " despues de nombreEntrada
     */
    public void formatearCadena(String nombreEntrada, boolean tabular, boolean puntoComa){
        System.out.println();
        tabularCadena(true);
        System.out.print(nombreEntrada);
        añadirPuntoComa(true);
    }
    
    /**
     * Tabula una cadena de caracteres si tabular=true
     */
    private void tabularCadena(boolean tabular){
        if(tabular){
            System.out.print("\t");
        }
    }
    
    /**
     * Imprime punto y coma si ponerPuntoComa=true
     */
    private void añadirPuntoComa(boolean ponerPuntoComa){
        if(ponerPuntoComa){
            System.out.print(": ");
        }
    }
    
    /**
     * Imprime los datos del usuario (nombre y email)
     * 
     * Ejemplo formato: 
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  Nombre: Marcos Barrios
     *  Email: marcosloscardones@gmail.com
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    public void imprimirDatos(){
        //"* * * * * * ....... * *"
        System.out.println(UIMensajes.g_EncabezadoMenus());
        
        //"Nombre", "Email", "* * * * * * ....... * *"
        System.out.println(UIMensajes.g_Nombre() + ": " +
            usuario.obtenerNombreUsuario());
        System.out.println(UIMensajes.g_Email() + ": " +
            usuario.obtenerEmailUsuario());
        System.out.println(UIMensajes.g_EncabezadoMenus());
    }
    
    /**
     * Imprime en forma de lista los nombres y emails de los usuarios
     * que se encuentran en la base de datos del programa.
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirListaUsuarios(Usuarios usuarios){
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            //"* * * * * * ....... * *"
            System.out.println(UIMensajes.g_EncabezadoMenus());
            System.out.println(UIMensajes.g_Nombre() + ": " +
                usuarios.obtenerUsuario(i).obtenerNombreUsuario() + 
                "  " + usuarios.obtenerUsuario(i).obtenerEmailUsuario());
        }
    }
}
