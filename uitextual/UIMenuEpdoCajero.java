package uitextual;

import backend.Usuarios;
import backend.Util;

import productos.Productos;
import productos.Producto;


/**
 * Clase encargada de implementar las opciones que los empleados cajeros
 * de la tienda pueden llevar a cabo con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoCajero extends UIMenuAccionable{
    
    //Cajero usando el programa
    private UIEpdoCajero cajero;
    
    //Metodo constructor
    public UIMenuEpdoCajero(Usuarios usuarios, Productos productos,
        UIEpdoCajero usuario){
        super(usuarios, productos, usuario);
        this.cajero = usuario;
        
        //Añade las opciones al menu y las imprime por primera vez
        añadirOpciones();
        
        //Activa la interaccion usuario-programa
        activarInteraccion();
    }
    
    /**
     * Devuelve el cajero usando el programa
     */
    private UIEpdoCajero obtenerCajero(){
        return cajero;
    }
    
    /**
     * Vuelve a imprimir el menu y activa de nuevo las entradas para elegir
     * una opcion
     */
    private void volverMenu(){
        obtenerMenu().imprimirOpciones();
        activarInteraccion();
    }
    
    /**
     * Añade las opciones con las que el usuario del programa interactuara
     * 
     * (0) Añadir un producto
     * (1) Lista de productos
     * (2) Salir del programa
     * 
     */
    private void añadirOpciones(){
        obtenerMenu().añadirOpcion(UIMensajes.menuCajeroOpcionCrearProducto());
        obtenerMenu().añadirOpcion(UIMensajes.menuCajeroOpcionActualizarProducto());
        obtenerMenu().añadirOpcion(UIMensajes.menuCajeroOpcionVerDatosProducto());
        obtenerMenu().añadirOpcion(UIMensajes.menuCajeroOpcionListaProductos());
        obtenerMenu().añadirOpcion(UIMensajes.menuPrincipalOpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    private void activarInteraccion(){
        int entrada = obtenerMenu().obtenerOpcion();
        switch(entrada){ //Si la opcion es salir (asignada a ultima pos)
            case 0:
                //Añadir producto a la base de datos
                //Llamamos al metodo en la clase UI para EpdoCajeros
                Producto producto = obtenerCajero().crearProducto();
                obtenerProductos().añadirProducto(producto);
                
                //Vuelve a imprimir el menu
                volverMenu();
                break;
                
            case 1: //Actualizar los datos de un producto
                
                //Obtenemos la cadena "Numero de producto"
                String aux = UIMensajes.menuCajeroOpcionListaProductosNumeroProducto();
                
                //Obtenemos el numero de producto del producto a modificar
                int numeroProducto = (int) Util.UIactualizarNumeroProducto(aux, 0, 999999);
                
                //Invocamos el metodo para modificarlo
                obtenerCajero().actualizarProducto(obtenerProductos(), numeroProducto);
                
                //Vuelve a imprimir el menu
                volverMenu();
                break;
            case 2: //Ver las caracteristicas de un producto
                obtenerCajero().imprimirDatosProducto(obtenerProductos());
                
                volverMenu();
                break;
            case 3:
                //Lista de productos
                opcionListaProductos();
                
                //Vuelve a imprimir el menu
                volverMenu();
                break;
                
            case 4:
            //Cierra del programa
            System.exit(0);
            break;
        }
    }
    
    /**
     * Obtiene la lista de productos de la tienda (base de datos de
     * productos) e imprime una lista.
     */
    private void opcionListaProductos(){
        System.out.println(UIMensajes.menuEncabezado());
        System.out.println(UIMensajes.menuCajeroOpcionListaProductos());
        System.out.println();
        for(int i = 0; i < obtenerProductos().obtenerTamaño(); i++){
            //Obtiene el producto mediante su id dentro del arraylist
            //si el segundo argumento fuese true entonces usaria el
            //numero de producto en vez de la id en arraylist
            Producto temp = obtenerProductos().obtenerProducto(i, false);
            System.out.print("\t" + UIMensajes.menuCajeroOpcionListaProductosNumeroProducto()
                + ": " + temp.obtenerNumeroProducto() + " ");
            System.out.print("|" + UIMensajes.opcionCrearProductoPrecio() 
                + ": " + temp.obtenerPrecio() + " ");
            System.out.print("|" + UIMensajes.opcionCrearProductoPrecio()
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

