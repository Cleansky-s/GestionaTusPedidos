package rest.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import rest.control.Controller;
import rest.factories.BuilderBasedFactory;
import rest.model.Proveedor;
import rest.model.Persona;

public class ProveedorDAOImpl implements ProveedorDAO{
	
	List<Proveedor> lis = new Vector<>();
	BuilderBasedFactory<Persona> fac;
	private static Controller control;
	public ProveedorDAOImpl(BuilderBasedFactory<Persona> fac, Controller control) {
	this.fac = fac;
	this.control = control;
	}

    @Override
	public List<Proveedor> listAll() {
    	return lis;
	}

	@Override
	public Proveedor search(String id) {
		Proveedor p = null;
		for(int i = 0; i < lis.size() && p == null; i++){
			if(id == lis.get(i).getId()){
				p = lis.get(i);
			}
		}
		return p;
	}

	@Override
	public void create(Proveedor t) {
		try {
			commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Proveedor t) {
		Proveedor p = search(t.getId());
		lis.remove(p);
		lis.add(t);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Proveedor t) {
		lis.remove(t);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void read() throws IllegalArgumentException, FileNotFoundException {
		JSONArray  proveedorArray;
		InputStream in = new FileInputStream(new File("resources/Proveedor.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			if(!jsonInput.isEmpty()){
			Proveedor c;
			proveedorArray = jsonInput.getJSONArray("Proveedor");
			for(int i = 0; i < proveedorArray.length(); i++) {
				if(!proveedorArray.getJSONObject(i).isNull(null)) {
					c = (Proveedor) fac.createInstance(proveedorArray.getJSONObject(i));
					lis.add(c);
				}
			}
			// Pass the list to the model
			control.listProvAdd(this.listAll());
			}
			else control.listProvAdd(this.listAll());
		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}
	}
	
	private void commit() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("resources/Proveedor.json"));
		JSONObject o = new JSONObject();
		for(int i = 0;i<lis.size();i++){
			JSONObject a = new JSONObject();
			a.put("type","Proveedor");
			a.put("id",lis.get(i).getId());
			a.put("nombre",lis.get(i).getNombre());
			a.put("direccion",lis.get(i).getdDireccion());
			a.put("telefono",lis.get(i).getTelefono());
			a.put("email",lis.get(i).getEmail());
			a.put("web",lis.get(i).getWeb());
			a.put("deuda",lis.get(i).getDeuda());
			o.append("Proveedor",a);
		}
		p.print(o.toString());
	}

	@Override
	public String GenerateNewId() {
		int idLength = 8; // the length of the new ID
	    String prefix = "PR";
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