package uitextual;

/**
 * Base para todas las clases de menu que van a implementar las funciones
 * del programa.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public abstract class UIMenuAccionable extends UIObjeto{
    
    //Menu contenedor de las opciones
    private UIMenu menu;
    
    
    //Metodo constructor
    public UIMenuAccionable(){
        menu = new UIMenu();
    }
    
    /**
     * Devuelve el menu con la lista de opciones
     * 
     * @return menu Contenedor de las opciones
     */
    protected UIMenu obtenerMenu(){
        return menu;
    }
    
    /**
	 * Imprime el menu y activa la entrada de usuario
	 */
	protected abstract void activar();
    
}