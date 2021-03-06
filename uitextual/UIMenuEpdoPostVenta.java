package uitextual;

import backend.Usuarios;

import productos.Productos;

/**
 * Menu para los empleados de post venta.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoPostVenta extends UIMenuAccionable{
    
    private UIEpdoPostVenta postVenta;

    //Metodo constructor
    public UIMenuEpdoPostVenta(Usuarios usuarios, Productos productos,
        UIEpdoPostVenta usuario){
        super(usuarios, productos, usuario);
        postVenta = usuario;
        
        //Añade las opciones al menu y las imprime por primera vez
        añadirOpciones();
        
        //Activa la interaccion usuario-programa
        activarInteraccion();
    }
    
    /**
     * Devuelve el empleado de PostVenta para el menu
     */
    private UIEpdoPostVenta obtenerPostVenta(){
        return postVenta;
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
     * (0) Devolver un producto
     * (2) Salir del programa
     * 
     */
    private void añadirOpciones(){
        //"Reparar un producto", "Devolver un producto",
        // "Comprobar el estado de un producto", "Salir del programa"
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarProductos());
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.mPV_OpcionRepararProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mPV_OpcionDevolverProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mPV_OpcionComprobarEstadoProducto());
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
            obtenerPostVenta().imprimirBusquedaProductos(obtenerUsuarios(), obtenerProductos());
            volverMenu();
            break;
            
            case 1: //Buscar usuarios
            obtenerPostVenta().imprimirBusquedaUsuarios(obtenerUsuarios());
            volverMenu();
            break;
            
            case 2: //Reparar un producto
            obtenerPostVenta().repararProducto(obtenerProductos(), obtenerUsuarios());
            
            //Vuelve al menu
            volverMenu();
            break;
            
            case 3: //Devolver un producto
            obtenerPostVenta().devolverProducto(obtenerProductos(), obtenerUsuarios());
            
            //Vuelve al menu
            volverMenu();
            break;
            
            case 4: //"Comprobar el estado de un producto"
            obtenerPostVenta().comprobarEstadoProducto(obtenerUsuarios());
            volverMenu();
            break;
            
            case 5: //Cerrar Sesion
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 6: //Salir del programa
            System.exit(0);
            break;
            
        }
    }
}
