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

public class ClienteAltaDialog extends JDialog implements GestionObserver {  
    private Controller ctrl;
    private JTextField[] dataField;
    private String datas[];
    private final Integer datasSIZE = 4;

    public ClienteAltaDialog(Frame parent, Controller ctrl){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.initiate();
        initGUI();
    }

    private void initiate(){
        datas = new String[datasSIZE];
        dataField = new JTextField[datasSIZE];
        for(int i = 0; i < datasSIZE; i++)
        	dataField[i] = new JTextField(15);
    }

    private void initGUI() {
        setTitle("Alta Cliente");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel boxesPanel = new JPanel();
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 5, 5));
        centerPanel.setBorder(
        BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);

        JLabel nombre = new JLabel("Introduce nombre : ");
        JLabel dirireccion = new JLabel("Introduce dirireccion : ");
        JLabel telefono = new JLabel("Introduce telefono : ");
        JLabel email = new JLabel("Introduce email : ");
        //JLabel id = new JLabel("Introduce id : ");
        centerPanel.add(nombre);
        centerPanel.add(dataField[0]);
        centerPanel.add(dirireccion);
        centerPanel.add(dataField[1]);
        centerPanel.add(telefono);
        centerPanel.add(dataField[2]);
        centerPanel.add(email);
        centerPanel.add(dataField[3]);
        //centerPanel.add(id);
        //centerPanel.add(dataField[4]);
        mainPanel.add(centerPanel);
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Alta Cliente : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION)
        {
            for ( int i = 0; i < datasSIZE ; i++)
        {
            datas[i] = String.valueOf(dataField[i].getText());
        }
            Cliente c = new Cliente(ctrl.generateIdCliente(),datas[0],datas[1],datas[2],datas[3]);
            this.ctrl.createCliente(c);
            JOptionPane.showMessageDialog(null
                    , "Has dado alta a cliente :" + c.getId()
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
