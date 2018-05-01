package uitextual;

import backend.EpdoFinanciacion;
import backend.Usuarios;
import backend.Cliente;
import backend.Util;

/**
 * Dispone de metodos para dar de alta a clientes en el sistema con sus 
 * datos personales y actualizar los datos de clientes del sistema.
 * 
 * Los metodos estan orientados a la parte textual de la interfaz de
 * usuario del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoFinanciacion extends UIUsuario{
    
    //Metodo constructor
    public UIEpdoFinanciacion(EpdoFinanciacion usuario){
        super(usuario);
    }
    
    /**
     * Actualiza los datos de un cliente mediante interrogacion
     * de datos en forma de interfaz textual.
     * 
     * @param usuarios Base de datos de usuarios
     */
    public void actualizarDatosCliente(Usuarios usuarios){
        System.out.println();
        System.out.print("\t" + UIMensajes.menuFinanciacionActualizarDatos() + ": ");
        String neCliente = UIEntradas.obtenerCadena(true).toLowerCase();
        System.out.println("\t" + UIMensajes.menuFinanciacionActualizarDatosElegir()
           + " (" +  UIMensajes.menuFinanciacionActualizarDatosNombre() +
           "/" + UIMensajes.menuFinanciacionActualizarDatosEmail() + "): ");
        String mod = UIEntradas.obtenerCadena(false).toLowerCase();
        String nuevoDato;
        if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosNombre().toLowerCase())){
            //Modificar el nombre del cliente
            System.out.println("\t" + UIMensajes.menuFinanciacionActualizarDatosNombre() + ": ");
        }else if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosEmail().toLowerCase())){
            //Modificar el email del cliente
            System.out.println("\t" + UIMensajes.menuFinanciacionActualizarDatosEmail() + ": ");
        }
        nuevoDato = UIEntradas.obtenerCadena(false);
        Cliente cliente = (Cliente) usuarios.obtenerUsuario(neCliente);
        if(cliente!=null){
            if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosNombre().toLowerCase())){
                //Modificar el nombre del cliente
                cliente.asignarNombreUsuario(nuevoDato);
            }else if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosEmail().toLowerCase())){
                //Modificar el email del cliente
                cliente.asignarEmailUsuario(nuevoDato);
            }
            System.out.println(UIMensajes.menuFinanciacionActualizarDatosExito());
        }else{
            System.out.println(UIMensajes.menuFinanciacionActualizarDatosUsuarioNoEncontrado());
        }
    }
    
    /**
     * Registra un cliente nuevo en la base de datos del programa
     * 
     * @param usuarios Base de datos del programa
     */
    public void darAlta(Usuarios usuarios){
        System.out.println();
        System.out.print(UIMensajes.menuFinanciacionDarAlta() + ": ");
        System.out.println();
        System.out.print("\t");
        System.out.print(UIMensajes.menuFinanciacionActualizarDatosNombre() + ": ");
        String nombre = UIEntradas.obtenerCadena(true);
        System.out.println(UIMensajes.menuFinanciacionDarAltaNombreExito());
        System.out.println();
        System.out.print("\t");
        System.out.print(UIMensajes.menuFinanciacionActualizarDatosEmail() + ": ");
        String email = UIEntradas.obtenerCadena(true);
        System.out.println(UIMensajes.menuFinanciacionDarAltaEmailExito());
        usuarios.añadirUsuario(new Cliente(nombre, email));
        System.out.println(UIMensajes.menuFinanciacionDarAltaExito());
    }
    
    public void imprimirDatosCliente(Usuarios usuarios){
        System.out.println();
        System.out.print("\t" + UIMensajes.menuFinanciacionActualizarDatos() + ": ");
        String neCliente = UIEntradas.obtenerCadena(true).toLowerCase();
        Cliente cliente = (Cliente) usuarios.obtenerUsuario(neCliente);
        System.out.println("\t" +
            UIMensajes.menuFinanciacionActualizarDatosNombre() + ": "
            + cliente.obtenerNombreUsuario());
        System.out.println("\t" +
            UIMensajes.menuFinanciacionActualizarDatosEmail() + ": "
            + cliente.obtenerEmailUsuario());
    }
    
}
