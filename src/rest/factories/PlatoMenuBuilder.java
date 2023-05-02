package rest.factories;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import rest.model.Plato;
import rest.model.PlatoMenu;

public class PlatoMenuBuilder extends Builder<PlatoMenu> {

	public PlatoMenuBuilder() {
		super("Menu");
	}

	@Override
	protected PlatoMenu createTheInstance(JSONObject data) {
		PlatoMenu plato;
		
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
		try {
        	price = Double.parseDouble(data.get("price").toString());
        }
        catch(Exception e){
        	
        }
		
		plato = new PlatoMenu(data.get("nombre").toString(), ingredientes,
					price);
		
		return plato;
	}

}
