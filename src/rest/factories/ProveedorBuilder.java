package rest.factories;

import org.json.JSONObject;

import rest.model.Persona;
import rest.model.Proveedor;

public class ProveedorBuilder extends Builder<Persona> {

	public ProveedorBuilder() {
		super("Proveedor");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Proveedor createTheInstance(JSONObject data) {
		if (data == null)
			throw new IllegalArgumentException("Invalid value for createInstance: null");

		Proveedor p = new Proveedor(data.getString("id"),data.getString("nombre"),
				data.getString("direccion"),data.getString("telefono"),
				data.getString("email"), data.getString("web"),
				data.getDouble("deuda"));
		return p;
	}

}
