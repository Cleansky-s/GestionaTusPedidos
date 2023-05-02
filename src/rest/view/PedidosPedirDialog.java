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
import javax.swing.JLabel;
import javax.swing.JPanel;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

public class PedidosPedirDialog extends JDialog implements GestionObserver {

	private Controller ctrl;
	
	List<Pedido> pedidosMesa = new ArrayList<Pedido>();
	List<Pedido> pedidosDelivery = new ArrayList<Pedido>();
	
	JButton deliveryButton;
	JButton mesaButton;
	
	JDialog deliveryDialog;
	JDialog mesaDialog;
	
	public PedidosPedirDialog(Frame parent, Controller ctrl) {
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
		
		JPanel fillPanel = new JPanel();
		fillPanel.setMaximumSize(new Dimension(120, 120));
		fillPanel.setMinimumSize(new Dimension(120, 120));
		fillPanel.setPreferredSize(new Dimension(120, 120));
		mainPanel.add(fillPanel, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		deliveryButton = new JButton();
		deliveryButton.setBackground(Color.magenta);
		deliveryButton.setMaximumSize(new Dimension(120, 120));
		deliveryButton.setMinimumSize(new Dimension(120, 120));
		deliveryButton.setPreferredSize(new Dimension(120, 120));
		deliveryButton.setText("Delivery");
		deliveryButton.addActionListener((e)->{
			deliveryDialog = new PedidosPedirDeliveryDialog(Utils.getWindow(this), ctrl);
			deliveryDialog = null;
			setVisible(false);
		});
		buttonPanel.add(deliveryButton);
		
		mesaButton = new JButton();
		mesaButton.setBackground(Color.orange);
		mesaButton.setMaximumSize(new Dimension(120, 120));
		mesaButton.setMinimumSize(new Dimension(120, 120));
		mesaButton.setPreferredSize(new Dimension(120, 120));
		mesaButton.setText("Mesa");
		mesaButton.addActionListener((e)->{ 
			mesaDialog = new PedidosPedirMesaDialog(Utils.getWindow(this), ctrl);
			mesaDialog = null;
			setVisible(false);
		});
		buttonPanel.add(mesaButton);
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
		this.add(mainPanel);
		pack();
		setVisible(true);
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
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


}
