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
        //"Añadir reporte a un producto"
        //"Ver mis fichas de reparacion", "Salir del programa"
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionAñadirReporte());
        obtenerMenu().añadirOpcion(UIMensajes.mT_OpcionVerFichas());
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
            case 0: //Añadir reporte a un producto
            obtenerTecnico().añadirReporte(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 1: //Ver mis fichas de reparacion
            obtenerTecnico().verListaFichasReparacion(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 2: //Cerrar Sesion
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 3: //Salir del programa
            System.exit(0);
            break;
            
        }
    }
    
}
