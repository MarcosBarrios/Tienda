package uitextual;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar las entradas de texto.
 * 
 * Permite al usuario interactuar con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEntradas{
	private static final Scanner scanner = new Scanner(System.in);
	
	//Evitamos que se pueda crear una instancia de la clase
	private UIEntradas() {
		throw new IllegalStateException("Clase de utilidades");
	}
    
    /**
     * Permite al programa obtener un entero como entrada
     * del usuario.
     * 
     * 
     * @param min - Valor minimo que puede tomar el entero
     * @param max - Valor maximo que puede tomar el entero
     * @return aux - Valor obtenido como entrada del usuario
     */
    public static int obtenerEntero(int min, int max){
        int aux = 0;
        
        do{
            try{
                aux = scanner.nextInt();
            }catch(InputMismatchException ex){
                //"Entrada no valida. (Se esperaba numero)"
                System.out.println(UIMensajes.g_EI_EsperabaNumero());
            }
            if(aux < min || aux>max){
                //"Entrada no valida. (Fuera de rango)"
                System.out.println(UIMensajes.g_EI_FueraRango());
            }
        }while(aux < min || aux > max);
        
        return aux;
    }
    
    /**
     * Permite al programa obtener un numero decimal como entrada
     * del usuario.
     * 
     * 
     * @param min - Valor minimo que puede tomar el numero
     * @param max - Valor maximo que puede tomar el numero
     * @return aux - Valor obtenido como entrada del usuario
     */
    public static float obtenerDecimal(float min, float max){
        float aux = 0;
        do{
            try{
                aux = scanner.nextFloat();
            }catch(InputMismatchException ex){
                //"Entrada no valida. (Se esperaba numero)"
                System.out.println(UIMensajes.g_EI_EsperabaNumero());
            }
            if(aux < min || aux>max){
                //"Entrada no valida. (Fuera de rango)"
                System.out.println(UIMensajes.g_EI_FueraRango());
            }
            
        }while((aux < min || aux > max));
        
        return aux;
    }
    
    /**
     * Permite al programa obtener el valor de una variable
     * booleana como entrada del usuario.
     * 
     * El metodo pregunta por una cadena de caracter que tiene
     * que ser "true" o "false" para ser valida.
     * 
     * String entrada   Cadena de caracteres obtenida como entrada
     *                  de usuario al invocar obtenerBooleana()
     *                  
     * 
     * @return true si entrada.equals("true")
     */
    public static boolean obtenerBooleana(){
        String entrada;
        boolean valida = false;
        do{
            entrada = scanner.next();
            
            //Obtener "si", "no", "true" o "false"
            if(entrada.equalsIgnoreCase(UIMensajes.g_Si())||
            		entrada.equalsIgnoreCase("true")){
                return true;
            }else if(entrada.equalsIgnoreCase(UIMensajes.g_No()) ||
            		entrada.equalsIgnoreCase("false")){
                return false;
            }else{
                //"Entrada no valida. (Se esperaba true o false)"
                System.out.println(UIMensajes.g_EI_ValorBooleanoIncorrecto());
            }
            
        }while(!valida);
        
        return false;
    }
    
    /**
     * Devuelve una cadena de caracteres que este contenida
     * en una lista de cadena de caracteres.
     * 
     * @param listaValidas Lista con cadenas de caracteres validas
     * @param lineaCompleta
     * 
     * @return aux Una de las cadenas de caracteres que este en listaValidas.
     */
    public static String obtenerCadenaLimitada(ArrayList<String> listaValidas,
        boolean lineaCompleta){
        String aux;
        boolean valida = false;
        do{
            aux = obtenerCadena(lineaCompleta).toLowerCase().trim();
            if(listaValidas.contains(aux)){
                valida = true;
            }else{
                //"Entrada no valida. (Cadena no incluida)"
                System.out.println(UIMensajes.g_EI_CadenaNoIncluida());
            }
        }while(!valida);
        
        return aux;
    }
    
    /**
     * Permite al programa obtener una cadena de caracteres
     * como entrada del usuario.
     * 
     * @param lineaCompleta true: leer una linea completa
     *              
     * @return aux Cadena de caracteres obtenida del usuario
     */
    public static String obtenerCadena(boolean lineaCompleta){
        String aux;
        
        if(lineaCompleta){
            aux = scanner.nextLine();
        }else{
            aux = scanner.next().trim();   
        }        
        return aux;
    }
    
}

