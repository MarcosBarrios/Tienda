package uitextual;

import backend.Util;

/**
 * Implementa metodos para formatear cadenas y obtener
 * entradas rapidamente.
 * 
 * @author: Marcos Barrios
 * @version: 1.0
 */
public abstract class UIObjeto {

	//Metodo constructor
	public UIObjeto() {}
	
	/**
     * Pide una entrada para cada cadena de caracteres en nombresEntradas.
     * 
     * Las entradas obtenidas son lineas completas (UIEntradas.obtenerCadena(true))
     * 
     * @param nombresEntradas Array con los nombres para las diferentes entradas
     * @return salidas Array con entradas obtenidas del usuario
     */
    public String[] formularioCadenas(String[] nombresEntradas){
        int numeroEntradas = nombresEntradas.length;
        String [] salidas = new String[numeroEntradas];
        for(int i = 0; i < numeroEntradas; i++){
        	//Por cada dato en el array se pide una cadena
            formatearCadena(nombresEntradas[i], true, true);
            String entrada = UIEntradas.obtenerCadena(true);
            salidas[i] = entrada;
        }
        
        return salidas;
    }
    
    /**
     * Pide una entrada para cada entero o decimal en nombresEntradas.
     * 
     * @param nombresEntradas Array con los nombres para las diferentes entradas
     * @return salidas Array con entradas obtenidas del usuario
     */
    public float[] formularioDecimales(String[] nombresEntradas){
        int numeroEntradas = nombresEntradas.length;
        float [] salidas = new float[numeroEntradas];
        for(int i = 0; i < numeroEntradas; i++){
            formatearCadena(nombresEntradas[i], true, true);
            float entrada = UIEntradas.obtenerDecimal(-1, Util.MAXIMACANTIDAD);
            salidas[i] = entrada;
        }
        
        return salidas;
    }
    
    /**
     * Obtiene una cadena de caracteres usando texto formateado
     */
    public String formatearEntradaCadena(String nombreEntrada, boolean lineaCompleta){
        formatearCadena(nombreEntrada, true, true);
        return UIEntradas.obtenerCadena(lineaCompleta);
    }
    
    /**
     * Obtiene un numero decimal usando texto formateado
     * 
     * Puede ser convertido a entero para obtener entradas enteras
     */
    public float formatearEntradaDecimal(String nombreEntrada){
        formatearCadena(nombreEntrada, true, true);
        return UIEntradas.obtenerDecimal(-1, Util.MAXIMACANTIDAD);
    }
    
    /**
     * Obtiene un estado booleano usando texto formateado
     */
    public boolean formatearEntradaBoolean(String nombreEntrada){
        formatearCadena(nombreEntrada, true, false);
        System.out.print(" [true/si] o [false/no)]: ");
        return UIEntradas.obtenerBooleana();
    }
    
    /**
     * Da formato a una cadena. 
     * 
     * @param nombreEntrada Nombre de la entrada
     * @param tabular Si tabular=true pone "\t" antes de nombreEntrada
     * @param puntoComa Si puntoComa=true pone ": " despues de nombreEntrada
     */
    public void formatearCadena(String nombreEntrada, boolean tabular, boolean puntoComa){
        System.out.println();
        tabularCadena(true);
        System.out.print(nombreEntrada);
        anadirPuntoComa(true);
    }
    
    /**
     * Tabula una cadena de caracteres si tabular=true
     */
    private void tabularCadena(boolean tabular){
        if(tabular){
            System.out.print("\t");
        }
    }
    
    /**
     * Imprime punto y coma si ponerPuntoComa=true
     */
    private void anadirPuntoComa(boolean ponerPuntoComa){
        if(ponerPuntoComa){
            System.out.print(": ");
        }
    }
}
