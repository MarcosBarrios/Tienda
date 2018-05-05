package uitextual;

import backend.EpdoPostVenta;
import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;

import productos.Productos;
import productos.Producto;

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
    
    public void devolverProducto(Productos productos, Usuarios usuarios){
        //"Escriba a continuacion el DNI del cliente"
        String dniUsuario = formatearEntradaCadena(UIMensajes.mPV_IndicarCliente(), true);
        Usuario usuario = usuarios.obtenerUsuario(dniUsuario); //Obtener usuario por dni
        
        boolean encontrado = false;
        if(usuario != null){
            if(usuario instanceof Cliente){
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = productos.obtenerProducto(nProducto, true);
                
                if(producto!=null){ //Si se encontro el producto
                    //int diaComprado = producto.obtenerDiaCompra();
                    int mesComprado = producto.obtenerMesCompra();
                    
                    formatearCadena(UIMensajes.mPV_IntroducirFecha(), true, true);
                    
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
                        
                    }else{
                        //"El producto se ha comprado hace mas de 3 meses, devolucion rechazada"
                        System.out.println(UIMensajes.mPV_DevolucionRechazada());
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
}
