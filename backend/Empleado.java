package backend;

import java.util.ArrayList;
import java.util.Stack;

import productos.Producto;
import productos.Productos;
import uitextual.UIEmpleado;

/**
 * Clase encargada de definir los metodos comunes a todos los empleados
 * que utilizan el sistema. 
 * 
 * @author: Marcos Barrios
 * @version: 1.0
 */
public abstract class Empleado extends Usuario{
    
    //Datos con los que el empleado debe iniciar sesion
    private String usuario, contrasena;
    
    //Historial de operaciones
    private Stack<Operacion> historialOperaciones;
    
    //Base de datos de usuarios
    private Usuarios usuarios;
    
    //Base de datos de productos
    private Productos productos;
    
    //Fecha actual
    private int diaActual, mesActual, anoActual;
    
    public Empleado(Usuarios usuarios, Productos productos, 
    		int diaActual, int mesActual, int anoActual, String dni, 
    		String nombre, String email, String usuario, String contrasena){
        super(dni, nombre, email);
        this.usuarios = usuarios;
        this.productos = productos;
        this.diaActual = diaActual;
        this.mesActual = mesActual;
        this.anoActual = anoActual;
        this.usuario = usuario;
        this.contrasena = contrasena;
        historialOperaciones = new Stack<Operacion>();
    }
    
    /**
     * Devuelve la base de datos de usuarios
     * 
     * @return usuarios Base de datos de usuarios
     */
    protected Usuarios obtenerUsuarios() {
    	return usuarios;
    }
    
