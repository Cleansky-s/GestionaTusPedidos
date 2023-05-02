package rest.factories;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import rest.model.PedidoDomicilio;
import rest.model.Plato;

public class PlatoBuilder extends Builder<Plato> {

	public PlatoBuilder() {
		super("Plato");
	}

	protected Plato createTheInstance(JSONObject data) {
		
		Plato plato;
		
		Map<String, Double> ingredientes = new HashMap<String, Double>();
		
		if(data.has("ingredientes")) {
			JSONArray jArray = data.getJSONArray("ingredientes");
			 for (int i = 0; i < jArray.length(); i++) {
	                JSONObject ingredientesData = jArray.getJSONObject(i);
	                Double cant = 0.0;
	                try {
	                	cant = Double.parseDouble(ingredientesData.get("cantidad").toString());
	                }
	                catch(Exception e){
	                	
	                }
	                ingredientes.put(ingredientesData.get("nombre").toString(), cant);
	            }
		}
		
		Double price = 0.0;
		Integer cant = 0;
		try {
        	price = Double.parseDouble(data.get("price").toString());
        	cant = Integer.parseInt(data.get("cantidad").toString());
        }
        catch(Exception e){
        	
        }
		
		boolean listo = false;
		boolean recogido = false;
		
		if(data.get("estadoCocina").equals("listo"))
			listo = true;
		if(data.get("estadoCocina").equals("recogido"))
			recogido = true;
		
		plato = new Plato(data.get("id").toString(), data.get("nombre").toString(), ingredientes,
					price , cant, listo, recogido);
		
		return plato;
	}

}
