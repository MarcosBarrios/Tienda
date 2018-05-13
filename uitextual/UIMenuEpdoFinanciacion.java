package uitextual;

import backend.Usuarios;

import productos.Productos;

/**
 * Clase encargada de implementar las opciones que los empleados de
 * financiacion de la tienda pueden llevar a cabo con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuEpdoFinanciacion extends UIMenuAccionable{
    
    private UIEpdoFinanciacion financiador;

    //Metodo constructor
    public UIMenuEpdoFinanciacion(Usuarios usuarios, Productos productos,
        UIEpdoFinanciacion usuario){
        super(usuarios, productos, usuario);
        this.financiador = usuario;
        
        //Añade las opciones al menu y las imprime por primera vez
        añadirOpciones();
        
        //Activa la interaccion usuario-programa
        activarInteraccion();
    }
    
    /**
     * Devuelve el financiador asociado al menu
     */
    private UIEpdoFinanciacion obtenerFinanciador(){
        return financiador;
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
     * Estructura:
     * 
     * (0) Dar de alta a un cliente
     * (1) Actualizar datos de un cliente
     * (2) Ver los datos de un cliente
     * (3) Salir del programa
     * 
     */
    private void añadirOpciones(){
        obtenerMenu().añadirOpcion(UIMensajes.mF_OpcionDarAlta());
        obtenerMenu().añadirOpcion(UIMensajes.mF_OpcionActualizarDatos());
        obtenerMenu().añadirOpcion(UIMensajes.mF_OpcionVerDatosCliente());
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
            case 0: //Dar de alta a un cliente
            obtenerFinanciador().darAlta(obtenerUsuarios());
            //Vuelve a imprimir el menu
            volverMenu();
            break;
            
            case 1: //Actualizar datos de un cliente
            obtenerFinanciador().actualizarDatosCliente(obtenerUsuarios());
            //Vuelve a imprimir el menu
            volverMenu();
            break;
            
            case 2: //Ver los datos de un cliente
            obtenerFinanciador().imprimirDatosCliente(obtenerUsuarios());
            
            //Vuelve a imprimir el menu
            volverMenu();
            break;
            
            case 3: //Cerrar sesion
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 4: //Salir del programa
            System.exit(0);
            break;
        }

    }
}
