package uitextual;

/**
 * Contiene todos los mensajes utilizados en el programa almacenados
 * en metodos estaticos.
 * 
 * El proposito de esta clase es facilitar una posible traduccion del programa
 * textual.
 * 
 * NOTA: Con el sistema de añadido de nuevas categorias es necesario
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
        return "Elija una de las opciones escribiendo el nº de opcion a continuacion";
    }
    
    public static String g_EscribirUsuarioContraseña(){
        return "Escriba su usuario y contraseña a continuacion";
    }
    
    public static String g_Nombre(){
        return "Nombre";
    }
    
    public static String g_Email(){
        return "Email";
    }
    
    public static String g_Usuario(){
        return "Usuario";
    }
    
    public static String g_Contraseña(){
        return "Contraseña";
    }
    //*********************************************************************
    
    public static String mT_OpcionAñadirReporte(){
        return "Añadir reporte a un producto";
    }
    
    public static String mT_OpcionVerFichas(){
        return "Ver mis fichas de reparacion";
    }
    
    public static String mT_VLR_DatosTecnico(){
        return "Introducir el nombre, email o DNI del tecnico";
    }
    
    public static String mT_AR_Coste(){
        return "Coste";
    }
    
    //*********************************************************************
    
    public static String mPV_OpcionRepararProducto(){
        return "Reparar un producto";
    }
    
    public static String mPV_OpcionDevolverProducto(){
        return "Devolver un producto";
    }
    
    public static String mPV_RP_GarantiaCaducada(){
        return "El producto no dispone de garantia";
    }
    
    public static String mPV_RP_DescripcionProblema(){
        return "Descripcion del problema";
    }
    
    public static String mPV_RP_TecnicoNoEncontrado(){
        return "Tecnico no encontrado";
    }
    
    public static String mPV_RP_AsignarTecnico(){
        return "Nombre, email o DNI del tecnico encargado";
    }
    
    public static String mPV_RP_CumpleGarantia(){
        return "El producto mantiene la garantia. Precio 0 asegurado.";
    }
    
    public static String mPV_RP_NoCumpleGarantia(){
        return "El producto no mantiene la garantia. Es necesario realizar un pago";
    }
    
    public static String mPV_DP_IndicarCliente(){
        return "Escriba a continuacion el DNI del cliente";
    }
    
    public static String mPV_DP_IntroducirFecha(){
        return "Indique el dia, mes y año actual";
    }
    
    public static String mPV_DP_DevolucionAceptada(){
        return "Se ha devuelto el producto con exito";
    }
    
    public static String mPV_DP_DevolucionRechazada(){
        return "El producto se ha comprado hace mas de 3 meses, devolucion rechazada";
    }
    //*********************************************************************
    
    public static String mGU_OpcionAñadirEmpleado(){
        return "Añadir empleado al sistema";
    }
    
    public static String mGU_OpcionActualizarDatos(){
        return "Actualizar los datos de un empleado";
    }
    
    public static String mGU_OpcionVerListaEmpleados(){
        return "Ver lista de empleados";
    }
    
    public static String mGU_EspecificarDatos(){
        return "Especificar a continuacion el nombre, email, usuario" +
            "y la contraseña del empleado a registrar";
    }
    
    public static String mGU_AcE_EmpleadoActualizado(){
        return "Empleado actualizado con exito";
    }
    
    public static String mGU_AñE_EmpleadoRegistrado(){
        return "Empleado registrado con exito";
    }
    
    public static String mGU_AñE_EspecificarTipoEmpleado(){
        return "¿Tipo de empleado?";
    }
    
    public static String mGU_AñE_EmpleadoNoEncontrado(){
        return "Empleado no encontrado";
    }
    
    public static String mGU_AñE_Cajero(){
        return "Cajero";
    }
    
    public static String mGU_AñE_Financiacion(){
        return "Financiacion";
    }
    
    public static String mGU_AñE_Tecnico(){
        return "Tecnico";
    }
    
    public static String mGU_AñE_Comercial(){
        return "Comercial";
    }
    
    public static String mGU_AñE_PostVenta(){
        return "PostVenta";
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
    
    public static String mF_DA_IndicarNombreEmail(){
        return "Indique a continuacion el nombre e email del cliente a registrar";
    }
    
    public static String mF_DA_NombreAceptado(){
        return "Nombre aceptado";
    }
    
    public static String mF_DA_EmailAceptado(){
        return "Email aceptado";
    }
    
    public static String mF_DA_RegistradoExito(){
        return "Se ha registrado el cliente con exito";
    }
    
    public static String mF_AD_IndicarNombreEmail(){
        return "Indique a continuacion el nombre o email del cliente";
    }
    
    public static String mF_AD_ClienteNoEncontrado(){
        return "Cliente no encontrado";
    }
    
    public static String mF_AD_QueModificar(){
        return "¿Que desea modificar?";
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
    
    public static String mP_OpcionListaUsuarios(){
        return "Lista de usuarios";
    }
    
    public static String mP_OpcionGestionarUsuarios(){
        return "Gestionar usuarios";
    }

    public static String mP_FalloLogin(){
        return "Usuario o contraseña incorrectos";
    }
    
    public static String mP_ExitoLogin(){
        return "Se ha entrado a la cuenta con exito";
    }
    
    public static String mP_ContraseñaIncorrecta(){
        return "Contraseña incorrecta";
    }
    
    //*********************************************************************
    
    public static String mC_OpcionVenderProducto(){
        return "Vender un producto a un cliente";
    }
    
    public static String mC_OpcionAñadirProducto(){
        return "Añadir un producto";
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
    
    public static String mC_VP_SinStock(){
        return "No quedan reservas del producto especificado";
    }
    
    public static String mC_AñP_ProcederIntroduccion(){
        return "A continuacion se va a proceder a introducir" +
            " las caracteristicas del producto. ";
    }
    
    public static String mC_AñP_Precio(){
        return "Precio (en euros)";
    }
    
    public static String mC_AñP_Descripcion(){
        return "Descripcion";
    }
    
    public static String mC_AñP_Peso(){
        return "Peso (en kg) ";
    }
    
    public static String mC_AñP_Cantidad(){
        return "Cantidad";
    }
    
    public static String mC_AñP_IntroducirFechaCompra(){
        return "Introducir a continuacion la fecha de compra";
    }
    
    public static String mC_AñP_Dia(){
        return "Dia";
    }
    
    public static String mC_AñP_Mes(){
        return "Mes";
    }
    
    public static String mC_AñP_Año(){
        return "Año";
    }
    
    public static String mC_AñP_ElegirCategoriaProducto(){
        return "Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) ";
    }
    
    public static String mC_AñP_CategoriaSeleccionada(){
        return "Categoria seleccionada correctamente";
    }
    
    public static String mC_AñP_CategoriaInexistente(){
        return "Categoria inexistente, por favor vuelva a intentarlo ";
    }
    
    //Sensible a la hora de añadir un producto
    public static String mC_AñP_Sonido(){
        return "Sonido";
    }
    
    //Sensible a la hora de añadir un producto
    public static String mC_AñP_Hogar(){
        return "Hogar";
    }
    
    //Sensible a la hora de añadir un producto
    public static String mC_AñP_Telefonia(){
        return "Telefonia";
    }
    
    //Sensible a la hora de añadir un producto
    public static String mC_AñP_Imagen(){
        return "Imagen";
    }
    
    //Sensible a la hora de añadir un producto
    public static String mC_AñP_Informatica(){
        return "Informatica";
    }
    
    public static String mC_AcP_NombreDNIEmailCliente(){
        return "Especificar el nombre, email o dni del cliente";
    }
    
    public static String mC_AcP_EncontradoEnCliente(){
        return "Se ha encontrado un producto igual de ";
    }
    
    public static String mC_AcP_BuscarCliente(){
        return "¿El producto pertenece a un cliente? (si/no)";
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
    
    public static String mC_AcP_FechaCompra(){
        return "Fecha de compra";
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
    
    public static String mC_AcP_AñadirCaracteristica(){
        return "Añadir caracteristica al producto";
    }
    
    public static String mC_AcP_ElegirOpcion(){
        return "Elegir opcion a modificar";
    }
    
    public static String mC_VDP_IntroducirNumeroProducto(){
        return "Introduzca a continuacion el numero del producto";
    }
    
    public static String mC_LP_Estado(){
        return "Estado";
    }
    
    public static String mC_LP_NumeroProducto(){
        return "Numero de producto";
    }
    
    //*********************************************************************
}
