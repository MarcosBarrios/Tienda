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
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatos(), true, true);
        String neCliente = UIEntradas.obtenerCadena(true).toLowerCase();
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatosElegir(), false, true);
        System.out.print(" (" +  UIMensajes.menuFinanciacionActualizarDatosNombre() +
           "/" + UIMensajes.menuFinanciacionActualizarDatosEmail() + "): ");
        String mod = UIEntradas.obtenerCadena(false).toLowerCase();
        String nuevoDato;
        if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosNombre().toLowerCase())){
            //Modificar el nombre del cliente
            formatearCadena(UIMensajes.menuFinanciacionActualizarDatosNombre(), true, true);
        }else if(mod.equals(UIMensajes.menuFinanciacionActualizarDatosEmail().toLowerCase())){
            //Modificar el email del cliente
            formatearCadena(UIMensajes.menuFinanciacionActualizarDatosEmail(), true, true);
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
        formatearCadena(UIMensajes.menuFinanciacionDarAlta(), true, true);
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatosNombre(), true, true);
        String nombre = UIEntradas.obtenerCadena(true);
        System.out.println(UIMensajes.menuFinanciacionDarAltaNombreExito());
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatosEmail(), true, true);
        String email = UIEntradas.obtenerCadena(true);
        System.out.println(UIMensajes.menuFinanciacionDarAltaEmailExito());
        usuarios.añadirUsuario(new Cliente(nombre, email));
        System.out.println(UIMensajes.menuFinanciacionDarAltaExito());
    }
    
    /**
     * Imprime los datos de un cliente
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirDatosCliente(Usuarios usuarios){
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatos(), true, true);
        String neCliente = UIEntradas.obtenerCadena(true).toLowerCase();
        Cliente cliente = (Cliente) usuarios.obtenerUsuario(neCliente);
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatosNombre(), true, true);
        System.out.print(cliente.obtenerNombreUsuario());
        formatearCadena(UIMensajes.menuFinanciacionActualizarDatosEmail(), true, true);
        System.out.print(cliente.obtenerEmailUsuario());
    }
    
}
