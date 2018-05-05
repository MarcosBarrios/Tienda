package uitextual;

import productos.*;
import backend.*;

/**
 * Clase encargada de implementar la interaccioón usuario del programa - ordenador
 * en forma de texto.
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class UIMain{
    
    //Usuario utilizando el programa
    private UIUsuario usuario;
    
    //Base de datos de productos del programa
    private Productos productos;
    
    //Base de datos de usuarios del programa
    private Usuarios usuarios;
    
    //Menu principal del programa
    private UIMenu menu;
    
    public UIMain(){
        productos = new Productos();
        usuarios = new Usuarios();
        Usuario u = new EpdoCajero("11111111B", "Marcos", "azucar@gmail.com", "vodafone",
            "12345");
        usuario = new UIEpdoCajero((EpdoCajero) u);
        UIMenuAccionable menuPrincipal = new UIMenuPrincipal(usuarios, productos,
            usuario);
    }
    
}
