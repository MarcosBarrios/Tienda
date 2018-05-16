package uitextual;

import backend.Usuarios;

import productos.Productos;

/**
 * Implementa el menu de UIEpdoTecnico
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoTecnico extends UIMenuAccionable{
    
    private UIEpdoTecnico tecnico;
    
    public UIMenuEpdoTecnico(Usuarios usuarios, Productos productos,
        UIEpdoTecnico usuario){
        super(usuarios, productos, usuario);
        tecnico = usuario;
                
        //Añade las opciones al menu y las imprime por primera vez
        añadirOpciones();
        
        //Activa la interaccion usuario-programa
        activarInteraccion();
    }
    
    /**
     * Devuelve el empleado de PostVenta para el menu
     */
    private UIEpdoTecnico obtenerTecnico(){
        return tecnico;
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
        //"Añadir reporte a un producto", "Ver mis fichas de reparacion",
        //"Ver el estado de un producto",
        //"Añadir una pieza a la lista de piezas necesarias", 
        //"Eliminar una pieza de la lista de piezas necesarias",
        //"Ver piezas necesarias", "Cerrar sesion", "Salir del programa"
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarProductos());
        obtenerMenu().añadirOpcion(UIMensajes.b_OpcionBuscarUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionAñadirReporte());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionVerFichas());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionVerEstadoProducto());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionAñadirPieza());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionEliminarPieza());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionVerPiezasNecesarias());
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
            obtenerTecnico().imprimirBusquedaProductos(obtenerUsuarios(), obtenerProductos());
            volverMenu();
            break;
            
            case 1: //Buscar usuarios
            obtenerTecnico().imprimirBusquedaUsuarios(obtenerUsuarios());
            volverMenu();
            break;
            
            case 2: //Añadir reporte a un producto
            obtenerTecnico().añadirReporte(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 3: //Ver mis fichas de reparacion
            obtenerTecnico().verListaFichasReparacion(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 4: //Ver el estado de un producto
            obtenerTecnico().verEstadoProducto(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 5: //Añadir una pieza a la lista de piezas necesarias
            obtenerTecnico().añadirPiezaNecesaria();
            
            volverMenu();
            break;
            
            case 6: //Eliminar una pieza de la lista de piezas necesarias
            obtenerTecnico().eliminarPiezaNecesaria();
            
            volverMenu();
            break;
            
            case 7: //Ver piezas necesarias
            obtenerTecnico().verPiezasNecesarias();
            
            volverMenu();
            break;
            
            case 8: //Cerrar Sesion
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 9: //Salir del programa
            System.exit(0);
            break;
            
        }
    }
    
}
