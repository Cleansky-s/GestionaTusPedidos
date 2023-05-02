package rest.model;

import java.util.Map;

public class Plato extends PlatoMenu {

	private boolean listo;
	private boolean recogido;
	private String idPlato;
	private Integer cant;
	
	public Plato(String idPlato, String nombre, Map<String, Double> ingredientes, Double price, Integer cant,
			boolean listo, boolean recogido) {
		super(nombre, ingredientes, price);
		this.idPlato = idPlato;
		this.cant = cant;
		this.listo = listo;
		this.recogido = recogido;
	}
	
	public void customIngrediente(String nombre, Double cant) throws IllegalArgumentException {
		if(cant > 0)
			throw new IllegalArgumentException("Cantidad de ingrediente menor que 0");
		
		if(ingredientes.containsKey(nombre)) {
			Double cant_original = ingredientes.get(nombre);
			
			if(cant_original < cant) {
				Double ratio = cant/cant_original;
				price += 0.10*ratio;
			}
				
			ingredientes.put(nombre, cant);	
		}
			
	}
	
	public String getId() {return this.idPlato;}
	
	public Integer getCant() {return this.cant;}	
	
	public void setCant(Integer cant) {this.cant = cant;}
	
	public boolean getStateCocina() {return this.listo;}
	
	public void setStateCocina(boolean state) {this.listo = state;}
	
	public boolean getStateCamarero() {return this.recogido;}
	
	public void setStateCamarero(boolean state) {this.recogido = state;}

}
