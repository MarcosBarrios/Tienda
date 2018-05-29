package uitextual;

import backend.EpdoTecnico;
import backend.FichaReparacion;

import java.util.ArrayList;
import java.util.Iterator;

import backend.Pieza;

import productos.Reporte;
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
        //Obtenemos el nombre de la pieza a eliminar. "Nombre de la pieza"
        String nombrePieza = formatearEntradaCadena(UIMensajes.mT_AP_NombrePieza(), true);
        
        //Eliminamos la pieza con el nombre nombrePieza
        obtenerTecnico().eliminarPieza(nombrePieza);
    }
    
    /**
     * Imprime una lista de las piezas que el tecnico necesita
     * 
     */
    public void verPiezasNecesarias(){
    	//Obtenemos la lista de piezas necesarias del tecnico
        ArrayList<Pieza> listaPiezas = obtenerTecnico().obtenerListaPiezasNecesarias();
        Iterator<Pieza> itr = listaPiezas.iterator();
        while(itr.hasNext()) { //Iterar todas las piezas
        	Pieza pieza = itr.next();
        	
        	//Imprimir la informacion de la pieza
        	System.out.println();
            formatearCadena(UIMensajes.mT_AP_PrecioPieza(), true, true);
            System.out.print(pieza.obtenerPrecio() + " | ");
            formatearCadena(UIMensajes.mT_AP_NombrePieza(), true, true);
            System.out.print(pieza.obtenerNombre() + " | ");
            formatearCadena(UIMensajes.mT_AP_DescripcionPieza(), true, true);
            System.out.print(pieza.obtenerDescripcion() + " | ");
        }
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
        if(obtenerTecnico().anadirReporte(DNI, nProducto, descripcionProblema, 
        		costeProblema, nuevoEstado)) {
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
        
        if(ultimoReporte!=null) {
        	//Imprime la informacion del ultimo reporte del producto
            System.out.println(ultimoReporte);
            
            //Imprime la descripcion pues no esta incluida en el toString de Reporte
            formatearCadena(UIMensajes.mC_AnP_Descripcion(), true, true);
            System.out.println(ultimoReporte.obtenerDescripcion());
        }else {
        	//"No se ha encontrado el reporte"
		    System.out.println(UIMensajes.mT_VEP_ReporteNoEncontrado());
        }
    }
    
    /**
     * Imprime la lista de fichas de reparacion asignadas al tecnico
     * 
     */
    public void verListaFichasReparacion(){
        //Obtenemos la lista de fichas del tecnico
        ArrayList<FichaReparacion> listaFichas = obtenerTecnico().obtenerListaFichasReparacion();
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
