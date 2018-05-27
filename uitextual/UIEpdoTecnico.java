package uitextual;

import backend.EpdoTecnico;
import backend.Usuarios;
import backend.Usuario;
import backend.FichaReparacion;

import java.util.ArrayList;
import java.util.Iterator;

import backend.Cliente;
import backend.FichaCliente;
import backend.Pieza;
import backend.EnumOperaciones;

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
public class UIEpdoTecnico extends UIEmpleado{
    
    //Metodo constructor
    public UIEpdoTecnico(EpdoTecnico usuario){
        super(usuario);
    }
    
    /**
     * Devuelve el empleado tecnico usando la interfaz de gestion
     * de usuarios.
     * 
     * @return (EpdoTecnico) obtenerUsuario()
     */
    private EpdoTecnico obtenerTecnico(){
        return (EpdoTecnico)obtenerUsuario();
    }
    
    /**
     * Anade una pieza a la coleccion de piezas necesarias del tecnico
     * 
     * @param usuarios Base de datos de usuarios de la tienda
     */
    public void anadirPiezaNecesaria(){
        //"Precio de la pieza", "Nombre de la pieza", "Descripcion de la pieza"
        float precioPieza = (int) formatearEntradaDecimal(UIMensajes.mT_AP_PrecioPieza());
        String nombrePieza = formatearEntradaCadena(UIMensajes.mT_AP_NombrePieza(), true);
        String descripcionPieza = formatearEntradaCadena(UIMensajes.mT_AP_DescripcionPieza(), true);
        
        obtenerTecnico().anadirPieza(precioPieza, nombrePieza, descripcionPieza);
    }
    
