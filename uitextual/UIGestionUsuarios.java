package uitextual;

import backend.EpdoFinanciacion;
import backend.EpdoCajero;
import backend.EpdoComercial;
import backend.EpdoPostVenta;
import backend.EpdoTecnico;
import backend.Usuarios;
import backend.Usuario;
import backend.Empleado;
import backend.Util;
import backend.FichaReparacion;
import backend.Cliente;
import backend.Solicitud;
import backend.FichaCliente;
import backend.Operacion;
import backend.OperacionUsuario;
import backend.OperacionProducto;

import java.util.ArrayList;

/**
 * Parte funcional del menu de gestion de usuarios.
 * 
 *  Operaciones implementadas:
 *  
 *  - Añadir empleado al sistema
 *  - Actualizar nombre,email, usuario o contraseña de un empleado
 *  - Ver lista de usuarios
 *  
 *  Utiliza la clase EpdoFinanciacion como contenedor de la contraseña
 *  para entrar al menu de gestion de usuarios. 
 *  
 *  A la hora de acceder al menu de gestion de usuarios el menu principal
 *  solo pedira la contraseña.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIGestionUsuarios extends UIUsuario{
    
    //Metodo constructor
    public UIGestionUsuarios(EpdoFinanciacion usuario){
        super(usuario);
    }
    
    /**
     * Imprime el historial de un usuario.
     * 
     * @param usuarios Base de datos de usuarios
     */
    public void verHistorialUsuario(Usuarios usuarios){
        String datosUsuario = formatearEntradaCadena(UIMensajes.mGU_VHU_IntroducirUsuario(), true);
        Usuario u = usuarios.obtenerUsuario(datosUsuario);
        
        if(u != null){
            if(u instanceof Cliente){
                Cliente c = (Cliente) u;
                FichaCliente fc = c.obtenerFichaCliente();
                
                for(int i = 0; i < fc.obtenerNumeroOperaciones(); i++){
                    Operacion operacion = fc.obtenerOperacion(i);
                    
                    //Distinguimos entre las dos tipos de operaciones
                    if(operacion instanceof OperacionUsuario){
                        OperacionUsuario opUsuario = (OperacionUsuario)operacion;
                        
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mGU_VHU_EmpleadoResponsable());
                        System.out.print(": " + opUsuario.obtenerResponsable().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mGU_VHU_ClienteAfectado());
                        System.out.print(": " + opUsuario.obtenerUsuario().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mGU_VHU_FechaOperacion());
                        System.out.print(": " + opUsuario.obtenerDia() + "/");
                        System.out.print(opUsuario.obtenerMes() + "/");
                        System.out.print(opUsuario.obtenerAño());
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                        System.out.print(opUsuario.obtenerDescripcion());
                    }else{
                        OperacionProducto opProducto = (OperacionProducto)operacion;
                        
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mGU_VHU_EmpleadoResponsable());
                        System.out.print(": " + opProducto.obtenerResponsable().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mC_LP_NumeroProducto());
                        System.out.print(": " + opProducto.obtenerNumeroProducto());
                        System.out.print(" | " + UIMensajes.mGU_VHU_FechaOperacion());
                        System.out.print(": " + opProducto.obtenerDia() + "/");
                        System.out.print(opProducto.obtenerMes() + "/");
                        System.out.print(opProducto.obtenerAño());
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                        System.out.print(opProducto.obtenerDescripcion());
                    }
                    
                }
                
            }else if(u instanceof Empleado){
                Empleado e = (Empleado) u;
                
                for(int i = 0; i < e.obtenerNumeroOperaciones(); i++){
                    Operacion operacion = e.obtenerOperacion(i);
                    
                    //Distinguimos entre las dos tipos de operaciones
                    if(operacion instanceof OperacionUsuario){
                        OperacionUsuario opUsuario = (OperacionUsuario)operacion;
                        
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mGU_VHU_EmpleadoResponsable());
                        System.out.print(": " + opUsuario.obtenerResponsable().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mGU_VHU_ClienteAfectado());
                        System.out.print(": " + opUsuario.obtenerUsuario().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mGU_VHU_FechaOperacion());
                        System.out.print(": " + opUsuario.obtenerDia() + "/");
                        System.out.print(opUsuario.obtenerMes() + "/");
                        System.out.print(opUsuario.obtenerAño());
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                        System.out.print(opUsuario.obtenerDescripcion());
                    }else{
                        OperacionProducto opProducto = (OperacionProducto)operacion;
                        
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mGU_VHU_EmpleadoResponsable());
                        System.out.print(": " + opProducto.obtenerResponsable().obtenerNombreUsuario());
                        System.out.print(" | " + UIMensajes.mC_LP_NumeroProducto());
                        System.out.print(": " + opProducto.obtenerNumeroProducto());
                        System.out.print(" | " + UIMensajes.mGU_VHU_FechaOperacion());
                        System.out.print(": " + opProducto.obtenerDia() + "/");
                        System.out.print(opProducto.obtenerMes() + "/");
                        System.out.print(opProducto.obtenerAño());
                        System.out.println();
                        System.out.print("\t" + UIMensajes.mC_AñP_Descripcion() + ": ");
                        System.out.print(opProducto.obtenerDescripcion());
                    }
                    
                }
            }
        }
    }
    
    /**
     * Añade una solicitud sobre un cliente
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void añadirSolicitud(Usuarios usuarios){
        //"Indique a continuacion el nombre, email o DNI del cliente"
        String ncliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario u = usuarios.obtenerUsuario(ncliente);
        
        boolean encontrado = false;
        if(u!=null){
            if(u instanceof Cliente){
                Cliente c = (Cliente) u;
                encontrado = true;
                FichaCliente fc = c.obtenerFichaCliente();
                
                //Obtenemos la descripcion de la solicitud
                String descripcionSolicitud = formatearEntradaCadena(UIMensajes.mC_AñP_Descripcion(), true);
                
                //Añadimos la soliticud
                Solicitud s = new Solicitud(false, fc, descripcionSolicitud);
                s.asignarNumeroSolicitud(); //Asignamos su numero de solicitud correspondiente
                
                //Añadimos la solicitud a la ficha del cliente
                fc.añadirSolicitud(s);
                
                //"Solicitud añadida con exito
                System.out.println(UIMensajes.mGU_AS_SolicitudAñadida());
            }
        }
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Acepta una solicitud utilizando su respectivo numero de solicitud
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void aceptarSolicitud(Usuarios usuarios){
        //"Indique a continuacion el nombre, email o DNI del cliente"
        String ncliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario u = usuarios.obtenerUsuario(ncliente);
        
        boolean encontrado = false;
        if(u!=null){
            if(u instanceof Cliente){
                Cliente c = (Cliente) u;
                encontrado = true;
                FichaCliente fc = c.obtenerFichaCliente();
                
                //"Indicar el numero de la solicitud a aceptar"
                int numeroSolicitud = (int)formatearEntradaDecimal(UIMensajes.mGU_AS_IndicarNumeroSolicitud());
                
                //Obtenemos la solicitud utilizando el numero de solicitud
                Solicitud s = fc.obtenerSolicitud(numeroSolicitud, true);
                
                s.cambiarAceptada(true); //Aceptamos la solicitud
            }
        }
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Imprime una lista de solicitudes de todos los clientes
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void verListaSolicitudes(Usuarios usuarios){
        
        //Iteramos todos los usuarios
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario u = usuarios.obtenerUsuario(i);
            if(u instanceof Cliente){ //Si el usuario es un cliente
                Cliente c = (Cliente) u;
                FichaCliente fc = c.obtenerFichaCliente();
                
                //Imprimimos el nombre del cliente
                System.out.println();
                System.out.print("\t" + UIMensajes.g_Nombre()+": ");
                System.out.print(c.obtenerNombreUsuario());
                
                //Iteramos todas las solicitudes del cliente
                for(int j = 0; j < fc.obtenerNumeroSolicitudes(); j++){
                    //Obtenemos una solicitud mediante posicion en lista de solicitudes
                    Solicitud s = fc.obtenerSolicitud(0, false);
                    
                    //Imprimimos el estado seguido de la descripcion
                    System.out.println();
                    if(s.obtenerAceptada()){
                        //"Solicitud aceptada"
                        System.out.print("\t" + UIMensajes.mGU_VLS_SolicitudAceptada());
                    }else{
                        //"Solicitud pendiente"
                        System.out.print("\t" + UIMensajes.mGU_VLS_SolicitudPendiente());
                    }
                    System.out.print(" | ");
                    System.out.print(UIMensajes.mC_AñP_Descripcion()+": ");
                    System.out.print(s.obtenerDescripcion());
                }
            }
        }
        
    }
    
    /**
     * Imprime una lista con todas las fichas de reparacion y sus
     * tecnicos asignatos.
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void verFichasReparacion(Usuarios usuarios){
        
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario u = usuarios.obtenerUsuario(i);

            //Si el usuario obtenido es un tecnico
            if(u instanceof EpdoTecnico){
                //Imprimimos sus fichas de reparacion
                EpdoTecnico t = (EpdoTecnico) u;
                
                //Imprimimos el nombre del tecnico
                System.out.println();
                System.out.print(UIMensajes.g_Nombre() + ": ");
                System.out.print(t.obtenerNombreUsuario());
                for(int j = 0; j < t.obtenerNumeroFichas(); j++){
                    //Imprimimos el nombre del propietario y el numero del producto
                    FichaReparacion fr = t.obtenerFichaReparacion(j);
                    System.out.print(UIMensajes.g_Nombre() + ": ");
                    System.out.print(fr.obtenerPropietario().obtenerNombreUsuario() + " | ");
                    System.out.print(UIMensajes.mC_LP_NumeroProducto() + ": ");
                    System.out.print(fr.obtenerProducto().obtenerNumeroProducto());
                }
                
            }
        }
        
    }
    
    /**
     * Añade un empleado a la base de datos de usuarios del programa
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void añadirEmpleado(Usuarios usuarios){
        //"Especificar a continuacion el nombre, email, usuario" +
        //"y la contraseña del empleado a registrar"
        System.out.println(UIMensajes.mGU_EspecificarDatos());
        
        //Creamos un formulario para las entradas no complejas
        String[] entradas = new String[4];
        entradas[0] = UIMensajes.g_Nombre();
        entradas[1] = UIMensajes.g_Email();
        entradas[2] = UIMensajes.g_Usuario();
        entradas[3] = UIMensajes.g_Contraseña();
        String[] salidas = formularioCadenas(entradas);
        
        //Crea la clase adecuada segun el tipo empleado que se desea registrar
        Empleado e = obtenerTipoEmpleado(salidas[0], salidas[1], 
            salidas[2], salidas[3]);
        
        //Añadimos el empleado a la base de datos de usuarios del programa
        usuarios.añadirUsuario(e);
        
        //"Empleado registrado con exito"
        System.out.println(UIMensajes.mGU_AñE_EmpleadoRegistrado());
    }
    
    /**
     * Crea la clase de empleado adecuada preguntando al usuario
     * que desea registrar un nuevo empleado.
     */
    private Empleado obtenerTipoEmpleado(String nombre, String email,
        String usuario, String contraseña){
        Empleado aux = null;
        
        String postventa = UIMensajes.mGU_AñE_PostVenta();
        String cajero = UIMensajes.mGU_AñE_Cajero();
        String financiacion = UIMensajes.mGU_AñE_Financiacion();
        String tecnico = UIMensajes.mGU_AñE_Tecnico();
        String comercial = UIMensajes.mGU_AñE_Comercial();
        
        System.out.println();
        formatearCadena(UIMensajes.mGU_AñE_EspecificarTipoEmpleado(), true, true);
        System.out.print(" (" + cajero + "/" + postventa +
            "/" + financiacion + "/" + tecnico + "/" + comercial + ") ");
        
        //Obtenemos la lista con los diferentes empleados del programa.
        ArrayList<String> listaEmpleados = Util.listaEmpleados();
        String tipoEmpleado = UIEntradas.obtenerCadenaLimitada(listaEmpleados, false);
        
        //Asigna la clase de aux segun la entrada obtenida.
        if(tipoEmpleado.equals(postventa.toLowerCase())){
            aux = new EpdoPostVenta("", nombre, email, usuario, contraseña);
        }else if(tipoEmpleado.equals(cajero.toLowerCase())){
            aux = new EpdoCajero("", nombre, email, usuario, contraseña);
        }else if(tipoEmpleado.equals(financiacion.toLowerCase())){
            aux = new EpdoFinanciacion("", nombre, email, usuario, contraseña);
        }else if(tipoEmpleado.equals(tecnico.toLowerCase())){
            aux = new EpdoTecnico("", nombre, email, usuario, contraseña);
        }else if(tipoEmpleado.equals(comercial.toLowerCase())){
            aux = new EpdoComercial("", nombre, email, usuario, contraseña);
        }
        
        return aux;
    }
    
    /**
     * Actualiza los datos de un empleado de la tienda.
     * 
     * @usuarios Base de datos del programa
     */
    public void actualizarDatosEmpleado(Usuarios usuarios){
        String entrada = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Empleado empleado = (Empleado) usuarios.obtenerUsuario(entrada.toLowerCase());
    
        if(empleado!=null){ //Si se encuentra el empleado
            String nombre = UIMensajes.g_Nombre();
            String email = UIMensajes.g_Email();
            String usuario = UIMensajes.g_Usuario();
            String contraseña = UIMensajes.g_Contraseña();
            
            //"¿Que desea modificar?" , "Nombre", "Email"
            formatearCadena(UIMensajes.mF_AD_QueModificar(), false, true);
            System.out.print(" (" +  nombre +
               "/" + email + 
               "/" + usuario + 
               "/" + contraseña + "): ");
               
            //Creamos la lista con los posibles valores
            ArrayList<String> listaModificaciones = new ArrayList<String>();
            listaModificaciones.add(nombre.toLowerCase());
            listaModificaciones.add(email.toLowerCase());
            listaModificaciones.add(usuario.toLowerCase());
            listaModificaciones.add(contraseña.toLowerCase());
            
            //Obtenemos una cadena que sea igual a uno de los valores añadidos
            //a listaModificaciones.
            String modElegida = UIEntradas.obtenerCadenaLimitada(listaModificaciones,false);
            
            if(modElegida.equals(nombre.toLowerCase())){ //Modificar nombre
                formatearCadena(nombre, true, true);
                String nuevoNombre = UIEntradas.obtenerCadena(true);
                empleado.asignarNombreUsuario(nuevoNombre);
            }else if(modElegida.equals(email.toLowerCase())){ //Modificar email
                formatearCadena(email, true, true);
                String nuevoEmail = UIEntradas.obtenerCadena(true);
                empleado.asignarEmailUsuario(nuevoEmail);
            }else if(modElegida.equals(usuario.toLowerCase())){ //Modificar usuario
                formatearCadena(usuario, true, true);
                String nuevoUsuario = UIEntradas.obtenerCadena(true);
                empleado.asignarNombreUsuario(nuevoUsuario);
            }else if(modElegida.equals(contraseña.toLowerCase())){ //Modificar contraseña
                formatearCadena(contraseña, true, true);
                String nuevaContraseña = UIEntradas.obtenerCadena(true);
                empleado.asignarContraseña(nuevaContraseña);
            }
            //"Empleado actualizadon con exito"
            System.out.println(UIMensajes.mGU_AcE_EmpleadoActualizado());
        }else{ //Si no se encuentra el empleado
            //"Empleado no encontrado"
            System.out.println(UIMensajes.mGU_AñE_EmpleadoNoEncontrado());
        }
        
    }
    
    /**
     * Implime una lista con los empleados de la tienda.
     * 
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void imprimirListaEmpleados(Usuarios usuarios){
        System.out.println();
        System.out.print(UIMensajes.g_Nombre() + "  " +
            UIMensajes.g_Email() + "  " + 
            UIMensajes.g_Usuario() + "  " +
            UIMensajes.g_Contraseña());
        for(int i = 0; i < usuarios.obtenerTamaño(); i++){
            Usuario usuario = usuarios.obtenerUsuario(i);
            if(usuario instanceof Empleado){ //Si es un empleado
                Empleado temp = (Empleado) usuario;
                
                //Imprimimos nombre, email, usuario y contraseña.
                System.out.println();
                System.out.print(UIMensajes.g_Nombre() + ": ");
                System.out.print(temp.obtenerNombreUsuario());
                System.out.print("  ");
                System.out.print(temp.obtenerEmailUsuario());
                System.out.print("  ");
                System.out.print(temp.obtenerUsuario());
                System.out.print("  ");
                System.out.print(temp.obtenerContraseña());
            }
        }
        System.out.println();
    }
    
}
