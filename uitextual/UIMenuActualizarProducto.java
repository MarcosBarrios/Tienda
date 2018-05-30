package uitextual;

import java.util.ArrayList;
import java.util.Iterator;

import productos.Caracteristica;
import productos.EnumEstadoProducto;
import productos.Producto;
import productos.ProductoHogar;
import productos.ProductoImagen;
import productos.ProductoInformatica;
import productos.ProductoSonido;
import productos.ProductoTelefonia;

/**
 * Menu que implementa las opciones para actualizar un producto. 
 * 
 * @author Marcos Barrios
 * @version 1.1
 */
public class UIMenuActualizarProducto extends UIMenuAccionable {

	//Producto siendo actualizado
	private Producto producto;
	
	//Indica si el menu esta activo o no
	private boolean activo = true;
	
	public UIMenuActualizarProducto(Producto producto) {
		this.asignarProducto(producto);
		
        //Anade las opciones al menu
		anadirOpciones();
	}
	
	/**
	 * Desactiva el menu
	 */
	private void desactivarMenu() {
		activo = false;
	}
	
	/**
	 * Devuelve el producto siendo actualizado
	 * 
	 * @return Producto siendo actualizado
	 */
	public Producto obtenerProducto() {
		return producto;
	}

	/**
	 * Asigna o reasigna el producto siendo actualizado
	 * 
	 * @param producto Producto a actualizar
	 */
	public void asignarProducto(Producto producto) {
		this.producto = producto;
	}

