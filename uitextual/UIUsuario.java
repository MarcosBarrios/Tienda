package uitextual;

import backend.Cliente;
import backend.Usuario;
import backend.Usuarios;
import backend.Empleado;
import backend.Util;
import backend.FichaCliente;
import backend.EnumOperaciones;
import backend.Operacion;
import backend.OperacionUsuario;
import backend.OperacionProducto;

import productos.Producto;

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
    
    //Fecha actual
    private int diaActual, mesActual, añoActual;
    
    //Metodo constructor
    public UIUsuario(Usuario usuario, int diaActual, int mesActual, int añoActual){
        this.usuario = usuario;
        this.diaActual = diaActual;
        this.mesActual = mesActual;
        this.añoActual = añoActual;
    }
    
    /**
     * Devuelve el usuario utilizando el programa
     * 
     * @return usuario Usuario utilizando el programa
     */
    protected Usuario obtenerUsuario(){
        return usuario;
    }
    
    /**
     * Devuelve el dia actual en el que se esta usando el programa.
     * 
     * @return diaActual Dia actual
     */
    protected int obtenerDiaActual(){
        return diaActual;
    }
    
    /**
     * Devuelve el mes actual en el que se esta usando el programa.
     * 
     * @return mesActual Mes actual
     */
    protected int obtenerMesActual(){
        return mesActual;
    }
    
    /**
     * Devuelve el año actual en el que se esta usando el programa.
     * 
     * @return añoActual Año actual
     */
    protected int obtenerAñoActual(){
        return añoActual;
    }
    
    /**
     * Deja constancia de las acciones realizadas en los respectivos
     * historiales.
     * 
     * @param cliente Cliente involucrado en la operacion
     * @param responsable Empleado que ha realizado la accion
     * @param tipoOperacion Tipo de operacion realizada
     * @param dia Fecha de la operacion
     * @param mes Fecha de la operacion
     * @param año Fecha de la operacion
     */
    protected void dejarConstancia(Usuario usuario, Empleado responsable, 
    EnumOperaciones tipoOperacion, int dia, int mes, int año){
        //Obtenemos la cadena que indicara la operacion que se ha realizado.
        String cadenaOperacion = EnumOperaciones.obtenerCadenaOperacion(tipoOperacion);
        
        Operacion operacion = new OperacionUsuario(usuario, responsable, cadenaOperacion, dia,
        mes, año);
        
        //Añadimos la operacion a la ficha de cliente o a Empleado dependiendo
        //del tipo de usuario especificado
        if(usuario instanceof Cliente){
            Cliente cliente = (Cliente) usuario;
            FichaCliente fc = cliente.obtenerFichaCliente();
            
            //Añadimos operacion a la ficha de cliente
            fc.añadirOperacion(operacion);
        }else{ //Si se trata de un empleado
            if(usuario instanceof Empleado){
                Empleado empleado = (Empleado) usuario;
                empleado.añadirOperacion(operacion);
            }
        }
        
        //En cualquier caso la operacion la realizara un empleado de la tienda.
        responsable.añadirOperacion(operacion);
    }
    
    /**
     * Deja constancia de las acciones realizadas en los respectivos
     * historiales.
     * 
     * @param numProducto Numero del producto involucrado
     * @param responsable Empleado que ha realizado la accion
     * @param tipoOperacion Tipo de operacion realizada
     * @param dia Fecha de la operacion
     * @param mes Fecha de la operacion
     * @param año Fecha de la operacion
     */
    protected void dejarConstancia(Cliente propietario, Producto producto, 
    Empleado responsable, EnumOperaciones tipoOperacion, int dia, int mes, int año){
        //Obtenemos la cadena que indicara la operacion que se ha realizado.
        String cadenaOperacion = EnumOperaciones.obtenerCadenaOperacion(tipoOperacion);
        
        Operacion operacion = new OperacionProducto(producto.obtenerNumeroProducto(), 
        responsable, cadenaOperacion, dia, mes, año);
        
        //Obtenemos la ficha de cliente y le añadimos la operacion
        FichaCliente fc = propietario.obtenerFichaCliente();
        fc.añadirOperacion(operacion);
        
        //En cualquier caso la operacion la realizara un empleado de la tienda.
        responsable.añadirOperacion(operacion);
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
        return UIEntradas.obtenerBooleana(false);
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
        //"* * * * * * ....... * *"
        System.out.println(UIMensajes.g_EncabezadoMenus());
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            System.out.println(UIMensajes.g_Nombre() + ": " +
                usuarios.obtenerUsuario(i).obtenerNombreUsuario() + 
                "  " + usuarios.obtenerUsuario(i).obtenerEmailUsuario());
        }
    }
}
