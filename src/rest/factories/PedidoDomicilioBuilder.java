package rest.factories;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import rest.DAO.PlatoDAOImpl;
import rest.model.Cliente;
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.Plato;

public class PedidoDomicilioBuilder extends Builder<Pedido> {

	public PedidoDomicilioBuilder() {
		super("Delivery");
	}

	@Override
	protected PedidoDomicilio createTheInstance(JSONObject data) {
		if (data == null)
			throw new IllegalArgumentException("Invalid value for createInstance: null");

		PedidoDomicilio ped;
		
		List<Plato> platos = new ArrayList<>();
		
		List<Builder<Plato>> b2 = new Vector<>();
        b2.add(new PlatoBuilder());
		
		PlatoDAOImpl daoPlato = new PlatoDAOImpl(new BuilderBasedFactory<Plato>(b2));
		
		try {
			daoPlato.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(data.has("platos")) {
			JSONArray jArray = data.getJSONArray("platos");
			 for (int i = 0; i < jArray.length(); i++) {
	                JSONObject platoData = jArray.getJSONObject(i);
	                Plato plato = daoPlato.search(platoData.get("idPlato").toString());
	                //Plato plato = new Plato(platoData.get("idPlato").toString(), nombre, ingredientes, price, cant);
	                platos.add(plato);
	            }
		}
		
		if(data.has("idCliente")) {
			ped = new PedidoDomicilio(data.get("id").toString(), data.get("idCliente").toString(), platos,
					data.get("direccion").toString(), data.get("telefono").toString(), data.get("CP").toString());
		}
		else {
			ped = new PedidoDomicilio(data.get("id").toString(), null, platos,
					data.get("direccion").toString(), data.get("telefono").toString(), data.get("CP").toString());
		}
		
		return ped;
	}

}
