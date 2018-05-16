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
        //"Buscar productos"
        //"Buscar usuarios"
        //"Vender un producto a un cliente",
        //"Añadir un producto",
        //"Actualizar producto", 
        //"Ver las caracteristicas de un producto" ,
        //"Lista de productos"
        //"Cerrar sesion"
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarProductos());
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.mC_OpcionVenderProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mC_OpcionAñadirProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mC_OpcionActualizarProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mC_OpcionVerDatosProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mC_OpcionListaProductos());
        obtenerMenu().añadirOpcion(UIMensajes.g_CerrarSesion());
        obtenerMenu().añadirOpcion(UIMensajes.g_OpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    private void activarInteraccion(){
        int entrada = obtenerMenu().obtenerOpcion();
        switch(entrada){ 
            case 0: //"Buscar productos"
            obtenerCajero().imprimirBusquedaProductos(obtenerUsuarios(), obtenerProductos());
            volverMenu();
            break;
            
            case 1: //Buscar usuarios
            obtenerCajero().imprimirBusquedaUsuarios(obtenerUsuarios());
            volverMenu();
            break;
            
            case 2: //"Vender un producto a un cliente
            obtenerCajero().venderProducto(obtenerUsuarios(), obtenerProductos());
            
            //Volver a imprimir el menu
            volverMenu();
            break;
            
            case 3: //Añadir producto a la base de datos
            //Llamamos al metodo en la clase UI para EpdoCajeros
            obtenerCajero().añadirProducto(obtenerProductos());
            
            //Vuelve a imprimir el menu
            volverMenu();
            break;
                
            case 4: //Actualizar los datos de un producto
            
            //Invocamos el metodo para modificarlo
            obtenerCajero().actualizarProducto(obtenerProductos(), obtenerUsuarios());
            
            //Volver a imprimir el menu
            volverMenu();
            break;
                
            case 5: //Ver las caracteristicas de un producto
            obtenerCajero().imprimirDatosProducto(obtenerProductos(), obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 6: //Lista de productos
            
            //Imprimir lista de productos
            obtenerCajero().imprimirListaProductos(obtenerProductos(), obtenerUsuarios());
            
            //Volver a imprimir el menu
            volverMenu();
            break;
            
            case 7: //Cerrar sesión
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 8: //Salir del programa
            System.exit(0);
            break;
        }
    }
    
}

