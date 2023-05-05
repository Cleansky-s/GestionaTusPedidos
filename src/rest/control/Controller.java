package rest.control;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import rest.DAO.CRUD;
import rest.DAO.ClienteDAOImpl;
import rest.DAO.PedidoDAOImpl;
import rest.DAO.PlatoDAOImpl;
import rest.DAO.ProveedorDAOImpl;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.ClienteBuilder;
import rest.factories.PedidoDomicilioBuilder;
import rest.factories.PedidoLocalBuilder;
import rest.factories.PlatoBuilder;
import rest.factories.ProveedorBuilder;
import rest.model.*;

public class Controller {
    private GestionaTusPedidos gtp;
    private static CRUD<Cliente> daoCliente;
    private static CRUD<Pedido> daoPedido;
    private static CRUD<Proveedor> daoProveedor;
    private static CRUD<Plato> daoPlato;
    private static BuilderBasedFactory<Persona> facPersona;
    private static BuilderBasedFactory<Pedido> facPedido;
    private static BuilderBasedFactory<Plato> facPlato;

    public Controller() throws FileNotFoundException {
		 List<Builder<Persona>> b1 = new Vector<>();
	     b1.add(new ClienteBuilder());
	     b1.add(new ProveedorBuilder());
	     facPersona = new BuilderBasedFactory<Persona>(b1);
	     
	     List<Builder<Pedido>> b2 = new Vector<>();
	     b2.add(new PedidoDomicilioBuilder());
	     b2.add(new PedidoLocalBuilder());
	     facPedido = new BuilderBasedFactory<Pedido>(b2);
		
	     List<Builder<Plato>> b3 = new Vector<>();
	     b3.add(new PlatoBuilder());
	     facPlato = new BuilderBasedFactory<Plato>(b3);
    	
    	this.gtp = new GestionaTusPedidos();
    	this.daoCliente = new ClienteDAOImpl(facPersona, this);
    	this.daoPedido = new PedidoDAOImpl(facPedido, this);
    	this.daoProveedor = new ProveedorDAOImpl(facPersona, this);
    	this.daoPlato = new PlatoDAOImpl(facPlato);
        loadData();
	}

	public void loadData() throws FileNotFoundException {
        daoCliente.read();
        daoPedido.read();
        daoProveedor.read();
        daoPlato.read();
    }

    // Bridge methods
    public void reset() {
        gtp.reset();
    }

    // If we are able to use an active MVC, we'll use observers
    public void addObserver(GestionObserver o) {
        gtp.addObserver(o);
    }

    public void removeObserver(GestionObserver o) {
        gtp.removeObserver(o);
    }

    public void listClienteAdd(List<Cliente> lC){
        gtp.listClienteAdd(new ArrayList<Cliente>(lC));
    }

    public void listPedidoAdd(List<Pedido> lPe){
        gtp.listPedidoAdd(new ArrayList<Pedido>(lPe));
    }

    public void listProvAdd(List<Proveedor> lPr){
        gtp.listProvAdd(new ArrayList<Proveedor>(lPr));
    }

    public void createCliente(Cliente t) {
        if(gtp.createCliente(t))
            daoCliente.create(t);
    }

    public void deleteCliente(String ClienteID) {
        Cliente c = daoCliente.search(ClienteID);
        if(gtp.removeCliente(c))
        	daoCliente.delete(c);
    }

    public Cliente buscarCliente(String selectedClienteID) {
        return daoCliente.search(selectedClienteID);
    }

    public void updateCliente(Cliente c1) {
    	gtp.updateCliente(c1);
        daoCliente.update(c1);
    }
    
    public void createProveedor(Proveedor t) {
        if(gtp.createProveedor(t))
        	daoProveedor.create(t);
    }

    public void deleteProveedor(String ProvID) {
    	Proveedor p = daoProveedor.search(ProvID);
        if(gtp.removeProveedor(p))
        	daoProveedor.delete(p);
    }

    public Proveedor buscarProveedor(String selectedProvID) {
        return daoProveedor.search(selectedProvID);
    }

    public void updateProveedor(Proveedor p) {
    	gtp.updateProveedor(p);
    	daoProveedor.update(p);
    }
    
    public void createPedido(Pedido p) {
        if(gtp.createPedido(p))
            daoPedido.create(p);
        for(Plato plat: p.getPlatos())
        	daoPlato.create(plat);
    }

    public void deletePedido(String PedidoID) {
        Pedido ped = daoPedido.search(PedidoID);
        if(gtp.removePedido(ped))
        	daoPedido.delete(ped);
        for(Plato plat: ped.getPlatos())
        	daoPlato.delete(plat);
    }

    public Pedido searchPedido(String selectedPedidoID) {
        return daoPedido.search(selectedPedidoID);
    }

    public void updatePedido(Pedido p) {
    	gtp.updatePedido(p);
        daoPedido.update(p);
        for(Plato plat: p.getPlatos())
        	daoPlato.update(plat);
    }

	public String generateIdPedido() {
		return daoPedido.GenerateNewId();
	}

	public String generateIdPlato() {
		return daoPlato.GenerateNewId();
	}
	
	public String generateIdCliente() {
		return daoCliente.GenerateNewId();
	}
	
	public String generateIdProveedor() {
		return daoProveedor.GenerateNewId();
	}

	public void addClientPoints(String id, int points) {
		Cliente c = buscarCliente(id);
		if(c!=null) {
			c.setPoints(points);
		}
		daoCliente.update(c);
	}
	
}