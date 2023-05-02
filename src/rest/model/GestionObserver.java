package rest.model;
import java.util.List;

public interface GestionObserver {
    public void onRegister(List<Cliente> clientes,List<Pedido> pedidos, List<Proveedor> proveedores);
    public void onCliAdded(Cliente cli);
    public void onPedAdded(Pedido ped);
    public void onProvAdded(Proveedor prov);
    public void onCliDeleted(Cliente cli);
    public void onPedDeleted(Pedido ped);
    public void onProvDeleted(Proveedor prov);
    public void onCliUpdated(Cliente cli);
    public void onPedUpdated(Pedido ped);
    public void onProvUpdated(Proveedor prov);
}