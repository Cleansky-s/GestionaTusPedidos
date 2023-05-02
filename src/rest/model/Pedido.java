package rest.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Pedido {

	String id;
	String type;
	
	String idCliente;
	Double cuenta;
	
	//Map<Plato, Integer> platos;
	List<Plato> platos;
	
	public Pedido(String id, String idCliente, List<Plato> platos) {
		this.id = id;
		this.idCliente = idCliente;
		this.platos = platos;
		cuenta = 0.0;
	}
	
	public abstract String  getType();
	
	public void addPlato(Plato plato) {
		int i = 0;
		boolean found = false;
		while(i < platos.size() && !found) {
			if(platos.get(i).equals(plato)) {
				found = true;
			}
			i++;
		}
		
		platos.add(plato);
			
		cuenta += plato.getPrice();
	}
	
	public void removePlato(Plato plato) {
		
		int i = 0;
		boolean found = false;
		while(i < platos.size() && !found) {
			if(platos.get(i).equals(plato)) {
				found = true;
				platos.remove(i);
			}
			i++;
		}
	
	   cuenta -= plato.getPrice();
	}
	
	public void customPlato(Plato plato, String ingrediente, Double cant) {
		boolean found = false;
		int i = 0;
		
		while(i < platos.size() && !found) {
			if(platos.get(i).equals(plato)) {
				cuenta =- plato.getPrice();
				platos.get(i).customIngrediente(ingrediente, cant);
				cuenta += plato.getPrice();
			}
			i++;
		}
		
	}
	
	public Double getCuenta() {return this.cuenta;}
	
	public String getId() {
		return this.id;
	}
	
	public String getIdCliente() {
		return this.idCliente;
	}
	
	public String getIdMesa() {
		return null;
	}

	public List<Plato> getPlatos() {
		return platos;
	}
	
	public String getDireccion() {return null;}
	
	public String getTelefono() {return null;}
	
	public String getCP() {return null;}
	
}
