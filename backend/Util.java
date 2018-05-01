package backend;

import backend.Usuarios;
import backend.Usuario;
import backend.Empleado;
import java.util.ArrayList;
import uitextual.UIMensajes;
import uitextual.UIEntradas;

/**
 * Clase contenedora de metodos estaticos usados globalmente por el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Util{
    
    /**
     * Actualiza la informacion de un producto almacenada en cadena de caracteres.
     *
     * @param nombreString Nombre del dato del producto que se quiere actualizar
     * @param lineaCompleta true: leer linea entera
     */
    public static String UIactualizarStringProducto(String nombreString, boolean lineaCompleta){
        System.out.println();
        System.out.print("\t");
        System.out.print(nombreString + ": ");
        String nuevoValor = UIEntradas.obtenerCadena(lineaCompleta);
        System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoExito());        
        return nuevoValor;
    }
    
    /**
     * Actualiza la informacion de un producto almacenada en un numero decimal.
     *
     * @param nombreString Nombre del dato del producto que se quiere actualizar
     * @param min Minimo valor aceptable
     * @param max Maximo valor aceptable
     */
    public static float UIactualizarNumeroProducto(String nombreString, float min, float max){
        System.out.println();
        System.out.print("\t");
        System.out.print(nombreString + ": ");
        float nuevoValor = UIEntradas.obtenerDecimal(min, max);
        System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoExito());
        return nuevoValor;
    }
    
    /**
     * Actualiza la informacion de un producto almacenada en boolean.
     *
     * @param nombreString Nombre del dato del producto que se quiere actualizar
     */
    public static boolean UIactualizarEstadoProducto(String nombreString){
        System.out.println();
        System.out.print("\t");
        System.out.print(nombreString + "[true (si)/false (no)]: ");
        boolean nuevoValor = UIEntradas.obtenerBooleana();
        System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoExito());
        return nuevoValor;
    }
    
    /**
     * Devuelve una lista con todas las categorias de productos que hay en
     * la tienda.
     * 
     * @return aux Lista con las categorias en las que puede estar cada producto
     */
    public static ArrayList<String> listaCategoriasProductos(){
        ArrayList<String> aux = new ArrayList<String>();
        
        aux.add(UIMensajes.opcionCrearProductoCategoriaHogar());
        aux.add(UIMensajes.opcionCrearProductoCategoriaSonido());
        aux.add(UIMensajes.opcionCrearProductoCategoriaInformatica());
        aux.add(UIMensajes.opcionCrearProductoCategoriaImagen());
        aux.add(UIMensajes.opcionCrearProductoCategoriaTelefonia());
        
        return aux;
    }
    
    /**
     * Comprueba si la base de datos de usuarios de la tienda contiene un
     * usuario con los parametros pasados.
     * 
     * Utilizado por el sistema de inicio de sesion de la tienda.
     * 
     * @param usuario Nombre de usuario de la cuenta
     * @param contraseña Contraseña del usuario que quiere iniciar sesion
     * @return aux true si existe una cuenta con los datos proporcionados
     */
    public static boolean coincideUsuario(Usuarios usuarios, String usuario, 
        String contraseña){
        
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            if(usuarios.obtenerUsuario(i) instanceof Empleado){
                Empleado temp = (Empleado) usuarios.obtenerUsuario(i);
                if(temp.obtenerNombreUsuario().equals(usuario) &&
                    temp.obtenerContraseña().equals(contraseña)){
                        return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Busca en la base de datos un empleado con la con los datos
     * especificados.
     * 
     * @param usuario Nombre de usuario de la cuenta
     * @param contraseña Contraseña de la cuenta
     * @return temp Usuario con los datos especificados
     */
    public static Usuario buscarCuentaEmpleado(Usuarios usuarios, String usuario, 
    String contraseña){
        
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            if(usuarios.obtenerUsuario(i) instanceof Empleado){
                Empleado temp = (Empleado) usuarios.obtenerUsuario(i);
                if(temp.obtenerUsuario().equals(usuario) &&
                    temp.obtenerContraseña().equals(contraseña)){
                        return temp;
                }
            }
        }
        return null;
    }
    
}