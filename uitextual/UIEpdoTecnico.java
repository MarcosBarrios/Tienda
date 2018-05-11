package uitextual;

import backend.EpdoTecnico;
import backend.Usuarios;
import backend.Usuario;
import backend.FichaReparacion;
import backend.Cliente;
import backend.FichaCliente;
import backend.Util;
import backend.Pieza;

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
    
    private EpdoTecnico tecnico;
    
    //Metodo constructor
    public UIEpdoTecnico(EpdoTecnico usuario){
        super(usuario);
        this.tecnico = usuario;
    }
    
    /**
     * Añade una pieza a la coleccion de piezas necesarias del tecnico
     * 
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void añadirPiezaNecesaria(){
        //"Precio de la pieza", "Nombre de la pieza", "Descripcion de la pieza"
        float precioPieza = (int) formatearEntradaDecimal(UIMensajes.mT_AP_PrecioPieza());
        String nombrePieza = formatearEntradaCadena(UIMensajes.mT_AP_NombrePieza(), true);
        String descripcionPieza = formatearEntradaCadena(UIMensajes.mT_AP_DescripcionPieza(), true);
        
        //Tras obtener los datos necesarios añadimos la pieza a la coleccion del tecnico
        //que ha iniciado sesion al programa
        Pieza p = new Pieza(precioPieza, nombrePieza, descripcionPieza);
        tecnico.añadirPieza(p);
    }
    
    public void eliminarPiezaNecesaria(){
        //"Nombre de la pieza"
        String nombrePieza = formatearEntradaCadena(UIMensajes.mT_AP_NombrePieza(), true);
        
        for(int i = 0; i < tecnico.obtenerNumeroPiezas(); i++){
            Pieza p = tecnico.obtenerPieza(i);
            if(p.obtenerNombre().toLowerCase().equals(nombrePieza.toLowerCase())){
                tecnico.eliminarPieza(i);
                //"Pieza eliminada"
                System.out.println(UIMensajes.mT_EP_PiezaEliminada());
            }
            
            //Si no se encuentra el producto
            if(!p.obtenerNombre().toLowerCase().equals(nombrePieza.toLowerCase()) &&
                i==tecnico.obtenerNumeroPiezas()-1){
                //"No se encontro la pieza"
                System.out.println(UIMensajes.mT_EP_PiezaNoEncontrada());
            }
        }
        
    }
    
    /**
     * Imprime una lista de las piezas que el tecnico necesita
     * 
     */
    public void verPiezasNecesarias(){
        for(int i = 0; i < tecnico.obtenerNumeroPiezas(); i++){
            Pieza p = tecnico.obtenerPieza(i);
            
            System.out.println();
            formatearCadena(UIMensajes.mT_AP_PrecioPieza(), true, true);
            System.out.print(p.obtenerPrecio() + " | ");
            formatearCadena(UIMensajes.mT_AP_NombrePieza(), true, true);
            System.out.print(p.obtenerNombre() + " | ");
            formatearCadena(UIMensajes.mT_AP_DescripcionPieza(), true, true);
            System.out.print(p.obtenerDescripcion() + " | ");
        }
    }
    
    /**
     * Añade un reporte a un producto 
     * 
     * @param usuarios Base de datos de usuarios del programa
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
     * Imprime el estado de un producto mediante su ultimo reporte añadido
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void verEstadoProducto(Usuarios usuarios){
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
                    //Obtenemos el ultimo reporte del producto
                    Reporte rAnterior = producto.obtenerReporte(producto.obtenerNumeroReportes()-1);
                    
                    //Imprimir la informacion del ultimo reporte del producto
                    
                    //Estado
                    formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
                    System.out.print(rAnterior.obtenerNuevoEstado());
                    
                    //Fecha
                    
                    //Dia
                    formatearCadena(UIMensajes.mC_AñP_Dia(), true, true);
                    System.out.print(rAnterior.obtenerDiaReporte());
                    
                    //Mes
                    formatearCadena(UIMensajes.mC_AñP_Mes(), true, true);
                    System.out.print(rAnterior.obtenerMesReporte());
                    
                    //Año
                    formatearCadena(UIMensajes.mC_AñP_Año(), true, true);
                    System.out.print(rAnterior.obtenerAñoReporte());
                    
                    //Descripcion
                    formatearCadena(UIMensajes.mPV_RP_DescripcionProblema(), true, true);
                    System.out.print(rAnterior.obtenerDescripcion());
                    
                    //Si se ha pagado el producto
                    formatearCadena(UIMensajes.mT_AR_Pagado(), true, true);
                    if(rAnterior.obtenerPagado()){
                        System.out.print(UIMensajes.g_Si());
                    }else{
                        System.out.print(UIMensajes.g_No());
                    }
                    
                    //Coste
                    formatearCadena(UIMensajes.mT_AR_Coste(), true, true);
                    System.out.print(rAnterior.obtenerCoste());
                    
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