    /**
     * Devuelve la base de datos de productos
     * 
     * @return productos Base de datos de productos
     */
    protected Productos obtenerProductos() {
    	return productos;
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
     * Devuelve el ano actual en el que se esta usando el programa.
     * 
     * @return anoActual Ano actual
     */
    protected int obtenerAnoActual(){
        return anoActual;
    }
    
    /**
     * Asigna o reasigna el dia actual en el que el empleado
     * utiliza el programa. 
     * 
     * @param diaActual Dia actual de uso del programa
     */
    public void asignarDiaActual(int diaActual) {
    	this.diaActual = diaActual;
    }
    
    /**
     * Asigna o reasigna el mes actual en el que el empleado
     * utiliza el programa. 
     * 
     * @param mesActual Mes actual de uso del programa
     */
    public void asignarMesActual(int mesActual) {
    	this.mesActual = mesActual;
    }
    
    /**
     * Asigna o reasigna el ano actual en el que el empleado
     * utiliza el programa. 
     * 
     * @param anoActual Ano actual de uso del programa
     */
    public void asignarAnoActual(int anoActual) {
    	this.anoActual = anoActual;
    }
    
    /**
     * Anade una operacion al historial
     * 
     * @param operacion Operacion a anadir
     */
    public void anadirOperacion(Operacion operacion){
        historialOperaciones.add(operacion);
    }
    
    /**
     * Devuelve una operacion en la posicion id
     * 
     * @param id Posicion de la operacion en la coleccion
     */
    public Operacion obtenerOperacion(int id){
        return historialOperaciones.get(id);
    }
    
    /**
     * Devuelve el numero de operaciones almacenadas en
     * el historial de operaciones.
     * 
     * @return historialOperaiones.size()
     */
    public int obtenerNumeroOperaciones(){
        return historialOperaciones.size();
    }
    
    /**
     * Devuelve la contrasena asignada al empleado.
     * 
     * @return contrasena Contrasena para iniciar sesion
     */
    public String obtenerContrasena(){
        return contrasena;
    }
    
    /**
     * Asigna la contrasena que va a utilizar el empleado
     * para poder iniciar sesion en el programa.
     * 
     * @param contrasena Contrasena para iniciar sesion
     */
    public void asignarContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    /**
     * Devuelve el usuario asignado al empleado.
     * 
     * @return usuario Usuario para iniciar sesion
     */
    public String obtenerUsuario(){
        return usuario;
    }
    
    /**
     * Asigna el usuario que va a utilizar el empleado
     * para poder iniciar sesion en el programa.
     * 
     * @param usuario Usuario para iniciar sesion
     */
    public void asignarUsuario(String usuario){
        this.usuario = usuario;
    }
    
    /**
     * Busca usuarios cuyos datos coincidan con los especificados y los devuelve en
     * forma de ArrayList. Se ignoran en la busqueda los campos sin ningun caracter 
     * especificado ("").
     * 
     * Ejemplo: buscarUsuarios("", "Marcos", "Marcos@gmail.com") devuelve una lista
     * de usuarios de nombre "Marcos" Y email "Marcos@gmail.com" sin importar el DNI.
     * 
     * @param dni DNI del usuario
     * @param nombre Nombre del usuario
     * @param email Email del usuario
     * 
     * @return Lista de usuarios cuyos datos coinciden con los especificados
     */
    public ArrayList<Usuario> buscarUsuarios(String dni, String nombre, String email){
    	return Util.buscarUsuarios(obtenerUsuarios(), dni, nombre, email);
    }
    
    /**
     * Busca productos comprados cuyos datos coincidan con los especificados y los 
     * devuelve en forma de ArrayList. Se ignoran en la busqueda los campos sin 
     * ningun caracter especificado ("") y los valores numericos igual a menos uno (-1).
     * 
     * @param cliente Cliente propietario
     * @param cantidad Cantidad de unidades
     * @param precio Precio
     * @param peso Peso
     * @param descripcion Descripcion
     * @param dia Dia de compra
     * @param mes Mes de compra
     * @param ano Año de compra
     * @param tiempoGarantia Tiempo de garantia
     * @param numeroCaja Numero de caja en el que se compro
     * 
     * @return Lista de productos cuyos datos coinciden con los especificados
     */
    public ArrayList<Producto> buscarProductosEnCliente(Cliente cliente, int cantidad,
    		float precio, float peso, String descripcion, int dia, int mes, int ano, 
    		int tiempoGarantia, int numeroCaja){
    	return Util.buscarProductosEnCliente(obtenerProductos(), cliente, cantidad, precio, 
    			peso, descripcion, dia, mes, ano, tiempoGarantia, numeroCaja);
    }
    
    /**
     * Busca productos almacenados cuyos datos coincidan con los especificados y los 
     * devuelve en forma de ArrayList. Se ignoran en la busqueda los campos sin 
     * ningun caracter especificado ("") y los valores numericos igual a menos uno (-1).
     * 
     * Ejemplo: buscarProducto(-1, 2, 1, "", 1, 1, 2018, -1, -1) devuelve una lista
     * de productos cuyo precio es 2, pesan 1 kg y han sido comprados el 1/1/2018.
     * 
     * @param cliente Cliente propietario
     * @param cantidad Cantidad de unidades
     * @param precio Precio
     * @param peso Peso
     * @param descripcion Descripcion
     * @param dia Dia de compra
     * @param mes Mes de compra
     * @param ano Año de compra
     * @param tiempoGarantia Tiempo de garantia
     * @param numeroCaja Numero de caja en el que se compro
     * 
     * @return Lista de productos cuyos datos coinciden con los especificados
     */
    public ArrayList<Producto> buscarProductos(int cantidad,
    		float precio, float peso, String descripcion, int dia, int mes, int ano, 
    		int tiempoGarantia, int numeroCaja){
    	return Util.buscarProductos(obtenerProductos(), cantidad, precio, 
    			peso, descripcion, dia, mes, ano, tiempoGarantia, numeroCaja);
    }
    
    /**
     * Devuelve una lista con todos los clientes que han comprado algo en la tienda.
     * 
     * @return Lista con todos los clientes registrados
     */
    public ArrayList<Cliente> obtenerListaClientes(){
    	return Util.obtenerListaClientes(obtenerUsuarios());
    }
    
    public ArrayList<Empleado> obtenerListaEmpleados(){
    	return Util.obtenerListaEmpleados(obtenerUsuarios());
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
     * @param ano Fecha de la operacion
     */
    public void dejarConstancia(Usuario usuario, EnumOperaciones tipoOperacion, 
    		int dia, int mes, int ano){
        //Obtenemos la cadena que indicara la operacion que se ha realizado.
        String cadenaOperacion = EnumOperaciones.obtenerCadenaOperacion(tipoOperacion);
        
        Operacion operacion = new OperacionUsuario(usuario, this, cadenaOperacion, dia,
        mes, ano);
        
        //Anadimos la operacion a la ficha de cliente o a Empleado dependiendo
        //del tipo de usuario especificado
        if(usuario instanceof Cliente){
            Cliente cliente = (Cliente) usuario;
            FichaCliente fc = cliente.obtenerFichaCliente();
            
            //Anadimos operacion a la ficha de cliente
            fc.anadirOperacion(operacion);
        }else{ //Si se trata de un empleado
            if(usuario instanceof Empleado){
                Empleado empleado = (Empleado) usuario;
                empleado.anadirOperacion(operacion);
            }
        }
        
        //En cualquier caso la operacion la realizara un empleado de la tienda.
        anadirOperacion(operacion);
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
     * @param ano Fecha de la operacion
     */
    public void dejarConstancia(Cliente propietario, Producto producto, 
    Empleado responsable, EnumOperaciones tipoOperacion, int dia, int mes, int ano){
        //Obtenemos la cadena que indicara la operacion que se ha realizado.
        String cadenaOperacion = EnumOperaciones.obtenerCadenaOperacion(tipoOperacion);
        
        Operacion operacion = new OperacionProducto(producto.obtenerNumeroProducto(), 
        responsable, cadenaOperacion, dia, mes, ano);
        
        //Obtenemos la ficha de cliente y le anadimos la operacion
        FichaCliente fc = propietario.obtenerFichaCliente();
        fc.anadirOperacion(operacion);
        
        //En cualquier caso la operacion la realizara un empleado de la tienda.
        responsable.anadirOperacion(operacion);
    }
    
    /**
     * Devuelve la clase que implementa las funciones para este empleado
     * 
     * @return UIEmpleado clase UI para este empleado
     */
    public abstract UIEmpleado obtenerUI();
}