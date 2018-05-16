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
import backend.EpdoComercial;
import backend.EpdoPostVenta;
import backend.EpdoCajero;
import backend.EpdoTecnico;
import backend.EpdoFinanciacion;

import productos.Producto;
import productos.Productos;
import productos.ProductoSonido;
import productos.ProductoInformatica;
import productos.ProductoHogar;
import productos.ProductoTelefonia;
import productos.ProductoImagen;

import java.util.ArrayList;
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
            float entrada = UIEntradas.obtenerDecimal(-1, Util.MAXIMACANTIDAD);
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
        return UIEntradas.obtenerDecimal(-1, Util.MAXIMACANTIDAD);
    }
    
    /**
     * Obtiene un estado booleano usando texto formateado
     */
    public boolean formatearEntradaBoolean(String nombreEntrada){
        formatearCadena(nombreEntrada, true, false);
        System.out.print(" [true/si] o [false/no)]: ");
        return UIEntradas.obtenerBooleana(true);
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
     * Imprime una lista de usuarios 
     * 
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void imprimirBusquedaUsuarios(Usuarios usuarios){
        //"Indicar a continuacion los datos de busqueda"
        formatearCadena(UIMensajes.b_IndicarDatos(), true, true);
        //"No escriba nada en el campo para ignorar ese dato"
        System.out.println("(" + UIMensajes.b_ValoresPredeterminados() + ")");
        
        //Obtenemos los datos necesarios para una busqueda
        String dni = formatearEntradaCadena(UIMensajes.g_DNI(), true);
        String nombre = formatearEntradaCadena(UIMensajes.g_Nombre(), true);
        String email = formatearEntradaCadena(UIMensajes.g_Email(), true);
        
        //Obtenemos una lista de usuarios mediante una busqueda
        ArrayList<Usuario> listaUsuarios = Util.buscarUsuarios(usuarios,
        dni, nombre, email);
        //Iteramos los usuarios obtenidos
        Iterator<Usuario> itr = listaUsuarios.iterator();
        while(itr.hasNext()){
            Usuario temp = itr.next(); //Obtenemos un usuario
            
            //Si el usuario es un empleado
            if(temp instanceof Empleado){
                //"Empleados"
                formatearCadena(UIMensajes.b_Empleados(), true, true);
                
                System.out.println(); //Primera y unica linea
                System.out.print("\t(");
                //Distinguimos entre el tipo de empleado
                if(temp instanceof EpdoCajero){
                    //"Cajero"
                    System.out.print(UIMensajes.mGU_AñE_Cajero());
                }else if(temp instanceof EpdoPostVenta){
                    //"PostVenta"
                    System.out.print(UIMensajes.mGU_AñE_PostVenta());
                }else if(temp instanceof EpdoTecnico){
                    //"Tecnico"
                    System.out.print(UIMensajes.mGU_AñE_Tecnico());
                }else if(temp instanceof EpdoFinanciacion){
                    //"Financiacion"
                    System.out.print(UIMensajes.mGU_AñE_Financiacion());
                }else if(temp instanceof EpdoComercial){
                    //"Comercial"
                    System.out.print(UIMensajes.mGU_AñE_Comercial());
                }
                System.out.print(") " + UIMensajes.g_DNI() + ": ");
                System.out.print(usuario.obtenerDNI());
                System.out.print(" |" + UIMensajes.g_Nombre() + ": ");
                System.out.print(usuario.obtenerNombreUsuario());
                System.out.print(" |" + UIMensajes.g_Email() + ": ");
                System.out.print(usuario.obtenerEmailUsuario());
            }else if(temp instanceof Cliente){ //Si el usuario es un cliente
                //"Clientes"
                formatearCadena(UIMensajes.b_Clientes(), true, true);
                
                System.out.println(); //Primera y unica linea
                System.out.print("\t" + UIMensajes.g_DNI() + ": ");
                System.out.print(usuario.obtenerDNI());
                System.out.print(" |" + UIMensajes.g_Nombre() + ": ");
                System.out.print(usuario.obtenerNombreUsuario());
                System.out.print(" |" + UIMensajes.g_Email() + ": ");
                System.out.print(usuario.obtenerEmailUsuario());
            }
        }
    }
    
    /**
     * Imprime una lista de productos de un cliente o de la base de datos
     * 
     * @param usuarios Base de datos de usuarios
     * @param productos Base de datos de productos
     */
    public void imprimirBusquedaProductos(Usuarios usuarios, Productos productos){
        //"Indicar a continuacion los datos de busqueda"
        formatearCadena(UIMensajes.b_IndicarDatos(), true, true);
        //"No escriba nada en el campo para ignorar ese dato"
        System.out.println("(" + UIMensajes.b_ValoresNegativos() + ")");
        
        //"¿Buscar en clientes?"
        boolean buscarEnClientes = formatearEntradaBoolean(UIMensajes.b_BuscarEnCliente());
        
        //Creamos un formulario para las entradas numericas
        String[] entradas = new String[8];
        entradas[0] = UIMensajes.mC_AñP_Cantidad();
        entradas[1] = UIMensajes.mC_AñP_Peso();
        entradas[2] = UIMensajes.mC_AñP_Precio();
        entradas[3] = UIMensajes.mC_AñP_Dia();
        entradas[4] = UIMensajes.mC_AñP_Mes();
        entradas[5] = UIMensajes.mC_AñP_Año();
        entradas[6] = UIMensajes.mC_AcP_TiempoGarantia();
        entradas[7] = UIMensajes.mC_ILP_NumeroCaja();
        float[] salidas = formularioDecimales(entradas);
        
        //"Descripcion"
        String descripcion = formatearEntradaCadena(UIMensajes.mC_AñP_Descripcion(), true);
        
        //Distinguimos entre buscar el producto de un cliente o 
        //de la base de datos de la tienda
        if(buscarEnClientes){
            //"Buscando en clientes"
            formatearCadena(UIMensajes.b_BuscandoEnClientes(), true, true);
            
            //Iteramos todos los usuarios y trabajamos con los que son clientes
            for(int i = 0; i < usuarios.obtenerTamaño(); i++){
                Usuario usuario = usuarios.obtenerUsuario(i);
                
                if(usuario instanceof Cliente){
                    //Obtenemos el cliente
                    Cliente cliente = (Cliente) usuario;
                    
                    //Obtenemos la lista de productos que cumplen con las caracteristicas especificadas
                    //Util: Productos productos, Cliente cliente,
                    //int cantidad, float precio, float peso, String descripcion, int dia,
                    //int mes, int año, int tiempoGarantia, int numeroCaja
                    ArrayList<Producto> listaProductosCliente = Util.buscarProductosEnCliente(
                    productos, cliente, (int)salidas[0], salidas[1], salidas[2], descripcion,
                    (int)salidas[3], (int)salidas[4], (int)salidas[5], (int)salidas[6], 
                    (int)salidas[7]);
                    
                    //Iteramos la lista obtenida e imprimimos los datos de cada producto
                    Iterator<Producto> itr = listaProductosCliente.iterator();
                    while(itr.hasNext()){
                        Producto producto = itr.next();
                        
                        System.out.println(); //Primera linea
                        System.out.print("\t");
                        System.out.print(UIMensajes.g_Nombre() + ": ");
                        System.out.print(cliente.obtenerNombreUsuario() + " (");
                        //Imprimimos el tipo de producto
                        if(producto instanceof ProductoInformatica){
                            System.out.print(UIMensajes.mC_AñP_Informatica());
                        }else if(producto instanceof ProductoImagen){
                            System.out.print(UIMensajes.mC_AñP_Imagen());
                        }else if(producto instanceof ProductoTelefonia){
                            System.out.print(UIMensajes.mC_AñP_Telefonia());
                        }else if(producto instanceof ProductoSonido){
                            System.out.print(UIMensajes.mC_AñP_Sonido());
                        }else if(producto instanceof ProductoHogar){
                            System.out.print(UIMensajes.mC_AñP_Hogar());
                        }
                        System.out.print(") " + UIMensajes.mC_LP_NumeroProducto() + ": ");
                        System.out.print(producto.obtenerNumeroProducto());
                        System.out.print(" |" + UIMensajes.mC_AñP_Cantidad() + ": ");
                        System.out.print(producto.obtenerCantidad());
                        System.out.print(" |" + UIMensajes.mC_AñP_Precio() + ": ");
                        System.out.print(producto.obtenerPrecio());
                        System.out.print(" |" + UIMensajes.mC_AñP_Peso() + ": ");
                        System.out.print(producto.obtenerPeso());
                        
                        System.out.println(); //Segunda linea
                        System.out.print("\t" + UIMensajes.mC_AñP_Dia() + ": ");
                        System.out.print(producto.obtenerDiaCompra());
                        System.out.print(" |" + UIMensajes.mC_AñP_Mes() + ": ");
                        System.out.print(producto.obtenerMesCompra());
                        System.out.print(" |" + UIMensajes.mC_AñP_Año() + ": ");
                        System.out.print(producto.obtenerAñoCompra());
                        System.out.print(" |" + UIMensajes.mC_AcP_TiempoGarantia() + ": ");
                        System.out.print(producto.obtenerTiempoGarantia());
                        System.out.print(" |" + UIMensajes.mC_ILP_NumeroCaja() + ": ");
                        System.out.print(producto.obtenerNumeroCaja());
                        
                        System.out.println(); //Tercera y ultima linea
                        System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                        System.out.print(producto.obtenerDescripcion());
                        System.out.println("...");
                    }
                }
            }
        }else{
            //"Buscando en la base de datos de la tienda"
            formatearCadena(UIMensajes.b_BuscandoEnBaseDeDatos(), true, true);
            
            
            //Obtenemos la lista de productos que cumplen con las caracteristicas especificadas
            //Util: Productos productos,
            //int cantidad, float precio, float peso, String descripcion, int dia,
            //int mes, int año, int tiempoGarantia, int numeroCaja
            ArrayList<Producto> listaProductos = Util.buscarProductos(
            productos,(int)salidas[0], salidas[1], salidas[2], descripcion,
            (int)salidas[3], (int)salidas[4], (int)salidas[5], (int)salidas[6], 
            (int)salidas[7]);
            //Iteramos la lista obtenida e imprimimos los datos de cada producto
            Iterator<Producto> itr = listaProductos.iterator();
            while(itr.hasNext()){
                Producto producto = itr.next();
                
                System.out.println(); //Primera linea
                System.out.print("\t(");
                //Imprimimos el tipo de producto
                if(producto instanceof ProductoInformatica){
                    System.out.print(UIMensajes.mC_AñP_Informatica());
                }else if(producto instanceof ProductoImagen){
                    System.out.print(UIMensajes.mC_AñP_Imagen());
                }else if(producto instanceof ProductoTelefonia){
                    System.out.print(UIMensajes.mC_AñP_Telefonia());
                }else if(producto instanceof ProductoSonido){
                    System.out.print(UIMensajes.mC_AñP_Sonido());
                }else if(producto instanceof ProductoHogar){
                    System.out.print(UIMensajes.mC_AñP_Hogar());
                }
                System.out.print(") " + UIMensajes.mC_AñP_Cantidad() + ": ");
                System.out.print(producto.obtenerCantidad());
                System.out.print(" |" + UIMensajes.mC_AñP_Precio() + ": ");
                System.out.print(producto.obtenerPrecio());
                System.out.print(" |" + UIMensajes.mC_AñP_Peso() + ": ");
                System.out.print(producto.obtenerPeso());
                
                System.out.println(); //Segunda linea
                System.out.print("\t" + UIMensajes.mC_AñP_Dia() + ": ");
                System.out.print(producto.obtenerDiaCompra());
                System.out.print(" |" + UIMensajes.mC_AñP_Mes() + ": ");
                System.out.print(producto.obtenerMesCompra());
                System.out.print(" |" + UIMensajes.mC_AñP_Año() + ": ");
                System.out.print(producto.obtenerAñoCompra());
                System.out.print(" |" + UIMensajes.mC_AcP_TiempoGarantia() + ": ");
                System.out.print(producto.obtenerTiempoGarantia());
                System.out.print(" |" + UIMensajes.mC_ILP_NumeroCaja() + ": ");
                System.out.print(producto.obtenerNumeroCaja());
                
                System.out.println(); //Tercera y ultima linea
                System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                System.out.print(producto.obtenerDescripcion());
                System.out.println("...");
            }
            
        }
        
    }
}
