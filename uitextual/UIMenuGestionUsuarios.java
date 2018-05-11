package uitextual;

import productos.Productos;

import backend.Usuarios;

import uitextual.UIEpdoFinanciacion;

/**
 * En este menu se realizaran todas las operaciones que potencialmente
 * los dueños necesitaran llevar a cabo para el funcionamiento del
 * programa.
 * 
 * Para acceder al menu se emplea una contraseña pues el objetivo del
 * mismo es que al empezar a usar el programa sea posible asignar
 * los empleados de la tienda.
 * 
 * Entre las operaciones implementadas estan:
 * 
 *  - Añadir empleado al sistema
 *  - Actualizar nombre,email, usuario o contraseña de un empleado
 *  - Ver lista de usuarios
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuGestionUsuarios extends UIMenuAccionable{
    
    private UIGestionUsuarios temp;
    
    //Metodo constructor
    public UIMenuGestionUsuarios(Usuarios usuarios, Productos productos,
        UIGestionUsuarios usuario){
        super(usuarios, productos, usuario);
        this.temp = usuario;
        
        //Añade las opciones al menu y las imprime por primera vez
        añadirOpciones();
        
        //Activa la interaccion usuario-programa
        activarInteraccion();
    }
    
    /**
     * Devuelve el usuario que contiene la contraseña para 
     * acceder a gestion de usuarios.
     */
    private UIGestionUsuarios obtenerTemp(){
        return this.temp;
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
     *  (0) Añadir empleado al sistema
     *  (1) Actualizar los datos de un empleado
     *  (2) Ver lista de usuarios
     *  (3) Salir del programa
     * 
     */
    private void añadirOpciones(){
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionAñadirEmpleado());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionActualizarDatos());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionVerListaEmpleados());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionVerFichasReparacion());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionAñadirSolicitud());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionAceptarSolicitud());
        obtenerMenu().añadirOpcion(UIMensajes.mGU_OpcionVerSolicitudes());
        obtenerMenu().añadirOpcion(UIMensajes.g_CerrarSesion());
        obtenerMenu().añadirOpcion(UIMensajes.g_OpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    private void activarInteraccion(){
        int entrada = UIEntradas.obtenerEntero(0, obtenerMenu().obtenerNumeroOpciones());
        switch(entrada){
            case 0: //Añadir empleado al sistema
            obtenerTemp().añadirEmpleado(obtenerUsuarios());
            
            volverMenu(); //Imprime el menu de nuevo y activa la interaccion
            break;
            
            case 1: //Actualizar los datos de un empleado
            obtenerTemp().actualizarDatosEmpleado(obtenerUsuarios());
            
            volverMenu(); //Imprime el menu de nuevo y activa la interaccion
            break;
            
            case 2: //Ver lista de empleados
            obtenerTemp().imprimirListaEmpleados(obtenerUsuarios());
            volverMenu(); //Imprime el menu de nuevo y activa la interaccion
            break;
            
            case 3: //Ver fichas de reparacion
            obtenerTemp().verFichasReparacion(obtenerUsuarios());
            volverMenu();
            break;
            
            case 4: //Cerrar sesion
            UIMenuPrincipal menuPrincipal = new UIMenuPrincipal(obtenerUsuarios(), obtenerProductos(),
            obtenerUsuario());
            break;
            
            case 5: //Añadir solicitud a un cliente
            obtenerTemp().añadirSolicitud(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 6: //Aceptar solicitud sobre un cliente
            obtenerTemp().aceptarSolicitud(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 7: //Ver lista de solicitudes de todos los clientes
            obtenerTemp().verListaSolicitudes(obtenerUsuarios());
            
            volverMenu();
            break;
            
            case 8: //Salir del programa
            System.exit(0);
            break;
        }
    }
    
}
