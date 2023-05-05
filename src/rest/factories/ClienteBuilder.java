package rest.factories;

import org.json.JSONObject;

import rest.model.Cliente;
import rest.model.Persona;

public class ClienteBuilder extends Builder<Persona> {

	public ClienteBuilder() {
		super("Cliente");
		// TODO Auto-generated constructor stub
	}


	@Override
	protected Cliente createTheInstance(JSONObject data) {
		if (data == null)
			throw new IllegalArgumentException("Invalid value for createInstance: null");

		Cliente c = new Cliente(data.getString("id"),data.getString("nombre"),data.getString("direccion"),data.getString("telefono"),data.getString("email"), data.getString("puntos"));
		return c;

	}
}
