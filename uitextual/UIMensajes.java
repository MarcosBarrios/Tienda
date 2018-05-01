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
    
    public static String entradaIncorrectaEntero(){
        return "Por favor introduzca el numero de la opcion (Se esperaba numero)";
    }
    
    public static String entradaIncorrectaBooleana(){
        return "Entrada no valida. Intentelo de nuevo";
    }
    
    public static String menuEncabezado(){
        return "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
    }
    
    public static String menuFinanciacionActualizarDatos(){
        return "Indique a continuacion el nombre o email del cliente";
    }
    
    public static String menuFinanciacionDarAlta(){
        return "Indique a continuacion el nombre e email del cliente a registrar";
    }
    
    public static String menuFinanciacionDarAltaNombreExito(){
        return "Nombre aceptado";
    }
    
    public static String menuFinanciacionDarAltaEmailExito(){
        return "Email aceptado";
    }
    
    public static String menuFinanciacionActualizarDatosElegir(){
        return "¿Que desea modificar?";
    }
    
    public static String menuFinanciacionActualizarDatosNombre(){
        return "Nombre";
    }
    
    public static String menuFinanciacionActualizarDatosEmail(){
        return "Email";
    }
    
    public static String menuFinanciacionActualizarDatosUsuarioNoEncontrado(){
        return "Cliente no encontrado";
    }
    
    public static String menuFinanciacionDarAltaExito(){
        return "Se ha registrado el usuario con exito";
    }
    
    public static String menuFinanciacionActualizarDatosExito(){
        return "Se han actualizado los datos del cliente";
    }
    
    public static String menuFinanciacionOpcionActualizarDatos(){
        return "Actualizar datos de un cliente";
    }
    
    public static String menuFinanciacionOpcionDarAlta(){
        return "Dar de alta a un cliente";
    }
    
    public static String menuFinanciacionOpcionDatosCliente(){
        return "Ver los datos de un cliente";
    }
    
    public static String menuPrincipalOpcionIniciarSesion(){
        return "Iniciar Sesion";
    }
    
    public static String menuPrincipalOpcionListaUsuarios(){
        return "Lista de usuarios";
    }
    
    public static String menuPrincipalOpcionSalir(){
        return "Salir del programa";
    }
    
    public static String menuPrincipalLoginFallido(){
        return "Usuario o contraseña incorrectos";
    }
    
    public static String menuPrincipalLoginExito(){
        return "Bienvenido";
    }
    
    public static String menuCajeroOpcionListaProductos(){
        return "Lista de productos";
    }
    
    public static String menuCajeroOpcionActualizarProductoNoEncontrado(){
        return "No se ha encontrado ningun producto con el numero";
    }
    
    public static String menuCajeroOpcionVerDatosProducto(){
        return "Ver las caracteristicas de un producto";
    }
    
    public static String menuCajeroOpcionActualizarProductoEncontrado(){
        return "Producto encontrado. Numero de producto= ";
    }
    
    public static String menuCajeroOpcionActualizarProductoFinanciado(){
        return "Financiado";
    }
    
    public static String menuCajeroOpcionActualizarProductoFechaCompra(){
        return "Fecha de compra";
    }
    
    public static String menuCajeroOpcionCrearProducto(){
        return "Añadir un producto";
    }
    
    public static String menuCajeroOpcionDatosProducto(){
        return "Introduzca a continuacion el numero del producto";
    }
    
    public static String menuElegirOpcion(){
        return "Elija una de las opciones escribiendo el nº de opcion a continuacion";
    }
    
    public static String menuEntradaIncorrecta(){
        return "Entrada Incorrecta. Por favor, vuelva a intentarlo";
    }
    
    public static String opcionCrearProductoInicio(){
        return "A continuacion se va a proceder a introducir" +
            " las caracteristicas del producto. ";
    }
    
    public static String opcionCrearProductoPrecio(){
        return "Precio (en euros)";
    }
    
    public static String opcionCrearProductoDescripcion(){
        return "Descripcion";
    }
    
    public static String opcionCrearProductoPeso(){
        return "Peso (en kg) ";
    }
    
    public static String opcionCrearProductoCantidad(){
        return "Cantidad";
    }
    
    public static String opcionCrearProductoFechaCompra(){
        return "Introducir a continuacion la fecha de compra";
    }
    
    public static String opcionCrearProductoDiaCompra(){
        return "Dia";
    }
    
    public static String opcionCrearProductoMesCompra(){
        return "Mes";
    }
    
    public static String opcionCrearProductoAñoCompra(){
        return "Año";
    }
    
    public static String menuCajeroOpcionListaProductosDescripcion(){
        return "Descripcion";
    }
    
    public static String menuCajeroOpcionListaProductosEstado(){
        return "Estado";
    }
    
    public static String menuCajeroOpcionListaProductosNumeroProducto(){
        return "Numero de producto";
    }
    
    public static String menuCajeroOpcionActualizarProductoTituloCaracteristica(){
        return "Titulo";
    }
    
    public static String menuCajeroOpcionActualizarProductoDescripcionCaracteristica(){
        return "Descripcion";
    }
    
    public static String menuCajeroOpcionActualizarProductoCaracteristica(){
        return "Indique a continuacion el titulo de la caracteristica y su descripcion";
    }
    
    public static String menuCajeroOpcionActualizarProductoExito(){
        return "Se ha actualizado el producto con exito";
    }
    
    public static String menuCajeroOpcionActualizarProducto(){
        return "Actualizar producto";
    }
    
    public static String menuCajeroOpcionActualizarProductoTiempoGarantia(){
        return "Tiempo de garantia";
    }
    
    public static String menuCajeroOpcionActualizarProductoNuevaCaracteristica(){
        return "Añadir caracteristica al producto";
    }
    
    public static String menuCajeroOpcionActualizarProductoElegirOpcion(){
        return "Elegir opcion a modificar";
    }
    
    public static String opcionCrearProductoCategoria(){
        return "Categoria del producto (Sonido|Hogar|Telefonia|Imagen|Informatica) ";
    }
    
    public static String opcionCrearProductoCategoriaCorrecta(){
        return "Categoria seleccionada correctamente";
    }
    
    public static String opcionCrearProductoCategoriaIncorrecta(){
        return "Categoria inexistente, por favor vuelva a intentarlo ";
    }
    
    //Sensible a la hora de crear un producto
    public static String opcionCrearProductoCategoriaSonido(){
        return "Sonido";
    }
    
    //Sensible a la hora de crear un producto
    public static String opcionCrearProductoCategoriaHogar(){
        return "Hogar";
    }
    
    //Sensible a la hora de crear un producto
    public static String opcionCrearProductoCategoriaTelefonia(){
        return "Telefonia";
    }
    
    //Sensible a la hora de crear un producto
    public static String opcionCrearProductoCategoriaImagen(){
        return "Imagen";
    }
    
    //Sensible a la hora de crear un producto
    public static String opcionCrearProductoCategoriaInformatica(){
        return "Informatica";
    }
    
    public static String informacionNombre(){
        return "Nombre";
    }
    
    public static String informacionEmail(){
        return "Email";
    }
    
    public static String loginInformacion(){
        return "Escriba su usuario y contraseña a continuacion";
    }
    
    public static String loginUsuario(){
        return "Usuario";
    }
    
    public static String loginContraseña(){
        return "Contraseña";
    }
    
}
