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
        this.lCliUL = this.copyListCliente(lCli);
        
        this.lPed = new ArrayList<Pedido>();
        this.lPedUL = this.copyListPedido(lPed);
        
        this.lProv = new ArrayList<Proveedor>();
        this.lProvUL = this.copyListProveedor(lProv);
        
        this.observers = new ArrayList<GestionObserver>();
    }

    public void listClienteAdd(List<Cliente> lC){
    	this.lCli = this.copyListCliente(lC);
        this.lCliUL = this.copyListCliente(lCli);
    }

    public void listPedidoAdd(List<Pedido> lPe){
    	this.lPed = this.copyListPedido(lPe);
        this.lPedUL = this.copyListPedido(lPed);
    }

	public void listProvAdd(List<Proveedor> lPr){
		this.lProv = this.copyListProveedor(lPr);
        this.lProvUL = this.copyListProveedor(lProv);
    }

	public void reset(){
    	this.lCli.clear();
    	this.lProv.clear();
    	this.lProv.clear();
    }

    public void addObserver(GestionObserver o) {
         if(!observers.contains(o)) {
                observers.add(o);
                List<Cliente> lCliULunm = Collections.unmodifiableList(lCliUL);
                List<Proveedor> lProvULunm = Collections.unmodifiableList(lProvUL);
                List<Pedido> lPedULunm = Collections.unmodifiableList(lPedUL);
                o.onRegister(lCliULunm, lPedULunm, lProvULunm);
            }
    }

    public void removeObserver(GestionObserver o) {
    	if(observers.contains(o))
            observers.remove(o);
    }

    public boolean createCliente(Cliente t) {
        lCli.add(t);
        lCliUL.add(new Cliente(t));
        for(GestionObserver o : observers) 
            o.onCliAdded(new Cliente(t));
        return true;
    }
    public boolean removeCliente(Cliente t) {
    	int i = 0;
		boolean found = false;
		while(i < lCli.size() && !found) {
			if(lCli.get(i).getId().equals(t.getId())) {
				found = true;
				i--;
			}
			i++;
		}
		
		if(i < lCli.size())
			lCli.remove(i);
		
		i = 0;
		found = false;
		while(i < lCliUL.size() && !found) {
			if(lCliUL.get(i).getId().equals(t.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		if(i < lCliUL.size())
			lCliUL.remove(i);
		
        for(GestionObserver o : observers)
            o.onCliDeleted(t);
        return true;
    }
    
    public boolean createProveedor(Proveedor t) {
        lProv.add(t);
        lProvUL.add(new Proveedor(t));
        
        for(GestionObserver o : observers) 
            o.onProvAdded(t);
		return true;
	}

	public boolean removeProveedor(Proveedor t) {
		int i = 0;
		boolean found = false;
		while(i < lProv.size() && !found) {
			if(lProv.get(i).getId().equals(t.getId())) {
				found = true;
				i--;
			}
			i++;
		}
		
		if(i < lProv.size())
			lProv.remove(i);
		
		i = 0;
		found = false;
		while(i < lProvUL.size() && !found) {
			if(lProvUL.get(i).getId().equals(t.getId())) {
				found = true;
				i--;
			}
			i++;
		}
			
		if(i < lProvUL.size())
			lProvUL.remove(i);
		
        for(GestionObserver o : observers)
            o.onProvDeleted(t);
        return true;
	}

	public boolean createPedido(Pedido p) {
		lPed.add(p);
		lPedUL.add(p);
		for(GestionObserver o : observers)
            o.onPedAdded(p);
		return true;
	}

	public boolean removePedido(Pedido ped) {
		int i = 0;
		boolean found = false;
		while(i < lPed.size() && !found) {
			if(lPed.get(i).getId().equals(ped.getId())) {
				found = true;
				i--;
			}
			i++;
		}
		
		if(i < lPed.size())
			lPed.remove(i);
		
		i = 0;
		found = false;
		while(i < lPedUL.size() && !found) {
			if(lPedUL.get(i).getId().equals(ped.getId())) {
				found = true;
				i--;
			}
			i++;
		}
			
		if(i < lPedUL.size())
			lPedUL.remove(i);
		
		for(GestionObserver o: observers)
			o.onPedDeleted(ped);
		return true;
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
		
		i = 0;
		found = false;
		while(i < lPedUL.size() && !found) {
			if(lPedUL.get(i).getId().equals(p.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lPedUL.remove(i);
		lPedUL.add(p);

		for(GestionObserver o: observers)
			o.onPedUpdated(p);
	}
	
	public void updateProveedor(Proveedor p) {
		int i = 0;
		boolean found = false;
		while(i < lProv.size() && !found) {
			if(lProv.get(i).getId().equals(p.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lProv.remove(i);
		lProv.add(p);
		
		i = 0;
		found = false;
		while(i < lProvUL.size() && !found) {
			if(lProvUL.get(i).getId().equals(p.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lProvUL.remove(i);
		lProvUL.add(p);

		for(GestionObserver o: observers)
			o.onProvUpdated(p);
	}

	public void updateCliente(Cliente c1) {
		int i = 0;
		boolean found = false;
		while(i < lCli.size() && !found) {
			if(lCli.get(i).getId().equals(c1.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lCli.remove(i);
		lCli.add(c1);
		
		i = 0;
		found = false;
		while(i < lCliUL.size() && !found) {
			if(lCliUL.get(i).getId().equals(c1.getId())) {
				found = true;
				i--;
			}
			i++;
		}
				
		lCliUL.remove(i);
		lCliUL.add(c1);

		for(GestionObserver o: observers)
			o.onCliUpdated(c1);
	}
	
	private List<Cliente> copyListCliente(List<Cliente> src) {
		List<Cliente> newList = new ArrayList<Cliente>();
		for(Cliente elem: src)
			newList.add(new Cliente(elem));
		return newList;
	}
	
   private List<Pedido> copyListPedido(List<Pedido> src) {
	   List<Pedido> newList = new ArrayList<Pedido>();
		for(Pedido elem: src) {
			if(elem.getType().equals("Mesa"))
				newList.add(new PedidoLocal(elem));
			else
				newList.add(new PedidoDomicilio(elem));
		}
			
		return newList;
	}
   
   private List<Proveedor> copyListProveedor(List<Proveedor> src) {
	   List<Proveedor> newList = new ArrayList<Proveedor>();
		for(Proveedor elem: src)
			newList.add(new Proveedor(elem));
		return newList;
	}

}