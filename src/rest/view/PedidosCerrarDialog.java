package rest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.JSONObject;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.Plato;
import rest.model.Proveedor;

public class PedidosCerrarDialog extends JDialog implements GestionObserver {
	
	private Controller ctrl;
	
	List<Pedido> pedidosMesa = new ArrayList<Pedido>();
	List<Pedido> pedidosDelivery = new ArrayList<Pedido>();
	
	private DefaultComboBoxModel<String> optionsModel = new DefaultComboBoxModel<>();;
	private DefaultComboBoxModel<String> pedidosModel;
	private JComboBox<String> comboBoxOptions;
	private JComboBox<String> comboBoxPedidos;
	
	private JDialog facturaDialog;

	public PedidosCerrarDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Cerrar pedido");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel boxesPanel = new JPanel();
		boxesPanel.setLayout(new BorderLayout());
		boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel typePanel = new JPanel();
		comboBoxOptions = new JComboBox<>(optionsModel);
		comboBoxOptions.setPreferredSize(new Dimension(350, 30));
		typePanel.add(new JLabel("Tipo: "));
		typePanel.add(comboBoxOptions);
		boxesPanel.add(typePanel, BorderLayout.NORTH);
		comboBoxOptions.addActionListener((e) -> {
	        String selectedOption = (String) comboBoxOptions.getSelectedItem();
	        if (selectedOption.equals("Mesa")) {
	            updatePedidosModel(pedidosMesa);
	        } else {
	            updatePedidosModel(pedidosDelivery);
	        }
		});
		
		JPanel pedidoPanel = new JPanel();
		pedidosModel = new DefaultComboBoxModel<>();
		comboBoxPedidos = new JComboBox<>(pedidosModel);
		comboBoxPedidos.setPreferredSize(new Dimension(350, 30));
		pedidoPanel.add(new JLabel("Id: "));
		pedidoPanel.add(comboBoxPedidos);
		boxesPanel.add(pedidoPanel, BorderLayout.SOUTH);
		comboBoxOptions.addActionListener((e) -> {});
		
		mainPanel.add(boxesPanel, BorderLayout.NORTH);
		
		this.add(mainPanel);
		int selection = JOptionPane.showConfirmDialog(
            null, mainPanel, "Cerrar Pedido : "
            , JOptionPane.OK_CANCEL_OPTION
            , JOptionPane.PLAIN_MESSAGE);
	
	    if (selection == JOptionPane.OK_OPTION){
	        	
	    	int i = 0;
	    	boolean found = false;
	    	Pedido ped = null;
	    	if(comboBoxOptions.getSelectedItem().toString().equals("Mesa")) {
	    		while(i < pedidosMesa.size() && !found) {
	    			if(pedidosMesa.get(i).getIdMesa().equals(comboBoxPedidos.getSelectedItem().toString())) {
	    				found = true;
	    				ped = pedidosMesa.get(i);
	    			}
	    			i++;
	    		}
	    	}
	    	else {
	    		while(i < pedidosDelivery.size() && !found) {
	    			if(pedidosDelivery.get(i).getId().equals(comboBoxPedidos.getSelectedItem().toString())) {
	    				found = true;
	    				ped = pedidosDelivery.get(i);
	    			}
	    			i++;
	    		}
	    	}
	    	
	    	this.facturaDialog = new PedidosCerrarFacturaDialog(Utils.getWindow(this), ped);
	    	this.facturaDialog = null;
	    	
	    	ctrl.deletePedido(ped.getId());
	    	
            JOptionPane.showMessageDialog(null
                    , "Eliminado con exito"
                    , "Bienvenido "
                    , JOptionPane.PLAIN_MESSAGE);
	    }
	    
	    else if (selection == JOptionPane.CANCEL_OPTION){
	    	// Do something here.
	    }
		
		
		pack();
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		optionsModel.addElement("Mesa");
		optionsModel.addElement("Delivery");
		
		for(Pedido p: pedidos) {
			if(p.getType().equals("Mesa")) {
				this.pedidosMesa.add(p);
			}
			else {
				this.pedidosDelivery.add(p);
			}
		}
	}

	@Override
	public void onCliAdded(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedAdded(Pedido ped) {
		if(ped.getType().equals("Mesa")) {
			this.pedidosMesa.add(ped);
		}
		else {
			this.pedidosDelivery.add(ped);
		}
	
		
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
		int i;
		if(ped.getType().equals("Mesa")) {
			i = this.find(pedidosMesa, ped);
			if(i < pedidosMesa.size())
				pedidosMesa.remove(i);
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size())
				pedidosDelivery.remove(i);
		}
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
		int i;
		if(ped.getType().equals("Mesa")) {
			i = this.find(pedidosMesa, ped);
			if(i < pedidosMesa.size()) {
				pedidosMesa.remove(i);
			}
			pedidosMesa.add(ped);
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size()) {
				pedidosDelivery.remove(i);
			}
			pedidosDelivery.add(ped);
		}
		
	}

	@Override
	public void onProvUpdated(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}
	
	private int find(List<Pedido> list, Pedido ped) {
		int i = 0;
		boolean found = false;
		while(i < list.size() && !found) {
			if(list.get(i).getId().equals(ped.getId())) {
				found = true;
				i--;
			}
			i++;
		}
		return i;
	}
	
	private void updatePedidosModel(List<Pedido> pedidos) {
	    pedidosModel.removeAllElements();
	    for (Pedido p : pedidos) {
	    	if(p.getType().equals("Mesa")) {
	    		 pedidosModel.addElement(p.getIdMesa());
	    	}
	    	else {
	    		pedidosModel.addElement(p.getId());
	    	}
	    }
	       
	}
}
