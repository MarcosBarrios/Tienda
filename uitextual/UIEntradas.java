package uitextual;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase encargada de gestionar las entradas de texto.
 * 
 * Permite al usuario interactuar con el programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIEntradas{
    
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
        Scanner s = new Scanner(System.in);
        int aux = 0;
        
        do{
            try{
                aux = s.nextInt();
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
        Scanner s = new Scanner(System.in);
        float aux = 0;
        do{
            try{
                aux = s.nextFloat();
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
     * @return true si entrada.equals("true")
     */
    public static boolean obtenerBooleana(boolean siOno){
        Scanner s = new Scanner(System.in);
        String entrada;
        boolean valida = false;
        do{
            entrada = s.next().toLowerCase().trim();
            if(!siOno){ //Obtener "true" o "false"
                if(entrada.toLowerCase().equals("true")){
                    valida = true;
                    return true;
                }else if(entrada.toLowerCase().equals("false")){
                    valida = true;
                    return false;
                }else{
                    //"Entrada no valida. (Se esperaba true o false)"
                    System.out.println(UIMensajes.g_EI_ValorBooleanoIncorrecto());
                }
            }else{ //Obtener "si" o "no"
                if(entrada.toLowerCase().equals(UIMensajes.g_Si().toLowerCase())){
                    valida = true;
                    return true;
                }else if(entrada.toLowerCase().equals(UIMensajes.g_No().toLowerCase())){
                    valida = true;
                    return false;
                }else{
                    //"Entrada no valida. (Se esperaba true o false)"
                    System.out.println(UIMensajes.g_EI_ValorBooleanoIncorrecto());
                }
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
        Scanner s = new Scanner(System.in);
        String aux;
        
        if(lineaCompleta){
            aux = s.nextLine();
        }else{
            aux = s.next().trim();   
        }
        
        return aux;
    }
    
}

