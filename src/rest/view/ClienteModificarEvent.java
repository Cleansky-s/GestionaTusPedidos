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

public class ClienteModificarEvent extends JDialog implements GestionObserver {
    private Controller ctrl;
    private JTextField[] dataField = new JTextField[5];
    private String datas[] = new String[5];
    private Cliente c;
    List<Cliente> clienteList = new ArrayList<Cliente>();

    public ClienteModificarEvent(Frame parent, Controller ctrl, String s){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.c = ctrl.buscarCliente(s);
        this.initiate();
        initGUI();
    }

    private void initiate(){
        dataField[0] = new JTextField(c.getNombre());
        dataField[1] = new JTextField(c.getdDireccion());
        dataField[2] = new JTextField(c.getTelefono());
        dataField[3] = new JTextField(c.getEmail());
        dataField[4] = new JTextField(c.getId());
    }

    private void initGUI() {
        this.initiate();
        setTitle("Modificar Cliente");
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

        JLabel nombre = new JLabel("Introduce nombre : ");
        JLabel dirireccion = new JLabel("Introduce dirireccion : ");
        JLabel telefono = new JLabel("Introduce telefono : ");
        JLabel email = new JLabel("Introduce email : ");
        JLabel id = new JLabel("Introduce id : ");
        centerPanel.add(nombre);
        centerPanel.add(dataField[0]);
        centerPanel.add(dirireccion);
        centerPanel.add(dataField[1]);
        centerPanel.add(telefono);
        centerPanel.add(dataField[2]);
        centerPanel.add(email);
        centerPanel.add(dataField[3]);
        mainPanel.add(centerPanel);
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Modificar Cliente : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION)
        {
            for ( int i = 0; i < 5; i++)
            {
                datas[i] = String.valueOf(dataField[i].getText());
            }
            Cliente c1 = new Cliente(c.getId(),datas[0],datas[1],datas[2],datas[3]);
            ctrl.updatedCliente(c1);
            JOptionPane.showMessageDialog(null
                    , "Has modificado a cliente :" + c.getId()
                    , "Bienvenido "
                    , JOptionPane.PLAIN_MESSAGE);
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
