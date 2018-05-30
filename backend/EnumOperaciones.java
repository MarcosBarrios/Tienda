package backend;

/**
 * Contiene todas las operaciones y sus cadenas de caracteres en el programa.
 * 
 * El sistema que utiliza este enum es el de historial de operaciones; cada cliente
 * tiene un historial en FichaCliente al igual que los Empleados.
 * 
 * Cada vez que se hace una operacion se anade una entrada Operacion a las colecciones
 * correspondientes de cada actor.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public enum EnumOperaciones{
    mF_ACTUALIZARCLIENTE,
    mF_DARALTACLIENTE,
    mF_IMPRIMIRCLIENTE,
    mGU_ANADIRSOLICITUD,
    mGU_ACEPTARSOLICITUD,
    mGU_VERLISTASOLICITUDES,
    mGU_VERLISTAFICHASREPARACION,
    mGU_ANADIREMPLEADO,
    mGU_ACTUALIZARDATOSEMPLEADO,
    mGU_IMPRIMIRLISTAEMPLEADOS,
    mPV_REPARARPRODUCTO,
    mPV_COMPROBARESTADOPRODUCTO,
    mPV_DEVOLVERPRODUCTO,
    mT_ANADIRPIEZA,
    mT_ELIMINARPIEZA,
    mT_VERPIEZAS,
    mT_ANADIRREPORTE,
    mT_VERESTADOPRODUCTO,
    mT_VERLISTAFICHASREPARACION,
    mC_VENDERPRODUCTO,
    mC_ANADIRPRODUCTO,
    mC_ACTUALIZARPRODUCTO;
    
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
            
            case mGU_ANADIRSOLICITUD:
            return "Anadir una solicitud sobre un cliente";
            
            case mGU_ACEPTARSOLICITUD:
            return "Aceptar una solicitud sobre un cliente";
            
            case mGU_VERLISTASOLICITUDES:
            return "Ver la lista de solicitudes";
            
            case mGU_VERLISTAFICHASREPARACION:
            return "Ver la lista de fichas de reparacion";
            
            case mGU_ANADIREMPLEADO:
            return "Anadir empleado a base de datos";
            
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
            
            case mT_ANADIRPIEZA:
            return "Anadir pieza necesaria";
            
            case mT_ELIMINARPIEZA:
            return "Eliminar pieza necesaria";
            
            case mT_VERPIEZAS:
            return "Ver lista de piezas necesarias";
            
            case mT_ANADIRREPORTE:
            return "Anadir reporte a un producto";
            
            case mT_VERESTADOPRODUCTO:
            return "Imprimir estado de un producto";
            
            case mT_VERLISTAFICHASREPARACION:
            return "Ver fichas de reparacion de tecnico";
            
            case mC_VENDERPRODUCTO:
            return "Vender producto a cliente";
           
            case mC_ANADIRPRODUCTO:
            return "Anadir producto a base de datos";
            
            case mC_ACTUALIZARPRODUCTO:
            return "Actualizar datos de un producto";
        }
        
        return salida;
    }
    
}
