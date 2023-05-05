package rest.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoDomicilio extends Pedido {

	String CP;
	String direccion;
	String telefono;
	
	public PedidoDomicilio(Pedido ped) {
		super(ped);
		this.direccion = ped.getDireccion();
		this.telefono = ped.getTelefono();
		this.CP = ped.getCP();
		this.type = "Delivery";
	}
	
	public PedidoDomicilio(String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP) {
		super(id, idCliente, platos, cuenta);
		this.direccion = direccion;
		this.telefono = telefono;
		this.CP = CP;
		this.type = "Delivery";
	}

	public PedidoDomicilio() {
		super(null, null, new ArrayList<>(), null);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public String getDireccion() {return this.direccion;}
	
	public String getTelefono() {return this.telefono;}
	
	public String getCP() {return this.CP;}
	
	
}
