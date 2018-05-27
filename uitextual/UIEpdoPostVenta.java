package uitextual;

import backend.EpdoPostVenta;
import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;
import backend.FichaCliente;
import backend.FichaReparacion;
import backend.EpdoTecnico;
import backend.Factura;
import backend.EnumOperaciones;

import productos.Reporte;
import productos.Productos;
import productos.Producto;
import productos.EnumEstadoProducto;

import java.lang.Math;

/**
 * Implementa las funciones que debe realizar un empleado de
 * post venta en la parte textual del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoPostVenta extends UIEmpleado{
    
    public UIEpdoPostVenta(EpdoPostVenta usuario){
        super(usuario);
    }
    
    /**
     * Devuelve el empleado de post venta que esta usando el programa
     */
    private EpdoPostVenta obtenerPostVenta(){
        return (EpdoPostVenta)obtenerUsuario();
    }
    
    /**
     * El cliente exige la reparación de un producto. Se comprueba que el producto
     * no haya sido comprado hace más de 2 anos (reparacion gratuita a menos de 2 anos
     * de compra)
     * 
     * @param productos Base de datos de productos del programa
     * @param usuarios Base de datos de usuarios del programa
     */
    public void repararProducto(){
        //"Especificar el nombre, email o dni del cliente"
        String datosUsuario = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
        Usuario usuarioTemp = obtenerUsuarios().obtenerUsuario(datosUsuario); //Obtener usuario por dni
        
        boolean encontrado = false;
        if(usuarioTemp != null){
            if(usuarioTemp instanceof Cliente){
                encontrado = true;
                Cliente cliente = (Cliente) usuarioTemp;
                FichaCliente fc = cliente.obtenerFichaCliente();
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = fc.obtenerProductoComprado(nProducto, true);
                
                if(producto!=null){ //Si se encontro el producto
                    //Si ha pasado menos tiempo que el tiempo de garantia
                    //se repara el producto sin ningun coste, en caso contrario
                    //se cobra.
                    
                    int anoComprado = producto.obtenerAnoCompra();
                    
                    formatearCadena(UIMensajes.mPV_DP_IntroducirFecha(), true, true);
                    
                    //Obtenemos la descripcion del problema
                    String descripcionProblema = formatearEntradaCadena(UIMensajes.mPV_RP_DescripcionProblema(), true);
                    
                    //Obtener el tecnico al que asignar la ficha de reparacion
                    String datosTecnico = formatearEntradaCadena(UIMensajes.mPV_RP_AsignarTecnico(), true);
                    Usuario tempTecnico = obtenerUsuarios().obtenerUsuario(datosTecnico);
                    
                    boolean tecnicoEncontrado = false;
                    if(tempTecnico != null){
                        if(tempTecnico instanceof EpdoTecnico){
                            EpdoTecnico tecnico = (EpdoTecnico) tempTecnico;
                            tecnicoEncontrado = true;
                            
                            //Creamos el reporte que sera anadido al producto
                            Reporte r = new Reporte();
                            r.asignarEstado(EnumEstadoProducto.ROTO);
                            r.asignarDiaReporte(obtenerDiaActual()); //Dia
                            r.asignarMesReporte(obtenerMesActual()); //Mes
                            r.asignarAnoReporte(obtenerAnoActual()); //Ano
                            r.asignarDescripcion(descripcionProblema);
                            
                            //Si han pasado menos de 2 anos se repara gratuitamente
                            if(Math.abs(anoComprado-2) <= 0){
                                r.cambiarPagado(true);
                                //"El producto mantiene la garantia. Precio 0 asegurado."
                                System.out.println(UIMensajes.mPV_RP_CumpleGarantia());
                            }else{
                                r.cambiarPagado(false);
                                //"El producto no mantiene la garantia. Es necesario realizar un pago"
                                System.out.println(UIMensajes.mPV_RP_NoCumpleGarantia());
                            }
                            
                            //Anadimos el producto
                            producto.anadirReporte(r);
                            
                            //Creamos la ficha de reparacion que se va a asignar al tecnico
                            FichaReparacion fr = new FichaReparacion(producto, cliente);
                            
                            //Anadimos la ficha de reparacion al tecnico
                            tecnico.anadirFichaReparacion(fr);
                            
                            //Dejamos constancia
                            obtenerUsuario().dejarConstancia(cliente, producto, obtenerPostVenta(), EnumOperaciones.mPV_REPARARPRODUCTO,
                            obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
                        }
                    }
                    if(!tecnicoEncontrado){ //Si no se encuenta el tecnico
                        //"Tecnico no encontado"
                        System.out.println(UIMensajes.mPV_RP_TecnicoNoEncontrado());
                    }
                    
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
     * Comprueba el estado de un producto y, en caso de que este arreglado y 
     * la variable pagado del ultimo reporte este en false, se cobra al cliente.
     * 
     * (Genera una factura en caso de que sea necesario)
     * 
     * @param usuarios Base de datos de usuarios del programa
     */
    public void comprobarEstadoProducto(){
        //"Especificar el nombre, email o dni del cliente"
        String datosUsuario = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
        Usuario usuarioTemp = obtenerUsuarios().obtenerUsuario(datosUsuario); //Obtener usuario por dni
        
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
                    
                    //Fecha:
                    //Dia
                    formatearCadena(UIMensajes.mC_AnP_Dia(), true, true);
                    System.out.print(rAnterior.obtenerDiaReporte());
                    
                    //Mes
                    formatearCadena(UIMensajes.mC_AnP_Mes(), true, true);
                    System.out.print(rAnterior.obtenerMesReporte());
                    
                    //Ano
                    formatearCadena(UIMensajes.mC_AnP_Ano(), true, true);
                    System.out.print(rAnterior.obtenerAnoReporte());
                    
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
                    
                    //Si no se ha pagado
                    if(!rAnterior.obtenerPagado()){
                        //"El producto se ha arreglado con exito y esta a espera de pago"
                        System.out.println(UIMensajes.mPV_RP_ProductoArreglado());
                        
                        //"¿Generar factura al cliente?"
                        boolean generarFactura = formatearEntradaBoolean(UIMensajes.mT_CEP_GenerarFactura());
                        
                        if(generarFactura){
                            //"Indique el dia, mes y ano actual"
                            System.out.println(UIMensajes.mPV_DP_IntroducirFecha());

                            //Obtenemos el coste
                            int costeFactura = (int) formatearEntradaDecimal(UIMensajes.mT_AR_Coste());
                            String descripcion = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
                            
                            Factura f = new Factura(costeFactura, descripcion, obtenerDiaActual(),
                            obtenerMesActual(), obtenerAnoActual());
                                
                            fc.anadirFactura(f);
                            
                            //Dejamos constancia
                            obtenerUsuario().dejarConstancia(cliente, producto, obtenerPostVenta(),
                            EnumOperaciones.mPV_COMPROBARESTADOPRODUCTO,
                            obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
                        }
                    }else{
                        //"El producto ya se ha pagado"
                        System.out.println(UIMensajes.mT_CEP_ProcesoCompletado());
                    }
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
     * Devuelve un producto a la tienda
     * 
     * @param productos Base de datos de productos de la tienda
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void devolverProducto(){
        //"Especificar el nombre, email o dni del cliente"
        String dniUsuario = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
        Usuario usuario = obtenerUsuarios().obtenerUsuario(dniUsuario); //Obtener cliente
        
        boolean encontrado = false;
        if(usuario != null){
            if(usuario instanceof Cliente){
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                FichaCliente fc = cliente.obtenerFichaCliente();
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = fc.obtenerProductoComprado(nProducto, true);
                
                if(producto!=null){ //Si se encontro el producto
                    //int diaComprado = producto.obtenerDiaCompra();
                    int mesComprado = producto.obtenerMesCompra();
                    
                    formatearCadena(UIMensajes.mPV_DP_IntroducirFecha(), true, true);
                    
                    //fechaActual[1]=mesActual
                    
                    //Si no se ha comprado el producto hace mas de 3 meses
                    if(Math.abs(obtenerMesActual()-mesComprado) <= 3){
                        //Se acepta el producto
                        if(producto.obtenerCantidad()==1){ //Si el cliente solo ha comprado una ud. de ese producto
                            //Eliminar el producto de la coleccion de productos comprados del cliente
                            cliente.obtenerFichaCliente().eliminarProductoComprado(nProducto, true);
                        }else{ //Si ha comprado mas de una unidad
                            producto.asignarCantidad(producto.obtenerCantidad()+1);
                        }
                        //"Se ha devuelto el producto con exito"
                        System.out.println(UIMensajes.mPV_DP_DevolucionAceptada());
                        
                        //Dejamos constancia
                        obtenerUsuario().dejarConstancia(cliente, producto, obtenerPostVenta(),
                        EnumOperaciones.mPV_DEVOLVERPRODUCTO, obtenerDiaActual(), 
                        obtenerMesActual(), obtenerAnoActual());
                    }else{
                        //"El producto se ha comprado hace mas de 3 meses, devolucion rechazada"
                        System.out.println(UIMensajes.mPV_DP_DevolucionRechazada());
                    }
                }else{
                    //"No se ha encontrado ningun producto con el numero "
                    System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado() + nProducto);
                }
                
            }
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuAccionable activarMenu() {
		return new UIMenuEpdoPostVenta(this);
	}
}
