package rest.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.Plato;
import rest.model.Proveedor;

public class PedidosPedirDeliveryDialog extends JDialog implements GestionObserver {
	
	private Controller ctrl;
	
	private JTextField[] dataField = new JTextField[4];
	private String data[] = new String[4];

	private JDialog pedirDialog;
	
	public PedidosPedirDeliveryDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}

	private void initGUI() {
		setTitle("Introducir datos de pedido");
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
		
		JLabel idCliente = new JLabel("Introduce cliente (opcional) : ");
        JLabel direccion = new JLabel("Introduce direccion : ");
        JLabel CP = new JLabel("Introduce codigo postal : ");
        JLabel telefono = new JLabel("Introduce telefono : ");
        
        dataField[0] = new JTextField();
        dataField[1] = new JTextField();
        dataField[2] = new JTextField();
        dataField[3] = new JTextField();
        
        
        centerPanel.add(idCliente);
        centerPanel.add(dataField[0]);
        centerPanel.add(direccion);
        centerPanel.add(dataField[1]);
        centerPanel.add(CP);
        centerPanel.add(dataField[2]);
        centerPanel.add(telefono);
        centerPanel.add(dataField[3]);
        mainPanel.add(centerPanel);
        
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Informacion de Cliente : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION){
        	
        	for ( int i = 0; i < 4; i++){
        		data[i] = String.valueOf(dataField[i].getText());
            }
        	
        	List<Plato> platos = new ArrayList<>();
        	
        	PedidoDomicilio ped = new PedidoDomicilio();
    
        	if(data[0].equals(""))
        		ped = new PedidoDomicilio(ctrl.generateIdPedido(), null, platos, 0.0, data[1], data[3], data[2]);
        	else {
        		ped = new PedidoDomicilio(ctrl.generateIdPedido(), data[0], platos, 0.0, data[1], data[3], data[2]);
        	}
        	
            ctrl.createPedido(ped);
            
            pedirDialog = new PedidosPedirDeliveryPlatosDialog(Utils.getWindow(this), ctrl, ped);
            if(ped.getPlatos().size() > 0 && !data[0].equals("")) {
            	ctrl.addClientPoints(data[0], 10);
            	
            }
            pedirDialog = null;
            
        }
        else if (selection == JOptionPane.CANCEL_OPTION)
        {
            // Do something here.
        }

		
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCliAdded(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedAdded(Pedido ped) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProvAdded(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCliDeleted(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedDeleted(Pedido ped) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProvDeleted(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCliUpdated(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedUpdated(Pedido ped) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProvUpdated(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}

}
