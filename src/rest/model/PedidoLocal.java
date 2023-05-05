package rest.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoLocal extends Pedido {
	
	String idMesa;

	public PedidoLocal(Pedido elem) {
		super(elem);
		this.idMesa = elem.getIdMesa();
		this.type = "Mesa";
	}
	
	public PedidoLocal(String id, String idCliente, List<Plato> platos, Double cuenta, String idMesa) {
		super(id, idCliente, platos, cuenta);
		this.idMesa = idMesa;
		this.type = "Mesa";
	}

	public PedidoLocal() {
		super(null, null, new ArrayList<>(), null);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public String getIdMesa() {
		return this.idMesa;
	}

	
	
}
