package backend;


/**
 * Define el cliente que entra a la tienda a comprar o a vender.
 * Tiene capacidad para enviar reportes sobre el estado de un objeto
 * al igual que el EpdoTecnico
 * 
 * @author Marcos Barrios
 * @version 1.0
 */
public class Cliente extends Usuario{
    
    //Historial de productos comprados, facturas, manuales...
    private FichaCliente fichaCliente;
    
    //Metodo constructor
    public Cliente(String nombreUsuario, String emailUsuario){
        super(nombreUsuario, emailUsuario);
        fichaCliente = new FichaCliente();
    }
    
    /**
     * Asigna una ficha de cliente
     * 
     * @param fichaCliente La ficha a asignar
     */
    public void asignarFichaCliente(FichaCliente fichaCliente){
        this.fichaCliente = fichaCliente;
    }
    
    /**
     * Devuelve la ficha asignada al cliente
     * 
     * @return fichaCliente Ficha asignada al cliente
     */
    public FichaCliente obtenerFichaCliente(){
        return fichaCliente;
    }
    
}