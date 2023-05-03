package rest.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import rest.control.Controller;
import rest.factories.BuilderBasedFactory;
import rest.model.Pedido;
import rest.model.Plato;

public class PlatoDAOImpl implements PlatoDAO{

	List<Plato> lis = new Vector<>();
	BuilderBasedFactory<Plato> fac;
	
	
	public PlatoDAOImpl(BuilderBasedFactory<Plato> fac) {
    	this.fac = fac;
    }
	
	@Override
	public List<Plato> listAll() {
		return this.lis;
	}

	@Override
	public Plato search(String id) {
		Plato pla = null;
		for(int i = 0; i < lis.size() && pla==null; i++){
			if(id.equals(lis.get(i).getId())){
				pla = lis.get(i);
			}
		}
		return pla;
	}

	@Override
	public void read() throws FileNotFoundException {
		JSONArray  pedidoArray;
		InputStream in = new FileInputStream(new File("resources/Plato.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			//Read and create Cliente
			Plato p;
			pedidoArray = jsonInput.getJSONArray("Plato");
			for(int i = 0; i < pedidoArray.length(); i++) {
				if(!pedidoArray.isEmpty()) {
					p = fac.createInstance(pedidoArray.getJSONObject(i));
					lis.add(p);
				}
			}
			// Pass the list to the model
		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public void create(Plato p) {
		lis.add(p);
    	try {
			commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Plato plat) {
		Plato p = search(plat.getId());
		lis.remove(p);
		lis.add(plat);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Plato p) {
		lis.remove(p);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void commit() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("resources/Plato.json"));
		JSONObject o = new JSONObject();
		for(int i = 0;i<lis.size();i++){
			JSONObject  a = new JSONObject();
			a.put("type","Plato");
			a.put("id",lis.get(i).getId());
			a.put("nombre", lis.get(i).getNombre());
			a.put("price", lis.get(i).getPrice().toString());
			a.put("cantidad", lis.get(i).getCant().toString());
			if(lis.get(i).getIngredientes() != null && lis.get(i).getIngredientes().size() > 0) {
				JSONArray ingredientesArray = new JSONArray();
				Map<String, Double> ingr = lis.get(i).getIngredientes();
				for(String key: ingr.keySet()) {
					JSONObject json = new JSONObject();
					json.put("nombre", key);
					json.put("cantidad", ingr.get(key).toString());
					ingredientesArray.put(json);
				}
				a.put("ingredientes", ingredientesArray);
			}
			if(!lis.get(i).getStateCocina())
				a.put("estadoCocina", "preparando");
			else
				a.put("estadoCocina", "listo");
			
			if(!lis.get(i).getStateCamarero())
				a.put("estadoCamarero", "esperando");
			else
				a.put("estadoCamarero", "recogido");
			
			o.append("Plato",a);
		}
		if(lis.size() == 0) {
			o.append("Plato", new JSONObject());
		}
		p.print(o.toString());
	}
	
	@Override
	public String GenerateNewId() {
		int idLength = 8; // the length of the new ID
	    String prefix = "PL";
	    String newId; // declare newId as final
	    boolean idExists;

	    // loop until a unique ID is generated
	    do {
	        // generate a new random alphanumeric string of length idLength
	        StringBuilder sb = new StringBuilder(prefix);
	        for (int i = 0; i < idLength - prefix.length(); i++) {
	            int randomDigit = (int) (Math.random() * 10);
	            sb.append(randomDigit);
	        }
	        newId = sb.toString();

	        // check if any Pedido in the list already has this ID
	        final String possibleId = newId;
	        idExists = lis.stream().anyMatch(p -> p.getId().equals(possibleId));
	    } while (idExists);

	    return newId;
	}

}
