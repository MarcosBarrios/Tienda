package uitextual;

import backend.Cliente;
import backend.EpdoCajero;
import backend.Util;

import productos.Producto;
import productos.ProductoHogar;
import productos.ProductoInformatica;
import productos.ProductoTelefonia;
import productos.ProductoImagen;
import productos.ProductoSonido;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementacion de la representacion de la clase EpdoCajero en la
 * interfaz textual del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoCajero extends UIEmpleado{
    
    //Numero de la caja desde la que se esta operando
    private int numeroCaja;
    
    public UIEpdoCajero(EpdoCajero cajero) {
    	super(cajero);
        
        //Obtenemos el numero de la caja desde la cual el cajero va a operar
        formatearCadena(UIMensajes.mC_EspecificarNumeroCaja(), true, true);
        numeroCaja = (int) UIEntradas.obtenerDecimal(0, Util.NUMEROCAJAS);
    }
    
    /**
     * Devuelve el numero de caja desde la que se esta operando
     * 
     * @return numeroCaja Numero de caja desde la que se esta operando
     */
    public int obtenerNumeroCaja(){
        return numeroCaja;
    }
    
    /**
     * Devolvemos el cajero que esta trabajando sobre el programa
     * 
     * @return (EpdoCajero)obtenerUsuario()
     */
    private EpdoCajero obtenerCajero(){
        return (EpdoCajero) obtenerUsuario();
    }
    
    /**
     * Vende un producto a un cliente. 
     * 
     */
    public void venderProducto(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos el numero del producto al cual se va a anadir el reporte
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Preguntar si financiar o no el producto a vender
        boolean financiar = formatearEntradaBoolean(UIMensajes.mC_AnP_Financiar());

        //Obtenemos la descripcion para la factura
        String descripcionFactura = formatearEntradaCadena(UIMensajes.mC_AnP_DescripcionFactura(), true);
        
        boolean vendido = false;
        if(financiar) {
        	vendido = obtenerCajero().financiarProducto(dniCliente, nProducto, descripcionFactura);
        }else {
        	vendido = obtenerCajero().venderProducto(dniCliente, nProducto);
        }
        
        if(vendido) {
        	//"Se ha vendido el producto con exito"
        	System.out.println(UIMensajes.mC_VP_VendidoExito());
        }else {
        	//"No se ha encontrado el cliente, el producto o" +
    		//"no hay unidades del producto almacenadas"
        	System.out.println(UIMensajes.mC_VP_SinStock());
        }
    }
    
    /**
     * El cajero anade a la base de datos un producto nuevo.
     * 
     * Para ello se utiliza un formulario para obtener los datos
     * del producto.
     */
    public void anadirProducto(){
        //"A continuacion se va a proceder a introducir" + 
        //" las caracteristicas del producto. "
        System.out.println(UIMensajes.mC_AnP_ProcederIntroduccion());
        
        //Obtenemos los datos del producto a anadir y lo
        //anadimos a la base de datos
        obtenerCajero().anadirProducto(obtenerDatosProducto());
        
        //"Producto anadido a la base de datos con exito"
    	System.out.println(UIMensajes.mC_AnP_ProductoAnadido());
    }
    
    /**
     * Obtiene los datos del producto a anadir. (precio, cantidad,...)
     * 
     * @return Producto segun categoria con los datos asignados
     */
    private Producto obtenerDatosProducto() {
    	//Obtenemos la clase producto adecuada segun su categoria
        Producto producto = obtenerCategoria();
        
        //Creamos un formulario para los valores que son numeros        
        String entradasNumericas[] = new String[3];
        entradasNumericas[0] = UIMensajes.mC_AnP_Precio();
        entradasNumericas[1] = UIMensajes.mC_AnP_Cantidad();
        entradasNumericas[2] = UIMensajes.mC_AnP_Peso();
        float salidasNumericas[] = formularioDecimales(entradasNumericas);
        producto.asignarPrecio(salidasNumericas[0]);
        producto.asignarCantidad((int) salidasNumericas[1]);
        producto.asignarPeso(salidasNumericas[2]);
        
        //Descripcion
        String descripcion = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
        producto.asignarDescripcion(descripcion);
        
        //"Tiempo de garantia"
        int tiempoGarantia = (int)formatearEntradaDecimal(UIMensajes.mC_AcP_TiempoGarantia());
        producto.asignarTiempoGarantia(tiempoGarantia);
        
        //Asignamos caracteristicas al producto segun su categoria
        producto = anadirProductoEspecifico(producto);
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de anadirProducto(...)
     * 
     * Obtiene las opciones especificas iniciales del producto segun su categoria
     * 
     * @param producto Producto al que anadir las opciones especificas
     */
    private Producto anadirProductoEspecifico(Producto producto){
        //Anadimos opciones a actualizar segun la categoria del producto
        if(producto instanceof ProductoHogar){
            ProductoHogar ph = (ProductoHogar) producto;
            
            //"Ancho"
            int nuevoAncho = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Ancho());
            ph.asignarAncho(nuevoAncho);
           
            //"Alto"
            int nuevoAlto = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Alto());
            ph.asignarAncho(nuevoAlto);
            
            //"Consumo"
            float nuevoConsumo = (float) formatearEntradaDecimal(UIMensajes.mC_ICE_Consumo());
            ph.asignarConsumo(nuevoConsumo); 
            
            return ph;
        }else if(producto instanceof ProductoInformatica){
            ProductoInformatica pi = (ProductoInformatica) producto;
            //"Frecuencia"
            int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
            pi.asignarFrecuencia(nuevaFrecuencia);
         
            //"Numero de nucleos"
            int nuevoNumeroNucleos = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_NumeroNucleos());
            pi.asignarNumeroNucleos(nuevoNumeroNucleos);
            
            //"Capacidad de almacenamiento"
            float nuevaCapacidadAlmacenamiento = formatearEntradaDecimal(UIMensajes.mC_ICE_CapacidadAlmacenamiento());
            pi.asignarCapacidadAlmacenamiento(nuevaCapacidadAlmacenamiento);
                
            return pi;
        }else if(producto instanceof ProductoSonido){
            ProductoSonido ps = (ProductoSonido) producto;

            //"Inalambrico"
            boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
            ps.cambiarInalambrico(esInalambrico);
                                           
            //"Resistente al agua"
            boolean esResistenteAgua = formatearEntradaBoolean(UIMensajes.mC_ICE_ResistenteAgua());
            ps.cambiarResistenteAgua(esResistenteAgua);
                                         
            //"Bluetooth
            boolean tieneBluetooth = formatearEntradaBoolean(UIMensajes.mC_ICE_Bluetooth());
            ps.cambiarResistenteAgua(tieneBluetooth);
            
            //"Frecuencia"
            int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
            ps.asignarFrecuencia(nuevaFrecuencia);
            
            return ps;
        }else if(producto instanceof ProductoTelefonia){
            ProductoTelefonia pt = (ProductoTelefonia) producto;
            //"Bateria"
            boolean tieneBateria = formatearEntradaBoolean(UIMensajes.mC_ICE_TieneBateria());
            pt.cambiarInalambrico(tieneBateria);
            
            //"Inalambrico"
            boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
            pt.cambiarInalambrico(esInalambrico);
                     
            //"Frecuencia"
            int nuevaDuracion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Duracion());
            pt.asignarDuracion(nuevaDuracion);  
            
            return pt;
        }else if(producto instanceof ProductoImagen){
            ProductoImagen pi = (ProductoImagen) producto;
            
            //"Pulgadas"
            int nuevasPulgadas = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Pulgadas());
            pi.asignarPulgadas(nuevasPulgadas);
            
            //"Ancho de resolucion"
            int nuevoAnchoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AnchoResolucion());
            pi.asignarAnchoResolucion(nuevoAnchoResolucion);
            
            //"Alto de resolucion"
            int nuevoAltoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AltoResolucion());
            pi.asignarAltoResolucion(nuevoAltoResolucion);
                
            return pi;
        }
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de anadirProducto()
     * 
     * Devuelve un producto segun su categoria
     */
    private Producto obtenerCategoria(){
        //Categoria del producto (Hogar|Telefonia|Imagen|Informatica)
        String categoria;
        
        //Obtenemos la lista con las categorias de productos
        ArrayList<String> listaCategorias = Util.listaCategoriasProductos();
        
        //"Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) "
        System.out.println(UIMensajes.mC_AnP_ElegirCategoriaProducto());
        categoria = UIEntradas.obtenerCadenaLimitada(listaCategorias, false);
        
        //"Categoria seleccionada correctamente"
        System.out.println(UIMensajes.mC_AnP_CategoriaSeleccionada());
        
        //Creamos la clase del producto segun la categoria que sea
        Producto producto = null;
        if(categoria.equals(UIMensajes.mC_AnP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Imagen().toLowerCase())){
            producto = new ProductoImagen();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Sonido().toLowerCase())){
            producto = new ProductoSonido();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Informatica().toLowerCase())){
            producto = new ProductoInformatica();
        }
        
        return producto;
    }
    
    /**
     * Actualiza los datos de un producto. Para ello pregunta en primer lugar
     * por el numero del producto y el dni del cliente y luego imprime los datos
     * del producto encontrado.
     * 
     * A continuación se actualiza el producto mediante una llamada a un menu.
     * 
     */
    public void actualizarProducto(){
        //"¿El producto pertenece a un cliente? (si/no)"
        formatearCadena(UIMensajes.mC_AcP_BuscarCliente(), true, true);
        boolean buscarCliente = UIEntradas.obtenerBooleana();
        
        //"Si el producto a actualizar no pertenece a un cliente el"
		//+ "campo DNI no será utilizado por lo que su valor no importa."
        formatearCadena(UIMensajes.mC_AcP_SiNoBuscarEnCliente(), true, false);
        
        //Obtenemos el numero del producto a modificar
        int numeroProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos un producto comprado o del almacen indirectamente para poder
        //dejar constancia de la operacion y lo actualizamos mediante un menu 
        Producto producto = obtenerCajero().obtenerProductoActualizar(dniCliente, 
        		numeroProducto, buscarCliente);
        producto = actualizarProducto(producto);
    }
    
    /**
     * Utiliza un menu para actualizar los datos de un producto.
     * 
     * @param producto Producto a actualizar
     * 
     * @return Producto ya actualizado
     */
    private Producto actualizarProducto(Producto producto) {

        if(producto!=null){
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado() + 
            		producto.obtenerNumeroProducto());
            
            //Imprimimos las caracteristicas del producto
            System.out.print(producto);
            
            //Creamos un menu de actualizacion de productos y lo activamos
            UIMenuActualizarProducto map = new UIMenuActualizarProducto(producto);
            map.activar();
            
            //Actualizamos el producto
            producto = map.obtenerProducto();
        }else{
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado());
        }
        return null;
    }
    
    /**
     * Imprime los datos de un producto
     * 
     */
    public void imprimirDatosProducto(){
        //"Ver las caracteristicas de un producto"
        formatearCadena(UIMensajes.mC_OpcionVerDatosProducto(), false, true);
        
        //"¿El producto pertenece a un cliente? (si/no)"
        boolean perteneceCliente = formatearEntradaBoolean(UIMensajes.mC_AcP_BuscarCliente());
        
        if(!perteneceCliente){
        	//Imprimir producto almacenado en la base de datos
            imprimirProductoBaseDatos();
        }else{
        	//Imprimir producto comprado por un cliente
        	imprimirProductoComprado();
        }
        
    }

    /**
     * Pregunta por un numero de producto, se busca el producto
     * en la base de datos de la tienda y se imprimen sus datos
     */
    private void imprimirProductoBaseDatos() {
    	//Obtenemos el numero del producto
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos el producto de la base de datos
        Producto bProducto = obtenerCajero().obtenerProductoBaseDatos(nProducto);
        if(bProducto!=null){ //Si encuentra el producto
            System.out.println(bProducto);
        }else{ //Si NO encuentra el producto
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado() +
                nProducto);
        }
    }
    
    /**
     * Pregunta por un dni, un numero de producto, obtiene el 
     * producto comprado por el cliente e imprime sus datos.
     */
    private void imprimirProductoComprado() {
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos el numero del producto
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos el producto del cliente
        Producto productoComprado = obtenerCajero().obtenerProductoComprado(dniCliente, nProducto);
        if(productoComprado!=null){ //Si encuentra el producto
        	System.out.println(productoComprado);
        }else{
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado() +
                nProducto);
        }
    }
    
    /**
     * Imprime la lista de productos en la base de datos de productos 
     * seguida de la lista de productos comprados por cada cliente.
     * 
     */
    public void imprimirListaProductos(){
        System.out.println(UIMensajes.g_EncabezadoMenus());
        //"Lista de productos" 
        System.out.println(UIMensajes.mC_OpcionListaProductos() + ": ");
        
        //Imprimimos los productos en la base de datos. 
        ArrayList<Producto> productosBaseDatos = obtenerCajero().listaProductosBaseDatos();
        Iterator<Producto> itr = productosBaseDatos.iterator();
        while(itr.hasNext()) {
        	//Imprime el producto llamado al toString()
        	System.out.println(itr.next());
        }
        
        //Ahora imprimimos los productos comprados por los clientes
        imprimirProductosComprados();
    }
    
    /**
     * Imprime la lista de productos comprados por un cliente ademas del
     * cliente propietario de cada producto siendo imprimido.
     */
    private void imprimirProductosComprados() {
    	//Ahora imprimimos los productos comprados por los clientes
        ArrayList<Cliente> listaClientes = obtenerCajero().listaClientes();
        Iterator<Cliente> itr = listaClientes.iterator();
        while(itr.hasNext()) {
        	Cliente cliente = itr.next();

        	//Imprime los datos del cliente
        	System.out.println(cliente);
        	
        	//Imprimimos los productos del cliente
        	ArrayList<Producto> productosComprados = obtenerCajero().listaProductosComprados(cliente);
        	Iterator<Producto> itr2 = productosComprados.iterator();
        	while(itr2.hasNext()) {
        		//Imprime el producto llamado al toString()
            	System.out.println(itr2.next());
        	}
        }
    }
    
    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuEpdoCajero(this);
	}
}
