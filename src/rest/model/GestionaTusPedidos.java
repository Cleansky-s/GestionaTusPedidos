package rest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class GestionaTusPedidos implements Observable<GestionObserver> {
    // UL as Unmodifiable List
    private List<Cliente> lCli;
    private List<Cliente> lCliUL;
    private List<Pedido> lPed;
    private List<Pedido> lPedUL;
    private List<Proveedor> lProv;
    private List<Proveedor> lProvUL;
    private List<GestionObserver> observers;

    public GestionaTusPedidos() {
        this.lCli = new ArrayList<Cliente>();
        this.lCliUL = Collections.unmodifiableList(lCli);
        this.lPed = new ArrayList<Pedido>();
        this.lPedUL = Collections.unmodifiableList(lPed);
        this.lProv = new ArrayList<Proveedor>();
        this.lProvUL = Collections.unmodifiableList(lProv);
        this.observers = new ArrayList<GestionObserver>();
    }

    public void listClienteAdd(List<Cliente> lC){
        this.lCli = lC;
        this.lCliUL = Collections.unmodifiableList(lCli);
    }

    public void listPedidoAdd(List<Pedido> lPe){
        this.lPed = lPe;
        this.lPedUL = Collections.unmodifiableList(lPed);
    }

    public void listProvAdd(List<Proveedor> lPr){
        this.lProv = lPr;
        this.lProvUL = Collections.unmodifiableList(lProv);
    }

    public void reset(){
    	this.lCli.clear();
    	this.lPed.clear();
    	this.lProv.clear();
    }

    public void addObserver(GestionObserver o) {
         if(!observers.contains(o)) {
                observers.add(o);
                o.onRegister(lCliUL, lPedUL, lProvUL);
            }
    }

    public void removeObserver(GestionObserver o) {
    	if(observers.contains(o))
            observers.remove(o);
    }

    public boolean createCliente(Cliente t) {
        lCli.add(t);
        for(GestionObserver o : observers) 
            o.onCliAdded(t);
        return true;
    }
    public boolean removeCliente(Cliente t) {
        lCli.remove(t);
        for(GestionObserver o : observers)
            o.onCliDeleted(t);
        return true;
    }
    
    public boolean createProveedor(Proveedor t) {
        lProv.add(t);
        for(GestionObserver o : observers) 
            o.onProvAdded(t);
		return true;
	}

	public boolean removeProveedor(Proveedor t) {
		lProv.remove(t);
        for(GestionObserver o : observers)
            o.onProvDeleted(t);
        return true;
	}

	public boolean createPedido(Pedido p) {
		lPed.add(p);
		for(GestionObserver o : observers)
            o.onPedAdded(p);
		return true;
	}

	public void removePedido(Pedido ped) {
		lPed.remove(ped);
		for(GestionObserver o: observers) {
			o.onPedDeleted(ped);
		}
	}

	public void updatePedido(Pedido p) {
		
		int i = 0;
		boolean found = false;
		while(i < lPed.size() && !found) {
			if(lPed.get(i).getId().equals(p.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lPed.remove(i);
		lPed.add(p);

		for(GestionObserver o: observers)
			o.onPedUpdated(p);
		
	}

}