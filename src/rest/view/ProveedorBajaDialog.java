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

public class ProveedorBajaDialog extends JDialog implements GestionObserver {
    private Controller ctrl;

    List<Proveedor> proveedorList = new ArrayList<Proveedor>();
    private DefaultComboBoxModel<String> proveedorModel;;
    private JComboBox<String> comboBoxProveedores;

    public ProveedorBajaDialog(Frame parent, Controller ctrl){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        initGUI();
    }

    private void initGUI() {
        setTitle("Baja Proveedor");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel boxesPanel = new JPanel();
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);

        proveedorModel = new DefaultComboBoxModel<>();
        comboBoxProveedores = new JComboBox<>(proveedorModel);
        comboBoxProveedores.setPreferredSize(new Dimension(350, 30));
        boxesPanel.add(new JLabel("Proveedor: "));
        boxesPanel.add(comboBoxProveedores);
        updateProvModel(proveedorList);
        comboBoxProveedores.addActionListener((e) -> {});
        JButton baja = new JButton("BAJA");
        baja.addActionListener(e-> {
            String selectedProvID = (String) comboBoxProveedores.getSelectedItem();
            ctrl.deleteProveedor(selectedProvID);

        });
        boxesPanel.add(baja);
        mainPanel.add(boxesPanel);
        this.add(mainPanel);
        setLocationRelativeTo(getParent());
        pack();
        setVisible(true);
    }

    private void updateProvModel(List<Proveedor> proveedorList) {
        proveedorModel.removeAllElements();
        for(Proveedor p : proveedorList){
            proveedorModel.addElement(p.getId());
        }
    }

    @Override
    public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
        for(Proveedor p : proveedores)
            this.proveedorList.add(p);
    }

    @Override
    public void onCliAdded(Cliente cli) {

    }

    @Override
    public void onPedAdded(Pedido ped) {

    }

    @Override
    public void onProvAdded(Proveedor prov) {
        this.proveedorList.add(prov);
        updateProvModel(proveedorList);
    }

    @Override
    public void onCliDeleted(Cliente cli) {

    }

    @Override
    public void onPedDeleted(Pedido ped) {

    }

    @Override
    public void onProvDeleted(Proveedor prov) {
        proveedorList.remove(prov);
        updateProvModel(proveedorList);
    }

    @Override
    public void onCliUpdated(Cliente cli) {
    	
    }
    
    private Proveedor search(String id) {
    	Proveedor p = null;
        for(int i = 0; i < proveedorList.size() && p==null; i++){
            if(id.equals(proveedorList.get(i).getId())){
                p = proveedorList.get(i);
            }
        }
        return p;
    }

    @Override
    public void onPedUpdated(Pedido ped) {

    }

    @Override
    public void onProvUpdated(Proveedor prov) {
        Proveedor toRemove = search(prov.getId());
        proveedorList.remove(toRemove);
        proveedorList.add(prov);
        proveedorModel.removeAllElements();
        for(Proveedor p : proveedorList){
            proveedorModel.addElement(p.getId());
        }
    }
}
