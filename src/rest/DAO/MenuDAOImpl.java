package rest.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import rest.factories.BuilderBasedFactory;
import rest.model.Plato;
import rest.model.PlatoMenu;

public class MenuDAOImpl implements MenuDAO {


	List<PlatoMenu> lis = new Vector<>();
	BuilderBasedFactory<PlatoMenu> fac;
	
	
	public MenuDAOImpl(BuilderBasedFactory<PlatoMenu> fac) {
    	this.fac = fac;
    }
	
	@Override
	public List<PlatoMenu> listAll() {
		return this.lis;
	}

	@Override
	public PlatoMenu search(String id) {
		PlatoMenu pla = null;
		for(int i = 0; i < lis.size() && pla==null; i++){
			if(id.equals(lis.get(i).getNombre())){
				pla = lis.get(i);
			}
		}
		return pla;
	}

	@Override
	public void read() throws FileNotFoundException {
		JSONArray  pedidoArray;
		InputStream in = new FileInputStream(new File("resources/Menu.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			//Read and create Cliente
			PlatoMenu p;
			pedidoArray = jsonInput.getJSONArray("Menu");
			for(int i = 0; i < pedidoArray.length(); i++) {
				p = fac.createInstance(pedidoArray.getJSONObject(i));
				lis.add(p);
			}
			// Pass the list to the model
		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public void create(PlatoMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PlatoMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PlatoMenu t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GenerateNewId() {return new String();}

}
