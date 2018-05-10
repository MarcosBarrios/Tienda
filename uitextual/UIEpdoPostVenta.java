package uitextual;

import backend.EpdoPostVenta;
import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;
import backend.FichaCliente;
import backend.FichaReparacion;
import backend.EpdoTecnico;

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
public class UIEpdoPostVenta extends UIUsuario{
    
    public UIEpdoPostVenta(EpdoPostVenta usuario){
        super(usuario);
    }
    
    public void repararProducto(Productos productos, Usuarios usuarios){
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
                
                if(producto!=null){ //Si se encontro el producto
                    //Si ha pasado menos tiempo que el tiempo de garantia
                    //se repara el producto sin ningun coste, en caso contrario
                    //se cobra.
                    
                    int añoComprado = producto.obtenerAñoCompra();
                    
                    formatearCadena(UIMensajes.mPV_DP_IntroducirFecha(), true, true);
                    
                    int diaActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Dia());
                    int mesActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Mes());
                    int añoActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Año());
                    
                    //Obtenemos la descripcion del problema
                    String descripcionProblema = formatearEntradaCadena(UIMensajes.mPV_RP_DescripcionProblema(), true);
                    
                    //Obtener el tecnico al que asignar la ficha de reparacion
                    String datosTecnico = formatearEntradaCadena(UIMensajes.mPV_RP_AsignarTecnico(), true);
                    Usuario tempTecnico = usuarios.obtenerUsuario(datosTecnico);
                    
                    boolean tecnicoEncontrado = false;
                    if(tempTecnico != null){
                        if(tempTecnico instanceof EpdoTecnico){
                            EpdoTecnico tecnico = (EpdoTecnico) tempTecnico;
                            tecnicoEncontrado = true;
                            
                            //Creamos el reporte que sera añadido al producto
                            Reporte r = new Reporte();
                            r.asignarEstado(EnumEstadoProducto.ROTO);
                            r.asignarDiaReporte(diaActual);
                            r.asignarMesReporte(mesActual);
                            r.asignarAñoReporte(añoActual);
                            r.asignarDescripcion(descripcionProblema);
                            
                            //Si ha pasado menos tiempo que el tiempo de garantia (En años)
                            if(Math.abs(añoActual-añoComprado) <= producto.obtenerTiempoGarantia()){
                                r.cambiarPagado(true);
                                //"El producto mantiene la garantia. Precio 0 asegurado."
                                System.out.println(UIMensajes.mPV_RP_CumpleGarantia());
                            }else{
                                r.cambiarPagado(false);
                                //"El producto no mantiene la garantia. Es necesario realizar un pago"
                                System.out.println(UIMensajes.mPV_RP_NoCumpleGarantia());
                            }
                            
                            //Añadimos el producto
                            producto.añadirReporte(r);
                            
                            //Creamos la ficha de reparacion que se va a asignar al tecnico
                            FichaReparacion fr = new FichaReparacion(producto, cliente);
                            
                            //Añadimos la ficha de reparacion al tecnico
                            tecnico.añadirFichaReparacion(fr);
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
    
    public void devolverProducto(Productos productos, Usuarios usuarios){
        //"Especificar el nombre, email o dni del cliente"
        String dniUsuario = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
        Usuario usuario = usuarios.obtenerUsuario(dniUsuario); //Obtener cliente
        
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
                    
                    //int diaActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Dia());
                    int mesActual = (int) formatearEntradaDecimal(UIMensajes.mC_AñP_Mes());
                    
                    //Si no se ha comprado el producto hace mas de 3 meses
                    if(Math.abs(mesActual-mesComprado) <= 3){
                        if(producto.obtenerCantidad()==1){ //Si el cliente solo ha comprado una ud. de ese producto
                            //Eliminar el producto de la coleccion de productos comprados del cliente
                            cliente.obtenerFichaCliente().eliminarProductoComprado(nProducto, true);
                        }else{ //Si ha comprado mas de una unidad
                            producto.asignarCantidad(producto.obtenerCantidad()+1);
                        }
                        //"Se ha devuelto el producto con exito"
                        System.out.println(UIMensajes.mPV_DP_DevolucionAceptada());
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
}
