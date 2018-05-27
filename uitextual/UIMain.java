package uitextual;

import productos.*;
import backend.*;

/**
 * Clase encargada de implementar la interaccion usuario del programa - ordenador
 * en forma de texto.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMain{
    
	//Bases de datos de los productos y usuarios
	private Productos productos;
	private Usuarios usuarios;
	
    public UIMain(){
        productos = new Productos();
        usuarios = new Usuarios();
        
        //Preguntamos por la fecha actual al iniciar el programa
        System.out.println("\t" + UIMensajes.mPV_DP_IntroducirFecha() + ": ");
        System.out.println("\t" + UIMensajes.mC_AnP_Dia() + ": ");
        int dia = (int) UIEntradas.obtenerDecimal(1, 31);
        System.out.println("\t" + UIMensajes.mC_AnP_Mes() + ": ");
        int mes = (int) UIEntradas.obtenerDecimal(1, 12);
        System.out.println("\t" + UIMensajes.mC_AnP_Ano() + ": ");
        int ano = (int) UIEntradas.obtenerDecimal(2018, 2100);
        
        //Añadimos los empleados base a la base de usuarios
        añadirEmpleados(dia, mes, ano);
        
        //Activamos el menu principal
        UIMenuAccionable menuPrincipal = new UIMenuPrincipal(usuarios.obtenerLogeador(),
        		dia, mes, ano);
        menuPrincipal.activar();
    }
    
    /**
     * Añade los empleados base a la base de datos de usuarios de la tienda
     * 
     * @param diaActual
     * @param mesActual
     * @param anoActual
     */
    private void añadirEmpleados(int diaActual, int mesActual, int anoActual) {
        Usuario cajeroPrueba = new EpdoCajero(usuarios, productos, 
        		diaActual, mesActual, anoActual, "11111111A", "Marcos Barrios", 
        		"marcosloscardones@gmail.com", "unclainC", "1234");
        Usuario financiadorPrueba = new EpdoFinanciacion(usuarios, productos, 
        		diaActual, mesActual, anoActual, "11111112A", "Adrian Barrera", 
        		"adrian.zorro@gmail.com", "unclainF", "1234");
        Usuario postventaPrueba = new EpdoPostVenta(usuarios, productos, 
        		diaActual, mesActual, anoActual, "11111112A", "Postvento Barrera", 
        		"postventotolerante@gmail.com", "unclainPV", "1234");
        Usuario tecnicoPrueba = new EpdoTecnico(usuarios, productos, 
        		diaActual, mesActual, anoActual, "11111113A", "Tecnico Noestupido",
        		"tecnicoverdugo@gmail.com", "unclainT", "1234");
        
        //Usuario fijo para el menu gestion de usuarios
        Usuario gestorUsuarios = new EpdoFinanciacion(usuarios, productos, 
        		diaActual, mesActual, anoActual, "GGGGGGGGU", "GESTION_USUARIOS",
        		"GESTION_USUARIOS@gmail.com", "GESTION_USUARIOS", "12345");
        
        usuarios.anadirUsuario(cajeroPrueba);
        usuarios.anadirUsuario(financiadorPrueba);
        usuarios.anadirUsuario(gestorUsuarios);
        usuarios.anadirUsuario(postventaPrueba);
        usuarios.anadirUsuario(tecnicoPrueba);
    }
    
}
