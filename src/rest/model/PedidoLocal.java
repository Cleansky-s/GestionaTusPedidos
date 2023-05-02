package rest.model;

import java.util.List;

public class PedidoLocal extends Pedido {
	
	String idMesa;

	public PedidoLocal(String id, String idCliente, List<Plato> platos, String idMesa) {
		super(id, idCliente, platos);
		this.idMesa = idMesa;
		this.type = "Mesa";
	}

	public PedidoLocal() {
		super(null, null, null);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public String getIdMesa() {
		return this.idMesa;
	}

	
	
}