    /**
     * Elimina una pieza de la lista de piezas necesarias
     */
    public void eliminarPiezaNecesaria(){
        //"Nombre de la pieza"
        String nombrePieza = formatearEntradaCadena(UIMensajes.mT_AP_NombrePieza(), true);
        
        ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();
        Iterator<Pieza> itr = listaPiezas.iterator();
        while(itr.hasNext()) {
        	Pieza pieza = itr.next();
        	
        	if(p.obtenerNombre().toLowerCase().equals(nombrePieza.toLowerCase())){
                obtenerTecnico().eliminarPieza(pieza);
                //"Pieza eliminada"
                System.out.println(UIMensajes.mT_EP_PiezaEliminada());
            }
        }
        
        for(int i = 0; i < obtenerTecnico().obtenerNumeroPiezas(); i++){
            Pieza p = obtenerTecnico().obtenerPieza(i);
            if(p.obtenerNombre().toLowerCase().equals(nombrePieza.toLowerCase())){
                obtenerTecnico().eliminarPieza(i);
                //"Pieza eliminada"
                System.out.println(UIMensajes.mT_EP_PiezaEliminada());
            }
            
            //Si no se encuentra el producto
            if(!p.obtenerNombre().toLowerCase().equals(nombrePieza.toLowerCase()) &&
                i==obtenerTecnico().obtenerNumeroPiezas()-1){
                //"No se encontro la pieza"
                System.out.println(UIMensajes.mT_EP_PiezaNoEncontrada());
            }
        }
        
        //Dejamos constancia
        obtenerUsuario().dejarConstancia(obtenerTecnico(), obtenerTecnico(), EnumOperaciones.mT_ELIMINARPIEZA,
        obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Imprime una lista de las piezas que el tecnico necesita
     * 
     */
    public void verPiezasNecesarias(){
        
        for(int i = 0; i < obtenerTecnico().obtenerNumeroPiezas(); i++){
            Pieza p = obtenerTecnico().obtenerPieza(i);
            
            System.out.println();
            formatearCadena(UIMensajes.mT_AP_PrecioPieza(), true, true);
            System.out.print(p.obtenerPrecio() + " | ");
            formatearCadena(UIMensajes.mT_AP_NombrePieza(), true, true);
            System.out.print(p.obtenerNombre() + " | ");
            formatearCadena(UIMensajes.mT_AP_DescripcionPieza(), true, true);
            System.out.print(p.obtenerDescripcion() + " | ");
        }
        
        //Dejamos constancia
        obtenerUsuario().dejarConstancia(obtenerTecnico(), obtenerTecnico(), EnumOperaciones.mT_VERPIEZAS,
        obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Anade un reporte a un producto y deja constancia en el historial del
     * tecnico que ha manipulado el producto
     * 
     */
    public void anadirReporte(){
        //"Introducir el DNI del usuario"
        String DNI = formatearEntradaCadena(UIMensajes.mGU_VHU_IntroducirDNIUsuario(), 
        		true);
        
        //Obtenemos el numero del producto al cual se va a anadir el reporte
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos la descripcion del reporte (problema)
        String descripcionProblema = formatearEntradaCadena(UIMensajes.mPV_RP_DescripcionProblema(), 
        		true);
        
        //Obtenemos el coste de la modificacion al producto
        float costeProblema = formatearEntradaDecimal(UIMensajes.mT_AR_Coste());
        
        //Nuevo estado del producto tras anadir el reporte
        formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
        String nuevoEstado = UIEntradas.obtenerCadenaLimitada(EnumEstadoProducto.obtenerEstados(), 
        		false);
        
        //Anadimos el reporte al producto y dejamos constancia en el historial
        boolean anadido = obtenerTecnico().anadirReporte(DNI, nProducto, descripcionProblema, 
        		costeProblema, nuevoEstado);
        if(anadido) {
        	//"Reporte anadido con exito"
        	System.out.println(UIMensajes.mT_AR_ReporteAnadido());
        }else {
        	//"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * Imprime el estado de un producto mediante su ultimo reporte anadido
     * 
     */
    public void verEstadoProducto(){
    	//"Introducir el DNI del usuario"
        String DNI = formatearEntradaCadena(UIMensajes.mGU_VHU_IntroducirDNIUsuario(), true);
        
        //Obtenemos el numero del producto al cual se va a anadir el reporte
        int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Obtenemos el ultimo reporte del producto
        Reporte ultimoReporte = obtenerTecnico().obtenerUltimoReporteProducto(DNI, nProducto);
        
        //Imprime la informacion del ultimo reporte del producto
        imprimirInformacionReporte(ultimoReporte);

        if(ultimoReporte==null) {
        	//"No se ha encontrado el reporte"
		    System.out.println(UIMensajes.mT_VEP_ReporteNoEncontrado());
        }
    }
    
    /**
     * Imprime la informacion almacenada en un reporte
     * 
     * @param reporte con la informacion a imprimir
     */
    private void imprimirInformacionReporte(Reporte reporte) {
    	//Estado
        formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
        System.out.print(reporte.obtenerNuevoEstado());
        
        //Dia
        formatearCadena(UIMensajes.mC_AnP_Dia(), true, true);
        System.out.print(reporte.obtenerDiaReporte());
        
        //Mes
        formatearCadena(UIMensajes.mC_AnP_Mes(), true, true);
        System.out.print(reporte.obtenerMesReporte());
        
        //Ano
        formatearCadena(UIMensajes.mC_AnP_Ano(), true, true);
        System.out.print(reporte.obtenerAnoReporte());
        
        //Descripcion
        formatearCadena(UIMensajes.mPV_RP_DescripcionProblema(), true, true);
        System.out.print(reporte.obtenerDescripcion());
        
        //Si se ha pagado el producto
        formatearCadena(UIMensajes.mT_AR_Pagado(), true, true);
        if(reporte.obtenerPagado()){
            System.out.print(UIMensajes.g_Si());
        }else{
            System.out.print(UIMensajes.g_No());
        }
        
        //Coste
        formatearCadena(UIMensajes.mT_AR_Coste(), true, true);
        System.out.print(reporte.obtenerCoste());
    }
    
    /**
     * Imprime la lista de fichas de reparacion asignadas al tecnico
     * 
     */
    public void verListaFichasReparacion(){
    	//"Introducir el DNI del usuario"
        String DNI = formatearEntradaCadena(UIMensajes.mGU_VHU_IntroducirDNIUsuario(), true);
    	
        //Obtenemos la lista de fichas del tecnico
        ArrayList<FichaReparacion> listaFichas = obtenerTecnico().obtenerListaFichasReparacion(DNI);
        Iterator<FichaReparacion> itr = listaFichas.iterator();
        while(itr.hasNext()) {
        	FichaReparacion fr = itr.next();
        	
        	//Imprimimos el nombre y el numero de producto de cada ficha
        	System.out.println();
            System.out.print("\t" + UIMensajes.g_Nombre() + ": " +
                fr.obtenerPropietario().obtenerNombreUsuario());
            System.out.print(" |" + UIMensajes.mC_LP_NumeroProducto() + ": ");
            System.out.print(fr.obtenerProducto().obtenerNumeroProducto());
        }
        
        if(listaFichas.size()==0){
        	//No se ha encontrado ningun tecnico con el dni especificado." +
    		//" Tambien es posible que no hayan fichas de reparacion asignadas" + 
    		//"al tecnico encontrado.
            System.out.println(UIMensajes.mPV_RP_TecnicoNoEncontrado());
        }
    }
    
    /**
     * Devuelve el menu asociado al empleado
     */
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuEpdoTecnico(this);
	}
    
}
