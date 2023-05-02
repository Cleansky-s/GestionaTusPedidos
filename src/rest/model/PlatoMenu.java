package rest.model;

import java.util.Map;

public class PlatoMenu {
	String nombre;
	Map<String, Double> ingredientes;
	Double price;
	
	public PlatoMenu(String nombre, Map<String, Double> ingredientes, Double price) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.price = price;
	}
	
	public Double getPrice() {return this.price;}
	public String getNombre() {return this.nombre;}
	public Map<String, Double> getIngredientes() {return this.ingredientes;}
	
	public void addIngrediente(String nombre, Double cant) {
		if(cant > 0)
			throw new IllegalArgumentException("Cantidad de ingrediente menor que 0");
		
		if(!ingredientes.containsKey(nombre)) 
			ingredientes.put(nombre, cant);
	}
	
	public void removeIngrediente(String nombre) {
		if(ingredientes.containsKey(nombre)) 
			ingredientes.remove(nombre);
	}
	
	public void customIngrediente(String nombre, Double cant) throws IllegalArgumentException {
		if(cant > 0)
			throw new IllegalArgumentException("Cantidad de ingrediente menor que 0");
		
		if(ingredientes.containsKey(nombre))
			ingredientes.put(nombre, cant);	
	}
}
