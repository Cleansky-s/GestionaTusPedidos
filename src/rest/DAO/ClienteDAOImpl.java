package rest.DAO;

import java.io.*;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import rest.factories.BuilderBasedFactory;
import rest.model.Cliente;
import rest.control.Controller;
import rest.model.Persona;

public class ClienteDAOImpl implements ClienteDAO{

	List<Cliente> lis = new Vector<>();
	BuilderBasedFactory<Persona> fac;
	private static Controller control;
	
	public ClienteDAOImpl(BuilderBasedFactory<Persona> fac, Controller control) {
		this.fac = fac;
		this.control = control;
	}

	@Override
	public List<Cliente> listAll() {
		return lis;
	}
	
	@Override
	public void read() throws IllegalArgumentException, FileNotFoundException {
		JSONArray  clienteArray;
		InputStream in = new FileInputStream(new File("resources/Cliente.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			if(!jsonInput.isEmpty()){
			Cliente c;
			clienteArray = jsonInput.getJSONArray("Cliente");
			for(int i = 0; i < clienteArray.length(); i++) {
				if(!clienteArray.isEmpty()) {
					c = (Cliente) fac.createInstance(clienteArray.getJSONObject(i));
					lis.add(c);
				}
			}
			// Pass the list to the model
			control.listClienteAdd(this.listAll());
			}
			else control.listClienteAdd(this.listAll());
		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Cliente search(String id) {
		Cliente c = null;
		for(int i = 0; i < lis.size() && c==null; i++){
			if(id.equals(lis.get(i).getId())){
				c = lis.get(i);
			}
		}
		return c;
	}

	@Override
	public void create(Cliente t) {
		lis.add(t);
		try {
			commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Cliente t) {
		Cliente c = search(t.getId());
		lis.remove(c);
		lis.add(t);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Cliente t) {
		lis.remove(t);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void commit() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("resources/Cliente.json"));
		JSONObject o = new JSONObject();
		for(int i = 0;i<lis.size();i++){
			JSONObject  a = new JSONObject();
			a.put("type","Cliente");
			a.put("id",lis.get(i).getId());
			a.put("nombre",lis.get(i).getNombre());
			a.put("direccion",lis.get(i).getdDireccion());
			a.put("telefono",lis.get(i).getTelefono());
			a.put("email",lis.get(i).getEmail());
			a.put("puntos", lis.get(i).getPoints().toString());
			o.append("Cliente",a);
		}
		p.print(o.toString());
	}

	@Override
	public String GenerateNewId() {
		int idLength = 8; // the length of the new ID
	    String prefix = "C";
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