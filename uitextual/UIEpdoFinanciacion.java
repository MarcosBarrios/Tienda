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
        //"Indique a continuacion el nombre e email del cliente a registrar"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Cliente cliente = (Cliente) usuarios.obtenerUsuario(neCliente.toLowerCase());
        
        //"¿Que desea modificar?" , "Nombre", "Email"
        formatearCadena(UIMensajes.mF_AD_QueModificar(), false, true);
        System.out.print(" (" +  UIMensajes.gNombre() +
           "/" + UIMensajes.gEmail() + "): ");
        String mod = UIEntradas.obtenerCadena(false).toLowerCase();
        String nuevoDato;
        
        //"Nombre", "Email" 
        if(mod.equals(UIMensajes.gNombre().toLowerCase())){
            //Modificar el nombre del cliente
            formatearCadena(UIMensajes.gNombre(), true, true);
        }else if(mod.equals(UIMensajes.gEmail().toLowerCase())){
            //Modificar el email del cliente
            formatearCadena(UIMensajes.gEmail(), true, true);
        }
        nuevoDato = UIEntradas.obtenerCadena(false);
        
        if(cliente!=null){
            //"Nombre", "Email"
            if(mod.equals(UIMensajes.gNombre().toLowerCase())){
                //Modificar el nombre del cliente
                cliente.asignarNombreUsuario(nuevoDato);
            }else if(mod.equals(UIMensajes.gEmail().toLowerCase())){
                //Modificar el email del cliente
                cliente.asignarEmailUsuario(nuevoDato);
            }
            //"Se ha registrado el cliente con exito"
            System.out.println(UIMensajes.mF_DA_RegistradoExito());
        }else{
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Registra un cliente nuevo en la base de datos del programa
     * 
     * @param usuarios Base de datos del programa
     */
    public void darAlta(Usuarios usuarios){
        //"Indique a continuacion el nombre e email del cliente a registrar"
        formatearCadena(UIMensajes.mF_DA_IndicarNombreEmail(), true, true);
        
        //"Nombre", "Nombre aceptado"
        String nombre = formatearEntradaCadena(UIMensajes.gNombre(), true);
        System.out.println(UIMensajes.mF_DA_NombreAceptado());
        
        //"Email", "Email aceptado"
        String email = formatearEntradaCadena(UIMensajes.gEmail(), true);
        System.out.println(UIMensajes.mF_DA_EmailAceptado());
        
        //Añadimos el cliente a la base de datos de clientes
        usuarios.añadirUsuario(new Cliente(nombre, email));
        System.out.println(UIMensajes.mF_DA_RegistradoExito());
    }
    
    /**
     * Imprime los datos de un cliente
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirDatosCliente(Usuarios usuarios){
        //Obtenemos el cliente. "Indique a continuacion el nombre o email del cliente"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Cliente cliente = (Cliente) usuarios.obtenerUsuario(neCliente.toLowerCase());
        
        //Imprimir los datos del cliente. "Nombre", "Email"
        formatearCadena(UIMensajes.gNombre(), true, true);
        System.out.print(cliente.obtenerNombreUsuario());
        formatearCadena(UIMensajes.gEmail(), true, true);
        System.out.print(cliente.obtenerEmailUsuario());
    }
    
}
