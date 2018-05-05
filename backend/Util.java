package backend;

import productos.Productos;
import productos.Producto;

import backend.Usuarios;
import backend.Usuario;
import backend.Empleado;

import uitextual.UIMensajes;
import uitextual.UIEntradas;

import java.util.ArrayList;

/**
 * Clase contenedora de metodos estaticos usados globalmente por el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Util{
    
    //Precio maximo que un producto puede tener
    public static final float MAXIMOPRECIO = 999999f;
    
    //Peso maximo que un producto puede tener
    public static final float MAXIMOPESO = 999999f;
    
    //Maxima cantidad de productos
    public static final int MAXIMACANTIDAD = 999999;
    
    //Maximas fechas
    public static final int MAXIMOAÑO = 2050;
    public static final int MAXIMODIA = 31;
    public static final int MAXIMOMES = 12;
    
    /**
     * Devuelve verdadero si la base de datos de productos
     * contiene un producto.
     * 
     * @param producto Producto a comparar
     * @param productos Base de datos de productos de la tienda
     */
    public static Producto obtenerProductoIgual(Producto producto, Productos productos){
        
        for(int i = 0; i < productos.obtenerTamaño(); i++){
            Producto temp = productos.obtenerProducto(i, false);
            if(temp.obtenerPrecio()==producto.obtenerPrecio() &&
            temp.obtenerPeso()==producto.obtenerPeso() &&
            temp.obtenerDescripcion()==producto.obtenerDescripcion() &&
            temp.obtenerTiempoGarantia()==producto.obtenerTiempoGarantia() &&
            temp.obtenerDiaCompra() == producto.obtenerDiaCompra() &&
            temp.obtenerMesCompra() == producto.obtenerMesCompra() &&
            temp.obtenerAñoCompra() == producto.obtenerAñoCompra() &&
            temp.obtenerNumCaracteristicas() == producto.obtenerNumCaracteristicas()){
                //Si el producto cumple con todas las igualdades entonces se considera
                //igual a temp
                return temp;
            }
        }
        
        return null;
    }
    
    /**
     * Devuelve una lista con todos los tipos de empleados trabajando
     * en la tienda.
     * 
     * @return aux Lista con los empleados trabajando en la tienda
     */
    public static ArrayList<String> listaEmpleados(){
        ArrayList<String> aux = new ArrayList<String>();
        
        aux.add(UIMensajes.mGU_AñE_Cajero().toLowerCase());
        aux.add(UIMensajes.mGU_AñE_Financiacion().toLowerCase());
        aux.add(UIMensajes.mGU_AñE_Tecnico().toLowerCase());
        aux.add(UIMensajes.mGU_AñE_Comercial().toLowerCase());
        aux.add(UIMensajes.mGU_AñE_PostVenta().toLowerCase());
        
        return aux;
    }
    
    /**
     * Devuelve una lista con todas las categorias de productos que hay en
     * la tienda.
     * 
     * @return aux Lista con las categorias en las que puede estar cada producto
     */
    public static ArrayList<String> listaCategoriasProductos(){
        ArrayList<String> aux = new ArrayList<String>();
        
        aux.add(UIMensajes.mC_AñP_Hogar());
        aux.add(UIMensajes.mC_AñP_Sonido());
        aux.add(UIMensajes.mC_AñP_Informatica());
        aux.add(UIMensajes.mC_AñP_Imagen());
        aux.add(UIMensajes.mC_AñP_Telefonia());
        
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