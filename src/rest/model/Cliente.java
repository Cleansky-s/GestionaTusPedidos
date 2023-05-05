package rest.model;

public class Cliente extends Persona{
	
	private Integer points;
	
    public Cliente(String idC, String nombreC, String direccionC, String telefonoC, String emailC){
    	super(idC, nombreC, direccionC, telefonoC, emailC);
    	this.points = 0;
    } 
    
    public Cliente(String idC, String nombreC, String direccionC, String telefonoC, String emailC, String points){
    	super(idC, nombreC, direccionC, telefonoC, emailC);
    	this.points = Integer.parseInt(points);
    }
    
    public Cliente(Cliente c) {
    	super(c.getId(), c.getNombre(), c.getdDireccion(), c.getTelefono(), c.getEmail());
    	this.points = 0;
	}

	@Override
    public String toString() {
        return "Cliente [idCliente=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
                + "]";
    }
	
	public Integer getPoints() {
		return this.points;
	}
	public void setPoints(Integer points) {
		this.points += points;
	}
}
