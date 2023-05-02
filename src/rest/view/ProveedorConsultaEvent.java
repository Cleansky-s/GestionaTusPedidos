package rest.view;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorConsultaEvent extends JDialog implements GestionObserver {
    private Controller ctrl;
    private String datas[] = new String[6];
    private String s;
    List<Proveedor> proveedorList = new ArrayList<Proveedor>();

    public ProveedorConsultaEvent(Frame parent, Controller ctrl, String p){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.s = p;
        initGUI();
    }

    private void initGUI() {
        setTitle("Consulta Proveedor");
        Proveedor p = ctrl.buscarProveedor(s);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel boxesPanel = new JPanel();
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);

        JLabel nombre = new JLabel("nombre : " + p.getNombre());
        JLabel dirireccion = new JLabel("direccion : "+ p.getdDireccion());
        JLabel telefono = new JLabel("telefono : "+ p.getTelefono());
        JLabel email = new JLabel("email : "+ p.getEmail());
        JLabel web = new JLabel("web : "+ p.getWeb());
        JLabel deuda = new JLabel("deuda : "+ Double.toString(p.getDeuda()));
        JLabel id = new JLabel("id : "+ p.getId());
        centerPanel.add(id);
        centerPanel.add(nombre);
        centerPanel.add(dirireccion);
        centerPanel.add(telefono);
        centerPanel.add(email);
        centerPanel.add(web);
        centerPanel.add(deuda);
        mainPanel.add(centerPanel);
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Consultar Proveedor : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION)
        {
            // Do something here.
        }
        else if (selection == JOptionPane.CANCEL_OPTION)
        {
            // Do something here.
        }


    }

    @Override
    public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {

    }

    @Override
    public void onCliAdded(Cliente cli) {

    }

    @Override
    public void onPedAdded(Pedido ped) {

    }

    @Override
    public void onProvAdded(Proveedor prov) {

    }

    @Override
    public void onCliDeleted(Cliente cli) {

    }

    @Override
    public void onPedDeleted(Pedido ped) {

    }

    @Override
    public void onProvDeleted(Proveedor prov) {

    }

    @Override
    public void onCliUpdated(Cliente cli) {

    }

    @Override
    public void onPedUpdated(Pedido ped) {

    }

    @Override
    public void onProvUpdated(Proveedor prov) {

    }
}

