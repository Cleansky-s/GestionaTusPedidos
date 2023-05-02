package rest.model;

import org.json.JSONObject;

public abstract class Persona {
	    protected String id;
	    protected String nombre;
	    protected String direccion;
	    protected String telefono;
	    protected String email;
	    Persona(String idP, String nombreP, String direccionP, String telefonoP, String emailP){
	            id = idP;
	            nombre = nombreP;
	            direccion = direccionP;
	            telefono = telefonoP;
	            email = emailP;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String idP) {
	        this.id = idP;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombreP) {
	        this.nombre = nombreP;
	    }

	    public String getdDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccionP) {
	        this.direccion = direccionP;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefonoP) {
	        this.telefono = telefonoP;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String emailP) {
	        this.email = emailP;
	    }
}
