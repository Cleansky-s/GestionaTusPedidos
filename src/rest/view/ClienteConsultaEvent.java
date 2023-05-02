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

public class ClienteConsultaEvent extends JDialog implements GestionObserver {
    private Controller ctrl;
    private String datas[] = new String[5];
    private String s;
    List<Cliente> clienteList = new ArrayList<Cliente>();

    public ClienteConsultaEvent(Frame parent, Controller ctrl, String c){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.s = c;
        initGUI();
    }

    private void initGUI() {
        setTitle("Modificar Cliente");
        Cliente c = ctrl.buscarCliente(s);
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

        JLabel nombre = new JLabel("nombre : " + c.getNombre());
        JLabel dirireccion = new JLabel("dirireccion : "+ c.getdDireccion());
        JLabel telefono = new JLabel("telefono : "+ c.getTelefono());
        JLabel email = new JLabel("email : "+ c.getEmail());
        JLabel id = new JLabel("id : "+ c.getId());
        centerPanel.add(id);
        centerPanel.add(nombre);
        centerPanel.add(dirireccion);
        centerPanel.add(telefono);
        centerPanel.add(email);
        mainPanel.add(centerPanel);
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Consultar Cliente : "
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
