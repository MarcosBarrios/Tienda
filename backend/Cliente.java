package backend;

import uitextual.UIMensajes;

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
    
    //Almacenamiento para el domicilio y el telefono de cliente
    private String domicilio, telefono;
    
    //Metodo constructor
    public Cliente(String dni, String nombreUsuario, String emailUsuario, 
    String domicilio, String telefono){
        super(dni, nombreUsuario, emailUsuario);
        this.domicilio = domicilio;
        this.telefono = telefono;
        
        fichaCliente = new FichaCliente();
    }
    
    /**
     * Asigna o reasigna el domicilio en el que vive el cliente
     * 
     * @param domicilio Poblacion del cliente
     */
    public void asignarDomicilio(String domicilio){
        this.domicilio = domicilio;
    }
    
    /**
     * Devuelve el domicilio en el que vive el cliente
     * 
     * @return domicilio Poblacion del cliente
     */
    public String obtenerDomicilio(){
        return domicilio;
    }
    
    /**
     * Asigna o reasigna el telefono del cliente
     * 
     * @param telefono Telefono del cliente
     */
    public void asignarTelefono(String telefono){
        this.telefono = telefono;
    }
    
    /**
     * Devuelve el telefono del cliente
     * 
     * @return telefono Telefono del cliente
     */
    public String obtenerTelefono(){
        return telefono;
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
    
    /**
	 * Devuelve una cadena para referenciar este tipo de cliente
	 */
    @Override
	public String toString() {
		return UIMensajes.mGU_AnE_Cliente();
	}
    
}