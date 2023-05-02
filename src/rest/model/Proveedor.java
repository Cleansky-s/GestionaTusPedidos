package rest.model;

public class Proveedor extends Persona {

private String web;
	
	private double deuda;
	
	public Proveedor(String idPr, String nombrePr, String direccionPr, String telefonoPr, String emailPr, String webPr, double deudaPr) {
		super(idPr, nombrePr, direccionPr, telefonoPr, emailPr);
		this.web = webPr;
		this.deuda = deudaPr;
	}
	
    public String getWeb() {
        return web;
    }
    
    public double getDeuda() {
        return deuda;
    }
    
    public void sumaDeuda(double deuda) {
        this.deuda += deuda;
    }

}
