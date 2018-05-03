package uitextual;

import backend.Usuario;
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
     * El cajero ingresa en la base de datos de productos un producto que
     * la tienda acaba de recibir.
     * 
     * Interrogatorio textual sobre las caracteristicas del producto.
     *
     * @param productos Base de datos de productos de la tienda
     */
    public Producto añadirProducto(){
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
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de añadirProducto()
     * 
     * Devuelve un producto segun su categoria
     */
    private Producto obtenerCategoria(){
        //Categoria del producto (Hogar|Telefonia|Imagen|Informatica)
        boolean valido = false;
        String categoria;
        
        //Obtenemos la lista con las categorias de productos
        ArrayList<String> listaCategorias = Util.listaCategoriasProductos();
        
        //"Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) "
        System.out.println(UIMensajes.mC_AñP_ElegirCategoriaProducto());
        do{
            categoria = UIEntradas.obtenerCadena(false).toLowerCase().trim();
            
            //Comprobamos si al categoria especificada existe
            Iterator<String> itr = listaCategorias.iterator();
            while(itr.hasNext()){
                String temp = itr.next().toLowerCase();
                if(temp.equals(categoria)){
                    valido = true; //En caso de que encuentre la categoria especificada
                }
            }
            if(!valido){
                //"Categoria inexistente, por favor vuelva a intentarlo "
                System.out.print(UIMensajes.mC_AñP_CategoriaInexistente());
            }
        }while(!valido);
        //""Categoria seleccionada correctamente"
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
    public void actualizarProducto(Productos productos, int numProducto){
        //Obtenemos el producto con el numero de producto
        Producto producto = productos.obtenerProducto(numProducto, true);
        if(producto!=null){
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()+
                numProducto);
            
            imprimirCaracteristicasProducto(producto);
            
            //Creamos un menu para elegir que opcion modificar
            menuModificacionOpciones(producto);
        }else{ //En caso de que no encuentre el producto
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()
                + numProducto);
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
    public void imprimirDatosProducto(Productos productos){
        //"Numero de producto"
        formatearCadena(UIMensajes.mC_OpcionVerDatosProducto(), false, true);
        formatearCadena(UIMensajes.mC_LP_NumeroProducto(),
            true, true);
        //Pregunta por el numero de producto hasta obtener un numero valido
        int nProducto = UIEntradas.obtenerEntero(0, productos.obtenerTamaño());
        Producto producto = productos.obtenerProducto(nProducto, true);
        if(producto!=null){ //Si encuentra el producto
            imprimirCaracteristicasProducto(producto);
        }else{ //Si NO encuentra el producto
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
        }
    }
    
    /**
     * Obtiene la lista de productos de la tienda (base de datos de
     * productos) e imprime una lista.
     * 
     * Numero de producto, Cantidad, Precio, Peso, Estado, Descripcion
     * 
     */
    public void imprimirListaProductos(Productos productos){
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
            System.out.println("|" + 
                UIMensajes.mC_AñP_Descripcion()
                + ": " + temp.obtenerDescripcion() + " ");
            System.out.println();
        }
    }
    
}
