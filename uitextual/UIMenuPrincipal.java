package uitextual;

import backend.Usuarios;
import backend.Util;
import backend.Empleado;
import backend.EpdoCajero;
import backend.EpdoPostVenta;
import backend.EpdoFinanciacion;

import productos.Productos;

/**
 * Clase encargada de imprimir y obtener entradas para el menu que se abre
 * automaticamente tras iniciar el programa para permitir la interaccion
 * usuario-programa
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenuPrincipal extends UIMenuAccionable{

    
    //Metodo constructor
    public UIMenuPrincipal(Usuarios usuarios, Productos productos, 
    UIUsuario usuario){
        super(usuarios, productos, usuario);
        añadirOpciones();
        activarInteraccion();
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
     * (0) Iniciar sesion
     * (1) Lista de usuarios
     * (2) Salir
     * 
     */
    private void añadirOpciones(){
        obtenerMenu().añadirOpcion(UIMensajes.menuPrincipalOpcionIniciarSesion());
        obtenerMenu().añadirOpcion(UIMensajes.menuPrincipalOpcionListaUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.menuPrincipalOpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    private void activarInteraccion(){
        int entrada = obtenerMenu().obtenerOpcion();
        switch(entrada){
            case 0:
                //Iniciar sesion
                login(); //Vuelve al menu si falla el inicio de sesion
                break;
                
            case 1:
                //Lista de usuarios
                System.out.print(" " +
                    UIMensajes.menuPrincipalOpcionListaUsuarios() + ": ");
                System.out.println();
                obtenerUsuario().imprimirListaUsuarios(obtenerUsuarios());
                
                //Vuelve al menu
                volverMenu();
                break;
                
            case 2:
                //Salir del programa
                System.exit(0);
                break;
        }
    }

    /**
     * Entra al programa como un empleado especifico usando los datos
     * de su cuenta (usuario y contraseña)
     * 
     * En caso de login fallido vuelve al menu
     */
    private void login(){
        System.out.println(UIMensajes.menuEncabezado());
        System.out.println(UIMensajes.loginInformacion());
        System.out.print("\t" + UIMensajes.loginUsuario() + ": ");
        //Obtenemos el usuario
        String nombreUsuario = UIEntradas.obtenerCadena(false);
        System.out.print("\t" + UIMensajes.loginContraseña() + ": ");
        //Obtenemos la contraseña
        String contraseña = UIEntradas.obtenerCadena(false);
        
        //Buscamos un posible empleado con los datos previamente especificados
        Empleado temp = (Empleado) Util.buscarCuentaEmpleado(obtenerUsuarios(),
                nombreUsuario, contraseña);
        
        if(temp != null){ //Si encuentra un empleado con los datos
            if(temp instanceof EpdoCajero){
                System.out.println(UIMensajes.menuPrincipalLoginExito() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoCajero menuCajero = new UIMenuEpdoCajero(obtenerUsuarios(),
                obtenerProductos(), new UIEpdoCajero((EpdoCajero) temp));
            }else if(temp instanceof EpdoPostVenta){
                //En caso de empleados de postventa
            }else if(temp instanceof EpdoFinanciacion){
                System.out.println(UIMensajes.menuPrincipalLoginExito() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoFinanciacion menuFinanciador = new UIMenuEpdoFinanciacion(
                    obtenerUsuarios(),obtenerProductos(), 
                    new UIEpdoFinanciacion((EpdoFinanciacion) temp));
            }
            //PENDIENTE añadir condiciones para cada tipo de empleado
        }else{ //Si por el contrario no existe ninguna cuenta con dichos datos
            //Avisa de fallo y vuelve al menu
            System.out.println(UIMensajes.menuPrincipalLoginFallido());
            volverMenu();
        }
    }
}
