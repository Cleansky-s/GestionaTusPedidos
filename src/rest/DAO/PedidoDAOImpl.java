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
import rest.model.Cliente;
import rest.model.Pedido;
import rest.model.Persona;
import rest.model.Plato;

public class PedidoDAOImpl implements PedidoDAO{

	List<Pedido> lis = new Vector<>();
	BuilderBasedFactory<Pedido> fac;
	private static Controller control;
	
    public PedidoDAOImpl(BuilderBasedFactory<Pedido> fac, Controller control) {
    	this.fac = fac;
		this.control = control;
    }

    @Override
    public List<Pedido> listAll() {
        return this.lis;
    }

    @Override
    public Pedido search(String id) {
    	Pedido ped = null;
		for(int i = 0; i < lis.size() && ped==null; i++){
			if(id.equals(lis.get(i).getId())){
				ped = lis.get(i);
			}
		}
		return ped;
    }

    @Override
    public void read() throws FileNotFoundException {
    	JSONArray  pedidoArray;
		InputStream in = new FileInputStream(new File("resources/Pedido.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			//Read and create Cliente
			Pedido p;
			pedidoArray = jsonInput.getJSONArray("Pedido");
			for(int i = 0; i < pedidoArray.length(); i++) {
				if(!pedidoArray.isEmpty()) {
					p = fac.createInstance(pedidoArray.getJSONObject(i));
					lis.add(p);
				}
				
			}
			// Pass the list to the model
			control.listPedidoAdd(this.listAll());
		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}

    }

    @Override
    public void create(Pedido pedido) {
    	lis.add(pedido);
    	try {
			commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void update(Pedido pedido) {
    	Pedido p = search(pedido.getId());
		lis.remove(p);
		lis.add(pedido);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void delete(Pedido pedido) {
    	lis.remove(pedido);
		try {
			commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void commit() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("resources/Pedido.json"));
		JSONObject o = new JSONObject();
		for(int i = 0;i<lis.size();i++){
			JSONObject  a = new JSONObject();
			a.put("type",lis.get(i).getType());
			a.put("id",lis.get(i).getId());
			if(lis.get(i).getType().equals("Mesa"))
				a.put("idMesa",lis.get(i).getIdMesa());
			else {
				a.put("direccion", lis.get(i).getDireccion());
				a.put("CP", lis.get(i).getCP());
				a.put("telefono", lis.get(i).getTelefono());
			}
			if(lis.get(i).getId() != null)
				a.put("idCliente", lis.get(i).getIdCliente());
				
			if(lis.get(i).getPlatos() != null && lis.get(i).getPlatos().size() > 0) {
				JSONArray platosArray = new JSONArray();
				for(Plato plat: lis.get(i).getPlatos()) {
					JSONObject json = new JSONObject();
					json.put("idPlato", plat.getId());
					platosArray.put(json);
				}
				a.put("platos", platosArray);
			}

			
			o.append("Pedido",a);
		}
		if(lis.size() == 0) {
			o.append("Pedido", new JSONObject());
		}
		p.print(o.toString());
	}

	@Override
	public String GenerateNewId() {
		int idLength = 8; // the length of the new ID
	    String prefix = "P";
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