package rest.model;

public class Cliente extends Persona{
    public Cliente(String idC, String nombreC, String direccionC, String telefonoC, String emailC){
    	super(idC, nombreC, direccionC, telefonoC, emailC);
    }
    
    @Override
    public String toString() {
        return "Cliente [idCliente=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
                + "]";
    }
}
