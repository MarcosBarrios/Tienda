package uitextual;

import backend.EpdoPostVenta;

import productos.Reporte;
import productos.EnumEstadoProducto;

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
     * El cliente exige la reparacion de un producto. Se comprueba que el producto
     * no haya sido comprado hace mas de 2 anos (reparacion gratuita a menos de 2 anos
     * de compra)
     * 
     */
    public void repararProducto(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos el numero del producto al cual se va a anadir el reporte
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos la descripcion del problema
        String descripcionProblema = formatearEntradaCadena(UIMensajes.mPV_RP_DescripcionProblema(), 
        		true);
        
        //Obtener el tecnico al que asignar la ficha de reparacion
        //"Nombre, email o DNI del tecnico encargado"
        String dniTecnico = formatearEntradaCadena(UIMensajes.mPV_RP_AsignarTecnico(), true);
        
        //Se aplican los cambios necesarios para la reparacion
        if(obtenerPostVenta().repararProducto(dniCliente, nProducto, 
        		descripcionProblema, dniTecnico)) {
        	//"Se ha anadido el producto para reparacion"
        	System.out.println(UIMensajes.mPV_RP_OperacionExitosa());
        }else {
        	//"Tecnico, cliente o producto no encontrado"
        	System.out.print(UIMensajes.mPV_RP_OperacionFallida());
        }
    }
    
    /**
     * Comprueba el estado de un producto y se cobra al cliente
     * en caso de que este arreglado y la variable pagado del 
     * ultimo reporte este en false.
     * 
     * (Genera una factura en caso de que sea necesario)
     * 
     */
    public void comprobarEstadoProducto(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos el numero del producto
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos el ultimo reporte del producto en reparacion
        Reporte ultimoReporte = obtenerPostVenta().obtenerUltimoReporte(dniCliente, nProducto);
        if(ultimoReporte!=null) {
        	imprimirInformacionReporte(ultimoReporte); //Imprimimos la informacion del reporte
        	
        	//Comprobamos si se ha reparado el producto y si se ha pagado
        	//la reparacion. Genera una factura si no se ha pagado.
        	comprobarEstadoProducto(ultimoReporte, dniCliente, nProducto, 
        			ultimoReporte.obtenerDescripcion());
        }else {
        	//"No se ha encontrado ningun reporte con los datos especificados"
        	System.out.println(UIMensajes.mPV_CEP_ReporteNoEncontrado());
        }
    }
    
    /**
     * Comprueba si se ha reparado un producto y, en caso de que
     * haya sido reparado, se comprueba si el producto ya ha sido
     * pagado. En caso de que no haya sido pagado, se genera una
     * factura y se asigna al cliente del producto reparado.
     * 
     * @param ultimoReporte Reporte con el estado actual del producto
     * @param dniCliente DNI del cliente
     * @param numeroProducto Numero del producto
     * @param descripcionProblema Descripcion del ultimo reporte
     */
    private void comprobarEstadoProducto(Reporte ultimoReporte, String dniCliente,
    		int numeroProducto, String descripcionProblema) {
    	if(ultimoReporte.obtenerNuevoEstado()==EnumEstadoProducto.INTACTO) {
    		if(ultimoReporte.obtenerPagado()) {
    			//"Producto reparado y reparacion pagada"
    			System.out.println(UIMensajes.mPV_RP_ProductoPagado());
    		}else {
    			//El producto ha sido reparado y falta pagar su reparacion
    			generarFactura(dniCliente, numeroProducto, descripcionProblema);
    		}
    	}else {
    		//"El producto todavia no ha sido reparado"
    		System.out.println(UIMensajes.mPV_CEP_ProductoNoReparado());
    	}
    }
    
    /**
     * Se pregunta si se desea generar una factura y, en caso
     * afirmativo, se genera la factura y se asigna al cliente.
     * 
     * @param dniCliente DNI del cliente propietario del producto
     * @param numeroProducto Numero del producto
     * @param descripcionProblema Descripcion del ultimo reporte
     */
    private void generarFactura(String dniCliente, int numeroProducto, 
    		String descripcionProblema) {
    	//"El producto se ha arreglado con exito y esta a espera de pago"
        System.out.println(UIMensajes.mPV_RP_ProductoArreglado());
        
        //"¿Generar factura al cliente?"
        boolean generarFactura = formatearEntradaBoolean(UIMensajes.mT_CEP_GenerarFactura());
        
        if(generarFactura){
        	//Obtenemos el coste
            int costeFactura = (int) formatearEntradaDecimal(UIMensajes.mT_AR_Coste());
            
            //Genera una factura con la descripcion del ultimo reporte
        	obtenerPostVenta().generarFactura(dniCliente, numeroProducto, costeFactura,
        			descripcionProblema);
        }
    }
    
    /**
     * Imprime la informacion almacenada en un reporte
     */
    private void imprimirInformacionReporte(Reporte reporte) {
    	//Imprimir la informacion del ultimo reporte del producto
        System.out.println(reporte);
        
        //La descripcion se imprime a parte
        formatearCadena(UIMensajes.mC_AnP_Descripcion(), true, true);
        System.out.print(reporte.obtenerDescripcion());
    }
    
    /**
     * Devuelve un producto a la tienda
     * 
     */
    public void devolverProducto(){
    	//"Indique a continuacion el DNI del cliente"
        String dniCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarDNICliente(), true);
        
        //Obtenemos el numero del producto
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        if(obtenerPostVenta().devolverProducto(dniCliente, nProducto)) {
        	//"Se ha devuelto el producto con exito"
        	System.out.println(UIMensajes.mPV_DP_DevolucionAceptada());
        }else {
        	//"No se ha encontrado el producto o el cliente o"+
			//"El producto ha sido comprado hace mas de 3 meses";
        	System.out.println(UIMensajes.mPV_DP_DevolucionFallida());
        }
    }
    
    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuEpdoPostVenta(this);
	}
}