	/**
     * Anade una opcion por cada dato a actualizar
     * 
     * (0) Precio
     * (1) Cantidad
     * (2) Descripcion
     * (3) Peso
     * (4) Financiacion
     * (5) Estado
     * (6) Ano
     * (7) Mes
     * (8) Dia
     * (9) Tiempo de garantia
     * (10) Numero de caja
     */
    private void anadirOpciones(){
        
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Precio());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Cantidad());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Descripcion());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Peso());
        obtenerMenu().anadirOpcion(UIMensajes.mGU_AnE_Financiacion());
        obtenerMenu().anadirOpcion(UIMensajes.mC_LP_Estado());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Ano());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Mes());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AnP_Dia());
        obtenerMenu().anadirOpcion(UIMensajes.mC_AcP_TiempoGarantia());
        obtenerMenu().anadirOpcion(UIMensajes.mC_ILP_NumeroCaja());
        //"Anadir caracteristica al producto"
        obtenerMenu().anadirOpcion(UIMensajes.mC_AcP_AnadirCaracteristica());
        
        //Anadimos las opciones especificas segun la categoria del producto
        anadirOpcionesEspecificas();
    }
    
    /**
     * Anade las opciones a actualizar segun la categoria del producto
     */
    private void anadirOpcionesEspecificas(){
        if(producto instanceof ProductoHogar){
            obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Ancho());
            obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Alto());
            obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Consumo());
        }else if(producto instanceof ProductoInformatica){
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Frecuencia());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_NumeroNucleos());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_CapacidadAlmacenamiento());
        }else if(producto instanceof ProductoSonido){
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Inalambrico());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_ResistenteAgua());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Bluetooth());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Frecuencia());
        }else if(producto instanceof ProductoImagen){
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_TieneBateria());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Inalambrico());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Duracion());
        }else if(producto instanceof ProductoTelefonia){
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_Pulgadas());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_AnchoResolucion());
        	obtenerMenu().anadirOpcion(UIMensajes.mC_ICE_AltoResolucion());
        }
    }
	
	/**
     * Imprime las opciones y obtiene una entrada con el numero
     * de opcion que el usuario quiere usar si la variable
     * activo esta en verdadero. 
     */
	protected void activar() {
		if(activo) {
			obtenerMenu().imprimirOpciones();
	        implementacionOpciones();
		}
	}
	
	/**
     * Obtiene una entrada con el numero de la opcion que el
     * usuario quiere usar.
     */
    private void implementacionOpciones(){
        //Implementacion de lo que hace cada opcion
        int entrada = UIEntradas.obtenerEntero(0, obtenerMenu().obtenerNumeroOpciones());
        switch(entrada){
            case 0: //Precio
            float nuevoPrecio = formatearEntradaDecimal(UIMensajes.mC_AnP_Precio());
            producto.asignarPrecio(nuevoPrecio);
            break;
                
            case 1: //Cantidad
            int nuevaCantidad = (int) formatearEntradaDecimal(UIMensajes.mC_AnP_Cantidad());
            producto.asignarCantidad(nuevaCantidad);
            break;
                
            case 2: //Descripcion
            String nuevaDescripcion = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
            producto.asignarDescripcion(nuevaDescripcion);
            break;
            
            case 3: //Peso
            float nuevoPeso = formatearEntradaDecimal(UIMensajes.mC_AnP_Peso());
            producto.asignarPeso(nuevoPeso);
            break;
                
            case 4: //Financiacion
            boolean financiacion = formatearEntradaBoolean(UIMensajes.mC_AcP_Financiado());
            producto.cambiarFinanciado(financiacion);
            break;
                
            case 5: //Estado del producto (INTACTO, ROTO, DEVUELTO (29/04))
            ArrayList<String> listaEstados = EnumEstadoProducto.obtenerEstados();
            formatearCadena(UIMensajes.mC_LP_Estado(), true, false); //"Estado"
            System.out.print(" [");
            //Vamos a imprimir el numero de estados posibles
            Iterator<String> itr = listaEstados.iterator();
            while(itr.hasNext()){
                String temp = itr.next().toLowerCase();
                System.out.print(temp + "/");
            }
            System.out.print("] :");
            //Obtenemos una cadena que sea "intacto" o "roto" o "vendido"
            String nuevoEstado = UIEntradas.obtenerCadenaLimitada(listaEstados, false);
            String estadoIntacto = EnumEstadoProducto.estadoProductoIntacto().toLowerCase();
            String estadoRoto = EnumEstadoProducto.estadoProductoRoto().toLowerCase();
            String estadoDevuelto = EnumEstadoProducto.estadoProductoDevuelto().toLowerCase();
            if(nuevoEstado.equals(estadoIntacto)){
                producto.cambiarEstado(EnumEstadoProducto.INTACTO);
            }else if(nuevoEstado.equals(estadoRoto)){
                producto.cambiarEstado(EnumEstadoProducto.ROTO);
            }else if(nuevoEstado.equals(estadoDevuelto)){
                producto.cambiarEstado(EnumEstadoProducto.DEVUELTO);
            }
            break;
            
            case 6: //Ano de compra
            int nuevoAno = (int) formatearEntradaDecimal(UIMensajes.mC_AnP_Ano());
            producto.asignarAnoCompra(nuevoAno);
            break;
            
            case 7: //Mes de compra
            int nuevoMes = (int) formatearEntradaDecimal(UIMensajes.mC_AnP_Mes());
            producto.asignarMesCompra(nuevoMes);
            break;
            
            case 8: //Dia de compra
            int nuevoDia = (int) formatearEntradaDecimal(UIMensajes.mC_AnP_Dia());
            producto.asignarDiaCompra(nuevoDia);
            break;
            
            case 9: //Tiempo de garantia
            int nuevoTiempoGarantia = (int) formatearEntradaDecimal(UIMensajes.mC_AcP_TiempoGarantia());
            producto.asignarTiempoGarantia(nuevoTiempoGarantia);
            break;
            
            case 10: //Numero de caja
            int nuevoNumeroCaja = (int) formatearEntradaDecimal(UIMensajes.mC_ILP_NumeroCaja());
            producto.asignarNumeroCaja(nuevoNumeroCaja);
            
            case 11: //Anadir una caracteristica. "Indique a continuacion el titulo de la caracteristica y su descripcion"
            formatearCadena(UIMensajes.mC_Acp_CaracteristicaTituloDescripcion(), false, true);
            String tituloCaracteristica = formatearEntradaCadena(UIMensajes.mC_AcP_Titulo(), true);
            String descripcionCaracteristica = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
            producto.anadirCaracteristica(new Caracteristica(tituloCaracteristica, descripcionCaracteristica));
            break;
        }
        
        //Anadimos las opciones segun la categoria del producto
        implementacionOpcionesEspecificas(entrada);
        
        //"Se ha actualizado el producto con exito"
        System.out.println(UIMensajes.mC_AcP_Exito()); 
        
        //Implementamos funcion para dejar de activar el menu
        opcionAplicarCambios(entrada);
        
        //Reactivamos el menu tras cada cambio siempre y cuando siga activo
        activar(); 
    }
    
    /**
     * Implementa las funciones del menu segun la categoria del producto 
     * a actualizar. 
     * 
     * @param entrada Opcion elegida por el usuario
     */
    private void implementacionOpcionesEspecificas(int entrada){
        
        //Anadimos opciones a actualizar segun la categoria del producto
        if(producto instanceof ProductoHogar){
            ProductoHogar ph = (ProductoHogar) producto;
            
            switch(entrada){
                case 12:
                //"Ancho"
                int nuevoAncho = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Ancho());
                ph.asignarAncho(nuevoAncho);
                break;
                
                case 13:
                //"Alto"
                int nuevoAlto = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Alto());
                ph.asignarAncho(nuevoAlto);
                break;
                
                case 14:
                //"Consumo"
                float nuevoConsumo = (float) formatearEntradaDecimal(UIMensajes.mC_ICE_Consumo());
                ph.asignarConsumo(nuevoConsumo);
                break;
            }
        }else if(producto instanceof ProductoInformatica){
            ProductoInformatica pi = (ProductoInformatica) producto;
            
            switch(entrada){
                case 12:
                //"Frecuencia"
                int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
                pi.asignarFrecuencia(nuevaFrecuencia);
                break;
                
                case 13:
                //"Numero de nucleos"
                int nuevoNumeroNucleos = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_NumeroNucleos());
                pi.asignarNumeroNucleos(nuevoNumeroNucleos);
                break;
                
                case 14:
                //"Capacidad de almacenamiento"
                float nuevaCapacidadAlmacenamiento = formatearEntradaDecimal(UIMensajes.mC_ICE_CapacidadAlmacenamiento());
                pi.asignarCapacidadAlmacenamiento(nuevaCapacidadAlmacenamiento);
                break;
            }
            
        }else if(producto instanceof ProductoSonido){
            ProductoSonido ps = (ProductoSonido) producto;
            
            switch(entrada){
                case 12:
                //"Inalambrico"
                boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
                ps.cambiarInalambrico(esInalambrico);
                break;
                
                case 13:
                //"Resistente al agua"
                boolean esResistenteAgua = formatearEntradaBoolean(UIMensajes.mC_ICE_ResistenteAgua());
                ps.cambiarResistenteAgua(esResistenteAgua);
                break;
                
                case 14:
                //"Bluetooth
                boolean tieneBluetooth = formatearEntradaBoolean(UIMensajes.mC_ICE_Bluetooth());
                ps.cambiarResistenteAgua(tieneBluetooth);
                break;
                
                case 15:
                //"Frecuencia"
                int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
                ps.asignarFrecuencia(nuevaFrecuencia);
                break;
            }
            
        }else if(producto instanceof ProductoTelefonia){
            ProductoTelefonia pt = (ProductoTelefonia) producto;
            
            switch(entrada){
                case 12:
                //"Bateria"
                boolean tieneBateria = formatearEntradaBoolean(UIMensajes.mC_ICE_TieneBateria());
                pt.cambiarInalambrico(tieneBateria);
                break;
                
                case 13:
                //"Inalambrico"
                boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
                pt.cambiarInalambrico(esInalambrico);
                break;
                
                case 14:
                //"Frecuencia"
                int nuevaDuracion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Duracion());
                pt.asignarDuracion(nuevaDuracion);
                break;
            }
        }else if(producto instanceof ProductoImagen){
            ProductoImagen pi = (ProductoImagen) producto;
            
            switch(entrada){
                case 12:
                //"Pulgadas"
                int nuevasPulgadas = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Pulgadas());
                pi.asignarPulgadas(nuevasPulgadas);
                break;
                
                case 13:
                //"Ancho de resolucion"
                int nuevoAnchoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AnchoResolucion());
                pi.asignarAnchoResolucion(nuevoAnchoResolucion);
                break;
                
                case 14:
                //"Alto de resolucion"
                int nuevoAltoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AltoResolucion());
                pi.asignarAltoResolucion(nuevoAltoResolucion);
                break;
            }
           
        }
    }
    
    /**
     * Implementa la opcion de aplicar cambios del menu.
     * 
     * @param entrada Opcion elegida por el usuario
     */
    private void opcionAplicarCambios(int entrada) {
    	if(entrada==obtenerMenu().obtenerNumeroOpciones()) {
    		//"Se han aplicado los cambios al producto"
    		System.out.println(UIMensajes.mC_mAP_CambiosAplicados());
    		desactivarMenu(); //Desactiva el menu
    	}
    }

}
