package uitextual;

import backend.EpdoTecnico;
import backend.Usuarios;
import backend.Usuario;
import backend.FichaReparacion;
import backend.Cliente;
import backend.FichaCliente;
import backend.Util;

import productos.Reporte;
import productos.Producto;
import productos.Productos;
import productos.EnumEstadoProducto;

/**
 * Implementa las funciones de los tecnicos de la tienda.
 *
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoTecnico extends UIUsuario{
    
    //Metodo constructor
    public UIEpdoTecnico(EpdoTecnico usuario){
        super(usuario);
    }
    
    /**
     * Añade un reporte a un producto 
     */
    public void añadirReporte(Usuarios usuarios){
        //"Especificar el nombre, email o dni del cliente"
        String datosUsuario = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
        Usuario usuarioTemp = usuarios.obtenerUsuario(datosUsuario); //Obtener usuario por dni
        
        boolean encontrado = false;
        if(usuarioTemp != null){
            if(usuarioTemp instanceof Cliente){
                
                encontrado = true;
                Cliente cliente = (Cliente) usuarioTemp;
                FichaCliente fc = cliente.obtenerFichaCliente();
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = fc.obtenerProductoComprado(nProducto, true);

                if(producto!=null){
                    int diaActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Dia());
                    int mesActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Mes());
                    int añoActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Año());
                   
                    //Obtenemos la descripcion del problema
                    String descripcionProblema = formatearEntradaCadena(UIMensajes.mPV_RP_DescripcionProblema(), true);
                
                    int costeProblema = (int) formatearEntradaDecimal(UIMensajes.mT_AR_Coste());
                    
                    formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
                    String nuevoEstado = UIEntradas.obtenerCadenaLimitada(EnumEstadoProducto.obtenerEstados(), false);
                    
                    //Obtenemos el ultimo reporte del producto
                    Reporte rAnterior = producto.obtenerReporte(producto.obtenerNumeroReportes()-1);
                    
                    Reporte r = new Reporte();
                    r.asignarDiaReporte(diaActual);
                    r.asignarMesReporte(mesActual);
                    r.asignarAñoReporte(añoActual);
                    r.asignarDescripcion(descripcionProblema);
                    r.asignarCoste(costeProblema);
                    
                    //Mantenemos la variable de coste por financiacion
                    if(rAnterior.obtenerPagado()){
                        r.cambiarPagado(true);
                    }
                    
                    //Si nuevoEstado es devuelto
                    if(nuevoEstado.equals(EnumEstadoProducto.estadoProductoDevuelto().toLowerCase())){
                        r.asignarEstado(EnumEstadoProducto.DEVUELTO);
                    }else if(nuevoEstado.equals(EnumEstadoProducto.estadoProductoRoto().toLowerCase())){
                        //Si nuevoEstado es roto
                        r.asignarEstado(EnumEstadoProducto.ROTO);
                    }else{
                        //Si nuevoEstado es intacto
                        r.asignarEstado(EnumEstadoProducto.INTACTO);
                    }
                    
                    //Añadimos el reporte al producto
                    producto.añadirReporte(r);
                }else{
                    //"No se ha encontrado ningun producto con el numero "
                    System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
                }
            }
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Imprime la lista de fichas de reparacion asignadas al tecnico
     * 
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void verListaFichasReparacion(Usuarios usuarios){
        //"Introducir el nombre, email o DNI del tecnico"
        String datosTecnico = formatearEntradaCadena(UIMensajes.mT_VLR_DatosTecnico(), true);
        Usuario usuarioTemp = usuarios.obtenerUsuario(datosTecnico); //Obtener tecnico 
        
        boolean encontrado = false;
        if(usuarioTemp != null){
            if(usuarioTemp instanceof EpdoTecnico){
                encontrado = true;
                EpdoTecnico tecnico = (EpdoTecnico) usuarioTemp;
                
                for(int i = 0; i < tecnico.obtenerNumeroFichas(); i++){
                    FichaReparacion fr = tecnico.obtenerFichaReparacion(i);
                    System.out.println();
                    System.out.print("\t" + UIMensajes.g_Nombre() + ": " +
                        fr.obtenerPropietario().obtenerNombreUsuario());
                    System.out.print(" |" + UIMensajes.mC_LP_NumeroProducto() + ": ");
                    System.out.print(fr.obtenerProducto().obtenerNumeroProducto());
                }
            }
        }
        if(!encontrado){
            //"Tecnico no encontrado"
            System.out.println(UIMensajes.mPV_RP_TecnicoNoEncontrado());
        }
    }
    
}
