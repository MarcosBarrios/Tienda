package backend;

import productos.Productos;
import productos.Producto;
import productos.EnumEstadoProducto;

import backend.Usuarios;
import backend.Usuario;
import backend.Empleado;
import backend.FichaCliente;

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
    
    //Numero de cajas de la tienda
    public static final int NUMEROCAJAS = 10;
    
    /**
     * Permite encontrar una lista de usuarios que cumplan ciertos datos.
     * 
     * Cada parametro es ignorado si su valor es "".
     * 
     */
    public static ArrayList<Usuario> buscarUsuarios(Usuarios usuarios, String dni,
    String nombreUsuario, String emailUsuario){
        ArrayList<Usuario> al = new ArrayList<Usuario>();
        
        //Iteramos todos los usuarios
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario usuario = usuarios.obtenerUsuario(i);
            
            //Comprobamos datos
            if(coincideValorCadena(usuario.obtenerNombreUsuario(), nombreUsuario)
            && coincideValorCadena(usuario.obtenerEmailUsuario(), emailUsuario)
            && coincideValorCadena(usuario.obtenerDNI(), dni)){
                al.add(usuario);
            }
        }
        
        return al;
    }
    
    /**
     * Permite encontrar una lista de productos que cumplan ciertas caracteristicas 
     * que pertenezcan a un cliente.
     *  
     * Cada parametro es tenido en cuenta en la busqueda siempre que su
     * valor no sea igual a "" o -1.
     * 
     * @return producto Producto cuyas caracteristicas coinciden con las buscadas
     */
    public static ArrayList<Producto> buscarProductosEnCliente(Productos productos, Cliente cliente,
    int cantidad, float precio, float peso, String descripcion, int dia,
    int mes, int año, int tiempoGarantia, int numeroCaja){
        
        /*
         * Necesitamos que los 9 del producto coincidan con la busqueda tal que
         * si un valor es "" o -1 es completamente ignorado.
         * 
         * Cada valor sera verdadero si el producto es igual al valor especificado
         * del parametro o si el valor del parametro es igual a "" o -1.
         */
        
        ArrayList<Producto> al = new ArrayList<Producto>();
        
        //Obtenemos la ficha de cliente pues es la que almacena
        //los productos del cliente
        FichaCliente fc = cliente.obtenerFichaCliente();
        
        //Iteramos todos los productos de la base de datos de la tienda
        for(int i = 0; i < productos.obtenerTamaño(); i++){
            //Obtenemos un producto usando su posicion en la coleccion
            Producto producto = fc.obtenerProductoComprado(i, false);
            
            if(coincideValorEntero(producto.obtenerCantidad(), cantidad)
            && coincideValorEntero((int)producto.obtenerPrecio(), (int)precio)
            && coincideValorEntero((int)producto.obtenerPeso(), (int) peso)
            && coincideValorEntero(producto.obtenerDiaCompra(), dia)
            && coincideValorEntero(producto.obtenerMesCompra(), mes)
            && coincideValorEntero(producto.obtenerAñoCompra(), año)
            && coincideValorEntero(producto.obtenerTiempoGarantia(), tiempoGarantia)
            && coincideValorEntero(producto.obtenerNumeroCaja(), numeroCaja)
            && coincideValorCadena(producto.obtenerDescripcion(), descripcion)){
                al.add(producto);
            }
            
        }
        
        return al;
    }
    
    /**
     * Permite encontrar una lista de productos que cumplan ciertas caracteristicas 
     * y que esten almacenados en la base de datos de la tienda.
     *  
     * Cada parametro es tenido en cuenta en la busqueda siempre que su
     * valor no sea igual a "" o -1.
     * 
     * @return producto Producto cuyas caracteristicas coinciden con las buscadas
     */
    public static ArrayList<Producto> buscarProductos(Productos productos, int cantidad, 
    float precio, float peso, String descripcion, int dia,
    int mes, int año, int tiempoGarantia, int numeroCaja){
        
        /*
         * Necesitamos que los 9 del producto coincidan con la busqueda tal que
         * si un valor es "" o -1 es completamente ignorado.
         * 
         * Cada valor sera verdadero si el producto es igual al valor especificado
         * del parametro o si el valor del parametro es igual a "" o -1.
         */
        
        ArrayList<Producto> al = new ArrayList<Producto>();
        
        //Iteramos todos los productos de la base de datos de la tienda
        for(int i = 0; i < productos.obtenerTamaño(); i++){
            //Obtenemos un producto usando su posicion en la coleccion
            Producto producto = productos.obtenerProducto(i, false);
            
            if(coincideValorEntero(producto.obtenerCantidad(), cantidad)
            && coincideValorEntero((int)producto.obtenerPrecio(), (int)precio)
            && coincideValorEntero((int)producto.obtenerPeso(), (int) peso)
            && coincideValorEntero(producto.obtenerDiaCompra(), dia)
            && coincideValorEntero(producto.obtenerMesCompra(), mes)
            && coincideValorEntero(producto.obtenerAñoCompra(), año)
            && coincideValorEntero(producto.obtenerTiempoGarantia(), tiempoGarantia)
            && coincideValorEntero(producto.obtenerNumeroCaja(), numeroCaja)
            && coincideValorCadena(producto.obtenerDescripcion(), descripcion)){
                al.add(producto);
            }
            
        }
        
        return al;
    }
    
    /**
     * Metodo auxiliar de buscarProducto(...)
     * 
     * Devuelve verdadero si entrada==-1 o si valorProducto==entrada
     */
    private static boolean coincideValorEntero(int valorProducto, int entrada){
        if(entrada==-1 || valorProducto==entrada){
            return true;
        }
        return false;
    }
    
    /**
     * Metodo auxiliar de buscarProducto(...)
     * 
     * Devuelve verdadero si entrada.equals("") o si cadenaProducto.equals(entrada)
     */
    private static boolean coincideValorCadena(String cadenaProducto, String entrada){
        if(entrada.equals("") || cadenaProducto.toLowerCase().equals(entrada)){
            return true;
        }
        return false;
    }
    
    /**
     * Devuelve Si o No dependiendo del valor de un boolean
     * 
     * @param b Boolean del que obtener el valor
     * 
     * @return aux "Si" si b==true, "No" si b==false
     */
    public static String booleanAPalabra(boolean b){
        
        if(b){
            //"Si"
            return UIMensajes.g_Si();
        }else{
            //"No"
            return UIMensajes.g_No();
        }
        
    }
    
    /**
     * Devuelve una ficha de reparacion
     * 
     * @param usuarios Base de datos de usuarios
     * @param cliente Cliente propietario del producto
     * @param numProducto Numero del producto a tratar
     * 
     * @return fr Ficha de reparacion asignada al cliente y producto con numProducto
     */
    public static FichaReparacion obtenerFichaReparacion(Usuarios usuarios, Cliente cliente,
    int numProducto){
        
        /*
         * Iterar todos los usuarios de la tienda tal que
         * por cada tecnico encontrado se compruebe si contiene
         * una ficha con correspondiente a cierto cliente y numero
         * de producto
         */
        
        String nombreCliente = cliente.obtenerNombreUsuario();
        String dniCliente = cliente.obtenerDNI();
        
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario usuarioTemp = usuarios.obtenerUsuario(i);
            if(usuarioTemp instanceof EpdoTecnico){
                EpdoTecnico tecnico = (EpdoTecnico) usuarioTemp;
                
                for(int j = 0; j < tecnico.obtenerNumeroFichas(); j++){
                    FichaReparacion fr = tecnico.obtenerFichaReparacion(j);
                    if(fr.obtenerPropietario().obtenerNombreUsuario().equals(nombreCliente) &&
                        fr.obtenerPropietario().obtenerDNI().equals(dniCliente) &&
                        fr.obtenerProducto().obtenerNumeroProducto()==numProducto){
                         //Ficha de reparacion correcta encontrada
                         return fr;
                    }
                }
            }
        }   
        
        return null;
    }
    
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
     * Devuelve una lista con los productos comprados por un cliente que tengan
     * un numero de producto especificado como parametro.
     * 
     * @param cliente Cliente del que obtener la lista
     * @param numProducto El numero de producto con el que buscar coincidencias
     * @return aux Lista con los productos comprados por un cliente con numProducto
     */
    public static ArrayList<Producto> listaProductosComprados(Cliente cliente){
        ArrayList<Producto> aux = new ArrayList<Producto>();
        
        int nProductosComprados = cliente.obtenerFichaCliente().obtenerNumeroProductosComprados();
        for(int j = 0; j < nProductosComprados; j++){ //Por cada producto comprado por el cliente
           Producto temp = cliente.obtenerFichaCliente().obtenerProductoComprado(j, false);
           aux.add(temp);
        }
        
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
        
        aux.add(UIMensajes.mC_AñP_Hogar().toLowerCase());
        aux.add(UIMensajes.mC_AñP_Sonido().toLowerCase());
        aux.add(UIMensajes.mC_AñP_Informatica().toLowerCase());
        aux.add(UIMensajes.mC_AñP_Imagen().toLowerCase());
        aux.add(UIMensajes.mC_AñP_Telefonia().toLowerCase());
        
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