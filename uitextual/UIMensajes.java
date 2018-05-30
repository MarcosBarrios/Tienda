package uitextual;

/**
 * Contiene todos los mensajes utilizados en el programa almacenados
 * en metodos estaticos.
 * 
 * El proposito de esta clase es facilitar una posible traduccion del programa
 * textual.
 * 
 * NOTA: Con el sistema de anadido de nuevas categorias es necesario
 * traducir los enums ademas de esta clase.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMensajes{
    
    public static String g_CerrarSesion(){
        return "Cerrar Sesion";
    }
    
    //Sensible a la hora de obtener entradas. Utilizado en UIEntradas
    public static String g_Si(){
        return "Si";
    }
    
    //Sensible a la hora de obtener entradas. Utilizado en UIEntradas
    public static String g_No(){
        return "No";
    }
    
    public static String g_FueraRango(){
        return "Fuera de rango";
    }
    
    public static String g_EI_EsperabaNumero(){
        return "Entrada no valida. (Se esperaba numero)";
    }
    
    public static String g_EI_FueraRango(){
        return "Entrada no valida. (Fuera de rango) ";
    }
    
    public static String g_EI_ValorBooleanoIncorrecto(){
        return "Entrada no valida. (Se esperaba true o false)";
    }
    
    public static String g_EI_CadenaNoIncluida(){
        return "Entrada no valida. (Cadena no incluida)";
    }
    
    public static String g_EncabezadoMenus(){
        return "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
    }
    
    public static String g_OpcionSalir(){
        return "Salir del programa";
    }
    
    public static String g_ElegirOpcionMenu(){
        return "Elija una de las opciones escribiendo el nÂº de opcion a continuacion";
    }
    
    public static String g_EscribirUsuarioContrasena(){
        return "Escriba su usuario y contrasena a continuacion";
    }
    
    public static String g_DNI(){
        return "DNI";
    }
    
    public static String g_Nombre(){
        return "Nombre";
    }
    
    public static String g_Email(){
        return "Email";
    }
    
    public static String g_Domicilio(){
        return "Domicilio";
    }
    
    public static String g_Telefono(){
        return "Telefono";
    }
    
    public static String g_Usuario(){
        return "Usuario";
    }
    
    public static String g_Contrasena(){
        return "Contrasena";
    }
    
    public static String g_TipoUsuario() {
    	return "Tipo usuario: ";
    }
    
    //*********************************************************************
    
    public static String b_OpcionBuscarProductos(){
        return "Buscar productos";
    }
    
    public static String b_OpcionBuscarUsuarios(){
        return "Buscar usuarios";
    }
    
    public static String b_IndicarDatos(){
        return "Indicar a continuacion los datos de busqueda";
    }
    
    public static String b_ValoresPredeterminados(){
        return "No escribir nada para no incluir un dato textual en la busqueda";
    }
    
    public static String b_ValoresNegativos(){
        return "Escribir -1 para no incluir un dato numerico en la busqueda";
    }
    
    public static String b_BuscarEnCliente(){
        return "Buscar en clientes?";
    }
    
    public static String b_BuscandoEnClientes(){
        return "Buscando en clientes";
    }
    
    public static String b_BuscandoEnBaseDeDatos(){
        return "Buscando en la base de datos de la tienda";
    }
    
    //*********************************************************************
    
    public static String mT_OpcionAnadirReporte(){
        return "Anadir reporte a un producto";
    }
    
    public static String mT_OpcionVerFichas(){
        return "Ver mis fichas de reparacion";
    }
    
    public static String mT_OpcionAnadirPieza(){
        return "Anadir una pieza a la lista de piezas necesarias";
    }
    
    public static String mT_OpcionEliminarPieza(){
        return "Eliminar una pieza de la lista de piezas necesarias";
    }
    
    public static String mT_OpcionVerEstadoProducto(){
        return "Ver el estado de un producto en reparacion";
    }
    
    public static String mT_OpcionVerPiezasNecesarias(){
        return "Ver piezas necesarias";
    }
    
    public static String mT_VLR_DatosTecnico(){
        return "Introducir el nombre, email o DNI del tecnico";
    }
    
    public static String mT_AR_Coste(){
        return "Coste";
    }
    
    public static String mTdia_AR_Pagado(){
        return "Pagado";
    }
    
    public static String mT_AP_NombrePieza(){
        return "Nombre de la pieza";
    }
    
    public static String mT_AP_DescripcionPieza(){
        return "Descripcion de la pieza";
    }
    
    public static String mT_AP_PrecioPieza(){
        return "Precio de la pieza";
    }
    
    public static String mT_EP_PiezaEliminada(){
        return "Pieza eliminada";
    }
    
    public static String mT_AR_ReporteAnadido() {
    	return "Reporte anadido con exito";
    }
    
    public static String mT_VEP_ReporteNoEncontrado() {
    	return "No se ha encontrado el reporte";
    }
    
    public static String mT_EP_PiezaNoEncontrada(){
        return "No se encontro la pieza";
    }
    
    public static String mT_CEP_GenerarFactura(){
        return "Generar factura al cliente?";
    }
    
    public static String mT_CEP_ProcesoCompletado(){
        return "El producto ya se ha pagado";
    }
    
    //*********************************************************************
    
    public static String mPV_OpcionRepararProducto(){
        return "Reparar un producto";
    }
    
    public static String mPV_OpcionDevolverProducto(){
        return "Devolver un producto";
    }
    
    public static String mPV_OpcionComprobarEstadoProducto(){
        return "Comprobar el estado de un producto";
    }
    
    public static String mPV_RP_OperacionExitosa() {
    	return "Se ha anadido el producto para reparacion";
    }
    
    public static String mPV_RP_OperacionFallida() {
    	return "Tecnico, cliente o producto no encontrado";
    }
    
    public static String mPV_RP_ProductoArreglado(){
        return "El producto se ha arreglado con exito y esta a espera de pago";
    }
    
    public static String mPV_RP_GarantiaCaducada(){
        return "El producto no dispone de garantia";
    }
    
    public static String mPV_RP_DescripcionProblema(){
        return "Descripcion del problema";
    }
    
    public static String mPV_RP_TecnicoNoEncontrado(){
        return "No se ha encontrado ningun tecnico con el dni especificado." +
        		" Tambien es posible que no hayan fichas de reparacion asignadas" + 
        		"al tecnico encontrado.";
    }
    
    public static String mPV_RP_AsignarTecnico(){
        return "Nombre, email o DNI del tecnico encargado";
    }
    
    public static String mPV_RP_ProductoPagado() {
    	return "Producto reparado y reparacion pagada";
    }
    
    public static String mPV_RP_CumpleGarantia(){
        return "El producto mantiene la garantia. Precio 0 asegurado.";
    }
    
    public static String mPV_RP_NoCumpleGarantia(){
        return "El producto no mantiene la garantia. Es necesario realizar un pago";
    }
    
    public static String mPV_CEP_ReporteNoEncontrado() {
    	return "No se ha encontrado ningun reporte con los datos especificados";
    }
    
    public static String mPV_CEP_ProductoNoReparado() {
    	return "El producto todavia no ha sido reparado";
    }
    
    public static String mPV_DP_IndicarCliente(){
        return "Escriba a continuacion el DNI del cliente";
    }
    
    public static String mPV_DP_IntroducirFecha() {
		return "Indique el dia, mes y año actual";
	}
    
    public static String mPV_DP_DevolucionAceptada(){
        return "Se ha devuelto el producto con exito";
    }
    
    public static String mPV_DP_DevolucionFallida() {
    	return "No se ha encontrado el producto o el cliente o"+
    			"El producto ha sido comprado hace mas de 3 meses";
    }
    
    public static String mT_AR_Pagado(){
       return "Pagado";
    }
    
    //*********************************************************************
    
    public static String mGU_OpcionAnadirEmpleado(){
        return "Anadir empleado a la base de datos";
    }
    
    public static String mGU_OpcionActualizarDatos(){
        return "Actualizar los datos de un empleado";
    }
    
    public static String mGU_OpcionVerListaEmpleados(){
        return "Ver lista de empleados";
    }
    
    public static String mGU_OpcionVerListaClientes(){
        return "Ver lista de clientes";
    }
    
    public static String mGU_OpcionVerFichasReparacion(){
        return "Ver lista de fichas de reparacion";
    }
    
    public static String mGU_OpcionAnadirSolicitud(){
        return "Anadir solicitud a la ficha de un cliente";
    }
    
    public static String mGU_OpcionAceptarSolicitud(){
        return "Aceptar una solicitud de un cliente";
    }
    
    public static String mGU_OpcionVerSolicitudes(){
        return "Ver una lista de solicitudes de todos los clientes";
    }
    
    public static String mGU_OpcionVerHistorialUsuario(){
        return "Ver el historial de operaciones de un usuario";
    }
    
    public static String mGU_EspecificarDatos(){
        return "Especificar a continuacion el nombre, email, usuario" +
            "y la contrasena del empleado a registrar";
    }
    
    public static String mGU_AcE_EmpleadoActualizado(){
        return "Empleado actualizado con exito";
    }
    
    public static String mGU_AnE_EmpleadoRegistrado(){
        return "Empleado registrado con exito";
    }
    
    public static String mGU_AnE_UsuarioYaExistente(){
        return "Ya existe un usuario con los datos especificados";
    }
    
    public static String mGU_AnE_EspecificarTipoEmpleado(){
        return "Tipo de empleado?";
    }
    
    public static String mGU_AnE_Cliente() {
    	return "Cliente";
    }
    
    public static String mGU_AnE_EmpleadoNoEncontrado(){
        return "Empleado no encontrado";
    }
    
    public static String mGU_AnE_Cajero(){
        return "Cajero";
    }
    
    public static String mGU_AnE_Financiacion(){
        return "Financiacion";
    }
    
    public static String mGU_AnE_GestorUsuarios() {
    	return "Gestor usuarios";
    }
    
    public static String mGU_AnE_Tecnico(){
        return "Tecnico";
    }
    
    public static String mGU_AnE_Comercial(){
        return "Comercial";
    }
    
    public static String mGU_AnE_PostVenta(){
        return "PostVenta";
    }
    
    public static String mGU_AS_SolicitudAnadida(){
        return "Solicitud anadida con exito";
    }
    
    public static String mGU_AS_IndicarNumeroSolicitud(){
        return "Indicar el numero de la solicitud a aceptar";
    }
    
    public static String mGU_VLS_SolicitudAceptada(){
        return "Solicitud aceptada";
    }
    
    public static String mGU_VLS_SolicitudPendiente(){
        return "Solicitud pendiente";
    }

    public static String mGU_VHU_IntroducirDNIUsuario(){
        return "Introducir el DNI del usuario";
    }
    
    public static String mGU_VHU_EmpleadoResponsable(){
        return "Responsable";
    }
    
    public static String mGU_VHU_ClienteAfectado(){
        return "Afectado";
    }
    
    public static String mGU_VHU_FechaOperacion(){
        return "Fecha de la operacion";
    }
    
    //*********************************************************************
    
    public static String mF_OpcionDarAlta(){
        return "Dar de alta a un cliente";
    }
    
    public static String mF_OpcionActualizarDatos(){
        return "Actualizar datos de un cliente";
    }
    
    public static String mF_OpcionVerDatosCliente(){
        return "Ver los datos de un cliente";
    }
    
    public static String mF_OpcionVerFacturasCliente(){
        return "Ver las facturas de un cliente";
    }
    
    public static String mF_DA_EspecificarDatosCliente(){
        return "Indicar a continuacion los datos del cliente a registrar";
    }
    
    public static String mF_DA_UsuarioYaRegistrado(){
        return "Ya existe un usuario registrado con el DNI especificado, registro fallido";
    }
    
    public static String mF_DA_NombreAceptado(){
        return "Nombre aceptado";
    }
    
    public static String mF_DA_EmailAceptado(){
        return "Email aceptado";
    }
    
    public static String mF_DA_DomicilioAceptado(){
        return "Domicilio aceptado";
    }
    
    public static String mF_DA_TelefonoAceptado(){
        return "Telefono aceptado";
    }
    
    public static String mF_DA_EscribirDNI(){
        return "Escribir DNI";
    }
    
    public static String mF_DA_RegistradoExito(){
        return "Se ha registrado el cliente con exito";
    }
    
    public static String mF_ADC_ClienteActualizado() {
    	return "Se ha actualizado el cliente con exito";
    }
    
    public static String mF_AD_IndicarDNICliente(){
        return "Indique a continuacion el DNI del cliente";
    }
    
    public static String mF_AD_ClienteNoEncontrado(){
        return "Cliente no encontrado";
    }
    
    public static String mF_AD_Nombre(){
        return "Nombre";
    }
    
    public static String mF_AD_Email(){
        return "Email";
    }
    
    public static String mF_AD_DatosActualizados(){
        return "Se han actualizado los datos del cliente";
    }
    
    //*********************************************************************
    
    public static String mP_OpcionIniciarSesion(){
        return "Iniciar Sesion";
    }
    
    public static String mP_OpcionGestionarUsuarios(){
        return "Gestionar usuarios";
    }

    public static String mP_FalloLogin(){
        return "Usuario o contrasena incorrectos";
    }
    
    public static String mP_ExitoLogin(){
        return "Se ha entrado a la cuenta con exito";
    }
    
    public static String mP_ContrasenaIncorrecta(){
        return "Contrasena incorrecta";
    }
    
    //*********************************************************************
    
    public static String mC_mAP_CambiosAplicados() {
    	return "Se han aplicado los cambios al producto";
    }
    
    //*********************************************************************
    public static String mC_OpcionVenderProducto(){
        return "Vender un producto a un cliente";
    }
    
    public static String mC_OpcionAnadirProducto(){
        return "Anadir un producto";
    }
    
    public static String mC_OpcionActualizarProducto(){
        return "Actualizar producto";
    }
    
    public static String mC_OpcionVerDatosProducto(){
        return "Ver las caracteristicas de un producto";
    }
    
    public static String mC_OpcionListaProductos(){
        return "Lista de productos";
    }
    
    public static String mC_ITP_CategoriaProducto(){
        return "Categoria del producto";
    }
    
    public static String mC_ICE_Ancho(){
        return "Ancho";
    }
    
    public static String mC_ICE_Alto(){
        return "Alto";
    }
    
    public static String mC_ICE_Consumo(){
        return "Consumo";
    }
    
    public static String mC_ICE_Inalambrico(){
        return "Es inalambrico?";
    }
    
    public static String mC_ICE_ResistenteAgua(){
        return "Es resistente al agua?";
    }
    
    public static String mC_ICE_Bluetooth(){
        return "Tiene bluetooth?";
    }
    
    public static String mC_ICE_Frecuencia(){
        return "Frecuencia";
    }
    
    public static String mC_ICE_NumeroNucleos(){
        return "Numero de nucleos";
    }
    
    public static String mC_ICE_CapacidadAlmacenamiento(){
        return "Capacidad de almacenamiento";
    }
    
    public static String mC_ICE_TieneBateria(){
        return "Tiene bateria?";
    }
    
    public static String mC_ICE_Duracion(){
        return "Duracion";
    }
    
    public static String mC_ICE_Pulgadas(){
        return "Pulgadas";
    }
    
    public static String mC_ICE_AnchoResolucion(){
        return "Ancho (Resolucion)";
    }
    
    public static String mC_ICE_AltoResolucion(){
        return "Alto (Resolucion)";
    }
    
    public static String mC_VP_SinStock(){
        return "No se ha encontrado el cliente, el producto o" +
        		"no hay unidades del producto almacenadas";
    }
    
    public static String mC_VP_VendidoExito() {
    	return "Se ha vendido el producto con exito";
    }
    
    public static String mC_AnP_ProcederIntroduccion(){
        return "A continuacion se va a proceder a introducir" +
            " las caracteristicas del producto. ";
    }
    
    public static String mC_AnP_ProductoAnadido() {
    	return "Producto anadido a la base de datos con exito";
    }

    public static String mC_AnP_Precio(){
        return "Precio (en euros)";
    }
    
    public static String mC_AnP_Descripcion(){
        return "Descripcion";
    }
    
    public static String mC_AnP_Peso(){
        return "Peso (en kg) ";
    }
    
    public static String mC_AnP_Cantidad(){
        return "Cantidad";
    }
    
    public static String mC_AnP_IntroducirFechaCompra(){
        return "Introducir a continuacion la fecha de compra";
    }
    
    public static String mC_AnP_Dia(){
        return "Dia";
    }
    
    public static String mC_AnP_Mes(){
        return "Mes";
    }
    
    public static String mC_AnP_Ano(){
        return "Ano";
    }
    
    public static String mC_AnP_ElegirCategoriaProducto(){
        return "Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) ";
    }
    
    public static String mC_AnP_CategoriaSeleccionada(){
        return "Categoria seleccionada correctamente";
    }
    
    public static String mC_AnP_CategoriaInexistente(){
        return "Categoria inexistente, por favor vuelva a intentarlo ";
    }
    
    //Sensible a la hora de anadir un producto
    public static String mC_AnP_Sonido(){
        return "Sonido";
    }
    
    //Sensible a la hora de anadir un producto
    public static String mC_AnP_Hogar(){
        return "Hogar";
    }
    
    //Sensible a la hora de anadir un producto
    public static String mC_AnP_Telefonia(){
        return "Telefonia";
    }
    
    //Sensible a la hora de anadir un producto
    public static String mC_AnP_Imagen(){
        return "Imagen";
    }
    
    //Sensible a la hora de anadir un producto
    public static String mC_AnP_Informatica(){
        return "Informatica";
    }
    
    public static String mC_AcP_NombreDNIEmailCliente(){
        return "Especificar el nombre, email o dni del cliente";
    }
    
    public static String mC_AcP_EncontradoEnCliente(){
        return "Se ha encontrado un producto igual de ";
    }
    
    public static String mC_AcP_BuscarCliente(){
        return "El producto pertenece a un cliente? (si/no)";
    }
    
    public static String mC_AcP_ProductoNoEncontrado(){
        return "No se ha encontrado ningun producto con el numero ";
    }
    
    public static String mC_AcP_ProductoEncontrado(){
        return "Producto encontrado. Numero de producto = ";
    }
    
    public static String mC_AcP_Financiado(){
        return "Financiado";
    }
    
    public static String mC_AnP_Financiar(){
        return "Financiar producto?";
    }
    
    public static String mC_AnP_DescripcionFactura(){
        return "Descripcion de la factura";
    }
    
    public static String mC_AcP_FechaCompra(){
        return "Fecha de compra";
    }
    
    public static String mC_AcP_SiNoBuscarEnCliente() {
    	return "Si el producto a actualizar no pertenece a un cliente el"
    			+ "campo DNI no será utilizado por lo que su valor no importa.";
    }
    
    public static String mC_AcP_Titulo(){
        return "Titulo";
    }
    
    public static String mC_Acp_CaracteristicaTituloDescripcion(){
        return "Indique a continuacion el titulo de la caracteristica y su descripcion";
    }
    
    public static String mC_AcP_Exito(){
        return "Se ha actualizado el producto con exito";
    }
    
    public static String mC_AcP_TiempoGarantia(){
        return "Tiempo de garantia";
    }
    
    public static String mC_AcP_AnadirCaracteristica(){
        return "Anadir caracteristica al producto";
    }

    public static String mC_VDP_IntroducirNumeroProducto(){
        return "Introduzca a continuacion el numero del producto";
    }
    
    public static String mC_ILP_NumeroCaja(){
        return "Numero de caja";
    }
    
    public static String mC_LP_Estado(){
        return "Estado";
    }
    
    public static String mC_LP_NumeroProducto(){
        return "Numero de producto";
    }
    
    public static String mC_EspecificarNumeroCaja(){
        return "Especificar el numero de la caja desde la que se va a operar";
    }

    //*********************************************************************
}
