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

public class ProveedorDeudaEvent extends JDialog implements GestionObserver {
    private Controller ctrl;
    private final static int dataSize = 1;
    private JTextField[] dataField = new JTextField[dataSize];
    private String datas[] = new String[dataSize];
    private Proveedor p;
    private double pDouble;
    List<Proveedor> proveedorList = new ArrayList<Proveedor>();

    public ProveedorDeudaEvent(Frame parent, Controller ctrl, String s){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.p = ctrl.buscarProveedor(s);
        this.initiate();
        initGUI();
    }

    private void initiate(){

    }

    private void initGUI() {
        this.initiate();
        setTitle("Cambiar deuda");
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
        JLabel deuda = new JLabel("deuda : "+ Double.toString(p.getDeuda()));
        JLabel id = new JLabel("id : "+ p.getId());
        centerPanel.add(id);
        centerPanel.add(nombre);
        centerPanel.add(deuda);
        mainPanel.add(centerPanel);
        
		// Deuda Spinner
		JPanel valor = new JPanel();
		valor.setLayout(new BoxLayout(valor, BoxLayout.X_AXIS));
		valor.add(new JLabel("Valor del cambio: "));
		int min = -100000, max = 100000, step_value = 5;
		JSpinner _valorSpinner = new JSpinner(new SpinnerNumberModel(0.0, min, max, step_value));
		valor.setToolTipText("Valores: " + min + "-" + max);
		_valorSpinner.setMaximumSize(new Dimension(80, 40));
		_valorSpinner.setMinimumSize(new Dimension(80, 40));
		_valorSpinner.setPreferredSize(new Dimension(80, 40));
		valor.add(_valorSpinner);
		
		mainPanel.add(valor);
        
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Cambiar deuda : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION)
        {          
            try {
            	pDouble = Double.parseDouble(_valorSpinner.getValue().toString());
            	p.sumaDeuda(pDouble);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + _valorSpinner.getValue().toString());
                pDouble = 0.0;
            }
            Proveedor p1 = 
            		new Proveedor(p.getId(), p.getNombre(), p.getdDireccion(), p.getTelefono()
            				, p.getEmail(), p.getWeb(), p.getDeuda());
            ctrl.updatedProveedor(p1);
            JOptionPane.showMessageDialog(null
                    , "Has modificado al proveedor :" + p.getId()
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
