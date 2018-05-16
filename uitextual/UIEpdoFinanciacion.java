package uitextual;

import backend.EpdoFinanciacion;
import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;
import backend.Util;
import backend.EnumOperaciones;
import backend.FichaCliente;
import backend.Factura;

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
    public UIEpdoFinanciacion(EpdoFinanciacion usuario,
    int diaActual, int mesActual, int añoActual){
        super(usuario, diaActual, mesActual, añoActual);
    }
    
    /**
     * Devuelve el financiador
     * 
     * @return (EpdoFinanciacion)obtenerUsuario()
     */
    private EpdoFinanciacion obtenerFinanciador(){
        return (EpdoFinanciacion) obtenerUsuario();
    }
    
    /**
     * Actualiza los datos de un cliente mediante interrogacion
     * de datos en forma de interfaz textual.
     * 
     * @param usuarios Base de datos de usuarios
     */
    public void actualizarDatosCliente(Usuarios usuarios){
        //"Indique a continuacion el nombre o email del cliente a registrar"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario usuario = usuarios.obtenerUsuario(neCliente.toLowerCase());
        
        boolean encontrado = false;
        if(usuario != null){
            if(usuario instanceof Cliente){  //Si el usuario encontrado es un cliente
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                
                //Entradas aceptables
                String nombre = UIMensajes.g_Nombre();
                String email = UIMensajes.g_Email();
                String domicilio = UIMensajes.g_Domicilio();
                String telefono = UIMensajes.g_Telefono();
                
                //"¿Que desea modificar?" , "Nombre", "Email", "Domicilio", "Telefono"
                formatearCadena(UIMensajes.mF_AD_QueModificar(), true, true);
                System.out.print(" (" +  nombre + "/" + email + "/" + 
                    domicilio + "/" + telefono + "): ");
                
                //Entradas aceptables
                ArrayList<String> listaModificaciones = new ArrayList<String>();
                listaModificaciones.add(nombre.toLowerCase());
                listaModificaciones.add(email.toLowerCase());
                listaModificaciones.add(domicilio.toLowerCase());
                listaModificaciones.add(telefono.toLowerCase());
                   
                //Obtenemos una cadena que indique lo que se quiere modificar
                String modElegida = UIEntradas.obtenerCadenaLimitada(listaModificaciones, false);
                
                //"Nombre", "Email" , "Domicilio", "Telefono"
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
                }else if(modElegida.equals(domicilio.toLowerCase())){
                    //Modificar el domicilio del cliente
                    formatearCadena(domicilio, true, true);
                    String nuevoDomicilio = UIEntradas.obtenerCadena(true);
                    cliente.asignarDomicilio(nuevoDomicilio);
                }else if(modElegida.equals(telefono.toLowerCase())){
                    //Modificar el domicilio del cliente
                    formatearCadena(telefono, true, true);
                    String nuevoTelefono = UIEntradas.obtenerCadena(true);
                    cliente.asignarTelefono(nuevoTelefono);
                }
                
                //"Se ha registrado el cliente con exito"
                System.out.println(UIMensajes.mF_DA_RegistradoExito());
                
                //Dejamos constancia de la operacion realizada
                dejarConstancia(cliente, obtenerFinanciador(), EnumOperaciones.mF_ACTUALIZARCLIENTE,
                obtenerDiaActual(), obtenerMesActual(), obtenerAñoActual());
            }
        }
        
        if(!encontrado){
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
        
        //"Escribir DNI"
        String dni = formatearEntradaCadena(UIMensajes.mF_DA_EscribirDNI(), true);
        Usuario u = usuarios.obtenerUsuario(dni);
        
        if(u==null){
            //"Nombre", "Nombre aceptado"
            String nombre = formatearEntradaCadena(UIMensajes.g_Nombre(), true);
            System.out.println(UIMensajes.mF_DA_NombreAceptado());
        
            //"Email", "Email aceptado"
            String email = formatearEntradaCadena(UIMensajes.g_Email(), true);
            System.out.println(UIMensajes.mF_DA_EmailAceptado());
            
            //"Domicilio", "Domicilio aceptado"
            String domicilio = formatearEntradaCadena(UIMensajes.g_Domicilio(), true);
            System.out.println(UIMensajes.mF_DA_DomicilioAceptado());
            
            //"Telefono", "Telefono aceptado"
            String telefono = formatearEntradaCadena(UIMensajes.g_Telefono(), true);
            System.out.println(UIMensajes.mF_DA_TelefonoAceptado());
            
            //Creamos el cliente con los datos especificados
            Cliente cliente = new Cliente(dni, nombre, email, domicilio, telefono);
            
            //Añadimos el cliente a la base de datos de clientes
            usuarios.añadirUsuario(cliente);
            System.out.println(UIMensajes.mF_DA_RegistradoExito());
            
            //Dejamos constancia de la operacion realizada
            dejarConstancia(cliente, obtenerFinanciador(), EnumOperaciones.mF_DARALTACLIENTE,
            obtenerDiaActual(), obtenerMesActual(), obtenerAñoActual());
        }else{
            //"Ya existe un cliente registrado con el DNI especificado, registro fallido"
            System.out.println("\t" + UIMensajes.mF_DA_ClienteYaRegistrado());
        }
        
    }
    
    /**
     * Imprime los datos de un cliente
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void imprimirDatosCliente(Usuarios usuarios){
        //Obtenemos el cliente. "Indique a continuacion el nombre o email del cliente"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario usuario = usuarios.obtenerUsuario(neCliente.toLowerCase());

        boolean encontrado = false;
        if(usuario!=null){
            if(usuario instanceof Cliente){ //Si el usuario encontrado es un cliente
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                
                //Imprimir los datos del cliente. "Nombre", "Email"
                formatearCadena(UIMensajes.g_DNI(), true, true);
                System.out.print(cliente.obtenerDNI());
                formatearCadena(UIMensajes.g_Nombre(), true, true);
                System.out.print(cliente.obtenerNombreUsuario());
                formatearCadena(UIMensajes.g_Email(), true, true);
                System.out.print(cliente.obtenerEmailUsuario());
                formatearCadena(UIMensajes.g_Domicilio(), true, true);
                System.out.print(cliente.obtenerDomicilio());  
                formatearCadena(UIMensajes.g_Telefono(), true, true);
                System.out.print(cliente.obtenerTelefono());  
                
                //Dejamos constancia de la operacion realizada
                dejarConstancia(cliente, obtenerFinanciador(), EnumOperaciones.mF_IMPRIMIRCLIENTE,
                obtenerDiaActual(), obtenerMesActual(), obtenerAñoActual());
            }
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Imprime las facturas de un cliente
     * 
     * @param usuarios Base de datos de usuarios 
     */
    public void verListaFacturas(Usuarios usuarios){
        //Obtenemos el cliente. "Indique a continuacion el nombre o email del cliente"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario usuario = usuarios.obtenerUsuario(neCliente.toLowerCase());

        boolean encontrado = false;
        if(usuario!=null){
            if(usuario instanceof Cliente){ //Si el usuario encontrado es un cliente
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                FichaCliente fc = cliente.obtenerFichaCliente();
                
                //Imprimimos la informacion de cada factura que tenga el cliente
                for(int i = 0; i < fc.obtenerNumeroFacturas(); i++){
                    Factura factura = fc.obtenerFactura(i);
                    
                    System.out.println(); //Primera linea
                    System.out.print("\t" + UIMensajes.mT_AR_Coste());
                    System.out.print(factura.obtenerCoste());
                    System.out.print(" |" + UIMensajes.mC_AñP_Dia());
                    System.out.print(factura.obtenerDia());
                    System.out.print(" |" + UIMensajes.mC_AñP_Mes());
                    System.out.print(factura.obtenerMes());
                    System.out.print(" |" + UIMensajes.mC_AñP_Año());
                    System.out.print(factura.obtenerAño());                    
                    
                    System.out.println(); //Segunda linea
                    System.out.print("\t" + UIMensajes.mC_AñP_DescripcionFactura());
                    System.out.print(factura.obtenerDescripcion());
                }
                
                //Dejamos constancia de la operacion realizada
                dejarConstancia(cliente, obtenerFinanciador(), EnumOperaciones.mF_IMPRIMIRCLIENTE,
                obtenerDiaActual(), obtenerMesActual(), obtenerAñoActual());
            }
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
}
