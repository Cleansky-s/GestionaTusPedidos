package rest.model;

import java.util.List;

public class PedidoDomicilio extends Pedido {

	String CP;
	String direccion;
	String telefono;
	
	public PedidoDomicilio(String id, String idCliente, List<Plato> platos, String direccion, String telefono, String CP) {
		super(id, idCliente, platos);
		this.direccion = direccion;
		this.telefono = telefono;
		this.CP = CP;
		this.type = "Delivery";
	}

	public PedidoDomicilio() {
		super(null, null, null);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public String getDireccion() {return this.direccion;}
	
	public String getTelefono() {return this.telefono;}
	
	public String getCP() {return this.CP;}
	
	
}
