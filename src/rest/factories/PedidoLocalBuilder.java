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
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.PedidoLocal;
import rest.model.Plato;

public class PedidoLocalBuilder extends Builder<Pedido> {

	public PedidoLocalBuilder() {
		super("Mesa");
	}

	@Override
	protected PedidoLocal createTheInstance(JSONObject data) {
		if (data == null)
			throw new IllegalArgumentException("Invalid value for createInstance: null");

		PedidoLocal ped;
		
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
	                platos.add(plato);
	            }
		}
		
		if(data.has("idCliente")) {
			ped = new PedidoLocal(data.get("id").toString(), data.get("idCliente").toString(), platos, Double.parseDouble(data.get("cuenta").toString()),
					data.get("idMesa").toString());
		}
		else {
			ped = new PedidoLocal(data.get("id").toString(), null, platos, Double.parseDouble(data.get("cuenta").toString()),
					data.get("idMesa").toString());
		}
		
		return ped;
	}

}
