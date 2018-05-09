package uitextual;

import backend.Usuarios;
import backend.Util;
import backend.Empleado;
import backend.EpdoCajero;
import backend.EpdoPostVenta;
import backend.EpdoFinanciacion;
import backend.EpdoTecnico;
import backend.EpdoComercial;

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
        //"Iniciar Sesion", "Lista de usuarios", 
        //"Gestionar usuarios", "Salir del programa"
        obtenerMenu().añadirOpcion(UIMensajes.mP_OpcionIniciarSesion());
        obtenerMenu().añadirOpcion(UIMensajes.mP_OpcionGestionarUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.mP_OpcionListaUsuarios());
        obtenerMenu().añadirOpcion(UIMensajes.g_OpcionSalir());
        obtenerMenu().imprimirOpciones();
    }
    
    /**
     * Implementa el funcionamiento del menu
     */
    private void activarInteraccion(){
        int entrada = obtenerMenu().obtenerOpcion();
        switch(entrada){
            case 0: //Iniciar sesion
            login(); //Vuelve al menu si falla el inicio de sesion
            break;
            
            case 1: //"Gestionar usuarios"
            accederGestionUsuarios(); //Accede al menu de gestion de usuarios
            break;
            
            case 2: //Lista de usuarios, "Lista de usuarios"
            obtenerUsuario().imprimirListaUsuarios(obtenerUsuarios());
            
            //Vuelve al menu
            volverMenu();
            break;
            
            case 3: //Salir del programa
            System.exit(0);
            break;
        }
    }

    /**
     * Accede al menu de gestion de usuarios
     */
    private void accederGestionUsuarios(){
        //Preguntamos por la contraseña
        String contraseña = obtenerUsuario().formatearEntradaCadena(UIMensajes.g_Contraseña(), false);
        //Buscamos un posible empleado con los datos previamente especificados
        Empleado temp = (Empleado) Util.buscarCuentaEmpleado(obtenerUsuarios(),
                "GESTION_USUARIOS", contraseña);
        if(temp!=null){ //Si la contraseña es correcta
            UIMenuGestionUsuarios uig = new UIMenuGestionUsuarios(obtenerUsuarios(),
                obtenerProductos(), new UIGestionUsuarios((EpdoFinanciacion) temp));
        }else{ //Si la contraseña NO es correcta
            //Avisa de fallo y vuelve al menu, "contraseña incorrecta"
            System.out.println(UIMensajes.mP_ContraseñaIncorrecta());
            volverMenu();
        }
    }
    
    /**
     * Entra al programa como un empleado especifico usando los datos
     * de su cuenta (usuario y contraseña)
     * 
     * En caso de login fallido vuelve al menu
     */
    private void login(){
        System.out.println(UIMensajes.g_EncabezadoMenus());
        //"Iniciar sesion", "Usuario", "Contraseña"
        System.out.println(UIMensajes.mP_OpcionIniciarSesion());
        obtenerUsuario().formatearCadena(UIMensajes.g_Usuario(), true, true);
        //Obtenemos el usuario
        String nombreUsuario = UIEntradas.obtenerCadena(false);
        obtenerUsuario().formatearCadena(UIMensajes.g_Contraseña(), true, true);
        //Obtenemos la contraseña
        String contraseña = UIEntradas.obtenerCadena(false);
        
        //Buscamos un posible empleado con los datos previamente especificados
        Empleado temp = (Empleado) Util.buscarCuentaEmpleado(obtenerUsuarios(),
                nombreUsuario, contraseña);
        
        if(temp != null){ //Si encuentra un empleado con los datos
            if(temp instanceof EpdoCajero){
                //"Se ha entrado a la cuenta con exito"
                System.out.println(UIMensajes.mP_ExitoLogin() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoCajero menuCajero = new UIMenuEpdoCajero(obtenerUsuarios(),
                obtenerProductos(), new UIEpdoCajero((EpdoCajero) temp));
            }else if(temp instanceof EpdoPostVenta){
                //"Se ha entrado a la cuenta con exito"
                System.out.println(UIMensajes.mP_ExitoLogin() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoPostVenta menuPostVenta = new UIMenuEpdoPostVenta(
                    obtenerUsuarios(),obtenerProductos(), 
                    new UIEpdoPostVenta((EpdoPostVenta) temp));
            }else if(temp instanceof EpdoFinanciacion){
                //"Se ha entrado a la cuenta con exito"
                System.out.println(UIMensajes.mP_ExitoLogin() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoFinanciacion menuFinanciador = new UIMenuEpdoFinanciacion(
                    obtenerUsuarios(),obtenerProductos(), 
                    new UIEpdoFinanciacion((EpdoFinanciacion) temp));
            }else if(temp instanceof EpdoTecnico){
                //"Se ha entrado a la cuenta con exito"
                System.out.println(UIMensajes.mP_ExitoLogin() + 
                    " " + temp.obtenerNombreUsuario());
                UIMenuEpdoTecnico menuFinanciador = new UIMenuEpdoTecnico(
                    obtenerUsuarios(),obtenerProductos(), 
                    new UIEpdoTecnico((EpdoTecnico) temp));
            }
            //PENDIENTE añadir condiciones para cada tipo de empleado
        }else{ //Si por el contrario no existe ninguna cuenta con dichos datos
            //Avisa de fallo y vuelve al menu, "Usuario o contraseña incorrectos"
            System.out.println(UIMensajes.mP_FalloLogin());
            volverMenu();
        }
    }
}
