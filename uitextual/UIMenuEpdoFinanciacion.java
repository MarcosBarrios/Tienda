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
     * (2) Salir del programa
     * 
     */
    public void añadirOpciones(){
        obtenerMenu().añadirOpcion(UIMensajes.menuFinanciacionOpcionDarAlta());
        obtenerMenu().añadirOpcion(UIMensajes.menuFinanciacionOpcionActualizarDatos());
        obtenerMenu().añadirOpcion(UIMensajes.menuFinanciacionOpcionDatosCliente());
        obtenerMenu().añadirOpcion(UIMensajes.menuPrincipalOpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    public void activarInteraccion(){
        int entrada = UIEntradas.obtenerEntero(0, obtenerMenu().obtenerNumeroOpciones());
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
            case 3: //Salir del programa
                System.exit(0);
                break;
        }

    }
}
