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

public class ClienteConsultaDialog extends JDialog implements GestionObserver {
    private Controller ctrl;

    List<Cliente> clienteList = new ArrayList<Cliente>();
    private DefaultComboBoxModel<String> clienteModel;;
    private JComboBox<String> comboBoxClientes;

    public ClienteConsultaDialog(Frame parent, Controller ctrl){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        initGUI();
    }

    private void initGUI() {
        setTitle("Consulta Cliente");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel boxesPanel = new JPanel();
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);

        clienteModel = new DefaultComboBoxModel<>();
        comboBoxClientes = new JComboBox<>(clienteModel);
        comboBoxClientes.setPreferredSize(new Dimension(350, 30));
        boxesPanel.add(new JLabel("Cliente: "));
        boxesPanel.add(comboBoxClientes);
        updateClienteModel(clienteList);
        comboBoxClientes.addActionListener((e) -> {});
        JButton consulta = new JButton("Consulta");
        consulta.addActionListener(e-> {
            String selectedClienteID = (String) comboBoxClientes.getSelectedItem();
            new ClienteConsultaEvent(Utils.getWindow(this),ctrl,selectedClienteID);
        }
        );
        boxesPanel.add(consulta);
        mainPanel.add(boxesPanel);
        this.add(mainPanel);
        setLocationRelativeTo(getParent());
        pack();
        setVisible(true);
    }

    private void updateClienteModel(List<Cliente> clienteList) {
        clienteModel.removeAllElements();
        for(Cliente c : clienteList){
            clienteModel.addElement(c.getId());
        }
    }

    @Override
    public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
        for(Cliente c : clientes) {
        	this.clienteList.add(c);
        }
    }

    @Override
    public void onCliAdded(Cliente cli) {
        this.clienteList.add(cli);
        updateClienteModel(clienteList);
    }

    @Override
    public void onPedAdded(Pedido ped) {

    }

    @Override
    public void onProvAdded(Proveedor prov) {

    }

    @Override
    public void onCliDeleted(Cliente cli) {
        clienteList.remove(search(cli.getId()));
        updateClienteModel(clienteList);
    }

    @Override
    public void onPedDeleted(Pedido ped) {

    }

    @Override
    public void onProvDeleted(Proveedor prov) {

    }

    @Override
    public void onCliUpdated(Cliente cli) {
        clienteList.remove(search(cli.getId()));
        clienteList.add(cli);
        clienteModel.removeAllElements();
        for(Cliente c : clienteList){
            clienteModel.addElement(c.getId());
        }

    }
    public Cliente search(String id) {
        Cliente c = null;
        for(int i = 0; i < clienteList.size() && c==null; i++){
            if(id.equals(clienteList.get(i).getId())){
                c = clienteList.get(i);
            }
        }
        return c;
    }

    @Override
    public void onPedUpdated(Pedido ped) {

    }

    @Override
    public void onProvUpdated(Proveedor prov) {

    }
}
