package uitextual;

import backend.Usuario;
import backend.Usuarios;
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
    public String[] formulario(String[] nombresEntradas){
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
        System.out.println(UIMensajes.menuEncabezado());
        System.out.println(UIMensajes.informacionNombre() + ": " +
            usuario.obtenerNombreUsuario());
        System.out.println(UIMensajes.informacionEmail() + ": " +
            usuario.obtenerEmailUsuario());
        System.out.println(UIMensajes.menuEncabezado());
    }
    
    /**
     * Imprime en forma de lista los nombres y emails de los usuarios
     * que se encuentran en la base de datos del programa.
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirListaUsuarios(Usuarios usuarios){
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            System.out.println(UIMensajes.menuEncabezado());
            System.out.println(UIMensajes.informacionNombre() + ": " +
                usuarios.obtenerUsuario(i).obtenerNombreUsuario() + 
                "  " + usuarios.obtenerUsuario(i).obtenerEmailUsuario());
        }
    }
}
