package uitextual;

import backend.Usuarios;
import backend.Usuario;
import backend.Cliente;
import backend.Empleado;
import backend.EpdoCajero;
import backend.Util;
import backend.EnumOperaciones;
import backend.FichaCliente;
import backend.Factura;

import productos.Productos;
import productos.Producto;
import productos.ProductoHogar;
import productos.ProductoInformatica;
import productos.ProductoTelefonia;
import productos.ProductoImagen;
import productos.ProductoSonido;
import productos.Caracteristica;
import productos.EnumEstadoProducto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementacion de la representacion de la clase EpdoCajero en la
 * interfaz textual del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEpdoCajero extends UIEmpleado{
    
    //Numero de la caja desde la que se esta operando
    private int numeroCaja;
    
    public UIEpdoCajero(EpdoCajero cajero) {
    	super(cajero);
        
        //Obtenemos el numero de la caja desde la cual el cajero va a operar
        formatearCadena(UIMensajes.mC_EspecificarNumeroCaja(), true, true);
        numeroCaja = (int) UIEntradas.obtenerDecimal(0, Util.NUMEROCAJAS);
    }
    
    /**
     * Devuelve el numero de caja desde la que se esta operando
     * 
     * @return numeroCaja Numero de caja desde la que se esta operando
     */
    public int obtenerNumeroCaja(){
        return numeroCaja;
    }
    
    /**
     * Devolvemos el cajero que esta trabajando sobre el programa
     * 
     * @return (EpdoCajero)obtenerUsuario()
     */
    private EpdoCajero obtenerCajero(){
        return (EpdoCajero) obtenerUsuario();
    }
    
    /**
     * Vende un producto a un cliente. 
     * 
     * @param usuarios Base de datos de usuarios
     * @param productos Base de datos de productos
     */
    public void venderProducto(){
        //Obtener el cliente al que se va a vender el producto. "Indicar el nombre del cliente"
        String neCliente = formatearEntradaCadena(UIMensajes.mF_AD_IndicarNombreEmail(), true);
        Usuario usuario = obtenerUsuarios().obtenerUsuario(neCliente.toLowerCase());
        
        boolean encontrado = true;
        if(usuario!=null){
            
            if(usuario instanceof Cliente){
                encontrado = true;
                Cliente cliente = (Cliente) usuario;
                
                //Obtener el producto que se va a vender al cliente. "Numero de producto"
                int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                Producto producto = obtenerProductos().obtenerProducto(nProducto, true);
                
                if(producto!=null){
                    //Asignamos el numero de la caja desde la que se vende el producto
                    producto.asignarNumeroCaja(obtenerCajero().obtenerNumeroCaja());
                    
                    //Si hay al menos un producto en el almacen
                    if(producto.obtenerCantidad()>0){
                        //Financiar?
                        boolean financiar = formatearEntradaBoolean(UIMensajes.mC_AnP_Financiar());
                        producto.cambiarFinanciado(financiar);
        
                        //Si se quiere financiar el producto
                        if(financiar){
                            //Creamos la factura y asignamos sus datos
                            Factura factura = new Factura();
                            factura.asignarCoste(producto.obtenerPrecio());
                            factura.asignarDia(obtenerDiaActual());
                            factura.asignarMes(obtenerMesActual());
                            factura.asignarAno(obtenerAnoActual());
                            
                            //"Descripcion de la factura"
                            String descripcionFactura = formatearEntradaCadena(UIMensajes.mC_AnP_DescripcionFactura(), true);
                            factura.asignarDescripcion(descripcionFactura);
                            
                            //Anadimos la factura al historial de facturas del cliente
                            FichaCliente fc = cliente.obtenerFichaCliente();
                            fc.anadirFactura(factura);
                        }
                        
                        //Anadir producto a la lista de productos comprados del cliente
                        cliente.obtenerFichaCliente().anadirProductoComprado(producto);
                        producto.asignarCantidad(producto.obtenerCantidad()-1);
                        
                        //Dejamos constancia en el historial
                        obtenerUsuario().dejarConstancia(cliente, producto, obtenerCajero(), 
                        EnumOperaciones.mC_VENDERPRODUCTO, obtenerDiaActual(), obtenerMesActual(), 
                        obtenerAnoActual());
                    }else{ //Si por otro lado no hay reservas del producto
                        //"No quedan reservas del producto especificado"
                        System.out.println(UIMensajes.mC_VP_SinStock());
                    }
                    
                }else{
                    //"No se ha encontrado ningun producto con el numero "
                    System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
                }
            }
        }else{ //En caso de que no encuentre ningun usuario
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
        
        if(!encontrado){
            //"Cliente no encontrado"
            System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
        }
    }
    
    /**
     * El cajero ingresa en la base de datos de productos un producto que
     * la tienda acaba de recibir.
     * 
     * Interrogatorio textual sobre las caracteristicas del producto.
     *
     * @param productos Base de datos de productos de la tienda
     */
    public void anadirProducto(){
        //"A continuacion se va a proceder a introducir" + 
        //" las caracteristicas del producto. "
        System.out.println(UIMensajes.mC_AnP_ProcederIntroduccion());
        
        //Obtenemos la clase producto adecuada segun su categoria
        Producto producto = obtenerCategoria();
        
        //Creamos un formulario para los valores que son numeros        
        String entradasNumericas[] = new String[3];
        entradasNumericas[0] = UIMensajes.mC_AnP_Precio();
        entradasNumericas[1] = UIMensajes.mC_AnP_Cantidad();
        entradasNumericas[2] = UIMensajes.mC_AnP_Peso();
        float salidasNumericas[] = formularioDecimales(entradasNumericas);
        producto.asignarPrecio(salidasNumericas[0]);
        producto.asignarCantidad((int) salidasNumericas[1]);
        producto.asignarPeso(salidasNumericas[2]);
        
        //Descripcion
        String descripcion = formatearEntradaCadena(UIMensajes.mC_AnP_Descripcion(), true);
        producto.asignarDescripcion(descripcion);
        
        //"Tiempo de garantia"
        int tiempoGarantia = (int)formatearEntradaDecimal(UIMensajes.mC_AcP_TiempoGarantia());
        producto.asignarTiempoGarantia(tiempoGarantia);
        
        //Pregunta por caracteristicas del producto segun su categoria
        producto = anadirProductoEspecifico(producto);
        
        //Asignamos la fecha de compra al producto
        producto.asignarDiaCompra(obtenerDiaActual());
        producto.asignarMesCompra(obtenerMesActual());
        producto.asignarAnoCompra(obtenerAnoActual());
        
        //Se comprueba si la base de datos contiene un producto igual al que se
        //va a anadir, en cuyo caso simplemente aumenta la cantidad del actual
        //producto en la base de datos en vez de anadir un producto mas a esta.
        Producto productoIgual = Util.obtenerProductoIgual(producto, obtenerProductos());
        if(productoIgual!=null){
            productoIgual.asignarCantidad(productoIgual.obtenerCantidad()+1);
        }else{
            obtenerProductos().anadirProducto(producto);
        }
        
        //Dejamos constancia en el historial
        obtenerUsuario().dejarConstancia(obtenerCajero(), obtenerCajero(), EnumOperaciones.mC_ANADIRPRODUCTO, 
        obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Metodo auxiliar de anadirProducto(...)
     * 
     * Obtiene las opciones especificas iniciales del producto segun su categoria
     * 
     * @param producto Producto al que anadir las opciones especificas
     */
    private Producto anadirProductoEspecifico(Producto producto){
        //Anadimos opciones a actualizar segun la categoria del producto
        if(producto instanceof ProductoHogar){
            ProductoHogar ph = (ProductoHogar) producto;
            
            //"Ancho"
            int nuevoAncho = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Ancho());
            ph.asignarAncho(nuevoAncho);
           
            //"Alto"
            int nuevoAlto = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Alto());
            ph.asignarAncho(nuevoAlto);
            
            //"Consumo"
            float nuevoConsumo = (float) formatearEntradaDecimal(UIMensajes.mC_ICE_Consumo());
            ph.asignarConsumo(nuevoConsumo); 
            
            return ph;
        }else if(producto instanceof ProductoInformatica){
            ProductoInformatica pi = (ProductoInformatica) producto;
            //"Frecuencia"
            int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
            pi.asignarFrecuencia(nuevaFrecuencia);
         
            //"Numero de nucleos"
            int nuevoNumeroNucleos = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_NumeroNucleos());
            pi.asignarNumeroNucleos(nuevoNumeroNucleos);
            
            //"Capacidad de almacenamiento"
            float nuevaCapacidadAlmacenamiento = formatearEntradaDecimal(UIMensajes.mC_ICE_CapacidadAlmacenamiento());
            pi.asignarCapacidadAlmacenamiento(nuevaCapacidadAlmacenamiento);
                
            return pi;
        }else if(producto instanceof ProductoSonido){
            ProductoSonido ps = (ProductoSonido) producto;

            //"Inalambrico"
            boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
            ps.cambiarInalambrico(esInalambrico);
                                           
            //"Resistente al agua"
            boolean esResistenteAgua = formatearEntradaBoolean(UIMensajes.mC_ICE_ResistenteAgua());
            ps.cambiarResistenteAgua(esResistenteAgua);
                                         
            //"Bluetooth
            boolean tieneBluetooth = formatearEntradaBoolean(UIMensajes.mC_ICE_Bluetooth());
            ps.cambiarResistenteAgua(tieneBluetooth);
            
            //"Frecuencia"
            int nuevaFrecuencia = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Frecuencia());
            ps.asignarFrecuencia(nuevaFrecuencia);
            
            return ps;
        }else if(producto instanceof ProductoTelefonia){
            ProductoTelefonia pt = (ProductoTelefonia) producto;
            //"Bateria"
            boolean tieneBateria = formatearEntradaBoolean(UIMensajes.mC_ICE_TieneBateria());
            pt.cambiarInalambrico(tieneBateria);
            
            //"Inalambrico"
            boolean esInalambrico = formatearEntradaBoolean(UIMensajes.mC_ICE_Inalambrico());
            pt.cambiarInalambrico(esInalambrico);
                     
            //"Frecuencia"
            int nuevaDuracion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Duracion());
            pt.asignarDuracion(nuevaDuracion);  
            
            return pt;
        }else if(producto instanceof ProductoImagen){
            ProductoImagen pi = (ProductoImagen) producto;
            
            //"Pulgadas"
            int nuevasPulgadas = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_Pulgadas());
            pi.asignarPulgadas(nuevasPulgadas);
            
            //"Ancho de resolucion"
            int nuevoAnchoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AnchoResolucion());
            pi.asignarAnchoResolucion(nuevoAnchoResolucion);
            
            //"Alto de resolucion"
            int nuevoAltoResolucion = (int) formatearEntradaDecimal(UIMensajes.mC_ICE_AltoResolucion());
            pi.asignarAltoResolucion(nuevoAltoResolucion);
                
            return pi;
        }
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de anadirProducto()
     * 
     * Devuelve un producto segun su categoria
     */
    private Producto obtenerCategoria(){
        //Categoria del producto (Hogar|Telefonia|Imagen|Informatica)
        String categoria;
        
        //Obtenemos la lista con las categorias de productos
        ArrayList<String> listaCategorias = Util.listaCategoriasProductos();
        
        //"Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) "
        System.out.println(UIMensajes.mC_AnP_ElegirCategoriaProducto());
        categoria = UIEntradas.obtenerCadenaLimitada(listaCategorias, false);
        
        //"Categoria seleccionada correctamente"
        System.out.println(UIMensajes.mC_AnP_CategoriaSeleccionada());
        
        //Creamos la clase del producto segun la categoria que sea
        Producto producto = null;
        if(categoria.equals(UIMensajes.mC_AnP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Imagen().toLowerCase())){
            producto = new ProductoImagen();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Sonido().toLowerCase())){
            producto = new ProductoSonido();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Hogar().toLowerCase())){
            producto = new ProductoHogar();
        }
        else if(categoria.equals(UIMensajes.mC_AnP_Informatica().toLowerCase())){
            producto = new ProductoInformatica();
        }
        
        return producto;
    }
    
    /**
     * Actualiza los datos de un producto. Para ello pregunta en primer lugar
     * por el numero del producto, posteriormente ensena una lista con los datos
     * del producto y pregunta que opcion quiere modificar.
     * 
     * Por ultimo pregunta por el nuevo valor al que se quiere modificar.
     * 
     * Adicionalmente habra una opcion para anadir una caracteristica adicional.
     * 
     * @param productos Base de datos de productos de la tienda
     * @param numProducto Numero del producto que se quiere modificar
     */
    public void actualizarProducto(){
        //"¿El producto pertenece a un cliente? (si/no)"
        formatearCadena(UIMensajes.mC_AcP_BuscarCliente(), true, true);
        boolean buscarCliente = UIEntradas.obtenerBooleana();
        
        //Obtenemos el numero del producto a modificar
        int numeroProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
        
        //Buscamos un producto que tenga el numero de producto especificado por el usuario
        Producto producto = null;
        Cliente cliente = null;
        if(buscarCliente){ //Si se va a buscar el producto en un cliente
            //"Especificar el nombre, email o dni del cliente"
            String entrada = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
            Usuario usuario = obtenerUsuarios().obtenerUsuario(entrada);
            
            boolean encontrado = false;
            if(usuario instanceof Cliente){
                if(usuario!=null){ //Si se encontro el cliente
                    cliente = (Cliente) usuario;
                
                    ArrayList<Producto> listaProductos = Util.listaProductosComprados(cliente);
                    Iterator<Producto> itr = listaProductos.iterator();
                    while(itr.hasNext()){
                        Producto tempProducto = itr.next();
                        
                        //Si se encuentra un producto con el numero especificado en la 
                        //lista de productos del cliente
                        if(tempProducto.obtenerNumeroProducto()==numeroProducto){
                            encontrado = true;
                            producto = tempProducto;
                            
                            //"Se ha encontrado un producto igual de "
                            System.out.println(UIMensajes.mC_AcP_EncontradoEnCliente() + ": "
                                + cliente.obtenerNombreUsuario());
                        }
                    }
                }

            }else{ //Si no se encuentra el cliente
                //"Cliente no encontrado"
                System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
            }
            
            if(!encontrado){ //Si no se ha encontrado ningun producto
                //"No se ha encontrado ningun producto con el numero"
                System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado());
            }
        }else{ //Si el producto a actualizar no pertenece a un cliente
            producto = obtenerProductos().obtenerProducto(numeroProducto, true);
        }
        
        if(producto!=null){
            //"Producto encontrado. Numero de producto = "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()+
                numeroProducto);
            
            //Imprimimos las caracteristicas del producto
            imprimirCaracteristicasProducto(producto);
            
            //Creamos un menu para elegir que opcion modificar
            producto = menuModificacionOpciones(producto);
            
            if(!buscarCliente){
                //Dejamos constancia de la operacion
                obtenerUsuario().dejarConstancia(obtenerCajero(), obtenerCajero(), EnumOperaciones.mC_ACTUALIZARPRODUCTO,
                obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
            }else{
                //Dejamos constancia de la operacion
                if(cliente!=null){ //Evitamos que se trabaje con un null
                	obtenerUsuario().dejarConstancia(cliente, producto, obtenerCajero(), EnumOperaciones.mC_ACTUALIZARPRODUCTO,
                    obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
                }
            }
        }else{ //En caso de que no encuentre el producto
            //"No se ha encontrado ningun producto con el numero "
            System.out.println(UIMensajes.mC_AcP_ProductoEncontrado()
                + numeroProducto);
        }
        
    }
    
    /**
     * Metodo auxiliar de actualizarProducto(...)
     * 
     * Crea un menu para elegir la opcion a modificar
     * 
     */
    private Producto menuModificacionOpciones(Producto producto){
        //Iniciamos el menu y anadimos las opciones
        UIMenu menuModOpciones = anadirOpcionesMenuActualizar(producto);
    
        //Imprime el menu
        menuModOpciones.imprimirOpciones();
        
        //Implementacion de que hace cada opcion
        int entrada = UIEntradas.obtenerEntero(0, menuModOpciones.obtenerNumeroOpciones());
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
        
        //Modificar las caracteristicas especificas segun categoria de producto
        producto = menuModificacionesEspecifico(producto, entrada);
        
        //"Se ha actualizado el producto con exito"
        System.out.println(UIMensajes.mC_AcP_Exito()); 
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de menuModificacionOpciones(...) 
     */
    private Producto menuModificacionesEspecifico(Producto producto, int entrada){
        
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
            return ph;
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
            
            return pi;
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
            
            return ps;
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
            return pt;
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
           
            return pi;
        }
        
        return producto;
    }
    
    /**
     * Metodo auxiliar de menuModificacionOpciones(...)
     * Anade las opciones al menu
     */
    private UIMenu anadirOpcionesMenuActualizar(Producto producto){
        UIMenu menuModOpciones = new UIMenu();
        
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Precio());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Cantidad());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Descripcion());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Peso());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AcP_Financiado());
        menuModOpciones.anadirOpcion(UIMensajes.mC_LP_Estado());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Ano());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Mes());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AnP_Dia());
        menuModOpciones.anadirOpcion(UIMensajes.mC_AcP_TiempoGarantia());
        menuModOpciones.anadirOpcion(UIMensajes.mC_ILP_NumeroCaja());
        //"Anadir caracteristica al producto"
        menuModOpciones.anadirOpcion(UIMensajes.mC_AcP_AnadirCaracteristica());
        
        //Anadimos las opciones especificas segun la categoria del producto
        menuModOpciones = anadirOpcionesEspecificas(producto, menuModOpciones);
        
        return menuModOpciones;
    }
    
    /**
     * Metodo auxiliar de anadirOpcionesMenuActualizar(...)
     * 
     * Anade las opciones especificas necesarias al menu de modificacion
     * de un producto dependiendo de la categoria del producto
     * 
     * @param menuModOpciones Menu al que anadir las opciones especificas
     */
    private UIMenu anadirOpcionesEspecificas(Producto producto, UIMenu menuModOpciones){
        
        if(producto instanceof ProductoHogar){
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Ancho());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Alto());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Consumo());
        }else if(producto instanceof ProductoInformatica){
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Frecuencia());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_NumeroNucleos());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_CapacidadAlmacenamiento());
        }else if(producto instanceof ProductoSonido){
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Inalambrico());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_ResistenteAgua());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Bluetooth());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Frecuencia());
        }else if(producto instanceof ProductoImagen){
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_TieneBateria());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Inalambrico());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Duracion());
        }else if(producto instanceof ProductoTelefonia){
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_Pulgadas());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_AnchoResolucion());
            menuModOpciones.anadirOpcion(UIMensajes.mC_ICE_AltoResolucion());
        }
        
        return menuModOpciones;
    }
    
    /**
     * Metodo auxiliar de actualizarProducto(...) 
     * 
     * Imprime las caracteristicas del producto pasado como argumento
     * 
     * @param producto Producto con las caracteristicas a imprimir
     */
    private void imprimirCaracteristicasProducto(Producto producto){
        //Numero de producto
        formatearCadena(UIMensajes.mC_LP_NumeroProducto(), 
            true, true);
        System.out.print(producto.obtenerNumeroProducto());
        
        //Imprime la categoria del producto con el mismo formato que el resto
        imprimirCaracteristicasEspecificas(producto);
        
        //Precio
        formatearCadena(UIMensajes.mC_AnP_Precio(), true, true);
        System.out.print(producto.obtenerPrecio());
        
        //Cantidad
        formatearCadena(UIMensajes.mC_AnP_Cantidad(), true, true);
        System.out.print(producto.obtenerCantidad());
        
        //Descripcion
        formatearCadena(UIMensajes.mC_AnP_Descripcion(), true, true);
        System.out.print(producto.obtenerDescripcion());
        
        //Peso
        formatearCadena(UIMensajes.mC_AnP_Peso(), true, true);
        System.out.print(producto.obtenerPeso());
        
        //Financiado
        formatearCadena(UIMensajes.mC_AcP_Financiado(), 
            true, true);
        System.out.print(producto.obtenerEstadoFinanciado());
        
        //Estado
        formatearCadena(UIMensajes.mC_LP_Estado(), true, true);
        System.out.print(producto.obtenerEstadoProducto());
        
        //Numero de caja
        formatearCadena(UIMensajes.mC_ILP_NumeroCaja(), true, true);
        System.out.print(producto.obtenerNumeroCaja());
        
        //Fecha de compra
        formatearCadena(UIMensajes.mC_AcP_FechaCompra(), 
            true, true);
        System.out.print(producto.obtenerDiaCompra() + "/");
        System.out.print(producto.obtenerMesCompra() + "/");
        System.out.print(producto.obtenerAnoCompra());
        System.out.print(" " + UIMensajes.mC_AnP_Dia());
        System.out.print("/" + UIMensajes.mC_AnP_Mes());
        System.out.print("/" + UIMensajes.mC_AnP_Ano());
        
        //Tiempo de garantia
        formatearCadena(UIMensajes.mC_AcP_TiempoGarantia(), 
            true, true);
        System.out.print(producto.obtenerTiempoGarantia());
        
        //Imprime caracteristicas segun el tipo de producto
        imprimirCaracteristicasEspecificas(producto);
        
        //Caracteristicas extra
        for(int i = 0; i < producto.obtenerNumCaracteristicas(); i++){
            Caracteristica temp = producto.obtenerCaracteristica(i);
            formatearCadena(temp.obtenerTitulo(), true, true);
            System.out.print(temp.obtenerDescripcion());
        }
    }

    
    /**
     * Metodo auxiliar del metodo imprimirCaracteristicasProducto(...)
     * 
     * Imprime las caracteristicas especificas del producto segun su
     * categoria
     */
    private void imprimirCaracteristicasEspecificas(Producto producto){
        if(producto instanceof ProductoHogar){
            ProductoHogar ph = (ProductoHogar) producto;
            
            //"Ancho"
            formatearCadena(UIMensajes.mC_ICE_Ancho(), true, true);
            System.out.print(ph.obtenerAncho());
            
            //"Alto"
            formatearCadena(UIMensajes.mC_ICE_Alto(), true, true);
            System.out.print(ph.obtenerAlto());
            
            //"Consumo"
            formatearCadena(UIMensajes.mC_ICE_Consumo(), true, true);
            System.out.print(ph.obtenerConsumo());
        }else if(producto instanceof ProductoSonido){
            ProductoSonido ps = (ProductoSonido) producto;
            
            //Util.booleanAPalabra toma una boolean y devuelve "Si" o "No"
            
            //"¿Es inalambrico?"
            formatearCadena(UIMensajes.mC_ICE_Inalambrico(), true, true);
            System.out.print(Util.booleanAPalabra(ps.inalambrico()));
            
            //"¿Es resistente al agua?"
            formatearCadena(UIMensajes.mC_ICE_ResistenteAgua(), true, true);
            System.out.print(Util.booleanAPalabra(ps.resistenteAgua()));
            
            //"¿Tiene bluetooth?"
            formatearCadena(UIMensajes.mC_ICE_Bluetooth(), true, true);
            System.out.print(Util.booleanAPalabra(ps.bluetooth()));
            
            //"Frecuencia"
            formatearCadena(UIMensajes.mC_ICE_Frecuencia(), true, true);
            System.out.print(ps.obtenerFrecuencia());
            
        }else if(producto instanceof ProductoInformatica){
            ProductoInformatica pi = (ProductoInformatica) producto;
            
            //"Frecuencia"
            formatearCadena(UIMensajes.mC_ICE_Frecuencia(), true, true);
            System.out.print(pi.obtenerFrecuencia());
            
            //"Numero de nucleos"
            formatearCadena(UIMensajes.mC_ICE_NumeroNucleos(), true, true);
            System.out.print(pi.obtenerNumeroNucleos());
        }else if(producto instanceof ProductoImagen){
            ProductoImagen pi = (ProductoImagen) producto;
            
            //"Pulgadas"
            formatearCadena(UIMensajes.mC_ICE_Pulgadas(), true, true);
            System.out.print(pi.obtenerPulgadas());
            
            //"Ancho (Resolucion)"
            formatearCadena(UIMensajes.mC_ICE_AnchoResolucion(), true, true);
            System.out.print(pi.obtenerAnchoResolucion());
            
            //"Alto (Resolucion)"
            formatearCadena(UIMensajes.mC_ICE_AltoResolucion(), true, true);
            System.out.print(pi.obtenerAltoResolucion());
        }else if(producto instanceof ProductoTelefonia){
            ProductoTelefonia pt = (ProductoTelefonia) producto;
            
            //"¿Tiene bateria?"
            formatearCadena(UIMensajes.mC_ICE_TieneBateria(), true, true);
            System.out.print(Util.booleanAPalabra(pt.tieneBateria()));
            
            //"¿Es inalambrico?"
            formatearCadena(UIMensajes.mC_ICE_Inalambrico(), true, true);
            System.out.print(Util.booleanAPalabra(pt.esInalambrico()));
            
            //"Duracion"
            formatearCadena(UIMensajes.mC_ICE_Duracion(), true, true);
            System.out.print(pt.obtenerDuracion());
        }
    }
    
    /**
     * Imprime los datos de un producto
     * 
     * @param productos Base de datos de productos
     * @param usuarios Base de datos de usuarios 
     */
    public void imprimirDatosProducto(){
        //"Ver las caracteristicas de un producto"
        formatearCadena(UIMensajes.mC_OpcionVerDatosProducto(), false, true);
        
        //"¿El producto pertenece a un cliente? (si/no)"
        boolean perteneceCliente = formatearEntradaBoolean(UIMensajes.mC_AcP_BuscarCliente());
        
        if(!perteneceCliente){ //Si el producto NO pertenece a un cliente
            
            //Pregunta por el numero de producto hasta obtener un numero valido
            //"Numero de producto"
            int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
            Producto bProducto = obtenerProductos().obtenerProducto(nProducto, true);
            if(bProducto!=null){ //Si encuentra el producto
                imprimirCaracteristicasProducto(bProducto);
                
                //Dejamos constancia de la operacion realizada
                obtenerUsuario().dejarConstancia(obtenerCajero(), obtenerCajero(), EnumOperaciones.mC_IMPRIMIRDATOSPRODUCTO,
                obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
            }else{ //Si NO encuentra el producto
                //"No se ha encontrado ningun producto con el numero "
                System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado() +
                    nProducto);
            }
        }else{ //Si el producto pertenece a un cliente
            //"Especificar el nombre, email o dni del cliente"
            String datosCliente = formatearEntradaCadena(UIMensajes.mC_AcP_NombreDNIEmailCliente(), true);
            Usuario usuario = obtenerUsuarios().obtenerUsuario(datosCliente);
            
            boolean encontrado = false;
            if(usuario!=null){ //Si se encontro un usuario con los datos especificados
                if(usuario instanceof Cliente){ //Si el usuario encontrado es un cliente
                    encontrado = true;
                    Cliente cliente = (Cliente) usuario;
                    FichaCliente fc = cliente.obtenerFichaCliente();
                    
                    //"Numero de producto"
                    int nProducto = (int) formatearEntradaDecimal(UIMensajes.mC_LP_NumeroProducto());
                    Producto bProducto = obtenerProductos().obtenerProducto(nProducto, true);
                    
                    //Buscamos el producto de la coleccion del cliente
                    Producto producto = fc.obtenerProductoComprado(nProducto, true);
                    
                    if(producto!=null){ //Si se encuentra el producto
                        imprimirCaracteristicasProducto(bProducto);
                
                        //Dejamos constancia de la operacion realizada
                        obtenerUsuario().dejarConstancia(cliente, producto, obtenerCajero(), EnumOperaciones.mC_IMPRIMIRDATOSPRODUCTO,
                        obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
                    }else{ //Si NO se encuentra el producto
                        //"No se ha encontrado ningun producto con el numero "
                        System.out.println(UIMensajes.mC_AcP_ProductoNoEncontrado() +
                            nProducto);
                    }
                }
            }
            if(!encontrado){ //Si no se encuentra el cliente
                //"Cliente no encontrado"
                System.out.println(UIMensajes.mF_AD_ClienteNoEncontrado());
            }
        }
        
    }
    
    /**
     * Metodo auxiliar de imprimirListaProductos(...)
     */
    private void imprimirCaracteristicasProductoComprado(Usuarios usuarios){
        for(int i = 0; i < usuarios.obtenerTamano(); i++){
            Usuario tempUsuario = usuarios.obtenerUsuario(i);
            if(tempUsuario instanceof Cliente){ //Obtenemos un cliente
                Cliente cliente = (Cliente) tempUsuario;
                
                //Obtenemos la lista de productos que el cliente ha comprado
                ArrayList<Producto> productosComprados = Util.listaProductosComprados(cliente);
                Iterator<Producto> itr = productosComprados.iterator();
                while(itr.hasNext()){ //Por cada producto comprado
                    Producto tempProducto = itr.next();
                    System.out.println("\t" + UIMensajes.g_Nombre()
                        + ": " + tempUsuario.obtenerNombreUsuario() + " ");
                    imprimirCaracteristicasCompacta(tempProducto);
                }
                
            }
        }
    }
    
    /**
     * Imprime la lista de productos en la base de datos de productos 
     * seguida de la lista de productos comprados.
     * 
     * Numero de producto, Cantidad, Precio, Peso, Estado, Descripcion
     * 
     * @param productos Base de datos de productos
     * @param usuarios Base de datos de usuario
     */
    public void imprimirListaProductos(){
        System.out.println(UIMensajes.g_EncabezadoMenus());
        
        //"Lista de productos"
        System.out.println();
        System.out.println(UIMensajes.mC_OpcionListaProductos() + ": ");
        for(int i = 0; i < obtenerProductos().obtenerTamano(); i++){
            //Obtenemos un producto usando la posicion en la coleccion
            Producto temp = obtenerProductos().obtenerProducto(i, false);
            
            //Imprimir lista compacta con las caracteristicas de un producto
            imprimirCaracteristicasCompacta(temp);
        }
        
        //Ahora imprimimos los productos comprados por los clientes
        imprimirCaracteristicasProductoComprado(obtenerUsuarios());
        
        //Dejamos constancia de la operacion
        obtenerUsuario().dejarConstancia(obtenerCajero(), obtenerCajero(), EnumOperaciones.mC_IMPRIMIRLISTAPRODUCTOS,
        obtenerDiaActual(), obtenerMesActual(), obtenerAnoActual());
    }
    
    /**
     * Metodo auxiliar de imprimirListaProductos(...)
     * 
     * Imprime las caracteristicas de un producto de manera compacta
     */
    private void imprimirCaracteristicasCompacta(Producto producto){
        System.out.println(); //Primera linea
        //"Numero de producto"
        System.out.print("\t" + UIMensajes.mC_LP_NumeroProducto()
        + ": " + producto.obtenerNumeroProducto());
            
        //"Cantidad"
        System.out.print(" |" + UIMensajes.mC_AnP_Cantidad()
        + ": " + producto.obtenerCantidad());
        
        //"Precio"
        System.out.print(" |" + UIMensajes.mC_AnP_Precio() 
        + ": " + producto.obtenerPrecio());
        
        //"Precio"
        System.out.print(" |" + UIMensajes.mC_AnP_Peso()
        + ": " + producto.obtenerPeso());
        
        //"Estado" {INTACTO, ROTO, DEVUELTO}
        System.out.print(" |" + UIMensajes.mC_LP_Estado()
        + ": " + producto.obtenerEstadoProducto() + " ");
        
        System.out.println(); //Segunda linea
        //"Financiado"
        System.out.print("\t" + UIMensajes.mC_AcP_Financiado() + ": ");
        System.out.print(Util.booleanAPalabra(producto.obtenerEstadoFinanciado()));
        
        //"Fecha de compra"
        System.out.print(" |" + UIMensajes.mC_AcP_FechaCompra() + ": ");
        System.out.print(producto.obtenerDiaCompra() + "/");
        System.out.print(producto.obtenerMesCompra() + "/");
        System.out.print(producto.obtenerAnoCompra());
        System.out.print(" " + UIMensajes.mC_AnP_Dia());
        System.out.print("/" + UIMensajes.mC_AnP_Mes());
        System.out.print("/" + UIMensajes.mC_AnP_Ano());
        
        //Tiempo de garantia
        System.out.print(" |" + UIMensajes.mC_AcP_TiempoGarantia() + ": ");
        System.out.print(producto.obtenerTiempoGarantia());
        
        System.out.println(); //Tercera linea
        //"Numero de caja"
        System.out.print("\t" + UIMensajes.mC_ILP_NumeroCaja() + ": ");
        System.out.print(producto.obtenerNumeroCaja());
        
        //Caracteristicas especificas segun categoria
        caracteristicasEspecificasCompactas(producto);
        
        //Caracteristicas anadidas por usuario
        caracteristicasNuevasCompactas(producto);
        
        System.out.println(); //Ultima linea
        //"Descripcion"
        System.out.print("\t" + UIMensajes.mC_AnP_Descripcion()
        + ": " + producto.obtenerDescripcion() + " ");
    }
    
    /**
     * Metodo auxiliar de imprimirCaracteristicasCompacta(...)
     * 
     * Imprime caracteristicas especificas segun la categoria del producto
     */
    private void caracteristicasEspecificasCompactas(Producto producto){
        if(producto instanceof ProductoHogar){
            ProductoHogar ph = (ProductoHogar) producto;
            
            System.out.println();
            //"Ancho"
            System.out.print("\t" + UIMensajes.mC_ICE_Ancho() + ": ");
            System.out.print(ph.obtenerAncho());
            
            //"Alto"
            System.out.print(" |" + UIMensajes.mC_ICE_Alto() + ": ");
            System.out.print(ph.obtenerAlto());
            
            //"Consumo"
            System.out.print(" |" + UIMensajes.mC_ICE_Consumo() + ": ");
            System.out.print(ph.obtenerConsumo());
        }else if(producto instanceof ProductoSonido){
            ProductoSonido ps = (ProductoSonido) producto;
            
            //Util.booleanAPalabra toma una boolean y devuelve "Si" o "No"
            
            System.out.println();
            //"¿Es inalambrico?"
            System.out.print("\t" + UIMensajes.mC_ICE_Inalambrico() + ": ");
            System.out.print(Util.booleanAPalabra(ps.inalambrico()));
            
            //"¿Es resistente al agua?"
            System.out.print(" |" + UIMensajes.mC_ICE_ResistenteAgua() + ": ");
            System.out.print(Util.booleanAPalabra(ps.resistenteAgua()));
            
            //"¿Tiene bluetooth?"
            System.out.print(" |" + UIMensajes.mC_ICE_Bluetooth() + ": ");
            System.out.print(Util.booleanAPalabra(ps.bluetooth()));
            
            System.out.println();
            //"Frecuencia"
            System.out.print("\t" + UIMensajes.mC_ICE_Frecuencia() + ": ");
            System.out.print(ps.obtenerFrecuencia());
            
        }else if(producto instanceof ProductoInformatica){
            ProductoInformatica pi = (ProductoInformatica) producto;
            
            System.out.println();
            
            //"Frecuencia"
            System.out.print("\t" + UIMensajes.mC_ICE_Frecuencia() + ": ");
            System.out.print(pi.obtenerFrecuencia());
            
            //"Numero de nucleos"
            System.out.print(" |"+ UIMensajes.mC_ICE_NumeroNucleos() + ": ");
            System.out.print(pi.obtenerNumeroNucleos());
            
            //"Capacidad de almacenamiento"
            System.out.print(" |" + UIMensajes.mC_ICE_CapacidadAlmacenamiento() + ": ");
            System.out.print(pi.obtenerCapacidadAlmacenamiento());
            
        }else if(producto instanceof ProductoImagen){
            ProductoImagen pi = (ProductoImagen) producto;
            
            System.out.println();
            //"Pulgadas"
            System.out.print("\t" + UIMensajes.mC_ICE_Pulgadas() + ": ");
            System.out.print(pi.obtenerPulgadas());
            
            //"Ancho (Resolucion)"
            System.out.print(" |" + UIMensajes.mC_ICE_AnchoResolucion() + ": ");
            System.out.print(pi.obtenerAnchoResolucion());
            
            //"Alto (Resolucion)"
            System.out.print(" |" + UIMensajes.mC_ICE_AltoResolucion() + ": ");
            System.out.print(pi.obtenerAltoResolucion());
        }else if(producto instanceof ProductoTelefonia){
            ProductoTelefonia pt = (ProductoTelefonia) producto;
            
            System.out.println();
            //"¿Tiene bateria?"
            System.out.print("\t" + UIMensajes.mC_ICE_TieneBateria() + ": ");
            System.out.print(Util.booleanAPalabra(pt.tieneBateria()));
            
            //"¿Es inalambrico?"
            System.out.print(" |" + UIMensajes.mC_ICE_Inalambrico() + ": ");
            System.out.print(Util.booleanAPalabra(pt.esInalambrico()));
            
            //"Duracion"
            System.out.print(" |" + UIMensajes.mC_ICE_Duracion() + ": ");
            System.out.print(pt.obtenerDuracion());
        }
    }
    
    /**
     * Metodo auxiliar de imprimirCaracteristicasCompacta(...)
     * 
     * Imprime las caracteristicas anadidas por usuarios
     */
    private void caracteristicasNuevasCompactas(Producto producto){
        System.out.println();
        
        //Controla el numero de caracteristicas por linea
        int caracteristicasEnLinea = 1; 
        
        for(int i = 0; i < producto.obtenerNumCaracteristicas(); i++){
            Caracteristica temp = producto.obtenerCaracteristica(i);
            if(caracteristicasEnLinea==1){
                //Tabulamos la primera caracteristica de la linea
                System.out.print("\t");
            }
            System.out.print(temp.obtenerTitulo() + ": ");
            System.out.print(temp.obtenerDescripcion());
            if(caracteristicasEnLinea==3){ //Si hay 3 caracteristicas en linea
                //Resetear para tener 3 caracteristicas por linea
                caracteristicasEnLinea = 1;
            }else{
                System.out.print(" |");
                caracteristicasEnLinea++;
            }
        }
    }
    
	
	public UIMenuEmpleado obtenerMenu() {
		return new UIMenuEpdoCajero(this);
	}
}
