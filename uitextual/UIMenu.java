package uitextual;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Genera un menu con varias opciones de navegacion
 * 
 * La idea es que se puedan ir añadiendo opciones externamente.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMenu{
    
    //Coleccion con las opciones de menu
    private ArrayList<String> listaOpciones;
    
    //Metodo constructor
    public UIMenu(){
        listaOpciones = new ArrayList<String>();
    }
    
    /**
     * Devuelve la lista de opciones de la clase
     */
    private ArrayList<String> obtenerListaOpciones(){
        return listaOpciones;
    }
    
    /**
     * Utiliza el gestionador de entradas del programa para
     * exigir al usuario que elija una opcion.
     */
    public int obtenerOpcion(){
        return UIEntradas.obtenerEntero(0, obtenerListaOpciones().size());
    }
    
    /**
     * Imprime el menu
     */
    public void imprimirOpciones(){
        System.out.println(); //Linea de espacio
        System.out.println(UIMensajes.menuEncabezado()); //Imprime el decorado superior del menu
        System.out.println(UIMensajes.menuElegirOpcion());
        System.out.println();
        
        //Itera las opciones del menu almacenadas en la coleccion y las imprime
        //formato: (x) :opcion:
        //ej formato: (4) Añadir producto a base de datos
        Iterator<String> itr = listaOpciones.iterator();
        int aux = 0;
        
        while(itr.hasNext()){
            String temp = itr.next();
            
            System.out.println("\t(" + aux + ") " + temp);
            aux++;
        }
        
        System.out.println();
        System.out.println(UIMensajes.menuEncabezado()); //Imprime el decorado inferior de menu
        System.out.println();
    }
    
    /**
     * Reasigna la lista de opciones interna
     */
    private void asignarListaOpciones(ArrayList<String> listaOpciones){
        this.listaOpciones = listaOpciones;
    }
    
    /**
     * Añade una opcion en la ultima posicion del menu
     * 
     * @param opcion - Texto de la opcion
     */
    public void añadirOpcion(String opcion){
        obtenerListaOpciones().add(opcion);
    }
    
    /**
     * Añade una opcion en la posicion indicada siempre que sea valida
     * 
     * @Precondicion: pos => 0 && pos <= listaOpciones.size()
     * @param opcion - Texto de la opcion
     * @param pos - Posicion de la opcion en el menu
     */
    public void añadirOpcion(String opcion, int pos){
        ArrayList<String> aux = new ArrayList<String>();
        
        if(pos >= 0 && pos <= obtenerListaOpciones().size()){
            Iterator<String> itr = obtenerListaOpciones().iterator();
            int numeroIteracion = 0;
            while(itr.hasNext()){
                String temp = itr.next();
                if(pos != numeroIteracion){ //Si no es la posicion buscada
                    aux.add(temp); //Simplemente se añade a la coleccion auxiliar
                }else { //Si es la posicion en la que se quiere añadir la opcion
                    aux.add(opcion); //Añadir a la coleccion auxiliar la nueva opcion
                    aux.add(temp);  //Añadir la opcion que estaba en la posicion buscada
                                    //despues de la nueva opcion
                }
                numeroIteracion++;
            }
        }
        
        //Tras terminar asignamos la coleccion auxiliar a la del menu
        asignarListaOpciones(aux);
    }
    
    /**
     * Elimina una opcion del menu utilizando unicamente el texto
     * 
     * @param opcion - El texto exacto de la opcion a borrar
     */
    public void eliminarOpcion(String opcion){
        Iterator<String> itr = obtenerListaOpciones().iterator();
        boolean contenida = false;
        int aux = 0; //almacena la posicion de la opcion a eliminar (solo si existe)
        
        //Comprobamos antes que nada si existe la opcion
        while(itr.hasNext()){
            String temp = itr.next();
            if(temp.equals(opcion)){
                contenida = true;
            }
            aux++;
        }
        
        if(contenida){ //Si existe la opcion y esta en el menu
            obtenerListaOpciones().remove(aux); //la borramos
        }
        
    }
    
    /**
     * Elimina una opcion del menu utilizando su posicion
     * 
     * @Precondicion: pos => 0 && pos <= listaOpciones.size()
     * @param pos - Posicion de la opcion en el menu
     */
    public void eliminarOpcion(int pos){
        if(pos >= 0 && pos <= obtenerListaOpciones().size()){
            obtenerListaOpciones().remove(pos);
        }
    }
    
    /**
     * Devuelve el nº asignado en el menu de la opcion con el texto
     * especificado.
     * 
     * @param opcion - El texto de la opcion
     * @return aux - Numero asignado a la opcion buscada
     */
    public int obtenerNumeroOpcion(String opcion){
        Iterator<String> itr = obtenerListaOpciones().iterator();
        int aux = 0;
        boolean encontrado = false;
        
        while(itr.hasNext() && !encontrado){
            String temp = itr.next();
            if(temp.equals(opcion)){
                encontrado = true;
            }
            aux++;
        }
        
        return aux;
    }
    
    /**
     * Devuelve el nº de opciones del menu
     * @return obtenerListaOpciones.size() - Numero de opciones que tiene el menu
     */
    public int obtenerNumeroOpciones(){
        return obtenerListaOpciones().size();
    }
}
