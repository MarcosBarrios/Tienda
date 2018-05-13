package backend;

import java.util.ArrayList;

/**
 * Contiene todas las operaciones y sus cadenas de caracteres en el programa.
 * 
 * El sistema que utiliza este enum es el de historial de operaciones; cada cliente
 * tiene un historial en FichaCliente al igual que los Empleados.
 * 
 * Cada vez que se hace una operacion se añade una entrada Operacion a las colecciones
 * correspondientes de cada actor.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public enum EnumOperaciones{
    mF_ACTUALIZARCLIENTE,
    mF_DARALTACLIENTE,
    mF_IMPRIMIRCLIENTE,
    mGU_AÑADIRSOLICITUD,
    mGU_ACEPTARSOLICITUD,
    mGU_VERLISTASOLICITUDES,
    mGU_VERLISTAFICHASREPARACION,
    mGU_AÑADIREMPLEADO,
    mGU_ACTUALIZARDATOSEMPLEADO,
    mGU_IMPRIMIRLISTAEMPLEADOS,
    mPV_REPARARPRODUCTO,
    mPV_COMPROBARESTADOPRODUCTO,
    mPV_DEVOLVERPRODUCTO,
    mT_AÑADIRPIEZA,
    mT_ELIMINARPIEZA,
    mT_VERPIEZAS,
    mT_AÑADIRREPORTE,
    mT_VERESTADOPRODUCTO,
    mT_VERLISTAFICHASREPARACION,
    mC_VENDERPRODUCTO,
    mC_AÑADIRPRODUCTO,
    mC_ACTUALIZARPRODUCTO,
    mC_IMPRIMIRDATOSPRODUCTO,
    mC_IMPRIMIRLISTAPRODUCTOS;
    
    /**
     * Devuelve una cadena de caracteres asociada a una operacion
     * 
     * @param operacion Operacion de la que obtener cadena
     */
    public static String obtenerCadenaOperacion(EnumOperaciones operaciones){
        String salida = "";
        
        //Se va a devolver una cadena por cada estado del enum.
        switch(operaciones){
            case mF_ACTUALIZARCLIENTE:
            return "Actualizar datos cliente";
            
            case mF_DARALTACLIENTE:
            return "Dar de alta a un cliente";
            
            case mF_IMPRIMIRCLIENTE:
            return "Imprimir los datos de un cliente";
            
            case mGU_AÑADIRSOLICITUD:
            return "Añadir una solicitud sobre un cliente";
            
            case mGU_ACEPTARSOLICITUD:
            return "Aceptar una solicitud sobre un cliente";
            
            case mGU_VERLISTASOLICITUDES:
            return "Ver la lista de solicitudes";
            
            case mGU_VERLISTAFICHASREPARACION:
            return "Ver la lista de fichas de reparacion";
            
            case mGU_AÑADIREMPLEADO:
            return "Añadir empleado a base de datos";
            
            case mGU_ACTUALIZARDATOSEMPLEADO:
            return "Actualizar los datos de un empleado";
            
            case mGU_IMPRIMIRLISTAEMPLEADOS:
            return "Imprimir una lista con los empleados de la tienda";
            
            case mPV_REPARARPRODUCTO:
            return "Reparar un producto";
            
            case mPV_COMPROBARESTADOPRODUCTO:
            return "Comprobar estado de un producto";
            
            case mPV_DEVOLVERPRODUCTO:
            return "Devolver un producto";
            
            case mT_AÑADIRPIEZA:
            return "Añadir pieza necesaria";
            
            case mT_ELIMINARPIEZA:
            return "Eliminar pieza necesaria";
            
            case mT_VERPIEZAS:
            return "Ver lista de piezas necesarias";
            
            case mT_AÑADIRREPORTE:
            return "Añadir reporte a un producto";
            
            case mT_VERESTADOPRODUCTO:
            return "Imprimir estado de un producto";
            
            case mT_VERLISTAFICHASREPARACION:
            return "Ver fichas de reparacion de tecnico";
            
            case mC_VENDERPRODUCTO:
            return "Vender producto a cliente";
           
            case mC_AÑADIRPRODUCTO:
            return "Añadir producto a base de datos";
            
            case mC_ACTUALIZARPRODUCTO:
            return "Actualizar datos de un producto";
                        
            case mC_IMPRIMIRDATOSPRODUCTO:
            return "Imprimir datos de un producto";
            
            case mC_IMPRIMIRLISTAPRODUCTOS:
            return "Imprimir base de datos de productos";

        }
        
        return salida;
    }
    
}
