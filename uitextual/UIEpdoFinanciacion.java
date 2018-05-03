package uitextual;

import backend.EpdoFinanciacion;
import backend.Usuarios;
import backend.Cliente;
import backend.Util;

import java.util.ArrayList;

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
        
        if(cliente!=null){
            String nombre = UIMensajes.g_Nombre();
            String email = UIMensajes.g_Email();
            
            //"¿Que desea modificar?" , "Nombre", "Email"
            formatearCadena(UIMensajes.mF_AD_QueModificar(), true, true);
            System.out.print(" (" +  nombre + "/" + email + "): ");
            
            ArrayList<String> listaModificaciones = new ArrayList<String>();
            listaModificaciones.add(nombre.toLowerCase());
            listaModificaciones.add(email.toLowerCase());
               
            String modElegida = UIEntradas.obtenerCadenaLimitada(listaModificaciones, false);
            
            //"Nombre", "Email" 
            if(modElegida.equals(nombre.toLowerCase())){
                //Modificar el nombre del cliente
                formatearCadena(nombre, true, true);
                String nuevoNombre = UIEntradas.obtenerCadena(true);
                cliente.asignarNombreUsuario(nuevoNombre);
            }else if(modElegida.equals(email.toLowerCase())){
                //Modificar el email del cliente
                formatearCadena(email, true, true);
                String nuevoEmail = UIEntradas.obtenerCadena(true);
                cliente.asignarEmailUsuario(nuevoEmail);
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
        String nombre = formatearEntradaCadena(UIMensajes.g_Nombre(), true);
        System.out.println(UIMensajes.mF_DA_NombreAceptado());
        
        //"Email", "Email aceptado"
        String email = formatearEntradaCadena(UIMensajes.g_Email(), true);
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
        formatearCadena(UIMensajes.g_Nombre(), true, true);
        System.out.print(cliente.obtenerNombreUsuario());
        formatearCadena(UIMensajes.g_Email(), true, true);
        System.out.print(cliente.obtenerEmailUsuario());
    }
    
}
