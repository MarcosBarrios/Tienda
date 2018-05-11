package uitextual;

import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;
import backend.EpdoCajero;
import backend.Util;

import productos.Productos;
import productos.Producto;
import productos.ProductoHogar;
import productos.ProductoInformatica;
import productos.ProductoTelefonia;
import productos.ProductoImagen;
import productos.ProductoSonido;
import productos.Caracteristica;
import productos.EnumEstadoProducto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementacion de la representacion de la clase EpdoCajero en la
 * interfaz textual del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoCajero extends UIUsuario{
    
    public UIEpdoCajero(EpdoCajero usuario){
        super(usuario);
    }
    
    /**
     * Vende un producto a un cliente. 
     * 
     * @param usuarios Base de datos de usuarios
     * @param productos Base de datos de productos
     */
    public void venderProducto(Usuarios usuarios, Productos productos){
        //Obtener el cliente al que se va a vender el producto. "Indicar el nombre del cliente"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario usuario = usuarios.obtenerUsuario(neCliente.toLowerCase());
        
        boolean encontrado = true;
        if(usuario!=null){
            
            if(usuario instanceof Cliente){
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = productos.obtenerProducto(nProducto, true);
                
                if(producto!=null){ 
                    if(producto.obtenerCantidad()>0){ //Si hay al menos un producto en el almacen
                        //Añadir producto a la lista de productos comprados del cliente
                        cliente.obtenerFichaCliente().añadirProductoComprado(producto);
                        producto.asignarCantidad(producto.obtenerCantidad()-1);
                    }else{ //Sin reservas
                        //"No quedan reservas del producto especificado"
                        System.out.println(UIMensajes.mC_VP_SinStock());
                    }
                    
                }else{
                    //"No se ha encontrado ningun producto con el numero "
                    System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
                }
            }
        }else{ //En caso de que no encuentre ningun usuario
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * El cajero ingresa en la base de datos de productos un producto que
     * la tienda acaba de recibir.
     * 
     * Interrogatorio textual sobre las caracteristicas del producto.
     *
     * @param productos Base de datos de productos de la tienda
     */
    public void añadirProducto(Productos productos){
        //"A continuacion se va a proceder a introducir" + 
        //" las caracteristicas del producto. "
        System.out.println(UIMensajes.mC_AñP_ProcederIntroduccion());
        
        //Obtenemos la clase producto adecuada segun su categoria
        Producto producto = obtenerCategoria();
        
        //Creamos un formulario para los valores que son numeros        
        String entradasNumericas[] = new String[6];
        entradasNumericas[0] = UIMensajes.mC_AñP_Precio();
        entradasNumericas[1] = UIMensajes.mC_AñP_Cantidad();
        entradasNumericas[2] = UIMensajes.mC_AñP_Peso();
        entradasNumericas[3] = UIMensajes.mC_AñP_Dia();
        entradasNumericas[4] = UIMensajes.mC_AñP_Mes();
        entradasNumericas[5] = UIMensajes.mC_AñP_Año();
        float salidasNumericas[] = formularioDecimales(entradasNumericas);
        producto.asignarPrecio(salidasNumericas[0]);
        producto.asignarCantidad((int) salidasNumericas[1]);
        producto.asignarPeso(salidasNumericas[2]);
        producto.asignarDiaCompra((int)salidasNumericas[3]);
        producto.asignarMesCompra((int)salidasNumericas[4]);
        producto.asignarAñoCompra((int)salidasNumericas[5]);
        
        //Descripcion
        String descripcion = formatearEntradaCadena(UIMensajes.mC_AñP_Descripcion(), true);
        producto.asignarDescripcion(descripcion);
        
        //Se comprueba si la base de datos contiene un producto igual al que se
        //va a añadir, en cuyo caso simplemente aumenta la cantidad del actual
        //producto en la base de datos en vez de añadir un producto mas a esta.
        Producto productoIgual = Util.obtenerProductoIgual(producto, productos);
        if(productoIgual!=null){
            productoIgual.asignarCantidad(productoIgual.obtenerCantidad()+1);
        }else{
            productos.añadirProducto(producto);
        }
    }
    
    /**
     * Metodo auxiliar de añadirProducto()
     * 
     * Devuelve un producto segun su categoria
     */
    private Producto obtenerCategoria(){
        //Categoria del producto (Hogar|Telefonia|Imagen|Informatica)
        String categoria;
        
        //Obtenemos la lista con las categorias de productos
        ArrayList<String> listaCategorias = Util.listaCategoriasProductos();
        
        //"Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) "
        System.out.println(UIMensajes.mC_AñP_ElegirCategoriaProducto());
        categoria = UIEntradas.obtenerCadenaLimitada(listaCategorias, false);
        
        //"Categoria seleccionada correctamente"
        System.out.println(UIMensajes.mC_AñP_CategoriaSeleccionada());
        
        //Creamos la clase del producto segun la categoria que sea
        Producto producto = null;
        if(categoria.equals(UIMensajes.mC_AñP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AñP_Imagen().toLowerCase())){
            producto = new ProductoImagen();
        }
        else if(categoria.equals(UIMensajes.mC_AñP_Sonido().toLowerCase())){
            producto = new ProductoSonido();
        }
        else if(categoria.equals(UIMensajes.mC_AñP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AñP_Informatica().toLowerCase())){
            producto = new ProductoInformatica();
        }
        
        return producto;
    }
    
    /**
     * Actualiza los datos de un producto. Para ello pregunta en primer lugar
     * por el numero del producto, posteriormente enseña una lista con los datos
     * del producto y pregunta que opcion quiere modificar.
     * 
     * Por ultimo pregunta por el nuevo valor al que se quiere modificar.
     * 
     * Adicionalmente habra una opcion para añadir una caracteristica adicional.
     * 
     * @param productos Base de datos de productos de la tienda
     * @param numProducto Numero del producto que se quiere modificar
     */
    public void actualizarProducto(Productos productos, Usuarios usuarios){
        //"¿El producto pertenece a un cliente? (si/no)"
        boolean buscarCliente = formatearEntradaBoolean(UIMensajes.mC_AcP_BuscarCliente());
        
        //Obtenemos el numero del producto a modificar
        int numeroProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos el producto con el numero de producto
        Producto producto = null;
        if(buscarCliente){ //Si se va a buscar el producto en un cliente
            //"Especificar el nombre, email o dni del cliente"
            String entrada = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
            Usuario usuario = usuarios.obtenerUsuario(entrada);
            
            boolean encontrado = false;
            if(usuario instanceof Cliente){
                if(usuario!=null){
                    Cliente cliente = (Cliente) usuario;
                
                    ArrayList<Producto> listaProductos = Util.listaProductosComprados(cliente);
                    Iterator<Producto> itr = listaProductos.iterator();
                    while(itr.hasNext()){
                        Producto tempProducto = itr.next();
                        
                        //Si se encuentra un producto con el numero especificado en la 
                        //lista de productos del cliente
                        if(tempProducto.obtenerNumeroProducto()==numeroProducto){
                            encontrado = true;
                            producto = tempProducto;
                            
                            //"mC_AcP_EncontradoEnCliente"
                            System.out.println(UIMensajes.mC_AcP_EncontradoEnCliente() + ": "
                                + cliente.obtenerNombreUsuario());
                        }
                    }
                }else{ //Si no se encuentra el cliente
                    //"Cliente no encontrado"
                    System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
                }

            }else{ //Si no se encuentra el cliente
                //"Cliente no encontrado"
                System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
            }
            
            if(!encontrado){ //Si no se ha encontrado ningun producto
                //"No se ha encontrado ningun producto con el numero"
                System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
            }
        }else{
            producto = productos.obtenerProducto(numeroProducto, true);
        }
        
        if(producto!=null){
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()+
                numeroProducto);
            
            imprimirCaracteristicasProducto(producto);
            
            //Creamos un menu para elegir que opcion modificar
            menuModificacionOpciones(producto);
        }else{ //En caso de que no encuentre el producto
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()
                + numeroProducto);
        }
        
    }
    
    /**
     * Metodo auxiliar de actualizarProducto(...)
     * 
     * Crea un menu para elegir la opcion a modificar
     * 
     */
    private void menuModificacionOpciones(Producto producto){
        //"Elegir opcion a modificar"
        System.out.println(UIMensajes.mC_AcP_ElegirOpcion());
        
        //Iniciamos el menu y añadimos las opciones
        UIMenu menuModOpciones = añadirOpcionesMenuActualizar();
    
        //Imprime el menu
        menuModOpciones.imprimirOpciones();
        
        //Implementacion de que hace cada opcion
        int entrada = UIEntradas.obtenerEntero(0, menuModOpciones.obtenerNumeroOpciones());
        switch(entrada){
            case 0: //Precio
                float nuevoPrecio = formatearEntradaDecimal(UIMensajes.mC_AñP_Precio());
                producto.asignarPrecio(nuevoPrecio);
                break;
            case 1: //Cantidad
                int nuevaCantidad = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Cantidad());
                producto.asignarCantidad(nuevaCantidad);
                break;
            case 2: //Descripcion
                String nuevaDescripcion = formatearEntradaCadena(UIMensajes.mC_AñP_Descripcion(), true);
                producto.asignarDescripcion(nuevaDescripcion);
                break;
            case 3: //Peso
                float nuevoPeso = formatearEntradaDecimal(UIMensajes.mC_AñP_Peso());
                producto.asignarPeso(nuevoPeso);
                break;
            case 4: //Devuelve el estado de la financiacion del producto
                boolean financiacion = formatearEntradaBoolean(UIMensajes.mC_AcP_Financiado());
                producto.cambiarFinanciado(financiacion);
                break;
            case 5: //Estado del producto (INTACTO, ROTO, DEVUELTO (29/04))
                ArrayList<String> listaEstados = EnumEstadoProducto.obtenerEstados();
                formatearCadena(UIMensajes.mC_LP_Estado(), true, false); //"Estado"
                System.out.print(" [");
                //Vamos a imprimir el numero de estados posibles
                Iterator<String> itr = listaEstados.iterator();
                while(itr.hasNext()){
                    String temp = itr.next().toLowerCase();
                    System.out.print(temp + "/");
                }
                System.out.print("] :");
                //Obtenemos una cadena que sea "intacto" o "roto" o "vendido"
                String nuevoEstado = UIEntradas.obtenerCadenaLimitada(listaEstados, false);
                String estadoIntacto = EnumEstadoProducto.estadoProductoIntacto().toLowerCase();
                String estadoRoto = EnumEstadoProducto.estadoProductoRoto().toLowerCase();
                String estadoDevuelto = EnumEstadoProducto.estadoProductoDevuelto().toLowerCase();
                if(nuevoEstado.equals(estadoIntacto)){
                    producto.cambiarEstado(EnumEstadoProducto.INTACTO);
                }else if(nuevoEstado.equals(estadoRoto)){
                    producto.cambiarEstado(EnumEstadoProducto.ROTO);
                }else if(nuevoEstado.equals(estadoDevuelto)){
                    producto.cambiarEstado(EnumEstadoProducto.DEVUELTO);
                }
                break;
            case 6: //Año de compra
                int nuevoAño = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Año());
                producto.asignarAñoCompra(nuevoAño);
                break;
            case 7: //Mes de compra
                int nuevoMes = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Mes());
                producto.asignarMesCompra(nuevoMes);
                break;
            case 8: //Dia de compra
                int nuevoDia = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Dia());
                producto.asignarDiaCompra(nuevoDia);
                break;
            case 9: //Tiempo de garantia
                int nuevoTiempoGarantia = (int) formatearEntradaDecimal(UIMensajes.mC_AcP_TiempoGarantia());
                producto.asignarTiempoGarantia(nuevoTiempoGarantia);
                break;
            case 10: //Añadir una caracteristica. "Indique a continuacion el titulo de la caracteristica y su descripcion"
                formatearCadena(UIMensajes.mC_Acp_CaracteristicaTituloDescripcion(), false, true);
                String tituloCaracteristica = formatearEntradaCadena(UIMensajes.mC_AcP_Titulo(), true);
                String descripcionCaracteristica = formatearEntradaCadena(UIMensajes.mC_AñP_Descripcion(), true);
                producto.añadirCaracteristica(new Caracteristica(tituloCaracteristica, descripcionCaracteristica));
                break;
        }
        //"Se ha actualizado el producto con exito"
        System.out.println(UIMensajes.mC_AcP_Exito()); 
    }
    
    /**
     * Metodo auxiliar de menuModificacionOpciones(...)
     * Añade las opciones al menu
     */
    private UIMenu añadirOpcionesMenuActualizar(){
        UIMenu menuModOpciones = new UIMenu();
        
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Precio());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Cantidad());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Descripcion());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Peso());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AcP_Financiado());
        menuModOpciones.añadirOpcion(UIMensajes.mC_LP_Estado());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Año());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Mes());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AñP_Dia());
        menuModOpciones.añadirOpcion(UIMensajes.mC_AcP_TiempoGarantia());
        
        //"Añadir caracteristica al producto"
        menuModOpciones.añadirOpcion(UIMensajes.mC_AcP_AñadirCaracteristica());
        
        return menuModOpciones;
    }
    
    /**
     * Metodo auxiliar de actualizarProducto(...) 
     * 
     * Imprime las caracteristicas del producto pasado como argumento
     * 
     * @param producto Producto con las caracteristicas a imprimir
     */
    private void imprimirCaracteristicasProducto(Producto producto){
        //Numero de producto
        formatearCadena(UIMensajes.mC_LP_NumeroProducto(), 
            true, true);
        System.out.print(producto.obtenerNumeroProducto());
        
        //Precio
        formatearCadena(UIMensajes.mC_AñP_Precio(), true, true);
        System.out.print(producto.obtenerPrecio());
        
        //Cantidad
        formatearCadena(UIMensajes.mC_AñP_Cantidad(), true, true);
        System.out.print(producto.obtenerCantidad());
        
        //Descripcion
        formatearCadena(UIMensajes.mC_AñP_Descripcion(), true, true);
        System.out.print(producto.obtenerDescripcion());
        
        //Peso
        formatearCadena(UIMensajes.mC_AñP_Peso(), true, true);
        System.out.print(producto.obtenerPeso());
        
        //Devuelve el estado de la financiacion del producto
        formatearCadena(UIMensajes.mC_AcP_Financiado(), 
            true, true);
        System.out.print(producto.obtenerEstadoFinanciado());
        
        //Estado
        formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
        System.out.print(producto.obtenerEstadoProducto());
        
        //Fecha de compra
        formatearCadena(UIMensajes.mC_AcP_FechaCompra(), 
            true, true);
        System.out.print(producto.obtenerDiaCompra() + "/");
        System.out.print(producto.obtenerMesCompra() + "/");
        System.out.print(producto.obtenerAñoCompra());
        System.out.print(" " + UIMensajes.mC_AñP_Dia());
        System.out.print("/" + UIMensajes.mC_AñP_Mes());
        System.out.print("/" + UIMensajes.mC_AñP_Año());
        
        //Tiempo de garantia
        formatearCadena(UIMensajes.mC_AcP_TiempoGarantia(), 
            true, true);
        System.out.print(producto.obtenerTiempoGarantia());
        
        //Caracteristicas extra
        for(int i = 0; i < producto.obtenerNumCaracteristicas(); i++){
            Caracteristica temp = producto.obtenerCaracteristica(i);
            formatearCadena(temp.obtenerTitulo(), true, true);
            System.out.print(temp.obtenerDescripcion());
        }
    }
    
    /**
     * Imprime los datos de un producto
     * 
     * @param productos Base de datos de productos del programa
     */
    public void imprimirDatosProducto(Productos productos, Usuarios usuarios){
        
        //"Numero de producto"
        formatearCadena(UIMensajes.mC_OpcionVerDatosProducto(), false, true);
        formatearCadena(UIMensajes.mC_LP_NumeroProducto(),
            true, true);
            
        //Buscamos coincidencias en la tienda
        //Pregunta por el numero de producto hasta obtener un numero valido
        int nProducto = UIEntradas.obtenerEntero(0, productos.obtenerTamaño());
        Producto bProducto = productos.obtenerProducto(nProducto, true);
        if(bProducto!=null){ //Si encuentra el producto
            imprimirCaracteristicasProducto(bProducto);
        }else{ //Si NO encuentra el producto
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
        }
        
        //Imprimimos los productos comprados por los clientes
        imprimirProductosCompradosCliente(usuarios, nProducto);
    }
    
    /**
     * Metodo auxiliar de imprimirDatosProducto(...)
     */
    private void imprimirProductosCompradosCliente(Usuarios usuarios, int numProducto){
        
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){ //Para cada cliente de la tienda
            Usuario tempUsuario = usuarios.obtenerUsuario(i);
            
            if(tempUsuario instanceof Cliente){ //Obtenemos un cliente
                Cliente cliente = (Cliente) tempUsuario;
                
                //Obtenemos la lista de productos que el cliente ha comprado
                ArrayList<Producto> productosComprados = Util.listaProductosComprados(cliente);
                Iterator<Producto> itr = productosComprados.iterator();
                while(itr.hasNext()){ //Por cada producto comprado
                    Producto tempProducto = itr.next();
                    //Si el numero de producto de tempProducto coincide con el del parametro
                    if(tempProducto.obtenerNumeroProducto()==numProducto){
                        System.out.println();
                        System.out.print("\t" + cliente.obtenerNombreUsuario());
                        System.out.print("(" + cliente.obtenerDNI() + ") ");
                        System.out.print("|" + UIMensajes.mC_LP_NumeroProducto()
                            + ": " + tempProducto.obtenerNumeroProducto() + " ");
                        System.out.print("|" + UIMensajes.mC_AñP_Cantidad()
                            + ": " + tempProducto.obtenerCantidad() + " ");
                        System.out.print("|" + UIMensajes.mC_AñP_Precio() 
                            + ": " + tempProducto.obtenerPrecio() + " ");
                        System.out.print("|" + UIMensajes.mC_AñP_Peso()
                            + ": " + tempProducto.obtenerPeso() + " ");
                        System.out.print("|" + UIMensajes.mC_LP_Estado()
                            + ": " + tempProducto.obtenerEstadoProducto() + " ");
                        System.out.println();
                        System.out.println("|" + UIMensajes.mC_AñP_Descripcion()
                            + ": " + tempProducto.obtenerDescripcion() + " ");
                    }
                }
                
            }
        }
    }
    
    /**
     * Metodo auxiliar de imprimirListaProductos(...)
     */
    private void imprimirCaracteristicasProductoComprado(Usuarios usuarios){
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario tempUsuario = usuarios.obtenerUsuario(i);
            if(tempUsuario instanceof Cliente){ //Obtenemos un cliente
                Cliente cliente = (Cliente) tempUsuario;
                
                //Obtenemos la lista de productos que el cliente ha comprado
                ArrayList<Producto> productosComprados = Util.listaProductosComprados(cliente);
                Iterator<Producto> itr = productosComprados.iterator();
                while(itr.hasNext()){ //Por cada producto comprado
                    Producto tempProducto = itr.next();
                    System.out.print("\t" + UIMensajes.g_Nombre()
                        + ": " + tempUsuario.obtenerNombreUsuario() + " ");
                    System.out.print("|" + UIMensajes.mC_LP_NumeroProducto()
                        + ": " + tempProducto.obtenerNumeroProducto() + " ");
                    System.out.print("|" + UIMensajes.mC_AñP_Cantidad()
                        + ": " + tempProducto.obtenerCantidad() + " ");
                    System.out.print("|" + UIMensajes.mC_AñP_Precio() 
                        + ": " + tempProducto.obtenerPrecio() + " ");
                    System.out.print("|" + UIMensajes.mC_AñP_Peso()
                        + ": " + tempProducto.obtenerPeso() + " ");
                    System.out.print("|" + UIMensajes.mC_LP_Estado()
                        + ": " + tempProducto.obtenerEstadoProducto() + " ");
                    System.out.println();
                    System.out.println("\t" + UIMensajes.mC_AñP_Descripcion()
                        + ": " + tempProducto.obtenerDescripcion() + " ");
                    System.out.println();
                    
                }
                
            }
        }
    }
    
    /**
     * Obtiene la lista de productos de la tienda (base de datos de
     * productos) e imprime una lista.
     * 
     * Numero de producto, Cantidad, Precio, Peso, Estado, Descripcion
     * 
     */
    public void imprimirListaProductos(Productos productos, Usuarios usuarios){
        System.out.println(UIMensajes.g_EncabezadoMenus());
        System.out.println(UIMensajes.mC_OpcionListaProductos());
        System.out.println();
        for(int i = 0; i < productos.obtenerTamaño(); i++){
            //Obtiene el producto mediante su id dentro del arraylist
            //si el segundo argumento fuese true entonces usaria el
            //numero de producto en vez de la id en arraylist
            Producto temp = productos.obtenerProducto(i, false);
            System.out.print("\t" + UIMensajes.mC_LP_NumeroProducto()
                + ": " + temp.obtenerNumeroProducto() + " ");
            System.out.print("|" + UIMensajes.mC_AñP_Cantidad()
                + ": " + temp.obtenerCantidad() + " ");
            System.out.print("|" + UIMensajes.mC_AñP_Precio() 
                + ": " + temp.obtenerPrecio() + " ");
            System.out.print("|" + UIMensajes.mC_AñP_Peso()
                + ": " + temp.obtenerPeso() + " ");
            System.out.print("|" + UIMensajes.mC_LP_Estado()
                + ": " + temp.obtenerEstadoProducto() + " ");
            System.out.println();
            System.out.println("\t" + UIMensajes.mC_AñP_Descripcion()
                + ": " + temp.obtenerDescripcion() + " ");
            System.out.println();
        }
        
        //Ahora imprimimos los productos comprados por los clientes
        imprimirCaracteristicasProductoComprado(usuarios);
    }
    
}
