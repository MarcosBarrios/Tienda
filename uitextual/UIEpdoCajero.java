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
    public Producto crearProducto(){
        System.out.println(UIMensajes.opcionCrearProductoInicio());
        
        //Categoria del producto (Hogar|Telefonia|Imagen|Informatica)
        boolean valido = false;
        String categoria;
        
        //Obtenemos la lista con las categorias de productos
        ArrayList<String> listaCategorias = Util.listaCategoriasProductos();
        
        System.out.println(UIMensajes.opcionCrearProductoCategoria());
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
                System.out.print(UIMensajes.opcionCrearProductoCategoriaIncorrecta());
            }
        }while(!valido);
        System.out.println(UIMensajes.opcionCrearProductoCategoriaCorrecta());
        
        //Creamos la clase del producto segun la categoria que sea
        Producto producto = null;
        if(categoria.equals(UIMensajes.opcionCrearProductoCategoriaHogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.opcionCrearProductoCategoriaImagen().toLowerCase())){
            producto = new ProductoImagen();
        }
        else if(categoria.equals(UIMensajes.opcionCrearProductoCategoriaSonido().toLowerCase())){
            producto = new ProductoSonido();
        }
        else if(categoria.equals(UIMensajes.opcionCrearProductoCategoriaTelefonia().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.opcionCrearProductoCategoriaInformatica().toLowerCase())){
            producto = new ProductoInformatica();
        }
        
        //Descripcion
        System.out.print(UIMensajes.opcionCrearProductoDescripcion() + ": ");
        String descripcion = UIEntradas.obtenerCadena(true);
        producto.asignarDescripcion(descripcion);
        
        //Precio
        System.out.print(UIMensajes.opcionCrearProductoPrecio() + ": ");
        float precio = UIEntradas.obtenerDecimal(0, Util.MAXIMOPRECIO);
        producto.asignarPrecio(precio);
        
        //Cantidad
        System.out.print(UIMensajes.opcionCrearProductoCantidad() + ": ");
        int cantidad = UIEntradas.obtenerEntero(0, Util.MAXIMACANTIDAD);
        producto.asignarCantidad(cantidad);
        
        //Peso
        System.out.print(UIMensajes.opcionCrearProductoPeso() + ": ");
        float peso = UIEntradas.obtenerDecimal(0, Util.MAXIMOPESO);
        producto.asignarPeso(peso);
        
        //Fecha de compra (dia/mes/año)
        System.out.println(UIMensajes.opcionCrearProductoFechaCompra() + ": ");
        
        //Dia
        System.out.print(UIMensajes.opcionCrearProductoDiaCompra() + ": ");
        int diaCompra = UIEntradas.obtenerEntero(0, Util.MAXIMODIA);
        producto.asignarDiaCompra(diaCompra);
        
        //Mes
        System.out.print(UIMensajes.opcionCrearProductoMesCompra() + ": ");
        int mesCompra = UIEntradas.obtenerEntero(0, Util.MAXIMOMES);
        producto.asignarMesCompra(mesCompra);
        
        //Año
        System.out.print(UIMensajes.opcionCrearProductoAñoCompra() + ": ");
        int añoCompra = UIEntradas.obtenerEntero(0, Util.MAXIMOAÑO);
        producto.asignarAñoCompra(añoCompra);
        
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
            System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoEncontrado()+
                numProducto); //"Producto encontrado. Numero de producto= "
            
            imprimirCaracteristicasProducto(producto);
            
            //Creamos un menu para elegir que opcion modificar
            menuModificacionOpciones(producto);
        }else{ //En caso de que no encuentre el producto
            System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoNoEncontrado()
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
        System.out.println();
        System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoElegirOpcion());
        
        //Iniciamos el menu y añadimos las opciones
        UIMenu menuModOpciones = new UIMenu();
        
        //Numero producto (NO ACTUALIZABLE)
        //menuModOpciones.añadirOpcion(UIMensajes.menuCajeroOpcionListaProductosNumeroProducto());
        
        //Precio
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoPrecio());
        
        //Cantidad
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoCantidad());
        
        //Descripcion
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoDescripcion());
        
        //Peso
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoPeso());
        
        //Devuelve el estado de la financiacion del producto
        menuModOpciones.añadirOpcion(UIMensajes.menuCajeroOpcionActualizarProductoFinanciado());
        
        //Estado
        menuModOpciones.añadirOpcion(UIMensajes.menuCajeroOpcionListaProductosEstado());
        
        //Año de compra
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoAñoCompra());
        
        //Mes de compra
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoMesCompra());
        
        //Dia de compra
        menuModOpciones.añadirOpcion(UIMensajes.opcionCrearProductoDiaCompra());
    
        //Tiempo de garantia
        menuModOpciones.añadirOpcion(UIMensajes.menuCajeroOpcionActualizarProductoTiempoGarantia());
        
        //Añadir una caracteristica
        menuModOpciones.añadirOpcion(UIMensajes.menuCajeroOpcionActualizarProductoNuevaCaracteristica());
    
        //Imprime el menu
        menuModOpciones.imprimirOpciones();
        
        //Implementacion de que hace cada opcion
        int entrada = UIEntradas.obtenerEntero(0, menuModOpciones.obtenerNumeroOpciones());
        switch(entrada){
            /*case 0: //Numero producto (NO ACTUALIZABLE)
                String numeroProducto = UIMensajes.menuCajeroOpcionListaProductosNumeroProducto();
                String nuevoNumeroProducto = actualizarStringProducto(numeroProducto, _lineaCompleta_)
                
                break;*/
            case 0: //Precio
                String precio = UIMensajes.opcionCrearProductoPrecio();
                float nuevoPrecio = Util.UIactualizarNumeroProducto(precio, 0, Util.MAXIMOPRECIO);
                producto.asignarPrecio(nuevoPrecio);
                break;
            case 1: //Cantidad
                String cantidad = UIMensajes.opcionCrearProductoCantidad();
                int nuevaCantidad = (int) Util.UIactualizarNumeroProducto(cantidad, 0, Util.MAXIMACANTIDAD);
                producto.asignarCantidad(nuevaCantidad);
                break;
            case 2: //Descripcion
                String descripcion = UIMensajes.opcionCrearProductoDescripcion();
                String nuevaDescripcion = Util.UIactualizarStringProducto(descripcion, true);
                producto.asignarDescripcion(nuevaDescripcion);
                break;
            case 3: //Peso
                String peso = UIMensajes.opcionCrearProductoPeso();
                float nuevoPeso = Util.UIactualizarNumeroProducto(peso, 0, Util.MAXIMOPESO);
                producto.asignarPrecio(nuevoPeso);
                break;
            case 4: //Devuelve el estado de la financiacion del producto
                String financiado = UIMensajes.menuCajeroOpcionActualizarProductoFinanciado();
                boolean financiacion = Util.UIactualizarEstadoProducto(financiado);
                break;
            case 5: //Estado del producto (INTACTO, ROTO, DEVUELTO (29/04))
                String estadoProducto = UIMensajes.menuCajeroOpcionListaProductosEstado();
                ArrayList<String> listaEstados = EnumEstadoProducto.obtenerEstados();
                System.out.println();
                System.out.print("\t" + estadoProducto);
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
                String año = UIMensajes.opcionCrearProductoDiaCompra();
                int nuevoAño = (int) Util.UIactualizarNumeroProducto(año, 2018, Util.MAXIMOAÑO);
                producto.asignarDiaCompra(nuevoAño);
                break;
            case 7: //Mes de compra
                String mes = UIMensajes.opcionCrearProductoDiaCompra();
                int nuevoMes = (int) Util.UIactualizarNumeroProducto(mes, 1, Util.MAXIMOMES);
                producto.asignarDiaCompra(nuevoMes);
                break;
            case 8: //Dia de compra
                String dia = UIMensajes.opcionCrearProductoDiaCompra();
                int nuevoDia = (int) Util.UIactualizarNumeroProducto(dia, 1, Util.MAXIMODIA);
                producto.asignarDiaCompra(nuevoDia);
                break;
            case 9: //Tiempo de garantia
                String tiempoGarantia = UIMensajes.menuCajeroOpcionActualizarProductoTiempoGarantia();
                int nuevoTiempoGarantia = (int) Util.UIactualizarNumeroProducto(tiempoGarantia, 0, 60);
                break;
            case 10: //Añadir una caracteristica
                System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoNuevaCaracteristica() 
                    + ": ");
                String titulo = UIMensajes.menuCajeroOpcionActualizarProductoTituloCaracteristica();
                String tituloCaracteristica = Util.UIactualizarStringProducto(titulo, true);
                String descrip = UIMensajes.menuCajeroOpcionActualizarProductoCaracteristica();
                String descripcionCaracteristica = Util.UIactualizarStringProducto(descrip, true);
                break;
        }
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
        formatearCadena(UIMensajes.menuCajeroOpcionListaProductosNumeroProducto(), 
            true, true);
        System.out.print(producto.obtenerNumeroProducto());
        
        //Precio
        formatearCadena(UIMensajes.opcionCrearProductoPrecio(), true, true);
        System.out.print(producto.obtenerPrecio());
        
        //Cantidad
        formatearCadena(UIMensajes.opcionCrearProductoCantidad(), true, true);
        System.out.print(producto.obtenerCantidad());
        
        //Descripcion
        formatearCadena(UIMensajes.opcionCrearProductoDescripcion(), true, true);
        System.out.print(producto.obtenerDescripcion());
        
        //Peso
        formatearCadena(UIMensajes.opcionCrearProductoPrecio(), true, true);
        System.out.print(producto.obtenerPeso());
        
        //Devuelve el estado de la financiacion del producto
        formatearCadena(UIMensajes.menuCajeroOpcionActualizarProductoFinanciado(), 
            true, true);
        System.out.print(producto.obtenerEstadoFinanciado());
        
        //Estado
        formatearCadena(UIMensajes.menuCajeroOpcionListaProductosEstado(), true, true);
        System.out.print(producto.obtenerEstadoProducto());
        
        //Fecha de compra
        formatearCadena(UIMensajes.menuCajeroOpcionActualizarProductoFechaCompra(), 
            true, true);
        System.out.print(producto.obtenerDiaCompra() + "/");
        System.out.print(producto.obtenerMesCompra() + "/");
        System.out.print(producto.obtenerAñoCompra());
        System.out.print(" " + UIMensajes.opcionCrearProductoDiaCompra());
        System.out.print("/" + UIMensajes.opcionCrearProductoMesCompra());
        System.out.print("/" + UIMensajes.opcionCrearProductoAñoCompra());
        
        //Tiempo de garantia
        formatearCadena(UIMensajes.menuCajeroOpcionActualizarProductoTiempoGarantia(), 
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
        formatearCadena(UIMensajes.menuCajeroOpcionDatosProducto(), false, true);
        formatearCadena(UIMensajes.menuCajeroOpcionListaProductosNumeroProducto(),
            true, true);
        //Pregunta por el numero de producto hasta obtener un numero valido
        int nProducto = UIEntradas.obtenerEntero(0, productos.obtenerTamaño());
        Producto producto = productos.obtenerProducto(nProducto, true);
        if(producto!=null){ //Si encuentra el producto
            imprimirCaracteristicasProducto(producto);
        }else{ //Si NO encuentra el producto
            System.out.println(UIMensajes.menuCajeroOpcionActualizarProductoNoEncontrado());
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
        System.out.println(UIMensajes.menuEncabezado());
        System.out.println(UIMensajes.menuCajeroOpcionListaProductos());
        System.out.println();
        for(int i = 0; i < productos.obtenerTamaño(); i++){
            //Obtiene el producto mediante su id dentro del arraylist
            //si el segundo argumento fuese true entonces usaria el
            //numero de producto en vez de la id en arraylist
            Producto temp = productos.obtenerProducto(i, false);
            System.out.print("\t" + UIMensajes.menuCajeroOpcionListaProductosNumeroProducto()
                + ": " + temp.obtenerNumeroProducto() + " ");
            System.out.print("|" + UIMensajes.opcionCrearProductoCantidad()
                + ": " + temp.obtenerCantidad() + " ");
            System.out.print("|" + UIMensajes.opcionCrearProductoPrecio() 
                + ": " + temp.obtenerPrecio() + " ");
            System.out.print("|" + UIMensajes.opcionCrearProductoPeso()
                + ": " + temp.obtenerPeso() + " ");
            System.out.print("|" + UIMensajes.menuCajeroOpcionListaProductosEstado()
                + ": " + temp.obtenerEstadoProducto() + " ");
            System.out.println("|" + 
                UIMensajes.menuCajeroOpcionListaProductosDescripcion()
                + ": " + temp.obtenerDescripcion() + " ");
            System.out.println();
        }
    }
    
}
